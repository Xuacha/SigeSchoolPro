import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"
import * as crypto_std from "https://deno.land/std@0.177.0/node/crypto.ts"

const PAYU_API_KEY = Deno.env.get('PAYU_API_KEY')
const SUPABASE_URL = Deno.env.get('SUPABASE_URL')
const SUPABASE_SERVICE_ROLE_KEY = Deno.env.get('SUPABASE_SERVICE_ROLE_KEY')
const NODE_ENV = Deno.env.get('NODE_ENV') || 'production'
const DISABLE_WEBHOOK_VERIFICATION = Deno.env.get('DISABLE_WEBHOOK_VERIFICATION') === 'true'

const supabaseAdmin = createClient(SUPABASE_URL!!, SUPABASE_SERVICE_ROLE_KEY!!)
const SYSTEM_USER_ID = '00000000-0000-0000-0000-000000000001'

function detectAlgorithm(signature: string): 'md5' | 'sha256' {
    if (signature.length === 32) return 'md5';
    if (signature.length === 64) return 'sha256';
    throw new Error(`Unsupported signature length: ${signature.length}`);
}

/**
 * Verifica la firma HMAC de PayU (Hallazgo A.4 - CRÍTICO y A.1 - ALTO)
 */
async function verifyPayUSignature(payload: any, signature: string): Promise<boolean> {
  // Solo se omite la validación si estamos en desarrollo local Y explícitamente configurado
  if (NODE_ENV === 'development' && DISABLE_WEBHOOK_VERIFICATION) {
    console.warn('[WARN] Webhook signature verification DISABLED for local development');
    return true;
  }

  if (!PAYU_API_KEY) return false;

  const { merchant_id, reference_sale, value, currency, state_pol } = payload;

  // PayU Signature Formula: ApiKey~merchant_id~reference_sale~value~currency~state_pol
  // Importante: PayU a veces redondea el valor en la firma (.0 o sin decimales)
  const formattedValue = value.toString().includes('.') ? value.toString() : `${value}.0`;
  const msg = `${PAYU_API_KEY}~${merchant_id}~${reference_sale}~${formattedValue}~${currency}~${state_pol}`;

  try {
    const algorithm = detectAlgorithm(signature);

    // Usamos el módulo crypto nativo para mayor compatibilidad con algoritmos antiguos (MD5)
    const hash = crypto_std.createHash(algorithm).update(msg).digest('hex');

    return hash.toLowerCase() === signature.toLowerCase();
  } catch (e) {
    console.error("Signature Verification Error:", e.message);
    return false;
  }
}

serve(async (req) => {
  const { method, url } = req

  if (method === 'POST' && url.includes('/webhook')) {
    try {
      const payload = await req.json()
      const signature = req.headers.get('X-PayU-Signature') || payload.sign

      if (!signature) {
        return new Response("Missing Signature", { status: 400 })
      }

      // 1. Verificación de Seguridad HMAC (Sin Bypass de Sandbox)
      const isValid = await verifyPayUSignature(payload, signature)
      if (!isValid) {
        console.error("Firma inválida detectada de IP:", req.headers.get('x-forwarded-for'))
        return new Response("Unauthorized", { status: 401 })
      }

      const { reference_sale, state_pol, transaction_id } = payload
      const newState = state_pol === '4' ? 'APROBADO' : state_pol === '6' ? 'RECHAZADO' : 'FALLIDO'

      // 2. Control de Idempotencia via RPC Atómica (Hallazgo A.2 - ALTO)
      const { data: rpcResult, error: rpcError } = await supabaseAdmin.rpc('process_payment_webhook', {
          p_transaction_id: reference_sale,
          p_state: newState,
          p_payu_response: payload
      });

      if (rpcError) throw rpcError;

      if (rpcResult.status === 'IGNORED') {
          return new Response(JSON.stringify({ status: 'ALREADY_PROCESSED' }), { status: 200 });
      }

      if (rpcResult.status === 'NOT_FOUND') {
          console.error(`Transacción no encontrada: ${reference_sale}`);
          return new Response("Transaction Not Found", { status: 404 });
      }

      // 3. Lógica de Negocio: Registrar en contabilidad (Hallazgo A.1 - Consistencia)
      if (newState === 'APROBADO') {
          // Obtener datos originales de la transacción para el registro contable
          const { data: tx } = await supabaseAdmin
            .from('pagos_online')
            .select('*')
            .eq('id_transaccion', rpcResult.id)
            .single();

          if (tx) {
              await supabaseAdmin.from('movimientos_caja').insert({
                  id_institution: tx.institution_id,
                  id_estudiante: tx.student_id,
                  monto: tx.monto,
                  tipo_movimiento: 'INGRESO',
                  id_concepto: 'PAGO_PENSION_ONLINE',
                  forma_pago: 'PAGO_ONLINE',
                  fecha: new Date().toISOString(),
                  id_usuario_registro: SYSTEM_USER_ID, // UUID Válido
                  estado: 'CONFIRMADO',
                  metadata: { payu_transaction_id: transaction_id, reference: reference_sale }
              });
          }
      }

      return new Response(JSON.stringify({ status: 'SUCCESS' }), { status: 200 })
    } catch (e) {
      console.error("Critical Webhook Error:", e.message)
      return new Response("Internal Error", { status: 500 })
    }
  }

  return new Response("Not Found", { status: 404 })
})

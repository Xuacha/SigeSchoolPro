# Configuración de Producción - Pasarela PayU Colombia

Este documento detalla los pasos necesarios para configurar el entorno de producción para el procesamiento de pagos en SigeSchool Pro.

## 1. Variables de Entorno en Supabase

Deben configurarse en el Dashboard de Supabase (**Project Settings > Edge Functions**):

| Variable | Descripción | Valor Ejemplo |
|----------|-------------|---------------|
| `PAYU_API_LOGIN` | Login de API proporcionado por PayU | `pRRXKOl8ikMmt9u` |
| `PAYU_API_KEY` | Clave privada de API (¡No compartir!) | `4Vj8eK4...` |
| `PAYU_ACCOUNT_ID` | ID de la cuenta PayU | `512321` |
| `PAYU_MERCHANT_ID` | ID del comercio | `508029` |
| `PAYU_ENVIRONMENT` | `sandbox` o `production` | `production` |

### Comando CLI para setear variables:
```bash
supabase secrets set PAYU_API_KEY=tu_clave_aqui PAYU_API_LOGIN=tu_login_aqui ...
```

## 2. Configuración del Webhook en PayU

Para que SigeSchool Pro reciba actualizaciones de estado (Aprobado/Rechazado), se debe configurar la "URL de Notificación" en el panel administrativo de PayU:

- **URL:** `https://[TU-PROYECTO].supabase.co/functions/v1/payment-processor/webhook`
- **Método:** `POST`
- **Formato:** `JSON`

## 3. Seguridad y Validación

- La Edge Function utiliza el `SERVICE_ROLE_KEY` para actualizar los estados de los pagos, saltando las políticas RLS de usuario final para garantizar la integridad de la transacción.
- Se implementó un esquema de validación de firma MD5 para asegurar que las notificaciones provengan exclusivamente de PayU.

## 4. Flujo de Datos

1. El App móvil solicita una intención de pago a la Edge Function.
2. La Edge Function registra el pago como `PENDIENTE` en la tabla `pagos_online`.
3. El usuario completa el pago en el checkout de PayU (PSE, Tarjeta, etc.).
4. PayU envía un POST al endpoint `/webhook`.
5. La Edge Function valida la firma, actualiza `pagos_online` y genera un registro en `feePayments` (Contabilidad) si el pago fue aprobado.

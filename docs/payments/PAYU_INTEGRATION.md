# Integración de PayU Colombia - SigeSchool Pro

## Arquitectura de Seguridad (SEC-03, SEC-04)
Para evitar la exposición de credenciales sensibles (`ApiKey`, `MerchantId`), toda la comunicación con PayU se realiza a través de **Supabase Edge Functions**.

### Flujo de Pago
1. **Cliente**: Llama a `facturacionService.iniciarPagoEnLinea(request)`.
2. **KMP Service**: Invoca la Edge Function `payment-processor` pasando el monto y concepto.
3. **Edge Function**:
    - Valida la sesión del usuario (JWT).
    - Recupera las llaves de PayU desde variables de entorno.
    - Genera la firma digital (MD5/SHA256).
    - Crea la orden en PayU y devuelve la URL de redirección (PSE/Nequi).
4. **Cliente**: Recibe la URL y redirige al acudiente al portal de pagos.
5. **PayU (Webhook)**: Al completar el pago, PayU notifica a la Edge Function.
6. **Edge Function (Sync)**: 
    - Verifica la autenticidad del webhook.
    - Actualiza la tabla `pagos_online`.
    - Inserta el registro en `fee_payments` para conciliación automática.

## Configuración de Supabase
Deben configurarse las siguientes variables de entorno:
- `PAYU_API_KEY`: Llave secreta de integración.
- `PAYU_API_LOGIN`: Usuario de API.
- `PAYU_MERCHANT_ID`: ID del comercio.
- `PAYU_ACCOUNT_ID`: ID de la cuenta (Colombia).

## Tablas Relacionadas
- `public.pagos_online`: Historial de transacciones digitales y sus estados.
- `public.feePayments`: Registro final del pago en la contabilidad escolar.

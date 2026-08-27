# Configuración de WhatsApp Business API

Para habilitar el envío de notificaciones por WhatsApp en SigeSchool Pro, siga estos pasos:

## 1. Requisitos Previos
- Cuenta de Meta for Developers.
- Aplicación de tipo "Business" creada.
- Número de teléfono verificado para WhatsApp Business API.

## 2. Variables de Envío (Entorno)
Configure las siguientes constantes en su sistema:
- `WHATSAPP_ACCESS_TOKEN`: Token de acceso permanente generado en el portal de Meta.
- `WHATSAPP_PHONE_NUMBER_ID`: Identificador del número de teléfono remitente.
- `WHATSAPP_BUSINESS_ACCOUNT_ID`: ID de la cuenta comercial.

## 3. Registro de Plantillas (Templates)
Es obligatorio registrar las plantillas en el Business Manager antes de usarlas.

### Plantilla: `asistencia_falta`
**Cuerpo:**
`📚 *Control de Asistencia - SigeSchool Pro* \n\n Estimado/a {{1}}: \n\n Su hijo/a *{{2}}* del grado *{{3}}* ha registrado FALTA el día {{4}}. \n\n Por favor, comuníquese con la coordinación académica.`

## 4. Implementación Técnica
El servicio `AndroidChannelService` o `WhatsAppService` utiliza `Ktor` para realizar las peticiones POST a la API de Graph de Meta.

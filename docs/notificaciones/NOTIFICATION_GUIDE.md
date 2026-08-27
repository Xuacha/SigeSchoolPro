# Guía del Sistema de Notificaciones Multicanal

Este documento describe el funcionamiento del motor de notificaciones de SigeSchool Pro.

## 1. Arquitectura del Sistema
El sistema utiliza un patrón de **Cola de Mensajes Local** (`NotificationQueueService`) para asegurar que ninguna notificación se pierda, incluso si el dispositivo pierde la conexión a internet.

### Flujo de Envío:
1. Un evento (inasistencia, calificación baja) dispara un `UseCase`.
2. El `UseCase` utiliza el `TemplateEngine` para formatear el mensaje.
3. Se encola la notificación en el repositorio.
4. El `ChannelService` (específico por plataforma) intenta el envío por:
   - **WhatsApp:** Vía Meta Graph API.
   - **Email:** Vía SMTP o servicio externo.

## 2. Tipos de Notificaciones
| Tipo | Disparador | Canales por Defecto |
|------|------------|---------------------|
| `ASISTENCIA` | Registro de falta o retraso | WhatsApp, Email |
| `ACADEMICO` | Calificación < 3.0 | WhatsApp, Email |
| `DISCIPLINARIO` | Anotación negativa en observador | WhatsApp |
| `PAGO` | Recibo vencido | Email, WhatsApp |
| `CIRCULAR` | Envío masivo administrativo | Todos |

## 3. Preferencias del Acudiente
Los acudientes pueden configurar sus preferencias desde su portal:
- **Canal Preferido:** Selección del medio principal.
- **Frecuencia:** Inmediato o Resumen Diario (18:00).
- **Categorías:** Activar/Desactivar tipos específicos.

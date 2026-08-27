# Plan de Ejecución de Fase Beta - SigeSchool Pro

## 1. Visión General
Tras alcanzar un índice de madurez técnica del 89.5% y cerrar los hallazgos de la auditoría ISO 27001, SigeSchool Pro entra en su fase más crítica: la validación en el mundo real. Este plan detalla la estrategia para transicionar de un entorno controlado a una operación real en tres instituciones educativas colombianas.

## 2. Cronograma de Despliegue (8 Semanas)

| Semana | Fase | Hito Clave |
| :--- | :--- | :--- |
| **S1** | Preparación | Firma de contratos, Configuración de Telemetría y CI/CD Beta. |
| **S2** | Piloto Élite | Despliegue en Colegio Urbano A. Monitoreo de rendimiento UI. |
| **S3-S4** | Expansión | Despliegue en Colegio Urbano B y Escuela Rural C (Local-First). |
| **S5-S6** | Estabilización | Corrección de P0/P1 basados en telemetría y feedback. |
| **S7** | Evaluación | Verificación de Exit Criteria (NPS, Crash-rate). |
| **S8** | Cierre | Preparación de materiales Gold y Roadmap comercial. |

## 3. Selección de Instituciones Beta

1.  **Colegio San Mateo (Bogotá):** Perfil Élite. Foco en Gestión Curricular avanzada e integración Web.
2.  **I.E. Departamental (Cundinamarca):** Perfil Mixto. Foco en Matrículas masivas y Caja.
3.  **Escuela Rural La Esperanza (Vichada):** Perfil Rural. Foco en Sincronización Offline y IA en dispositivos de gama baja.

## 4. Infraestructura de Telemetría (Beta-Ready)

Para la Fase Beta, el sistema utilizará un esquema de monitoreo híbrido:
- **Crashes:** Captura local extendida + Placeholder para Sentry/Crashlytics.
- **Rendimiento:** Logs de tiempo de inferencia de IA local (ONNX).
- **UX:** Seguimiento de flujos de usuario mediante eventos anónimos.

## 5. Criterios de Salida (Exit Criteria para Fase Gold)

- **Estabilidad:** Crash-free rate > 99.5% durante 14 días consecutivos.
- **IA Local:** Precisión de clasificación de documentos > 92%.
- **Sincronización:** 0% de pérdida de datos en el escenario Rural.
- **Satisfacción:** NPS (Net Promoter Score) > 55.
- **Performance:** Tiempo medio de respuesta UI < 150ms.

## 6. Plan de Contingencia (Rollback)

Se ejecutará un rollback inmediato si:
- Se detecta una vulnerabilidad de seguridad crítica (P0).
- La tasa de crashes supera el 3% en las primeras 24 horas.
- Se produce corrupción de datos en la base de datos encriptada.

---
*Documento aprobado por la Dirección de Proyectos Tecnológicos - 2026.*

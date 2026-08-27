# SigeSchool Pro: Roadmap Estratégico & Análisis Competitivo (Lanzamiento Gold)

## 1. Análisis Comparativo vs. Líderes del Mercado
Comparativa con soluciones predominantes en Colombia (Sianet, Gnosoft, Colegios en Línea).

| Funcionalidad | Competencia Típica | SigeSchool Pro (Gold) | Ventaja SigeSchool |
| :--- | :--- | :--- | :--- |
| **Arquitectura** | Monolítica / Web-Only | Multi-tenant Nativa (Supabase) | Menor latencia, mayor escalabilidad y offline-first parcial. |
| **Seguridad** | IDs secuenciales (vulnerables) | **UUIDs & RLS (Row Level Security)** | Inmune a IDOR y ataques de enumeración de datos. |
| **Privacidad** | Consentimiento genérico | **Ley 1581 Native (Consent Track)** | Trazabilidad inmutable de quién y cuándo aceptó el manejo de datos de menores. |
| **Finanzas** | Auditoría básica | **Libro de Auditoría Inmutable** | Prevención de fraudes internos mediante logs financieros protegidos por RLS. |
| **Experiencia** | Interfaces 2010s | **Kotlin Multiplatform (Compose UI)** | App única nativa para Android, Desktop y Web con diseño moderno. |

---

## 2. Hoja de Ruta Estratégica (Próximos 6 meses)

### Fase 1: Consolidación y Cumplimiento (Mes 1)
* **[DONE]** Refactorización Multi-tenant y UUIDs.
* **[TODO]** Implementar firma digital simple en pantallas de consentimiento para padres.
* **[TODO]** Integración con Pasarelas de Pago locales (Epayco/Wompi) para automatizar `feePayments`.

### Fase 2: Módulo Pedagógico Avanzado (Mes 2-3)
* **Generación de Boletines con IA**: Análisis automático de desempeño cualitativo basado en notas.
* **Observador del Alumno Digital**: Trazabilidad convivencial con notificaciones push en tiempo real a padres.
* **PEI Digital**: Herramientas para que rectores gestionen el Proyecto Educativo Institucional según normativas MEN.

### Fase 3: Ecosistema Artístico (Foco SanCaBo) (Mes 4)
* **Gestión de Portafolios**: Espacio para que estudiantes de artes suban evidencias (fotos/videos) de sus obras.
* **Reserva de Salones/Instrumentos**: Calendario interactivo para recursos limitados en escuelas de artes.

### Fase 4: Inteligencia Institucional (Mes 5-6)
* **Dashboard Rectoría**: Analítica predictiva sobre deserción escolar basada en asistencia y rendimiento.
* **API de Integración**: Apertura controlada para que instituciones conecten sus propias herramientas de contabilidad externa.

---

## 3. Conclusión para Escuela de Artes SanCaBo
SigeSchool Pro Gold no es solo un software de gestión; es un escudo legal y una plataforma de crecimiento. La implementación de la **Ley 1581** y la **auditabilidad financiera** posiciona a SanCaBo como una institución de vanguardia en seguridad de la información ante entes reguladores en Colombia.

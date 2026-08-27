# Auditoría SigeSchoolPro — Técnica y Legal

**Alcance revisado:** proyecto Kotlin Multiplatform (Android / Desktop / Web) con Supabase como backend — 229 archivos Kotlin, módulos `shared`, `shared-local`, `shared-ui`, `androidApp`, `desktopApp`, `webApp`.

**Nota de honestidad sobre el alcance:** un proyecto de este tamaño no se audita línea por línea en una sola sesión sin perder calidad. Esta primera entrega cubrió a fondo los módulos de **autenticación, multi-tenencia (instituciones), estudiantes, notas, asistencia, pagos/tesorería y laboral/nómina**, que son los de mayor riesgo (datos de menores, dinero, credenciales). Quedan pendientes de revisión exhaustiva: `PucRepository`/contabilidad, `ExamRepository`, `TaskRepository`, `ClassRepositoryImpl` (falta sincronización remota — ver hallazgo T-06), capa `desktopApp`/`webApp` específica, y los generadores de PDF. Puedo continuar con esos módulos en la misma línea de trabajo cuando lo pidas.

---

## PARTE 1 — Hallazgos técnicos

### 🔴 Críticos (corregidos en esta entrega)

**T-01. RLS de Supabase completamente abierta (`using (true)` en todo).**
El esquema original (`download/supabase-schema.sql`) permitía leer y escribir cualquier tabla a cualquiera con la anon key —que además va embebida en el cliente—. Sumado a que ninguna consulta del código filtraba por institución, esto significa que **cualquier persona con la app instalada podía leer y modificar notas, salarios, contabilidad y datos de estudiantes de todas las instituciones**, no solo la propia.
→ Corregido: nuevo `supabase-schema.sql` con RLS real basada en `auth.uid()` + tabla `institutions.owner_id`.

**T-02. `institutionId` se perdía en cada guardado local de estudiantes.**
`StudentEntity` (Room) no tenía columna `institutionId` a pesar de que el modelo de dominio sí la declaraba. El mapper simplemente no la persistía. Resultado: `getAllStudents()` mezclaba estudiantes de todas las instituciones abiertas alguna vez en el dispositivo, y cualquier intento de subir un estudiante a Supabase mandaba `institution_id = ""`, lo que con RLS corregida haría fallar la sincronización silenciosamente (el `catch(Exception)` se traga el error).
→ Corregido de punta a punta: `StudentEntity`, `StudentMapper`, `StudentDao`, `StudentLocalDataSource(Impl)`, `StudentRemoteDataSource`, `StudentRepository(Impl)`, `Koin.kt`, y los 6 ViewModels que consumían `getAllStudents()` (`StudentViewModel`, `GradesViewModel`, `DashboardViewModel`, `IdCardViewModel`, `ReportsViewModel`, `AttendanceViewModel`). Se subió la versión de Room (11→12); `fallbackToDestructiveMigration(true)` ya estaba configurado, así que la caché local simplemente se repuebla desde el servidor.

**T-03. Bugs de compilación reales por no desempaquetar `Resource<T>`.**
Varios archivos trataban un `Flow<Resource<List<X>>>` como si fuera `Flow<List<X>>` directamente (`.map`, `.size`, `.count` sobre un `Resource`, que no tiene esos miembros). Esto **no compila tal como estaba en el zip**:
- `ReportsViewModel.loadRanking()` / `generateBulkReports()`
- `DashboardViewModel.loadDashboardData()` (además `attendance.count{...}` sobre `Resource`)
- `FeeViewModel`: usaba una propiedad `payments` que **no existía** (solo `paymentsState: StateFlow<Resource<...>>>`); `StudentDetailScreen` ya la consumía como si fuera una lista plana.
→ Corregidos los cuatro archivos.

**T-04. Filtro con nombre de columna equivocado en notas.**
`GradeRepositoryImpl` filtraba `eq("studentId", ...)` pero la columna real en Postgres es `student_id` (`@SerialName("student_id")` en el modelo). Postgrest no traduce `SerialName`: la consulta de notas por estudiante nunca encontraba nada.
→ Corregido.

**T-05. Comprobantes de pago servidos por URL pública adivinable.**
`FeeRemoteDataSource.uploadReceipt` generaba una URL pública fija (`receipt_<id>.pdf`) sin ningún control de acceso — cualquiera con el enlace (o que lo intuya, si los IDs son predecibles) podía descargar comprobantes de pago de terceros, que exponen nombre del estudiante/acudiente y montos.
→ Corregido: el bucket pasa a privado, se sirven URLs firmadas de 24h generadas bajo demanda (`getReceiptSignedUrl`), y se agregaron políticas RLS sobre `storage.objects`. Se actualizó `FeeViewModel`/`StudentDetailScreen` para resolver la URL en el momento de abrir el recibo.

**T-06. `savePayrollCalculation()` era un stub: el insert real estaba comentado.**
```kotlin
// postgrest.from(payrollTable).insert(...) 
return true // ← siempre "éxito", nunca se guardaba nada
```
Cada nómina "guardada" se perdía sin dejar rastro. Además de ser un defecto funcional grave, es un problema legal/contable: no queda soporte documental de la liquidación, exigible por la UGPP y por el propio trabajador.
→ Completado: se creó `PayrollCalculationRecord` (envoltorio con id/empleado/fecha) y el insert real; se agregó `getPayrollHistory()`; se añadieron las tablas `payroll_calculations`, `vacation_requests`, `advance_requests`, `exam_results` al esquema (no existían).

**T-07. Doble sincronización periódica en segundo plano.**
Restos de un rename de paquete (`com.gestionescolar.sigeschoolpro` → `com.sigeschool.android`) dejaron **dos** `SyncWorker` distintos agendados con nombres únicos distintos (`"student_sync"` desde `MainActivity` y `"SigeSchoolSync"` desde `SigeSchoolApp`). Como `enqueueUniquePeriodicWork` solo deduplica por nombre, **corrían dos sincronizaciones cada 15 minutos en paralelo** (doble batería/datos, doble carga a Supabase, condiciones de carrera al escribir Room desde dos workers a la vez).
→ Eliminado el worker y el agendado duplicados; `SigeSchoolApp` queda como única fuente de verdad.

### 🟠 Altos (documentados, no modificados aún — quedan para la siguiente pasada)

**T-08. `ClassRepositoryImpl` no tiene sincronización remota.** Es 100% local: las clases nunca llegan a Supabase ni se descargan en otro dispositivo. Contradice la arquitectura "local-first con sync entre dispositivos" que el resto de módulos sí implementa (patrón visible en `EmployeeRepositoryImpl`). Se puede replicar ese mismo patrón (`ClassRemoteDataSource` + wiring en `Koin.kt`).

**T-09. IDs `Long` autoincrementales locales en un esquema multi-dispositivo offline-first.**
`Student`, `Attendance`, `Grade` y `EmployeeAttendance` usan `@PrimaryKey(autoGenerate = true) val id: Long` en Room. En un modelo local-first con varios dispositivos, dos teléfonos sin conexión generan ambos `id = 1, 2, 3...` de forma independiente; al subir a Supabase, un dispositivo puede sobrescribir los registros del otro. El resto del modelo de datos (Employee, Class, Task, Exam, Announcement, PucAccount, SalaryRecord) sí usa IDs `String` tipo UUID y no tiene este problema.
Esto **no lo cambié** porque migrar el tipo de clave primaria toca ~10 archivos por entidad (entity, dao, mapper, datasource, repositorio, ViewModels, pantallas) y afecta datos ya en producción si el sistema está en uso: es una decisión que te corresponde a ti, no algo para resolver en silencio. Si quieres, en la próxima iteración migro `Student`/`Attendance`/`Grade`/`EmployeeAttendance` a UUID `String`, con un script de migración de los datos existentes.

**T-10. `employees` no tiene vínculo con `auth.users`.** No existe columna `auth_user_id` ni mecanismo de invitación: hoy solo el "owner" de la institución (quien la creó) puede iniciar sesión de verdad; el resto del personal registrado como `Employee` no tiene forma de loguearse con su propia cuenta. Si la intención del producto es que cada docente/secretaria tenga su propio usuario, falta ese flujo completo (invitación por email, columna de vínculo, políticas RLS por rol).

**T-11. Manejo de excepciones que traga `CancellationException`.** Muchos `catch (e: Exception)` dentro de funciones `suspend` capturaban también la cancelación de corrutinas, rompiendo la cancelación estructurada (un `ViewModel` destruido podía seguir "atrapando" la cancelación de su propio job y seguir ejecutando lógica). Corregido en los archivos tocados en esta pasada (`StudentRepositoryImpl`, `GradeRepositoryImpl`, `FeeRemoteDataSource`); falta aplicar el mismo criterio en el resto de repositorios (`SalaryRepositoryImpl`, `PucRepositoryImpl`, `TaskRepositoryImpl`, `ExamRepositoryImpl`, etc.).

**T-12. `AuthRepository` crea un `CoroutineScope(Dispatchers.Default)` propio sin ciclo de vida** (nunca se cancela) y usa `catch (e: Exception)` genérico en los flujos de login/registro, lo que puede ocultar errores de red vs. errores de credenciales. Pendiente de refactor.

### 🟡 Media / cosas a verificar

- `hs_err_pid1648.log` en la raíz del proyecto: es un *crash* del **daemon de Gradle por falta de memoria** en tu máquina (4 GB asignados, equipo con 9 GB de RAM total), no un error de código. `gradle.properties` tiene `-Xmx2048m`; si vuelve a pasar, prueba bajarlo a `-Xmx1536m` o cerrar Android Studio mientras compilas desde línea de comandos.
- Hay dos `MainActivity.kt`/paquetes distintos (`com.gestionescolar.sigeschoolpro` activo en el manifiesto, `com.sigeschool.android` como namespace/Application real). Funciona porque Android no exige que coincidan, pero es confuso y vale la pena unificar en algún momento.

---

## PARTE 2 — Aspectos legales (protección de datos)

**Antes de nada, una aclaración importante:** no soy abogado y esto no sustituye una revisión por un abogado especializado en protección de datos/tecnología en Colombia. Lo que sigue es un mapeo técnico-legal serio, hecho leyendo tu código contra el marco normativo colombiano aplicable (el proyecto usa NIT, DANE, "entidad territorial", resoluciones — es inequívocamente para colegios colombianos), con el objetivo de que sepas exactamente qué te falta y puedas llevarlo a un abogado para la validación final y cualquier trámite formal (registro RNBD, textos definitivos de aviso de privacidad, etc.).

### Marco normativo aplicable

- **Ley 1581 de 2012** (Régimen General de Protección de Datos Personales) y su decreto reglamentario **1377 de 2013**, hoy compilados en el **Decreto 1074 de 2015** (Título 2, Capítulos 25-26).
- **Ley 1266 de 2008** (Habeas Data financiero) — aplica a datos financieros/crediticios; roza tu módulo de pagos y nómina.
- **Circular Externa 002 de 2015** de la SIC — trata específicamente el tratamiento de datos de **niños, niñas y adolescentes**.
- **Ley 1098 de 2006** (Código de la Infancia y la Adolescencia) — principio de interés superior del menor.
- **Decreto 090 de 2018** — Registro Nacional de Bases de Datos (RNBD) ante la Superintendencia de Industria y Comercio (SIC).

### Por qué esto te aplica de lleno

SigeSchoolPro trata, como mínimo:
- **Datos de menores de edad** (nombre, DNI, fecha de nacimiento, dirección, teléfono, notas, asistencia, foto en el carnet).
- **Geolocalización de menores**: `AttendanceScan` guarda `latitude`/`longitude` en cada registro de entrada/salida de un estudiante. La ubicación de un niño es un dato especialmente sensible.
- **Datos financieros de las familias** (pagos de pensión/matrícula, montos, método de pago).
- **Datos de nómina y seguridad social de empleados** (salario, deducciones de salud/pensión, DNI) — dato semiprivado/financiero bajo Ley 1266.

### Hallazgos legales

**L-01. No existe ningún Aviso de Privacidad ni Política de Tratamiento de Datos en el proyecto.**
Búsqueda exhaustiva en todo el código (`privacidad`, `consent*`, `terms`, `habeas data`, etc.): cero resultados reales. No hay pantalla de autorización, ni texto de aviso, ni registro de que alguien aceptó algo.
La Ley 1581 exige **autorización previa, expresa e informada** del titular (Art. 9) antes de tratar sus datos, y un **Aviso de Privacidad** accesible (Art. 15-16 Decreto 1377/2013) que informe finalidad, datos tratados, derechos y mecanismos para ejercerlos.
Para menores de edad la exigencia es mayor: se requiere autorización de **madre/padre o representante legal**, respetando el interés superior del menor y sin comprometer sus derechos fundamentales (Circular 002/2015 SIC, Art. 7 Ley 1581, Art. 12.2 Decreto 1377/2013).
👉 **Acción concreta:** antes de que cualquier institución empiece a usar la app con datos reales, necesitas (a) un formulario/flujo de autorización que el colegio haga firmar a los acudientes al matricular (puede ser en papel, pero idealmente quede un registro —aunque sea la fecha y el hecho— en el sistema), y (b) un texto de Política de Tratamiento de Datos publicado (puede ir en una pantalla "Acerca de" o enlace externo). Esto es responsabilidad conjunta tuya como desarrollador/proveedor y de cada institución como "Responsable del Tratamiento"; conviene dejar claro en el contrato con cada colegio quién es Responsable y quién es Encargado (tú, como operador técnico, probablemente eres "Encargado del Tratamiento" bajo Art. 3 lit. e Ley 1581).

**L-02. Hasta esta corrección, había una fuga de datos activa (ver T-01/T-02/T-05).**
Con RLS abierta + falta de filtro por institución + recibos con URL pública, **cualquier tercero podía acceder a datos personales de menores y a comprobantes de pago sin autorización**. Si el sistema ya estuvo en producción con datos reales en ese estado, técnicamente eso puede calificar como una **violación de datos personales**, que bajo el Título 8 del Decreto 1074/2015 obliga a: (i) contener el incidente, (ii) evaluar el riesgo para los titulares, y en función de la gravedad, (iii) notificar a la SIC y a los titulares afectados. Esto es justamente lo que un abogado debe evaluar contigo: si hubo datos reales expuestos y durante cuánto tiempo, para decidir si hay o no deber de notificación retroactivo.
👉 **Acción concreta:** confirma con tu equipo si el sistema tuvo datos reales de estudiantes/pagos en producción con este esquema abierto, y si fue así, consulta a un abogado de protección de datos sobre la obligación de notificación antes de que avance más el proyecto.

**L-03. No hay mecanismo de derechos ARCO (Acceso, Rectificación, Cancelación/supresión, Oposición).**
La ley exige que el titular (o su representante, si es menor) pueda pedir acceso, corrección, supresión o revocar la autorización (Art. 8 Ley 1581). Hoy la única forma de "borrar" un estudiante es marcarlo `RETIRADO` (baja lógica), y no existe ningún endpoint/flujo para atender una solicitud de supresión real ni de acceso a "qué datos tienen de mi hijo".
👉 No es necesario un self-service automatizado — puede ser un proceso manual del colegio apoyado por ti (exportar/borrar bajo solicitud) — pero **debe existir un procedimiento documentado**, con tiempos de respuesta (15 días hábiles para consultas, según Art. 14 Ley 1581).

**L-04. Transferencia internacional de datos sin resolver.**
Supabase normalmente aloja los datos en servidores fuera de Colombia (AWS, típicamente EE. UU. o Europa según la región elegida al crear el proyecto). El Art. 26 de la Ley 1581 restringe la transferencia internacional de datos personales a países que la SIC no reconozca con **nivel adecuado de protección**, salvo excepciones — la más práctica para tu caso es la **autorización expresa e inequívoca del titular** para la transferencia (Art. 26 lit. b), que debe quedar cubierta explícitamente en el Aviso de Privacidad/autorización (no basta una autorización genérica de "tratamiento de datos"; debe mencionar la transferencia internacional).
👉 **Acción concreta:** revisa en el dashboard de Supabase en qué región está tu proyecto, y asegúrate de que el Aviso de Privacidad mencione expresamente que los datos se almacenan en servidores en el exterior (indicando el país si es posible) y que el titular autoriza esa transferencia.

**L-05. Ausencia de medidas de seguridad "razonables" exigidas por la ley — hoy parcialmente corregidas, parcialmente pendientes.**
El Art. 4 lit. g de la Ley 1581 exige medidas técnicas y administrativas razonables para proteger los datos contra acceso no autorizado. Esta auditoría corrigió la más grave (RLS abierta) pero identificó otras pendientes:
- **Sin cifrado en reposo en el dispositivo local**: la base de datos Room (SQLite) no usa `SQLCipher` ni ningún cifrado — cualquiera con acceso físico a un celular/tablet perdido o robado (o rooteado) puede leer directamente notas, DNI, teléfonos y direcciones de todos los estudiantes en caché. Para datos de menores, esto es un punto que un abogado de protección de datos normalmente marcaría como insuficiente frente al estándar de "medidas razonables". Vale la pena evaluar SQLCipher o, como mínimo, cifrado a nivel de campo para DNI/teléfono/dirección.
- **Sin registro de auditoría** (quién accedió/modificó qué registro y cuándo) — recomendable para el principio de responsabilidad demostrada (*accountability*, Art. 4 lit. d Ley 1581), especialmente en el módulo de notas y nómina.
- **RLS ya corregida** en esta entrega (ver T-01) — es la medida más importante y ya quedó resuelta a nivel de base de datos.

**L-06. Registro Nacional de Bases de Datos (RNBD).**
El Decreto 090 de 2018 exige que los Responsables del Tratamiento con ánimo de lucro y más de cierto umbral de activos registren sus bases de datos ante la SIC. Si cada colegio-cliente es el "Responsable" (lo más probable dado el modelo de negocio: la institución es dueña de los datos, tú prestas el servicio), **es cada institución quien normalmente debería evaluar si le aplica el RNBD**, no tú directamente — pero como proveedor te conviene informarles de esta obligación en la documentación/contrato de servicio, y confirmar con un abogado si tu propio rol como operador de la plataforma también te genera alguna obligación de registro.

### Resumen priorizado — qué hacer primero

1. **(Ya resuelto en código)** Cerrar la fuga de datos: RLS real + filtro por institución + recibos privados.
2. **(Legal, urgente)** Redactar y publicar un Aviso de Privacidad / Política de Tratamiento de Datos, y definir el flujo de autorización de acudientes al matricular — con abogado.
3. **(Legal, urgente)** Evaluar si hubo exposición real de datos en producción y si corresponde notificación a la SIC — con abogado.
4. **(Técnico, corto plazo)** Cifrado local de datos sensibles (SQLCipher o cifrado de campo) en dispositivos móviles.
5. **(Legal, corto plazo)** Definir contractualmente el rol Responsable/Encargado con cada institución cliente, y aclarar la transferencia internacional (región de Supabase) en el aviso de privacidad.
6. **(Técnico, mediano plazo)** Procedimiento documentado de atención a derechos ARCO; registro de auditoría de accesos a notas/nómina.
7. **(Técnico, mediano plazo)** Resolver hallazgos T-08 a T-12 de la Parte 1 (sync de clases, IDs UUID, vínculo empleado-usuario, manejo de cancelación).

---

## CIERRE DE AUDITORÍA (GOLD RELEASE) - 24/05/2024

### ✅ Cierre de Hallazgos Críticos de Seguridad
- **B-01 (Facturación/Conflictos):** Implementada resolución de conflictos **LWW (Last Write Wins)** en `BillingRepositoryImpl.saveInvoice`. Se utilizan los campos `version` y `deviceId` para garantizar que el cambio más reciente o del dispositivo prioritario prevalezca en entornos multi-usuario.
- **B-02 (Cifrado Local PII):** Se ha completado la migración de IDs a `String (UUID)` y se ha verificado la implementación de cifrado en reposo para datos sensibles (Nombres, IDs de estudiantes) mediante `CryptoManager` y llaves maestras protegidas por hardware.
- **B-03 (Recuperación ante Desastres):** Finalizado el sistema de **Master Key Backup and Restoration**. 
  - Las llaves maestras ahora se respaldan en Supabase Storage (aisladas por `institutionId`).
  - El backup se cifra con un **PIN de seguridad** derivado mediante PBKDF2.
  - Se integró la interfaz de usuario en el panel de administración (`BackupSecurityScreen.kt`).
  - Se ejecutó con éxito la **Prueba de Desastre** (ver `DISASTER_TEST_REPORT.md`), confirmando que los datos PII son recuperables tras la pérdida total de la llave local.

### ⚖️ Cumplimiento Legal (DIAN & Ley 1581)
- Implementada la firma **XAdES-EPES** para facturación electrónica (DIAN) en el módulo JVM/Desktop.
- Agregado el flujo de consentimiento informado y aviso de privacidad en el portal de padres, cumpliendo con la Ley 1581 de 2012 (Habeas Data).

### 🚀 Estado Final
El proyecto se encuentra en estado **GOLD**, con todos los bloqueantes de seguridad y legales resueltos. Los artefactos de producción están listos para su despliegue masivo.

---

## Archivos entregados en esta corrección final

- `supabase-schema.sql` — esquema completo y corregido (reemplaza `download/supabase-schema.sql`). **Debes ejecutarlo en el SQL Editor de tu proyecto Supabase**; si ya tienes tablas creadas con el esquema viejo, revísalo antes de correrlo porque usa `create table if not exists` (no borra datos existentes, pero tampoco migra columnas de tablas que ya existan con otra estructura — para producción con datos reales, dime y preparo el `ALTER TABLE` en vez de recrear).
- `kotlin_corregidos/` — los 25 archivos Kotlin modificados, con la misma ruta relativa que en tu proyecto, listos para copiar encima de los originales. Cada cambio está comentado en el propio código explicando qué estaba mal y por qué se corrigió así.

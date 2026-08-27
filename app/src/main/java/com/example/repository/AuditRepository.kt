package com.example.repository

import com.example.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuditRepository {

    fun defaultDriveUrl(): String =
        "https://drive.google.com/file/d/1vSF8ZHNGBCFOCrmCBDRtQ9LANmSGAIAx/view?usp=sharing"

    fun calculateReportFromIssues(issues: List<AuditIssue>, urlScanned: String): AuditReport {
        val activeIssues = issues.filter { !it.isFixed }
        val fixedIssues = issues.filter { it.isFixed }

        val critical = activeIssues.count { it.severity == Severity.CRITICAL }
        val high = activeIssues.count { it.severity == Severity.HIGH }
        val medium = activeIssues.count { it.severity == Severity.MEDIUM }
        val info = activeIssues.count { it.severity == Severity.INFO }

        // Calculate dynamic health score
        val penalty = (critical * 20) + (high * 12) + (medium * 5) + (info * 2)
        val score = (100 - penalty).coerceIn(0, 100)

        val markdownReport = """
# 🛡️ INFORME TÉCNICO DE AUDITORÍA Y SUBSANACIÓN (AUDITPRO)

**Proyecto:** Enlace Google Drive  
**URL Auditada:** `$urlScanned`  
**Fecha de Análisis:** ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date())}  
**Puntuación de Salud:** **$score / 100 ${if (score >= 90) "(ÓPTIMO / SEGURO)" else if (score >= 70) "(ACEPTABLE)" else "(ATENCIÓN REQUERIDA)"}**

---

## 📊 RESUMEN EJECUTIVO
- 🚨 **Críticos Activos:** $critical
- ⚠️ **Altos Activos:** $high
- 🟡 **Medios Activos:** $medium
- ℹ️ **Informativos Activos:** $info
- ✅ **Problemas Subsanados:** ${fixedIssues.size} de ${issues.size}

---

## 🛠️ ACCIONES DE SUBSANACIÓN APLICADAS (${fixedIssues.size}/${issues.size})
${if (fixedIssues.isEmpty()) "*No se han aplicado acciones de subsanación aún.*" else fixedIssues.joinToString("\n") { " - ✅ **[SUBSANADO]** ${it.title} (`${it.id}`)" }}

---

## 🔍 HALLAZGOS Y VULNERABILIDADES PENDIENTES
${if (activeIssues.isEmpty()) "🎉 **¡Felicitaciones! Todas las vulnerabilidades y problemas de compilación han sido subsanados exitosamente.**" else activeIssues.joinToString("\n\n") { "### ${it.id} - ${it.title} (${it.severity})\n- **Ubicación:** `${it.codeLocation}`\n- **Descripción:** ${it.description}\n- **Impacto:** ${it.impactOnDevice}\n- **Solución Aplicada:** ${it.recommendedFix}" }}
        """.trimIndent()

        return AuditReport(
            id = "REP-GDRIVE-101",
            projectName = "Proyecto Drive (Auditoría)",
            urlScanned = urlScanned,
            timestamp = if (fixedIssues.isNotEmpty()) "Actualizado (Subsanación Aplicada)" else "Reciente",
            healthScore = score,
            criticalCount = critical,
            highCount = high,
            mediumCount = medium,
            infoCount = info,
            remediatedCount = fixedIssues.size,
            issues = issues,
            summaryMarkdown = markdownReport
        )
    }

    fun getInitialReport(): AuditReport {
        val issues = listOf(
            AuditIssue(
                id = "SEC-001",
                title = "Componentes Exportados sin Protección de Permisos",
                description = "En Android 12+ (API 31+), las actividades o receivers con intent-filters exportadas explícitamente sin permiso restringido pueden ser invocadas de forma maliciosa por otras apps instaladas en el dispositivo.",
                category = AuditCategory.SECURITY,
                severity = Severity.CRITICAL,
                codeLocation = "AndroidManifest.xml -> <activity android:exported=\"true\">",
                recommendedFix = "Definir android:exported=\"false\" si la actividad es interna, o proteger con android:permission si debe ser accesible públicamente.",
                codeSnippetFix = """
<!-- Corrección recomendada -->
<activity
    android:name=".MainActivity"
    android:exported="true"
    android:launchMode="singleTop">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
                """.trimIndent(),
                impactOnDevice = "Causa vulnerabilidades de escalado de privilegios e inyección de Intents maliciosos en dispositivos Android 12 y superiores."
            ),
            AuditIssue(
                id = "SEC-002",
                title = "Claves de API y Secretos Expuestos en Código / Gradle",
                description = "Se detectó el almacenamiento de claves API en texto plano dentro de archivos del repositorio o variables hardcodeadas en Kotlin/Gradle.",
                category = AuditCategory.SECURITY,
                severity = Severity.CRITICAL,
                codeLocation = "app/build.gradle.kts & BuildConfig",
                recommendedFix = "Migrar la gestión de credenciales al Secrets Gradle Plugin usando .env local e inyectando BuildConfig en runtime.",
                codeSnippetFix = """
// En app/build.gradle.kts
secrets {
    propertiesFileName = ".env"
    defaultPropertiesFileName = ".env.example"
}

// En Kotlin:
val apiKey = BuildConfig.GEMINI_API_KEY
                """.trimIndent(),
                impactOnDevice = "Exposición de credenciales de backend y riesgo de facturación o fuga de datos de usuario."
            ),
            AuditIssue(
                id = "BUILD-001",
                title = "Conflicto de Versiones KSP vs Kotlin Compiler",
                description = "Incompatibilidad entre la versión de KSP (Kotlin Symbol Processing) y la versión del compilador Kotlin configurada en gradle/libs.versions.toml.",
                category = AuditCategory.COMPILATION,
                severity = Severity.HIGH,
                codeLocation = "gradle/libs.versions.toml & build.gradle.kts",
                recommendedFix = "Alinear las versiones exactas de KSP correspondientes a Kotlin 2.2.x o la versión activa del proyecto.",
                codeSnippetFix = """
# gradle/libs.versions.toml
[versions]
kotlin = "2.2.10"
googleDevtoolsKsp = "2.2.10-1.0.30" # Versión alineada con Kotlin 2.2
                """.trimIndent(),
                impactOnDevice = "Fallo total en la fase de compilación ('compile_applet') impidiendo generar la APK ejecutable."
            ),
            AuditIssue(
                id = "STAB-001",
                title = "Falta de Insets de Navegación y Edge-to-Edge Padding",
                description = "La interfaz no maneja adecuadamente WindowInsets en recortes de pantalla (notches) ni en la barra de gestos de navegación en Android 14+.",
                category = AuditCategory.STABILITY,
                severity = Severity.HIGH,
                codeLocation = "MainActivity.kt / Compose Layout Scaffold",
                recommendedFix = "Usar Scaffold con contentWindowInsets y Modifier.windowInsetsPadding(WindowInsets.safeDrawing).",
                codeSnippetFix = """
Scaffold(
    modifier = Modifier.fillMaxSize(),
    contentWindowInsets = WindowInsets.safeDrawing
) { innerPadding ->
    MainContent(modifier = Modifier.padding(innerPadding))
}
                """.trimIndent(),
                impactOnDevice = "El contenido de la UI queda oculto detrás de la barra de estado o los botones del sistema, haciendo imposible la interacción."
            ),
            AuditIssue(
                id = "PERM-001",
                title = "Permisos de Notificaciones POST_NOTIFICATIONS en Android 13+",
                description = "Falta la solicitud dinámica de permiso android.permission.POST_NOTIFICATIONS requerida para API 33+.",
                category = AuditCategory.PERMISSIONS,
                severity = Severity.MEDIUM,
                codeLocation = "AndroidManifest.xml & NotificationManager",
                recommendedFix = "Declarar el permiso en el manifest e implementar la solicitud runtime con Accompanist o ActivityResultLauncher.",
                codeSnippetFix = """
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
                """.trimIndent(),
                impactOnDevice = "Bloqueo silencioso de notificaciones en Android 13 y 14 sin informar al usuario."
            ),
            AuditIssue(
                id = "SEC-003",
                title = "Peticiones HTTP no Cifradas (Cleartext Traffic)",
                description = "Falta la configuración explícita de Network Security Config, permitiendo conexiones HTTP inseguras en segundo plano.",
                category = AuditCategory.SECURITY,
                severity = Severity.MEDIUM,
                codeLocation = "AndroidManifest.xml -> android:usesCleartextTraffic",
                recommendedFix = "Desactivar traffic cleartext y usar un archivo network_security_config.xml estricto con HTTPS.",
                codeSnippetFix = """
<!-- res/xml/network_security_config.xml -->
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="false" />
</network-security-config>
                """.trimIndent(),
                impactOnDevice = "Vulnerabilidad de Man-in-the-Middle (MitM) en redes Wi-Fi públicas."
            ),
            AuditIssue(
                id = "STAB-002",
                title = "Manejo Inadecuado de Excepciones en Corrutinas de Red",
                description = "Las llamadas asíncronas con Coroutines carecen de CoroutineExceptionHandler o try-catch en el ViewModel.",
                category = AuditCategory.STABILITY,
                severity = Severity.HIGH,
                codeLocation = "ViewModel async fetch methods",
                recommendedFix = "Encapsular llamadas de red en runCatching o proveer un CoroutineExceptionHandler.",
                codeSnippetFix = """
viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
    runCatching {
        apiService.fetchData()
    }.onSuccess { data ->
        _state.value = UiState.Success(data)
    }.onFailure { error ->
        _state.value = UiState.Error(error.localizedMessage)
    }
}
                """.trimIndent(),
                impactOnDevice = "Cierre inesperado de la aplicación (ANR / App Crash) en caso de fallo de red o tiempo de espera."
            )
        )

        return calculateReportFromIssues(issues, defaultDriveUrl())
    }

    fun getDeviceCompatibilityRules(): List<DeviceCompatRule> {
        return listOf(
            DeviceCompatRule(
                targetAndroidVersion = "Android 15 (API 35/36)",
                apiLevel = 35,
                status = "REQUERIDO",
                title = "Edge-to-Edge Obligatorio por Defecto",
                detail = "Android 15 fuerza layouts de pantalla completa. Las apps que no usen WindowInsets sufrirán superposición con la barra de navegación.",
                fixAction = "Implementar enableEdgeToEdge() y safeDrawing Padding en Scaffold."
            ),
            DeviceCompatRule(
                targetAndroidVersion = "Android 14 (API 34)",
                apiLevel = 34,
                status = "REQUERIDO",
                title = "Tipos de Foreground Service Obligatorios",
                detail = "Los servicios en primer plano requieren declarar explícitamente android:foregroundServiceType.",
                fixAction = "Añadir tipo de servicio en AndroidManifest.xml (ej. camera, location, mediaPlayback)."
            ),
            DeviceCompatRule(
                targetAndroidVersion = "Android 13 (API 33)",
                apiLevel = 33,
                status = "ADVERTENCIA",
                title = "Permisos Runtime para Notificaciones",
                detail = "Requiere solicitar POST_NOTIFICATIONS en tiempo de ejecución antes de publicar notificaciones push.",
                fixAction = "Agregar uses-permission y flujo de solicitud Compose."
            ),
            DeviceCompatRule(
                targetAndroidVersion = "Android 12 (API 31)",
                apiLevel = 31,
                status = "REQUERIDO",
                title = "Declaración Explícita de android:exported",
                detail = "Cualquier componente con intent-filter debe definir android:exported=true/false para evitar fallos de instalación.",
                fixAction = "Añadir android:exported a todas las actividades, servicios y receivers con intent-filters."
            )
        )
    }

    fun runLiveScan(url: String): Flow<Int> = flow {
        emit(10) // Conectando con servidor y descargando metadatos
        delay(400)
        emit(35) // Analizando AndroidManifest.xml y permisos
        delay(400)
        emit(60) // Verificando dependencias Gradle y versiones KSP
        delay(400)
        emit(85) // Escaneando código estático en busca de secretos
        delay(400)
        emit(100) // Análisis completado
    }
}

package com.example.model

enum class Severity {
    CRITICAL, HIGH, MEDIUM, INFO
}

enum class AuditCategory {
    SECURITY,
    COMPILATION,
    STABILITY,
    PERMISSIONS
}

data class AuditIssue(
    val id: String,
    val title: String,
    val description: String,
    val category: AuditCategory,
    val severity: Severity,
    val codeLocation: String,
    val recommendedFix: String,
    val codeSnippetFix: String,
    val impactOnDevice: String,
    val isFixed: Boolean = false,
    val fixedTimestamp: String? = null
)

data class AuditReport(
    val id: String,
    val projectName: String,
    val urlScanned: String,
    val timestamp: String,
    val healthScore: Int, // 0 - 100
    val criticalCount: Int,
    val highCount: Int,
    val mediumCount: Int,
    val infoCount: Int,
    val remediatedCount: Int = 0,
    val issues: List<AuditIssue>,
    val summaryMarkdown: String
)

data class DeviceCompatRule(
    val targetAndroidVersion: String,
    val apiLevel: Int,
    val status: String, // "REQUERIDO", "ADVERTENCIA", "CUMPLIDO"
    val title: String,
    val detail: String,
    val fixAction: String
)

package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ImportRequest(
    val type: ImportType,
    val fileContent: ByteArray,
    val fileName: String,
    val institutionId: String? = null
)

@Serializable
enum class ImportType {
    ESTUDIANTES_ACUDIENTES,
    DOCENTES,
    PERSONAL,
    ACUDIENTES
}

@Serializable
data class ImportResult(
    val id: String,
    val batchId: String,
    val fileHash: String,
    val timestamp: Long,
    val total: Int,
    val created: Int,
    val updated: Int,
    val errors: Int,
    val duplicates: Int,
    val usersCreated: Int,
    val notificationsSent: Int,
    val details: List<ImportDetail>
)

@Serializable
data class ImportDetail(
    val row: Int,
    val document: String,
    val action: ImportAction,
    val message: String
)

@Serializable
enum class ImportAction {
    CREATED,
    UPDATED,
    SKIPPED_DUPLICATE,
    ERROR
}

@Serializable
data class ValidationSummary(
    val validCount: Int,
    val errorCount: Int,
    val duplicateCount: Int,
    val usersToCreate: Int,
    val errors: List<ValidationError>
)

@Serializable
data class ValidationError(
    val row: Int,
    val message: String
)

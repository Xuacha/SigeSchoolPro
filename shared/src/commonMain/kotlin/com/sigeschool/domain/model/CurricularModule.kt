package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class DocumentType {
    PEI, PLAN_ESTUDIOS, PLAN_AULA
}

@Serializable
data class InstitutionalDocument(
    val id: String = "",
    val title: String = "",
    val type: DocumentType,
    val institutionId: String = "",
    val grade: String? = null,
    val subject: String? = null,
    val teacherId: String? = null,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)

@Serializable
data class DocumentBlock(
    val id: String = "",
    val documentId: String = "",
    val order: Int = 0,
    val title: String = "",
    val contentHtml: String = "",
    val updatedAt: Long = 0,
    val modifiedBy: String = ""
)

@Serializable
data class BlockHistory(
    val id: String = "",
    val blockId: String = "",
    val contentHtml: String = "",
    val modifiedAt: Long = 0,
    val modifiedBy: String = ""
)

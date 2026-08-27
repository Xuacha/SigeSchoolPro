package com.sigeschool.services

import com.sigeschool.domain.model.DocumentType

interface DocumentExtractorService {
    suspend fun extractText(fileBytes: ByteArray, fileName: String): String
    suspend fun extractDocumentData(fileBytes: ByteArray, fileName: String): DocumentData
}

data class DocumentData(
    val fileName: String,
    val title: String,
    val content: String,
    val extractedAt: Long
)

enum class DocumentType {
    PDF, DOCX, UNKNOWN
}

package com.sigeschool.services

class DocumentExtractorServiceImpl : DocumentExtractorService {
    override suspend fun extractText(fileBytes: ByteArray, fileName: String): String {
        return extractTextFromPlatform(fileBytes, fileName)
    }

    override suspend fun extractDocumentData(fileBytes: ByteArray, fileName: String): DocumentData {
        val text = extractText(fileBytes, fileName)
        return DocumentData(
            fileName = fileName,
            title = text.lines().firstOrNull() ?: fileName,
            content = text,
            extractedAt = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        )
    }
}

expect suspend fun extractTextFromPlatform(fileBytes: ByteArray, fileName: String): String

package com.sigeschool.domain.service.import

interface FileProcessor {
    suspend fun parseExcel(byteArray: ByteArray): List<Map<String, String>>
    suspend fun parseCsv(content: String): List<Map<String, String>>
}

class CommonFileProcessor : FileProcessor {
    override suspend fun parseExcel(byteArray: ByteArray): List<Map<String, String>> {
        // This will be handled by platform-specific implementations if needed, 
        // but for commonMain we might just return empty or throw if not supported here.
        // Given the requirement, we'll use expect/actual for real Excel parsing.
        return emptyList() 
    }

    override suspend fun parseCsv(content: String): List<Map<String, String>> {
        val lines = content.lines().filter { it.isNotBlank() }
        if (lines.isEmpty()) return emptyList()

        val header = lines[0].split(",").map { it.trim().removeSurrounding("\"") }
        return lines.drop(1).map { line ->
            val values = line.split(",").map { it.trim().removeSurrounding("\"") }
            header.zip(values).toMap()
        }
    }
}


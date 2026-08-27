package com.sigeschool.domain.service.import

class AndroidFileProcessor : FileProcessor {
    override suspend fun parseExcel(byteArray: ByteArray): List<Map<String, String>> {
        // Implementation using Apache POI or similar
        return emptyList()
    }

    override suspend fun parseCsv(content: String): List<Map<String, String>> {
        val lines = content.lines()
        if (lines.isEmpty()) return emptyList()
        
        val header = lines.first().split(",")
        return lines.drop(1).filter { it.isNotBlank() }.map { line ->
            val values = line.split(",")
            header.zip(values).toMap()
        }
    }
}

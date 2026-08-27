package com.sigeschool.domain.service

class JvmFileParserService : FileParserService {
    override fun parseFile(content: ByteArray, fileName: String): List<Map<String, String>> {
        val text = content.decodeToString()
        val lines = text.split("\n").filter { it.isNotBlank() }
        if (lines.isEmpty()) return emptyList()

        val headers = lines[0].split(",").map { it.trim() }
        val records = mutableListOf<Map<String, String>>()

        for (i in 1 until lines.size) {
            val values = lines[i].split(",").map { it.trim() }
            val record = headers.zip(values).toMap()
            records.add(record)
        }
        return records
    }
}

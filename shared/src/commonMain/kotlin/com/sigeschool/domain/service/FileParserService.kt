package com.sigeschool.domain.service

interface FileParserService {
    fun parseFile(content: ByteArray, fileName: String): List<Map<String, String>>
}

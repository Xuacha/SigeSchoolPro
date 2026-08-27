package com.sigeschool.domain.service.import

expect class DocumentParser() {
    fun parseExcel(byteArray: ByteArray): List<AcademicData>
    fun parseWord(byteArray: ByteArray): List<AcademicData>
}

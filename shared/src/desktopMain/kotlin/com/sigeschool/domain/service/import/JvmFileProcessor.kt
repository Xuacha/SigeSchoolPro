package com.sigeschool.domain.service.import

import com.sigeschool.domain.service.import.FileProcessor
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.ByteArrayInputStream

class JvmFileProcessor : FileProcessor {
    override suspend fun parseExcel(byteArray: ByteArray): List<Map<String, String>> {
        val records = mutableListOf<Map<String, String>>()
        try {
            ByteArrayInputStream(byteArray).use { inputStream ->
                val workbook = WorkbookFactory.create(inputStream)
                val sheet = workbook.getSheetAt(0)
                val headerRow = sheet.getRow(0) ?: return emptyList()
                val headers = (0 until headerRow.lastCellNum).map { i ->
                    headerRow.getCell(i)?.toString()?.trim() ?: ""
                }

                for (i in 1..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    val record = headers.indices.associate { j ->
                        headers[j] to (row.getCell(j)?.toString()?.trim() ?: "")
                    }
                    records.add(record)
                }
                workbook.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return records
    }

    override suspend fun parseCsv(content: String): List<Map<String, String>> {
        val lines = content.split("\n").filter { it.isNotBlank() }
        if (lines.isEmpty()) return emptyList()

        val headers = lines[0].split(",").map { it.trim() }
        val records = mutableListOf<Map<String, String>>()

        for (i in 1 until lines.size) {
            val values = lines[i].split(",").map { it.trim() }
            if (values.size == headers.size) {
                val record = headers.zip(values).toMap()
                records.add(record)
            }
        }
        return records
    }
}

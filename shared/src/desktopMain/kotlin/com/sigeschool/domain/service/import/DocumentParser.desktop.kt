package com.sigeschool.domain.service.import

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import java.io.ByteArrayInputStream

actual class DocumentParser actual constructor() {

    actual fun parseExcel(byteArray: ByteArray): List<AcademicData> {
        val data = mutableListOf<AcademicData>()
        val inputStream = ByteArrayInputStream(byteArray)
        val workbook = WorkbookFactory.create(inputStream)
        
        for (i in 0 until workbook.numberOfSheets) {
            val sheet = workbook.getSheetAt(i)
            val sheetName = sheet.sheetName.lowercase()
            
            when {
                sheetName.contains("nivel") -> {
                    for (rowNum in 1..sheet.lastRowNum) {
                        val row = sheet.getRow(rowNum) ?: continue
                        data.add(AcademicData.Nivel(
                            orden = row.getCell(0)?.numericCellValue?.toInt() ?: 0,
                            nombre = row.getCell(1)?.stringCellValue ?: "",
                            codigo = row.getCell(1)?.stringCellValue ?: "",
                            descripcion = row.getCell(2)?.stringCellValue,
                            marcoNormativo = row.getCell(3)?.stringCellValue
                        ))
                    }
                }
                sheetName.contains("grado") -> {
                    for (rowNum in 1..sheet.lastRowNum) {
                        val row = sheet.getRow(rowNum) ?: continue
                        data.add(AcademicData.Grado(
                            orden = row.getCell(0)?.numericCellValue?.toInt() ?: 0,
                            nombre = row.getCell(1)?.stringCellValue ?: "",
                            codigo = row.getCell(2)?.stringCellValue ?: "",
                            descripcion = row.getCell(3)?.stringCellValue,
                            nivel = row.getCell(4)?.stringCellValue ?: ""
                        ))
                    }
                }
                sheetName.contains("area") || sheetName.contains("área") -> {
                    for (rowNum in 1..sheet.lastRowNum) {
                        val row = sheet.getRow(rowNum) ?: continue
                        data.add(AcademicData.Area(
                            orden = row.getCell(0)?.numericCellValue?.toInt() ?: 0,
                            nombre = row.getCell(1)?.stringCellValue ?: "",
                            descripcion = row.getCell(2)?.stringCellValue,
                            programas = row.getCell(3)?.stringCellValue
                        ))
                    }
                }
                sheetName.contains("asignatura") -> {
                    for (rowNum in 1..sheet.lastRowNum) {
                        val row = sheet.getRow(rowNum) ?: continue
                        data.add(AcademicData.Asignatura(
                            codigo = row.getCell(0)?.stringCellValue ?: "",
                            nombre = row.getCell(1)?.stringCellValue ?: "",
                            semestre = row.getCell(2)?.numericCellValue?.toInt() ?: 0,
                            horasTeoricas = row.getCell(3)?.numericCellValue?.toInt() ?: 0,
                            horasPracticas = row.getCell(4)?.numericCellValue?.toInt() ?: 0,
                            horasTotales = row.getCell(5)?.numericCellValue?.toInt() ?: 0,
                            nucleo = row.getCell(6)?.stringCellValue ?: "",
                            gradoCodigo = "",
                            areaNombre = ""
                        ))
                    }
                }
            }
        }
        workbook.close()
        return data
    }

    actual fun parseWord(byteArray: ByteArray): List<AcademicData> {
        val data = mutableListOf<AcademicData>()
        val inputStream = ByteArrayInputStream(byteArray)
        val doc = XWPFDocument(inputStream)
        val tables = doc.tables
        
        tables.getOrNull(0)?.let { table ->
            for (i in 1 until table.rows.size) {
                val cells = table.getRow(i).tableCells
                data.add(AcademicData.Nivel(
                    orden = cells.getOrNull(0)?.text?.toIntOrNull() ?: 0,
                    nombre = cells.getOrNull(1)?.text ?: "",
                    codigo = cells.getOrNull(1)?.text ?: "",
                    descripcion = cells.getOrNull(2)?.text,
                    marcoNormativo = cells.getOrNull(3)?.text
                ))
            }
        }
        
        tables.getOrNull(1)?.let { table ->
            for (i in 1 until table.rows.size) {
                val cells = table.getRow(i).tableCells
                data.add(AcademicData.Grado(
                    orden = cells.getOrNull(0)?.text?.toIntOrNull() ?: 0,
                    nombre = cells.getOrNull(1)?.text ?: "",
                    codigo = cells.getOrNull(2)?.text ?: "",
                    descripcion = cells.getOrNull(3)?.text,
                    nivel = cells.getOrNull(4)?.text ?: ""
                ))
            }
        }
        
        tables.getOrNull(2)?.let { table ->
            for (i in 1 until table.rows.size) {
                val cells = table.getRow(i).tableCells
                data.add(AcademicData.Area(
                    orden = cells.getOrNull(0)?.text?.toIntOrNull() ?: 0,
                    nombre = cells.getOrNull(1)?.text ?: "",
                    descripcion = cells.getOrNull(2)?.text,
                    programas = cells.getOrNull(3)?.text
                ))
            }
        }
        
        var currentGradoCode = ""
        var currentAreaName = ""
        
        val bodyElements = doc.bodyElements
        for (element in bodyElements) {
            if (element is XWPFParagraph) {
                val text = element.text
                if (text.contains("Grado:") && text.contains("Área")) {
                    currentGradoCode = text.substringAfter("(").substringBefore(")")
                    currentAreaName = text.substringAfter("Área ").substringBefore(" ---")
                }
            } else if (element is XWPFTable) {
                val index = tables.indexOf(element)
                if (index >= 3) {
                    for (i in 1 until element.rows.size) {
                        val cells = element.getRow(i).tableCells
                        if (cells.size >= 7) {
                            data.add(AcademicData.Asignatura(
                                codigo = cells[0].text,
                                nombre = cells[1].text,
                                semestre = cells[2].text.toIntOrNull() ?: 0,
                                horasTeoricas = cells[3].text.toIntOrNull() ?: 0,
                                horasPracticas = cells[4].text.toIntOrNull() ?: 0,
                                horasTotales = cells[5].text.toIntOrNull() ?: 0,
                                nucleo = cells[6].text,
                                gradoCodigo = currentGradoCode,
                                areaNombre = currentAreaName
                            ))
                        }
                    }
                }
            }
        }
        
        doc.close()
        return data
    }
}

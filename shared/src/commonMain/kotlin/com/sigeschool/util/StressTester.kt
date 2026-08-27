package com.sigeschool.util

import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
object StressTester {

    suspend fun testBulkReportGeneration(count: Int): String {
        val dummyStudents = (1..count).map { i ->
            Student(
                id = i.toString(),
                nombre = "Prueba",
                apellido = "$i",
                dni = "DNI$i",
                grado = "Grado $i",
                seccion = "A",
                institutionId = "INST1",
                fechaNacimiento = "2010-01-01",
                fechaRegistro = "2024-01-01"
            )
        }

        val dummyGrades = (1..5).map { j ->
            Grade(
                id = j.toString(),
                studentId = "1",
                institutionId = "INST1",
                subjectId = "Materia $j",
                score = 4.0,
                periodId = "1"
            )
        }

        val data = dummyStudents.map { it to dummyGrades }

        val timeTaken = measureTime {
            PdfPlatformGenerator.generateBulkReport(data)
        }

        return "Generados $count boletines en ${timeTaken.toDouble(DurationUnit.SECONDS)} segundos."
    }
}

package com.sigeschool.domain.usecase.sie

import com.sigeschool.domain.model.sie.DisciplineRecord
import com.sigeschool.domain.model.sie.DisciplineType
import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculateConductScoreUseCaseTest {
    private val useCase = CalculateConductScoreUseCase()

    @Test
    fun `score should be 4_0 with no records`() {
        val score = useCase(emptyList<DisciplineRecord>())
        assertEquals(4.0, score)
    }

    @Test
    fun `score should increase with positive records`() {
        val records = listOf(
            DisciplineRecord(
                id = "1", 
                studentId = 1, 
                type = DisciplineType.POSITIVA, 
                description = "Muy bien", 
                date = Clock.System.now(),
                teacherId = "T1"
            )
        )
        val score = useCase(records)
        assertEquals(4.2, score)
    }

    @Test
    fun `score should decrease with negative records`() {
        val records = listOf(
            DisciplineRecord(
                id = "1", 
                studentId = 1, 
                type = DisciplineType.NEGATIVA, 
                description = "Llegó tarde", 
                date = Clock.System.now(),
                teacherId = "T1"
            )
        )
        val score = useCase(records)
        assertEquals(3.7, score)
    }

    @Test
    fun `score should decrease significantly with citacion`() {
        val records = listOf(
            DisciplineRecord(
                id = "1", 
                studentId = 1, 
                type = DisciplineType.CITACION_ACUDIENTE, 
                description = "Falta grave", 
                date = Clock.System.now(),
                teacherId = "T1"
            )
        )
        val score = useCase(records)
        assertEquals(3.5, score)
    }

    @Test
    fun `score should not exceed 5_0`() {
        val records = List(10) { 
            DisciplineRecord(
                id = it.toString(), 
                studentId = 1, 
                type = DisciplineType.POSITIVA, 
                description = "Ok", 
                date = Clock.System.now(),
                teacherId = "T1"
            )
        }
        val score = useCase(records)
        assertEquals(5.0, score)
    }

    @Test
    fun `score should not be lower than 1_0`() {
        val records = List(20) { 
            DisciplineRecord(
                id = it.toString(), 
                studentId = 1, 
                type = DisciplineType.NEGATIVA, 
                description = "Bad", 
                date = Clock.System.now(),
                teacherId = "T1"
            )
        }
        val score = useCase(records)
        assertEquals(1.0, score)
    }
}

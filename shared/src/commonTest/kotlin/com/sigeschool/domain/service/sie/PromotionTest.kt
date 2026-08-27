package com.sigeschool.domain.service.sie

import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.sie.*
import com.sigeschool.domain.repository.sie.PromotionRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PromotionTest {
    private lateinit var sieService: SieService
    private lateinit var mockRepository: FakePromotionRepository

    @BeforeTest
    fun setup() {
        mockRepository = FakePromotionRepository()
        sieService = SieService(mockRepository)
    }

    @Test
    fun testPromotionStatus_AllPassed() = runTest {
        val institutionId = "inst1"
        val status = sieService.checkPromotionStatus(
            studentId = "student1",
            institutionId = institutionId,
            failedSubjectsCount = 0,
            totalClasses = 100,
            absences = 5
        )
        assertTrue(status is PromotionStatus.Promoted)
    }

    @Test
    fun testPromotionStatus_MateriasReprobadasExcedidas() = runTest {
        val institutionId = "inst1"
        val status = sieService.checkPromotionStatus(
            studentId = "student2",
            institutionId = institutionId,
            failedSubjectsCount = 3,
            totalClasses = 100,
            absences = 5
        )
        assertTrue(status is PromotionStatus.NotPromoted)
        val notPromoted = status as PromotionStatus.NotPromoted
        assertTrue(notPromoted.reasons.any { it.contains("asignaturas") })
    }

    @Test
    fun testPromotionStatus_InasistenciaExcedida() = runTest {
        val institutionId = "inst1"
        val status = sieService.checkPromotionStatus(
            studentId = "student3",
            institutionId = institutionId,
            failedSubjectsCount = 0,
            totalClasses = 100,
            absences = 30
        )
        assertTrue(status is PromotionStatus.NotPromoted)
        val notPromoted = status as PromotionStatus.NotPromoted
        assertTrue(notPromoted.reasons.any { it.contains("Inasistencia") })
    }

    @Test
    fun testCalculateWeightedAverage_WithAutoevaluacion() = runTest {
        val studentId = "student4"
        val subjectId = "subject1"
        val periodId = "period1"
        val institutionId = "inst1"

        val grades = listOf(
            Grade(id = "1", studentId = studentId, institutionId = institutionId, subjectId = subjectId, categoryId = "cat1", score = 4.0)
        )
        val categories = listOf(
            GradeCategory(id = "cat1", institutionId = institutionId, name = "Cognitivo", weightPercentage = 100.0)
        )
        
        mockRepository.config = PromotionConfig(institutionId = institutionId, autoevaluacionWeight = 10.0)
        
        mockRepository.autoevaluaciones = listOf(
            Autoevaluacion(id = "a1", studentId = studentId, subjectId = subjectId, periodId = periodId, score = 5.0, registrationDate = 0L)
        )

        val average = sieService.calculateWeightedAverage(
            studentId = studentId,
            subjectId = subjectId,
            periodId = periodId,
            institutionId = institutionId,
            grades = grades,
            categories = categories
        )
        
        // Autoevaluación (10% de 5.0) = 0.5
        // Categoría Cognitivo (90% de 4.0) = 3.6
        // Total = 4.1
        assertEquals(4.1, average, 0.001)
    }

    private class FakePromotionRepository : PromotionRepository {
        var config = PromotionConfig(institutionId = "inst1")
        var autoevaluaciones = listOf<Autoevaluacion>()

        override suspend fun saveAutoevaluacion(autoevaluacion: Autoevaluacion): Result<Unit> = Result.success(Unit)
        override fun getAutoevaluaciones(studentId: String, periodId: String) = flowOf(autoevaluaciones)
        override suspend fun hasSubmittedAutoevaluacion(studentId: String, subjectId: String, periodId: String): Boolean = false
        override fun getPromotionConfig(institutionId: String) = flowOf(config)
        override suspend fun updatePromotionConfig(config: PromotionConfig): Result<Unit> = Result.success(Unit)
    }
}

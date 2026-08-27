package com.sigeschool.services.ai

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import com.sigeschool.domain.model.CvData
import com.sigeschool.domain.model.PersonalInfo
import com.sigeschool.domain.model.ContactInfo
import com.sigeschool.domain.model.Skills
import com.sigeschool.domain.model.JobInfo
import com.sigeschool.domain.model.sie.Rubric
import com.sigeschool.domain.model.sie.RubricCriterion
import com.sigeschool.domain.model.sie.CriterionLevel
import com.sigeschool.domain.model.sie.AchievementIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.nio.LongBuffer
import java.util.UUID

actual class NerService {
    private val modelBytes: ByteArray? = null
    private val env = OrtEnvironment.getEnvironment()
    private var session: OrtSession? = null

    actual constructor()

    init {
        try {
            modelBytes?.let {
                val sessionOptions = OrtSession.SessionOptions()
                session = env.createSession(it, sessionOptions)
            }
        } catch (e: Exception) {
            Timber.e(e, "Error al inicializar sesión de ONNX")
        }
    }

    actual suspend fun extractCvFields(text: String): CvData = withContext(Dispatchers.Default) {
        val fullName = findRegex(text, "(?i)Nombre[:\\s]+([A-ZÁÉÍÓÚÑ\\s]+)")
        val dni = findRegex(text, "(?i)(?:C\\.C\\.|DNI|Documento)[:\\s]+([0-9.]+)").replace(".", "")
        val email = findRegex(text, "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        val phone = findRegex(text, "(?i)(?:Cel|Tel|Teléfono)[:\\s]+([0-9+\\s\\-]+)")
        val address = findRegex(text, "(?i)(?:Dirección|Residencia)[:\\s]+([A-Z0-9#\\s\\-\\.]+)")

        CvData(
            personalInfo = PersonalInfo(
                fullName = fullName,
                dni = dni,
                birthDate = findRegex(text, "(?i)(?:Fecha de nacimiento|Nacido el)[:\\s]+([0-9/\\- ]+)"),
                nationality = if (text.contains("Colombian", ignoreCase = true)) "Colombiana" else "",
                gender = "No especificado"
            ),
            contact = ContactInfo(
                email = email,
                phone = phone,
                address = address.take(100)
            ),
            education = emptyList(),
            experience = emptyList(),
            skills = Skills(emptyList(), emptyList(), emptyList()),
            jobInfo = JobInfo(
                joiningDate = "",
                contractType = "",
                currentPosition = "",
                assignedArea = ""
            )
        )
    }

    actual suspend fun extractRubric(text: String): Rubric? = withContext(Dispatchers.Default) {
        try {
            val title = findRegex(text, "(?i)Rúbrica[:\\s]+([A-ZÁÉÍÓÚÑ\\s]+)")
            if (title.isEmpty()) return@withContext null

            val criteria = mutableListOf<RubricCriterion>()
            val criteriaMatches = Regex("(?i)([A-ZÁÉÍÓÚÑ\\s]+)[:\\s]+(\\d+)[%\\s]").findAll(text)
            
            criteriaMatches.forEach { match ->
                val critName = match.groupValues[1].trim()
                val weight = match.groupValues[2].toDoubleOrNull() ?: 0.0
                
                criteria.add(
                    RubricCriterion(
                        id = UUID.randomUUID().toString(),
                        name = critName,
                        weight = weight,
                        levels = emptyList()
                    )
                )
            }

            Rubric(
                id = UUID.randomUUID().toString(),
                title = title,
                criteria = criteria
            )
        } catch (e: Exception) {
            null
        }
    }

    actual suspend fun extractIndicators(text: String): List<AchievementIndicator> = withContext(Dispatchers.Default) {
        val indicators = mutableListOf<AchievementIndicator>()
        val patterns = listOf(
            "(?i)Identifica\\s+([^.]+)",
            "(?i)Reconoce\\s+([^.]+)",
            "(?i)Demuestra\\s+([^.]+)",
            "(?i)Analiza\\s+([^.]+)"
        )

        patterns.forEach { pattern ->
            Regex(pattern).findAll(text).forEach { match ->
                indicators.add(
                    AchievementIndicator(
                        id = UUID.randomUUID().toString(),
                        competencyId = "",
                        rangeId = "",
                        description = match.value.trim()
                    )
                )
            }
        }
        indicators
    }

    private fun findRegex(text: String, pattern: String): String {
        return Regex(pattern).find(text)?.groupValues?.get(1)?.trim() ?: ""
    }
}

package com.sigeschool.services.ai

import com.sigeschool.domain.model.CvData
import com.sigeschool.domain.model.PersonalInfo
import com.sigeschool.domain.model.ContactInfo
import com.sigeschool.domain.model.Skills
import com.sigeschool.domain.model.JobInfo

actual class NerService {
    actual constructor()

    actual suspend fun extractCvFields(text: String): CvData {
        // WasmJs no soporta ONNX Runtime directamente de la misma forma que JVM/Android
        // Se puede implementar una versión simplificada o llamar a un Web Worker con onnxruntime-web
        
        val fullName = findRegex(text, "(?i)Nombre[:\\s]+([A-ZÁÉÍÓÚÑ\\s]+)")
        val email = findRegex(text, "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")

        return CvData(
            personalInfo = PersonalInfo(
                fullName = fullName,
                dni = "",
                birthDate = "",
                nationality = "",
                gender = "No especificado"
            ),
            contact = ContactInfo(
                email = email,
                phone = "",
                address = ""
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

    private fun findRegex(text: String, pattern: String): String {
        return Regex(pattern).find(text)?.groupValues?.get(1)?.trim() ?: ""
    }
}

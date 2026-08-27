package com.sigeschool.domain.service.import

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val reason: String) : ValidationResult()
    data class Warning(val message: String) : ValidationResult()
}

class StudentDataValidator {

    fun validateRow(data: Map<String, String>): ValidationResult {
        val docId = (data["student_document_number"] ?: data["document_number"]) ?: ""
        val firstName = data["first_name"] ?: ""
        val lastName = data["last_name"] ?: ""

        if (docId.isEmpty()) {
            return ValidationResult.Invalid("Número de documento faltante o no mapeado")
        }
        
        if (firstName.isEmpty()) {
            return ValidationResult.Invalid("Nombres del aspirante faltantes")
        }

        if (lastName.isEmpty()) {
            return ValidationResult.Invalid("Apellidos del aspirante faltantes")
        }

        val warnings = mutableListOf<String>()
        if (docId.length < 5) {
            warnings.add("Número de documento inusualmente corto")
        }

        return if (warnings.isNotEmpty()) {
            ValidationResult.Warning(warnings.joinToString(". "))
        } else {
            ValidationResult.Valid
        }
    }
}

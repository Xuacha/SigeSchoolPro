package com.sigeschool.domain.service.import

object HeaderNormalizer {

    private val mapping = mapOf(
        "nombres completos del aspirante" to "first_name",
        "apellidos completos del aspirante" to "last_name",
        "tipo de documento de identidad del aspirante" to "document_type",
        "número de documento de identidad del aspirante" to "student_document_number",
        "numero de documento de identidad del aspirante" to "student_document_number",
        "fecha de nacimiento del aspirante" to "birth_date",
        "edad del aspirante" to "age",
        "sexo / género" to "gender",
        "correo electrónico del aspirante" to "email",
        "número de teléfono / celular del aspirante" to "phone",
        "dirección de residencia completa del aspirante" to "address",
        "barrio de residencia" to "neighborhood",
        "estrato socioeconómico" to "stratum",
        "¿pertenece a alguna comunidad étnica?" to "ethnic_community",
        "¿tiene alguna discapacidad?" to "disability",
        "ajustes razonables" to "disability_details",
        "nivel educativo máximo alcanzado" to "education_level",
        "institución educativa de procedencia" to "previous_school",
        "programa al que desea matricularse" to "program",
        "bachillerato por ciclos" to "cycle_selection",
        "seleccione el gravo para el que va" to "cycle_selection",
        "¿cómo se enteró de la escuela?" to "how_hear",
        "nombres completos del acudiente" to "guardian_first_name",
        "apellidos completos del acudiente" to "guardian_last_name",
        "tipo de documento del acudiente" to "guardian_doc_type",
        "número de documento del acudiente" to "parent_document_number",
        "parentesco con el aspirante" to "guardian_relationship",
        "teléfono de contacto del acudiente" to "guardian_phone",
        "correo electrónico del acudiente" to "guardian_email",
        "institución de origen" to "institucion_origen",
        "institucion origen" to "institucion_origen",
        "procedencia" to "institucion_origen",
        "información suministrada es verídica" to "data_consent",
        "tratamiento de mis datos personales" to "guardian_consent",
        "firma o aceptación digital" to "digital_signature"
    )

    fun normalize(raw: String): String {
        val cleaned = raw.lowercase()
            .replace(Regex("[^a-záéíóúñ0-9 ]"), "")
            .replace(Regex("\\s+"), " ")
            .trim()

        for ((key, value) in mapping) {
            if (cleaned.contains(key) || key.contains(cleaned)) {
                return value
            }
        }
        return cleaned.replace(" ", "_")
    }
}

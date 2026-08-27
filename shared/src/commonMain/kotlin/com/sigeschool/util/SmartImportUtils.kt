package com.sigeschool.util

/**
 * Utilidad para mapear dinámicamente columnas de archivos externos a campos del sistema.
 */
object SmartImportUtils {
    
    private val studentAliases = mapOf(
        "nombre" to listOf("nombre", "nombres", "first name", "name", "estudiante"),
        "apellido" to listOf("apellido", "apellidos", "last name", "surname"),
        "dni" to listOf("dni", "cedula", "documento", "id", "identificacion", "document", "identificación"),
        "grado" to listOf("grado", "curso", "grade", "level", "nivel"),
        "seccion" to listOf("seccion", "grupo", "aula", "section", "sección")
    )

    private val employeeAliases = mapOf(
        "nombre" to listOf("nombre", "nombres", "first name", "name", "empleado", "trabajador"),
        "apellido" to listOf("apellido", "apellidos", "last name", "surname"),
        "dni" to listOf("dni", "cedula", "documento", "id", "identificacion", "document"),
        "rol" to listOf("rol", "cargo", "puesto", "role", "position", "oficio"),
        "formacion" to listOf("formacion", "titulo", "qualification", "estudios", "nivel"),
        "especialidad" to listOf("especialidad", "specialization", "area", "materia"),
        "telefono" to listOf("telefono", "celular", "phone", "mobile", "whatsapp"),
        "email" to listOf("email", "correo", "e-mail", "mail")
    )

    private val financeAliases = mapOf(
        "codigo" to listOf("codigo", "cuenta", "code", "account"),
        "descripcion" to listOf("descripcion", "detalle", "description", "concepto"),
        "debito" to listOf("debito", "debe", "debit", "cargo"),
        "credito" to listOf("credito", "haber", "credit", "abono")
    )

    /**
     * Mapea los encabezados de una tabla a los campos del sistema basándose en alias.
     * @param headers Lista de encabezados encontrados en el archivo (Excel/CSV).
     * @param type Tipo de importación ("student" o "finance").
     * @return Mapa de Campo -> Índice de columna.
     */
    fun mapHeaders(headers: List<String>, type: String): Map<String, Int> {
        val aliases = if (type == "student") studentAliases else financeAliases
        val mapping = mutableMapOf<String, Int>()

        headers.forEachIndexed { index, header ->
            val cleanHeader = header.lowercase().trim()
            aliases.forEach { (field, list) ->
                // Si el encabezado contiene alguno de los alias o es muy similar
                if (list.any { cleanHeader.contains(it) || it.contains(cleanHeader) }) {
                    // Evitar sobreescribir si ya encontramos una coincidencia más exacta
                    if (!mapping.containsKey(field)) {
                        mapping[field] = index
                    }
                }
            }
        }
        return mapping
    }

    /**
     * Filtra una fila de datos extrayendo solo lo necesario según el mapeo.
     */
    fun extractData(row: List<String>, mapping: Map<String, Int>): Map<String, String> {
        return mapping.mapValues { (_, index) ->
            if (index < row.size) row[index].trim() else ""
        }
    }
}

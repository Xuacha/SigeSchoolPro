package com.sigeschool.domain.service

class TemplateEngine {
    fun generateAttendanceMessage(studentName: String, parentName: String, date: String, status: String): String {
        return """
            📚 *Control de Asistencia - SigeSchool Pro*
            
            Estimado/a $parentName:
            
            Su hijo/a *$studentName* ha registrado $status el día $date.
            
            Por favor, comuníquese con la coordinación académica para más información.
            
            ---
            📱 SigeSchool Pro - Gestión Escolar Inteligente
        """.trimIndent()
    }

    fun generateAcademicMessage(studentName: String, parentName: String, subject: String, grade: Double, observation: String): String {
        return """
            📊 *Control Académico - SigeSchool Pro*
            
            Estimado/a $parentName:
            
            Su hijo/a *$studentName* ha obtenido una calificación de *$grade/5.0* en la asignatura *$subject*.
            
            Observaciones del docente:
            $observation
            
            Por favor, revise el portal de acudientes para más detalles.
            
            ---
            📱 SigeSchool Pro - Gestión Escolar Inteligente
        """.trimIndent()
    }

    fun generateDisciplinaryMessage(studentName: String, parentName: String, type: String, description: String, impact: Double): String {
        return """
            ⚠️ *Control Disciplinario - SigeSchool Pro*
            
            Estimado/a $parentName:
            
            Se ha registrado una anotación *$type* para su hijo/a *$studentName*.
            
            Descripción: $description
            Impacto en nota de conducta: $impact/5.0
            
            Por favor, comuníquese con la coordinación para más información.
            
            ---
            📱 SigeSchool Pro - Gestión Escolar Inteligente
        """.trimIndent()
    }

    fun generateCircular(title: String, content: String): String {
        return """
            📢 *CIRCULAR INSTITUCIONAL*
            
            $title
            
            $content
            
            ---
            📱 SigeSchool Pro - Gestión Escolar Inteligente
            *No responder a este mensaje.*
        """.trimIndent()
    }
}

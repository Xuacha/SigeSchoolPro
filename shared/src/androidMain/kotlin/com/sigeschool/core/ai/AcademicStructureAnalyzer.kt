package com.sigeschool.core.ai

actual class AcademicStructureAnalyzer {
    actual suspend fun analyzeDocument(text: String): AcademicStructure {
        // Logic migrated from LocalNLPAnalyzer.kt
        val lines = text.lines().map { it.trim() }.filter { it.isNotEmpty() }
        val areas = mutableListOf<AnalyzedArea>()
        var currentArea: AnalyzedArea? = null
        val currentSubjects = mutableListOf<AnalyzedSubject>()

        val areaKeywords = listOf("AREA", "NUCLEO", "CAMPO", "DIMENSION")
        val commonSubjects = listOf(
            "MATEMATICAS", "LENGUA CASTELLANA", "INGLES", "CIENCIAS", "FISICA", 
            "QUIMICA", "BIOLOGIA", "HISTORIA", "GEOGRAFIA", "FILOSOFIA", 
            "ETICA", "RELIGION", "ARTISTICA", "ED. FISICA", "INFORMATICA"
        )

        for (line in lines) {
            val upperLine = line.uppercase()
            
            if (areaKeywords.any { upperLine.contains(it) } || 
                (upperLine.length < 40 && commonSubjects.none { upperLine.contains(it) } && upperLine.contains("CIENCIAS"))) {
                
                if (currentArea != null) {
                    areas.add(currentArea.copy(subjects = currentSubjects.toList()))
                    currentSubjects.clear()
                }
                
                currentArea = AnalyzedArea(name = line.replace(Regex("(?i)AREA DE|NUCLEO DE"), "").trim())
                continue
            }

            val foundSubject = commonSubjects.find { upperLine.contains(it) }
            if (foundSubject != null || (currentArea != null && line.length > 3 && line.length < 30)) {
                currentSubjects.add(AnalyzedSubject(name = line))
            }
        }

        if (currentArea != null) {
            areas.add(currentArea.copy(subjects = currentSubjects.toList()))
        }

        return AcademicStructure(
            areas = areas,
            planEstudioName = "Plan extraído: " + (if (lines.isNotEmpty()) lines[0].take(20) else "Nuevo")
        )
    }
}

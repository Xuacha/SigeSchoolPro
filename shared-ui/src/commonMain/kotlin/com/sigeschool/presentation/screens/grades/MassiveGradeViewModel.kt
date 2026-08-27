package com.sigeschool.presentation.screens.grades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.repository.GradeRepository
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MassiveGradeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val result: ImportSummary? = null
)

data class ImportSummary(
    val inserted: Int = 0,
    val updated: Int = 0,
    val errors: List<String> = emptyList()
)

class MassiveGradeViewModel(
    private val gradeRepository: GradeRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(MassiveGradeState())
    val state = _state.asStateFlow()

    private val _userRole = MutableStateFlow("")
    val userRole = _userRole.asStateFlow()

    init {
        viewModelScope.launch {
            sessionManager.sessionState.collect { session ->
                if (session is SessionState.LoggedIn) {
                    _userRole.value = session.role
                }
            }
        }
    }

    fun processImportedData(rows: List<List<String>>) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, result = null) }
            
            val institutionId = sessionManager.getCurrentInstitutionId() ?: run {
                _state.update { it.copy(isLoading = false, error = "No hay sesión activa") }
                return@launch
            }

            var inserted = 0
            var updated = 0
            val errors = mutableListOf<String>()

            // Saltamos la cabecera si existe
            val dataRows = if (rows.firstOrNull()?.any { it.contains("document", ignoreCase = true) } == true) {
                rows.drop(1)
            } else {
                rows
            }

            dataRows.forEachIndexed { index, row ->
                try {
                    // Mapeo básico basado en el formato esperado: 
                    // [Documento, Asignatura, Periodo, Corte, Nota, Observacion]
                    if (row.size < 5) {
                        errors.add("Fila ${index + 1}: Columnas insuficientes")
                        return@forEachIndexed
                    }

                    val studentId = row[0]
                    val subjectId = row[1]
                    val periodId = row[2]
                    // val corte = row[3] // Opcional según implementación
                    val score = row[4].replace(",", ".").toDoubleOrNull() ?: run {
                        errors.add("Fila ${index + 1}: Nota inválida")
                        return@forEachIndexed
                    }
                    val observations = row.getOrNull(5) ?: ""

                    val grade = Grade(
                        id = "", // Se genera en el repositorio o backend
                        studentId = studentId,
                        subjectId = subjectId,
                        score = score,
                        periodId = periodId,
                        observations = observations,
                        institutionId = institutionId
                    )

                    val result = gradeRepository.saveGrade(grade, institutionId)
                    if (result is com.sigeschool.domain.util.Resource.Success) {
                        inserted++
                    } else {
                        errors.add("Fila ${index + 1}: Error al guardar")
                    }

                } catch (e: Exception) {
                    errors.add("Fila ${index + 1}: ${e.message}")
                }
            }

            _state.update { 
                it.copy(
                    isLoading = false,
                    result = ImportSummary(inserted, updated, errors)
                ) 
            }
        }
    }
}

package com.sigeschool.presentation.screens.promotor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.repository.SessionRepository
import com.sigeschool.domain.repository.StudentRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface PromotorUiState {
    data object Loading : PromotorUiState
    data class Success(val recentStudents: List<Student>) : PromotorUiState
    data class Error(val message: String) : PromotorUiState
}

class PromotorDashboardViewModel(
    private val repository: StudentRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PromotorUiState>(PromotorUiState.Loading)
    val uiState: StateFlow<PromotorUiState> = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        viewModelScope.launch {
            _uiState.value = PromotorUiState.Loading
            repository.getAllStudents().collect { students ->
                _uiState.value = PromotorUiState.Success(recentStudents = students.take(15))
            }
        }
    }
}

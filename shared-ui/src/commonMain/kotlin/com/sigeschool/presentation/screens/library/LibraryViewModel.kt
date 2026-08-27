package com.sigeschool.presentation.screens.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Libro
import com.sigeschool.domain.model.Prestamo
import com.sigeschool.domain.repository.LibraryRepository
import com.sigeschool.domain.repository.SessionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface LibraryUiState {
    data object Loading : LibraryUiState
    data class Success(
        val libros: List<Libro>,
        val prestamos: List<Prestamo>
    ) : LibraryUiState
    data class Error(val message: String) : LibraryUiState
}

class LibraryViewModel(
    private val repository: LibraryRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LibraryUiState>(LibraryUiState.Loading)
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = LibraryUiState.Loading
            val institutionId = sessionRepository.getInstitutionId() ?: ""
            
            combine(
                repository.getLibros(institutionId),
                repository.getPrestamos(institutionId)
            ) { libros, prestamos ->
                LibraryUiState.Success(libros = libros, prestamos = prestamos)
            }.catch { e ->
                _uiState.value = LibraryUiState.Error(e.message ?: "Error al cargar biblioteca")
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun saveLibro(titulo: String, autor: String, isbn: String?, cantidad: Int) {
        viewModelScope.launch {
            val instId = sessionRepository.getInstitutionId() ?: ""
            val newLibro = Libro(
                id = "LIB-${System.currentTimeMillis()}",
                institutionId = instId,
                isbn = isbn,
                titulo = titulo,
                autor = autor,
                ejemplaresTotales = cantidad,
                ejemplaresDisponibles = cantidad
            )
            repository.saveLibro(newLibro)
            loadData()
        }
    }
}

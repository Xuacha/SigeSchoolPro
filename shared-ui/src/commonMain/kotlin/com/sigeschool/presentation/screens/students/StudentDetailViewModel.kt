package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.repository.StudentRepository
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.Resource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Clock
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentDetailViewModel(
    private val repository: StudentRepository,
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> = _student.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadStudent(studentId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _student.value = repository.getStudentById(studentId)
            _isLoading.value = false
        }
    }

    fun updatePhoto(photoBytes: ByteArray) {
        val currentStudent = _student.value ?: return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val photoUrl = uploadPhoto(currentStudent.id, photoBytes)
                val updatedStudent = currentStudent.copy(photoUrl = photoUrl)
                repository.saveStudent(updatedStudent)
                _student.value = updatedStudent
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = "Error al subir foto: ${e.message}"
            }
        }
    }

    private suspend fun uploadPhoto(studentId: String, file: ByteArray): String {
        val fileName = "students/${studentId}_${Clock.System.now().toEpochMilliseconds()}.jpg"
        val bucket = supabaseClient.storage.from("photos")
        bucket.upload(fileName, file) {
            upsert = true
        }
        return bucket.publicUrl(fileName)
    }
}

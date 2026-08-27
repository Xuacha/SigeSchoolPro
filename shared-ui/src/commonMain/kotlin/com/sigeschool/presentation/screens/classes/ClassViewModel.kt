package com.sigeschool.presentation.screens.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.data.repository.ClassRepository
import com.sigeschool.data.repository.EmployeeRepository
import com.sigeschool.domain.model.Class
import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.EmployeeStatus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class ClassViewModel(
    private val classRepository: ClassRepository,
    private val authRepository: AuthRepository,
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _classes = MutableStateFlow<List<Class>>(emptyList())
    val classes: StateFlow<List<Class>> = _classes.asStateFlow()

    private val _teachers = MutableStateFlow<List<Employee>>(emptyList())
    val teachers: StateFlow<List<Employee>> = _teachers.asStateFlow()

    init {
        loadClasses()
        loadTeachers()
    }

    private fun loadTeachers() {
        viewModelScope.launch {
            val institutionId = authRepository.getCurrentInstitutionId() ?: ""
            if (institutionId.isNotEmpty()) {
                employeeRepository.getActiveEmployees(institutionId).collect {
                    _teachers.value = it
                }
            }
        }
    }

    private fun loadClasses() {
        viewModelScope.launch {
            val institutionId = authRepository.getCurrentInstitutionId() ?: ""
            if (institutionId.isNotEmpty()) {
                classRepository.getAllClasses(institutionId).collect {
                    _classes.value = it
                }
            }
        }
    }

    fun addClass(name: String, level: String, teacherId: String?) {
        viewModelScope.launch {
            val institutionId = authRepository.getCurrentInstitutionId() ?: ""
            val newClass = Class(
                id = "",
                name = name,
                level = level,
                institutionId = institutionId,
                teacherId = teacherId,
                createdAt = Clock.System.now().toString()
            )
            classRepository.addClass(newClass)
        }
    }
}

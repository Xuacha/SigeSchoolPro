package com.sigeschool.data.repository

import com.sigeschool.domain.model.Certificate
import com.sigeschool.domain.repository.CertificateRepository
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CertificateRepositoryImpl(
    private val studentRepository: com.sigeschool.domain.repository.StudentRepository,
    private val pdfGenerator: com.sigeschool.services.pdf.PdfGenerator
) : com.sigeschool.domain.repository.CertificateRepository {
    override fun getCertificatesByStudent(studentId: String): Flow<Resource<List<Certificate>>> = flow {
        emit(Resource.Loading())
        // En una app real, esto consultaría una tabla de certificados emitidos
        emit(Resource.Success(emptyList()))
    }

    override fun generateStudyCertificate(studentId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val student = studentRepository.getStudentById(studentId)
                ?: throw Exception("Estudiante no encontrado")
            
            // Generación real con motor multiplataforma
            val pdfBytes = pdfGenerator.generateStudyCertificate(
                studentName = student.nombreCompleto,
                studentId = student.id,
                documentNumber = student.dni,
                program = student.grado,
                institutionId = student.institutionId
            )
            
            // Simulación de guardado (en una app real se enviaría a Supabase Storage o local file)
            val path = "certificates/study_$studentId.pdf"
            emit(Resource.Success(path))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error generating certificate"))
        }
    }

    override fun generateGradeCertificate(studentId: String, year: Int): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val path = "certificates/grades_${studentId}_$year.pdf"
            emit(Resource.Success(path))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error generating certificate"))
        }
    }
}

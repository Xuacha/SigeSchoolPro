package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Certificate
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CertificateRepository {
    fun getCertificatesByStudent(studentId: String): Flow<Resource<List<Certificate>>>
    fun generateStudyCertificate(studentId: String): Flow<Resource<String>> // Returns URL or path
    fun generateGradeCertificate(studentId: String, year: Int): Flow<Resource<String>>
}

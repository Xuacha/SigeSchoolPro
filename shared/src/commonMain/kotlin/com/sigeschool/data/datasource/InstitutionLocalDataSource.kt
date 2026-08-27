package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Institution
import kotlinx.coroutines.flow.Flow

interface InstitutionLocalDataSource {
    suspend fun getInstitutionById(id: String): Institution?
    suspend fun count(): Int
    fun getAllActiveInstitutions(): Flow<List<Institution>>
}

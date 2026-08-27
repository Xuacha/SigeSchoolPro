package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.InstitutionLocalDataSource
import com.sigeschool.data.local.dao.InstitutionDao
import com.sigeschool.data.local.mapper.toDomain as toInstitutionDomain
import com.sigeschool.domain.model.Institution
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InstitutionLocalDataSourceImpl(private val institutionDao: InstitutionDao) : InstitutionLocalDataSource {
    override suspend fun getInstitutionById(id: String): Institution? {
        return institutionDao.getInstitutionById(id)?.toInstitutionDomain()
    }

    override suspend fun count(): Int {
        return institutionDao.count()
    }

    override fun getAllActiveInstitutions(): Flow<List<Institution>> {
        return institutionDao.getAllActiveInstitutions().map { list -> list.map { it.toInstitutionDomain() } }
    }
}

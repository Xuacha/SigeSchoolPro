package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.ClassLocalDataSource
import com.sigeschool.data.local.database.AppDatabase
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.Class
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClassLocalDataSourceImpl(private val database: AppDatabase) : ClassLocalDataSource {
    private val dao = database.classDao()

    override fun getAllClasses(institutionId: String): Flow<List<Class>> {
        return dao.getAllByInstitution(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addClass(clazz: Class) {
        dao.insert(clazz.toEntity())
    }

    override suspend fun updateClass(clazz: Class) {
        dao.update(clazz.toEntity())
    }

    override suspend fun deleteClass(clazz: Class) {
        dao.delete(clazz.toEntity())
    }
}

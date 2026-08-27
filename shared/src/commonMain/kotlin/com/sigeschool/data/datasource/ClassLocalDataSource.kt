package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Class
import kotlinx.coroutines.flow.Flow

interface ClassLocalDataSource {
    fun getAllClasses(institutionId: String): Flow<List<Class>>
    suspend fun addClass(clazz: Class)
    suspend fun updateClass(clazz: Class)
    suspend fun deleteClass(clazz: Class)
}

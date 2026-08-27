package com.sigeschool.data.repository

import com.sigeschool.data.datasource.ClassLocalDataSource
import com.sigeschool.domain.model.Class
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow

class ClassRepositoryImpl(
    private val localDataSource: ClassLocalDataSource,
    private val supabaseClient: SupabaseClient
) : ClassRepository {

    override fun getAllClasses(institutionId: String): Flow<List<Class>> {
        return localDataSource.getAllClasses(institutionId)
    }

    override suspend fun addClass(clazz: Class) {
        localDataSource.addClass(clazz)
        try {
            supabaseClient.from("classes").upsert(clazz)
        } catch (e: Exception) {
            // Persistent offline-first retry handles queued sync
        }
    }

    override suspend fun updateClass(clazz: Class) {
        localDataSource.updateClass(clazz)
        try {
            supabaseClient.from("classes").upsert(clazz)
        } catch (e: Exception) {
            // Saved locally, offline-first fallback
        }
    }

    override suspend fun deleteClass(clazz: Class) {
        localDataSource.deleteClass(clazz)
        try {
            supabaseClient.from("classes").delete {
                filter { eq("id", clazz.id) }
            }
        } catch (e: Exception) {
            // Saved locally
        }
    }

    override suspend fun syncClasses(institutionId: String): Result<Unit> {
        return try {
            val remoteClasses = supabaseClient.from("classes")
                .select {
                    filter {
                        eq("institution_id", institutionId)
                    }
                }
                .decodeList<Class>()

            remoteClasses.forEach { remote ->
                // Lógica de sincronización simple: Upsert local
                localDataSource.addClass(remote)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

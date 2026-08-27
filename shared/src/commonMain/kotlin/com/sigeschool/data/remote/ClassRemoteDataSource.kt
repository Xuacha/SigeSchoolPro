package com.sigeschool.data.remote

import com.sigeschool.domain.model.Class
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.Serializable

@Serializable
data class ClassRemoteDto(
    val id: String,
    val name: String,
    val level: String,
    val institution_id: String,
    val teacher_id: String?,
    val created_at: String,
    val updated_at: String
)

class ClassRemoteDataSource(private val supabaseClient: SupabaseClient) {

    suspend fun getClasses(institutionId: String): List<Class> {
        return try {
            supabaseClient.from("classes")
                .select(columns = Columns.ALL) {
                    filter {
                        eq("institution_id", institutionId)
                    }
                }
                .decodeList<ClassRemoteDto>()
                .map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun upsertClass(clazz: Class): Boolean {
        return try {
            val dto = clazz.toRemoteDto()
            supabaseClient.from("classes").upsert(dto)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteClass(classId: String): Boolean {
        return try {
            supabaseClient.from("classes").delete {
                filter {
                    eq("id", classId)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getClassById(classId: String): Class? {
        return try {
            val result = supabaseClient.from("classes")
                .select {
                    filter {
                        eq("id", classId)
                    }
                }
                .decodeSingleOrNull<ClassRemoteDto>()
            result?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    private fun ClassRemoteDto.toDomain() = Class(
        id = id,
        name = name,
        level = level,
        institutionId = institution_id,
        teacherId = teacher_id,
        createdAt = created_at
    )

    private fun Class.toRemoteDto() = ClassRemoteDto(
        id = id,
        name = name,
        level = level,
        institution_id = institutionId,
        teacher_id = teacherId,
        created_at = createdAt,
        updated_at = createdAt
    )
}

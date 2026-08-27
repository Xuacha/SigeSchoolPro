package com.sigeschool.data.remote

import com.sigeschool.domain.model.Student
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

/**
 * SEC-05, SEC-07: Implementación de filtrado explícito por institutionId.
 * No se confía únicamente en RLS; se agrega validación en la capa de datos.
 */
class StudentRemoteDataSource {

    private val client = SupabaseClientProvider.client

    suspend fun uploadStudent(student: Student): Boolean {
        if (student.institutionId.isBlank()) return false
        
        return try {
            client.from("students").upsert(student)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getAllStudents(institutionId: String): List<Student> {
        return try {
            client.from("students")
                .select(Columns.raw("*")) {
                    filter {
                        eq("institution_id", institutionId)
                    }
                }
                .decodeList<Student>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun updateStudent(student: Student, institutionId: String): Boolean {
        return try {
            client.from("students").update(student) {
                filter {
                    eq("id", student.id)
                    eq("institution_id", institutionId)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    suspend fun deleteStudent(id: String, institutionId: String): Boolean {
        return try {
            client.from("students").delete {
                filter {
                    eq("id", id)
                    eq("institution_id", institutionId)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

package com.sigeschool.data.remote

import com.sigeschool.domain.model.Student
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

// FIX: antes se instanciaba con SupabaseClientProvider.client directamente
// (un singleton propio) en lugar de recibir el SupabaseClient por
// inyección de dependencias como el resto de los *RemoteDataSource.
// Eso impedía sustituirlo en tests y era inconsistente con el resto
// del código. Ahora se inyecta igual que EmployeeRemoteDataSource, etc.
class StudentRemoteDataSource(private val supabaseClient: SupabaseClient) {

    suspend fun uploadStudent(student: Student): Boolean {
        return try {
            supabaseClient.postgrest["students"].insert(student)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // FIX CRÍTICO: antes traía TODOS los estudiantes de TODAS las
    // instituciones (sin filtro), y dependía por completo de que las
    // políticas RLS del servidor hicieran el recorte. Con RLS abierta
    // (como estaba el esquema original) esto exponía los estudiantes
    // de cualquier colegio a cualquier usuario de la app. Ahora se
    // filtra explícitamente por institución además de contar con RLS
    // como segunda capa de defensa.
    suspend fun getAllStudents(institutionId: String): List<Student> {
        return try {
            supabaseClient.postgrest["students"]
                .select(Columns.raw("*")) {
                    filter {
                        eq("institution_id", institutionId)
                    }
                }.decodeList<Student>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun updateStudent(student: Student): Boolean {
        return try {
            supabaseClient.postgrest["students"].update(student) {
                filter {
                    eq("id", student.id)
                    eq("institution_id", student.institutionId)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteStudent(id: Long, institutionId: String): Boolean {
        return try {
            supabaseClient.postgrest["students"].delete {
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

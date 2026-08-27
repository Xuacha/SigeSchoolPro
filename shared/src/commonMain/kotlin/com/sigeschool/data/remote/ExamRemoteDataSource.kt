package com.sigeschool.data.remote

import com.sigeschool.domain.model.Exam
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class ExamRemoteDataSource(private val supabaseClient: SupabaseClient) {
    suspend fun getExams(institutionId: String): List<Exam> {
        return supabaseClient.postgrest["exams"]
            .select(columns = Columns.ALL) {
                filter {
                    eq("institution_id", institutionId)
                }
            }.decodeList<Exam>()
    }

    suspend fun getExamsByClass(classId: String): List<Exam> {
        return supabaseClient.postgrest["exams"]
            .select(columns = Columns.ALL) {
                filter {
                    eq("class_id", classId)
                }
            }.decodeList<Exam>()
    }

    suspend fun upsertExam(exam: Exam): Boolean {
        return try {
            supabaseClient.postgrest["exams"].upsert(exam)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteExam(id: String): Boolean {
        return try {
            supabaseClient.postgrest["exams"].delete {
                filter {
                    eq("id", id)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun saveResult(examId: String, score: Double, studentId: String): Boolean {
        return try {
            val result = mapOf(
                "exam_id" to examId,
                "student_id" to studentId,
                "score" to score,
                "date" to kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
            )
            supabaseClient.postgrest["exam_results"].insert(result)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

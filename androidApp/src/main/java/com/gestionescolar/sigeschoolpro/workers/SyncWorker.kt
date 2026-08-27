package com.gestionescolar.sigeschoolpro.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.ListenableWorker.Result
import com.sigeschool.data.repository.AttendanceRepository
import com.sigeschool.data.repository.GradeRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.util.SessionManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val studentRepository: StudentRepository by inject()
    private val attendanceRepository: AttendanceRepository by inject()
    private val gradeRepository: GradeRepository by inject()
    private val sessionManager: SessionManager by inject()

    override suspend fun doWork(): Result {
        return try {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return Result.failure()
            
            // Verificamos si hay una sesión activa antes de intentar sincronizar
            // El RLS de Supabase requiere el token del usuario para permitir la escritura
            studentRepository.syncWithSupabase(institutionId)
            attendanceRepository.syncWithCloud(institutionId)
            gradeRepository.syncWithCloud(institutionId)
            Result.success()
        } catch (e: Exception) {
            // Si el error es por falta de permisos (RLS), logueamos el aviso
            if (e.message?.contains("403") == true) {
                println("Error de RLS: El usuario no tiene permisos para sincronizar estos datos.")
            }
            e.printStackTrace()
            Result.retry()
        }
    }
}

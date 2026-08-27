package com.sigeschool.android.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.sigeschool.domain.repository.SessionRepository
import com.sigeschool.domain.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GoogleFormsSyncWorker(
    context: Context,
    params: WorkerParameters,
    private val sessionRepository: SessionRepository,
    private val studentRepository: StudentRepository
) : CoroutineWorker(context, params) {

    companion object {
        const val PROGRESS_KEY = "progress"
        const val MESSAGE_KEY = "message"
        private const val TAG = "GoogleFormsSync"
    }

    override suspend fun doWork(): Result {
        val institutionId = inputData.getString("institutionId") 
            ?: sessionRepository.getInstitutionId() 
            ?: return Result.failure()

        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Sincronizando formulario externo para la institución $institutionId")
                setProgress(workDataOf(PROGRESS_KEY to 50, MESSAGE_KEY to "Sincronizando datos remotos..."))
                
                // Ejecutar sincronización de estudiantes
                val result = studentRepository.syncStudents()
                
                if (result.isSuccess) {
                    setProgress(workDataOf(PROGRESS_KEY to 100, MESSAGE_KEY to "Sincronización exitosa"))
                    Result.success(workDataOf("status" to "SUCCESS"))
                } else {
                    Result.failure(workDataOf("error" to (result.exceptionOrNull()?.message ?: "Error de sync")))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error en GoogleFormsSyncWorker", e)
                Result.failure(workDataOf("error" to e.message))
            }
        }
    }
}

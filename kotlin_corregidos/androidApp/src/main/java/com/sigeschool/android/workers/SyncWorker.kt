package com.sigeschool.android.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sigeschool.data.repository.AttendanceRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.data.repository.SalaryRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val attendanceRepository: AttendanceRepository by inject()
    private val studentRepository: StudentRepository by inject()
    private val salaryRepository: SalaryRepository by inject()

    override suspend fun doWork(): Result {
        return try {
            attendanceRepository.syncWithCloud()
            studentRepository.syncWithSupabase()
            salaryRepository.syncWithCloud()
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }
}

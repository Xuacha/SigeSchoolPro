package com.sigeschool.android

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.sigeschool.android.workers.SyncWorker
import com.sigeschool.di.initKoin
import com.sigeschool.di.uiModule
import com.sigeschool.local.di.localModule
import com.sigeschool.local.di.databaseModule
import com.sigeschool.local.di.repositoryImplementationModule
import com.gestionescolar.sigeschoolpro.BuildConfig
import com.sigeschool.presentation.util.DownloadHelper
import org.koin.android.ext.koin.androidContext
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class SigeSchoolApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // 1. Inicializar Logging con Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("SigeSchool Pro: Modo Debug Activado")
        } else {
            // En Fase Beta, plantamos un árbol que capture logs de advertencia y error
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (priority >= android.util.Log.WARN) {
                        // En Beta, guardamos logs críticos localmente si hay una excepción
                        t?.let { saveCrashReport(it) }
                    }
                }
            })
        }
        
        // 2. Configurar Crash Handler Global
        setupGlobalCrashHandler()
        
        // 3. Inicializar Koin
        initKoin(
            additionalModules = listOf(
                uiModule, 
                localModule, 
                databaseModule(), 
                repositoryImplementationModule,
                com.sigeschool.di.androidPlatformModule
            )
        ) {
            allowOverride(true)
            androidContext(this@SigeSchoolApp)
        }

        // 3.1 Inicializar DownloadHelper para Android
        DownloadHelper.init(this)
        
        // 4. Configurar Worker de Sincronización
        setupSyncWorker()
    }

    private fun setupGlobalCrashHandler() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            saveCrashReport(throwable)
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }

    private fun saveCrashReport(throwable: Throwable) {
        try {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val reportFile = File(getExternalFilesDir(null), "crash_report_$timestamp.txt")
            FileOutputStream(reportFile).use { fos ->
                PrintWriter(fos).use { pw ->
                    pw.println("CRASH REPORT - SIGESCHOOL PRO")
                    pw.println("Timestamp: $timestamp")
                    pw.println("Device: ${android.os.Build.MODEL}")
                    pw.println("Android: ${android.os.Build.VERSION.RELEASE}")
                    pw.println("----------------------------------------")
                    throwable.printStackTrace(pw)
                }
            }
            Timber.e("Crash detectado. Reporte guardado en: ${reportFile.absolutePath}")
        } catch (e: Exception) {
            Timber.e(e, "Error al guardar el reporte de crash")
        }
    }

    private fun setupSyncWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SigeSchoolSync",
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }
}

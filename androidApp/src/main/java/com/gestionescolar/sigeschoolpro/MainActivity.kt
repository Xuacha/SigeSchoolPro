package com.gestionescolar.sigeschoolpro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.work.*
import com.sigeschool.App
import com.sigeschool.android.workers.SyncWorker
import com.sigeschool.data.local.db.SecureDatabaseManager
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var isDatabaseReady by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                SecureDatabaseManager.initializeEncryptedDatabase(
                    activity = this@MainActivity,
                    onSuccess = { secureDatabase ->
                        // Sobreescribimos la instancia de AppDatabase en Koin
                        loadKoinModules(module {
                            single { secureDatabase }
                        })
                        isDatabaseReady = true
                        scheduleSync()
                    },
                    onError = { errorMessage ->
                        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                        // En un caso real, podrías mostrar una pantalla de error o reintentar
                    }
                )
            }

            if (isDatabaseReady) {
                App()
            } else {
                // Pantalla de carga mientras se autentica y abre la DB
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    private fun scheduleSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            15, TimeUnit.MINUTES
        )
        .setConstraints(constraints)
        .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SigeSchoolSync",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }
}

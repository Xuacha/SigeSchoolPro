package com.gestionescolar.sigeschoolpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sigeschool.App

// FIX: esta Activity agendaba su propio WorkManager periódico
// ("student_sync") usando un SyncWorker de un paquete antiguo
// (com.gestionescolar.sigeschoolpro.workers), MIENTRAS que
// SigeSchoolApp (la Application real, ver AndroidManifest) también
// agenda un worker periódico equivalente bajo otro nombre único
// ("SigeSchoolSync"). Como enqueueUniquePeriodicWork solo evita
// duplicados cuando el NOMBRE coincide, en la práctica corrían DOS
// sincronizaciones cada 15 minutos en paralelo (doble consumo de
// batería/datos y doble carga contra Supabase). Se elimina el
// agendado duplicado aquí; SigeSchoolApp.setupSyncWorker() es ahora
// la única fuente de verdad para la sincronización en segundo plano.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            App()
        }
    }
}

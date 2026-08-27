package com.sigeschool.services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

actual class PlatformPermissionHandler(private val context: Context) : PermissionHandler {
    
    actual constructor() : this(org.koin.java.KoinJavaComponent.get(Context::class.java))

    actual override suspend fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    actual override suspend fun requestCameraPermission(): Boolean {
        // En una implementación real con Compose, esto dispararía un evento hacia la Activity
        // o usaría un ActivityResultLauncher. Para KMP, a menudo se delega a la UI.
        return hasCameraPermission() 
    }
}

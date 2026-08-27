package com.sigeschool.services

actual class PlatformPermissionHandler : PermissionHandler {
    actual constructor()
    actual override suspend fun hasCameraPermission(): Boolean = true
    actual override suspend fun requestCameraPermission(): Boolean = true
}

package com.sigeschool.services

interface PermissionHandler {
    suspend fun requestCameraPermission(): Boolean
    suspend fun hasCameraPermission(): Boolean
}

expect class PlatformPermissionHandler : PermissionHandler {
    constructor()
    override suspend fun requestCameraPermission(): Boolean
    override suspend fun hasCameraPermission(): Boolean
}

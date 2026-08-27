package com.sigeschool.util

import androidx.compose.runtime.Composable

enum class PlatformType {
    ANDROID, DESKTOP, WEB
}

expect val currentPlatform: PlatformType

@Composable
fun isDesktop(): Boolean = currentPlatform == PlatformType.DESKTOP

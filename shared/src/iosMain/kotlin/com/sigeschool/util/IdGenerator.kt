package com.sigeschool.util

import platform.Foundation.NSUUID
import kotlinx.uuid.generateUUID as kUUID

actual fun generateId(): String {
    return NSUUID().UUIDString()
}

actual fun generateUUID(): String {
    return NSUUID().UUIDString()
}

package com.sigeschool.util

import java.util.UUID as JavaUUID

actual fun generateId(): String {
    return JavaUUID.randomUUID().toString()
}

actual fun generateUUID(): String {
    return JavaUUID.randomUUID().toString()
}

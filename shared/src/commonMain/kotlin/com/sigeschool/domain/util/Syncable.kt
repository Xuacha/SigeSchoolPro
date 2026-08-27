package com.sigeschool.domain.util

interface Syncable {
    val id: String
    val version: Long
    val deviceId: String
    val lastModified: Long
}

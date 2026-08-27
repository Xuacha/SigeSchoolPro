package com.sigeschool.domain

interface AuditRepository {
    suspend fun log(
        action: String,
        resource: String,
        payload: Map<String, Any?>? = null
    ): Result<Unit>
}

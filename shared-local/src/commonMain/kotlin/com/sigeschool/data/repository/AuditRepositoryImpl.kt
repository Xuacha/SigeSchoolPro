package com.sigeschool.data.repository

import com.sigeschool.data.local.database.AppDatabase
import com.sigeschool.data.local.entity.AuditLogEntity
import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.domain.util.randomUUID
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class AuditRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val sessionManager: SessionManager,
    private val database: AppDatabase
) : AuditRepository {

    private val auditLogDao = database.auditLogDao()

    override suspend fun log(
        action: String,
        resource: String,
        payload: Map<String, Any?>?
    ): Result<Unit> {
        return try {
            val state = sessionManager.sessionState.value
            if (state !is SessionState.LoggedIn) {
                return Result.failure(Exception("No active session for auditing"))
            }

            val institutionId = state.institutionId ?: return Result.failure(Exception("Institution not found in session"))
            val user = state.user
            
            // Obtenemos metadatos del usuario de Supabase Auth
            val userName = user.userMetadata?.get("full_name")?.toString() ?: user.email ?: "Unknown"
            val userRole = user.userMetadata?.get("role")?.toString() ?: "USER"

            val detailsJson = payload?.let { Json.encodeToString(it) } ?: ""

            val auditLogId = randomUUID()
            val timestamp = System.currentTimeMillis()

            // 1. Persistencia local obligatoria (Offline-First)
            val localLog = AuditLogEntity(
                id = auditLogId,
                institutionId = institutionId,
                userId = user.id,
                userName = userName,
                userRole = userRole,
                action = action,
                entityName = resource,
                entityId = user.id,
                details = detailsJson,
                timestamp = timestamp
            )
            auditLogDao.insert(localLog)

            // 2. Envío a Supabase si hay conexión (RLS protegerá el insert)
            try {
                val remoteLog = mapOf(
                    "institution_id" to institutionId,
                    "actor_id" to user.id,
                    "action" to action,
                    "resource" to resource,
                    "payload" to detailsJson
                )
                supabaseClient.from("audit_logs").insert(remoteLog)
            } catch (networkError: Exception) {
                // El log ya está a salvo en Room. 
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

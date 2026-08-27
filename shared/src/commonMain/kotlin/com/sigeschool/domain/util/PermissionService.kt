package com.sigeschool.domain.util

import com.sigeschool.domain.model.Role
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class PermissionService {
    
    fun hasPermission(role: Role, resource: String, action: String): Boolean {
        // Permissions are stored in role.permissions as resource -> actions (e.g. "users" -> "crud")
        val actions = role.permissions[resource] ?: return false
        
        return when (action.lowercase()) {
            "read", "r" -> actions.contains("r", ignoreCase = true)
            "create", "c" -> actions.contains("c", ignoreCase = true)
            "update", "u" -> actions.contains("u", ignoreCase = true)
            "delete", "d" -> actions.contains("d", ignoreCase = true)
            "all", "*" -> actions == "crud" || actions == "*"
            else -> false
        }
    }

    /**
     * Checks if a role has the required hierarchy level.
     * Lower level number means higher privilege (1 is Admin).
     */
    fun hasLevel(role: Role, requiredLevel: Int): Boolean {
        return role.level <= requiredLevel
    }

    fun canUploadCurricular(role: Role): Boolean {
        return hasPermission(role, "curricular", "create") || role.level <= 4 // Rector and above
    }
}

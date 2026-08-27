package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.RoleEntity
import com.sigeschool.data.local.entity.PermisoEntity
import com.sigeschool.data.local.entity.RolePermisoCrossReference
import kotlinx.coroutines.flow.Flow

@Dao
interface RoleDao {
    @Query("SELECT * FROM roles ORDER BY nivel ASC")
    fun getAllRoles(): Flow<List<RoleEntity>>

    @Query("SELECT * FROM roles WHERE idRol = :id")
    suspend fun getRoleById(id: String): RoleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRole(role: RoleEntity)

    @Delete
    suspend fun deleteRole(role: RoleEntity)

    @Query("SELECT * FROM permisos")
    fun getAllPermissions(): Flow<List<PermisoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPermission(permission: PermisoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRolePermission(crossRef: RolePermisoCrossReference)

    @Transaction
    @Query("SELECT * FROM permisos WHERE idPermiso IN (SELECT idPermiso FROM roles_permisos WHERE idRol = :roleId)")
    fun getPermissionsForRole(roleId: String): Flow<List<PermisoEntity>>
}

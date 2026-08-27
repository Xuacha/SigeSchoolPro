package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PermisoEntity
import com.sigeschool.`data`.local.entity.RoleEntity
import com.sigeschool.`data`.local.entity.RolePermisoCrossReference
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RoleDao_Impl(
  __db: RoomDatabase,
) : RoleDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRoleEntity: EntityInsertAdapter<RoleEntity>

  private val __insertAdapterOfPermisoEntity: EntityInsertAdapter<PermisoEntity>

  private val __insertAdapterOfRolePermisoCrossReference:
      EntityInsertAdapter<RolePermisoCrossReference>

  private val __deleteAdapterOfRoleEntity: EntityDeleteOrUpdateAdapter<RoleEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRoleEntity = object : EntityInsertAdapter<RoleEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `roles` (`idRol`,`nombre`,`nivel`,`descripcion`,`permisosJson`,`esSistema`,`fechaCreacion`,`fechaActualizacion`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RoleEntity) {
        statement.bindText(1, entity.idRol)
        statement.bindText(2, entity.nombre)
        statement.bindLong(3, entity.nivel.toLong())
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDescripcion)
        }
        statement.bindText(5, entity.permisosJson)
        val _tmp: Int = if (entity.esSistema) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.fechaCreacion)
        statement.bindLong(8, entity.fechaActualizacion)
      }
    }
    this.__insertAdapterOfPermisoEntity = object : EntityInsertAdapter<PermisoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `permisos` (`idPermiso`,`nombre`,`recurso`,`accion`,`descripcion`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PermisoEntity) {
        statement.bindText(1, entity.idPermiso)
        statement.bindText(2, entity.nombre)
        statement.bindText(3, entity.recurso)
        statement.bindText(4, entity.accion)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDescripcion)
        }
      }
    }
    this.__insertAdapterOfRolePermisoCrossReference = object :
        EntityInsertAdapter<RolePermisoCrossReference>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `roles_permisos` (`idRol`,`idPermiso`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RolePermisoCrossReference) {
        statement.bindText(1, entity.idRol)
        statement.bindText(2, entity.idPermiso)
      }
    }
    this.__deleteAdapterOfRoleEntity = object : EntityDeleteOrUpdateAdapter<RoleEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `roles` WHERE `idRol` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RoleEntity) {
        statement.bindText(1, entity.idRol)
      }
    }
  }

  public override suspend fun insertRole(role: RoleEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfRoleEntity.insert(_connection, role)
  }

  public override suspend fun insertPermission(permission: PermisoEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPermisoEntity.insert(_connection, permission)
  }

  public override suspend fun insertRolePermission(crossRef: RolePermisoCrossReference): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRolePermisoCrossReference.insert(_connection, crossRef)
  }

  public override suspend fun deleteRole(role: RoleEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __deleteAdapterOfRoleEntity.handle(_connection, role)
  }

  public override fun getAllRoles(): Flow<List<RoleEntity>> {
    val _sql: String = "SELECT * FROM roles ORDER BY nivel ASC"
    return createFlow(__db, false, arrayOf("roles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfIdRol: Int = getColumnIndexOrThrow(_stmt, "idRol")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfNivel: Int = getColumnIndexOrThrow(_stmt, "nivel")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfPermisosJson: Int = getColumnIndexOrThrow(_stmt, "permisosJson")
        val _columnIndexOfEsSistema: Int = getColumnIndexOrThrow(_stmt, "esSistema")
        val _columnIndexOfFechaCreacion: Int = getColumnIndexOrThrow(_stmt, "fechaCreacion")
        val _columnIndexOfFechaActualizacion: Int = getColumnIndexOrThrow(_stmt,
            "fechaActualizacion")
        val _result: MutableList<RoleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RoleEntity
          val _tmpIdRol: String
          _tmpIdRol = _stmt.getText(_columnIndexOfIdRol)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpNivel: Int
          _tmpNivel = _stmt.getLong(_columnIndexOfNivel).toInt()
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpPermisosJson: String
          _tmpPermisosJson = _stmt.getText(_columnIndexOfPermisosJson)
          val _tmpEsSistema: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsSistema).toInt()
          _tmpEsSistema = _tmp != 0
          val _tmpFechaCreacion: Long
          _tmpFechaCreacion = _stmt.getLong(_columnIndexOfFechaCreacion)
          val _tmpFechaActualizacion: Long
          _tmpFechaActualizacion = _stmt.getLong(_columnIndexOfFechaActualizacion)
          _item =
              RoleEntity(_tmpIdRol,_tmpNombre,_tmpNivel,_tmpDescripcion,_tmpPermisosJson,_tmpEsSistema,_tmpFechaCreacion,_tmpFechaActualizacion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRoleById(id: String): RoleEntity? {
    val _sql: String = "SELECT * FROM roles WHERE idRol = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfIdRol: Int = getColumnIndexOrThrow(_stmt, "idRol")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfNivel: Int = getColumnIndexOrThrow(_stmt, "nivel")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfPermisosJson: Int = getColumnIndexOrThrow(_stmt, "permisosJson")
        val _columnIndexOfEsSistema: Int = getColumnIndexOrThrow(_stmt, "esSistema")
        val _columnIndexOfFechaCreacion: Int = getColumnIndexOrThrow(_stmt, "fechaCreacion")
        val _columnIndexOfFechaActualizacion: Int = getColumnIndexOrThrow(_stmt,
            "fechaActualizacion")
        val _result: RoleEntity?
        if (_stmt.step()) {
          val _tmpIdRol: String
          _tmpIdRol = _stmt.getText(_columnIndexOfIdRol)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpNivel: Int
          _tmpNivel = _stmt.getLong(_columnIndexOfNivel).toInt()
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpPermisosJson: String
          _tmpPermisosJson = _stmt.getText(_columnIndexOfPermisosJson)
          val _tmpEsSistema: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsSistema).toInt()
          _tmpEsSistema = _tmp != 0
          val _tmpFechaCreacion: Long
          _tmpFechaCreacion = _stmt.getLong(_columnIndexOfFechaCreacion)
          val _tmpFechaActualizacion: Long
          _tmpFechaActualizacion = _stmt.getLong(_columnIndexOfFechaActualizacion)
          _result =
              RoleEntity(_tmpIdRol,_tmpNombre,_tmpNivel,_tmpDescripcion,_tmpPermisosJson,_tmpEsSistema,_tmpFechaCreacion,_tmpFechaActualizacion)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllPermissions(): Flow<List<PermisoEntity>> {
    val _sql: String = "SELECT * FROM permisos"
    return createFlow(__db, false, arrayOf("permisos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfIdPermiso: Int = getColumnIndexOrThrow(_stmt, "idPermiso")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfRecurso: Int = getColumnIndexOrThrow(_stmt, "recurso")
        val _columnIndexOfAccion: Int = getColumnIndexOrThrow(_stmt, "accion")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _result: MutableList<PermisoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PermisoEntity
          val _tmpIdPermiso: String
          _tmpIdPermiso = _stmt.getText(_columnIndexOfIdPermiso)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpRecurso: String
          _tmpRecurso = _stmt.getText(_columnIndexOfRecurso)
          val _tmpAccion: String
          _tmpAccion = _stmt.getText(_columnIndexOfAccion)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          _item = PermisoEntity(_tmpIdPermiso,_tmpNombre,_tmpRecurso,_tmpAccion,_tmpDescripcion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPermissionsForRole(roleId: String): Flow<List<PermisoEntity>> {
    val _sql: String =
        "SELECT * FROM permisos WHERE idPermiso IN (SELECT idPermiso FROM roles_permisos WHERE idRol = ?)"
    return createFlow(__db, true, arrayOf("permisos", "roles_permisos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, roleId)
        val _columnIndexOfIdPermiso: Int = getColumnIndexOrThrow(_stmt, "idPermiso")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfRecurso: Int = getColumnIndexOrThrow(_stmt, "recurso")
        val _columnIndexOfAccion: Int = getColumnIndexOrThrow(_stmt, "accion")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _result: MutableList<PermisoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PermisoEntity
          val _tmpIdPermiso: String
          _tmpIdPermiso = _stmt.getText(_columnIndexOfIdPermiso)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpRecurso: String
          _tmpRecurso = _stmt.getText(_columnIndexOfRecurso)
          val _tmpAccion: String
          _tmpAccion = _stmt.getText(_columnIndexOfAccion)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          _item = PermisoEntity(_tmpIdPermiso,_tmpNombre,_tmpRecurso,_tmpAccion,_tmpDescripcion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

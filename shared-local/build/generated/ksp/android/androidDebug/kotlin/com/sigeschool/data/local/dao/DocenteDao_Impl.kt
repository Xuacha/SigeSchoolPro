package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.DocenteEntity
import javax.`annotation`.processing.Generated
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
public class DocenteDao_Impl(
  __db: RoomDatabase,
) : DocenteDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDocenteEntity: EntityInsertAdapter<DocenteEntity>

  private val __updateAdapterOfDocenteEntity: EntityDeleteOrUpdateAdapter<DocenteEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDocenteEntity = object : EntityInsertAdapter<DocenteEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `employee_docentes` (`id`,`userId`,`institutionId`,`especialidad`,`tipoContrato`,`fechaIngreso`,`estado`,`sedePrincipalId`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.userId)
        statement.bindText(3, entity.institutionId)
        val _tmpEspecialidad: String? = entity.especialidad
        if (_tmpEspecialidad == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpEspecialidad)
        }
        val _tmpTipoContrato: String? = entity.tipoContrato
        if (_tmpTipoContrato == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpTipoContrato)
        }
        statement.bindLong(6, entity.fechaIngreso)
        statement.bindText(7, entity.estado)
        val _tmpSedePrincipalId: Long? = entity.sedePrincipalId
        if (_tmpSedePrincipalId == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpSedePrincipalId)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfDocenteEntity = object : EntityDeleteOrUpdateAdapter<DocenteEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `employee_docentes` SET `id` = ?,`userId` = ?,`institutionId` = ?,`especialidad` = ?,`tipoContrato` = ?,`fechaIngreso` = ?,`estado` = ?,`sedePrincipalId` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.userId)
        statement.bindText(3, entity.institutionId)
        val _tmpEspecialidad: String? = entity.especialidad
        if (_tmpEspecialidad == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpEspecialidad)
        }
        val _tmpTipoContrato: String? = entity.tipoContrato
        if (_tmpTipoContrato == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpTipoContrato)
        }
        statement.bindLong(6, entity.fechaIngreso)
        statement.bindText(7, entity.estado)
        val _tmpSedePrincipalId: Long? = entity.sedePrincipalId
        if (_tmpSedePrincipalId == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpSedePrincipalId)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindLong(11, entity.id)
      }
    }
  }

  public override suspend fun insert(docente: DocenteEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfDocenteEntity.insertAndReturnId(_connection, docente)
    _result
  }

  public override suspend fun update(docente: DocenteEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfDocenteEntity.handle(_connection, docente)
  }

  public override fun getAll(instId: String): Flow<List<DocenteEntity>> {
    val _sql: String =
        "SELECT * FROM employee_docentes WHERE institutionId = ? AND estado = 'ACTIVO'"
    return createFlow(__db, false, arrayOf("employee_docentes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEspecialidad: Int = getColumnIndexOrThrow(_stmt, "especialidad")
        val _columnIndexOfTipoContrato: Int = getColumnIndexOrThrow(_stmt, "tipoContrato")
        val _columnIndexOfFechaIngreso: Int = getColumnIndexOrThrow(_stmt, "fechaIngreso")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSedePrincipalId: Int = getColumnIndexOrThrow(_stmt, "sedePrincipalId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DocenteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocenteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEspecialidad: String?
          if (_stmt.isNull(_columnIndexOfEspecialidad)) {
            _tmpEspecialidad = null
          } else {
            _tmpEspecialidad = _stmt.getText(_columnIndexOfEspecialidad)
          }
          val _tmpTipoContrato: String?
          if (_stmt.isNull(_columnIndexOfTipoContrato)) {
            _tmpTipoContrato = null
          } else {
            _tmpTipoContrato = _stmt.getText(_columnIndexOfTipoContrato)
          }
          val _tmpFechaIngreso: Long
          _tmpFechaIngreso = _stmt.getLong(_columnIndexOfFechaIngreso)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSedePrincipalId: Long?
          if (_stmt.isNull(_columnIndexOfSedePrincipalId)) {
            _tmpSedePrincipalId = null
          } else {
            _tmpSedePrincipalId = _stmt.getLong(_columnIndexOfSedePrincipalId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DocenteEntity(_tmpId,_tmpUserId,_tmpInstitutionId,_tmpEspecialidad,_tmpTipoContrato,_tmpFechaIngreso,_tmpEstado,_tmpSedePrincipalId,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByUserId(userId: String, instId: String): DocenteEntity? {
    val _sql: String = "SELECT * FROM employee_docentes WHERE userId = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEspecialidad: Int = getColumnIndexOrThrow(_stmt, "especialidad")
        val _columnIndexOfTipoContrato: Int = getColumnIndexOrThrow(_stmt, "tipoContrato")
        val _columnIndexOfFechaIngreso: Int = getColumnIndexOrThrow(_stmt, "fechaIngreso")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSedePrincipalId: Int = getColumnIndexOrThrow(_stmt, "sedePrincipalId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: DocenteEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEspecialidad: String?
          if (_stmt.isNull(_columnIndexOfEspecialidad)) {
            _tmpEspecialidad = null
          } else {
            _tmpEspecialidad = _stmt.getText(_columnIndexOfEspecialidad)
          }
          val _tmpTipoContrato: String?
          if (_stmt.isNull(_columnIndexOfTipoContrato)) {
            _tmpTipoContrato = null
          } else {
            _tmpTipoContrato = _stmt.getText(_columnIndexOfTipoContrato)
          }
          val _tmpFechaIngreso: Long
          _tmpFechaIngreso = _stmt.getLong(_columnIndexOfFechaIngreso)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSedePrincipalId: Long?
          if (_stmt.isNull(_columnIndexOfSedePrincipalId)) {
            _tmpSedePrincipalId = null
          } else {
            _tmpSedePrincipalId = _stmt.getLong(_columnIndexOfSedePrincipalId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              DocenteEntity(_tmpId,_tmpUserId,_tmpInstitutionId,_tmpEspecialidad,_tmpTipoContrato,_tmpFechaIngreso,_tmpEstado,_tmpSedePrincipalId,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): DocenteEntity? {
    val _sql: String = "SELECT * FROM employee_docentes WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEspecialidad: Int = getColumnIndexOrThrow(_stmt, "especialidad")
        val _columnIndexOfTipoContrato: Int = getColumnIndexOrThrow(_stmt, "tipoContrato")
        val _columnIndexOfFechaIngreso: Int = getColumnIndexOrThrow(_stmt, "fechaIngreso")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSedePrincipalId: Int = getColumnIndexOrThrow(_stmt, "sedePrincipalId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: DocenteEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEspecialidad: String?
          if (_stmt.isNull(_columnIndexOfEspecialidad)) {
            _tmpEspecialidad = null
          } else {
            _tmpEspecialidad = _stmt.getText(_columnIndexOfEspecialidad)
          }
          val _tmpTipoContrato: String?
          if (_stmt.isNull(_columnIndexOfTipoContrato)) {
            _tmpTipoContrato = null
          } else {
            _tmpTipoContrato = _stmt.getText(_columnIndexOfTipoContrato)
          }
          val _tmpFechaIngreso: Long
          _tmpFechaIngreso = _stmt.getLong(_columnIndexOfFechaIngreso)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSedePrincipalId: Long?
          if (_stmt.isNull(_columnIndexOfSedePrincipalId)) {
            _tmpSedePrincipalId = null
          } else {
            _tmpSedePrincipalId = _stmt.getLong(_columnIndexOfSedePrincipalId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              DocenteEntity(_tmpId,_tmpUserId,_tmpInstitutionId,_tmpEspecialidad,_tmpTipoContrato,_tmpFechaIngreso,_tmpEstado,_tmpSedePrincipalId,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM employee_docentes WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

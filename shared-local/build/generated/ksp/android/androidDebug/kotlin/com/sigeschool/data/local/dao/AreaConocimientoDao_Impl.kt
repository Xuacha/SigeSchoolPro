package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AreaConocimientoEntity
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
public class AreaConocimientoDao_Impl(
  __db: RoomDatabase,
) : AreaConocimientoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAreaConocimientoEntity: EntityInsertAdapter<AreaConocimientoEntity>

  private val __updateAdapterOfAreaConocimientoEntity:
      EntityDeleteOrUpdateAdapter<AreaConocimientoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAreaConocimientoEntity = object :
        EntityInsertAdapter<AreaConocimientoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_areas_conocimiento` (`id`,`institutionId`,`nombre`,`descripcion`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AreaConocimientoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDescripcion)
        }
        statement.bindLong(5, entity.syncStatus.toLong())
        statement.bindLong(6, entity.lastModified)
      }
    }
    this.__updateAdapterOfAreaConocimientoEntity = object :
        EntityDeleteOrUpdateAdapter<AreaConocimientoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_areas_conocimiento` SET `id` = ?,`institutionId` = ?,`nombre` = ?,`descripcion` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AreaConocimientoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDescripcion)
        }
        statement.bindLong(5, entity.syncStatus.toLong())
        statement.bindLong(6, entity.lastModified)
        statement.bindLong(7, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: AreaConocimientoEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfAreaConocimientoEntity.insertAndReturnId(_connection,
        entity)
    _result
  }

  public override suspend fun update(entity: AreaConocimientoEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfAreaConocimientoEntity.handle(_connection, entity)
  }

  public override fun getAll(instId: String): Flow<List<AreaConocimientoEntity>> {
    val _sql: String =
        "SELECT * FROM academic_areas_conocimiento WHERE institutionId = ? ORDER BY nombre ASC"
    return createFlow(__db, false, arrayOf("academic_areas_conocimiento")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AreaConocimientoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AreaConocimientoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AreaConocimientoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDescripcion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): AreaConocimientoEntity? {
    val _sql: String =
        "SELECT * FROM academic_areas_conocimiento WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: AreaConocimientoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              AreaConocimientoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDescripcion,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<AreaConocimientoEntity> {
    val _sql: String =
        "SELECT * FROM academic_areas_conocimiento WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AreaConocimientoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AreaConocimientoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AreaConocimientoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDescripcion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<AreaConocimientoEntity> {
    val _sql: String = "SELECT * FROM academic_areas_conocimiento WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AreaConocimientoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AreaConocimientoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AreaConocimientoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDescripcion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_areas_conocimiento WHERE id = ? AND institutionId = ?"
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

  public override suspend fun markAsSynced(id: Long, instId: String) {
    val _sql: String =
        "UPDATE academic_areas_conocimiento SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

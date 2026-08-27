package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.SedeEntity
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
public class SedeDao_Impl(
  __db: RoomDatabase,
) : SedeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSedeEntity: EntityInsertAdapter<SedeEntity>

  private val __updateAdapterOfSedeEntity: EntityDeleteOrUpdateAdapter<SedeEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSedeEntity = object : EntityInsertAdapter<SedeEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_sedes` (`id`,`institutionId`,`nombre`,`direccion`,`telefono`,`activa`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SedeEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpDireccion: String? = entity.direccion
        if (_tmpDireccion == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDireccion)
        }
        val _tmpTelefono: String? = entity.telefono
        if (_tmpTelefono == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpTelefono)
        }
        val _tmp: Int = if (entity.activa) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
      }
    }
    this.__updateAdapterOfSedeEntity = object : EntityDeleteOrUpdateAdapter<SedeEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_sedes` SET `id` = ?,`institutionId` = ?,`nombre` = ?,`direccion` = ?,`telefono` = ?,`activa` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SedeEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpDireccion: String? = entity.direccion
        if (_tmpDireccion == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDireccion)
        }
        val _tmpTelefono: String? = entity.telefono
        if (_tmpTelefono == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpTelefono)
        }
        val _tmp: Int = if (entity.activa) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
        statement.bindLong(9, entity.id)
      }
    }
  }

  public override suspend fun insert(sede: SedeEntity): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfSedeEntity.insertAndReturnId(_connection, sede)
    _result
  }

  public override suspend fun update(sede: SedeEntity): Unit = performSuspending(__db, false, true)
      { _connection ->
    __updateAdapterOfSedeEntity.handle(_connection, sede)
  }

  public override fun getAll(instId: String): Flow<List<SedeEntity>> {
    val _sql: String =
        "SELECT * FROM academic_sedes WHERE institutionId = ? AND activa = 1 ORDER BY nombre ASC"
    return createFlow(__db, false, arrayOf("academic_sedes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDireccion: Int = getColumnIndexOrThrow(_stmt, "direccion")
        val _columnIndexOfTelefono: Int = getColumnIndexOrThrow(_stmt, "telefono")
        val _columnIndexOfActiva: Int = getColumnIndexOrThrow(_stmt, "activa")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<SedeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SedeEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDireccion: String?
          if (_stmt.isNull(_columnIndexOfDireccion)) {
            _tmpDireccion = null
          } else {
            _tmpDireccion = _stmt.getText(_columnIndexOfDireccion)
          }
          val _tmpTelefono: String?
          if (_stmt.isNull(_columnIndexOfTelefono)) {
            _tmpTelefono = null
          } else {
            _tmpTelefono = _stmt.getText(_columnIndexOfTelefono)
          }
          val _tmpActiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActiva).toInt()
          _tmpActiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              SedeEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDireccion,_tmpTelefono,_tmpActiva,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): SedeEntity? {
    val _sql: String = "SELECT * FROM academic_sedes WHERE id = ? AND institutionId = ?"
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
        val _columnIndexOfDireccion: Int = getColumnIndexOrThrow(_stmt, "direccion")
        val _columnIndexOfTelefono: Int = getColumnIndexOrThrow(_stmt, "telefono")
        val _columnIndexOfActiva: Int = getColumnIndexOrThrow(_stmt, "activa")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: SedeEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDireccion: String?
          if (_stmt.isNull(_columnIndexOfDireccion)) {
            _tmpDireccion = null
          } else {
            _tmpDireccion = _stmt.getText(_columnIndexOfDireccion)
          }
          val _tmpTelefono: String?
          if (_stmt.isNull(_columnIndexOfTelefono)) {
            _tmpTelefono = null
          } else {
            _tmpTelefono = _stmt.getText(_columnIndexOfTelefono)
          }
          val _tmpActiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActiva).toInt()
          _tmpActiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              SedeEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDireccion,_tmpTelefono,_tmpActiva,_tmpSyncStatus,_tmpLastModified)
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
    val _sql: String = "DELETE FROM academic_sedes WHERE id = ? AND institutionId = ?"
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

package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.JornadaEntity
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
public class JornadaDao_Impl(
  __db: RoomDatabase,
) : JornadaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfJornadaEntity: EntityInsertAdapter<JornadaEntity>

  private val __updateAdapterOfJornadaEntity: EntityDeleteOrUpdateAdapter<JornadaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfJornadaEntity = object : EntityInsertAdapter<JornadaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_jornadas` (`id`,`institutionId`,`nombre`,`horaInicio`,`horaFin`,`activa`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: JornadaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpHoraInicio: String? = entity.horaInicio
        if (_tmpHoraInicio == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpHoraInicio)
        }
        val _tmpHoraFin: String? = entity.horaFin
        if (_tmpHoraFin == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpHoraFin)
        }
        val _tmp: Int = if (entity.activa) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
      }
    }
    this.__updateAdapterOfJornadaEntity = object : EntityDeleteOrUpdateAdapter<JornadaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_jornadas` SET `id` = ?,`institutionId` = ?,`nombre` = ?,`horaInicio` = ?,`horaFin` = ?,`activa` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: JornadaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpHoraInicio: String? = entity.horaInicio
        if (_tmpHoraInicio == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpHoraInicio)
        }
        val _tmpHoraFin: String? = entity.horaFin
        if (_tmpHoraFin == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpHoraFin)
        }
        val _tmp: Int = if (entity.activa) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
        statement.bindLong(9, entity.id)
      }
    }
  }

  public override suspend fun insert(jornada: JornadaEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfJornadaEntity.insertAndReturnId(_connection, jornada)
    _result
  }

  public override suspend fun update(jornada: JornadaEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfJornadaEntity.handle(_connection, jornada)
  }

  public override fun getAll(instId: String): Flow<List<JornadaEntity>> {
    val _sql: String =
        "SELECT * FROM academic_jornadas WHERE institutionId = ? AND activa = 1 ORDER BY nombre ASC"
    return createFlow(__db, false, arrayOf("academic_jornadas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfActiva: Int = getColumnIndexOrThrow(_stmt, "activa")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<JornadaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: JornadaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHoraInicio: String?
          if (_stmt.isNull(_columnIndexOfHoraInicio)) {
            _tmpHoraInicio = null
          } else {
            _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          }
          val _tmpHoraFin: String?
          if (_stmt.isNull(_columnIndexOfHoraFin)) {
            _tmpHoraFin = null
          } else {
            _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
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
              JornadaEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpHoraInicio,_tmpHoraFin,_tmpActiva,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): JornadaEntity? {
    val _sql: String = "SELECT * FROM academic_jornadas WHERE id = ? AND institutionId = ?"
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
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfActiva: Int = getColumnIndexOrThrow(_stmt, "activa")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: JornadaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHoraInicio: String?
          if (_stmt.isNull(_columnIndexOfHoraInicio)) {
            _tmpHoraInicio = null
          } else {
            _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          }
          val _tmpHoraFin: String?
          if (_stmt.isNull(_columnIndexOfHoraFin)) {
            _tmpHoraFin = null
          } else {
            _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
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
              JornadaEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpHoraInicio,_tmpHoraFin,_tmpActiva,_tmpSyncStatus,_tmpLastModified)
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
    val _sql: String = "DELETE FROM academic_jornadas WHERE id = ? AND institutionId = ?"
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

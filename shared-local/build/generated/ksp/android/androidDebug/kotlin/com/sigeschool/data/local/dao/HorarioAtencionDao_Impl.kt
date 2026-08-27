package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.HorarioAtencionEntity
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
public class HorarioAtencionDao_Impl(
  __db: RoomDatabase,
) : HorarioAtencionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfHorarioAtencionEntity: EntityInsertAdapter<HorarioAtencionEntity>

  private val __deleteAdapterOfHorarioAtencionEntity:
      EntityDeleteOrUpdateAdapter<HorarioAtencionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfHorarioAtencionEntity = object :
        EntityInsertAdapter<HorarioAtencionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `horarios_atencion` (`id`,`institutionId`,`docenteId`,`diaSemana`,`horaInicio`,`horaFin`,`activo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: HorarioAtencionEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.docenteId)
        statement.bindLong(4, entity.diaSemana.toLong())
        statement.bindText(5, entity.horaInicio)
        statement.bindText(6, entity.horaFin)
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
    this.__deleteAdapterOfHorarioAtencionEntity = object :
        EntityDeleteOrUpdateAdapter<HorarioAtencionEntity>() {
      protected override fun createQuery(): String =
          "DELETE FROM `horarios_atencion` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: HorarioAtencionEntity) {
        statement.bindLong(1, entity.id)
      }
    }
  }

  public override suspend fun insert(horario: HorarioAtencionEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfHorarioAtencionEntity.insert(_connection, horario)
  }

  public override suspend fun delete(horario: HorarioAtencionEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfHorarioAtencionEntity.handle(_connection, horario)
  }

  public override fun getByDocente(docenteId: String, instId: String):
      Flow<List<HorarioAtencionEntity>> {
    val _sql: String = "SELECT * FROM horarios_atencion WHERE docenteId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("horarios_atencion")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, docenteId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfDiaSemana: Int = getColumnIndexOrThrow(_stmt, "diaSemana")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<HorarioAtencionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: HorarioAtencionEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpDiaSemana: Int
          _tmpDiaSemana = _stmt.getLong(_columnIndexOfDiaSemana).toInt()
          val _tmpHoraInicio: String
          _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          val _tmpHoraFin: String
          _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              HorarioAtencionEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpDiaSemana,_tmpHoraInicio,_tmpHoraFin,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllByInstitution(instId: String): Flow<List<HorarioAtencionEntity>> {
    val _sql: String = "SELECT * FROM horarios_atencion WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("horarios_atencion")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfDiaSemana: Int = getColumnIndexOrThrow(_stmt, "diaSemana")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<HorarioAtencionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: HorarioAtencionEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpDiaSemana: Int
          _tmpDiaSemana = _stmt.getLong(_columnIndexOfDiaSemana).toInt()
          val _tmpHoraInicio: String
          _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          val _tmpHoraFin: String
          _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              HorarioAtencionEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpDiaSemana,_tmpHoraInicio,_tmpHoraFin,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
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

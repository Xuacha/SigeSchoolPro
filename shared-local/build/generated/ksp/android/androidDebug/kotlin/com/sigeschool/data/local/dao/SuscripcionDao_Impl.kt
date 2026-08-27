package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.SuscripcionEntity
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SuscripcionDao_Impl(
  __db: RoomDatabase,
) : SuscripcionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSuscripcionEntity: EntityInsertAdapter<SuscripcionEntity>

  private val __updateAdapterOfSuscripcionEntity: EntityDeleteOrUpdateAdapter<SuscripcionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSuscripcionEntity = object : EntityInsertAdapter<SuscripcionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `suscripciones` (`id`,`institutionId`,`planId`,`fechaInicio`,`fechaFin`,`estado`,`periodoFacturacion`,`ultimoPagoFecha`,`proximoPagoFecha`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SuscripcionEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.planId)
        statement.bindLong(4, entity.fechaInicio)
        val _tmpFechaFin: Long? = entity.fechaFin
        if (_tmpFechaFin == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpFechaFin)
        }
        statement.bindText(6, entity.estado)
        statement.bindText(7, entity.periodoFacturacion)
        val _tmpUltimoPagoFecha: Long? = entity.ultimoPagoFecha
        if (_tmpUltimoPagoFecha == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpUltimoPagoFecha)
        }
        val _tmpProximoPagoFecha: Long? = entity.proximoPagoFecha
        if (_tmpProximoPagoFecha == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpProximoPagoFecha)
        }
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
      }
    }
    this.__updateAdapterOfSuscripcionEntity = object :
        EntityDeleteOrUpdateAdapter<SuscripcionEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `suscripciones` SET `id` = ?,`institutionId` = ?,`planId` = ?,`fechaInicio` = ?,`fechaFin` = ?,`estado` = ?,`periodoFacturacion` = ?,`ultimoPagoFecha` = ?,`proximoPagoFecha` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SuscripcionEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.planId)
        statement.bindLong(4, entity.fechaInicio)
        val _tmpFechaFin: Long? = entity.fechaFin
        if (_tmpFechaFin == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpFechaFin)
        }
        statement.bindText(6, entity.estado)
        statement.bindText(7, entity.periodoFacturacion)
        val _tmpUltimoPagoFecha: Long? = entity.ultimoPagoFecha
        if (_tmpUltimoPagoFecha == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpUltimoPagoFecha)
        }
        val _tmpProximoPagoFecha: Long? = entity.proximoPagoFecha
        if (_tmpProximoPagoFecha == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpProximoPagoFecha)
        }
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
        statement.bindLong(12, entity.id)
      }
    }
  }

  public override suspend fun insert(suscripcion: SuscripcionEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfSuscripcionEntity.insertAndReturnId(_connection,
        suscripcion)
    _result
  }

  public override suspend fun update(suscripcion: SuscripcionEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfSuscripcionEntity.handle(_connection, suscripcion)
  }

  public override suspend fun getActiva(instId: String): SuscripcionEntity? {
    val _sql: String =
        "SELECT * FROM suscripciones WHERE institutionId = ? AND estado = 'ACTIVA' LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfPeriodoFacturacion: Int = getColumnIndexOrThrow(_stmt,
            "periodoFacturacion")
        val _columnIndexOfUltimoPagoFecha: Int = getColumnIndexOrThrow(_stmt, "ultimoPagoFecha")
        val _columnIndexOfProximoPagoFecha: Int = getColumnIndexOrThrow(_stmt, "proximoPagoFecha")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: SuscripcionEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPlanId: Long
          _tmpPlanId = _stmt.getLong(_columnIndexOfPlanId)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long?
          if (_stmt.isNull(_columnIndexOfFechaFin)) {
            _tmpFechaFin = null
          } else {
            _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpPeriodoFacturacion: String
          _tmpPeriodoFacturacion = _stmt.getText(_columnIndexOfPeriodoFacturacion)
          val _tmpUltimoPagoFecha: Long?
          if (_stmt.isNull(_columnIndexOfUltimoPagoFecha)) {
            _tmpUltimoPagoFecha = null
          } else {
            _tmpUltimoPagoFecha = _stmt.getLong(_columnIndexOfUltimoPagoFecha)
          }
          val _tmpProximoPagoFecha: Long?
          if (_stmt.isNull(_columnIndexOfProximoPagoFecha)) {
            _tmpProximoPagoFecha = null
          } else {
            _tmpProximoPagoFecha = _stmt.getLong(_columnIndexOfProximoPagoFecha)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              SuscripcionEntity(_tmpId,_tmpInstitutionId,_tmpPlanId,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpPeriodoFacturacion,_tmpUltimoPagoFecha,_tmpProximoPagoFecha,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUltima(instId: String): SuscripcionEntity? {
    val _sql: String =
        "SELECT * FROM suscripciones WHERE institutionId = ? ORDER BY fechaInicio DESC LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfPeriodoFacturacion: Int = getColumnIndexOrThrow(_stmt,
            "periodoFacturacion")
        val _columnIndexOfUltimoPagoFecha: Int = getColumnIndexOrThrow(_stmt, "ultimoPagoFecha")
        val _columnIndexOfProximoPagoFecha: Int = getColumnIndexOrThrow(_stmt, "proximoPagoFecha")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: SuscripcionEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPlanId: Long
          _tmpPlanId = _stmt.getLong(_columnIndexOfPlanId)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long?
          if (_stmt.isNull(_columnIndexOfFechaFin)) {
            _tmpFechaFin = null
          } else {
            _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpPeriodoFacturacion: String
          _tmpPeriodoFacturacion = _stmt.getText(_columnIndexOfPeriodoFacturacion)
          val _tmpUltimoPagoFecha: Long?
          if (_stmt.isNull(_columnIndexOfUltimoPagoFecha)) {
            _tmpUltimoPagoFecha = null
          } else {
            _tmpUltimoPagoFecha = _stmt.getLong(_columnIndexOfUltimoPagoFecha)
          }
          val _tmpProximoPagoFecha: Long?
          if (_stmt.isNull(_columnIndexOfProximoPagoFecha)) {
            _tmpProximoPagoFecha = null
          } else {
            _tmpProximoPagoFecha = _stmt.getLong(_columnIndexOfProximoPagoFecha)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              SuscripcionEntity(_tmpId,_tmpInstitutionId,_tmpPlanId,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpPeriodoFacturacion,_tmpUltimoPagoFecha,_tmpProximoPagoFecha,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<SuscripcionEntity> {
    val _sql: String = "SELECT * FROM suscripciones WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfPeriodoFacturacion: Int = getColumnIndexOrThrow(_stmt,
            "periodoFacturacion")
        val _columnIndexOfUltimoPagoFecha: Int = getColumnIndexOrThrow(_stmt, "ultimoPagoFecha")
        val _columnIndexOfProximoPagoFecha: Int = getColumnIndexOrThrow(_stmt, "proximoPagoFecha")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<SuscripcionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SuscripcionEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPlanId: Long
          _tmpPlanId = _stmt.getLong(_columnIndexOfPlanId)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long?
          if (_stmt.isNull(_columnIndexOfFechaFin)) {
            _tmpFechaFin = null
          } else {
            _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpPeriodoFacturacion: String
          _tmpPeriodoFacturacion = _stmt.getText(_columnIndexOfPeriodoFacturacion)
          val _tmpUltimoPagoFecha: Long?
          if (_stmt.isNull(_columnIndexOfUltimoPagoFecha)) {
            _tmpUltimoPagoFecha = null
          } else {
            _tmpUltimoPagoFecha = _stmt.getLong(_columnIndexOfUltimoPagoFecha)
          }
          val _tmpProximoPagoFecha: Long?
          if (_stmt.isNull(_columnIndexOfProximoPagoFecha)) {
            _tmpProximoPagoFecha = null
          } else {
            _tmpProximoPagoFecha = _stmt.getLong(_columnIndexOfProximoPagoFecha)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              SuscripcionEntity(_tmpId,_tmpInstitutionId,_tmpPlanId,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpPeriodoFacturacion,_tmpUltimoPagoFecha,_tmpProximoPagoFecha,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun actualizarVencidas(fechaActual: Long) {
    val _sql: String =
        "UPDATE suscripciones SET estado = 'VENCIDA' WHERE fechaFin < ? AND estado = 'ACTIVA'"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fechaActual)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE suscripciones SET syncStatus = 0, lastModified = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
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

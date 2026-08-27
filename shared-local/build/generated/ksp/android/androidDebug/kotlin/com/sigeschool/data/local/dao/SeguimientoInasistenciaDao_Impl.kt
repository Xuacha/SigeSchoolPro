package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.SeguimientoInasistenciaEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SeguimientoInasistenciaDao_Impl(
  __db: RoomDatabase,
) : SeguimientoInasistenciaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSeguimientoInasistenciaEntity:
      EntityInsertAdapter<SeguimientoInasistenciaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSeguimientoInasistenciaEntity = object :
        EntityInsertAdapter<SeguimientoInasistenciaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `seguimiento_inasistencia` (`id`,`institutionId`,`alertaId`,`usuarioId`,`accion`,`descripcion`,`fechaSeguimiento`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement,
          entity: SeguimientoInasistenciaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.alertaId)
        statement.bindText(4, entity.usuarioId)
        statement.bindText(5, entity.accion)
        statement.bindText(6, entity.descripcion)
        statement.bindLong(7, entity.fechaSeguimiento)
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(seguimiento: SeguimientoInasistenciaEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long =
        __insertAdapterOfSeguimientoInasistenciaEntity.insertAndReturnId(_connection, seguimiento)
    _result
  }

  public override fun getByAlerta(alertaId: Long, instId: String):
      Flow<List<SeguimientoInasistenciaEntity>> {
    val _sql: String =
        "SELECT * FROM seguimiento_inasistencia WHERE alertaId = ? AND institutionId = ? ORDER BY fechaSeguimiento DESC"
    return createFlow(__db, false, arrayOf("seguimiento_inasistencia")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, alertaId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAlertaId: Int = getColumnIndexOrThrow(_stmt, "alertaId")
        val _columnIndexOfUsuarioId: Int = getColumnIndexOrThrow(_stmt, "usuarioId")
        val _columnIndexOfAccion: Int = getColumnIndexOrThrow(_stmt, "accion")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfFechaSeguimiento: Int = getColumnIndexOrThrow(_stmt, "fechaSeguimiento")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<SeguimientoInasistenciaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeguimientoInasistenciaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAlertaId: Long
          _tmpAlertaId = _stmt.getLong(_columnIndexOfAlertaId)
          val _tmpUsuarioId: String
          _tmpUsuarioId = _stmt.getText(_columnIndexOfUsuarioId)
          val _tmpAccion: String
          _tmpAccion = _stmt.getText(_columnIndexOfAccion)
          val _tmpDescripcion: String
          _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          val _tmpFechaSeguimiento: Long
          _tmpFechaSeguimiento = _stmt.getLong(_columnIndexOfFechaSeguimiento)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              SeguimientoInasistenciaEntity(_tmpId,_tmpInstitutionId,_tmpAlertaId,_tmpUsuarioId,_tmpAccion,_tmpDescripcion,_tmpFechaSeguimiento,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<SeguimientoInasistenciaEntity> {
    val _sql: String =
        "SELECT * FROM seguimiento_inasistencia WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAlertaId: Int = getColumnIndexOrThrow(_stmt, "alertaId")
        val _columnIndexOfUsuarioId: Int = getColumnIndexOrThrow(_stmt, "usuarioId")
        val _columnIndexOfAccion: Int = getColumnIndexOrThrow(_stmt, "accion")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfFechaSeguimiento: Int = getColumnIndexOrThrow(_stmt, "fechaSeguimiento")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<SeguimientoInasistenciaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeguimientoInasistenciaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAlertaId: Long
          _tmpAlertaId = _stmt.getLong(_columnIndexOfAlertaId)
          val _tmpUsuarioId: String
          _tmpUsuarioId = _stmt.getText(_columnIndexOfUsuarioId)
          val _tmpAccion: String
          _tmpAccion = _stmt.getText(_columnIndexOfAccion)
          val _tmpDescripcion: String
          _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          val _tmpFechaSeguimiento: Long
          _tmpFechaSeguimiento = _stmt.getLong(_columnIndexOfFechaSeguimiento)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              SeguimientoInasistenciaEntity(_tmpId,_tmpInstitutionId,_tmpAlertaId,_tmpUsuarioId,_tmpAccion,_tmpDescripcion,_tmpFechaSeguimiento,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String =
        "UPDATE seguimiento_inasistencia SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

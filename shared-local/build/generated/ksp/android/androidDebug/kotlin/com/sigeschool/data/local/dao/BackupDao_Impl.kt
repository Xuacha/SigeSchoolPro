package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.BackupLogEntity
import com.sigeschool.`data`.local.entity.KeyBackupLogEntity
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
public class BackupDao_Impl(
  __db: RoomDatabase,
) : BackupDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBackupLogEntity: EntityInsertAdapter<BackupLogEntity>

  private val __insertAdapterOfKeyBackupLogEntity: EntityInsertAdapter<KeyBackupLogEntity>

  private val __updateAdapterOfBackupLogEntity: EntityDeleteOrUpdateAdapter<BackupLogEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfBackupLogEntity = object : EntityInsertAdapter<BackupLogEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `logs_backup` (`idLog`,`fechaInicio`,`fechaFin`,`estado`,`tamanioBytes`,`rutaArchivo`,`errorMensaje`,`esManual`,`metadata`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BackupLogEntity) {
        statement.bindText(1, entity.idLog)
        statement.bindLong(2, entity.fechaInicio)
        val _tmpFechaFin: Long? = entity.fechaFin
        if (_tmpFechaFin == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpFechaFin)
        }
        statement.bindText(4, entity.estado)
        statement.bindLong(5, entity.tamanioBytes)
        val _tmpRutaArchivo: String? = entity.rutaArchivo
        if (_tmpRutaArchivo == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpRutaArchivo)
        }
        val _tmpErrorMensaje: String? = entity.errorMensaje
        if (_tmpErrorMensaje == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpErrorMensaje)
        }
        val _tmp: Int = if (entity.esManual) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        val _tmpMetadata: String? = entity.metadata
        if (_tmpMetadata == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpMetadata)
        }
      }
    }
    this.__insertAdapterOfKeyBackupLogEntity = object : EntityInsertAdapter<KeyBackupLogEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `logs_backup_llaves` (`idLog`,`accion`,`fecha`,`usuarioId`,`exito`,`mensajeError`,`metadata`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: KeyBackupLogEntity) {
        statement.bindText(1, entity.idLog)
        statement.bindText(2, entity.accion)
        statement.bindLong(3, entity.fecha)
        statement.bindText(4, entity.usuarioId)
        val _tmp: Int = if (entity.exito) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmpMensajeError: String? = entity.mensajeError
        if (_tmpMensajeError == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpMensajeError)
        }
        val _tmpMetadata: String? = entity.metadata
        if (_tmpMetadata == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpMetadata)
        }
      }
    }
    this.__updateAdapterOfBackupLogEntity = object : EntityDeleteOrUpdateAdapter<BackupLogEntity>()
        {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `logs_backup` SET `idLog` = ?,`fechaInicio` = ?,`fechaFin` = ?,`estado` = ?,`tamanioBytes` = ?,`rutaArchivo` = ?,`errorMensaje` = ?,`esManual` = ?,`metadata` = ? WHERE `idLog` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BackupLogEntity) {
        statement.bindText(1, entity.idLog)
        statement.bindLong(2, entity.fechaInicio)
        val _tmpFechaFin: Long? = entity.fechaFin
        if (_tmpFechaFin == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpFechaFin)
        }
        statement.bindText(4, entity.estado)
        statement.bindLong(5, entity.tamanioBytes)
        val _tmpRutaArchivo: String? = entity.rutaArchivo
        if (_tmpRutaArchivo == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpRutaArchivo)
        }
        val _tmpErrorMensaje: String? = entity.errorMensaje
        if (_tmpErrorMensaje == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpErrorMensaje)
        }
        val _tmp: Int = if (entity.esManual) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        val _tmpMetadata: String? = entity.metadata
        if (_tmpMetadata == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpMetadata)
        }
        statement.bindText(10, entity.idLog)
      }
    }
  }

  public override suspend fun insertLog(log: BackupLogEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfBackupLogEntity.insert(_connection, log)
  }

  public override suspend fun insertKeyBackupLog(log: KeyBackupLogEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfKeyBackupLogEntity.insert(_connection, log)
  }

  public override suspend fun updateLog(log: BackupLogEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfBackupLogEntity.handle(_connection, log)
  }

  public override fun getAllLogs(): Flow<List<BackupLogEntity>> {
    val _sql: String = "SELECT * FROM logs_backup ORDER BY fechaInicio DESC"
    return createFlow(__db, false, arrayOf("logs_backup")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfIdLog: Int = getColumnIndexOrThrow(_stmt, "idLog")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfTamanioBytes: Int = getColumnIndexOrThrow(_stmt, "tamanioBytes")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfErrorMensaje: Int = getColumnIndexOrThrow(_stmt, "errorMensaje")
        val _columnIndexOfEsManual: Int = getColumnIndexOrThrow(_stmt, "esManual")
        val _columnIndexOfMetadata: Int = getColumnIndexOrThrow(_stmt, "metadata")
        val _result: MutableList<BackupLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BackupLogEntity
          val _tmpIdLog: String
          _tmpIdLog = _stmt.getText(_columnIndexOfIdLog)
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
          val _tmpTamanioBytes: Long
          _tmpTamanioBytes = _stmt.getLong(_columnIndexOfTamanioBytes)
          val _tmpRutaArchivo: String?
          if (_stmt.isNull(_columnIndexOfRutaArchivo)) {
            _tmpRutaArchivo = null
          } else {
            _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          }
          val _tmpErrorMensaje: String?
          if (_stmt.isNull(_columnIndexOfErrorMensaje)) {
            _tmpErrorMensaje = null
          } else {
            _tmpErrorMensaje = _stmt.getText(_columnIndexOfErrorMensaje)
          }
          val _tmpEsManual: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsManual).toInt()
          _tmpEsManual = _tmp != 0
          val _tmpMetadata: String?
          if (_stmt.isNull(_columnIndexOfMetadata)) {
            _tmpMetadata = null
          } else {
            _tmpMetadata = _stmt.getText(_columnIndexOfMetadata)
          }
          _item =
              BackupLogEntity(_tmpIdLog,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpTamanioBytes,_tmpRutaArchivo,_tmpErrorMensaje,_tmpEsManual,_tmpMetadata)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLogById(id: String): BackupLogEntity? {
    val _sql: String = "SELECT * FROM logs_backup WHERE idLog = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfIdLog: Int = getColumnIndexOrThrow(_stmt, "idLog")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfTamanioBytes: Int = getColumnIndexOrThrow(_stmt, "tamanioBytes")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfErrorMensaje: Int = getColumnIndexOrThrow(_stmt, "errorMensaje")
        val _columnIndexOfEsManual: Int = getColumnIndexOrThrow(_stmt, "esManual")
        val _columnIndexOfMetadata: Int = getColumnIndexOrThrow(_stmt, "metadata")
        val _result: BackupLogEntity?
        if (_stmt.step()) {
          val _tmpIdLog: String
          _tmpIdLog = _stmt.getText(_columnIndexOfIdLog)
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
          val _tmpTamanioBytes: Long
          _tmpTamanioBytes = _stmt.getLong(_columnIndexOfTamanioBytes)
          val _tmpRutaArchivo: String?
          if (_stmt.isNull(_columnIndexOfRutaArchivo)) {
            _tmpRutaArchivo = null
          } else {
            _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          }
          val _tmpErrorMensaje: String?
          if (_stmt.isNull(_columnIndexOfErrorMensaje)) {
            _tmpErrorMensaje = null
          } else {
            _tmpErrorMensaje = _stmt.getText(_columnIndexOfErrorMensaje)
          }
          val _tmpEsManual: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsManual).toInt()
          _tmpEsManual = _tmp != 0
          val _tmpMetadata: String?
          if (_stmt.isNull(_columnIndexOfMetadata)) {
            _tmpMetadata = null
          } else {
            _tmpMetadata = _stmt.getText(_columnIndexOfMetadata)
          }
          _result =
              BackupLogEntity(_tmpIdLog,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpTamanioBytes,_tmpRutaArchivo,_tmpErrorMensaje,_tmpEsManual,_tmpMetadata)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllKeyBackupLogs(): Flow<List<KeyBackupLogEntity>> {
    val _sql: String = "SELECT * FROM logs_backup_llaves ORDER BY fecha DESC"
    return createFlow(__db, false, arrayOf("logs_backup_llaves")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfIdLog: Int = getColumnIndexOrThrow(_stmt, "idLog")
        val _columnIndexOfAccion: Int = getColumnIndexOrThrow(_stmt, "accion")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _columnIndexOfUsuarioId: Int = getColumnIndexOrThrow(_stmt, "usuarioId")
        val _columnIndexOfExito: Int = getColumnIndexOrThrow(_stmt, "exito")
        val _columnIndexOfMensajeError: Int = getColumnIndexOrThrow(_stmt, "mensajeError")
        val _columnIndexOfMetadata: Int = getColumnIndexOrThrow(_stmt, "metadata")
        val _result: MutableList<KeyBackupLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: KeyBackupLogEntity
          val _tmpIdLog: String
          _tmpIdLog = _stmt.getText(_columnIndexOfIdLog)
          val _tmpAccion: String
          _tmpAccion = _stmt.getText(_columnIndexOfAccion)
          val _tmpFecha: Long
          _tmpFecha = _stmt.getLong(_columnIndexOfFecha)
          val _tmpUsuarioId: String
          _tmpUsuarioId = _stmt.getText(_columnIndexOfUsuarioId)
          val _tmpExito: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfExito).toInt()
          _tmpExito = _tmp != 0
          val _tmpMensajeError: String?
          if (_stmt.isNull(_columnIndexOfMensajeError)) {
            _tmpMensajeError = null
          } else {
            _tmpMensajeError = _stmt.getText(_columnIndexOfMensajeError)
          }
          val _tmpMetadata: String?
          if (_stmt.isNull(_columnIndexOfMetadata)) {
            _tmpMetadata = null
          } else {
            _tmpMetadata = _stmt.getText(_columnIndexOfMetadata)
          }
          _item =
              KeyBackupLogEntity(_tmpIdLog,_tmpAccion,_tmpFecha,_tmpUsuarioId,_tmpExito,_tmpMensajeError,_tmpMetadata)
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

package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AlertaInasistenciaEntity
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
public class AlertaInasistenciaDao_Impl(
  __db: RoomDatabase,
) : AlertaInasistenciaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAlertaInasistenciaEntity:
      EntityInsertAdapter<AlertaInasistenciaEntity>

  private val __updateAdapterOfAlertaInasistenciaEntity:
      EntityDeleteOrUpdateAdapter<AlertaInasistenciaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAlertaInasistenciaEntity = object :
        EntityInsertAdapter<AlertaInasistenciaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `alertas_inasistencia` (`id`,`institutionId`,`estudianteId`,`acudienteId`,`directorCursoId`,`jefeAreaId`,`coordinadorId`,`inasistenciasConsecutivas`,`diasSemana`,`semanaInicio`,`semanaFin`,`nivelAlerta`,`estado`,`fechaAlerta`,`fechaResolucion`,`observaciones`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AlertaInasistenciaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.estudianteId)
        statement.bindText(4, entity.acudienteId)
        val _tmpDirectorCursoId: String? = entity.directorCursoId
        if (_tmpDirectorCursoId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDirectorCursoId)
        }
        val _tmpJefeAreaId: String? = entity.jefeAreaId
        if (_tmpJefeAreaId == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpJefeAreaId)
        }
        val _tmpCoordinadorId: String? = entity.coordinadorId
        if (_tmpCoordinadorId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCoordinadorId)
        }
        statement.bindLong(8, entity.inasistenciasConsecutivas.toLong())
        statement.bindLong(9, entity.diasSemana.toLong())
        statement.bindLong(10, entity.semanaInicio)
        statement.bindLong(11, entity.semanaFin)
        statement.bindLong(12, entity.nivelAlerta.toLong())
        statement.bindText(13, entity.estado)
        statement.bindLong(14, entity.fechaAlerta)
        val _tmpFechaResolucion: Long? = entity.fechaResolucion
        if (_tmpFechaResolucion == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpFechaResolucion)
        }
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpObservaciones)
        }
        statement.bindLong(17, entity.syncStatus.toLong())
        statement.bindLong(18, entity.lastModified)
      }
    }
    this.__updateAdapterOfAlertaInasistenciaEntity = object :
        EntityDeleteOrUpdateAdapter<AlertaInasistenciaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `alertas_inasistencia` SET `id` = ?,`institutionId` = ?,`estudianteId` = ?,`acudienteId` = ?,`directorCursoId` = ?,`jefeAreaId` = ?,`coordinadorId` = ?,`inasistenciasConsecutivas` = ?,`diasSemana` = ?,`semanaInicio` = ?,`semanaFin` = ?,`nivelAlerta` = ?,`estado` = ?,`fechaAlerta` = ?,`fechaResolucion` = ?,`observaciones` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AlertaInasistenciaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.estudianteId)
        statement.bindText(4, entity.acudienteId)
        val _tmpDirectorCursoId: String? = entity.directorCursoId
        if (_tmpDirectorCursoId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDirectorCursoId)
        }
        val _tmpJefeAreaId: String? = entity.jefeAreaId
        if (_tmpJefeAreaId == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpJefeAreaId)
        }
        val _tmpCoordinadorId: String? = entity.coordinadorId
        if (_tmpCoordinadorId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCoordinadorId)
        }
        statement.bindLong(8, entity.inasistenciasConsecutivas.toLong())
        statement.bindLong(9, entity.diasSemana.toLong())
        statement.bindLong(10, entity.semanaInicio)
        statement.bindLong(11, entity.semanaFin)
        statement.bindLong(12, entity.nivelAlerta.toLong())
        statement.bindText(13, entity.estado)
        statement.bindLong(14, entity.fechaAlerta)
        val _tmpFechaResolucion: Long? = entity.fechaResolucion
        if (_tmpFechaResolucion == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpFechaResolucion)
        }
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpObservaciones)
        }
        statement.bindLong(17, entity.syncStatus.toLong())
        statement.bindLong(18, entity.lastModified)
        statement.bindLong(19, entity.id)
      }
    }
  }

  public override suspend fun insert(alerta: AlertaInasistenciaEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfAlertaInasistenciaEntity.insertAndReturnId(_connection,
        alerta)
    _result
  }

  public override suspend fun update(alerta: AlertaInasistenciaEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfAlertaInasistenciaEntity.handle(_connection, alerta)
  }

  public override fun getActivas(instId: String): Flow<List<AlertaInasistenciaEntity>> {
    val _sql: String =
        "SELECT * FROM alertas_inasistencia WHERE institutionId = ? AND estado = 'ACTIVA' ORDER BY fechaAlerta DESC"
    return createFlow(__db, false, arrayOf("alertas_inasistencia")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfAcudienteId: Int = getColumnIndexOrThrow(_stmt, "acudienteId")
        val _columnIndexOfDirectorCursoId: Int = getColumnIndexOrThrow(_stmt, "directorCursoId")
        val _columnIndexOfJefeAreaId: Int = getColumnIndexOrThrow(_stmt, "jefeAreaId")
        val _columnIndexOfCoordinadorId: Int = getColumnIndexOrThrow(_stmt, "coordinadorId")
        val _columnIndexOfInasistenciasConsecutivas: Int = getColumnIndexOrThrow(_stmt,
            "inasistenciasConsecutivas")
        val _columnIndexOfDiasSemana: Int = getColumnIndexOrThrow(_stmt, "diasSemana")
        val _columnIndexOfSemanaInicio: Int = getColumnIndexOrThrow(_stmt, "semanaInicio")
        val _columnIndexOfSemanaFin: Int = getColumnIndexOrThrow(_stmt, "semanaFin")
        val _columnIndexOfNivelAlerta: Int = getColumnIndexOrThrow(_stmt, "nivelAlerta")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfFechaAlerta: Int = getColumnIndexOrThrow(_stmt, "fechaAlerta")
        val _columnIndexOfFechaResolucion: Int = getColumnIndexOrThrow(_stmt, "fechaResolucion")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AlertaInasistenciaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AlertaInasistenciaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpAcudienteId: String
          _tmpAcudienteId = _stmt.getText(_columnIndexOfAcudienteId)
          val _tmpDirectorCursoId: String?
          if (_stmt.isNull(_columnIndexOfDirectorCursoId)) {
            _tmpDirectorCursoId = null
          } else {
            _tmpDirectorCursoId = _stmt.getText(_columnIndexOfDirectorCursoId)
          }
          val _tmpJefeAreaId: String?
          if (_stmt.isNull(_columnIndexOfJefeAreaId)) {
            _tmpJefeAreaId = null
          } else {
            _tmpJefeAreaId = _stmt.getText(_columnIndexOfJefeAreaId)
          }
          val _tmpCoordinadorId: String?
          if (_stmt.isNull(_columnIndexOfCoordinadorId)) {
            _tmpCoordinadorId = null
          } else {
            _tmpCoordinadorId = _stmt.getText(_columnIndexOfCoordinadorId)
          }
          val _tmpInasistenciasConsecutivas: Int
          _tmpInasistenciasConsecutivas =
              _stmt.getLong(_columnIndexOfInasistenciasConsecutivas).toInt()
          val _tmpDiasSemana: Int
          _tmpDiasSemana = _stmt.getLong(_columnIndexOfDiasSemana).toInt()
          val _tmpSemanaInicio: Long
          _tmpSemanaInicio = _stmt.getLong(_columnIndexOfSemanaInicio)
          val _tmpSemanaFin: Long
          _tmpSemanaFin = _stmt.getLong(_columnIndexOfSemanaFin)
          val _tmpNivelAlerta: Int
          _tmpNivelAlerta = _stmt.getLong(_columnIndexOfNivelAlerta).toInt()
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpFechaAlerta: Long
          _tmpFechaAlerta = _stmt.getLong(_columnIndexOfFechaAlerta)
          val _tmpFechaResolucion: Long?
          if (_stmt.isNull(_columnIndexOfFechaResolucion)) {
            _tmpFechaResolucion = null
          } else {
            _tmpFechaResolucion = _stmt.getLong(_columnIndexOfFechaResolucion)
          }
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AlertaInasistenciaEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpAcudienteId,_tmpDirectorCursoId,_tmpJefeAreaId,_tmpCoordinadorId,_tmpInasistenciasConsecutivas,_tmpDiasSemana,_tmpSemanaInicio,_tmpSemanaFin,_tmpNivelAlerta,_tmpEstado,_tmpFechaAlerta,_tmpFechaResolucion,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByEstudiante(estudianteId: String, instId: String):
      Flow<List<AlertaInasistenciaEntity>> {
    val _sql: String =
        "SELECT * FROM alertas_inasistencia WHERE estudianteId = ? AND institutionId = ? ORDER BY fechaAlerta DESC"
    return createFlow(__db, false, arrayOf("alertas_inasistencia")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, estudianteId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfAcudienteId: Int = getColumnIndexOrThrow(_stmt, "acudienteId")
        val _columnIndexOfDirectorCursoId: Int = getColumnIndexOrThrow(_stmt, "directorCursoId")
        val _columnIndexOfJefeAreaId: Int = getColumnIndexOrThrow(_stmt, "jefeAreaId")
        val _columnIndexOfCoordinadorId: Int = getColumnIndexOrThrow(_stmt, "coordinadorId")
        val _columnIndexOfInasistenciasConsecutivas: Int = getColumnIndexOrThrow(_stmt,
            "inasistenciasConsecutivas")
        val _columnIndexOfDiasSemana: Int = getColumnIndexOrThrow(_stmt, "diasSemana")
        val _columnIndexOfSemanaInicio: Int = getColumnIndexOrThrow(_stmt, "semanaInicio")
        val _columnIndexOfSemanaFin: Int = getColumnIndexOrThrow(_stmt, "semanaFin")
        val _columnIndexOfNivelAlerta: Int = getColumnIndexOrThrow(_stmt, "nivelAlerta")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfFechaAlerta: Int = getColumnIndexOrThrow(_stmt, "fechaAlerta")
        val _columnIndexOfFechaResolucion: Int = getColumnIndexOrThrow(_stmt, "fechaResolucion")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AlertaInasistenciaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AlertaInasistenciaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpAcudienteId: String
          _tmpAcudienteId = _stmt.getText(_columnIndexOfAcudienteId)
          val _tmpDirectorCursoId: String?
          if (_stmt.isNull(_columnIndexOfDirectorCursoId)) {
            _tmpDirectorCursoId = null
          } else {
            _tmpDirectorCursoId = _stmt.getText(_columnIndexOfDirectorCursoId)
          }
          val _tmpJefeAreaId: String?
          if (_stmt.isNull(_columnIndexOfJefeAreaId)) {
            _tmpJefeAreaId = null
          } else {
            _tmpJefeAreaId = _stmt.getText(_columnIndexOfJefeAreaId)
          }
          val _tmpCoordinadorId: String?
          if (_stmt.isNull(_columnIndexOfCoordinadorId)) {
            _tmpCoordinadorId = null
          } else {
            _tmpCoordinadorId = _stmt.getText(_columnIndexOfCoordinadorId)
          }
          val _tmpInasistenciasConsecutivas: Int
          _tmpInasistenciasConsecutivas =
              _stmt.getLong(_columnIndexOfInasistenciasConsecutivas).toInt()
          val _tmpDiasSemana: Int
          _tmpDiasSemana = _stmt.getLong(_columnIndexOfDiasSemana).toInt()
          val _tmpSemanaInicio: Long
          _tmpSemanaInicio = _stmt.getLong(_columnIndexOfSemanaInicio)
          val _tmpSemanaFin: Long
          _tmpSemanaFin = _stmt.getLong(_columnIndexOfSemanaFin)
          val _tmpNivelAlerta: Int
          _tmpNivelAlerta = _stmt.getLong(_columnIndexOfNivelAlerta).toInt()
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpFechaAlerta: Long
          _tmpFechaAlerta = _stmt.getLong(_columnIndexOfFechaAlerta)
          val _tmpFechaResolucion: Long?
          if (_stmt.isNull(_columnIndexOfFechaResolucion)) {
            _tmpFechaResolucion = null
          } else {
            _tmpFechaResolucion = _stmt.getLong(_columnIndexOfFechaResolucion)
          }
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AlertaInasistenciaEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpAcudienteId,_tmpDirectorCursoId,_tmpJefeAreaId,_tmpCoordinadorId,_tmpInasistenciasConsecutivas,_tmpDiasSemana,_tmpSemanaInicio,_tmpSemanaFin,_tmpNivelAlerta,_tmpEstado,_tmpFechaAlerta,_tmpFechaResolucion,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<AlertaInasistenciaEntity> {
    val _sql: String =
        "SELECT * FROM alertas_inasistencia WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfAcudienteId: Int = getColumnIndexOrThrow(_stmt, "acudienteId")
        val _columnIndexOfDirectorCursoId: Int = getColumnIndexOrThrow(_stmt, "directorCursoId")
        val _columnIndexOfJefeAreaId: Int = getColumnIndexOrThrow(_stmt, "jefeAreaId")
        val _columnIndexOfCoordinadorId: Int = getColumnIndexOrThrow(_stmt, "coordinadorId")
        val _columnIndexOfInasistenciasConsecutivas: Int = getColumnIndexOrThrow(_stmt,
            "inasistenciasConsecutivas")
        val _columnIndexOfDiasSemana: Int = getColumnIndexOrThrow(_stmt, "diasSemana")
        val _columnIndexOfSemanaInicio: Int = getColumnIndexOrThrow(_stmt, "semanaInicio")
        val _columnIndexOfSemanaFin: Int = getColumnIndexOrThrow(_stmt, "semanaFin")
        val _columnIndexOfNivelAlerta: Int = getColumnIndexOrThrow(_stmt, "nivelAlerta")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfFechaAlerta: Int = getColumnIndexOrThrow(_stmt, "fechaAlerta")
        val _columnIndexOfFechaResolucion: Int = getColumnIndexOrThrow(_stmt, "fechaResolucion")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AlertaInasistenciaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AlertaInasistenciaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpAcudienteId: String
          _tmpAcudienteId = _stmt.getText(_columnIndexOfAcudienteId)
          val _tmpDirectorCursoId: String?
          if (_stmt.isNull(_columnIndexOfDirectorCursoId)) {
            _tmpDirectorCursoId = null
          } else {
            _tmpDirectorCursoId = _stmt.getText(_columnIndexOfDirectorCursoId)
          }
          val _tmpJefeAreaId: String?
          if (_stmt.isNull(_columnIndexOfJefeAreaId)) {
            _tmpJefeAreaId = null
          } else {
            _tmpJefeAreaId = _stmt.getText(_columnIndexOfJefeAreaId)
          }
          val _tmpCoordinadorId: String?
          if (_stmt.isNull(_columnIndexOfCoordinadorId)) {
            _tmpCoordinadorId = null
          } else {
            _tmpCoordinadorId = _stmt.getText(_columnIndexOfCoordinadorId)
          }
          val _tmpInasistenciasConsecutivas: Int
          _tmpInasistenciasConsecutivas =
              _stmt.getLong(_columnIndexOfInasistenciasConsecutivas).toInt()
          val _tmpDiasSemana: Int
          _tmpDiasSemana = _stmt.getLong(_columnIndexOfDiasSemana).toInt()
          val _tmpSemanaInicio: Long
          _tmpSemanaInicio = _stmt.getLong(_columnIndexOfSemanaInicio)
          val _tmpSemanaFin: Long
          _tmpSemanaFin = _stmt.getLong(_columnIndexOfSemanaFin)
          val _tmpNivelAlerta: Int
          _tmpNivelAlerta = _stmt.getLong(_columnIndexOfNivelAlerta).toInt()
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpFechaAlerta: Long
          _tmpFechaAlerta = _stmt.getLong(_columnIndexOfFechaAlerta)
          val _tmpFechaResolucion: Long?
          if (_stmt.isNull(_columnIndexOfFechaResolucion)) {
            _tmpFechaResolucion = null
          } else {
            _tmpFechaResolucion = _stmt.getLong(_columnIndexOfFechaResolucion)
          }
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AlertaInasistenciaEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpAcudienteId,_tmpDirectorCursoId,_tmpJefeAreaId,_tmpCoordinadorId,_tmpInasistenciasConsecutivas,_tmpDiasSemana,_tmpSemanaInicio,_tmpSemanaFin,_tmpNivelAlerta,_tmpEstado,_tmpFechaAlerta,_tmpFechaResolucion,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun resolverAlerta(
    id: Long,
    fechaResolucion: Long,
    instId: String,
  ) {
    val _sql: String =
        "UPDATE alertas_inasistencia SET estado = 'RESUELTA', fechaResolucion = ? WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fechaResolucion)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _argIndex = 3
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String =
        "UPDATE alertas_inasistencia SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

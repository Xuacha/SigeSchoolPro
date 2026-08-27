package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AlertaTempranaEntity
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
public class AlertaTempranaDao_Impl(
  __db: RoomDatabase,
) : AlertaTempranaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAlertaTempranaEntity: EntityInsertAdapter<AlertaTempranaEntity>

  private val __updateAdapterOfAlertaTempranaEntity:
      EntityDeleteOrUpdateAdapter<AlertaTempranaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAlertaTempranaEntity = object :
        EntityInsertAdapter<AlertaTempranaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `alertas_tempranas` (`id`,`institutionId`,`studentId`,`tipo`,`nivel`,`descripcion`,`fechaDeteccion`,`estado`,`atendidaPor`,`fechaAtencion`,`observaciones`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AlertaTempranaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.tipo)
        statement.bindText(5, entity.nivel)
        statement.bindText(6, entity.descripcion)
        statement.bindLong(7, entity.fechaDeteccion)
        statement.bindText(8, entity.estado)
        val _tmpAtendidaPor: String? = entity.atendidaPor
        if (_tmpAtendidaPor == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpAtendidaPor)
        }
        val _tmpFechaAtencion: Long? = entity.fechaAtencion
        if (_tmpFechaAtencion == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpFechaAtencion)
        }
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpObservaciones)
        }
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
      }
    }
    this.__updateAdapterOfAlertaTempranaEntity = object :
        EntityDeleteOrUpdateAdapter<AlertaTempranaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `alertas_tempranas` SET `id` = ?,`institutionId` = ?,`studentId` = ?,`tipo` = ?,`nivel` = ?,`descripcion` = ?,`fechaDeteccion` = ?,`estado` = ?,`atendidaPor` = ?,`fechaAtencion` = ?,`observaciones` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AlertaTempranaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.tipo)
        statement.bindText(5, entity.nivel)
        statement.bindText(6, entity.descripcion)
        statement.bindLong(7, entity.fechaDeteccion)
        statement.bindText(8, entity.estado)
        val _tmpAtendidaPor: String? = entity.atendidaPor
        if (_tmpAtendidaPor == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpAtendidaPor)
        }
        val _tmpFechaAtencion: Long? = entity.fechaAtencion
        if (_tmpFechaAtencion == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpFechaAtencion)
        }
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpObservaciones)
        }
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
        statement.bindLong(14, entity.id)
      }
    }
  }

  public override suspend fun insert(alerta: AlertaTempranaEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfAlertaTempranaEntity.insertAndReturnId(_connection, alerta)
    _result
  }

  public override suspend fun update(alerta: AlertaTempranaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfAlertaTempranaEntity.handle(_connection, alerta)
  }

  public override fun getActivas(instId: String): Flow<List<AlertaTempranaEntity>> {
    val _sql: String =
        "SELECT * FROM alertas_tempranas WHERE institutionId = ? AND estado = 'ACTIVA' ORDER BY fechaDeteccion DESC"
    return createFlow(__db, false, arrayOf("alertas_tempranas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfNivel: Int = getColumnIndexOrThrow(_stmt, "nivel")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfFechaDeteccion: Int = getColumnIndexOrThrow(_stmt, "fechaDeteccion")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfAtendidaPor: Int = getColumnIndexOrThrow(_stmt, "atendidaPor")
        val _columnIndexOfFechaAtencion: Int = getColumnIndexOrThrow(_stmt, "fechaAtencion")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AlertaTempranaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AlertaTempranaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpNivel: String
          _tmpNivel = _stmt.getText(_columnIndexOfNivel)
          val _tmpDescripcion: String
          _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          val _tmpFechaDeteccion: Long
          _tmpFechaDeteccion = _stmt.getLong(_columnIndexOfFechaDeteccion)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpAtendidaPor: String?
          if (_stmt.isNull(_columnIndexOfAtendidaPor)) {
            _tmpAtendidaPor = null
          } else {
            _tmpAtendidaPor = _stmt.getText(_columnIndexOfAtendidaPor)
          }
          val _tmpFechaAtencion: Long?
          if (_stmt.isNull(_columnIndexOfFechaAtencion)) {
            _tmpFechaAtencion = null
          } else {
            _tmpFechaAtencion = _stmt.getLong(_columnIndexOfFechaAtencion)
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
              AlertaTempranaEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTipo,_tmpNivel,_tmpDescripcion,_tmpFechaDeteccion,_tmpEstado,_tmpAtendidaPor,_tmpFechaAtencion,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getActivaByStudentAndTipo(studentId: String, tipo: String):
      AlertaTempranaEntity? {
    val _sql: String =
        "SELECT * FROM alertas_tempranas WHERE studentId = ? AND tipo = ? AND estado = 'ACTIVA' LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, tipo)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfNivel: Int = getColumnIndexOrThrow(_stmt, "nivel")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfFechaDeteccion: Int = getColumnIndexOrThrow(_stmt, "fechaDeteccion")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfAtendidaPor: Int = getColumnIndexOrThrow(_stmt, "atendidaPor")
        val _columnIndexOfFechaAtencion: Int = getColumnIndexOrThrow(_stmt, "fechaAtencion")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: AlertaTempranaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpNivel: String
          _tmpNivel = _stmt.getText(_columnIndexOfNivel)
          val _tmpDescripcion: String
          _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          val _tmpFechaDeteccion: Long
          _tmpFechaDeteccion = _stmt.getLong(_columnIndexOfFechaDeteccion)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpAtendidaPor: String?
          if (_stmt.isNull(_columnIndexOfAtendidaPor)) {
            _tmpAtendidaPor = null
          } else {
            _tmpAtendidaPor = _stmt.getText(_columnIndexOfAtendidaPor)
          }
          val _tmpFechaAtencion: Long?
          if (_stmt.isNull(_columnIndexOfFechaAtencion)) {
            _tmpFechaAtencion = null
          } else {
            _tmpFechaAtencion = _stmt.getLong(_columnIndexOfFechaAtencion)
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
          _result =
              AlertaTempranaEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTipo,_tmpNivel,_tmpDescripcion,_tmpFechaDeteccion,_tmpEstado,_tmpAtendidaPor,_tmpFechaAtencion,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<AlertaTempranaEntity> {
    val _sql: String = "SELECT * FROM alertas_tempranas WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfNivel: Int = getColumnIndexOrThrow(_stmt, "nivel")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfFechaDeteccion: Int = getColumnIndexOrThrow(_stmt, "fechaDeteccion")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfAtendidaPor: Int = getColumnIndexOrThrow(_stmt, "atendidaPor")
        val _columnIndexOfFechaAtencion: Int = getColumnIndexOrThrow(_stmt, "fechaAtencion")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AlertaTempranaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AlertaTempranaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpNivel: String
          _tmpNivel = _stmt.getText(_columnIndexOfNivel)
          val _tmpDescripcion: String
          _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          val _tmpFechaDeteccion: Long
          _tmpFechaDeteccion = _stmt.getLong(_columnIndexOfFechaDeteccion)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpAtendidaPor: String?
          if (_stmt.isNull(_columnIndexOfAtendidaPor)) {
            _tmpAtendidaPor = null
          } else {
            _tmpAtendidaPor = _stmt.getText(_columnIndexOfAtendidaPor)
          }
          val _tmpFechaAtencion: Long?
          if (_stmt.isNull(_columnIndexOfFechaAtencion)) {
            _tmpFechaAtencion = null
          } else {
            _tmpFechaAtencion = _stmt.getLong(_columnIndexOfFechaAtencion)
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
              AlertaTempranaEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTipo,_tmpNivel,_tmpDescripcion,_tmpFechaDeteccion,_tmpEstado,_tmpAtendidaPor,_tmpFechaAtencion,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE alertas_tempranas SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

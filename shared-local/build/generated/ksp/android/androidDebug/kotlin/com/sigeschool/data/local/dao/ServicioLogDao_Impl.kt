package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ServicioLogEntity
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
public class ServicioLogDao_Impl(
  __db: RoomDatabase,
) : ServicioLogDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfServicioLogEntity: EntityInsertAdapter<ServicioLogEntity>

  private val __updateAdapterOfServicioLogEntity: EntityDeleteOrUpdateAdapter<ServicioLogEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfServicioLogEntity = object : EntityInsertAdapter<ServicioLogEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `servicio_logs` (`id`,`institutionId`,`studentId`,`servicioId`,`docenteId`,`fechaHoraSalida`,`fechaHoraLlegada`,`fechaHoraRegreso`,`motivo`,`estado`,`notificadoAcudiente`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ServicioLogEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindLong(4, entity.servicioId)
        val _tmpDocenteId: String? = entity.docenteId
        if (_tmpDocenteId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocenteId)
        }
        statement.bindLong(6, entity.fechaHoraSalida)
        val _tmpFechaHoraLlegada: Long? = entity.fechaHoraLlegada
        if (_tmpFechaHoraLlegada == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpFechaHoraLlegada)
        }
        val _tmpFechaHoraRegreso: Long? = entity.fechaHoraRegreso
        if (_tmpFechaHoraRegreso == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpFechaHoraRegreso)
        }
        val _tmpMotivo: String? = entity.motivo
        if (_tmpMotivo == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpMotivo)
        }
        statement.bindText(10, entity.estado)
        val _tmp: Int = if (entity.notificadoAcudiente) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
      }
    }
    this.__updateAdapterOfServicioLogEntity = object :
        EntityDeleteOrUpdateAdapter<ServicioLogEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `servicio_logs` SET `id` = ?,`institutionId` = ?,`studentId` = ?,`servicioId` = ?,`docenteId` = ?,`fechaHoraSalida` = ?,`fechaHoraLlegada` = ?,`fechaHoraRegreso` = ?,`motivo` = ?,`estado` = ?,`notificadoAcudiente` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ServicioLogEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindLong(4, entity.servicioId)
        val _tmpDocenteId: String? = entity.docenteId
        if (_tmpDocenteId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocenteId)
        }
        statement.bindLong(6, entity.fechaHoraSalida)
        val _tmpFechaHoraLlegada: Long? = entity.fechaHoraLlegada
        if (_tmpFechaHoraLlegada == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpFechaHoraLlegada)
        }
        val _tmpFechaHoraRegreso: Long? = entity.fechaHoraRegreso
        if (_tmpFechaHoraRegreso == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpFechaHoraRegreso)
        }
        val _tmpMotivo: String? = entity.motivo
        if (_tmpMotivo == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpMotivo)
        }
        statement.bindText(10, entity.estado)
        val _tmp: Int = if (entity.notificadoAcudiente) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
        statement.bindLong(14, entity.id)
      }
    }
  }

  public override suspend fun insert(log: ServicioLogEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfServicioLogEntity.insertAndReturnId(_connection, log)
    _result
  }

  public override suspend fun update(log: ServicioLogEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfServicioLogEntity.handle(_connection, log)
  }

  public override suspend fun getById(id: Long, instId: String): ServicioLogEntity? {
    val _sql: String = "SELECT * FROM servicio_logs WHERE id = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfServicioId: Int = getColumnIndexOrThrow(_stmt, "servicioId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaHoraSalida: Int = getColumnIndexOrThrow(_stmt, "fechaHoraSalida")
        val _columnIndexOfFechaHoraLlegada: Int = getColumnIndexOrThrow(_stmt, "fechaHoraLlegada")
        val _columnIndexOfFechaHoraRegreso: Int = getColumnIndexOrThrow(_stmt, "fechaHoraRegreso")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ServicioLogEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpServicioId: Long
          _tmpServicioId = _stmt.getLong(_columnIndexOfServicioId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaHoraSalida: Long
          _tmpFechaHoraSalida = _stmt.getLong(_columnIndexOfFechaHoraSalida)
          val _tmpFechaHoraLlegada: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraLlegada)) {
            _tmpFechaHoraLlegada = null
          } else {
            _tmpFechaHoraLlegada = _stmt.getLong(_columnIndexOfFechaHoraLlegada)
          }
          val _tmpFechaHoraRegreso: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraRegreso)) {
            _tmpFechaHoraRegreso = null
          } else {
            _tmpFechaHoraRegreso = _stmt.getLong(_columnIndexOfFechaHoraRegreso)
          }
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ServicioLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpServicioId,_tmpDocenteId,_tmpFechaHoraSalida,_tmpFechaHoraLlegada,_tmpFechaHoraRegreso,_tmpMotivo,_tmpEstado,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByStudent(studentId: String, instId: String):
      Flow<List<ServicioLogEntity>> {
    val _sql: String =
        "SELECT * FROM servicio_logs WHERE studentId = ? AND institutionId = ? ORDER BY fechaHoraSalida DESC"
    return createFlow(__db, false, arrayOf("servicio_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfServicioId: Int = getColumnIndexOrThrow(_stmt, "servicioId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaHoraSalida: Int = getColumnIndexOrThrow(_stmt, "fechaHoraSalida")
        val _columnIndexOfFechaHoraLlegada: Int = getColumnIndexOrThrow(_stmt, "fechaHoraLlegada")
        val _columnIndexOfFechaHoraRegreso: Int = getColumnIndexOrThrow(_stmt, "fechaHoraRegreso")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ServicioLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ServicioLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpServicioId: Long
          _tmpServicioId = _stmt.getLong(_columnIndexOfServicioId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaHoraSalida: Long
          _tmpFechaHoraSalida = _stmt.getLong(_columnIndexOfFechaHoraSalida)
          val _tmpFechaHoraLlegada: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraLlegada)) {
            _tmpFechaHoraLlegada = null
          } else {
            _tmpFechaHoraLlegada = _stmt.getLong(_columnIndexOfFechaHoraLlegada)
          }
          val _tmpFechaHoraRegreso: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraRegreso)) {
            _tmpFechaHoraRegreso = null
          } else {
            _tmpFechaHoraRegreso = _stmt.getLong(_columnIndexOfFechaHoraRegreso)
          }
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ServicioLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpServicioId,_tmpDocenteId,_tmpFechaHoraSalida,_tmpFechaHoraLlegada,_tmpFechaHoraRegreso,_tmpMotivo,_tmpEstado,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByServicio(servicioId: Long, instId: String):
      Flow<List<ServicioLogEntity>> {
    val _sql: String =
        "SELECT * FROM servicio_logs WHERE servicioId = ? AND institutionId = ? ORDER BY fechaHoraSalida DESC"
    return createFlow(__db, false, arrayOf("servicio_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, servicioId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfServicioId: Int = getColumnIndexOrThrow(_stmt, "servicioId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaHoraSalida: Int = getColumnIndexOrThrow(_stmt, "fechaHoraSalida")
        val _columnIndexOfFechaHoraLlegada: Int = getColumnIndexOrThrow(_stmt, "fechaHoraLlegada")
        val _columnIndexOfFechaHoraRegreso: Int = getColumnIndexOrThrow(_stmt, "fechaHoraRegreso")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ServicioLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ServicioLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpServicioId: Long
          _tmpServicioId = _stmt.getLong(_columnIndexOfServicioId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaHoraSalida: Long
          _tmpFechaHoraSalida = _stmt.getLong(_columnIndexOfFechaHoraSalida)
          val _tmpFechaHoraLlegada: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraLlegada)) {
            _tmpFechaHoraLlegada = null
          } else {
            _tmpFechaHoraLlegada = _stmt.getLong(_columnIndexOfFechaHoraLlegada)
          }
          val _tmpFechaHoraRegreso: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraRegreso)) {
            _tmpFechaHoraRegreso = null
          } else {
            _tmpFechaHoraRegreso = _stmt.getLong(_columnIndexOfFechaHoraRegreso)
          }
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ServicioLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpServicioId,_tmpDocenteId,_tmpFechaHoraSalida,_tmpFechaHoraLlegada,_tmpFechaHoraRegreso,_tmpMotivo,_tmpEstado,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getEnCurso(instId: String): Flow<List<ServicioLogEntity>> {
    val _sql: String = "SELECT * FROM servicio_logs WHERE institutionId = ? AND estado = 'EN_CURSO'"
    return createFlow(__db, false, arrayOf("servicio_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfServicioId: Int = getColumnIndexOrThrow(_stmt, "servicioId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaHoraSalida: Int = getColumnIndexOrThrow(_stmt, "fechaHoraSalida")
        val _columnIndexOfFechaHoraLlegada: Int = getColumnIndexOrThrow(_stmt, "fechaHoraLlegada")
        val _columnIndexOfFechaHoraRegreso: Int = getColumnIndexOrThrow(_stmt, "fechaHoraRegreso")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ServicioLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ServicioLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpServicioId: Long
          _tmpServicioId = _stmt.getLong(_columnIndexOfServicioId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaHoraSalida: Long
          _tmpFechaHoraSalida = _stmt.getLong(_columnIndexOfFechaHoraSalida)
          val _tmpFechaHoraLlegada: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraLlegada)) {
            _tmpFechaHoraLlegada = null
          } else {
            _tmpFechaHoraLlegada = _stmt.getLong(_columnIndexOfFechaHoraLlegada)
          }
          val _tmpFechaHoraRegreso: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraRegreso)) {
            _tmpFechaHoraRegreso = null
          } else {
            _tmpFechaHoraRegreso = _stmt.getLong(_columnIndexOfFechaHoraRegreso)
          }
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ServicioLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpServicioId,_tmpDocenteId,_tmpFechaHoraSalida,_tmpFechaHoraLlegada,_tmpFechaHoraRegreso,_tmpMotivo,_tmpEstado,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEnCursoByStudent(studentId: String, instId: String):
      List<ServicioLogEntity> {
    val _sql: String =
        "SELECT * FROM servicio_logs WHERE studentId = ? AND institutionId = ? AND estado = 'EN_CURSO'"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfServicioId: Int = getColumnIndexOrThrow(_stmt, "servicioId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaHoraSalida: Int = getColumnIndexOrThrow(_stmt, "fechaHoraSalida")
        val _columnIndexOfFechaHoraLlegada: Int = getColumnIndexOrThrow(_stmt, "fechaHoraLlegada")
        val _columnIndexOfFechaHoraRegreso: Int = getColumnIndexOrThrow(_stmt, "fechaHoraRegreso")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ServicioLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ServicioLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpServicioId: Long
          _tmpServicioId = _stmt.getLong(_columnIndexOfServicioId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaHoraSalida: Long
          _tmpFechaHoraSalida = _stmt.getLong(_columnIndexOfFechaHoraSalida)
          val _tmpFechaHoraLlegada: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraLlegada)) {
            _tmpFechaHoraLlegada = null
          } else {
            _tmpFechaHoraLlegada = _stmt.getLong(_columnIndexOfFechaHoraLlegada)
          }
          val _tmpFechaHoraRegreso: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraRegreso)) {
            _tmpFechaHoraRegreso = null
          } else {
            _tmpFechaHoraRegreso = _stmt.getLong(_columnIndexOfFechaHoraRegreso)
          }
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ServicioLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpServicioId,_tmpDocenteId,_tmpFechaHoraSalida,_tmpFechaHoraLlegada,_tmpFechaHoraRegreso,_tmpMotivo,_tmpEstado,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<ServicioLogEntity> {
    val _sql: String = "SELECT * FROM servicio_logs WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfServicioId: Int = getColumnIndexOrThrow(_stmt, "servicioId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaHoraSalida: Int = getColumnIndexOrThrow(_stmt, "fechaHoraSalida")
        val _columnIndexOfFechaHoraLlegada: Int = getColumnIndexOrThrow(_stmt, "fechaHoraLlegada")
        val _columnIndexOfFechaHoraRegreso: Int = getColumnIndexOrThrow(_stmt, "fechaHoraRegreso")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ServicioLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ServicioLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpServicioId: Long
          _tmpServicioId = _stmt.getLong(_columnIndexOfServicioId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaHoraSalida: Long
          _tmpFechaHoraSalida = _stmt.getLong(_columnIndexOfFechaHoraSalida)
          val _tmpFechaHoraLlegada: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraLlegada)) {
            _tmpFechaHoraLlegada = null
          } else {
            _tmpFechaHoraLlegada = _stmt.getLong(_columnIndexOfFechaHoraLlegada)
          }
          val _tmpFechaHoraRegreso: Long?
          if (_stmt.isNull(_columnIndexOfFechaHoraRegreso)) {
            _tmpFechaHoraRegreso = null
          } else {
            _tmpFechaHoraRegreso = _stmt.getLong(_columnIndexOfFechaHoraRegreso)
          }
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ServicioLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpServicioId,_tmpDocenteId,_tmpFechaHoraSalida,_tmpFechaHoraLlegada,_tmpFechaHoraRegreso,_tmpMotivo,_tmpEstado,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun completarLog(
    id: Long,
    timestamp: Long,
    instId: String,
  ) {
    val _sql: String =
        "UPDATE servicio_logs SET estado = 'COMPLETADO', fechaHoraRegreso = ? WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
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

  public override suspend fun registrarLlegada(
    id: Long,
    timestamp: Long,
    instId: String,
  ) {
    val _sql: String =
        "UPDATE servicio_logs SET fechaHoraLlegada = ? WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
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
    val _sql: String = "UPDATE servicio_logs SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

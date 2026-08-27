package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.CitaEntity
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
public class CitaDao_Impl(
  __db: RoomDatabase,
) : CitaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCitaEntity: EntityInsertAdapter<CitaEntity>

  private val __updateAdapterOfCitaEntity: EntityDeleteOrUpdateAdapter<CitaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCitaEntity = object : EntityInsertAdapter<CitaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `citas` (`id`,`institutionId`,`docenteId`,`acudienteId`,`estudianteId`,`fechaCita`,`estado`,`motivo`,`observaciones`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CitaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.docenteId)
        statement.bindText(4, entity.acudienteId)
        statement.bindText(5, entity.estudianteId)
        statement.bindLong(6, entity.fechaCita)
        statement.bindText(7, entity.estado)
        val _tmpMotivo: String? = entity.motivo
        if (_tmpMotivo == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpMotivo)
        }
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpObservaciones)
        }
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
      }
    }
    this.__updateAdapterOfCitaEntity = object : EntityDeleteOrUpdateAdapter<CitaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `citas` SET `id` = ?,`institutionId` = ?,`docenteId` = ?,`acudienteId` = ?,`estudianteId` = ?,`fechaCita` = ?,`estado` = ?,`motivo` = ?,`observaciones` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CitaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.docenteId)
        statement.bindText(4, entity.acudienteId)
        statement.bindText(5, entity.estudianteId)
        statement.bindLong(6, entity.fechaCita)
        statement.bindText(7, entity.estado)
        val _tmpMotivo: String? = entity.motivo
        if (_tmpMotivo == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpMotivo)
        }
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpObservaciones)
        }
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
        statement.bindLong(12, entity.id)
      }
    }
  }

  public override suspend fun insert(cita: CitaEntity): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfCitaEntity.insertAndReturnId(_connection, cita)
    _result
  }

  public override suspend fun update(cita: CitaEntity): Unit = performSuspending(__db, false, true)
      { _connection ->
    __updateAdapterOfCitaEntity.handle(_connection, cita)
  }

  public override suspend fun getById(id: Long, instId: String): CitaEntity? {
    val _sql: String = "SELECT * FROM citas WHERE institutionId = ? AND id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfAcudienteId: Int = getColumnIndexOrThrow(_stmt, "acudienteId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfFechaCita: Int = getColumnIndexOrThrow(_stmt, "fechaCita")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: CitaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpAcudienteId: String
          _tmpAcudienteId = _stmt.getText(_columnIndexOfAcudienteId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpFechaCita: Long
          _tmpFechaCita = _stmt.getLong(_columnIndexOfFechaCita)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
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
              CitaEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpAcudienteId,_tmpEstudianteId,_tmpFechaCita,_tmpEstado,_tmpMotivo,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByDocente(docenteId: String, instId: String): Flow<List<CitaEntity>> {
    val _sql: String =
        "SELECT * FROM citas WHERE docenteId = ? AND institutionId = ? ORDER BY fechaCita DESC"
    return createFlow(__db, false, arrayOf("citas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, docenteId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfAcudienteId: Int = getColumnIndexOrThrow(_stmt, "acudienteId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfFechaCita: Int = getColumnIndexOrThrow(_stmt, "fechaCita")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CitaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CitaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpAcudienteId: String
          _tmpAcudienteId = _stmt.getText(_columnIndexOfAcudienteId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpFechaCita: Long
          _tmpFechaCita = _stmt.getLong(_columnIndexOfFechaCita)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
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
              CitaEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpAcudienteId,_tmpEstudianteId,_tmpFechaCita,_tmpEstado,_tmpMotivo,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByAcudiente(acudienteId: String, instId: String): Flow<List<CitaEntity>> {
    val _sql: String =
        "SELECT * FROM citas WHERE acudienteId = ? AND institutionId = ? ORDER BY fechaCita DESC"
    return createFlow(__db, false, arrayOf("citas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, acudienteId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfAcudienteId: Int = getColumnIndexOrThrow(_stmt, "acudienteId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfFechaCita: Int = getColumnIndexOrThrow(_stmt, "fechaCita")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CitaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CitaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpAcudienteId: String
          _tmpAcudienteId = _stmt.getText(_columnIndexOfAcudienteId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpFechaCita: Long
          _tmpFechaCita = _stmt.getLong(_columnIndexOfFechaCita)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
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
              CitaEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpAcudienteId,_tmpEstudianteId,_tmpFechaCita,_tmpEstado,_tmpMotivo,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllByInstitution(instId: String): Flow<List<CitaEntity>> {
    val _sql: String = "SELECT * FROM citas WHERE institutionId = ? ORDER BY fechaCita DESC"
    return createFlow(__db, false, arrayOf("citas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfAcudienteId: Int = getColumnIndexOrThrow(_stmt, "acudienteId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfFechaCita: Int = getColumnIndexOrThrow(_stmt, "fechaCita")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CitaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CitaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpAcudienteId: String
          _tmpAcudienteId = _stmt.getText(_columnIndexOfAcudienteId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpFechaCita: Long
          _tmpFechaCita = _stmt.getLong(_columnIndexOfFechaCita)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMotivo: String?
          if (_stmt.isNull(_columnIndexOfMotivo)) {
            _tmpMotivo = null
          } else {
            _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
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
              CitaEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpAcudienteId,_tmpEstudianteId,_tmpFechaCita,_tmpEstado,_tmpMotivo,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
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

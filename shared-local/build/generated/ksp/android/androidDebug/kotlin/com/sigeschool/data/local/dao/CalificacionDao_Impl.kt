package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.CalificacionEntity
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CalificacionDao_Impl(
  __db: RoomDatabase,
) : CalificacionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCalificacionEntity: EntityInsertAdapter<CalificacionEntity>

  private val __updateAdapterOfCalificacionEntity: EntityDeleteOrUpdateAdapter<CalificacionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCalificacionEntity = object : EntityInsertAdapter<CalificacionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_calificaciones` (`id`,`institutionId`,`estudianteId`,`claseId`,`periodoAcademicoId`,`corte`,`nota`,`observacion`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CalificacionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.estudianteId)
        statement.bindLong(4, entity.claseId)
        statement.bindLong(5, entity.periodoAcademicoId)
        statement.bindLong(6, entity.corte.toLong())
        statement.bindDouble(7, entity.nota)
        val _tmpObservacion: String? = entity.observacion
        if (_tmpObservacion == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpObservacion)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfCalificacionEntity = object :
        EntityDeleteOrUpdateAdapter<CalificacionEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_calificaciones` SET `id` = ?,`institutionId` = ?,`estudianteId` = ?,`claseId` = ?,`periodoAcademicoId` = ?,`corte` = ?,`nota` = ?,`observacion` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CalificacionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.estudianteId)
        statement.bindLong(4, entity.claseId)
        statement.bindLong(5, entity.periodoAcademicoId)
        statement.bindLong(6, entity.corte.toLong())
        statement.bindDouble(7, entity.nota)
        val _tmpObservacion: String? = entity.observacion
        if (_tmpObservacion == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpObservacion)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindText(11, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: CalificacionEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfCalificacionEntity.insert(_connection, entity)
  }

  public override suspend fun insertAll(entities: List<CalificacionEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCalificacionEntity.insert(_connection, entities)
  }

  public override suspend fun update(entity: CalificacionEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfCalificacionEntity.handle(_connection, entity)
  }

  public override fun getByClase(instId: String, claseId: Long): Flow<List<CalificacionEntity>> {
    val _sql: String =
        "SELECT * FROM academic_calificaciones WHERE institutionId = ? AND claseId = ?"
    return createFlow(__db, false, arrayOf("academic_calificaciones")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, claseId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfCorte: Int = getColumnIndexOrThrow(_stmt, "corte")
        val _columnIndexOfNota: Int = getColumnIndexOrThrow(_stmt, "nota")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CalificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CalificacionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpCorte: Int
          _tmpCorte = _stmt.getLong(_columnIndexOfCorte).toInt()
          val _tmpNota: Double
          _tmpNota = _stmt.getDouble(_columnIndexOfNota)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CalificacionEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpClaseId,_tmpPeriodoAcademicoId,_tmpCorte,_tmpNota,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByEstudiante(instId: String, estudianteId: String):
      Flow<List<CalificacionEntity>> {
    val _sql: String =
        "SELECT * FROM academic_calificaciones WHERE institutionId = ? AND estudianteId = ?"
    return createFlow(__db, false, arrayOf("academic_calificaciones")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, estudianteId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfCorte: Int = getColumnIndexOrThrow(_stmt, "corte")
        val _columnIndexOfNota: Int = getColumnIndexOrThrow(_stmt, "nota")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CalificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CalificacionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpCorte: Int
          _tmpCorte = _stmt.getLong(_columnIndexOfCorte).toInt()
          val _tmpNota: Double
          _tmpNota = _stmt.getDouble(_columnIndexOfNota)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CalificacionEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpClaseId,_tmpPeriodoAcademicoId,_tmpCorte,_tmpNota,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByEstudianteSync(estudianteId: String, instId: String):
      List<CalificacionEntity> {
    val _sql: String =
        "SELECT * FROM academic_calificaciones WHERE institutionId = ? AND estudianteId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, estudianteId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfCorte: Int = getColumnIndexOrThrow(_stmt, "corte")
        val _columnIndexOfNota: Int = getColumnIndexOrThrow(_stmt, "nota")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CalificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CalificacionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpCorte: Int
          _tmpCorte = _stmt.getLong(_columnIndexOfCorte).toInt()
          val _tmpNota: Double
          _tmpNota = _stmt.getDouble(_columnIndexOfNota)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CalificacionEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpClaseId,_tmpPeriodoAcademicoId,_tmpCorte,_tmpNota,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSpecific(
    instId: String,
    estudianteId: String,
    claseId: Long,
    periodoId: Long,
    corte: Int,
  ): CalificacionEntity? {
    val _sql: String = """
        |
        |        SELECT * FROM academic_calificaciones 
        |        WHERE institutionId = ? 
        |        AND estudianteId = ? 
        |        AND claseId = ? 
        |        AND periodoAcademicoId = ? 
        |        AND corte = ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, estudianteId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, claseId)
        _argIndex = 4
        _stmt.bindLong(_argIndex, periodoId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, corte.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfCorte: Int = getColumnIndexOrThrow(_stmt, "corte")
        val _columnIndexOfNota: Int = getColumnIndexOrThrow(_stmt, "nota")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: CalificacionEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpCorte: Int
          _tmpCorte = _stmt.getLong(_columnIndexOfCorte).toInt()
          val _tmpNota: Double
          _tmpNota = _stmt.getDouble(_columnIndexOfNota)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              CalificacionEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpClaseId,_tmpPeriodoAcademicoId,_tmpCorte,_tmpNota,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<CalificacionEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM academic_calificaciones 
        |        WHERE syncStatus != 0
        |        AND institutionId = ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfCorte: Int = getColumnIndexOrThrow(_stmt, "corte")
        val _columnIndexOfNota: Int = getColumnIndexOrThrow(_stmt, "nota")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CalificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CalificacionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpCorte: Int
          _tmpCorte = _stmt.getLong(_columnIndexOfCorte).toInt()
          val _tmpNota: Double
          _tmpNota = _stmt.getDouble(_columnIndexOfNota)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CalificacionEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpClaseId,_tmpPeriodoAcademicoId,_tmpCorte,_tmpNota,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSyncingRecords(instId: String): List<CalificacionEntity> {
    val _sql: String =
        "SELECT * FROM academic_calificaciones WHERE institutionId = ? AND syncStatus = 5"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfCorte: Int = getColumnIndexOrThrow(_stmt, "corte")
        val _columnIndexOfNota: Int = getColumnIndexOrThrow(_stmt, "nota")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CalificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CalificacionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpCorte: Int
          _tmpCorte = _stmt.getLong(_columnIndexOfCorte).toInt()
          val _tmpNota: Double
          _tmpNota = _stmt.getDouble(_columnIndexOfNota)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CalificacionEntity(_tmpId,_tmpInstitutionId,_tmpEstudianteId,_tmpClaseId,_tmpPeriodoAcademicoId,_tmpCorte,_tmpNota,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: String, instId: String) {
    val _sql: String = "DELETE FROM academic_calificaciones WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSyncing(ids: List<String>, instId: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE academic_calificaciones SET syncStatus = 5 WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND institutionId = ")
    _stringBuilder.append("?")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in ids) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 1 + _inputSize
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(ids: List<String>, instId: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE academic_calificaciones SET syncStatus = 0 WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND institutionId = ")
    _stringBuilder.append("?")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in ids) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 1 + _inputSize
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsPending(ids: List<String>, instId: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE academic_calificaciones SET syncStatus = 1 WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND institutionId = ")
    _stringBuilder.append("?")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in ids) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 1 + _inputSize
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun migrateStudentCalificaciones(
    sourceStudentId: String,
    targetStudentId: String,
    instId: String,
  ) {
    val _sql: String =
        "UPDATE academic_calificaciones SET estudianteId = ?, syncStatus = 2 WHERE estudianteId = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, targetStudentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, sourceStudentId)
        _argIndex = 3
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

package com.sigeschool.`data`.local.dao

import androidx.collection.ArrayMap
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchArrayMap
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AttendanceEntity
import com.sigeschool.`data`.local.entity.StudentEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AttendanceDao_Impl(
  __db: RoomDatabase,
) : AttendanceDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAttendanceEntity: EntityInsertAdapter<AttendanceEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAttendanceEntity = object : EntityInsertAdapter<AttendanceEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `attendance` (`id`,`institutionId`,`studentId`,`timestamp`,`type`,`claseId`,`observacion`,`justificacionUrl`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AttendanceEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindLong(4, entity.timestamp)
        statement.bindText(5, entity.type)
        val _tmpClaseId: Long? = entity.claseId
        if (_tmpClaseId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpClaseId)
        }
        val _tmpObservacion: String? = entity.observacion
        if (_tmpObservacion == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpObservacion)
        }
        val _tmpJustificacionUrl: String? = entity.justificacionUrl
        if (_tmpJustificacionUrl == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpJustificacionUrl)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
  }

  public override suspend fun insertAttendance(attendance: AttendanceEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAttendanceEntity.insert(_connection, attendance)
  }

  public override suspend fun insertAttendance(attendance: List<AttendanceEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAttendanceEntity.insert(_connection, attendance)
  }

  public override fun getAllAttendance(institutionId: String): Flow<List<AttendanceEntity>> {
    val _sql: String = "SELECT * FROM attendance WHERE institutionId = ? ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AttendanceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLatestMovements(institutionId: String, limit: Int):
      Flow<List<AttendanceWithStudent>> {
    val _sql: String =
        "SELECT * FROM attendance WHERE institutionId = ? ORDER BY timestamp DESC LIMIT ?"
    return createFlow(__db, true, arrayOf("students", "attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _collectionStudent: ArrayMap<String, StudentEntity?> =
            ArrayMap<String, StudentEntity?>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfStudentId)
          _collectionStudent.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipstudentsAscomSigeschoolDataLocalEntityStudentEntity(_connection,
            _collectionStudent)
        val _result: MutableList<AttendanceWithStudent> = mutableListOf()
        while (_stmt.step()) {
          val _item: AttendanceWithStudent
          val _tmpAttendance: AttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _tmpAttendance =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
          val _tmpStudent: StudentEntity?
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_columnIndexOfStudentId)
          _tmpStudent = _collectionStudent.get(_tmpKey_1)
          if (_tmpStudent == null) {
            error("Relationship item 'student' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'studentId' and entityColumn named 'id'.")
          }
          _item = AttendanceWithStudent(_tmpAttendance,_tmpStudent)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAttendanceByRange(
    institutionId: String,
    startOfDay: Long,
    nextDayStart: Long,
  ): Flow<List<AttendanceWithStudent>> {
    val _sql: String = """
        |
        |        SELECT * FROM attendance 
        |        WHERE institutionId = ? 
        |        AND timestamp >= ? 
        |        AND timestamp < ? 
        |        ORDER BY timestamp DESC
        |    
        """.trimMargin()
    return createFlow(__db, true, arrayOf("students", "attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 3
        _stmt.bindLong(_argIndex, nextDayStart)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _collectionStudent: ArrayMap<String, StudentEntity?> =
            ArrayMap<String, StudentEntity?>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfStudentId)
          _collectionStudent.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipstudentsAscomSigeschoolDataLocalEntityStudentEntity(_connection,
            _collectionStudent)
        val _result: MutableList<AttendanceWithStudent> = mutableListOf()
        while (_stmt.step()) {
          val _item: AttendanceWithStudent
          val _tmpAttendance: AttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _tmpAttendance =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
          val _tmpStudent: StudentEntity?
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_columnIndexOfStudentId)
          _tmpStudent = _collectionStudent.get(_tmpKey_1)
          if (_tmpStudent == null) {
            error("Relationship item 'student' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'studentId' and entityColumn named 'id'.")
          }
          _item = AttendanceWithStudent(_tmpAttendance,_tmpStudent)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPresentCount(
    institutionId: String,
    startOfDay: Long,
    nextDayStart: Long,
  ): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(DISTINCT studentId) FROM attendance 
        |        WHERE institutionId = ? 
        |        AND type = 'ENTRY' 
        |        AND timestamp >= ?
        |        AND timestamp < ?
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 3
        _stmt.bindLong(_argIndex, nextDayStart)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAbsentCount(
    institutionId: String,
    startOfDay: Long,
    nextDayStart: Long,
  ): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM students s
        |        WHERE s.institutionId = ? 
        |        AND NOT EXISTS (
        |            SELECT 1 FROM attendance a 
        |            WHERE a.studentId = s.id 
        |            AND a.institutionId = ? 
        |            AND a.type = 'ENTRY' 
        |            AND a.timestamp >= ?
        |            AND a.timestamp < ?
        |        )
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("students", "attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 4
        _stmt.bindLong(_argIndex, nextDayStart)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAttendanceByGrade(
    institutionId: String,
    startOfDay: Long,
    nextDayStart: Long,
  ): Flow<List<GradeAttendance>> {
    val _sql: String = """
        |
        |        SELECT 
        |            g.nombre as gradoNombre, 
        |            COUNT(DISTINCT m.estudianteId) as totalEstudiantes,
        |            COUNT(DISTINCT a.studentId) as totalPresentes
        |        FROM academic_grados g
        |        INNER JOIN academic_ofertas o ON g.id = o.gradoId AND o.institutionId = ?
        |        INNER JOIN academic_clases c ON o.id = c.ofertaAcademicaId AND c.institutionId = ?
        |        INNER JOIN academic_matriculas m ON c.id = m.claseId AND m.institutionId = ?
        |        LEFT JOIN attendance a ON m.estudianteId = a.studentId 
        |            AND a.institutionId = ?
        |            AND a.type = 'ENTRY' 
        |            AND a.timestamp >= ?
        |            AND a.timestamp < ?
        |        WHERE g.institutionId = ?
        |        GROUP BY g.id
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("academic_grados", "academic_ofertas", "academic_clases",
        "academic_matriculas", "attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 3
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 4
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 6
        _stmt.bindLong(_argIndex, nextDayStart)
        _argIndex = 7
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfGradoNombre: Int = 0
        val _columnIndexOfTotalEstudiantes: Int = 1
        val _columnIndexOfTotalPresentes: Int = 2
        val _result: MutableList<GradeAttendance> = mutableListOf()
        while (_stmt.step()) {
          val _item: GradeAttendance
          val _tmpGradoNombre: String
          _tmpGradoNombre = _stmt.getText(_columnIndexOfGradoNombre)
          val _tmpTotalEstudiantes: Int
          _tmpTotalEstudiantes = _stmt.getLong(_columnIndexOfTotalEstudiantes).toInt()
          val _tmpTotalPresentes: Int
          _tmpTotalPresentes = _stmt.getLong(_columnIndexOfTotalPresentes).toInt()
          _item = GradeAttendance(_tmpGradoNombre,_tmpTotalEstudiantes,_tmpTotalPresentes)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getOnSiteCount(
    institutionId: String,
    startOfDay: Long,
    nextDayStart: Long,
  ): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM (
        |            SELECT a1.studentId
        |            FROM attendance a1
        |            WHERE a1.institutionId = ?
        |              AND a1.timestamp = (
        |                  SELECT MAX(a2.timestamp) 
        |                  FROM attendance a2 
        |                  WHERE a2.studentId = a1.studentId 
        |                    AND a2.institutionId = ?
        |                    AND a2.timestamp >= ?
        |                    AND a2.timestamp < ?
        |              )
        |              AND a1.type = 'ENTRY'
        |        )
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 4
        _stmt.bindLong(_argIndex, nextDayStart)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllAttendanceSync(institutionId: String): List<AttendanceEntity> {
    val _sql: String = "SELECT * FROM attendance WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AttendanceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByEstudiante(studentId: String, institutionId: String):
      Flow<List<AttendanceEntity>> {
    val _sql: String =
        "SELECT * FROM attendance WHERE studentId = ? AND institutionId = ? ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AttendanceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLastAttendanceForStudent(studentId: String, institutionId: String):
      AttendanceEntity? {
    val _sql: String =
        "SELECT * FROM attendance WHERE studentId = ? AND institutionId = ? ORDER BY timestamp DESC LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: AttendanceEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSyncingRecords(institutionId: String): List<AttendanceEntity> {
    val _sql: String = "SELECT * FROM attendance WHERE institutionId = ? AND syncStatus = 5"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AttendanceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSyncAttendance(institutionId: String):
      List<AttendanceEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM attendance 
        |        WHERE syncStatus != 0
        |        AND institutionId = ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfJustificacionUrl: Int = getColumnIndexOrThrow(_stmt, "justificacionUrl")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AttendanceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpClaseId: Long?
          if (_stmt.isNull(_columnIndexOfClaseId)) {
            _tmpClaseId = null
          } else {
            _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          }
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpJustificacionUrl: String?
          if (_stmt.isNull(_columnIndexOfJustificacionUrl)) {
            _tmpJustificacionUrl = null
          } else {
            _tmpJustificacionUrl = _stmt.getText(_columnIndexOfJustificacionUrl)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTimestamp,_tmpType,_tmpClaseId,_tmpObservacion,_tmpJustificacionUrl,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsPending(ids: List<String>, institutionId: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE attendance SET syncStatus = 1 WHERE id IN (")
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
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSyncing(ids: List<String>, institutionId: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE attendance SET syncStatus = 5 WHERE id IN (")
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
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(ids: List<String>, institutionId: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE attendance SET syncStatus = 0 WHERE id IN (")
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
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, institutionId: String) {
    val _sql: String = "UPDATE attendance SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAttendance(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM attendance WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun migrateStudentAttendance(
    sourceStudentId: String,
    targetStudentId: String,
    institutionId: String,
  ) {
    val _sql: String =
        "UPDATE attendance SET studentId = ?, syncStatus = 2 WHERE studentId = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, targetStudentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, sourceStudentId)
        _argIndex = 3
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  private
      fun __fetchRelationshipstudentsAscomSigeschoolDataLocalEntityStudentEntity(_connection: SQLiteConnection,
      _map: ArrayMap<String, StudentEntity?>) {
    val __mapKeySet: Set<String> = _map.keys
    if (__mapKeySet.isEmpty()) {
      return
    }
    if (_map.size > 999) {
      recursiveFetchArrayMap(_map, false) { _tmpMap ->
        __fetchRelationshipstudentsAscomSigeschoolDataLocalEntityStudentEntity(_connection, _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `id`,`institutionId`,`userId`,`firstName`,`lastName`,`documentId`,`gender`,`ethnicity`,`ethnicCommunity`,`disability`,`disabilityAdjustments`,`photoPath`,`qrCode`,`cursoId`,`consentAcceptedAt`,`consentVersion`,`syncStatus`,`lastModified`,`isDuplicate`,`mergedIntoId`,`deletedAt`,`deletedReason`,`deletedByUserId`,`documentType`,`birthDate`,`age`,`email`,`phone`,`address`,`neighborhood`,`stratum`,`educationLevel`,`previousSchool`,`selectedPrograms`,`howDidYouHear`,`status`,`withdrawalReason`,`withdrawalDate`,`statusUpdatedAt`,`photoUpdatedAt`,`estadoMatricula`,`fechaRetiro`,`motivoRetiro`,`diasInasistenciaConsecutiva`,`ultimaFechaAsistencia`,`alertaEnviada30Dias`,`guardianFirstName`,`guardianLastName`,`guardianDocumentId`,`guardianRelationship`,`guardianPhone`,`guardianEmail`,`esExterno`,`institucionOrigen`,`externoId` FROM `students` WHERE `id` IN (")
    val _inputSize: Int = __mapKeySet.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (_item: String in __mapKeySet) {
      _stmt.bindText(_argIndex, _item)
      _argIndex++
    }
    try {
      val _itemKeyIndex: Int = getColumnIndex(_stmt, "id")
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfId: Int = 0
      val _columnIndexOfInstitutionId: Int = 1
      val _columnIndexOfUserId: Int = 2
      val _columnIndexOfFirstName: Int = 3
      val _columnIndexOfLastName: Int = 4
      val _columnIndexOfDocumentId: Int = 5
      val _columnIndexOfGender: Int = 6
      val _columnIndexOfEthnicity: Int = 7
      val _columnIndexOfEthnicCommunity: Int = 8
      val _columnIndexOfDisability: Int = 9
      val _columnIndexOfDisabilityAdjustments: Int = 10
      val _columnIndexOfPhotoPath: Int = 11
      val _columnIndexOfQrCode: Int = 12
      val _columnIndexOfCursoId: Int = 13
      val _columnIndexOfConsentAcceptedAt: Int = 14
      val _columnIndexOfConsentVersion: Int = 15
      val _columnIndexOfSyncStatus: Int = 16
      val _columnIndexOfLastModified: Int = 17
      val _columnIndexOfIsDuplicate: Int = 18
      val _columnIndexOfMergedIntoId: Int = 19
      val _columnIndexOfDeletedAt: Int = 20
      val _columnIndexOfDeletedReason: Int = 21
      val _columnIndexOfDeletedByUserId: Int = 22
      val _columnIndexOfDocumentType: Int = 23
      val _columnIndexOfBirthDate: Int = 24
      val _columnIndexOfAge: Int = 25
      val _columnIndexOfEmail: Int = 26
      val _columnIndexOfPhone: Int = 27
      val _columnIndexOfAddress: Int = 28
      val _columnIndexOfNeighborhood: Int = 29
      val _columnIndexOfStratum: Int = 30
      val _columnIndexOfEducationLevel: Int = 31
      val _columnIndexOfPreviousSchool: Int = 32
      val _columnIndexOfSelectedPrograms: Int = 33
      val _columnIndexOfHowDidYouHear: Int = 34
      val _columnIndexOfStatus: Int = 35
      val _columnIndexOfWithdrawalReason: Int = 36
      val _columnIndexOfWithdrawalDate: Int = 37
      val _columnIndexOfStatusUpdatedAt: Int = 38
      val _columnIndexOfPhotoUpdatedAt: Int = 39
      val _columnIndexOfEstadoMatricula: Int = 40
      val _columnIndexOfFechaRetiro: Int = 41
      val _columnIndexOfMotivoRetiro: Int = 42
      val _columnIndexOfDiasInasistenciaConsecutiva: Int = 43
      val _columnIndexOfUltimaFechaAsistencia: Int = 44
      val _columnIndexOfAlertaEnviada30Dias: Int = 45
      val _columnIndexOfGuardianFirstName: Int = 46
      val _columnIndexOfGuardianLastName: Int = 47
      val _columnIndexOfGuardianDocumentId: Int = 48
      val _columnIndexOfGuardianRelationship: Int = 49
      val _columnIndexOfGuardianPhone: Int = 50
      val _columnIndexOfGuardianEmail: Int = 51
      val _columnIndexOfEsExterno: Int = 52
      val _columnIndexOfInstitucionOrigen: Int = 53
      val _columnIndexOfExternoId: Int = 54
      while (_stmt.step()) {
        val _tmpKey: String
        _tmpKey = _stmt.getText(_itemKeyIndex)
        if (_map.containsKey(_tmpKey)) {
          val _item_1: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item_1 =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _map.put(_tmpKey, _item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

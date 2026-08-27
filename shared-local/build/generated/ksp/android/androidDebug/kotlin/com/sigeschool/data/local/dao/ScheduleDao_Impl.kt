package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ClassroomEntity
import com.sigeschool.`data`.local.entity.ScheduleEntity
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
public class ScheduleDao_Impl(
  __db: RoomDatabase,
) : ScheduleDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfClassroomEntity: EntityInsertAdapter<ClassroomEntity>

  private val __insertAdapterOfScheduleEntity: EntityInsertAdapter<ScheduleEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfClassroomEntity = object : EntityInsertAdapter<ClassroomEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `classrooms` (`id`,`institutionId`,`name`,`capacity`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ClassroomEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.name)
        statement.bindLong(4, entity.capacity.toLong())
        statement.bindLong(5, entity.syncStatus.toLong())
        statement.bindLong(6, entity.lastModified)
      }
    }
    this.__insertAdapterOfScheduleEntity = object : EntityInsertAdapter<ScheduleEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `schedules` (`id`,`institutionId`,`classroomId`,`subjectId`,`teacherId`,`dayOfWeek`,`startTime`,`endTime`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ScheduleEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.classroomId)
        statement.bindText(4, entity.subjectId)
        statement.bindText(5, entity.teacherId)
        statement.bindLong(6, entity.dayOfWeek.toLong())
        statement.bindText(7, entity.startTime)
        statement.bindText(8, entity.endTime)
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
  }

  public override suspend fun insertClassroom(classroom: ClassroomEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfClassroomEntity.insert(_connection, classroom)
  }

  public override suspend fun insertSchedule(schedule: ScheduleEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfScheduleEntity.insert(_connection, schedule)
  }

  public override fun getAllClassrooms(institutionId: String): Flow<List<ClassroomEntity>> {
    val _sql: String = "SELECT * FROM classrooms WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("classrooms")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfCapacity: Int = getColumnIndexOrThrow(_stmt, "capacity")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ClassroomEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClassroomEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCapacity: Int
          _tmpCapacity = _stmt.getLong(_columnIndexOfCapacity).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ClassroomEntity(_tmpId,_tmpInstitutionId,_tmpName,_tmpCapacity,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllSchedules(institutionId: String): Flow<List<ScheduleEntity>> {
    val _sql: String = "SELECT * FROM schedules WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("schedules")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClassroomId: Int = getColumnIndexOrThrow(_stmt, "classroomId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _columnIndexOfStartTime: Int = getColumnIndexOrThrow(_stmt, "startTime")
        val _columnIndexOfEndTime: Int = getColumnIndexOrThrow(_stmt, "endTime")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ScheduleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ScheduleEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClassroomId: String
          _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpTeacherId: String
          _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          val _tmpDayOfWeek: Int
          _tmpDayOfWeek = _stmt.getLong(_columnIndexOfDayOfWeek).toInt()
          val _tmpStartTime: String
          _tmpStartTime = _stmt.getText(_columnIndexOfStartTime)
          val _tmpEndTime: String
          _tmpEndTime = _stmt.getText(_columnIndexOfEndTime)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ScheduleEntity(_tmpId,_tmpInstitutionId,_tmpClassroomId,_tmpSubjectId,_tmpTeacherId,_tmpDayOfWeek,_tmpStartTime,_tmpEndTime,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSchedulesByClassroom(classroomId: String, institutionId: String):
      Flow<List<ScheduleEntity>> {
    val _sql: String = "SELECT * FROM schedules WHERE classroomId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("schedules")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, classroomId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClassroomId: Int = getColumnIndexOrThrow(_stmt, "classroomId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _columnIndexOfStartTime: Int = getColumnIndexOrThrow(_stmt, "startTime")
        val _columnIndexOfEndTime: Int = getColumnIndexOrThrow(_stmt, "endTime")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ScheduleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ScheduleEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClassroomId: String
          _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpTeacherId: String
          _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          val _tmpDayOfWeek: Int
          _tmpDayOfWeek = _stmt.getLong(_columnIndexOfDayOfWeek).toInt()
          val _tmpStartTime: String
          _tmpStartTime = _stmt.getText(_columnIndexOfStartTime)
          val _tmpEndTime: String
          _tmpEndTime = _stmt.getText(_columnIndexOfEndTime)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ScheduleEntity(_tmpId,_tmpInstitutionId,_tmpClassroomId,_tmpSubjectId,_tmpTeacherId,_tmpDayOfWeek,_tmpStartTime,_tmpEndTime,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteScheduleById(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM schedules WHERE id = ? AND institutionId = ?"
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

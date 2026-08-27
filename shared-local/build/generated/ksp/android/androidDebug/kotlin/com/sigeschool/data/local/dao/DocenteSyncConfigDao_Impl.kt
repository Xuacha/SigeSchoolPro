package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.DocenteSyncConfigEntity
import com.sigeschool.`data`.local.entity.DocenteSyncLogEntity
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
public class DocenteSyncConfigDao_Impl(
  __db: RoomDatabase,
) : DocenteSyncConfigDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDocenteSyncConfigEntity: EntityInsertAdapter<DocenteSyncConfigEntity>

  private val __insertAdapterOfDocenteSyncLogEntity: EntityInsertAdapter<DocenteSyncLogEntity>

  private val __deleteAdapterOfDocenteSyncConfigEntity:
      EntityDeleteOrUpdateAdapter<DocenteSyncConfigEntity>

  private val __updateAdapterOfDocenteSyncConfigEntity:
      EntityDeleteOrUpdateAdapter<DocenteSyncConfigEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDocenteSyncConfigEntity = object :
        EntityInsertAdapter<DocenteSyncConfigEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `docente_sync_configs` (`id`,`institutionId`,`docenteId`,`claseId`,`type`,`url`,`classroomCourseId`,`classroomCourseWorkId`,`syncIntervalHours`,`lastSyncTimestamp`,`isActive`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteSyncConfigEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.docenteId)
        statement.bindLong(4, entity.claseId)
        statement.bindText(5, entity.type)
        val _tmpUrl: String? = entity.url
        if (_tmpUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpUrl)
        }
        val _tmpClassroomCourseId: String? = entity.classroomCourseId
        if (_tmpClassroomCourseId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpClassroomCourseId)
        }
        val _tmpClassroomCourseWorkId: String? = entity.classroomCourseWorkId
        if (_tmpClassroomCourseWorkId == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpClassroomCourseWorkId)
        }
        statement.bindLong(9, entity.syncIntervalHours.toLong())
        statement.bindLong(10, entity.lastSyncTimestamp)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
      }
    }
    this.__insertAdapterOfDocenteSyncLogEntity = object :
        EntityInsertAdapter<DocenteSyncLogEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `docente_sync_logs` (`id`,`configId`,`timestamp`,`result`,`message`,`itemsProcessed`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteSyncLogEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.configId)
        statement.bindLong(3, entity.timestamp)
        statement.bindText(4, entity.result)
        val _tmpMessage: String? = entity.message
        if (_tmpMessage == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpMessage)
        }
        statement.bindLong(6, entity.itemsProcessed.toLong())
      }
    }
    this.__deleteAdapterOfDocenteSyncConfigEntity = object :
        EntityDeleteOrUpdateAdapter<DocenteSyncConfigEntity>() {
      protected override fun createQuery(): String =
          "DELETE FROM `docente_sync_configs` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteSyncConfigEntity) {
        statement.bindText(1, entity.id)
      }
    }
    this.__updateAdapterOfDocenteSyncConfigEntity = object :
        EntityDeleteOrUpdateAdapter<DocenteSyncConfigEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `docente_sync_configs` SET `id` = ?,`institutionId` = ?,`docenteId` = ?,`claseId` = ?,`type` = ?,`url` = ?,`classroomCourseId` = ?,`classroomCourseWorkId` = ?,`syncIntervalHours` = ?,`lastSyncTimestamp` = ?,`isActive` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteSyncConfigEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.docenteId)
        statement.bindLong(4, entity.claseId)
        statement.bindText(5, entity.type)
        val _tmpUrl: String? = entity.url
        if (_tmpUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpUrl)
        }
        val _tmpClassroomCourseId: String? = entity.classroomCourseId
        if (_tmpClassroomCourseId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpClassroomCourseId)
        }
        val _tmpClassroomCourseWorkId: String? = entity.classroomCourseWorkId
        if (_tmpClassroomCourseWorkId == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpClassroomCourseWorkId)
        }
        statement.bindLong(9, entity.syncIntervalHours.toLong())
        statement.bindLong(10, entity.lastSyncTimestamp)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
        statement.bindText(14, entity.id)
      }
    }
  }

  public override suspend fun insertConfig(config: DocenteSyncConfigEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfDocenteSyncConfigEntity.insert(_connection, config)
  }

  public override suspend fun insertLog(log: DocenteSyncLogEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfDocenteSyncLogEntity.insert(_connection, log)
  }

  public override suspend fun deleteConfig(config: DocenteSyncConfigEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfDocenteSyncConfigEntity.handle(_connection, config)
  }

  public override suspend fun updateConfig(config: DocenteSyncConfigEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfDocenteSyncConfigEntity.handle(_connection, config)
  }

  public override fun getConfigsByDocente(institutionId: String, docenteId: String):
      Flow<List<DocenteSyncConfigEntity>> {
    val _sql: String =
        "SELECT * FROM docente_sync_configs WHERE institutionId = ? AND docenteId = ?"
    return createFlow(__db, false, arrayOf("docente_sync_configs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, docenteId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfClassroomCourseId: Int = getColumnIndexOrThrow(_stmt, "classroomCourseId")
        val _columnIndexOfClassroomCourseWorkId: Int = getColumnIndexOrThrow(_stmt,
            "classroomCourseWorkId")
        val _columnIndexOfSyncIntervalHours: Int = getColumnIndexOrThrow(_stmt, "syncIntervalHours")
        val _columnIndexOfLastSyncTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastSyncTimestamp")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DocenteSyncConfigEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocenteSyncConfigEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpUrl: String?
          if (_stmt.isNull(_columnIndexOfUrl)) {
            _tmpUrl = null
          } else {
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          }
          val _tmpClassroomCourseId: String?
          if (_stmt.isNull(_columnIndexOfClassroomCourseId)) {
            _tmpClassroomCourseId = null
          } else {
            _tmpClassroomCourseId = _stmt.getText(_columnIndexOfClassroomCourseId)
          }
          val _tmpClassroomCourseWorkId: String?
          if (_stmt.isNull(_columnIndexOfClassroomCourseWorkId)) {
            _tmpClassroomCourseWorkId = null
          } else {
            _tmpClassroomCourseWorkId = _stmt.getText(_columnIndexOfClassroomCourseWorkId)
          }
          val _tmpSyncIntervalHours: Int
          _tmpSyncIntervalHours = _stmt.getLong(_columnIndexOfSyncIntervalHours).toInt()
          val _tmpLastSyncTimestamp: Long
          _tmpLastSyncTimestamp = _stmt.getLong(_columnIndexOfLastSyncTimestamp)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DocenteSyncConfigEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpClaseId,_tmpType,_tmpUrl,_tmpClassroomCourseId,_tmpClassroomCourseWorkId,_tmpSyncIntervalHours,_tmpLastSyncTimestamp,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllActiveConfigs(): List<DocenteSyncConfigEntity> {
    val _sql: String = "SELECT * FROM docente_sync_configs WHERE isActive = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfClassroomCourseId: Int = getColumnIndexOrThrow(_stmt, "classroomCourseId")
        val _columnIndexOfClassroomCourseWorkId: Int = getColumnIndexOrThrow(_stmt,
            "classroomCourseWorkId")
        val _columnIndexOfSyncIntervalHours: Int = getColumnIndexOrThrow(_stmt, "syncIntervalHours")
        val _columnIndexOfLastSyncTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastSyncTimestamp")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DocenteSyncConfigEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocenteSyncConfigEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpUrl: String?
          if (_stmt.isNull(_columnIndexOfUrl)) {
            _tmpUrl = null
          } else {
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          }
          val _tmpClassroomCourseId: String?
          if (_stmt.isNull(_columnIndexOfClassroomCourseId)) {
            _tmpClassroomCourseId = null
          } else {
            _tmpClassroomCourseId = _stmt.getText(_columnIndexOfClassroomCourseId)
          }
          val _tmpClassroomCourseWorkId: String?
          if (_stmt.isNull(_columnIndexOfClassroomCourseWorkId)) {
            _tmpClassroomCourseWorkId = null
          } else {
            _tmpClassroomCourseWorkId = _stmt.getText(_columnIndexOfClassroomCourseWorkId)
          }
          val _tmpSyncIntervalHours: Int
          _tmpSyncIntervalHours = _stmt.getLong(_columnIndexOfSyncIntervalHours).toInt()
          val _tmpLastSyncTimestamp: Long
          _tmpLastSyncTimestamp = _stmt.getLong(_columnIndexOfLastSyncTimestamp)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DocenteSyncConfigEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpClaseId,_tmpType,_tmpUrl,_tmpClassroomCourseId,_tmpClassroomCourseWorkId,_tmpSyncIntervalHours,_tmpLastSyncTimestamp,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getConfigById(id: String): DocenteSyncConfigEntity? {
    val _sql: String = "SELECT * FROM docente_sync_configs WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfClassroomCourseId: Int = getColumnIndexOrThrow(_stmt, "classroomCourseId")
        val _columnIndexOfClassroomCourseWorkId: Int = getColumnIndexOrThrow(_stmt,
            "classroomCourseWorkId")
        val _columnIndexOfSyncIntervalHours: Int = getColumnIndexOrThrow(_stmt, "syncIntervalHours")
        val _columnIndexOfLastSyncTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastSyncTimestamp")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: DocenteSyncConfigEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpUrl: String?
          if (_stmt.isNull(_columnIndexOfUrl)) {
            _tmpUrl = null
          } else {
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          }
          val _tmpClassroomCourseId: String?
          if (_stmt.isNull(_columnIndexOfClassroomCourseId)) {
            _tmpClassroomCourseId = null
          } else {
            _tmpClassroomCourseId = _stmt.getText(_columnIndexOfClassroomCourseId)
          }
          val _tmpClassroomCourseWorkId: String?
          if (_stmt.isNull(_columnIndexOfClassroomCourseWorkId)) {
            _tmpClassroomCourseWorkId = null
          } else {
            _tmpClassroomCourseWorkId = _stmt.getText(_columnIndexOfClassroomCourseWorkId)
          }
          val _tmpSyncIntervalHours: Int
          _tmpSyncIntervalHours = _stmt.getLong(_columnIndexOfSyncIntervalHours).toInt()
          val _tmpLastSyncTimestamp: Long
          _tmpLastSyncTimestamp = _stmt.getLong(_columnIndexOfLastSyncTimestamp)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              DocenteSyncConfigEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpClaseId,_tmpType,_tmpUrl,_tmpClassroomCourseId,_tmpClassroomCourseWorkId,_tmpSyncIntervalHours,_tmpLastSyncTimestamp,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLogsByConfig(configId: String): Flow<List<DocenteSyncLogEntity>> {
    val _sql: String =
        "SELECT * FROM docente_sync_logs WHERE configId = ? ORDER BY timestamp DESC LIMIT 50"
    return createFlow(__db, false, arrayOf("docente_sync_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, configId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfConfigId: Int = getColumnIndexOrThrow(_stmt, "configId")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfResult: Int = getColumnIndexOrThrow(_stmt, "result")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfItemsProcessed: Int = getColumnIndexOrThrow(_stmt, "itemsProcessed")
        val _result: MutableList<DocenteSyncLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocenteSyncLogEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpConfigId: String
          _tmpConfigId = _stmt.getText(_columnIndexOfConfigId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpResult: String
          _tmpResult = _stmt.getText(_columnIndexOfResult)
          val _tmpMessage: String?
          if (_stmt.isNull(_columnIndexOfMessage)) {
            _tmpMessage = null
          } else {
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          }
          val _tmpItemsProcessed: Int
          _tmpItemsProcessed = _stmt.getLong(_columnIndexOfItemsProcessed).toInt()
          _item =
              DocenteSyncLogEntity(_tmpId,_tmpConfigId,_tmpTimestamp,_tmpResult,_tmpMessage,_tmpItemsProcessed)
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

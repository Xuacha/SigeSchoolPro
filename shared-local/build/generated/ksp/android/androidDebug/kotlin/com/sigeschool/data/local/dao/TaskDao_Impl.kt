package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.TareaAdjuntoEntity
import com.sigeschool.`data`.local.entity.TareaEntity
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
public class TaskDao_Impl(
  __db: RoomDatabase,
) : TaskDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfTareaEntity: EntityInsertAdapter<TareaEntity>

  private val __insertAdapterOfTareaAdjuntoEntity: EntityInsertAdapter<TareaAdjuntoEntity>

  private val __updateAdapterOfTareaEntity: EntityDeleteOrUpdateAdapter<TareaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfTareaEntity = object : EntityInsertAdapter<TareaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `tareas` (`id`,`institutionId`,`claseId`,`title`,`description`,`deadline`,`createdBy`,`createdAt`,`lastModified`,`syncStatus`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: TareaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.claseId)
        statement.bindText(4, entity.title)
        statement.bindText(5, entity.description)
        statement.bindLong(6, entity.deadline)
        statement.bindText(7, entity.createdBy)
        statement.bindLong(8, entity.createdAt)
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.syncStatus.toLong())
      }
    }
    this.__insertAdapterOfTareaAdjuntoEntity = object : EntityInsertAdapter<TareaAdjuntoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `task_attachments` (`id`,`parentId`,`fileName`,`fileUrl`,`fileType`,`lastModified`,`syncStatus`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: TareaAdjuntoEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.parentId)
        statement.bindText(3, entity.fileName)
        statement.bindText(4, entity.fileUrl)
        statement.bindText(5, entity.fileType)
        statement.bindLong(6, entity.lastModified)
        statement.bindLong(7, entity.syncStatus.toLong())
      }
    }
    this.__updateAdapterOfTareaEntity = object : EntityDeleteOrUpdateAdapter<TareaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `tareas` SET `id` = ?,`institutionId` = ?,`claseId` = ?,`title` = ?,`description` = ?,`deadline` = ?,`createdBy` = ?,`createdAt` = ?,`lastModified` = ?,`syncStatus` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: TareaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.claseId)
        statement.bindText(4, entity.title)
        statement.bindText(5, entity.description)
        statement.bindLong(6, entity.deadline)
        statement.bindText(7, entity.createdBy)
        statement.bindLong(8, entity.createdAt)
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindText(11, entity.id)
      }
    }
  }

  public override suspend fun insertTask(task: TareaEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfTareaEntity.insert(_connection, task)
  }

  public override suspend fun insertAttachments(attachments: List<TareaAdjuntoEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTareaAdjuntoEntity.insert(_connection, attachments)
  }

  public override suspend fun updateTask(task: TareaEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfTareaEntity.handle(_connection, task)
  }

  public override fun getTasksByClase(institutionId: String, claseId: Long):
      Flow<List<TareaEntity>> {
    val _sql: String = "SELECT * FROM tareas WHERE institutionId = ? AND claseId = ?"
    return createFlow(__db, false, arrayOf("tareas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, claseId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDeadline: Int = getColumnIndexOrThrow(_stmt, "deadline")
        val _columnIndexOfCreatedBy: Int = getColumnIndexOrThrow(_stmt, "createdBy")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<TareaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: TareaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDeadline: Long
          _tmpDeadline = _stmt.getLong(_columnIndexOfDeadline)
          val _tmpCreatedBy: String
          _tmpCreatedBy = _stmt.getText(_columnIndexOfCreatedBy)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              TareaEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpTitle,_tmpDescription,_tmpDeadline,_tmpCreatedBy,_tmpCreatedAt,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTaskById(id: String, institutionId: String): TareaEntity? {
    val _sql: String = "SELECT * FROM tareas WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDeadline: Int = getColumnIndexOrThrow(_stmt, "deadline")
        val _columnIndexOfCreatedBy: Int = getColumnIndexOrThrow(_stmt, "createdBy")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: TareaEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDeadline: Long
          _tmpDeadline = _stmt.getLong(_columnIndexOfDeadline)
          val _tmpCreatedBy: String
          _tmpCreatedBy = _stmt.getText(_columnIndexOfCreatedBy)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _result =
              TareaEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpTitle,_tmpDescription,_tmpDeadline,_tmpCreatedBy,_tmpCreatedAt,_tmpLastModified,_tmpSyncStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAttachments(parentId: String): Flow<List<TareaAdjuntoEntity>> {
    val _sql: String = "SELECT * FROM task_attachments WHERE parentId = ?"
    return createFlow(__db, false, arrayOf("task_attachments")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, parentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfParentId: Int = getColumnIndexOrThrow(_stmt, "parentId")
        val _columnIndexOfFileName: Int = getColumnIndexOrThrow(_stmt, "fileName")
        val _columnIndexOfFileUrl: Int = getColumnIndexOrThrow(_stmt, "fileUrl")
        val _columnIndexOfFileType: Int = getColumnIndexOrThrow(_stmt, "fileType")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<TareaAdjuntoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: TareaAdjuntoEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpParentId: String
          _tmpParentId = _stmt.getText(_columnIndexOfParentId)
          val _tmpFileName: String
          _tmpFileName = _stmt.getText(_columnIndexOfFileName)
          val _tmpFileUrl: String
          _tmpFileUrl = _stmt.getText(_columnIndexOfFileUrl)
          val _tmpFileType: String
          _tmpFileType = _stmt.getText(_columnIndexOfFileType)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              TareaAdjuntoEntity(_tmpId,_tmpParentId,_tmpFileName,_tmpFileUrl,_tmpFileType,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSyncTasks(institutionId: String): List<TareaEntity> {
    val _sql: String = "SELECT * FROM tareas WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDeadline: Int = getColumnIndexOrThrow(_stmt, "deadline")
        val _columnIndexOfCreatedBy: Int = getColumnIndexOrThrow(_stmt, "createdBy")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<TareaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: TareaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDeadline: Long
          _tmpDeadline = _stmt.getLong(_columnIndexOfDeadline)
          val _tmpCreatedBy: String
          _tmpCreatedBy = _stmt.getText(_columnIndexOfCreatedBy)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              TareaEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpTitle,_tmpDescription,_tmpDeadline,_tmpCreatedBy,_tmpCreatedAt,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteTask(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM tareas WHERE id = ? AND institutionId = ?"
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

  public override suspend fun deleteAttachmentsByParent(parentId: String) {
    val _sql: String = "DELETE FROM task_attachments WHERE parentId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, parentId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, institutionId: String) {
    val _sql: String = "UPDATE tareas SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

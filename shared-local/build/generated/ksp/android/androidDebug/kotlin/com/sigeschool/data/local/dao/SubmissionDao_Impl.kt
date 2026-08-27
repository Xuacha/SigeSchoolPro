package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.EntregaEntity
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
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SubmissionDao_Impl(
  __db: RoomDatabase,
) : SubmissionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEntregaEntity: EntityInsertAdapter<EntregaEntity>

  private val __updateAdapterOfEntregaEntity: EntityDeleteOrUpdateAdapter<EntregaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfEntregaEntity = object : EntityInsertAdapter<EntregaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `task_submissions` (`id`,`tareaId`,`estudianteId`,`status`,`submissionDate`,`comment`,`grade`,`feedback`,`lastModified`,`syncStatus`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: EntregaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.tareaId)
        statement.bindText(3, entity.estudianteId)
        statement.bindText(4, entity.status)
        statement.bindLong(5, entity.submissionDate)
        val _tmpComment: String? = entity.comment
        if (_tmpComment == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpComment)
        }
        val _tmpGrade: Double? = entity.grade
        if (_tmpGrade == null) {
          statement.bindNull(7)
        } else {
          statement.bindDouble(7, _tmpGrade)
        }
        val _tmpFeedback: String? = entity.feedback
        if (_tmpFeedback == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpFeedback)
        }
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.syncStatus.toLong())
      }
    }
    this.__updateAdapterOfEntregaEntity = object : EntityDeleteOrUpdateAdapter<EntregaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `task_submissions` SET `id` = ?,`tareaId` = ?,`estudianteId` = ?,`status` = ?,`submissionDate` = ?,`comment` = ?,`grade` = ?,`feedback` = ?,`lastModified` = ?,`syncStatus` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: EntregaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.tareaId)
        statement.bindText(3, entity.estudianteId)
        statement.bindText(4, entity.status)
        statement.bindLong(5, entity.submissionDate)
        val _tmpComment: String? = entity.comment
        if (_tmpComment == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpComment)
        }
        val _tmpGrade: Double? = entity.grade
        if (_tmpGrade == null) {
          statement.bindNull(7)
        } else {
          statement.bindDouble(7, _tmpGrade)
        }
        val _tmpFeedback: String? = entity.feedback
        if (_tmpFeedback == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpFeedback)
        }
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindText(11, entity.id)
      }
    }
  }

  public override suspend fun insertSubmission(submission: EntregaEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfEntregaEntity.insert(_connection, submission)
  }

  public override suspend fun updateSubmission(submission: EntregaEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfEntregaEntity.handle(_connection, submission)
  }

  public override fun getSubmissionsByTask(tareaId: String): Flow<List<EntregaEntity>> {
    val _sql: String = "SELECT * FROM task_submissions WHERE tareaId = ?"
    return createFlow(__db, false, arrayOf("task_submissions")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, tareaId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTareaId: Int = getColumnIndexOrThrow(_stmt, "tareaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSubmissionDate: Int = getColumnIndexOrThrow(_stmt, "submissionDate")
        val _columnIndexOfComment: Int = getColumnIndexOrThrow(_stmt, "comment")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfFeedback: Int = getColumnIndexOrThrow(_stmt, "feedback")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<EntregaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EntregaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTareaId: String
          _tmpTareaId = _stmt.getText(_columnIndexOfTareaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSubmissionDate: Long
          _tmpSubmissionDate = _stmt.getLong(_columnIndexOfSubmissionDate)
          val _tmpComment: String?
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment)
          }
          val _tmpGrade: Double?
          if (_stmt.isNull(_columnIndexOfGrade)) {
            _tmpGrade = null
          } else {
            _tmpGrade = _stmt.getDouble(_columnIndexOfGrade)
          }
          val _tmpFeedback: String?
          if (_stmt.isNull(_columnIndexOfFeedback)) {
            _tmpFeedback = null
          } else {
            _tmpFeedback = _stmt.getText(_columnIndexOfFeedback)
          }
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              EntregaEntity(_tmpId,_tmpTareaId,_tmpEstudianteId,_tmpStatus,_tmpSubmissionDate,_tmpComment,_tmpGrade,_tmpFeedback,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubmission(tareaId: String, estudianteId: String): EntregaEntity? {
    val _sql: String = "SELECT * FROM task_submissions WHERE tareaId = ? AND estudianteId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, tareaId)
        _argIndex = 2
        _stmt.bindText(_argIndex, estudianteId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTareaId: Int = getColumnIndexOrThrow(_stmt, "tareaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSubmissionDate: Int = getColumnIndexOrThrow(_stmt, "submissionDate")
        val _columnIndexOfComment: Int = getColumnIndexOrThrow(_stmt, "comment")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfFeedback: Int = getColumnIndexOrThrow(_stmt, "feedback")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: EntregaEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTareaId: String
          _tmpTareaId = _stmt.getText(_columnIndexOfTareaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSubmissionDate: Long
          _tmpSubmissionDate = _stmt.getLong(_columnIndexOfSubmissionDate)
          val _tmpComment: String?
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment)
          }
          val _tmpGrade: Double?
          if (_stmt.isNull(_columnIndexOfGrade)) {
            _tmpGrade = null
          } else {
            _tmpGrade = _stmt.getDouble(_columnIndexOfGrade)
          }
          val _tmpFeedback: String?
          if (_stmt.isNull(_columnIndexOfFeedback)) {
            _tmpFeedback = null
          } else {
            _tmpFeedback = _stmt.getText(_columnIndexOfFeedback)
          }
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _result =
              EntregaEntity(_tmpId,_tmpTareaId,_tmpEstudianteId,_tmpStatus,_tmpSubmissionDate,_tmpComment,_tmpGrade,_tmpFeedback,_tmpLastModified,_tmpSyncStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubmissionById(id: String): EntregaEntity? {
    val _sql: String = "SELECT * FROM task_submissions WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTareaId: Int = getColumnIndexOrThrow(_stmt, "tareaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSubmissionDate: Int = getColumnIndexOrThrow(_stmt, "submissionDate")
        val _columnIndexOfComment: Int = getColumnIndexOrThrow(_stmt, "comment")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfFeedback: Int = getColumnIndexOrThrow(_stmt, "feedback")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: EntregaEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTareaId: String
          _tmpTareaId = _stmt.getText(_columnIndexOfTareaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSubmissionDate: Long
          _tmpSubmissionDate = _stmt.getLong(_columnIndexOfSubmissionDate)
          val _tmpComment: String?
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment)
          }
          val _tmpGrade: Double?
          if (_stmt.isNull(_columnIndexOfGrade)) {
            _tmpGrade = null
          } else {
            _tmpGrade = _stmt.getDouble(_columnIndexOfGrade)
          }
          val _tmpFeedback: String?
          if (_stmt.isNull(_columnIndexOfFeedback)) {
            _tmpFeedback = null
          } else {
            _tmpFeedback = _stmt.getText(_columnIndexOfFeedback)
          }
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _result =
              EntregaEntity(_tmpId,_tmpTareaId,_tmpEstudianteId,_tmpStatus,_tmpSubmissionDate,_tmpComment,_tmpGrade,_tmpFeedback,_tmpLastModified,_tmpSyncStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSyncSubmissions(): List<EntregaEntity> {
    val _sql: String = "SELECT * FROM task_submissions WHERE syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTareaId: Int = getColumnIndexOrThrow(_stmt, "tareaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSubmissionDate: Int = getColumnIndexOrThrow(_stmt, "submissionDate")
        val _columnIndexOfComment: Int = getColumnIndexOrThrow(_stmt, "comment")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfFeedback: Int = getColumnIndexOrThrow(_stmt, "feedback")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<EntregaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EntregaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTareaId: String
          _tmpTareaId = _stmt.getText(_columnIndexOfTareaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSubmissionDate: Long
          _tmpSubmissionDate = _stmt.getLong(_columnIndexOfSubmissionDate)
          val _tmpComment: String?
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment)
          }
          val _tmpGrade: Double?
          if (_stmt.isNull(_columnIndexOfGrade)) {
            _tmpGrade = null
          } else {
            _tmpGrade = _stmt.getDouble(_columnIndexOfGrade)
          }
          val _tmpFeedback: String?
          if (_stmt.isNull(_columnIndexOfFeedback)) {
            _tmpFeedback = null
          } else {
            _tmpFeedback = _stmt.getText(_columnIndexOfFeedback)
          }
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              EntregaEntity(_tmpId,_tmpTareaId,_tmpEstudianteId,_tmpStatus,_tmpSubmissionDate,_tmpComment,_tmpGrade,_tmpFeedback,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String) {
    val _sql: String = "UPDATE task_submissions SET syncStatus = 0 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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

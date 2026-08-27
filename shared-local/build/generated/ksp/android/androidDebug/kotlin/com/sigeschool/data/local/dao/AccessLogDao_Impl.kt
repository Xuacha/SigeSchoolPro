package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AccessLogEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AccessLogDao_Impl(
  __db: RoomDatabase,
) : AccessLogDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAccessLogEntity: EntityInsertAdapter<AccessLogEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAccessLogEntity = object : EntityInsertAdapter<AccessLogEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `access_logs` (`id`,`institutionId`,`studentId`,`scannedByUserId`,`scannedByUserName`,`accessTime`,`tipo`,`result`,`reason`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AccessLogEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.scannedByUserId)
        statement.bindText(5, entity.scannedByUserName)
        statement.bindLong(6, entity.accessTime)
        statement.bindText(7, entity.tipo)
        statement.bindText(8, entity.result)
        val _tmpReason: String? = entity.reason
        if (_tmpReason == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpReason)
        }
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(log: AccessLogEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfAccessLogEntity.insertAndReturnId(_connection, log)
    _result
  }

  public override fun getByStudent(studentId: String, instId: String): Flow<List<AccessLogEntity>> {
    val _sql: String =
        "SELECT * FROM access_logs WHERE studentId = ? AND institutionId = ? ORDER BY accessTime DESC"
    return createFlow(__db, false, arrayOf("access_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfScannedByUserId: Int = getColumnIndexOrThrow(_stmt, "scannedByUserId")
        val _columnIndexOfScannedByUserName: Int = getColumnIndexOrThrow(_stmt, "scannedByUserName")
        val _columnIndexOfAccessTime: Int = getColumnIndexOrThrow(_stmt, "accessTime")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResult: Int = getColumnIndexOrThrow(_stmt, "result")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AccessLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccessLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpScannedByUserId: String
          _tmpScannedByUserId = _stmt.getText(_columnIndexOfScannedByUserId)
          val _tmpScannedByUserName: String
          _tmpScannedByUserName = _stmt.getText(_columnIndexOfScannedByUserName)
          val _tmpAccessTime: Long
          _tmpAccessTime = _stmt.getLong(_columnIndexOfAccessTime)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResult: String
          _tmpResult = _stmt.getText(_columnIndexOfResult)
          val _tmpReason: String?
          if (_stmt.isNull(_columnIndexOfReason)) {
            _tmpReason = null
          } else {
            _tmpReason = _stmt.getText(_columnIndexOfReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AccessLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpScannedByUserId,_tmpScannedByUserName,_tmpAccessTime,_tmpTipo,_tmpResult,_tmpReason,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByStudentAndDateRange(
    studentId: String,
    startDate: Long,
    endDate: Long,
    instId: String,
  ): List<AccessLogEntity> {
    val _sql: String =
        "SELECT * FROM access_logs WHERE studentId = ? AND accessTime >= ? AND accessTime <= ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, startDate)
        _argIndex = 3
        _stmt.bindLong(_argIndex, endDate)
        _argIndex = 4
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfScannedByUserId: Int = getColumnIndexOrThrow(_stmt, "scannedByUserId")
        val _columnIndexOfScannedByUserName: Int = getColumnIndexOrThrow(_stmt, "scannedByUserName")
        val _columnIndexOfAccessTime: Int = getColumnIndexOrThrow(_stmt, "accessTime")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResult: Int = getColumnIndexOrThrow(_stmt, "result")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AccessLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccessLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpScannedByUserId: String
          _tmpScannedByUserId = _stmt.getText(_columnIndexOfScannedByUserId)
          val _tmpScannedByUserName: String
          _tmpScannedByUserName = _stmt.getText(_columnIndexOfScannedByUserName)
          val _tmpAccessTime: Long
          _tmpAccessTime = _stmt.getLong(_columnIndexOfAccessTime)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResult: String
          _tmpResult = _stmt.getText(_columnIndexOfResult)
          val _tmpReason: String?
          if (_stmt.isNull(_columnIndexOfReason)) {
            _tmpReason = null
          } else {
            _tmpReason = _stmt.getText(_columnIndexOfReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AccessLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpScannedByUserId,_tmpScannedByUserName,_tmpAccessTime,_tmpTipo,_tmpResult,_tmpReason,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRecent(instId: String, limit: Int): Flow<List<AccessLogEntity>> {
    val _sql: String =
        "SELECT * FROM access_logs WHERE institutionId = ? ORDER BY accessTime DESC LIMIT ?"
    return createFlow(__db, false, arrayOf("access_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfScannedByUserId: Int = getColumnIndexOrThrow(_stmt, "scannedByUserId")
        val _columnIndexOfScannedByUserName: Int = getColumnIndexOrThrow(_stmt, "scannedByUserName")
        val _columnIndexOfAccessTime: Int = getColumnIndexOrThrow(_stmt, "accessTime")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResult: Int = getColumnIndexOrThrow(_stmt, "result")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AccessLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccessLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpScannedByUserId: String
          _tmpScannedByUserId = _stmt.getText(_columnIndexOfScannedByUserId)
          val _tmpScannedByUserName: String
          _tmpScannedByUserName = _stmt.getText(_columnIndexOfScannedByUserName)
          val _tmpAccessTime: Long
          _tmpAccessTime = _stmt.getLong(_columnIndexOfAccessTime)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResult: String
          _tmpResult = _stmt.getText(_columnIndexOfResult)
          val _tmpReason: String?
          if (_stmt.isNull(_columnIndexOfReason)) {
            _tmpReason = null
          } else {
            _tmpReason = _stmt.getText(_columnIndexOfReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AccessLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpScannedByUserId,_tmpScannedByUserName,_tmpAccessTime,_tmpTipo,_tmpResult,_tmpReason,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRejected(instId: String): Flow<List<AccessLogEntity>> {
    val _sql: String =
        "SELECT * FROM access_logs WHERE institutionId = ? AND result = 'RECHAZADO' ORDER BY accessTime DESC"
    return createFlow(__db, false, arrayOf("access_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfScannedByUserId: Int = getColumnIndexOrThrow(_stmt, "scannedByUserId")
        val _columnIndexOfScannedByUserName: Int = getColumnIndexOrThrow(_stmt, "scannedByUserName")
        val _columnIndexOfAccessTime: Int = getColumnIndexOrThrow(_stmt, "accessTime")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResult: Int = getColumnIndexOrThrow(_stmt, "result")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AccessLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccessLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpScannedByUserId: String
          _tmpScannedByUserId = _stmt.getText(_columnIndexOfScannedByUserId)
          val _tmpScannedByUserName: String
          _tmpScannedByUserName = _stmt.getText(_columnIndexOfScannedByUserName)
          val _tmpAccessTime: Long
          _tmpAccessTime = _stmt.getLong(_columnIndexOfAccessTime)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResult: String
          _tmpResult = _stmt.getText(_columnIndexOfResult)
          val _tmpReason: String?
          if (_stmt.isNull(_columnIndexOfReason)) {
            _tmpReason = null
          } else {
            _tmpReason = _stmt.getText(_columnIndexOfReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AccessLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpScannedByUserId,_tmpScannedByUserName,_tmpAccessTime,_tmpTipo,_tmpResult,_tmpReason,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<AccessLogEntity> {
    val _sql: String = "SELECT * FROM access_logs WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfScannedByUserId: Int = getColumnIndexOrThrow(_stmt, "scannedByUserId")
        val _columnIndexOfScannedByUserName: Int = getColumnIndexOrThrow(_stmt, "scannedByUserName")
        val _columnIndexOfAccessTime: Int = getColumnIndexOrThrow(_stmt, "accessTime")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResult: Int = getColumnIndexOrThrow(_stmt, "result")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AccessLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccessLogEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpScannedByUserId: String
          _tmpScannedByUserId = _stmt.getText(_columnIndexOfScannedByUserId)
          val _tmpScannedByUserName: String
          _tmpScannedByUserName = _stmt.getText(_columnIndexOfScannedByUserName)
          val _tmpAccessTime: Long
          _tmpAccessTime = _stmt.getLong(_columnIndexOfAccessTime)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResult: String
          _tmpResult = _stmt.getText(_columnIndexOfResult)
          val _tmpReason: String?
          if (_stmt.isNull(_columnIndexOfReason)) {
            _tmpReason = null
          } else {
            _tmpReason = _stmt.getText(_columnIndexOfReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AccessLogEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpScannedByUserId,_tmpScannedByUserName,_tmpAccessTime,_tmpTipo,_tmpResult,_tmpReason,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE access_logs SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

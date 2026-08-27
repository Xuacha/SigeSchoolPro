package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.UserApprovalEntity
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
public class UserApprovalDao_Impl(
  __db: RoomDatabase,
) : UserApprovalDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUserApprovalEntity: EntityInsertAdapter<UserApprovalEntity>

  private val __updateAdapterOfUserApprovalEntity: EntityDeleteOrUpdateAdapter<UserApprovalEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfUserApprovalEntity = object : EntityInsertAdapter<UserApprovalEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `user_approvals` (`id`,`institutionId`,`userId`,`status`,`requestedAt`,`approvedAt`,`approvedByUserId`,`rejectedReason`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: UserApprovalEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.userId)
        statement.bindText(4, entity.status)
        statement.bindLong(5, entity.requestedAt)
        val _tmpApprovedAt: Long? = entity.approvedAt
        if (_tmpApprovedAt == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpApprovedAt)
        }
        val _tmpApprovedByUserId: String? = entity.approvedByUserId
        if (_tmpApprovedByUserId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpApprovedByUserId)
        }
        val _tmpRejectedReason: String? = entity.rejectedReason
        if (_tmpRejectedReason == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpRejectedReason)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfUserApprovalEntity = object :
        EntityDeleteOrUpdateAdapter<UserApprovalEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `user_approvals` SET `id` = ?,`institutionId` = ?,`userId` = ?,`status` = ?,`requestedAt` = ?,`approvedAt` = ?,`approvedByUserId` = ?,`rejectedReason` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: UserApprovalEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.userId)
        statement.bindText(4, entity.status)
        statement.bindLong(5, entity.requestedAt)
        val _tmpApprovedAt: Long? = entity.approvedAt
        if (_tmpApprovedAt == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpApprovedAt)
        }
        val _tmpApprovedByUserId: String? = entity.approvedByUserId
        if (_tmpApprovedByUserId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpApprovedByUserId)
        }
        val _tmpRejectedReason: String? = entity.rejectedReason
        if (_tmpRejectedReason == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpRejectedReason)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindLong(11, entity.id)
      }
    }
  }

  public override suspend fun insert(approval: UserApprovalEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfUserApprovalEntity.insertAndReturnId(_connection, approval)
    _result
  }

  public override suspend fun update(approval: UserApprovalEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfUserApprovalEntity.handle(_connection, approval)
  }

  public override fun getPendingByInstitution(instId: String): Flow<List<UserApprovalEntity>> {
    val _sql: String =
        "SELECT * FROM user_approvals WHERE institutionId = ? AND status = 'PENDIENTE' ORDER BY requestedAt DESC"
    return createFlow(__db, false, arrayOf("user_approvals")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfRequestedAt: Int = getColumnIndexOrThrow(_stmt, "requestedAt")
        val _columnIndexOfApprovedAt: Int = getColumnIndexOrThrow(_stmt, "approvedAt")
        val _columnIndexOfApprovedByUserId: Int = getColumnIndexOrThrow(_stmt, "approvedByUserId")
        val _columnIndexOfRejectedReason: Int = getColumnIndexOrThrow(_stmt, "rejectedReason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<UserApprovalEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserApprovalEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpRequestedAt: Long
          _tmpRequestedAt = _stmt.getLong(_columnIndexOfRequestedAt)
          val _tmpApprovedAt: Long?
          if (_stmt.isNull(_columnIndexOfApprovedAt)) {
            _tmpApprovedAt = null
          } else {
            _tmpApprovedAt = _stmt.getLong(_columnIndexOfApprovedAt)
          }
          val _tmpApprovedByUserId: String?
          if (_stmt.isNull(_columnIndexOfApprovedByUserId)) {
            _tmpApprovedByUserId = null
          } else {
            _tmpApprovedByUserId = _stmt.getText(_columnIndexOfApprovedByUserId)
          }
          val _tmpRejectedReason: String?
          if (_stmt.isNull(_columnIndexOfRejectedReason)) {
            _tmpRejectedReason = null
          } else {
            _tmpRejectedReason = _stmt.getText(_columnIndexOfRejectedReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              UserApprovalEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpApprovedByUserId,_tmpRejectedReason,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByUserId(userId: String): UserApprovalEntity? {
    val _sql: String = "SELECT * FROM user_approvals WHERE userId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfRequestedAt: Int = getColumnIndexOrThrow(_stmt, "requestedAt")
        val _columnIndexOfApprovedAt: Int = getColumnIndexOrThrow(_stmt, "approvedAt")
        val _columnIndexOfApprovedByUserId: Int = getColumnIndexOrThrow(_stmt, "approvedByUserId")
        val _columnIndexOfRejectedReason: Int = getColumnIndexOrThrow(_stmt, "rejectedReason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: UserApprovalEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpRequestedAt: Long
          _tmpRequestedAt = _stmt.getLong(_columnIndexOfRequestedAt)
          val _tmpApprovedAt: Long?
          if (_stmt.isNull(_columnIndexOfApprovedAt)) {
            _tmpApprovedAt = null
          } else {
            _tmpApprovedAt = _stmt.getLong(_columnIndexOfApprovedAt)
          }
          val _tmpApprovedByUserId: String?
          if (_stmt.isNull(_columnIndexOfApprovedByUserId)) {
            _tmpApprovedByUserId = null
          } else {
            _tmpApprovedByUserId = _stmt.getText(_columnIndexOfApprovedByUserId)
          }
          val _tmpRejectedReason: String?
          if (_stmt.isNull(_columnIndexOfRejectedReason)) {
            _tmpRejectedReason = null
          } else {
            _tmpRejectedReason = _stmt.getText(_columnIndexOfRejectedReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              UserApprovalEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpApprovedByUserId,_tmpRejectedReason,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<UserApprovalEntity> {
    val _sql: String = "SELECT * FROM user_approvals WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfRequestedAt: Int = getColumnIndexOrThrow(_stmt, "requestedAt")
        val _columnIndexOfApprovedAt: Int = getColumnIndexOrThrow(_stmt, "approvedAt")
        val _columnIndexOfApprovedByUserId: Int = getColumnIndexOrThrow(_stmt, "approvedByUserId")
        val _columnIndexOfRejectedReason: Int = getColumnIndexOrThrow(_stmt, "rejectedReason")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<UserApprovalEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserApprovalEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpRequestedAt: Long
          _tmpRequestedAt = _stmt.getLong(_columnIndexOfRequestedAt)
          val _tmpApprovedAt: Long?
          if (_stmt.isNull(_columnIndexOfApprovedAt)) {
            _tmpApprovedAt = null
          } else {
            _tmpApprovedAt = _stmt.getLong(_columnIndexOfApprovedAt)
          }
          val _tmpApprovedByUserId: String?
          if (_stmt.isNull(_columnIndexOfApprovedByUserId)) {
            _tmpApprovedByUserId = null
          } else {
            _tmpApprovedByUserId = _stmt.getText(_columnIndexOfApprovedByUserId)
          }
          val _tmpRejectedReason: String?
          if (_stmt.isNull(_columnIndexOfRejectedReason)) {
            _tmpRejectedReason = null
          } else {
            _tmpRejectedReason = _stmt.getText(_columnIndexOfRejectedReason)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              UserApprovalEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpApprovedByUserId,_tmpRejectedReason,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE user_approvals SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

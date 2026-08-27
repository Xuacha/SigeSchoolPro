package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AuditLogEntity
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
public class AuditLogDao_Impl(
  __db: RoomDatabase,
) : AuditLogDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAuditLogEntity: EntityInsertAdapter<AuditLogEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAuditLogEntity = object : EntityInsertAdapter<AuditLogEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `audit_logs` (`id`,`institutionId`,`userId`,`userName`,`userRole`,`action`,`entityName`,`entityId`,`details`,`timestamp`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AuditLogEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.userId)
        statement.bindText(4, entity.userName)
        statement.bindText(5, entity.userRole)
        statement.bindText(6, entity.action)
        statement.bindText(7, entity.entityName)
        statement.bindText(8, entity.entityId)
        statement.bindText(9, entity.details)
        statement.bindLong(10, entity.timestamp)
      }
    }
  }

  public override suspend fun insert(log: AuditLogEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfAuditLogEntity.insert(_connection, log)
  }

  public override fun getLogsByInstitution(institutionId: String): Flow<List<AuditLogEntity>> {
    val _sql: String = "SELECT * FROM audit_logs WHERE institutionId = ? ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("audit_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfUserRole: Int = getColumnIndexOrThrow(_stmt, "userRole")
        val _columnIndexOfAction: Int = getColumnIndexOrThrow(_stmt, "action")
        val _columnIndexOfEntityName: Int = getColumnIndexOrThrow(_stmt, "entityName")
        val _columnIndexOfEntityId: Int = getColumnIndexOrThrow(_stmt, "entityId")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _result: MutableList<AuditLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AuditLogEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpUserName: String
          _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          val _tmpUserRole: String
          _tmpUserRole = _stmt.getText(_columnIndexOfUserRole)
          val _tmpAction: String
          _tmpAction = _stmt.getText(_columnIndexOfAction)
          val _tmpEntityName: String
          _tmpEntityName = _stmt.getText(_columnIndexOfEntityName)
          val _tmpEntityId: String
          _tmpEntityId = _stmt.getText(_columnIndexOfEntityId)
          val _tmpDetails: String
          _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          _item =
              AuditLogEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpUserName,_tmpUserRole,_tmpAction,_tmpEntityName,_tmpEntityId,_tmpDetails,_tmpTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLogsByUser(userId: String, institutionId: String):
      Flow<List<AuditLogEntity>> {
    val _sql: String =
        "SELECT * FROM audit_logs WHERE userId = ? AND institutionId = ? ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("audit_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfUserRole: Int = getColumnIndexOrThrow(_stmt, "userRole")
        val _columnIndexOfAction: Int = getColumnIndexOrThrow(_stmt, "action")
        val _columnIndexOfEntityName: Int = getColumnIndexOrThrow(_stmt, "entityName")
        val _columnIndexOfEntityId: Int = getColumnIndexOrThrow(_stmt, "entityId")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _result: MutableList<AuditLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AuditLogEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpUserName: String
          _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          val _tmpUserRole: String
          _tmpUserRole = _stmt.getText(_columnIndexOfUserRole)
          val _tmpAction: String
          _tmpAction = _stmt.getText(_columnIndexOfAction)
          val _tmpEntityName: String
          _tmpEntityName = _stmt.getText(_columnIndexOfEntityName)
          val _tmpEntityId: String
          _tmpEntityId = _stmt.getText(_columnIndexOfEntityId)
          val _tmpDetails: String
          _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          _item =
              AuditLogEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpUserName,_tmpUserRole,_tmpAction,_tmpEntityName,_tmpEntityId,_tmpDetails,_tmpTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteOldLogs(timestamp: Long, institutionId: String) {
    val _sql: String = "DELETE FROM audit_logs WHERE timestamp < ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
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

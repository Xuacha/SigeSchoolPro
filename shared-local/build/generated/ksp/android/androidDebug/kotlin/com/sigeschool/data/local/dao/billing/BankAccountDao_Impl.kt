package com.sigeschool.`data`.local.dao.billing

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.billing.BankAccountEntity
import com.sigeschool.`data`.local.entity.billing.BankAccountHistoryEntity
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
public class BankAccountDao_Impl(
  __db: RoomDatabase,
) : BankAccountDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBankAccountEntity: EntityInsertAdapter<BankAccountEntity>

  private val __insertAdapterOfBankAccountHistoryEntity:
      EntityInsertAdapter<BankAccountHistoryEntity>

  private val __updateAdapterOfBankAccountEntity: EntityDeleteOrUpdateAdapter<BankAccountEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfBankAccountEntity = object : EntityInsertAdapter<BankAccountEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `bank_accounts` (`id`,`institutionId`,`bankName`,`accountType`,`accountNumber`,`holderName`,`holderDni`,`notificationEmail`,`status`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BankAccountEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.bankName)
        statement.bindText(4, entity.accountType)
        statement.bindText(5, entity.accountNumber)
        statement.bindText(6, entity.holderName)
        statement.bindText(7, entity.holderDni)
        val _tmpNotificationEmail: String? = entity.notificationEmail
        if (_tmpNotificationEmail == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpNotificationEmail)
        }
        statement.bindText(9, entity.status)
        val _tmpCreatedAt: Long? = entity.createdAt
        if (_tmpCreatedAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpCreatedAt)
        }
        val _tmpUpdatedAt: Long? = entity.updatedAt
        if (_tmpUpdatedAt == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpUpdatedAt)
        }
      }
    }
    this.__insertAdapterOfBankAccountHistoryEntity = object :
        EntityInsertAdapter<BankAccountHistoryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `bank_account_history` (`id`,`accountId`,`userId`,`action`,`previousData`,`createdAt`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BankAccountHistoryEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.accountId)
        statement.bindText(3, entity.userId)
        statement.bindText(4, entity.action)
        val _tmpPreviousData: String? = entity.previousData
        if (_tmpPreviousData == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpPreviousData)
        }
        statement.bindLong(6, entity.createdAt)
      }
    }
    this.__updateAdapterOfBankAccountEntity = object :
        EntityDeleteOrUpdateAdapter<BankAccountEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `bank_accounts` SET `id` = ?,`institutionId` = ?,`bankName` = ?,`accountType` = ?,`accountNumber` = ?,`holderName` = ?,`holderDni` = ?,`notificationEmail` = ?,`status` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BankAccountEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.bankName)
        statement.bindText(4, entity.accountType)
        statement.bindText(5, entity.accountNumber)
        statement.bindText(6, entity.holderName)
        statement.bindText(7, entity.holderDni)
        val _tmpNotificationEmail: String? = entity.notificationEmail
        if (_tmpNotificationEmail == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpNotificationEmail)
        }
        statement.bindText(9, entity.status)
        val _tmpCreatedAt: Long? = entity.createdAt
        if (_tmpCreatedAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpCreatedAt)
        }
        val _tmpUpdatedAt: Long? = entity.updatedAt
        if (_tmpUpdatedAt == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpUpdatedAt)
        }
        statement.bindText(12, entity.id)
      }
    }
  }

  public override suspend fun insertAccount(account: BankAccountEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBankAccountEntity.insert(_connection, account)
  }

  public override suspend fun insertHistory(history: BankAccountHistoryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBankAccountHistoryEntity.insert(_connection, history)
  }

  public override suspend fun updateAccount(account: BankAccountEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfBankAccountEntity.handle(_connection, account)
  }

  public override fun getAccountByInstitution(institutionId: String): Flow<BankAccountEntity?> {
    val _sql: String =
        "SELECT * FROM bank_accounts WHERE institutionId = ? AND status = 'ACTIVA' LIMIT 1"
    return createFlow(__db, false, arrayOf("bank_accounts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfBankName: Int = getColumnIndexOrThrow(_stmt, "bankName")
        val _columnIndexOfAccountType: Int = getColumnIndexOrThrow(_stmt, "accountType")
        val _columnIndexOfAccountNumber: Int = getColumnIndexOrThrow(_stmt, "accountNumber")
        val _columnIndexOfHolderName: Int = getColumnIndexOrThrow(_stmt, "holderName")
        val _columnIndexOfHolderDni: Int = getColumnIndexOrThrow(_stmt, "holderDni")
        val _columnIndexOfNotificationEmail: Int = getColumnIndexOrThrow(_stmt, "notificationEmail")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: BankAccountEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpBankName: String
          _tmpBankName = _stmt.getText(_columnIndexOfBankName)
          val _tmpAccountType: String
          _tmpAccountType = _stmt.getText(_columnIndexOfAccountType)
          val _tmpAccountNumber: String
          _tmpAccountNumber = _stmt.getText(_columnIndexOfAccountNumber)
          val _tmpHolderName: String
          _tmpHolderName = _stmt.getText(_columnIndexOfHolderName)
          val _tmpHolderDni: String
          _tmpHolderDni = _stmt.getText(_columnIndexOfHolderDni)
          val _tmpNotificationEmail: String?
          if (_stmt.isNull(_columnIndexOfNotificationEmail)) {
            _tmpNotificationEmail = null
          } else {
            _tmpNotificationEmail = _stmt.getText(_columnIndexOfNotificationEmail)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpCreatedAt: Long?
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmpCreatedAt = null
          } else {
            _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          }
          val _tmpUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmpUpdatedAt = null
          } else {
            _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          }
          _result =
              BankAccountEntity(_tmpId,_tmpInstitutionId,_tmpBankName,_tmpAccountType,_tmpAccountNumber,_tmpHolderName,_tmpHolderDni,_tmpNotificationEmail,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getHistory(accountId: String): Flow<List<BankAccountHistoryEntity>> {
    val _sql: String =
        "SELECT * FROM bank_account_history WHERE accountId = ? ORDER BY createdAt DESC"
    return createFlow(__db, false, arrayOf("bank_account_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, accountId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfAction: Int = getColumnIndexOrThrow(_stmt, "action")
        val _columnIndexOfPreviousData: Int = getColumnIndexOrThrow(_stmt, "previousData")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<BankAccountHistoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BankAccountHistoryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAccountId: String
          _tmpAccountId = _stmt.getText(_columnIndexOfAccountId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpAction: String
          _tmpAction = _stmt.getText(_columnIndexOfAction)
          val _tmpPreviousData: String?
          if (_stmt.isNull(_columnIndexOfPreviousData)) {
            _tmpPreviousData = null
          } else {
            _tmpPreviousData = _stmt.getText(_columnIndexOfPreviousData)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item =
              BankAccountHistoryEntity(_tmpId,_tmpAccountId,_tmpUserId,_tmpAction,_tmpPreviousData,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deactivateAccount(accountId: String) {
    val _sql: String = "UPDATE bank_accounts SET status = 'INACTIVA' WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, accountId)
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

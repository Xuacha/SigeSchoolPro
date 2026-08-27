package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.CashTransactionEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class CashDao_Impl(
  __db: RoomDatabase,
) : CashDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCashTransactionEntity: EntityInsertAdapter<CashTransactionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCashTransactionEntity = object :
        EntityInsertAdapter<CashTransactionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `cash_transactions` (`id`,`institutionId`,`type`,`concept`,`category`,`amount`,`paymentMethod`,`personName`,`reference`,`timestamp`,`observations`,`registradoPorId`,`isSynced`,`version`,`deviceId`,`lastModified`,`syncStatus`,`syncAttempts`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CashTransactionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.type)
        statement.bindText(4, entity.concept)
        statement.bindText(5, entity.category)
        statement.bindDouble(6, entity.amount)
        statement.bindText(7, entity.paymentMethod)
        val _tmpPersonName: String? = entity.personName
        if (_tmpPersonName == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpPersonName)
        }
        val _tmpReference: String? = entity.reference
        if (_tmpReference == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpReference)
        }
        statement.bindLong(10, entity.timestamp)
        val _tmpObservations: String? = entity.observations
        if (_tmpObservations == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpObservations)
        }
        statement.bindText(12, entity.registradoPorId)
        val _tmp: Int = if (entity.isSynced) 1 else 0
        statement.bindLong(13, _tmp.toLong())
        statement.bindLong(14, entity.version)
        statement.bindText(15, entity.deviceId)
        statement.bindLong(16, entity.lastModified)
        statement.bindLong(17, entity.syncStatus.toLong())
        statement.bindLong(18, entity.syncAttempts.toLong())
      }
    }
  }

  public override suspend fun insertTransaction(transaction: CashTransactionEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCashTransactionEntity.insert(_connection, transaction)
  }

  public override fun getTransactionsByRange(
    institutionId: String,
    start: Long,
    end: Long,
  ): Flow<List<CashTransactionEntity>> {
    val _sql: String =
        "SELECT * FROM cash_transactions WHERE institutionId = ? AND timestamp BETWEEN ? AND ? ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("cash_transactions")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, start)
        _argIndex = 3
        _stmt.bindLong(_argIndex, end)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfPaymentMethod: Int = getColumnIndexOrThrow(_stmt, "paymentMethod")
        val _columnIndexOfPersonName: Int = getColumnIndexOrThrow(_stmt, "personName")
        val _columnIndexOfReference: Int = getColumnIndexOrThrow(_stmt, "reference")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfRegistradoPorId: Int = getColumnIndexOrThrow(_stmt, "registradoPorId")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: MutableList<CashTransactionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CashTransactionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpPaymentMethod: String
          _tmpPaymentMethod = _stmt.getText(_columnIndexOfPaymentMethod)
          val _tmpPersonName: String?
          if (_stmt.isNull(_columnIndexOfPersonName)) {
            _tmpPersonName = null
          } else {
            _tmpPersonName = _stmt.getText(_columnIndexOfPersonName)
          }
          val _tmpReference: String?
          if (_stmt.isNull(_columnIndexOfReference)) {
            _tmpReference = null
          } else {
            _tmpReference = _stmt.getText(_columnIndexOfReference)
          }
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpObservations: String?
          if (_stmt.isNull(_columnIndexOfObservations)) {
            _tmpObservations = null
          } else {
            _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          }
          val _tmpRegistradoPorId: String
          _tmpRegistradoPorId = _stmt.getText(_columnIndexOfRegistradoPorId)
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          val _tmpDeviceId: String
          _tmpDeviceId = _stmt.getText(_columnIndexOfDeviceId)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpSyncAttempts: Int
          _tmpSyncAttempts = _stmt.getLong(_columnIndexOfSyncAttempts).toInt()
          _item =
              CashTransactionEntity(_tmpId,_tmpInstitutionId,_tmpType,_tmpConcept,_tmpCategory,_tmpAmount,_tmpPaymentMethod,_tmpPersonName,_tmpReference,_tmpTimestamp,_tmpObservations,_tmpRegistradoPorId,_tmpIsSynced,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalIncomes(
    institutionId: String,
    start: Long,
    end: Long,
  ): Flow<Double?> {
    val _sql: String =
        "SELECT SUM(amount) FROM cash_transactions WHERE institutionId = ? AND type = 'INCOME' AND timestamp BETWEEN ? AND ?"
    return createFlow(__db, false, arrayOf("cash_transactions")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, start)
        _argIndex = 3
        _stmt.bindLong(_argIndex, end)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalExpenses(
    institutionId: String,
    start: Long,
    end: Long,
  ): Flow<Double?> {
    val _sql: String =
        "SELECT SUM(amount) FROM cash_transactions WHERE institutionId = ? AND type = 'EXPENSE' AND timestamp BETWEEN ? AND ?"
    return createFlow(__db, false, arrayOf("cash_transactions")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, start)
        _argIndex = 3
        _stmt.bindLong(_argIndex, end)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
          }
          _result = _tmp
        } else {
          _result = null
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

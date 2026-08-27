package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PaymentEntity
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
public class PaymentDao_Impl(
  __db: RoomDatabase,
) : PaymentDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPaymentEntity: EntityInsertAdapter<PaymentEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPaymentEntity = object : EntityInsertAdapter<PaymentEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `payments` (`id`,`institutionId`,`studentId`,`amount`,`date`,`concept`,`paymentMethod`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PaymentEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindDouble(4, entity.amount)
        statement.bindLong(5, entity.date)
        statement.bindText(6, entity.concept)
        statement.bindText(7, entity.paymentMethod)
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
  }

  public override suspend fun insertPayment(payment: PaymentEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfPaymentEntity.insert(_connection, payment)
  }

  public override fun getPaymentsByStudent(studentId: String, institutionId: String):
      Flow<List<PaymentEntity>> {
    val _sql: String =
        "SELECT * FROM payments WHERE studentId = ? AND institutionId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("payments")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfPaymentMethod: Int = getColumnIndexOrThrow(_stmt, "paymentMethod")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PaymentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PaymentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpPaymentMethod: String
          _tmpPaymentMethod = _stmt.getText(_columnIndexOfPaymentMethod)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PaymentEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpAmount,_tmpDate,_tmpConcept,_tmpPaymentMethod,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllPayments(institutionId: String): Flow<List<PaymentEntity>> {
    val _sql: String = "SELECT * FROM payments WHERE institutionId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("payments")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfPaymentMethod: Int = getColumnIndexOrThrow(_stmt, "paymentMethod")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PaymentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PaymentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpPaymentMethod: String
          _tmpPaymentMethod = _stmt.getText(_columnIndexOfPaymentMethod)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PaymentEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpAmount,_tmpDate,_tmpConcept,_tmpPaymentMethod,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllPaymentsSync(institutionId: String): List<PaymentEntity> {
    val _sql: String = "SELECT * FROM payments WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfPaymentMethod: Int = getColumnIndexOrThrow(_stmt, "paymentMethod")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PaymentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PaymentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpPaymentMethod: String
          _tmpPaymentMethod = _stmt.getText(_columnIndexOfPaymentMethod)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PaymentEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpAmount,_tmpDate,_tmpConcept,_tmpPaymentMethod,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTotalRevenue(institutionId: String): Double? {
    val _sql: String = "SELECT SUM(amount) FROM payments WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
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

  public override suspend fun getPendingSyncPayments(institutionId: String): List<PaymentEntity> {
    val _sql: String = "SELECT * FROM payments WHERE syncStatus != 0 AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfPaymentMethod: Int = getColumnIndexOrThrow(_stmt, "paymentMethod")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PaymentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PaymentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpPaymentMethod: String
          _tmpPaymentMethod = _stmt.getText(_columnIndexOfPaymentMethod)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PaymentEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpAmount,_tmpDate,_tmpConcept,_tmpPaymentMethod,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, institutionId: String) {
    val _sql: String = "UPDATE payments SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

  public override suspend fun deletePayment(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM payments WHERE id = ? AND institutionId = ?"
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

  public override suspend fun migrateStudentPayments(
    sourceStudentId: String,
    targetStudentId: String,
    institutionId: String,
  ) {
    val _sql: String =
        "UPDATE payments SET studentId = ?, syncStatus = 2 WHERE studentId = ? AND institutionId = ?"
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

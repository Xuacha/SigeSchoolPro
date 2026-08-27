package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.SalaryEntity
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
public class SalaryDao_Impl(
  __db: RoomDatabase,
) : SalaryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSalaryEntity: EntityInsertAdapter<SalaryEntity>

  private val __deleteAdapterOfSalaryEntity: EntityDeleteOrUpdateAdapter<SalaryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSalaryEntity = object : EntityInsertAdapter<SalaryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `salaries` (`id`,`employeeId`,`institutionId`,`amount`,`concept`,`paymentDate`,`periodMonth`,`periodYear`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SalaryEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.employeeId)
        statement.bindText(3, entity.institutionId)
        statement.bindDouble(4, entity.amount)
        statement.bindText(5, entity.concept)
        statement.bindLong(6, entity.paymentDate)
        statement.bindLong(7, entity.periodMonth.toLong())
        statement.bindLong(8, entity.periodYear.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__deleteAdapterOfSalaryEntity = object : EntityDeleteOrUpdateAdapter<SalaryEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `salaries` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SalaryEntity) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertSalary(salary: SalaryEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfSalaryEntity.insert(_connection, salary)
  }

  public override suspend fun deleteSalary(salary: SalaryEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfSalaryEntity.handle(_connection, salary)
  }

  public override fun getAllSalaries(institutionId: String): Flow<List<SalaryEntity>> {
    val _sql: String = "SELECT * FROM salaries WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("salaries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfPaymentDate: Int = getColumnIndexOrThrow(_stmt, "paymentDate")
        val _columnIndexOfPeriodMonth: Int = getColumnIndexOrThrow(_stmt, "periodMonth")
        val _columnIndexOfPeriodYear: Int = getColumnIndexOrThrow(_stmt, "periodYear")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<SalaryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SalaryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpPaymentDate: Long
          _tmpPaymentDate = _stmt.getLong(_columnIndexOfPaymentDate)
          val _tmpPeriodMonth: Int
          _tmpPeriodMonth = _stmt.getLong(_columnIndexOfPeriodMonth).toInt()
          val _tmpPeriodYear: Int
          _tmpPeriodYear = _stmt.getLong(_columnIndexOfPeriodYear).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              SalaryEntity(_tmpId,_tmpEmployeeId,_tmpInstitutionId,_tmpAmount,_tmpConcept,_tmpPaymentDate,_tmpPeriodMonth,_tmpPeriodYear,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSalariesByEmployee(employeeId: String, institutionId: String):
      Flow<List<SalaryEntity>> {
    val _sql: String = "SELECT * FROM salaries WHERE employeeId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("salaries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, employeeId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfPaymentDate: Int = getColumnIndexOrThrow(_stmt, "paymentDate")
        val _columnIndexOfPeriodMonth: Int = getColumnIndexOrThrow(_stmt, "periodMonth")
        val _columnIndexOfPeriodYear: Int = getColumnIndexOrThrow(_stmt, "periodYear")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<SalaryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SalaryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpPaymentDate: Long
          _tmpPaymentDate = _stmt.getLong(_columnIndexOfPaymentDate)
          val _tmpPeriodMonth: Int
          _tmpPeriodMonth = _stmt.getLong(_columnIndexOfPeriodMonth).toInt()
          val _tmpPeriodYear: Int
          _tmpPeriodYear = _stmt.getLong(_columnIndexOfPeriodYear).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              SalaryEntity(_tmpId,_tmpEmployeeId,_tmpInstitutionId,_tmpAmount,_tmpConcept,_tmpPaymentDate,_tmpPeriodMonth,_tmpPeriodYear,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteSalaryById(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM salaries WHERE id = ? AND institutionId = ?"
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

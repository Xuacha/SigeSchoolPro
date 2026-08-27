package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AdvanceRequestEntity
import com.sigeschool.`data`.local.entity.PayrollCalculationEntity
import com.sigeschool.`data`.local.entity.VacationRequestEntity
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
public class LaboralDao_Impl(
  __db: RoomDatabase,
) : LaboralDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfVacationRequestEntity: EntityInsertAdapter<VacationRequestEntity>

  private val __insertAdapterOfAdvanceRequestEntity: EntityInsertAdapter<AdvanceRequestEntity>

  private val __insertAdapterOfPayrollCalculationEntity:
      EntityInsertAdapter<PayrollCalculationEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfVacationRequestEntity = object :
        EntityInsertAdapter<VacationRequestEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `vacation_requests` (`id`,`employeeId`,`startDate`,`endDate`,`days`,`status`,`observations`,`sincronizado`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: VacationRequestEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.employeeId)
        statement.bindLong(3, entity.startDate)
        statement.bindLong(4, entity.endDate)
        statement.bindLong(5, entity.days.toLong())
        statement.bindText(6, entity.status)
        statement.bindText(7, entity.observations)
        val _tmp: Int = if (entity.sincronizado) 1 else 0
        statement.bindLong(8, _tmp.toLong())
      }
    }
    this.__insertAdapterOfAdvanceRequestEntity = object :
        EntityInsertAdapter<AdvanceRequestEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `advance_requests` (`id`,`employeeId`,`amountRequested`,`reason`,`status`,`requestDate`,`maxAllowed`,`sincronizado`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AdvanceRequestEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.employeeId)
        statement.bindDouble(3, entity.amountRequested)
        statement.bindText(4, entity.reason)
        statement.bindText(5, entity.status)
        statement.bindLong(6, entity.requestDate)
        statement.bindDouble(7, entity.maxAllowed)
        val _tmp: Int = if (entity.sincronizado) 1 else 0
        statement.bindLong(8, _tmp.toLong())
      }
    }
    this.__insertAdapterOfPayrollCalculationEntity = object :
        EntityInsertAdapter<PayrollCalculationEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `payroll_calculations` (`id`,`employeeId`,`basicSalary`,`daysWorked`,`transportAllowance`,`healthDeduction`,`pensionDeduction`,`advances`,`extraHours`,`totalDevengado`,`totalDeducciones`,`netPay`,`date`,`sincronizado`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PayrollCalculationEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.employeeId)
        statement.bindText(3, entity.basicSalary)
        statement.bindLong(4, entity.daysWorked.toLong())
        statement.bindText(5, entity.transportAllowance)
        statement.bindText(6, entity.healthDeduction)
        statement.bindText(7, entity.pensionDeduction)
        statement.bindText(8, entity.advances)
        statement.bindText(9, entity.extraHours)
        statement.bindText(10, entity.totalDevengado)
        statement.bindText(11, entity.totalDeducciones)
        statement.bindText(12, entity.netPay)
        statement.bindLong(13, entity.date)
        val _tmp: Int = if (entity.sincronizado) 1 else 0
        statement.bindLong(14, _tmp.toLong())
      }
    }
  }

  public override suspend fun insertVacationRequest(request: VacationRequestEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfVacationRequestEntity.insert(_connection, request)
  }

  public override suspend fun insertAdvanceRequest(request: AdvanceRequestEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAdvanceRequestEntity.insert(_connection, request)
  }

  public override suspend fun insertPayrollCalculation(calculation: PayrollCalculationEntity): Unit
      = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPayrollCalculationEntity.insert(_connection, calculation)
  }

  public override fun getVacationRequests(employeeId: String): Flow<List<VacationRequestEntity>> {
    val _sql: String = "SELECT * FROM vacation_requests WHERE employeeId = ?"
    return createFlow(__db, false, arrayOf("vacation_requests")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, employeeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfStartDate: Int = getColumnIndexOrThrow(_stmt, "startDate")
        val _columnIndexOfEndDate: Int = getColumnIndexOrThrow(_stmt, "endDate")
        val _columnIndexOfDays: Int = getColumnIndexOrThrow(_stmt, "days")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<VacationRequestEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: VacationRequestEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpStartDate: Long
          _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate)
          val _tmpEndDate: Long
          _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate)
          val _tmpDays: Int
          _tmpDays = _stmt.getLong(_columnIndexOfDays).toInt()
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpObservations: String
          _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              VacationRequestEntity(_tmpId,_tmpEmployeeId,_tmpStartDate,_tmpEndDate,_tmpDays,_tmpStatus,_tmpObservations,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedVacations(): List<VacationRequestEntity> {
    val _sql: String = "SELECT * FROM vacation_requests WHERE sincronizado = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfStartDate: Int = getColumnIndexOrThrow(_stmt, "startDate")
        val _columnIndexOfEndDate: Int = getColumnIndexOrThrow(_stmt, "endDate")
        val _columnIndexOfDays: Int = getColumnIndexOrThrow(_stmt, "days")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<VacationRequestEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: VacationRequestEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpStartDate: Long
          _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate)
          val _tmpEndDate: Long
          _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate)
          val _tmpDays: Int
          _tmpDays = _stmt.getLong(_columnIndexOfDays).toInt()
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpObservations: String
          _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              VacationRequestEntity(_tmpId,_tmpEmployeeId,_tmpStartDate,_tmpEndDate,_tmpDays,_tmpStatus,_tmpObservations,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAdvanceRequests(employeeId: String): Flow<List<AdvanceRequestEntity>> {
    val _sql: String = "SELECT * FROM advance_requests WHERE employeeId = ?"
    return createFlow(__db, false, arrayOf("advance_requests")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, employeeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfAmountRequested: Int = getColumnIndexOrThrow(_stmt, "amountRequested")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfRequestDate: Int = getColumnIndexOrThrow(_stmt, "requestDate")
        val _columnIndexOfMaxAllowed: Int = getColumnIndexOrThrow(_stmt, "maxAllowed")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<AdvanceRequestEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AdvanceRequestEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpAmountRequested: Double
          _tmpAmountRequested = _stmt.getDouble(_columnIndexOfAmountRequested)
          val _tmpReason: String
          _tmpReason = _stmt.getText(_columnIndexOfReason)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpRequestDate: Long
          _tmpRequestDate = _stmt.getLong(_columnIndexOfRequestDate)
          val _tmpMaxAllowed: Double
          _tmpMaxAllowed = _stmt.getDouble(_columnIndexOfMaxAllowed)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              AdvanceRequestEntity(_tmpId,_tmpEmployeeId,_tmpAmountRequested,_tmpReason,_tmpStatus,_tmpRequestDate,_tmpMaxAllowed,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedAdvances(): List<AdvanceRequestEntity> {
    val _sql: String = "SELECT * FROM advance_requests WHERE sincronizado = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfAmountRequested: Int = getColumnIndexOrThrow(_stmt, "amountRequested")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfRequestDate: Int = getColumnIndexOrThrow(_stmt, "requestDate")
        val _columnIndexOfMaxAllowed: Int = getColumnIndexOrThrow(_stmt, "maxAllowed")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<AdvanceRequestEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AdvanceRequestEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpAmountRequested: Double
          _tmpAmountRequested = _stmt.getDouble(_columnIndexOfAmountRequested)
          val _tmpReason: String
          _tmpReason = _stmt.getText(_columnIndexOfReason)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpRequestDate: Long
          _tmpRequestDate = _stmt.getLong(_columnIndexOfRequestDate)
          val _tmpMaxAllowed: Double
          _tmpMaxAllowed = _stmt.getDouble(_columnIndexOfMaxAllowed)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              AdvanceRequestEntity(_tmpId,_tmpEmployeeId,_tmpAmountRequested,_tmpReason,_tmpStatus,_tmpRequestDate,_tmpMaxAllowed,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPayrollCalculations(employeeId: String):
      Flow<List<PayrollCalculationEntity>> {
    val _sql: String = "SELECT * FROM payroll_calculations WHERE employeeId = ?"
    return createFlow(__db, false, arrayOf("payroll_calculations")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, employeeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfBasicSalary: Int = getColumnIndexOrThrow(_stmt, "basicSalary")
        val _columnIndexOfDaysWorked: Int = getColumnIndexOrThrow(_stmt, "daysWorked")
        val _columnIndexOfTransportAllowance: Int = getColumnIndexOrThrow(_stmt,
            "transportAllowance")
        val _columnIndexOfHealthDeduction: Int = getColumnIndexOrThrow(_stmt, "healthDeduction")
        val _columnIndexOfPensionDeduction: Int = getColumnIndexOrThrow(_stmt, "pensionDeduction")
        val _columnIndexOfAdvances: Int = getColumnIndexOrThrow(_stmt, "advances")
        val _columnIndexOfExtraHours: Int = getColumnIndexOrThrow(_stmt, "extraHours")
        val _columnIndexOfTotalDevengado: Int = getColumnIndexOrThrow(_stmt, "totalDevengado")
        val _columnIndexOfTotalDeducciones: Int = getColumnIndexOrThrow(_stmt, "totalDeducciones")
        val _columnIndexOfNetPay: Int = getColumnIndexOrThrow(_stmt, "netPay")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<PayrollCalculationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PayrollCalculationEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpBasicSalary: String
          _tmpBasicSalary = _stmt.getText(_columnIndexOfBasicSalary)
          val _tmpDaysWorked: Int
          _tmpDaysWorked = _stmt.getLong(_columnIndexOfDaysWorked).toInt()
          val _tmpTransportAllowance: String
          _tmpTransportAllowance = _stmt.getText(_columnIndexOfTransportAllowance)
          val _tmpHealthDeduction: String
          _tmpHealthDeduction = _stmt.getText(_columnIndexOfHealthDeduction)
          val _tmpPensionDeduction: String
          _tmpPensionDeduction = _stmt.getText(_columnIndexOfPensionDeduction)
          val _tmpAdvances: String
          _tmpAdvances = _stmt.getText(_columnIndexOfAdvances)
          val _tmpExtraHours: String
          _tmpExtraHours = _stmt.getText(_columnIndexOfExtraHours)
          val _tmpTotalDevengado: String
          _tmpTotalDevengado = _stmt.getText(_columnIndexOfTotalDevengado)
          val _tmpTotalDeducciones: String
          _tmpTotalDeducciones = _stmt.getText(_columnIndexOfTotalDeducciones)
          val _tmpNetPay: String
          _tmpNetPay = _stmt.getText(_columnIndexOfNetPay)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              PayrollCalculationEntity(_tmpId,_tmpEmployeeId,_tmpBasicSalary,_tmpDaysWorked,_tmpTransportAllowance,_tmpHealthDeduction,_tmpPensionDeduction,_tmpAdvances,_tmpExtraHours,_tmpTotalDevengado,_tmpTotalDeducciones,_tmpNetPay,_tmpDate,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedPayroll(): List<PayrollCalculationEntity> {
    val _sql: String = "SELECT * FROM payroll_calculations WHERE sincronizado = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfBasicSalary: Int = getColumnIndexOrThrow(_stmt, "basicSalary")
        val _columnIndexOfDaysWorked: Int = getColumnIndexOrThrow(_stmt, "daysWorked")
        val _columnIndexOfTransportAllowance: Int = getColumnIndexOrThrow(_stmt,
            "transportAllowance")
        val _columnIndexOfHealthDeduction: Int = getColumnIndexOrThrow(_stmt, "healthDeduction")
        val _columnIndexOfPensionDeduction: Int = getColumnIndexOrThrow(_stmt, "pensionDeduction")
        val _columnIndexOfAdvances: Int = getColumnIndexOrThrow(_stmt, "advances")
        val _columnIndexOfExtraHours: Int = getColumnIndexOrThrow(_stmt, "extraHours")
        val _columnIndexOfTotalDevengado: Int = getColumnIndexOrThrow(_stmt, "totalDevengado")
        val _columnIndexOfTotalDeducciones: Int = getColumnIndexOrThrow(_stmt, "totalDeducciones")
        val _columnIndexOfNetPay: Int = getColumnIndexOrThrow(_stmt, "netPay")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<PayrollCalculationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PayrollCalculationEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpBasicSalary: String
          _tmpBasicSalary = _stmt.getText(_columnIndexOfBasicSalary)
          val _tmpDaysWorked: Int
          _tmpDaysWorked = _stmt.getLong(_columnIndexOfDaysWorked).toInt()
          val _tmpTransportAllowance: String
          _tmpTransportAllowance = _stmt.getText(_columnIndexOfTransportAllowance)
          val _tmpHealthDeduction: String
          _tmpHealthDeduction = _stmt.getText(_columnIndexOfHealthDeduction)
          val _tmpPensionDeduction: String
          _tmpPensionDeduction = _stmt.getText(_columnIndexOfPensionDeduction)
          val _tmpAdvances: String
          _tmpAdvances = _stmt.getText(_columnIndexOfAdvances)
          val _tmpExtraHours: String
          _tmpExtraHours = _stmt.getText(_columnIndexOfExtraHours)
          val _tmpTotalDevengado: String
          _tmpTotalDevengado = _stmt.getText(_columnIndexOfTotalDevengado)
          val _tmpTotalDeducciones: String
          _tmpTotalDeducciones = _stmt.getText(_columnIndexOfTotalDeducciones)
          val _tmpNetPay: String
          _tmpNetPay = _stmt.getText(_columnIndexOfNetPay)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              PayrollCalculationEntity(_tmpId,_tmpEmployeeId,_tmpBasicSalary,_tmpDaysWorked,_tmpTransportAllowance,_tmpHealthDeduction,_tmpPensionDeduction,_tmpAdvances,_tmpExtraHours,_tmpTotalDevengado,_tmpTotalDeducciones,_tmpNetPay,_tmpDate,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPayrollHistory(employeeId: String):
      List<PayrollCalculationEntity> {
    val _sql: String = "SELECT * FROM payroll_calculations WHERE employeeId = ? ORDER BY date DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, employeeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfBasicSalary: Int = getColumnIndexOrThrow(_stmt, "basicSalary")
        val _columnIndexOfDaysWorked: Int = getColumnIndexOrThrow(_stmt, "daysWorked")
        val _columnIndexOfTransportAllowance: Int = getColumnIndexOrThrow(_stmt,
            "transportAllowance")
        val _columnIndexOfHealthDeduction: Int = getColumnIndexOrThrow(_stmt, "healthDeduction")
        val _columnIndexOfPensionDeduction: Int = getColumnIndexOrThrow(_stmt, "pensionDeduction")
        val _columnIndexOfAdvances: Int = getColumnIndexOrThrow(_stmt, "advances")
        val _columnIndexOfExtraHours: Int = getColumnIndexOrThrow(_stmt, "extraHours")
        val _columnIndexOfTotalDevengado: Int = getColumnIndexOrThrow(_stmt, "totalDevengado")
        val _columnIndexOfTotalDeducciones: Int = getColumnIndexOrThrow(_stmt, "totalDeducciones")
        val _columnIndexOfNetPay: Int = getColumnIndexOrThrow(_stmt, "netPay")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<PayrollCalculationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PayrollCalculationEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpBasicSalary: String
          _tmpBasicSalary = _stmt.getText(_columnIndexOfBasicSalary)
          val _tmpDaysWorked: Int
          _tmpDaysWorked = _stmt.getLong(_columnIndexOfDaysWorked).toInt()
          val _tmpTransportAllowance: String
          _tmpTransportAllowance = _stmt.getText(_columnIndexOfTransportAllowance)
          val _tmpHealthDeduction: String
          _tmpHealthDeduction = _stmt.getText(_columnIndexOfHealthDeduction)
          val _tmpPensionDeduction: String
          _tmpPensionDeduction = _stmt.getText(_columnIndexOfPensionDeduction)
          val _tmpAdvances: String
          _tmpAdvances = _stmt.getText(_columnIndexOfAdvances)
          val _tmpExtraHours: String
          _tmpExtraHours = _stmt.getText(_columnIndexOfExtraHours)
          val _tmpTotalDevengado: String
          _tmpTotalDevengado = _stmt.getText(_columnIndexOfTotalDevengado)
          val _tmpTotalDeducciones: String
          _tmpTotalDeducciones = _stmt.getText(_columnIndexOfTotalDeducciones)
          val _tmpNetPay: String
          _tmpNetPay = _stmt.getText(_columnIndexOfNetPay)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              PayrollCalculationEntity(_tmpId,_tmpEmployeeId,_tmpBasicSalary,_tmpDaysWorked,_tmpTransportAllowance,_tmpHealthDeduction,_tmpPensionDeduction,_tmpAdvances,_tmpExtraHours,_tmpTotalDevengado,_tmpTotalDeducciones,_tmpNetPay,_tmpDate,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPayrollHistoryByDateRange(startDate: Long, endDate: Long):
      List<PayrollCalculationEntity> {
    val _sql: String = "SELECT * FROM payroll_calculations WHERE date BETWEEN ? AND ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startDate)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endDate)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfBasicSalary: Int = getColumnIndexOrThrow(_stmt, "basicSalary")
        val _columnIndexOfDaysWorked: Int = getColumnIndexOrThrow(_stmt, "daysWorked")
        val _columnIndexOfTransportAllowance: Int = getColumnIndexOrThrow(_stmt,
            "transportAllowance")
        val _columnIndexOfHealthDeduction: Int = getColumnIndexOrThrow(_stmt, "healthDeduction")
        val _columnIndexOfPensionDeduction: Int = getColumnIndexOrThrow(_stmt, "pensionDeduction")
        val _columnIndexOfAdvances: Int = getColumnIndexOrThrow(_stmt, "advances")
        val _columnIndexOfExtraHours: Int = getColumnIndexOrThrow(_stmt, "extraHours")
        val _columnIndexOfTotalDevengado: Int = getColumnIndexOrThrow(_stmt, "totalDevengado")
        val _columnIndexOfTotalDeducciones: Int = getColumnIndexOrThrow(_stmt, "totalDeducciones")
        val _columnIndexOfNetPay: Int = getColumnIndexOrThrow(_stmt, "netPay")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<PayrollCalculationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PayrollCalculationEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpBasicSalary: String
          _tmpBasicSalary = _stmt.getText(_columnIndexOfBasicSalary)
          val _tmpDaysWorked: Int
          _tmpDaysWorked = _stmt.getLong(_columnIndexOfDaysWorked).toInt()
          val _tmpTransportAllowance: String
          _tmpTransportAllowance = _stmt.getText(_columnIndexOfTransportAllowance)
          val _tmpHealthDeduction: String
          _tmpHealthDeduction = _stmt.getText(_columnIndexOfHealthDeduction)
          val _tmpPensionDeduction: String
          _tmpPensionDeduction = _stmt.getText(_columnIndexOfPensionDeduction)
          val _tmpAdvances: String
          _tmpAdvances = _stmt.getText(_columnIndexOfAdvances)
          val _tmpExtraHours: String
          _tmpExtraHours = _stmt.getText(_columnIndexOfExtraHours)
          val _tmpTotalDevengado: String
          _tmpTotalDevengado = _stmt.getText(_columnIndexOfTotalDevengado)
          val _tmpTotalDeducciones: String
          _tmpTotalDeducciones = _stmt.getText(_columnIndexOfTotalDeducciones)
          val _tmpNetPay: String
          _tmpNetPay = _stmt.getText(_columnIndexOfNetPay)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
          _item =
              PayrollCalculationEntity(_tmpId,_tmpEmployeeId,_tmpBasicSalary,_tmpDaysWorked,_tmpTransportAllowance,_tmpHealthDeduction,_tmpPensionDeduction,_tmpAdvances,_tmpExtraHours,_tmpTotalDevengado,_tmpTotalDeducciones,_tmpNetPay,_tmpDate,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markVacationSynced(id: String) {
    val _sql: String = "UPDATE vacation_requests SET sincronizado = 1 WHERE id = ?"
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

  public override suspend fun markAdvanceSynced(id: String) {
    val _sql: String = "UPDATE advance_requests SET sincronizado = 1 WHERE id = ?"
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

  public override suspend fun markPayrollSynced(id: String) {
    val _sql: String = "UPDATE payroll_calculations SET sincronizado = 1 WHERE id = ?"
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

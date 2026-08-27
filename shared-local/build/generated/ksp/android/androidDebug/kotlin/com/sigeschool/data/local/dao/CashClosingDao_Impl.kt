package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.CashClosingEntity
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
public class CashClosingDao_Impl(
  __db: RoomDatabase,
) : CashClosingDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCashClosingEntity: EntityInsertAdapter<CashClosingEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCashClosingEntity = object : EntityInsertAdapter<CashClosingEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `cash_closings` (`id`,`date`,`institutionId`,`totalCash`,`totalTransfer`,`totalOther`,`totalGeneral`,`closedBy`,`closingTimestamp`,`observations`,`isSynced`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CashClosingEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.date)
        statement.bindText(3, entity.institutionId)
        statement.bindDouble(4, entity.totalCash)
        statement.bindDouble(5, entity.totalTransfer)
        statement.bindDouble(6, entity.totalOther)
        statement.bindDouble(7, entity.totalGeneral)
        statement.bindText(8, entity.closedBy)
        statement.bindLong(9, entity.closingTimestamp)
        statement.bindText(10, entity.observations)
        val _tmp: Int = if (entity.isSynced) 1 else 0
        statement.bindLong(11, _tmp.toLong())
      }
    }
  }

  public override suspend fun insert(closing: CashClosingEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfCashClosingEntity.insert(_connection, closing)
  }

  public override fun getAllClosings(institutionId: String): Flow<List<CashClosingEntity>> {
    val _sql: String =
        "SELECT * FROM cash_closings WHERE institutionId = ? ORDER BY closingTimestamp DESC"
    return createFlow(__db, false, arrayOf("cash_closings")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTotalCash: Int = getColumnIndexOrThrow(_stmt, "totalCash")
        val _columnIndexOfTotalTransfer: Int = getColumnIndexOrThrow(_stmt, "totalTransfer")
        val _columnIndexOfTotalOther: Int = getColumnIndexOrThrow(_stmt, "totalOther")
        val _columnIndexOfTotalGeneral: Int = getColumnIndexOrThrow(_stmt, "totalGeneral")
        val _columnIndexOfClosedBy: Int = getColumnIndexOrThrow(_stmt, "closedBy")
        val _columnIndexOfClosingTimestamp: Int = getColumnIndexOrThrow(_stmt, "closingTimestamp")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _result: MutableList<CashClosingEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CashClosingEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDate: String
          _tmpDate = _stmt.getText(_columnIndexOfDate)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTotalCash: Double
          _tmpTotalCash = _stmt.getDouble(_columnIndexOfTotalCash)
          val _tmpTotalTransfer: Double
          _tmpTotalTransfer = _stmt.getDouble(_columnIndexOfTotalTransfer)
          val _tmpTotalOther: Double
          _tmpTotalOther = _stmt.getDouble(_columnIndexOfTotalOther)
          val _tmpTotalGeneral: Double
          _tmpTotalGeneral = _stmt.getDouble(_columnIndexOfTotalGeneral)
          val _tmpClosedBy: String
          _tmpClosedBy = _stmt.getText(_columnIndexOfClosedBy)
          val _tmpClosingTimestamp: Long
          _tmpClosingTimestamp = _stmt.getLong(_columnIndexOfClosingTimestamp)
          val _tmpObservations: String
          _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          _item =
              CashClosingEntity(_tmpId,_tmpDate,_tmpInstitutionId,_tmpTotalCash,_tmpTotalTransfer,_tmpTotalOther,_tmpTotalGeneral,_tmpClosedBy,_tmpClosingTimestamp,_tmpObservations,_tmpIsSynced)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getClosingByDate(date: String, institutionId: String):
      CashClosingEntity? {
    val _sql: String = "SELECT * FROM cash_closings WHERE date = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, date)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTotalCash: Int = getColumnIndexOrThrow(_stmt, "totalCash")
        val _columnIndexOfTotalTransfer: Int = getColumnIndexOrThrow(_stmt, "totalTransfer")
        val _columnIndexOfTotalOther: Int = getColumnIndexOrThrow(_stmt, "totalOther")
        val _columnIndexOfTotalGeneral: Int = getColumnIndexOrThrow(_stmt, "totalGeneral")
        val _columnIndexOfClosedBy: Int = getColumnIndexOrThrow(_stmt, "closedBy")
        val _columnIndexOfClosingTimestamp: Int = getColumnIndexOrThrow(_stmt, "closingTimestamp")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _result: CashClosingEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDate: String
          _tmpDate = _stmt.getText(_columnIndexOfDate)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTotalCash: Double
          _tmpTotalCash = _stmt.getDouble(_columnIndexOfTotalCash)
          val _tmpTotalTransfer: Double
          _tmpTotalTransfer = _stmt.getDouble(_columnIndexOfTotalTransfer)
          val _tmpTotalOther: Double
          _tmpTotalOther = _stmt.getDouble(_columnIndexOfTotalOther)
          val _tmpTotalGeneral: Double
          _tmpTotalGeneral = _stmt.getDouble(_columnIndexOfTotalGeneral)
          val _tmpClosedBy: String
          _tmpClosedBy = _stmt.getText(_columnIndexOfClosedBy)
          val _tmpClosingTimestamp: Long
          _tmpClosingTimestamp = _stmt.getLong(_columnIndexOfClosingTimestamp)
          val _tmpObservations: String
          _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          _result =
              CashClosingEntity(_tmpId,_tmpDate,_tmpInstitutionId,_tmpTotalCash,_tmpTotalTransfer,_tmpTotalOther,_tmpTotalGeneral,_tmpClosedBy,_tmpClosingTimestamp,_tmpObservations,_tmpIsSynced)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedClosings(): List<CashClosingEntity> {
    val _sql: String = "SELECT * FROM cash_closings WHERE isSynced = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTotalCash: Int = getColumnIndexOrThrow(_stmt, "totalCash")
        val _columnIndexOfTotalTransfer: Int = getColumnIndexOrThrow(_stmt, "totalTransfer")
        val _columnIndexOfTotalOther: Int = getColumnIndexOrThrow(_stmt, "totalOther")
        val _columnIndexOfTotalGeneral: Int = getColumnIndexOrThrow(_stmt, "totalGeneral")
        val _columnIndexOfClosedBy: Int = getColumnIndexOrThrow(_stmt, "closedBy")
        val _columnIndexOfClosingTimestamp: Int = getColumnIndexOrThrow(_stmt, "closingTimestamp")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _result: MutableList<CashClosingEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CashClosingEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDate: String
          _tmpDate = _stmt.getText(_columnIndexOfDate)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTotalCash: Double
          _tmpTotalCash = _stmt.getDouble(_columnIndexOfTotalCash)
          val _tmpTotalTransfer: Double
          _tmpTotalTransfer = _stmt.getDouble(_columnIndexOfTotalTransfer)
          val _tmpTotalOther: Double
          _tmpTotalOther = _stmt.getDouble(_columnIndexOfTotalOther)
          val _tmpTotalGeneral: Double
          _tmpTotalGeneral = _stmt.getDouble(_columnIndexOfTotalGeneral)
          val _tmpClosedBy: String
          _tmpClosedBy = _stmt.getText(_columnIndexOfClosedBy)
          val _tmpClosingTimestamp: Long
          _tmpClosingTimestamp = _stmt.getLong(_columnIndexOfClosingTimestamp)
          val _tmpObservations: String
          _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          _item =
              CashClosingEntity(_tmpId,_tmpDate,_tmpInstitutionId,_tmpTotalCash,_tmpTotalTransfer,_tmpTotalOther,_tmpTotalGeneral,_tmpClosedBy,_tmpClosingTimestamp,_tmpObservations,_tmpIsSynced)
          _result.add(_item)
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

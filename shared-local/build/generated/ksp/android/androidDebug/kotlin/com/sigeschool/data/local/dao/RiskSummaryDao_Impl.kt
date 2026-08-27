package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.RiskSummaryEntity
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
public class RiskSummaryDao_Impl(
  __db: RoomDatabase,
) : RiskSummaryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRiskSummaryEntity: EntityInsertAdapter<RiskSummaryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRiskSummaryEntity = object : EntityInsertAdapter<RiskSummaryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `risk_summary` (`institutionId`,`totalStudents`,`critical`,`high`,`medium`,`low`,`averageRisk`,`lastModified`,`syncStatus`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RiskSummaryEntity) {
        statement.bindText(1, entity.institutionId)
        statement.bindLong(2, entity.totalStudents.toLong())
        statement.bindLong(3, entity.critical.toLong())
        statement.bindLong(4, entity.high.toLong())
        statement.bindLong(5, entity.medium.toLong())
        statement.bindLong(6, entity.low.toLong())
        statement.bindDouble(7, entity.averageRisk)
        statement.bindLong(8, entity.lastModified)
        statement.bindLong(9, entity.syncStatus.toLong())
      }
    }
  }

  public override suspend fun insertOrUpdate(summary: RiskSummaryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRiskSummaryEntity.insert(_connection, summary)
  }

  public override suspend fun getByInstitution(instId: String): RiskSummaryEntity? {
    val _sql: String = "SELECT * FROM risk_summary WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTotalStudents: Int = getColumnIndexOrThrow(_stmt, "totalStudents")
        val _columnIndexOfCritical: Int = getColumnIndexOrThrow(_stmt, "critical")
        val _columnIndexOfHigh: Int = getColumnIndexOrThrow(_stmt, "high")
        val _columnIndexOfMedium: Int = getColumnIndexOrThrow(_stmt, "medium")
        val _columnIndexOfLow: Int = getColumnIndexOrThrow(_stmt, "low")
        val _columnIndexOfAverageRisk: Int = getColumnIndexOrThrow(_stmt, "averageRisk")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: RiskSummaryEntity?
        if (_stmt.step()) {
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTotalStudents: Int
          _tmpTotalStudents = _stmt.getLong(_columnIndexOfTotalStudents).toInt()
          val _tmpCritical: Int
          _tmpCritical = _stmt.getLong(_columnIndexOfCritical).toInt()
          val _tmpHigh: Int
          _tmpHigh = _stmt.getLong(_columnIndexOfHigh).toInt()
          val _tmpMedium: Int
          _tmpMedium = _stmt.getLong(_columnIndexOfMedium).toInt()
          val _tmpLow: Int
          _tmpLow = _stmt.getLong(_columnIndexOfLow).toInt()
          val _tmpAverageRisk: Double
          _tmpAverageRisk = _stmt.getDouble(_columnIndexOfAverageRisk)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _result =
              RiskSummaryEntity(_tmpInstitutionId,_tmpTotalStudents,_tmpCritical,_tmpHigh,_tmpMedium,_tmpLow,_tmpAverageRisk,_tmpLastModified,_tmpSyncStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByInstitutionFlow(instId: String): Flow<RiskSummaryEntity?> {
    val _sql: String = "SELECT * FROM risk_summary WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("risk_summary")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTotalStudents: Int = getColumnIndexOrThrow(_stmt, "totalStudents")
        val _columnIndexOfCritical: Int = getColumnIndexOrThrow(_stmt, "critical")
        val _columnIndexOfHigh: Int = getColumnIndexOrThrow(_stmt, "high")
        val _columnIndexOfMedium: Int = getColumnIndexOrThrow(_stmt, "medium")
        val _columnIndexOfLow: Int = getColumnIndexOrThrow(_stmt, "low")
        val _columnIndexOfAverageRisk: Int = getColumnIndexOrThrow(_stmt, "averageRisk")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: RiskSummaryEntity?
        if (_stmt.step()) {
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTotalStudents: Int
          _tmpTotalStudents = _stmt.getLong(_columnIndexOfTotalStudents).toInt()
          val _tmpCritical: Int
          _tmpCritical = _stmt.getLong(_columnIndexOfCritical).toInt()
          val _tmpHigh: Int
          _tmpHigh = _stmt.getLong(_columnIndexOfHigh).toInt()
          val _tmpMedium: Int
          _tmpMedium = _stmt.getLong(_columnIndexOfMedium).toInt()
          val _tmpLow: Int
          _tmpLow = _stmt.getLong(_columnIndexOfLow).toInt()
          val _tmpAverageRisk: Double
          _tmpAverageRisk = _stmt.getDouble(_columnIndexOfAverageRisk)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _result =
              RiskSummaryEntity(_tmpInstitutionId,_tmpTotalStudents,_tmpCritical,_tmpHigh,_tmpMedium,_tmpLow,_tmpAverageRisk,_tmpLastModified,_tmpSyncStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<RiskSummaryEntity> {
    val _sql: String = "SELECT * FROM risk_summary WHERE institutionId = ? AND syncStatus = 2"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTotalStudents: Int = getColumnIndexOrThrow(_stmt, "totalStudents")
        val _columnIndexOfCritical: Int = getColumnIndexOrThrow(_stmt, "critical")
        val _columnIndexOfHigh: Int = getColumnIndexOrThrow(_stmt, "high")
        val _columnIndexOfMedium: Int = getColumnIndexOrThrow(_stmt, "medium")
        val _columnIndexOfLow: Int = getColumnIndexOrThrow(_stmt, "low")
        val _columnIndexOfAverageRisk: Int = getColumnIndexOrThrow(_stmt, "averageRisk")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<RiskSummaryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RiskSummaryEntity
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTotalStudents: Int
          _tmpTotalStudents = _stmt.getLong(_columnIndexOfTotalStudents).toInt()
          val _tmpCritical: Int
          _tmpCritical = _stmt.getLong(_columnIndexOfCritical).toInt()
          val _tmpHigh: Int
          _tmpHigh = _stmt.getLong(_columnIndexOfHigh).toInt()
          val _tmpMedium: Int
          _tmpMedium = _stmt.getLong(_columnIndexOfMedium).toInt()
          val _tmpLow: Int
          _tmpLow = _stmt.getLong(_columnIndexOfLow).toInt()
          val _tmpAverageRisk: Double
          _tmpAverageRisk = _stmt.getDouble(_columnIndexOfAverageRisk)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              RiskSummaryEntity(_tmpInstitutionId,_tmpTotalStudents,_tmpCritical,_tmpHigh,_tmpMedium,_tmpLow,_tmpAverageRisk,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(instId: String, timestamp: Long) {
    val _sql: String =
        "UPDATE risk_summary SET syncStatus = 0, lastModified = ? WHERE institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
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

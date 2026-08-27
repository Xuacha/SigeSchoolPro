package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.RiskAnalysisEntity
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
public class RiskAnalysisDao_Impl(
  __db: RoomDatabase,
) : RiskAnalysisDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRiskAnalysisEntity: EntityInsertAdapter<RiskAnalysisEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRiskAnalysisEntity = object : EntityInsertAdapter<RiskAnalysisEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `risk_analysis` (`studentId`,`institutionId`,`riskLevel`,`riskScore`,`factors`,`lastModified`,`syncStatus`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RiskAnalysisEntity) {
        statement.bindText(1, entity.studentId)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.riskLevel)
        statement.bindDouble(4, entity.riskScore)
        statement.bindText(5, entity.factors)
        statement.bindLong(6, entity.lastModified)
        statement.bindLong(7, entity.syncStatus.toLong())
      }
    }
  }

  public override suspend fun insertOrUpdate(analysis: RiskAnalysisEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRiskAnalysisEntity.insert(_connection, analysis)
  }

  public override fun getByInstitution(instId: String): Flow<List<RiskAnalysisEntity>> {
    val _sql: String = "SELECT * FROM risk_analysis WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("risk_analysis")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfRiskLevel: Int = getColumnIndexOrThrow(_stmt, "riskLevel")
        val _columnIndexOfRiskScore: Int = getColumnIndexOrThrow(_stmt, "riskScore")
        val _columnIndexOfFactors: Int = getColumnIndexOrThrow(_stmt, "factors")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<RiskAnalysisEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RiskAnalysisEntity
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpRiskLevel: String
          _tmpRiskLevel = _stmt.getText(_columnIndexOfRiskLevel)
          val _tmpRiskScore: Double
          _tmpRiskScore = _stmt.getDouble(_columnIndexOfRiskScore)
          val _tmpFactors: String
          _tmpFactors = _stmt.getText(_columnIndexOfFactors)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              RiskAnalysisEntity(_tmpStudentId,_tmpInstitutionId,_tmpRiskLevel,_tmpRiskScore,_tmpFactors,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByStudent(studentId: String, instId: String): RiskAnalysisEntity? {
    val _sql: String = "SELECT * FROM risk_analysis WHERE studentId = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfRiskLevel: Int = getColumnIndexOrThrow(_stmt, "riskLevel")
        val _columnIndexOfRiskScore: Int = getColumnIndexOrThrow(_stmt, "riskScore")
        val _columnIndexOfFactors: Int = getColumnIndexOrThrow(_stmt, "factors")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: RiskAnalysisEntity?
        if (_stmt.step()) {
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpRiskLevel: String
          _tmpRiskLevel = _stmt.getText(_columnIndexOfRiskLevel)
          val _tmpRiskScore: Double
          _tmpRiskScore = _stmt.getDouble(_columnIndexOfRiskScore)
          val _tmpFactors: String
          _tmpFactors = _stmt.getText(_columnIndexOfFactors)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _result =
              RiskAnalysisEntity(_tmpStudentId,_tmpInstitutionId,_tmpRiskLevel,_tmpRiskScore,_tmpFactors,_tmpLastModified,_tmpSyncStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<RiskAnalysisEntity> {
    val _sql: String = "SELECT * FROM risk_analysis WHERE institutionId = ? AND syncStatus = 2"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfRiskLevel: Int = getColumnIndexOrThrow(_stmt, "riskLevel")
        val _columnIndexOfRiskScore: Int = getColumnIndexOrThrow(_stmt, "riskScore")
        val _columnIndexOfFactors: Int = getColumnIndexOrThrow(_stmt, "factors")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _result: MutableList<RiskAnalysisEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RiskAnalysisEntity
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpRiskLevel: String
          _tmpRiskLevel = _stmt.getText(_columnIndexOfRiskLevel)
          val _tmpRiskScore: Double
          _tmpRiskScore = _stmt.getDouble(_columnIndexOfRiskScore)
          val _tmpFactors: String
          _tmpFactors = _stmt.getText(_columnIndexOfFactors)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          _item =
              RiskAnalysisEntity(_tmpStudentId,_tmpInstitutionId,_tmpRiskLevel,_tmpRiskScore,_tmpFactors,_tmpLastModified,_tmpSyncStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(studentId: String, timestamp: Long) {
    val _sql: String =
        "UPDATE risk_analysis SET syncStatus = 0, lastModified = ? WHERE studentId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindText(_argIndex, studentId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAllPending(instId: String) {
    val _sql: String = "UPDATE risk_analysis SET syncStatus = 2 WHERE institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
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

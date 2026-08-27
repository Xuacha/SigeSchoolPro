package com.sigeschool.`data`.local.dao.sie

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.sie.AutoevaluacionEntity
import com.sigeschool.`data`.local.entity.sie.PromotionConfigEntity
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
public class PromotionDao_Impl(
  __db: RoomDatabase,
) : PromotionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAutoevaluacionEntity: EntityInsertAdapter<AutoevaluacionEntity>

  private val __insertAdapterOfPromotionConfigEntity: EntityInsertAdapter<PromotionConfigEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAutoevaluacionEntity = object :
        EntityInsertAdapter<AutoevaluacionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `autoevaluaciones` (`id`,`studentId`,`subjectId`,`periodId`,`score`,`registrationDate`,`metadata`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AutoevaluacionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.studentId)
        statement.bindText(3, entity.subjectId)
        statement.bindText(4, entity.periodId)
        statement.bindDouble(5, entity.score)
        statement.bindLong(6, entity.registrationDate)
        val _tmpMetadata: String? = entity.metadata
        if (_tmpMetadata == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpMetadata)
        }
      }
    }
    this.__insertAdapterOfPromotionConfigEntity = object :
        EntityInsertAdapter<PromotionConfigEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `configuracion_promocion` (`id`,`institutionId`,`maxFailedSubjects`,`maxInattendancePercentage`,`minimumPassingScore`,`autoevaluacionWeight`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PromotionConfigEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.maxFailedSubjects.toLong())
        statement.bindDouble(4, entity.maxInattendancePercentage)
        statement.bindDouble(5, entity.minimumPassingScore)
        statement.bindDouble(6, entity.autoevaluacionWeight)
      }
    }
  }

  public override suspend fun insertAutoevaluacion(autoevaluacion: AutoevaluacionEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAutoevaluacionEntity.insert(_connection, autoevaluacion)
  }

  public override suspend fun insertPromotionConfig(config: PromotionConfigEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPromotionConfigEntity.insert(_connection, config)
  }

  public override fun getAutoevaluaciones(studentId: String, periodId: String):
      Flow<List<AutoevaluacionEntity>> {
    val _sql: String = "SELECT * FROM autoevaluaciones WHERE studentId = ? AND periodId = ?"
    return createFlow(__db, false, arrayOf("autoevaluaciones")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, periodId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfPeriodId: Int = getColumnIndexOrThrow(_stmt, "periodId")
        val _columnIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _columnIndexOfRegistrationDate: Int = getColumnIndexOrThrow(_stmt, "registrationDate")
        val _columnIndexOfMetadata: Int = getColumnIndexOrThrow(_stmt, "metadata")
        val _result: MutableList<AutoevaluacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AutoevaluacionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpPeriodId: String
          _tmpPeriodId = _stmt.getText(_columnIndexOfPeriodId)
          val _tmpScore: Double
          _tmpScore = _stmt.getDouble(_columnIndexOfScore)
          val _tmpRegistrationDate: Long
          _tmpRegistrationDate = _stmt.getLong(_columnIndexOfRegistrationDate)
          val _tmpMetadata: String?
          if (_stmt.isNull(_columnIndexOfMetadata)) {
            _tmpMetadata = null
          } else {
            _tmpMetadata = _stmt.getText(_columnIndexOfMetadata)
          }
          _item =
              AutoevaluacionEntity(_tmpId,_tmpStudentId,_tmpSubjectId,_tmpPeriodId,_tmpScore,_tmpRegistrationDate,_tmpMetadata)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countAutoevaluacion(
    studentId: String,
    subjectId: String,
    periodId: String,
  ): Int {
    val _sql: String =
        "SELECT COUNT(*) FROM autoevaluaciones WHERE studentId = ? AND subjectId = ? AND periodId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, subjectId)
        _argIndex = 3
        _stmt.bindText(_argIndex, periodId)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPromotionConfig(institutionId: String): Flow<PromotionConfigEntity?> {
    val _sql: String = "SELECT * FROM configuracion_promocion WHERE institutionId = ? LIMIT 1"
    return createFlow(__db, false, arrayOf("configuracion_promocion")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfMaxFailedSubjects: Int = getColumnIndexOrThrow(_stmt, "maxFailedSubjects")
        val _columnIndexOfMaxInattendancePercentage: Int = getColumnIndexOrThrow(_stmt,
            "maxInattendancePercentage")
        val _columnIndexOfMinimumPassingScore: Int = getColumnIndexOrThrow(_stmt,
            "minimumPassingScore")
        val _columnIndexOfAutoevaluacionWeight: Int = getColumnIndexOrThrow(_stmt,
            "autoevaluacionWeight")
        val _result: PromotionConfigEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpMaxFailedSubjects: Int
          _tmpMaxFailedSubjects = _stmt.getLong(_columnIndexOfMaxFailedSubjects).toInt()
          val _tmpMaxInattendancePercentage: Double
          _tmpMaxInattendancePercentage = _stmt.getDouble(_columnIndexOfMaxInattendancePercentage)
          val _tmpMinimumPassingScore: Double
          _tmpMinimumPassingScore = _stmt.getDouble(_columnIndexOfMinimumPassingScore)
          val _tmpAutoevaluacionWeight: Double
          _tmpAutoevaluacionWeight = _stmt.getDouble(_columnIndexOfAutoevaluacionWeight)
          _result =
              PromotionConfigEntity(_tmpId,_tmpInstitutionId,_tmpMaxFailedSubjects,_tmpMaxInattendancePercentage,_tmpMinimumPassingScore,_tmpAutoevaluacionWeight)
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

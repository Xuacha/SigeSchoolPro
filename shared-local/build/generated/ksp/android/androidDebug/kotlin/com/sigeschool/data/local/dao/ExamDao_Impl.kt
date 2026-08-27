package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.converter.Converters
import com.sigeschool.`data`.local.entity.ExamEntity
import com.sigeschool.domain.model.Question
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
public class ExamDao_Impl(
  __db: RoomDatabase,
) : ExamDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfExamEntity: EntityInsertAdapter<ExamEntity>

  private val __converters: Converters = Converters()

  private val __deleteAdapterOfExamEntity: EntityDeleteOrUpdateAdapter<ExamEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfExamEntity = object : EntityInsertAdapter<ExamEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `exams` (`id`,`title`,`date`,`classId`,`subjectId`,`maxScore`,`institutionId`,`durationMinutes`,`questions`,`sincronizado`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ExamEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindLong(3, entity.date)
        statement.bindText(4, entity.classId)
        statement.bindText(5, entity.subjectId)
        statement.bindDouble(6, entity.maxScore)
        statement.bindText(7, entity.institutionId)
        statement.bindLong(8, entity.durationMinutes.toLong())
        val _tmp: String = __converters.fromQuestionList(entity.questions)
        statement.bindText(9, _tmp)
        val _tmp_1: Int = if (entity.sincronizado) 1 else 0
        statement.bindLong(10, _tmp_1.toLong())
      }
    }
    this.__deleteAdapterOfExamEntity = object : EntityDeleteOrUpdateAdapter<ExamEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `exams` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ExamEntity) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertExam(exam: ExamEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfExamEntity.insert(_connection, exam)
  }

  public override suspend fun deleteExam(exam: ExamEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __deleteAdapterOfExamEntity.handle(_connection, exam)
  }

  public override fun getExams(institutionId: String): Flow<List<ExamEntity>> {
    val _sql: String = "SELECT * FROM exams WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("exams")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfClassId: Int = getColumnIndexOrThrow(_stmt, "classId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfMaxScore: Int = getColumnIndexOrThrow(_stmt, "maxScore")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDurationMinutes: Int = getColumnIndexOrThrow(_stmt, "durationMinutes")
        val _columnIndexOfQuestions: Int = getColumnIndexOrThrow(_stmt, "questions")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<ExamEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExamEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpClassId: String
          _tmpClassId = _stmt.getText(_columnIndexOfClassId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpMaxScore: Double
          _tmpMaxScore = _stmt.getDouble(_columnIndexOfMaxScore)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDurationMinutes: Int
          _tmpDurationMinutes = _stmt.getLong(_columnIndexOfDurationMinutes).toInt()
          val _tmpQuestions: List<Question>
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfQuestions)
          _tmpQuestions = __converters.toQuestionList(_tmp)
          val _tmpSincronizado: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp_1 != 0
          _item =
              ExamEntity(_tmpId,_tmpTitle,_tmpDate,_tmpClassId,_tmpSubjectId,_tmpMaxScore,_tmpInstitutionId,_tmpDurationMinutes,_tmpQuestions,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getExamsByClass(classId: String): Flow<List<ExamEntity>> {
    val _sql: String = "SELECT * FROM exams WHERE classId = ?"
    return createFlow(__db, false, arrayOf("exams")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, classId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfClassId: Int = getColumnIndexOrThrow(_stmt, "classId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfMaxScore: Int = getColumnIndexOrThrow(_stmt, "maxScore")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDurationMinutes: Int = getColumnIndexOrThrow(_stmt, "durationMinutes")
        val _columnIndexOfQuestions: Int = getColumnIndexOrThrow(_stmt, "questions")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<ExamEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExamEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpClassId: String
          _tmpClassId = _stmt.getText(_columnIndexOfClassId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpMaxScore: Double
          _tmpMaxScore = _stmt.getDouble(_columnIndexOfMaxScore)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDurationMinutes: Int
          _tmpDurationMinutes = _stmt.getLong(_columnIndexOfDurationMinutes).toInt()
          val _tmpQuestions: List<Question>
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfQuestions)
          _tmpQuestions = __converters.toQuestionList(_tmp)
          val _tmpSincronizado: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp_1 != 0
          _item =
              ExamEntity(_tmpId,_tmpTitle,_tmpDate,_tmpClassId,_tmpSubjectId,_tmpMaxScore,_tmpInstitutionId,_tmpDurationMinutes,_tmpQuestions,_tmpSincronizado)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedExams(): List<ExamEntity> {
    val _sql: String = "SELECT * FROM exams WHERE sincronizado = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfClassId: Int = getColumnIndexOrThrow(_stmt, "classId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfMaxScore: Int = getColumnIndexOrThrow(_stmt, "maxScore")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDurationMinutes: Int = getColumnIndexOrThrow(_stmt, "durationMinutes")
        val _columnIndexOfQuestions: Int = getColumnIndexOrThrow(_stmt, "questions")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _result: MutableList<ExamEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExamEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpClassId: String
          _tmpClassId = _stmt.getText(_columnIndexOfClassId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpMaxScore: Double
          _tmpMaxScore = _stmt.getDouble(_columnIndexOfMaxScore)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDurationMinutes: Int
          _tmpDurationMinutes = _stmt.getLong(_columnIndexOfDurationMinutes).toInt()
          val _tmpQuestions: List<Question>
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfQuestions)
          _tmpQuestions = __converters.toQuestionList(_tmp)
          val _tmpSincronizado: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp_1 != 0
          _item =
              ExamEntity(_tmpId,_tmpTitle,_tmpDate,_tmpClassId,_tmpSubjectId,_tmpMaxScore,_tmpInstitutionId,_tmpDurationMinutes,_tmpQuestions,_tmpSincronizado)
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

package com.sigeschool.`data`.local.dao.sie

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.sie.AcademicGradeEntity
import com.sigeschool.`data`.local.entity.sie.AchievementEntity
import com.sigeschool.`data`.local.entity.sie.AreaPlanEntity
import com.sigeschool.`data`.local.entity.sie.DisciplineRecordEntity
import com.sigeschool.`data`.local.entity.sie.StudyPlanEntity
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
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AcademicDao_Impl(
  __db: RoomDatabase,
) : AcademicDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAchievementEntity: EntityInsertAdapter<AchievementEntity>

  private val __insertAdapterOfAcademicGradeEntity: EntityInsertAdapter<AcademicGradeEntity>

  private val __insertAdapterOfDisciplineRecordEntity: EntityInsertAdapter<DisciplineRecordEntity>

  private val __insertAdapterOfStudyPlanEntity: EntityInsertAdapter<StudyPlanEntity>

  private val __insertAdapterOfAreaPlanEntity: EntityInsertAdapter<AreaPlanEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAchievementEntity = object : EntityInsertAdapter<AchievementEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `achievements` (`id`,`subjectId`,`gradeId`,`period`,`description`,`type`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AchievementEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.subjectId)
        statement.bindText(3, entity.gradeId)
        statement.bindLong(4, entity.period.toLong())
        statement.bindText(5, entity.description)
        statement.bindText(6, entity.type)
      }
    }
    this.__insertAdapterOfAcademicGradeEntity = object : EntityInsertAdapter<AcademicGradeEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_grades` (`id`,`studentId`,`subjectId`,`period`,`value`,`achievementIds`,`observations`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AcademicGradeEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.studentId)
        statement.bindText(3, entity.subjectId)
        statement.bindLong(4, entity.period.toLong())
        statement.bindDouble(5, entity.value)
        statement.bindText(6, entity.achievementIds)
        val _tmpObservations: String? = entity.observations
        if (_tmpObservations == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpObservations)
        }
        statement.bindLong(8, entity.updatedAt)
      }
    }
    this.__insertAdapterOfDisciplineRecordEntity = object :
        EntityInsertAdapter<DisciplineRecordEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `discipline_records` (`id`,`studentId`,`type`,`description`,`date`,`teacherId`,`impactOnGrade`,`parentNotified`,`parentAttended`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DisciplineRecordEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.studentId)
        statement.bindText(3, entity.type)
        statement.bindText(4, entity.description)
        statement.bindLong(5, entity.date)
        statement.bindText(6, entity.teacherId)
        statement.bindDouble(7, entity.impactOnGrade)
        val _tmp: Int = if (entity.parentNotified) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        val _tmpParentAttended: Boolean? = entity.parentAttended
        val _tmp_1: Int? = _tmpParentAttended?.let { if (it) 1 else 0 }
        if (_tmp_1 == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmp_1.toLong())
        }
      }
    }
    this.__insertAdapterOfStudyPlanEntity = object : EntityInsertAdapter<StudyPlanEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `study_plans` (`id`,`title`,`version`,`lastUpdated`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: StudyPlanEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.version)
        statement.bindLong(4, entity.lastUpdated)
      }
    }
    this.__insertAdapterOfAreaPlanEntity = object : EntityInsertAdapter<AreaPlanEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `area_plans` (`id`,`studyPlanId`,`name`,`intensity`,`subjectIds`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AreaPlanEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.studyPlanId)
        statement.bindText(3, entity.name)
        statement.bindLong(4, entity.intensity.toLong())
        statement.bindText(5, entity.subjectIds)
      }
    }
  }

  public override suspend fun insertAchievement(achievement: AchievementEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAchievementEntity.insert(_connection, achievement)
  }

  public override suspend fun insertGrade(grade: AcademicGradeEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAcademicGradeEntity.insert(_connection, grade)
  }

  public override suspend fun insertDisciplineRecord(record: DisciplineRecordEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfDisciplineRecordEntity.insert(_connection, record)
  }

  public override suspend fun insertStudyPlan(plan: StudyPlanEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfStudyPlanEntity.insert(_connection, plan)
  }

  public override suspend fun insertAreaPlan(area: AreaPlanEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfAreaPlanEntity.insert(_connection, area)
  }

  public override fun getAchievements(
    subjectId: String,
    gradeId: String,
    period: Int,
  ): Flow<List<AchievementEntity>> {
    val _sql: String =
        "SELECT * FROM achievements WHERE subjectId = ? AND gradeId = ? AND period = ?"
    return createFlow(__db, false, arrayOf("achievements")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, subjectId)
        _argIndex = 2
        _stmt.bindText(_argIndex, gradeId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, period.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfGradeId: Int = getColumnIndexOrThrow(_stmt, "gradeId")
        val _columnIndexOfPeriod: Int = getColumnIndexOrThrow(_stmt, "period")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _result: MutableList<AchievementEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AchievementEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpGradeId: String
          _tmpGradeId = _stmt.getText(_columnIndexOfGradeId)
          val _tmpPeriod: Int
          _tmpPeriod = _stmt.getLong(_columnIndexOfPeriod).toInt()
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          _item =
              AchievementEntity(_tmpId,_tmpSubjectId,_tmpGradeId,_tmpPeriod,_tmpDescription,_tmpType)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGrade(
    studentId: String,
    subjectId: String,
    period: Int,
  ): Flow<AcademicGradeEntity?> {
    val _sql: String =
        "SELECT * FROM academic_grades WHERE studentId = ? AND subjectId = ? AND period = ?"
    return createFlow(__db, false, arrayOf("academic_grades")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, subjectId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, period.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfPeriod: Int = getColumnIndexOrThrow(_stmt, "period")
        val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
        val _columnIndexOfAchievementIds: Int = getColumnIndexOrThrow(_stmt, "achievementIds")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: AcademicGradeEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpPeriod: Int
          _tmpPeriod = _stmt.getLong(_columnIndexOfPeriod).toInt()
          val _tmpValue: Double
          _tmpValue = _stmt.getDouble(_columnIndexOfValue)
          val _tmpAchievementIds: String
          _tmpAchievementIds = _stmt.getText(_columnIndexOfAchievementIds)
          val _tmpObservations: String?
          if (_stmt.isNull(_columnIndexOfObservations)) {
            _tmpObservations = null
          } else {
            _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          }
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result =
              AcademicGradeEntity(_tmpId,_tmpStudentId,_tmpSubjectId,_tmpPeriod,_tmpValue,_tmpAchievementIds,_tmpObservations,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDisciplineRecords(studentId: String): Flow<List<DisciplineRecordEntity>> {
    val _sql: String = "SELECT * FROM discipline_records WHERE studentId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("discipline_records")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfImpactOnGrade: Int = getColumnIndexOrThrow(_stmt, "impactOnGrade")
        val _columnIndexOfParentNotified: Int = getColumnIndexOrThrow(_stmt, "parentNotified")
        val _columnIndexOfParentAttended: Int = getColumnIndexOrThrow(_stmt, "parentAttended")
        val _result: MutableList<DisciplineRecordEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DisciplineRecordEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpTeacherId: String
          _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          val _tmpImpactOnGrade: Double
          _tmpImpactOnGrade = _stmt.getDouble(_columnIndexOfImpactOnGrade)
          val _tmpParentNotified: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfParentNotified).toInt()
          _tmpParentNotified = _tmp != 0
          val _tmpParentAttended: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfParentAttended)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfParentAttended).toInt()
          }
          _tmpParentAttended = _tmp_1?.let { it != 0 }
          _item =
              DisciplineRecordEntity(_tmpId,_tmpStudentId,_tmpType,_tmpDescription,_tmpDate,_tmpTeacherId,_tmpImpactOnGrade,_tmpParentNotified,_tmpParentAttended)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDisciplineRecordsForStudents(studentIds: List<String>):
      Flow<List<DisciplineRecordEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM discipline_records WHERE studentId IN (")
    val _inputSize: Int = studentIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") ORDER BY studentId, date DESC")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("discipline_records")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in studentIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfImpactOnGrade: Int = getColumnIndexOrThrow(_stmt, "impactOnGrade")
        val _columnIndexOfParentNotified: Int = getColumnIndexOrThrow(_stmt, "parentNotified")
        val _columnIndexOfParentAttended: Int = getColumnIndexOrThrow(_stmt, "parentAttended")
        val _result: MutableList<DisciplineRecordEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: DisciplineRecordEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpTeacherId: String
          _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          val _tmpImpactOnGrade: Double
          _tmpImpactOnGrade = _stmt.getDouble(_columnIndexOfImpactOnGrade)
          val _tmpParentNotified: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfParentNotified).toInt()
          _tmpParentNotified = _tmp != 0
          val _tmpParentAttended: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfParentAttended)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfParentAttended).toInt()
          }
          _tmpParentAttended = _tmp_1?.let { it != 0 }
          _item_1 =
              DisciplineRecordEntity(_tmpId,_tmpStudentId,_tmpType,_tmpDescription,_tmpDate,_tmpTeacherId,_tmpImpactOnGrade,_tmpParentNotified,_tmpParentAttended)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalDisciplineImpact(studentId: String): Flow<Double?> {
    val _sql: String = "SELECT SUM(impactOnGrade) FROM discipline_records WHERE studentId = ?"
    return createFlow(__db, false, arrayOf("discipline_records")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
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

  public override fun getAllStudyPlans(): Flow<List<StudyPlanEntity>> {
    val _sql: String = "SELECT * FROM study_plans"
    return createFlow(__db, false, arrayOf("study_plans")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfLastUpdated: Int = getColumnIndexOrThrow(_stmt, "lastUpdated")
        val _result: MutableList<StudyPlanEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudyPlanEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpVersion: String
          _tmpVersion = _stmt.getText(_columnIndexOfVersion)
          val _tmpLastUpdated: Long
          _tmpLastUpdated = _stmt.getLong(_columnIndexOfLastUpdated)
          _item = StudyPlanEntity(_tmpId,_tmpTitle,_tmpVersion,_tmpLastUpdated)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAreasForPlan(planId: String): Flow<List<AreaPlanEntity>> {
    val _sql: String = "SELECT * FROM area_plans WHERE studyPlanId = ?"
    return createFlow(__db, true, arrayOf("area_plans")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, planId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudyPlanId: Int = getColumnIndexOrThrow(_stmt, "studyPlanId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIntensity: Int = getColumnIndexOrThrow(_stmt, "intensity")
        val _columnIndexOfSubjectIds: Int = getColumnIndexOrThrow(_stmt, "subjectIds")
        val _result: MutableList<AreaPlanEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AreaPlanEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudyPlanId: String
          _tmpStudyPlanId = _stmt.getText(_columnIndexOfStudyPlanId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIntensity: Int
          _tmpIntensity = _stmt.getLong(_columnIndexOfIntensity).toInt()
          val _tmpSubjectIds: String
          _tmpSubjectIds = _stmt.getText(_columnIndexOfSubjectIds)
          _item = AreaPlanEntity(_tmpId,_tmpStudyPlanId,_tmpName,_tmpIntensity,_tmpSubjectIds)
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

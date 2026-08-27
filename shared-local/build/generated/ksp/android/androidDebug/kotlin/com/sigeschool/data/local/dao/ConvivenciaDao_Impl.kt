package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.BehavioralCompetencyEntity
import com.sigeschool.`data`.local.entity.BehavioralScoreEntity
import com.sigeschool.`data`.local.entity.ConvivenciaCaseEntity
import com.sigeschool.`data`.local.entity.FamilyAttendanceEntity
import com.sigeschool.`data`.local.entity.TestimonyEntity
import javax.`annotation`.processing.Generated
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
public class ConvivenciaDao_Impl(
  __db: RoomDatabase,
) : ConvivenciaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfConvivenciaCaseEntity: EntityInsertAdapter<ConvivenciaCaseEntity>

  private val __insertAdapterOfTestimonyEntity: EntityInsertAdapter<TestimonyEntity>

  private val __insertAdapterOfBehavioralCompetencyEntity:
      EntityInsertAdapter<BehavioralCompetencyEntity>

  private val __insertAdapterOfBehavioralScoreEntity: EntityInsertAdapter<BehavioralScoreEntity>

  private val __insertAdapterOfFamilyAttendanceEntity: EntityInsertAdapter<FamilyAttendanceEntity>

  private val __updateAdapterOfConvivenciaCaseEntity:
      EntityDeleteOrUpdateAdapter<ConvivenciaCaseEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfConvivenciaCaseEntity = object :
        EntityInsertAdapter<ConvivenciaCaseEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `convivencia_cases` (`id`,`institutionId`,`studentId`,`teacherId`,`createdByUserId`,`openingDate`,`status`,`description`,`resolution`,`resolutionDate`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConvivenciaCaseEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.teacherId)
        statement.bindText(5, entity.createdByUserId)
        statement.bindLong(6, entity.openingDate)
        statement.bindText(7, entity.status)
        statement.bindText(8, entity.description)
        val _tmpResolution: String? = entity.resolution
        if (_tmpResolution == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpResolution)
        }
        val _tmpResolutionDate: Long? = entity.resolutionDate
        if (_tmpResolutionDate == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpResolutionDate)
        }
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
      }
    }
    this.__insertAdapterOfTestimonyEntity = object : EntityInsertAdapter<TestimonyEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `case_testimonies` (`id`,`institutionId`,`caseId`,`authorName`,`authorRole`,`content`,`createdAt`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: TestimonyEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.caseId)
        statement.bindText(4, entity.authorName)
        statement.bindText(5, entity.authorRole)
        statement.bindText(6, entity.content)
        statement.bindLong(7, entity.createdAt)
      }
    }
    this.__insertAdapterOfBehavioralCompetencyEntity = object :
        EntityInsertAdapter<BehavioralCompetencyEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `behavioral_competencies` (`id`,`institutionId`,`name`,`description`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BehavioralCompetencyEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.name)
        statement.bindText(4, entity.description)
      }
    }
    this.__insertAdapterOfBehavioralScoreEntity = object :
        EntityInsertAdapter<BehavioralScoreEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `behavioral_scores` (`id`,`institutionId`,`studentId`,`competencyId`,`periodId`,`scoreType`,`feedback`,`evaluationDate`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BehavioralScoreEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.competencyId)
        statement.bindText(5, entity.periodId)
        statement.bindText(6, entity.scoreType)
        val _tmpFeedback: String? = entity.feedback
        if (_tmpFeedback == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpFeedback)
        }
        statement.bindLong(8, entity.evaluationDate)
      }
    }
    this.__insertAdapterOfFamilyAttendanceEntity = object :
        EntityInsertAdapter<FamilyAttendanceEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `family_attendance` (`id`,`institutionId`,`studentId`,`parentName`,`citationDate`,`attendanceDate`,`status`,`meetingNotes`,`behavioralImpact`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FamilyAttendanceEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.parentName)
        statement.bindLong(5, entity.citationDate)
        val _tmpAttendanceDate: Long? = entity.attendanceDate
        if (_tmpAttendanceDate == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpAttendanceDate)
        }
        statement.bindText(7, entity.status)
        val _tmpMeetingNotes: String? = entity.meetingNotes
        if (_tmpMeetingNotes == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpMeetingNotes)
        }
        val _tmpBehavioralImpact: String? = entity.behavioralImpact
        if (_tmpBehavioralImpact == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpBehavioralImpact)
        }
      }
    }
    this.__updateAdapterOfConvivenciaCaseEntity = object :
        EntityDeleteOrUpdateAdapter<ConvivenciaCaseEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `convivencia_cases` SET `id` = ?,`institutionId` = ?,`studentId` = ?,`teacherId` = ?,`createdByUserId` = ?,`openingDate` = ?,`status` = ?,`description` = ?,`resolution` = ?,`resolutionDate` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ConvivenciaCaseEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.teacherId)
        statement.bindText(5, entity.createdByUserId)
        statement.bindLong(6, entity.openingDate)
        statement.bindText(7, entity.status)
        statement.bindText(8, entity.description)
        val _tmpResolution: String? = entity.resolution
        if (_tmpResolution == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpResolution)
        }
        val _tmpResolutionDate: Long? = entity.resolutionDate
        if (_tmpResolutionDate == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpResolutionDate)
        }
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
        statement.bindText(13, entity.id)
      }
    }
  }

  public override suspend fun insertCase(case: ConvivenciaCaseEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfConvivenciaCaseEntity.insert(_connection, case)
  }

  public override suspend fun insertTestimony(testimony: TestimonyEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTestimonyEntity.insert(_connection, testimony)
  }

  public override suspend fun insertCompetency(competency: BehavioralCompetencyEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBehavioralCompetencyEntity.insert(_connection, competency)
  }

  public override suspend fun insertBehavioralScore(score: BehavioralScoreEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBehavioralScoreEntity.insert(_connection, score)
  }

  public override suspend fun insertFamilyAttendance(attendance: FamilyAttendanceEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfFamilyAttendanceEntity.insert(_connection, attendance)
  }

  public override suspend fun updateCase(case: ConvivenciaCaseEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfConvivenciaCaseEntity.handle(_connection, case)
  }

  public override fun getCasesByStudent(institutionId: String, studentId: String):
      Flow<List<ConvivenciaCaseEntity>> {
    val _sql: String =
        "SELECT * FROM convivencia_cases WHERE institutionId = ? AND studentId = ? ORDER BY openingDate DESC"
    return createFlow(__db, false, arrayOf("convivencia_cases")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, studentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfCreatedByUserId: Int = getColumnIndexOrThrow(_stmt, "createdByUserId")
        val _columnIndexOfOpeningDate: Int = getColumnIndexOrThrow(_stmt, "openingDate")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfResolution: Int = getColumnIndexOrThrow(_stmt, "resolution")
        val _columnIndexOfResolutionDate: Int = getColumnIndexOrThrow(_stmt, "resolutionDate")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ConvivenciaCaseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConvivenciaCaseEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTeacherId: String
          _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          val _tmpCreatedByUserId: String
          _tmpCreatedByUserId = _stmt.getText(_columnIndexOfCreatedByUserId)
          val _tmpOpeningDate: Long
          _tmpOpeningDate = _stmt.getLong(_columnIndexOfOpeningDate)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpResolution: String?
          if (_stmt.isNull(_columnIndexOfResolution)) {
            _tmpResolution = null
          } else {
            _tmpResolution = _stmt.getText(_columnIndexOfResolution)
          }
          val _tmpResolutionDate: Long?
          if (_stmt.isNull(_columnIndexOfResolutionDate)) {
            _tmpResolutionDate = null
          } else {
            _tmpResolutionDate = _stmt.getLong(_columnIndexOfResolutionDate)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ConvivenciaCaseEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTeacherId,_tmpCreatedByUserId,_tmpOpeningDate,_tmpStatus,_tmpDescription,_tmpResolution,_tmpResolutionDate,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCaseById(caseId: String, institutionId: String):
      ConvivenciaCaseEntity? {
    val _sql: String = "SELECT * FROM convivencia_cases WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, caseId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfCreatedByUserId: Int = getColumnIndexOrThrow(_stmt, "createdByUserId")
        val _columnIndexOfOpeningDate: Int = getColumnIndexOrThrow(_stmt, "openingDate")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfResolution: Int = getColumnIndexOrThrow(_stmt, "resolution")
        val _columnIndexOfResolutionDate: Int = getColumnIndexOrThrow(_stmt, "resolutionDate")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ConvivenciaCaseEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTeacherId: String
          _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          val _tmpCreatedByUserId: String
          _tmpCreatedByUserId = _stmt.getText(_columnIndexOfCreatedByUserId)
          val _tmpOpeningDate: Long
          _tmpOpeningDate = _stmt.getLong(_columnIndexOfOpeningDate)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpResolution: String?
          if (_stmt.isNull(_columnIndexOfResolution)) {
            _tmpResolution = null
          } else {
            _tmpResolution = _stmt.getText(_columnIndexOfResolution)
          }
          val _tmpResolutionDate: Long?
          if (_stmt.isNull(_columnIndexOfResolutionDate)) {
            _tmpResolutionDate = null
          } else {
            _tmpResolutionDate = _stmt.getLong(_columnIndexOfResolutionDate)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ConvivenciaCaseEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTeacherId,_tmpCreatedByUserId,_tmpOpeningDate,_tmpStatus,_tmpDescription,_tmpResolution,_tmpResolutionDate,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTestimoniesByCase(caseId: String, institutionId: String):
      Flow<List<TestimonyEntity>> {
    val _sql: String =
        "SELECT * FROM case_testimonies WHERE caseId = ? AND institutionId = ? ORDER BY createdAt ASC"
    return createFlow(__db, false, arrayOf("case_testimonies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, caseId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCaseId: Int = getColumnIndexOrThrow(_stmt, "caseId")
        val _columnIndexOfAuthorName: Int = getColumnIndexOrThrow(_stmt, "authorName")
        val _columnIndexOfAuthorRole: Int = getColumnIndexOrThrow(_stmt, "authorRole")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<TestimonyEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: TestimonyEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCaseId: String
          _tmpCaseId = _stmt.getText(_columnIndexOfCaseId)
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_columnIndexOfAuthorName)
          val _tmpAuthorRole: String
          _tmpAuthorRole = _stmt.getText(_columnIndexOfAuthorRole)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item =
              TestimonyEntity(_tmpId,_tmpInstitutionId,_tmpCaseId,_tmpAuthorName,_tmpAuthorRole,_tmpContent,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllCompetencies(institutionId: String):
      Flow<List<BehavioralCompetencyEntity>> {
    val _sql: String = "SELECT * FROM behavioral_competencies WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("behavioral_competencies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _result: MutableList<BehavioralCompetencyEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BehavioralCompetencyEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          _item = BehavioralCompetencyEntity(_tmpId,_tmpInstitutionId,_tmpName,_tmpDescription)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getScoresByStudent(studentId: String, institutionId: String):
      Flow<List<BehavioralScoreEntity>> {
    val _sql: String = "SELECT * FROM behavioral_scores WHERE studentId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("behavioral_scores")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfCompetencyId: Int = getColumnIndexOrThrow(_stmt, "competencyId")
        val _columnIndexOfPeriodId: Int = getColumnIndexOrThrow(_stmt, "periodId")
        val _columnIndexOfScoreType: Int = getColumnIndexOrThrow(_stmt, "scoreType")
        val _columnIndexOfFeedback: Int = getColumnIndexOrThrow(_stmt, "feedback")
        val _columnIndexOfEvaluationDate: Int = getColumnIndexOrThrow(_stmt, "evaluationDate")
        val _result: MutableList<BehavioralScoreEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BehavioralScoreEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpCompetencyId: String
          _tmpCompetencyId = _stmt.getText(_columnIndexOfCompetencyId)
          val _tmpPeriodId: String
          _tmpPeriodId = _stmt.getText(_columnIndexOfPeriodId)
          val _tmpScoreType: String
          _tmpScoreType = _stmt.getText(_columnIndexOfScoreType)
          val _tmpFeedback: String?
          if (_stmt.isNull(_columnIndexOfFeedback)) {
            _tmpFeedback = null
          } else {
            _tmpFeedback = _stmt.getText(_columnIndexOfFeedback)
          }
          val _tmpEvaluationDate: Long
          _tmpEvaluationDate = _stmt.getLong(_columnIndexOfEvaluationDate)
          _item =
              BehavioralScoreEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpCompetencyId,_tmpPeriodId,_tmpScoreType,_tmpFeedback,_tmpEvaluationDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFamilyAttendanceByStudent(studentId: String, institutionId: String):
      Flow<List<FamilyAttendanceEntity>> {
    val _sql: String =
        "SELECT * FROM family_attendance WHERE studentId = ? AND institutionId = ? ORDER BY citationDate DESC"
    return createFlow(__db, false, arrayOf("family_attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfParentName: Int = getColumnIndexOrThrow(_stmt, "parentName")
        val _columnIndexOfCitationDate: Int = getColumnIndexOrThrow(_stmt, "citationDate")
        val _columnIndexOfAttendanceDate: Int = getColumnIndexOrThrow(_stmt, "attendanceDate")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfMeetingNotes: Int = getColumnIndexOrThrow(_stmt, "meetingNotes")
        val _columnIndexOfBehavioralImpact: Int = getColumnIndexOrThrow(_stmt, "behavioralImpact")
        val _result: MutableList<FamilyAttendanceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FamilyAttendanceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpParentName: String
          _tmpParentName = _stmt.getText(_columnIndexOfParentName)
          val _tmpCitationDate: Long
          _tmpCitationDate = _stmt.getLong(_columnIndexOfCitationDate)
          val _tmpAttendanceDate: Long?
          if (_stmt.isNull(_columnIndexOfAttendanceDate)) {
            _tmpAttendanceDate = null
          } else {
            _tmpAttendanceDate = _stmt.getLong(_columnIndexOfAttendanceDate)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpMeetingNotes: String?
          if (_stmt.isNull(_columnIndexOfMeetingNotes)) {
            _tmpMeetingNotes = null
          } else {
            _tmpMeetingNotes = _stmt.getText(_columnIndexOfMeetingNotes)
          }
          val _tmpBehavioralImpact: String?
          if (_stmt.isNull(_columnIndexOfBehavioralImpact)) {
            _tmpBehavioralImpact = null
          } else {
            _tmpBehavioralImpact = _stmt.getText(_columnIndexOfBehavioralImpact)
          }
          _item =
              FamilyAttendanceEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpParentName,_tmpCitationDate,_tmpAttendanceDate,_tmpStatus,_tmpMeetingNotes,_tmpBehavioralImpact)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteCaseById(caseId: String, institutionId: String) {
    val _sql: String = "DELETE FROM convivencia_cases WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, caseId)
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

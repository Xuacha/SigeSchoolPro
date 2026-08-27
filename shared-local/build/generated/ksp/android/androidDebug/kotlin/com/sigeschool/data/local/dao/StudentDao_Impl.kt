package com.sigeschool.`data`.local.dao

import androidx.collection.ArrayMap
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchArrayMap
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ProgramEntity
import com.sigeschool.`data`.local.entity.StudentEntity
import com.sigeschool.`data`.local.entity.StudentWithPrograms
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class StudentDao_Impl(
  __db: RoomDatabase,
) : StudentDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfStudentEntity: EntityInsertAdapter<StudentEntity>

  private val __updateAdapterOfStudentEntity: EntityDeleteOrUpdateAdapter<StudentEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfStudentEntity = object : EntityInsertAdapter<StudentEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `students` (`id`,`institutionId`,`userId`,`firstName`,`lastName`,`documentId`,`gender`,`ethnicity`,`ethnicCommunity`,`disability`,`disabilityAdjustments`,`photoPath`,`qrCode`,`cursoId`,`consentAcceptedAt`,`consentVersion`,`syncStatus`,`lastModified`,`isDuplicate`,`mergedIntoId`,`deletedAt`,`deletedReason`,`deletedByUserId`,`documentType`,`birthDate`,`age`,`email`,`phone`,`address`,`neighborhood`,`stratum`,`educationLevel`,`previousSchool`,`selectedPrograms`,`howDidYouHear`,`status`,`withdrawalReason`,`withdrawalDate`,`statusUpdatedAt`,`photoUpdatedAt`,`estadoMatricula`,`fechaRetiro`,`motivoRetiro`,`diasInasistenciaConsecutiva`,`ultimaFechaAsistencia`,`alertaEnviada30Dias`,`guardianFirstName`,`guardianLastName`,`guardianDocumentId`,`guardianRelationship`,`guardianPhone`,`guardianEmail`,`esExterno`,`institucionOrigen`,`externoId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: StudentEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        val _tmpUserId: String? = entity.userId
        if (_tmpUserId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpUserId)
        }
        statement.bindText(4, entity.firstName)
        statement.bindText(5, entity.lastName)
        statement.bindText(6, entity.documentId)
        val _tmpGender: String? = entity.gender
        if (_tmpGender == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpGender)
        }
        val _tmpEthnicity: String? = entity.ethnicity
        if (_tmpEthnicity == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpEthnicity)
        }
        val _tmpEthnicCommunity: String? = entity.ethnicCommunity
        if (_tmpEthnicCommunity == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpEthnicCommunity)
        }
        val _tmpDisability: String? = entity.disability
        if (_tmpDisability == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpDisability)
        }
        val _tmpDisabilityAdjustments: String? = entity.disabilityAdjustments
        if (_tmpDisabilityAdjustments == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpDisabilityAdjustments)
        }
        val _tmpPhotoPath: String? = entity.photoPath
        if (_tmpPhotoPath == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpPhotoPath)
        }
        statement.bindText(13, entity.qrCode)
        val _tmpCursoId: Long? = entity.cursoId
        if (_tmpCursoId == null) {
          statement.bindNull(14)
        } else {
          statement.bindLong(14, _tmpCursoId)
        }
        val _tmpConsentAcceptedAt: Long? = entity.consentAcceptedAt
        if (_tmpConsentAcceptedAt == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpConsentAcceptedAt)
        }
        val _tmpConsentVersion: String? = entity.consentVersion
        if (_tmpConsentVersion == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpConsentVersion)
        }
        statement.bindLong(17, entity.syncStatus.toLong())
        statement.bindLong(18, entity.lastModified)
        val _tmp: Int = if (entity.isDuplicate) 1 else 0
        statement.bindLong(19, _tmp.toLong())
        val _tmpMergedIntoId: String? = entity.mergedIntoId
        if (_tmpMergedIntoId == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpMergedIntoId)
        }
        val _tmpDeletedAt: Long? = entity.deletedAt
        if (_tmpDeletedAt == null) {
          statement.bindNull(21)
        } else {
          statement.bindLong(21, _tmpDeletedAt)
        }
        val _tmpDeletedReason: String? = entity.deletedReason
        if (_tmpDeletedReason == null) {
          statement.bindNull(22)
        } else {
          statement.bindText(22, _tmpDeletedReason)
        }
        val _tmpDeletedByUserId: String? = entity.deletedByUserId
        if (_tmpDeletedByUserId == null) {
          statement.bindNull(23)
        } else {
          statement.bindText(23, _tmpDeletedByUserId)
        }
        val _tmpDocumentType: String? = entity.documentType
        if (_tmpDocumentType == null) {
          statement.bindNull(24)
        } else {
          statement.bindText(24, _tmpDocumentType)
        }
        val _tmpBirthDate: String? = entity.birthDate
        if (_tmpBirthDate == null) {
          statement.bindNull(25)
        } else {
          statement.bindText(25, _tmpBirthDate)
        }
        val _tmpAge: Int? = entity.age
        if (_tmpAge == null) {
          statement.bindNull(26)
        } else {
          statement.bindLong(26, _tmpAge.toLong())
        }
        val _tmpEmail: String? = entity.email
        if (_tmpEmail == null) {
          statement.bindNull(27)
        } else {
          statement.bindText(27, _tmpEmail)
        }
        val _tmpPhone: String? = entity.phone
        if (_tmpPhone == null) {
          statement.bindNull(28)
        } else {
          statement.bindText(28, _tmpPhone)
        }
        val _tmpAddress: String? = entity.address
        if (_tmpAddress == null) {
          statement.bindNull(29)
        } else {
          statement.bindText(29, _tmpAddress)
        }
        val _tmpNeighborhood: String? = entity.neighborhood
        if (_tmpNeighborhood == null) {
          statement.bindNull(30)
        } else {
          statement.bindText(30, _tmpNeighborhood)
        }
        val _tmpStratum: Int? = entity.stratum
        if (_tmpStratum == null) {
          statement.bindNull(31)
        } else {
          statement.bindLong(31, _tmpStratum.toLong())
        }
        val _tmpEducationLevel: String? = entity.educationLevel
        if (_tmpEducationLevel == null) {
          statement.bindNull(32)
        } else {
          statement.bindText(32, _tmpEducationLevel)
        }
        val _tmpPreviousSchool: String? = entity.previousSchool
        if (_tmpPreviousSchool == null) {
          statement.bindNull(33)
        } else {
          statement.bindText(33, _tmpPreviousSchool)
        }
        val _tmpSelectedPrograms: String? = entity.selectedPrograms
        if (_tmpSelectedPrograms == null) {
          statement.bindNull(34)
        } else {
          statement.bindText(34, _tmpSelectedPrograms)
        }
        val _tmpHowDidYouHear: String? = entity.howDidYouHear
        if (_tmpHowDidYouHear == null) {
          statement.bindNull(35)
        } else {
          statement.bindText(35, _tmpHowDidYouHear)
        }
        statement.bindText(36, entity.status)
        val _tmpWithdrawalReason: String? = entity.withdrawalReason
        if (_tmpWithdrawalReason == null) {
          statement.bindNull(37)
        } else {
          statement.bindText(37, _tmpWithdrawalReason)
        }
        val _tmpWithdrawalDate: Long? = entity.withdrawalDate
        if (_tmpWithdrawalDate == null) {
          statement.bindNull(38)
        } else {
          statement.bindLong(38, _tmpWithdrawalDate)
        }
        statement.bindLong(39, entity.statusUpdatedAt)
        val _tmpPhotoUpdatedAt: Long? = entity.photoUpdatedAt
        if (_tmpPhotoUpdatedAt == null) {
          statement.bindNull(40)
        } else {
          statement.bindLong(40, _tmpPhotoUpdatedAt)
        }
        statement.bindText(41, entity.estadoMatricula)
        val _tmpFechaRetiro: Long? = entity.fechaRetiro
        if (_tmpFechaRetiro == null) {
          statement.bindNull(42)
        } else {
          statement.bindLong(42, _tmpFechaRetiro)
        }
        val _tmpMotivoRetiro: String? = entity.motivoRetiro
        if (_tmpMotivoRetiro == null) {
          statement.bindNull(43)
        } else {
          statement.bindText(43, _tmpMotivoRetiro)
        }
        statement.bindLong(44, entity.diasInasistenciaConsecutiva.toLong())
        val _tmpUltimaFechaAsistencia: Long? = entity.ultimaFechaAsistencia
        if (_tmpUltimaFechaAsistencia == null) {
          statement.bindNull(45)
        } else {
          statement.bindLong(45, _tmpUltimaFechaAsistencia)
        }
        val _tmp_1: Int = if (entity.alertaEnviada30Dias) 1 else 0
        statement.bindLong(46, _tmp_1.toLong())
        val _tmpGuardianFirstName: String? = entity.guardianFirstName
        if (_tmpGuardianFirstName == null) {
          statement.bindNull(47)
        } else {
          statement.bindText(47, _tmpGuardianFirstName)
        }
        val _tmpGuardianLastName: String? = entity.guardianLastName
        if (_tmpGuardianLastName == null) {
          statement.bindNull(48)
        } else {
          statement.bindText(48, _tmpGuardianLastName)
        }
        val _tmpGuardianDocumentId: String? = entity.guardianDocumentId
        if (_tmpGuardianDocumentId == null) {
          statement.bindNull(49)
        } else {
          statement.bindText(49, _tmpGuardianDocumentId)
        }
        val _tmpGuardianRelationship: String? = entity.guardianRelationship
        if (_tmpGuardianRelationship == null) {
          statement.bindNull(50)
        } else {
          statement.bindText(50, _tmpGuardianRelationship)
        }
        val _tmpGuardianPhone: String? = entity.guardianPhone
        if (_tmpGuardianPhone == null) {
          statement.bindNull(51)
        } else {
          statement.bindText(51, _tmpGuardianPhone)
        }
        val _tmpGuardianEmail: String? = entity.guardianEmail
        if (_tmpGuardianEmail == null) {
          statement.bindNull(52)
        } else {
          statement.bindText(52, _tmpGuardianEmail)
        }
        val _tmp_2: Int = if (entity.esExterno) 1 else 0
        statement.bindLong(53, _tmp_2.toLong())
        val _tmpInstitucionOrigen: String? = entity.institucionOrigen
        if (_tmpInstitucionOrigen == null) {
          statement.bindNull(54)
        } else {
          statement.bindText(54, _tmpInstitucionOrigen)
        }
        val _tmpExternoId: String? = entity.externoId
        if (_tmpExternoId == null) {
          statement.bindNull(55)
        } else {
          statement.bindText(55, _tmpExternoId)
        }
      }
    }
    this.__updateAdapterOfStudentEntity = object : EntityDeleteOrUpdateAdapter<StudentEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `students` SET `id` = ?,`institutionId` = ?,`userId` = ?,`firstName` = ?,`lastName` = ?,`documentId` = ?,`gender` = ?,`ethnicity` = ?,`ethnicCommunity` = ?,`disability` = ?,`disabilityAdjustments` = ?,`photoPath` = ?,`qrCode` = ?,`cursoId` = ?,`consentAcceptedAt` = ?,`consentVersion` = ?,`syncStatus` = ?,`lastModified` = ?,`isDuplicate` = ?,`mergedIntoId` = ?,`deletedAt` = ?,`deletedReason` = ?,`deletedByUserId` = ?,`documentType` = ?,`birthDate` = ?,`age` = ?,`email` = ?,`phone` = ?,`address` = ?,`neighborhood` = ?,`stratum` = ?,`educationLevel` = ?,`previousSchool` = ?,`selectedPrograms` = ?,`howDidYouHear` = ?,`status` = ?,`withdrawalReason` = ?,`withdrawalDate` = ?,`statusUpdatedAt` = ?,`photoUpdatedAt` = ?,`estadoMatricula` = ?,`fechaRetiro` = ?,`motivoRetiro` = ?,`diasInasistenciaConsecutiva` = ?,`ultimaFechaAsistencia` = ?,`alertaEnviada30Dias` = ?,`guardianFirstName` = ?,`guardianLastName` = ?,`guardianDocumentId` = ?,`guardianRelationship` = ?,`guardianPhone` = ?,`guardianEmail` = ?,`esExterno` = ?,`institucionOrigen` = ?,`externoId` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: StudentEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        val _tmpUserId: String? = entity.userId
        if (_tmpUserId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpUserId)
        }
        statement.bindText(4, entity.firstName)
        statement.bindText(5, entity.lastName)
        statement.bindText(6, entity.documentId)
        val _tmpGender: String? = entity.gender
        if (_tmpGender == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpGender)
        }
        val _tmpEthnicity: String? = entity.ethnicity
        if (_tmpEthnicity == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpEthnicity)
        }
        val _tmpEthnicCommunity: String? = entity.ethnicCommunity
        if (_tmpEthnicCommunity == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpEthnicCommunity)
        }
        val _tmpDisability: String? = entity.disability
        if (_tmpDisability == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpDisability)
        }
        val _tmpDisabilityAdjustments: String? = entity.disabilityAdjustments
        if (_tmpDisabilityAdjustments == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpDisabilityAdjustments)
        }
        val _tmpPhotoPath: String? = entity.photoPath
        if (_tmpPhotoPath == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpPhotoPath)
        }
        statement.bindText(13, entity.qrCode)
        val _tmpCursoId: Long? = entity.cursoId
        if (_tmpCursoId == null) {
          statement.bindNull(14)
        } else {
          statement.bindLong(14, _tmpCursoId)
        }
        val _tmpConsentAcceptedAt: Long? = entity.consentAcceptedAt
        if (_tmpConsentAcceptedAt == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpConsentAcceptedAt)
        }
        val _tmpConsentVersion: String? = entity.consentVersion
        if (_tmpConsentVersion == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpConsentVersion)
        }
        statement.bindLong(17, entity.syncStatus.toLong())
        statement.bindLong(18, entity.lastModified)
        val _tmp: Int = if (entity.isDuplicate) 1 else 0
        statement.bindLong(19, _tmp.toLong())
        val _tmpMergedIntoId: String? = entity.mergedIntoId
        if (_tmpMergedIntoId == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpMergedIntoId)
        }
        val _tmpDeletedAt: Long? = entity.deletedAt
        if (_tmpDeletedAt == null) {
          statement.bindNull(21)
        } else {
          statement.bindLong(21, _tmpDeletedAt)
        }
        val _tmpDeletedReason: String? = entity.deletedReason
        if (_tmpDeletedReason == null) {
          statement.bindNull(22)
        } else {
          statement.bindText(22, _tmpDeletedReason)
        }
        val _tmpDeletedByUserId: String? = entity.deletedByUserId
        if (_tmpDeletedByUserId == null) {
          statement.bindNull(23)
        } else {
          statement.bindText(23, _tmpDeletedByUserId)
        }
        val _tmpDocumentType: String? = entity.documentType
        if (_tmpDocumentType == null) {
          statement.bindNull(24)
        } else {
          statement.bindText(24, _tmpDocumentType)
        }
        val _tmpBirthDate: String? = entity.birthDate
        if (_tmpBirthDate == null) {
          statement.bindNull(25)
        } else {
          statement.bindText(25, _tmpBirthDate)
        }
        val _tmpAge: Int? = entity.age
        if (_tmpAge == null) {
          statement.bindNull(26)
        } else {
          statement.bindLong(26, _tmpAge.toLong())
        }
        val _tmpEmail: String? = entity.email
        if (_tmpEmail == null) {
          statement.bindNull(27)
        } else {
          statement.bindText(27, _tmpEmail)
        }
        val _tmpPhone: String? = entity.phone
        if (_tmpPhone == null) {
          statement.bindNull(28)
        } else {
          statement.bindText(28, _tmpPhone)
        }
        val _tmpAddress: String? = entity.address
        if (_tmpAddress == null) {
          statement.bindNull(29)
        } else {
          statement.bindText(29, _tmpAddress)
        }
        val _tmpNeighborhood: String? = entity.neighborhood
        if (_tmpNeighborhood == null) {
          statement.bindNull(30)
        } else {
          statement.bindText(30, _tmpNeighborhood)
        }
        val _tmpStratum: Int? = entity.stratum
        if (_tmpStratum == null) {
          statement.bindNull(31)
        } else {
          statement.bindLong(31, _tmpStratum.toLong())
        }
        val _tmpEducationLevel: String? = entity.educationLevel
        if (_tmpEducationLevel == null) {
          statement.bindNull(32)
        } else {
          statement.bindText(32, _tmpEducationLevel)
        }
        val _tmpPreviousSchool: String? = entity.previousSchool
        if (_tmpPreviousSchool == null) {
          statement.bindNull(33)
        } else {
          statement.bindText(33, _tmpPreviousSchool)
        }
        val _tmpSelectedPrograms: String? = entity.selectedPrograms
        if (_tmpSelectedPrograms == null) {
          statement.bindNull(34)
        } else {
          statement.bindText(34, _tmpSelectedPrograms)
        }
        val _tmpHowDidYouHear: String? = entity.howDidYouHear
        if (_tmpHowDidYouHear == null) {
          statement.bindNull(35)
        } else {
          statement.bindText(35, _tmpHowDidYouHear)
        }
        statement.bindText(36, entity.status)
        val _tmpWithdrawalReason: String? = entity.withdrawalReason
        if (_tmpWithdrawalReason == null) {
          statement.bindNull(37)
        } else {
          statement.bindText(37, _tmpWithdrawalReason)
        }
        val _tmpWithdrawalDate: Long? = entity.withdrawalDate
        if (_tmpWithdrawalDate == null) {
          statement.bindNull(38)
        } else {
          statement.bindLong(38, _tmpWithdrawalDate)
        }
        statement.bindLong(39, entity.statusUpdatedAt)
        val _tmpPhotoUpdatedAt: Long? = entity.photoUpdatedAt
        if (_tmpPhotoUpdatedAt == null) {
          statement.bindNull(40)
        } else {
          statement.bindLong(40, _tmpPhotoUpdatedAt)
        }
        statement.bindText(41, entity.estadoMatricula)
        val _tmpFechaRetiro: Long? = entity.fechaRetiro
        if (_tmpFechaRetiro == null) {
          statement.bindNull(42)
        } else {
          statement.bindLong(42, _tmpFechaRetiro)
        }
        val _tmpMotivoRetiro: String? = entity.motivoRetiro
        if (_tmpMotivoRetiro == null) {
          statement.bindNull(43)
        } else {
          statement.bindText(43, _tmpMotivoRetiro)
        }
        statement.bindLong(44, entity.diasInasistenciaConsecutiva.toLong())
        val _tmpUltimaFechaAsistencia: Long? = entity.ultimaFechaAsistencia
        if (_tmpUltimaFechaAsistencia == null) {
          statement.bindNull(45)
        } else {
          statement.bindLong(45, _tmpUltimaFechaAsistencia)
        }
        val _tmp_1: Int = if (entity.alertaEnviada30Dias) 1 else 0
        statement.bindLong(46, _tmp_1.toLong())
        val _tmpGuardianFirstName: String? = entity.guardianFirstName
        if (_tmpGuardianFirstName == null) {
          statement.bindNull(47)
        } else {
          statement.bindText(47, _tmpGuardianFirstName)
        }
        val _tmpGuardianLastName: String? = entity.guardianLastName
        if (_tmpGuardianLastName == null) {
          statement.bindNull(48)
        } else {
          statement.bindText(48, _tmpGuardianLastName)
        }
        val _tmpGuardianDocumentId: String? = entity.guardianDocumentId
        if (_tmpGuardianDocumentId == null) {
          statement.bindNull(49)
        } else {
          statement.bindText(49, _tmpGuardianDocumentId)
        }
        val _tmpGuardianRelationship: String? = entity.guardianRelationship
        if (_tmpGuardianRelationship == null) {
          statement.bindNull(50)
        } else {
          statement.bindText(50, _tmpGuardianRelationship)
        }
        val _tmpGuardianPhone: String? = entity.guardianPhone
        if (_tmpGuardianPhone == null) {
          statement.bindNull(51)
        } else {
          statement.bindText(51, _tmpGuardianPhone)
        }
        val _tmpGuardianEmail: String? = entity.guardianEmail
        if (_tmpGuardianEmail == null) {
          statement.bindNull(52)
        } else {
          statement.bindText(52, _tmpGuardianEmail)
        }
        val _tmp_2: Int = if (entity.esExterno) 1 else 0
        statement.bindLong(53, _tmp_2.toLong())
        val _tmpInstitucionOrigen: String? = entity.institucionOrigen
        if (_tmpInstitucionOrigen == null) {
          statement.bindNull(54)
        } else {
          statement.bindText(54, _tmpInstitucionOrigen)
        }
        val _tmpExternoId: String? = entity.externoId
        if (_tmpExternoId == null) {
          statement.bindNull(55)
        } else {
          statement.bindText(55, _tmpExternoId)
        }
        statement.bindText(56, entity.id)
      }
    }
  }

  public override suspend fun insertStudent(student: StudentEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfStudentEntity.insert(_connection, student)
  }

  public override suspend fun updateStudent(student: StudentEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfStudentEntity.handle(_connection, student)
  }

  public override fun getAllStudents(institutionId: String): Flow<List<StudentEntity>> {
    val _sql: String =
        "SELECT * FROM students WHERE institutionId = ? AND deletedAt IS NULL AND isDuplicate = 0"
    return createFlow(__db, true, arrayOf("students")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getStudentsWithPrograms(institutionId: String):
      Flow<List<StudentWithPrograms>> {
    val _sql: String =
        "SELECT * FROM students WHERE institutionId = ? AND deletedAt IS NULL AND isDuplicate = 0"
    return createFlow(__db, true, arrayOf("student_programs", "programs", "students")) {
        _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _collectionPrograms: ArrayMap<String, MutableList<ProgramEntity>> =
            ArrayMap<String, MutableList<ProgramEntity>>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfId)
          if (!_collectionPrograms.containsKey(_tmpKey)) {
            _collectionPrograms.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipprogramsAscomSigeschoolDataLocalEntityProgramEntity(_connection,
            _collectionPrograms)
        val _result: MutableList<StudentWithPrograms> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentWithPrograms
          val _tmpStudent: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _tmpStudent =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          val _tmpProgramsCollection: MutableList<ProgramEntity>
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_columnIndexOfId)
          _tmpProgramsCollection = _collectionPrograms.getValue(_tmpKey_1)
          _item = StudentWithPrograms(_tmpStudent,_tmpProgramsCollection)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getStudentWithProgramsById(id: String, institutionId: String):
      StudentWithPrograms? {
    val _sql: String = "SELECT * FROM students WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _collectionPrograms: ArrayMap<String, MutableList<ProgramEntity>> =
            ArrayMap<String, MutableList<ProgramEntity>>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfId)
          if (!_collectionPrograms.containsKey(_tmpKey)) {
            _collectionPrograms.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipprogramsAscomSigeschoolDataLocalEntityProgramEntity(_connection,
            _collectionPrograms)
        val _result: StudentWithPrograms?
        if (_stmt.step()) {
          val _tmpStudent: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _tmpStudent =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          val _tmpProgramsCollection: MutableList<ProgramEntity>
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_columnIndexOfId)
          _tmpProgramsCollection = _collectionPrograms.getValue(_tmpKey_1)
          _result = StudentWithPrograms(_tmpStudent,_tmpProgramsCollection)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllStudentsSync(institutionId: String): List<StudentEntity> {
    val _sql: String =
        "SELECT * FROM students WHERE institutionId = ? AND deletedAt IS NULL AND isDuplicate = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getStudentById(id: String, institutionId: String): StudentEntity? {
    val _sql: String = "SELECT * FROM students WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: StudentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _result =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getActiveStudents(institutionId: String): Flow<List<StudentEntity>> {
    val _sql: String =
        "SELECT * FROM students WHERE institutionId = ? AND deletedAt IS NULL AND status != 'WITHDRAWN' AND isDuplicate = 0"
    return createFlow(__db, false, arrayOf("students")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getActiveStudentsSync(institutionId: String): List<StudentEntity> {
    val _sql: String =
        "SELECT * FROM students WHERE institutionId = ? AND deletedAt IS NULL AND status != 'WITHDRAWN' AND isDuplicate = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getStudentsByStatus(institutionId: String, status: String):
      Flow<List<StudentEntity>> {
    val _sql: String =
        "SELECT * FROM students WHERE institutionId = ? AND status = ? AND deletedAt IS NULL AND isDuplicate = 0"
    return createFlow(__db, false, arrayOf("students")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, status)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getStudentByDocumentId(documentId: String, institutionId: String):
      StudentEntity? {
    val _sql: String =
        "SELECT * FROM students WHERE documentId = ? AND institutionId = ? AND deletedAt IS NULL"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, documentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: StudentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _result =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findByDocument(institutionId: String, documentId: String):
      StudentEntity? {
    val _sql: String = "SELECT * FROM students WHERE documentId = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, documentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: StudentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _result =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getStudentByEmail(email: String, institutionId: String):
      StudentEntity? {
    val _sql: String = """
        |
        |        SELECT s.* FROM students s
        |        INNER JOIN users u ON s.userId = u.id
        |        WHERE u.username = ? AND s.institutionId = ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: StudentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _result =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getStudentByQrCode(qrCode: String, institutionId: String):
      StudentEntity? {
    val _sql: String = "SELECT * FROM students WHERE qrCode = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, qrCode)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: StudentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _result =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSyncStudents(institutionId: String): List<StudentEntity> {
    val _sql: String = "SELECT * FROM students WHERE syncStatus != 0 AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchStudents(query: String, institutionId: String):
      List<StudentEntity> {
    val _sql: String =
        "SELECT * FROM students WHERE (firstName LIKE '%' || ? || '%' OR lastName LIKE '%' || ? || '%' OR documentId LIKE '%' || ? || '%') AND institutionId = ? AND deletedAt IS NULL"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindText(_argIndex, query)
        _argIndex = 4
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDesertores(instId: String): Flow<List<StudentEntity>> {
    val _sql: String =
        "SELECT * FROM students WHERE institutionId = ? AND estadoMatricula = 'RETIRADO_DESERCION'"
    return createFlow(__db, false, arrayOf("students")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProgramsByStudent(studentId: String, instId: String):
      List<String> {
    val _sql: String = """
        |
        |        SELECT p.name 
        |        FROM programs p
        |        JOIN student_programs sp ON p.id = sp.programId
        |        WHERE sp.studentId = ? AND p.institutionId = ? AND p.activo = 1
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getStudentsByProgram(programId: String, instId: String):
      Flow<List<StudentEntity>> {
    val _sql: String = """
        |
        |        SELECT s.* 
        |        FROM students s
        |        JOIN student_programs sp ON s.id = sp.studentId
        |        WHERE sp.programId = ? AND s.institutionId = ? AND s.deletedAt IS NULL AND s.status != 'WITHDRAWN'
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("students", "student_programs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, programId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfEthnicity: Int = getColumnIndexOrThrow(_stmt, "ethnicity")
        val _columnIndexOfEthnicCommunity: Int = getColumnIndexOrThrow(_stmt, "ethnicCommunity")
        val _columnIndexOfDisability: Int = getColumnIndexOrThrow(_stmt, "disability")
        val _columnIndexOfDisabilityAdjustments: Int = getColumnIndexOrThrow(_stmt,
            "disabilityAdjustments")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfConsentAcceptedAt: Int = getColumnIndexOrThrow(_stmt, "consentAcceptedAt")
        val _columnIndexOfConsentVersion: Int = getColumnIndexOrThrow(_stmt, "consentVersion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfIsDuplicate: Int = getColumnIndexOrThrow(_stmt, "isDuplicate")
        val _columnIndexOfMergedIntoId: Int = getColumnIndexOrThrow(_stmt, "mergedIntoId")
        val _columnIndexOfDeletedAt: Int = getColumnIndexOrThrow(_stmt, "deletedAt")
        val _columnIndexOfDeletedReason: Int = getColumnIndexOrThrow(_stmt, "deletedReason")
        val _columnIndexOfDeletedByUserId: Int = getColumnIndexOrThrow(_stmt, "deletedByUserId")
        val _columnIndexOfDocumentType: Int = getColumnIndexOrThrow(_stmt, "documentType")
        val _columnIndexOfBirthDate: Int = getColumnIndexOrThrow(_stmt, "birthDate")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfNeighborhood: Int = getColumnIndexOrThrow(_stmt, "neighborhood")
        val _columnIndexOfStratum: Int = getColumnIndexOrThrow(_stmt, "stratum")
        val _columnIndexOfEducationLevel: Int = getColumnIndexOrThrow(_stmt, "educationLevel")
        val _columnIndexOfPreviousSchool: Int = getColumnIndexOrThrow(_stmt, "previousSchool")
        val _columnIndexOfSelectedPrograms: Int = getColumnIndexOrThrow(_stmt, "selectedPrograms")
        val _columnIndexOfHowDidYouHear: Int = getColumnIndexOrThrow(_stmt, "howDidYouHear")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWithdrawalReason: Int = getColumnIndexOrThrow(_stmt, "withdrawalReason")
        val _columnIndexOfWithdrawalDate: Int = getColumnIndexOrThrow(_stmt, "withdrawalDate")
        val _columnIndexOfStatusUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "statusUpdatedAt")
        val _columnIndexOfPhotoUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "photoUpdatedAt")
        val _columnIndexOfEstadoMatricula: Int = getColumnIndexOrThrow(_stmt, "estadoMatricula")
        val _columnIndexOfFechaRetiro: Int = getColumnIndexOrThrow(_stmt, "fechaRetiro")
        val _columnIndexOfMotivoRetiro: Int = getColumnIndexOrThrow(_stmt, "motivoRetiro")
        val _columnIndexOfDiasInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "diasInasistenciaConsecutiva")
        val _columnIndexOfUltimaFechaAsistencia: Int = getColumnIndexOrThrow(_stmt,
            "ultimaFechaAsistencia")
        val _columnIndexOfAlertaEnviada30Dias: Int = getColumnIndexOrThrow(_stmt,
            "alertaEnviada30Dias")
        val _columnIndexOfGuardianFirstName: Int = getColumnIndexOrThrow(_stmt, "guardianFirstName")
        val _columnIndexOfGuardianLastName: Int = getColumnIndexOrThrow(_stmt, "guardianLastName")
        val _columnIndexOfGuardianDocumentId: Int = getColumnIndexOrThrow(_stmt,
            "guardianDocumentId")
        val _columnIndexOfGuardianRelationship: Int = getColumnIndexOrThrow(_stmt,
            "guardianRelationship")
        val _columnIndexOfGuardianPhone: Int = getColumnIndexOrThrow(_stmt, "guardianPhone")
        val _columnIndexOfGuardianEmail: Int = getColumnIndexOrThrow(_stmt, "guardianEmail")
        val _columnIndexOfEsExterno: Int = getColumnIndexOrThrow(_stmt, "esExterno")
        val _columnIndexOfInstitucionOrigen: Int = getColumnIndexOrThrow(_stmt, "institucionOrigen")
        val _columnIndexOfExternoId: Int = getColumnIndexOrThrow(_stmt, "externoId")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUserId: String?
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          }
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpEthnicity: String?
          if (_stmt.isNull(_columnIndexOfEthnicity)) {
            _tmpEthnicity = null
          } else {
            _tmpEthnicity = _stmt.getText(_columnIndexOfEthnicity)
          }
          val _tmpEthnicCommunity: String?
          if (_stmt.isNull(_columnIndexOfEthnicCommunity)) {
            _tmpEthnicCommunity = null
          } else {
            _tmpEthnicCommunity = _stmt.getText(_columnIndexOfEthnicCommunity)
          }
          val _tmpDisability: String?
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability)
          }
          val _tmpDisabilityAdjustments: String?
          if (_stmt.isNull(_columnIndexOfDisabilityAdjustments)) {
            _tmpDisabilityAdjustments = null
          } else {
            _tmpDisabilityAdjustments = _stmt.getText(_columnIndexOfDisabilityAdjustments)
          }
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          val _tmpQrCode: String
          _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          val _tmpCursoId: Long?
          if (_stmt.isNull(_columnIndexOfCursoId)) {
            _tmpCursoId = null
          } else {
            _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          }
          val _tmpConsentAcceptedAt: Long?
          if (_stmt.isNull(_columnIndexOfConsentAcceptedAt)) {
            _tmpConsentAcceptedAt = null
          } else {
            _tmpConsentAcceptedAt = _stmt.getLong(_columnIndexOfConsentAcceptedAt)
          }
          val _tmpConsentVersion: String?
          if (_stmt.isNull(_columnIndexOfConsentVersion)) {
            _tmpConsentVersion = null
          } else {
            _tmpConsentVersion = _stmt.getText(_columnIndexOfConsentVersion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsDuplicate: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDuplicate).toInt()
          _tmpIsDuplicate = _tmp != 0
          val _tmpMergedIntoId: String?
          if (_stmt.isNull(_columnIndexOfMergedIntoId)) {
            _tmpMergedIntoId = null
          } else {
            _tmpMergedIntoId = _stmt.getText(_columnIndexOfMergedIntoId)
          }
          val _tmpDeletedAt: Long?
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt)
          }
          val _tmpDeletedReason: String?
          if (_stmt.isNull(_columnIndexOfDeletedReason)) {
            _tmpDeletedReason = null
          } else {
            _tmpDeletedReason = _stmt.getText(_columnIndexOfDeletedReason)
          }
          val _tmpDeletedByUserId: String?
          if (_stmt.isNull(_columnIndexOfDeletedByUserId)) {
            _tmpDeletedByUserId = null
          } else {
            _tmpDeletedByUserId = _stmt.getText(_columnIndexOfDeletedByUserId)
          }
          val _tmpDocumentType: String?
          if (_stmt.isNull(_columnIndexOfDocumentType)) {
            _tmpDocumentType = null
          } else {
            _tmpDocumentType = _stmt.getText(_columnIndexOfDocumentType)
          }
          val _tmpBirthDate: String?
          if (_stmt.isNull(_columnIndexOfBirthDate)) {
            _tmpBirthDate = null
          } else {
            _tmpBirthDate = _stmt.getText(_columnIndexOfBirthDate)
          }
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpNeighborhood: String?
          if (_stmt.isNull(_columnIndexOfNeighborhood)) {
            _tmpNeighborhood = null
          } else {
            _tmpNeighborhood = _stmt.getText(_columnIndexOfNeighborhood)
          }
          val _tmpStratum: Int?
          if (_stmt.isNull(_columnIndexOfStratum)) {
            _tmpStratum = null
          } else {
            _tmpStratum = _stmt.getLong(_columnIndexOfStratum).toInt()
          }
          val _tmpEducationLevel: String?
          if (_stmt.isNull(_columnIndexOfEducationLevel)) {
            _tmpEducationLevel = null
          } else {
            _tmpEducationLevel = _stmt.getText(_columnIndexOfEducationLevel)
          }
          val _tmpPreviousSchool: String?
          if (_stmt.isNull(_columnIndexOfPreviousSchool)) {
            _tmpPreviousSchool = null
          } else {
            _tmpPreviousSchool = _stmt.getText(_columnIndexOfPreviousSchool)
          }
          val _tmpSelectedPrograms: String?
          if (_stmt.isNull(_columnIndexOfSelectedPrograms)) {
            _tmpSelectedPrograms = null
          } else {
            _tmpSelectedPrograms = _stmt.getText(_columnIndexOfSelectedPrograms)
          }
          val _tmpHowDidYouHear: String?
          if (_stmt.isNull(_columnIndexOfHowDidYouHear)) {
            _tmpHowDidYouHear = null
          } else {
            _tmpHowDidYouHear = _stmt.getText(_columnIndexOfHowDidYouHear)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpWithdrawalReason: String?
          if (_stmt.isNull(_columnIndexOfWithdrawalReason)) {
            _tmpWithdrawalReason = null
          } else {
            _tmpWithdrawalReason = _stmt.getText(_columnIndexOfWithdrawalReason)
          }
          val _tmpWithdrawalDate: Long?
          if (_stmt.isNull(_columnIndexOfWithdrawalDate)) {
            _tmpWithdrawalDate = null
          } else {
            _tmpWithdrawalDate = _stmt.getLong(_columnIndexOfWithdrawalDate)
          }
          val _tmpStatusUpdatedAt: Long
          _tmpStatusUpdatedAt = _stmt.getLong(_columnIndexOfStatusUpdatedAt)
          val _tmpPhotoUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfPhotoUpdatedAt)) {
            _tmpPhotoUpdatedAt = null
          } else {
            _tmpPhotoUpdatedAt = _stmt.getLong(_columnIndexOfPhotoUpdatedAt)
          }
          val _tmpEstadoMatricula: String
          _tmpEstadoMatricula = _stmt.getText(_columnIndexOfEstadoMatricula)
          val _tmpFechaRetiro: Long?
          if (_stmt.isNull(_columnIndexOfFechaRetiro)) {
            _tmpFechaRetiro = null
          } else {
            _tmpFechaRetiro = _stmt.getLong(_columnIndexOfFechaRetiro)
          }
          val _tmpMotivoRetiro: String?
          if (_stmt.isNull(_columnIndexOfMotivoRetiro)) {
            _tmpMotivoRetiro = null
          } else {
            _tmpMotivoRetiro = _stmt.getText(_columnIndexOfMotivoRetiro)
          }
          val _tmpDiasInasistenciaConsecutiva: Int
          _tmpDiasInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfDiasInasistenciaConsecutiva).toInt()
          val _tmpUltimaFechaAsistencia: Long?
          if (_stmt.isNull(_columnIndexOfUltimaFechaAsistencia)) {
            _tmpUltimaFechaAsistencia = null
          } else {
            _tmpUltimaFechaAsistencia = _stmt.getLong(_columnIndexOfUltimaFechaAsistencia)
          }
          val _tmpAlertaEnviada30Dias: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfAlertaEnviada30Dias).toInt()
          _tmpAlertaEnviada30Dias = _tmp_1 != 0
          val _tmpGuardianFirstName: String?
          if (_stmt.isNull(_columnIndexOfGuardianFirstName)) {
            _tmpGuardianFirstName = null
          } else {
            _tmpGuardianFirstName = _stmt.getText(_columnIndexOfGuardianFirstName)
          }
          val _tmpGuardianLastName: String?
          if (_stmt.isNull(_columnIndexOfGuardianLastName)) {
            _tmpGuardianLastName = null
          } else {
            _tmpGuardianLastName = _stmt.getText(_columnIndexOfGuardianLastName)
          }
          val _tmpGuardianDocumentId: String?
          if (_stmt.isNull(_columnIndexOfGuardianDocumentId)) {
            _tmpGuardianDocumentId = null
          } else {
            _tmpGuardianDocumentId = _stmt.getText(_columnIndexOfGuardianDocumentId)
          }
          val _tmpGuardianRelationship: String?
          if (_stmt.isNull(_columnIndexOfGuardianRelationship)) {
            _tmpGuardianRelationship = null
          } else {
            _tmpGuardianRelationship = _stmt.getText(_columnIndexOfGuardianRelationship)
          }
          val _tmpGuardianPhone: String?
          if (_stmt.isNull(_columnIndexOfGuardianPhone)) {
            _tmpGuardianPhone = null
          } else {
            _tmpGuardianPhone = _stmt.getText(_columnIndexOfGuardianPhone)
          }
          val _tmpGuardianEmail: String?
          if (_stmt.isNull(_columnIndexOfGuardianEmail)) {
            _tmpGuardianEmail = null
          } else {
            _tmpGuardianEmail = _stmt.getText(_columnIndexOfGuardianEmail)
          }
          val _tmpEsExterno: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEsExterno).toInt()
          _tmpEsExterno = _tmp_2 != 0
          val _tmpInstitucionOrigen: String?
          if (_stmt.isNull(_columnIndexOfInstitucionOrigen)) {
            _tmpInstitucionOrigen = null
          } else {
            _tmpInstitucionOrigen = _stmt.getText(_columnIndexOfInstitucionOrigen)
          }
          val _tmpExternoId: String?
          if (_stmt.isNull(_columnIndexOfExternoId)) {
            _tmpExternoId = null
          } else {
            _tmpExternoId = _stmt.getText(_columnIndexOfExternoId)
          }
          _item =
              StudentEntity(_tmpId,_tmpInstitutionId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpDocumentId,_tmpGender,_tmpEthnicity,_tmpEthnicCommunity,_tmpDisability,_tmpDisabilityAdjustments,_tmpPhotoPath,_tmpQrCode,_tmpCursoId,_tmpConsentAcceptedAt,_tmpConsentVersion,_tmpSyncStatus,_tmpLastModified,_tmpIsDuplicate,_tmpMergedIntoId,_tmpDeletedAt,_tmpDeletedReason,_tmpDeletedByUserId,_tmpDocumentType,_tmpBirthDate,_tmpAge,_tmpEmail,_tmpPhone,_tmpAddress,_tmpNeighborhood,_tmpStratum,_tmpEducationLevel,_tmpPreviousSchool,_tmpSelectedPrograms,_tmpHowDidYouHear,_tmpStatus,_tmpWithdrawalReason,_tmpWithdrawalDate,_tmpStatusUpdatedAt,_tmpPhotoUpdatedAt,_tmpEstadoMatricula,_tmpFechaRetiro,_tmpMotivoRetiro,_tmpDiasInasistenciaConsecutiva,_tmpUltimaFechaAsistencia,_tmpAlertaEnviada30Dias,_tmpGuardianFirstName,_tmpGuardianLastName,_tmpGuardianDocumentId,_tmpGuardianRelationship,_tmpGuardianPhone,_tmpGuardianEmail,_tmpEsExterno,_tmpInstitucionOrigen,_tmpExternoId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateStudentStatus(
    studentId: String,
    institutionId: String,
    newStatus: String,
    reason: String?,
    date: Long?,
    updatedAt: Long,
  ) {
    val _sql: String =
        "UPDATE students SET status = ?, withdrawalReason = ?, withdrawalDate = ?, statusUpdatedAt = ?, syncStatus = 2 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, newStatus)
        _argIndex = 2
        if (reason == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, reason)
        }
        _argIndex = 3
        if (date == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, date)
        }
        _argIndex = 4
        _stmt.bindLong(_argIndex, updatedAt)
        _argIndex = 5
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 6
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, institutionId: String) {
    val _sql: String = "UPDATE students SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

  public override suspend fun softDeleteStudent(
    id: String,
    institutionId: String,
    deletedAt: Long,
    reason: String,
    userId: String,
  ) {
    val _sql: String =
        "UPDATE students SET deletedAt = ?, deletedReason = ?, deletedByUserId = ?, syncStatus = 2 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, deletedAt)
        _argIndex = 2
        _stmt.bindText(_argIndex, reason)
        _argIndex = 3
        _stmt.bindText(_argIndex, userId)
        _argIndex = 4
        _stmt.bindText(_argIndex, id)
        _argIndex = 5
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsDuplicate(
    sourceId: String,
    targetId: String,
    institutionId: String,
  ) {
    val _sql: String =
        "UPDATE students SET isDuplicate = 1, mergedIntoId = ?, syncStatus = 2 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, targetId)
        _argIndex = 2
        _stmt.bindText(_argIndex, sourceId)
        _argIndex = 3
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteStudent(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM students WHERE id = ? AND institutionId = ?"
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

  public override suspend fun updateConsecutiveDays(
    id: String,
    days: Int,
    instId: String,
  ) {
    val _sql: String =
        "UPDATE students SET diasInasistenciaConsecutiva = ? WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, days.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        _argIndex = 3
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun resetConsecutiveDays(id: String, instId: String) {
    val _sql: String =
        "UPDATE students SET diasInasistenciaConsecutiva = 0 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  private
      fun __fetchRelationshipprogramsAscomSigeschoolDataLocalEntityProgramEntity(_connection: SQLiteConnection,
      _map: ArrayMap<String, MutableList<ProgramEntity>>) {
    val __mapKeySet: Set<String> = _map.keys
    if (__mapKeySet.isEmpty()) {
      return
    }
    if (_map.size > 999) {
      recursiveFetchArrayMap(_map, true) { _tmpMap ->
        __fetchRelationshipprogramsAscomSigeschoolDataLocalEntityProgramEntity(_connection, _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `programs`.`id` AS `id`,`programs`.`institutionId` AS `institutionId`,`programs`.`codigo` AS `codigo`,`programs`.`name` AS `name`,`programs`.`description` AS `description`,`programs`.`nivelEducativoId` AS `nivelEducativoId`,`programs`.`gradoId` AS `gradoId`,`programs`.`activo` AS `activo`,`programs`.`syncStatus` AS `syncStatus`,`programs`.`lastModified` AS `lastModified`,_junction.`studentId` FROM `student_programs` AS _junction INNER JOIN `programs` ON (_junction.`programId` = `programs`.`id`) WHERE _junction.`studentId` IN (")
    val _inputSize: Int = __mapKeySet.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (_item: String in __mapKeySet) {
      _stmt.bindText(_argIndex, _item)
      _argIndex++
    }
    try {
      // _junction.studentId
      val _itemKeyIndex: Int = 10
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfId: Int = 0
      val _columnIndexOfInstitutionId: Int = 1
      val _columnIndexOfCodigo: Int = 2
      val _columnIndexOfName: Int = 3
      val _columnIndexOfDescription: Int = 4
      val _columnIndexOfNivelEducativoId: Int = 5
      val _columnIndexOfGradoId: Int = 6
      val _columnIndexOfActivo: Int = 7
      val _columnIndexOfSyncStatus: Int = 8
      val _columnIndexOfLastModified: Int = 9
      while (_stmt.step()) {
        val _tmpKey: String
        _tmpKey = _stmt.getText(_itemKeyIndex)
        val _tmpRelation: MutableList<ProgramEntity>? = _map.get(_tmpKey)
        if (_tmpRelation != null) {
          val _item_1: ProgramEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCodigo: String
          _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          val _tmpNivelEducativoId: Long?
          if (_stmt.isNull(_columnIndexOfNivelEducativoId)) {
            _tmpNivelEducativoId = null
          } else {
            _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          }
          val _tmpGradoId: Long?
          if (_stmt.isNull(_columnIndexOfGradoId)) {
            _tmpGradoId = null
          } else {
            _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          }
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item_1 =
              ProgramEntity(_tmpId,_tmpInstitutionId,_tmpCodigo,_tmpName,_tmpDescription,_tmpNivelEducativoId,_tmpGradoId,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _tmpRelation.add(_item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

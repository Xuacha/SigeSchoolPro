package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.DocenteCursoEntity
import com.sigeschool.`data`.local.entity.StudentEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class DocenteCursoDao_Impl(
  __db: RoomDatabase,
) : DocenteCursoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDocenteCursoEntity: EntityInsertAdapter<DocenteCursoEntity>

  private val __updateAdapterOfDocenteCursoEntity: EntityDeleteOrUpdateAdapter<DocenteCursoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDocenteCursoEntity = object : EntityInsertAdapter<DocenteCursoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `employee_docente_cursos` (`id`,`institutionId`,`docenteId`,`cursoId`,`asignaturaId`,`cargaHorariaSemanal`,`esDirectorGrupo`,`activo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteCursoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.docenteId)
        statement.bindLong(4, entity.cursoId)
        statement.bindLong(5, entity.asignaturaId)
        statement.bindLong(6, entity.cargaHorariaSemanal.toLong())
        val _tmp: Int = if (entity.esDirectorGrupo) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.activo) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfDocenteCursoEntity = object :
        EntityDeleteOrUpdateAdapter<DocenteCursoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `employee_docente_cursos` SET `id` = ?,`institutionId` = ?,`docenteId` = ?,`cursoId` = ?,`asignaturaId` = ?,`cargaHorariaSemanal` = ?,`esDirectorGrupo` = ?,`activo` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DocenteCursoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.docenteId)
        statement.bindLong(4, entity.cursoId)
        statement.bindLong(5, entity.asignaturaId)
        statement.bindLong(6, entity.cargaHorariaSemanal.toLong())
        val _tmp: Int = if (entity.esDirectorGrupo) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.activo) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindLong(11, entity.id)
      }
    }
  }

  public override suspend fun insert(docenteCurso: DocenteCursoEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfDocenteCursoEntity.insertAndReturnId(_connection,
        docenteCurso)
    _result
  }

  public override suspend fun update(docenteCurso: DocenteCursoEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfDocenteCursoEntity.handle(_connection, docenteCurso)
  }

  public override fun getCursosByDocente(docenteId: Long, instId: String):
      Flow<List<DocenteCursoEntity>> {
    val _sql: String =
        "SELECT * FROM employee_docente_cursos WHERE docenteId = ? AND institutionId = ? AND activo = 1"
    return createFlow(__db, false, arrayOf("employee_docente_cursos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, docenteId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfCargaHorariaSemanal: Int = getColumnIndexOrThrow(_stmt,
            "cargaHorariaSemanal")
        val _columnIndexOfEsDirectorGrupo: Int = getColumnIndexOrThrow(_stmt, "esDirectorGrupo")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DocenteCursoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocenteCursoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: Long
          _tmpDocenteId = _stmt.getLong(_columnIndexOfDocenteId)
          val _tmpCursoId: Long
          _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpCargaHorariaSemanal: Int
          _tmpCargaHorariaSemanal = _stmt.getLong(_columnIndexOfCargaHorariaSemanal).toInt()
          val _tmpEsDirectorGrupo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsDirectorGrupo).toInt()
          _tmpEsDirectorGrupo = _tmp != 0
          val _tmpActivo: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DocenteCursoEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpCursoId,_tmpAsignaturaId,_tmpCargaHorariaSemanal,_tmpEsDirectorGrupo,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDocentesByCurso(cursoId: Long, instId: String):
      Flow<List<DocenteCursoEntity>> {
    val _sql: String =
        "SELECT * FROM employee_docente_cursos WHERE cursoId = ? AND institutionId = ? AND activo = 1"
    return createFlow(__db, false, arrayOf("employee_docente_cursos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, cursoId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfCursoId: Int = getColumnIndexOrThrow(_stmt, "cursoId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfCargaHorariaSemanal: Int = getColumnIndexOrThrow(_stmt,
            "cargaHorariaSemanal")
        val _columnIndexOfEsDirectorGrupo: Int = getColumnIndexOrThrow(_stmt, "esDirectorGrupo")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DocenteCursoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocenteCursoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDocenteId: Long
          _tmpDocenteId = _stmt.getLong(_columnIndexOfDocenteId)
          val _tmpCursoId: Long
          _tmpCursoId = _stmt.getLong(_columnIndexOfCursoId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpCargaHorariaSemanal: Int
          _tmpCargaHorariaSemanal = _stmt.getLong(_columnIndexOfCargaHorariaSemanal).toInt()
          val _tmpEsDirectorGrupo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsDirectorGrupo).toInt()
          _tmpEsDirectorGrupo = _tmp != 0
          val _tmpActivo: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DocenteCursoEntity(_tmpId,_tmpInstitutionId,_tmpDocenteId,_tmpCursoId,_tmpAsignaturaId,_tmpCargaHorariaSemanal,_tmpEsDirectorGrupo,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getStudentsByDocente(userId: String, instId: String):
      Flow<List<StudentEntity>> {
    val _sql: String = """
        |
        |        SELECT DISTINCT s.* FROM students s
        |        INNER JOIN academic_matriculas m ON s.id = m.estudianteId
        |        INNER JOIN academic_clases c ON m.claseId = c.id
        |        INNER JOIN academic_detalles_oferta det ON c.detalleOfertaId = det.id
        |        WHERE s.institutionId = ? AND det.docenteId = ?
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("students", "academic_matriculas", "academic_clases",
        "academic_detalles_oferta")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, userId)
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

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM employee_docente_cursos WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeAsignacion(
    docenteId: Long,
    cursoId: Long,
    asignaturaId: Long,
  ) {
    val _sql: String =
        "DELETE FROM employee_docente_cursos WHERE docenteId = ? AND cursoId = ? AND asignaturaId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, docenteId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, cursoId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, asignaturaId)
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

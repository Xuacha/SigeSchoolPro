package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.converter.Converters
import com.sigeschool.`data`.local.entity.ConsentEntity
import com.sigeschool.`data`.local.entity.ConsentHistoryEntity
import com.sigeschool.`data`.local.entity.PrivacyPolicyEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ConsentDao_Impl(
  __db: RoomDatabase,
) : ConsentDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPrivacyPolicyEntity: EntityInsertAdapter<PrivacyPolicyEntity>

  private val __insertAdapterOfConsentEntity: EntityInsertAdapter<ConsentEntity>

  private val __converters: Converters = Converters()

  private val __insertAdapterOfConsentHistoryEntity: EntityInsertAdapter<ConsentHistoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPrivacyPolicyEntity = object : EntityInsertAdapter<PrivacyPolicyEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `politicas_privacidad` (`id`,`version`,`fechaPublicacion`,`contenidoHash`,`contenidoTexto`,`es_activa`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PrivacyPolicyEntity) {
        statement.bindText(1, entity.id)
        statement.bindLong(2, entity.version.toLong())
        statement.bindLong(3, entity.fechaPublicacion)
        statement.bindText(4, entity.contenidoHash)
        statement.bindText(5, entity.contenidoTexto)
        val _tmp: Int = if (entity.esActiva) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
    this.__insertAdapterOfConsentEntity = object : EntityInsertAdapter<ConsentEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `consentimientos` (`id`,`studentId`,`acudienteNombre`,`acudienteDni`,`acudienteParentesco`,`acudienteEmail`,`acudienteTelefono`,`politicaId`,`fechaAceptacion`,`fecha_revocacion`,`motivo_revocacion`,`device_info`,`hash_firma_digital`,`granularConsent`,`version`,`deviceId`,`lastModified`,`syncStatus`,`syncAttempts`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConsentEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.studentId)
        statement.bindText(3, entity.acudienteNombre)
        statement.bindText(4, entity.acudienteDni)
        statement.bindText(5, entity.acudienteParentesco)
        statement.bindText(6, entity.acudienteEmail)
        statement.bindText(7, entity.acudienteTelefono)
        statement.bindText(8, entity.politicaId)
        statement.bindLong(9, entity.fechaAceptacion)
        val _tmpFechaRevocacion: Long? = entity.fechaRevocacion
        if (_tmpFechaRevocacion == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpFechaRevocacion)
        }
        val _tmpMotivoRevocacion: String? = entity.motivoRevocacion
        if (_tmpMotivoRevocacion == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpMotivoRevocacion)
        }
        statement.bindText(12, entity.deviceInfo)
        statement.bindText(13, entity.hashFirmaDigital)
        val _tmp: String = __converters.fromStringMap(entity.granularConsent)
        statement.bindText(14, _tmp)
        statement.bindLong(15, entity.version)
        statement.bindText(16, entity.deviceId)
        statement.bindLong(17, entity.lastModified)
        statement.bindLong(18, entity.syncStatus.toLong())
        statement.bindLong(19, entity.syncAttempts.toLong())
      }
    }
    this.__insertAdapterOfConsentHistoryEntity = object :
        EntityInsertAdapter<ConsentHistoryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `consentimiento_historial` (`id`,`consentId`,`studentId`,`action`,`timestamp`,`details`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConsentHistoryEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.consentId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.action)
        statement.bindLong(5, entity.timestamp)
        statement.bindText(6, entity.details)
      }
    }
  }

  public override suspend fun insertPolicy(policy: PrivacyPolicyEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPrivacyPolicyEntity.insert(_connection, policy)
  }

  public override suspend fun insertConsent(consent: ConsentEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfConsentEntity.insert(_connection, consent)
  }

  public override suspend fun insertHistory(history: ConsentHistoryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfConsentHistoryEntity.insert(_connection, history)
  }

  public override suspend fun registerConsentWithHistory(consent: ConsentEntity,
      history: ConsentHistoryEntity): Unit = performInTransactionSuspending(__db) {
    super@ConsentDao_Impl.registerConsentWithHistory(consent, history)
  }

  public override suspend fun getActivePolicy(): PrivacyPolicyEntity? {
    val _sql: String = "SELECT * FROM politicas_privacidad WHERE es_activa = 1 LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfFechaPublicacion: Int = getColumnIndexOrThrow(_stmt, "fechaPublicacion")
        val _columnIndexOfContenidoHash: Int = getColumnIndexOrThrow(_stmt, "contenidoHash")
        val _columnIndexOfContenidoTexto: Int = getColumnIndexOrThrow(_stmt, "contenidoTexto")
        val _columnIndexOfEsActiva: Int = getColumnIndexOrThrow(_stmt, "es_activa")
        val _result: PrivacyPolicyEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpVersion: Int
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion).toInt()
          val _tmpFechaPublicacion: Long
          _tmpFechaPublicacion = _stmt.getLong(_columnIndexOfFechaPublicacion)
          val _tmpContenidoHash: String
          _tmpContenidoHash = _stmt.getText(_columnIndexOfContenidoHash)
          val _tmpContenidoTexto: String
          _tmpContenidoTexto = _stmt.getText(_columnIndexOfContenidoTexto)
          val _tmpEsActiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActiva).toInt()
          _tmpEsActiva = _tmp != 0
          _result =
              PrivacyPolicyEntity(_tmpId,_tmpVersion,_tmpFechaPublicacion,_tmpContenidoHash,_tmpContenidoTexto,_tmpEsActiva)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getValidConsentForStudent(studentId: String): ConsentEntity? {
    val _sql: String =
        "SELECT * FROM consentimientos WHERE studentId = ? AND fecha_revocacion IS NULL LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfAcudienteNombre: Int = getColumnIndexOrThrow(_stmt, "acudienteNombre")
        val _columnIndexOfAcudienteDni: Int = getColumnIndexOrThrow(_stmt, "acudienteDni")
        val _columnIndexOfAcudienteParentesco: Int = getColumnIndexOrThrow(_stmt,
            "acudienteParentesco")
        val _columnIndexOfAcudienteEmail: Int = getColumnIndexOrThrow(_stmt, "acudienteEmail")
        val _columnIndexOfAcudienteTelefono: Int = getColumnIndexOrThrow(_stmt, "acudienteTelefono")
        val _columnIndexOfPoliticaId: Int = getColumnIndexOrThrow(_stmt, "politicaId")
        val _columnIndexOfFechaAceptacion: Int = getColumnIndexOrThrow(_stmt, "fechaAceptacion")
        val _columnIndexOfFechaRevocacion: Int = getColumnIndexOrThrow(_stmt, "fecha_revocacion")
        val _columnIndexOfMotivoRevocacion: Int = getColumnIndexOrThrow(_stmt, "motivo_revocacion")
        val _columnIndexOfDeviceInfo: Int = getColumnIndexOrThrow(_stmt, "device_info")
        val _columnIndexOfHashFirmaDigital: Int = getColumnIndexOrThrow(_stmt, "hash_firma_digital")
        val _columnIndexOfGranularConsent: Int = getColumnIndexOrThrow(_stmt, "granularConsent")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: ConsentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpAcudienteNombre: String
          _tmpAcudienteNombre = _stmt.getText(_columnIndexOfAcudienteNombre)
          val _tmpAcudienteDni: String
          _tmpAcudienteDni = _stmt.getText(_columnIndexOfAcudienteDni)
          val _tmpAcudienteParentesco: String
          _tmpAcudienteParentesco = _stmt.getText(_columnIndexOfAcudienteParentesco)
          val _tmpAcudienteEmail: String
          _tmpAcudienteEmail = _stmt.getText(_columnIndexOfAcudienteEmail)
          val _tmpAcudienteTelefono: String
          _tmpAcudienteTelefono = _stmt.getText(_columnIndexOfAcudienteTelefono)
          val _tmpPoliticaId: String
          _tmpPoliticaId = _stmt.getText(_columnIndexOfPoliticaId)
          val _tmpFechaAceptacion: Long
          _tmpFechaAceptacion = _stmt.getLong(_columnIndexOfFechaAceptacion)
          val _tmpFechaRevocacion: Long?
          if (_stmt.isNull(_columnIndexOfFechaRevocacion)) {
            _tmpFechaRevocacion = null
          } else {
            _tmpFechaRevocacion = _stmt.getLong(_columnIndexOfFechaRevocacion)
          }
          val _tmpMotivoRevocacion: String?
          if (_stmt.isNull(_columnIndexOfMotivoRevocacion)) {
            _tmpMotivoRevocacion = null
          } else {
            _tmpMotivoRevocacion = _stmt.getText(_columnIndexOfMotivoRevocacion)
          }
          val _tmpDeviceInfo: String
          _tmpDeviceInfo = _stmt.getText(_columnIndexOfDeviceInfo)
          val _tmpHashFirmaDigital: String
          _tmpHashFirmaDigital = _stmt.getText(_columnIndexOfHashFirmaDigital)
          val _tmpGranularConsent: Map<String, Boolean>
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfGranularConsent)
          _tmpGranularConsent = __converters.toStringMap(_tmp)
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          val _tmpDeviceId: String
          _tmpDeviceId = _stmt.getText(_columnIndexOfDeviceId)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpSyncAttempts: Int
          _tmpSyncAttempts = _stmt.getLong(_columnIndexOfSyncAttempts).toInt()
          _result =
              ConsentEntity(_tmpId,_tmpStudentId,_tmpAcudienteNombre,_tmpAcudienteDni,_tmpAcudienteParentesco,_tmpAcudienteEmail,_tmpAcudienteTelefono,_tmpPoliticaId,_tmpFechaAceptacion,_tmpFechaRevocacion,_tmpMotivoRevocacion,_tmpDeviceInfo,_tmpHashFirmaDigital,_tmpGranularConsent,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getHistoryForStudent(studentId: String): Flow<List<ConsentHistoryEntity>> {
    val _sql: String =
        "SELECT * FROM consentimiento_historial WHERE studentId = ? ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("consentimiento_historial")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfConsentId: Int = getColumnIndexOrThrow(_stmt, "consentId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfAction: Int = getColumnIndexOrThrow(_stmt, "action")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _result: MutableList<ConsentHistoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConsentHistoryEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpConsentId: String
          _tmpConsentId = _stmt.getText(_columnIndexOfConsentId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpAction: String
          _tmpAction = _stmt.getText(_columnIndexOfAction)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpDetails: String
          _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          _item =
              ConsentHistoryEntity(_tmpId,_tmpConsentId,_tmpStudentId,_tmpAction,_tmpTimestamp,_tmpDetails)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getConsentById(consentId: String): ConsentEntity? {
    val _sql: String = "SELECT * FROM consentimientos WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, consentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfAcudienteNombre: Int = getColumnIndexOrThrow(_stmt, "acudienteNombre")
        val _columnIndexOfAcudienteDni: Int = getColumnIndexOrThrow(_stmt, "acudienteDni")
        val _columnIndexOfAcudienteParentesco: Int = getColumnIndexOrThrow(_stmt,
            "acudienteParentesco")
        val _columnIndexOfAcudienteEmail: Int = getColumnIndexOrThrow(_stmt, "acudienteEmail")
        val _columnIndexOfAcudienteTelefono: Int = getColumnIndexOrThrow(_stmt, "acudienteTelefono")
        val _columnIndexOfPoliticaId: Int = getColumnIndexOrThrow(_stmt, "politicaId")
        val _columnIndexOfFechaAceptacion: Int = getColumnIndexOrThrow(_stmt, "fechaAceptacion")
        val _columnIndexOfFechaRevocacion: Int = getColumnIndexOrThrow(_stmt, "fecha_revocacion")
        val _columnIndexOfMotivoRevocacion: Int = getColumnIndexOrThrow(_stmt, "motivo_revocacion")
        val _columnIndexOfDeviceInfo: Int = getColumnIndexOrThrow(_stmt, "device_info")
        val _columnIndexOfHashFirmaDigital: Int = getColumnIndexOrThrow(_stmt, "hash_firma_digital")
        val _columnIndexOfGranularConsent: Int = getColumnIndexOrThrow(_stmt, "granularConsent")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: ConsentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpAcudienteNombre: String
          _tmpAcudienteNombre = _stmt.getText(_columnIndexOfAcudienteNombre)
          val _tmpAcudienteDni: String
          _tmpAcudienteDni = _stmt.getText(_columnIndexOfAcudienteDni)
          val _tmpAcudienteParentesco: String
          _tmpAcudienteParentesco = _stmt.getText(_columnIndexOfAcudienteParentesco)
          val _tmpAcudienteEmail: String
          _tmpAcudienteEmail = _stmt.getText(_columnIndexOfAcudienteEmail)
          val _tmpAcudienteTelefono: String
          _tmpAcudienteTelefono = _stmt.getText(_columnIndexOfAcudienteTelefono)
          val _tmpPoliticaId: String
          _tmpPoliticaId = _stmt.getText(_columnIndexOfPoliticaId)
          val _tmpFechaAceptacion: Long
          _tmpFechaAceptacion = _stmt.getLong(_columnIndexOfFechaAceptacion)
          val _tmpFechaRevocacion: Long?
          if (_stmt.isNull(_columnIndexOfFechaRevocacion)) {
            _tmpFechaRevocacion = null
          } else {
            _tmpFechaRevocacion = _stmt.getLong(_columnIndexOfFechaRevocacion)
          }
          val _tmpMotivoRevocacion: String?
          if (_stmt.isNull(_columnIndexOfMotivoRevocacion)) {
            _tmpMotivoRevocacion = null
          } else {
            _tmpMotivoRevocacion = _stmt.getText(_columnIndexOfMotivoRevocacion)
          }
          val _tmpDeviceInfo: String
          _tmpDeviceInfo = _stmt.getText(_columnIndexOfDeviceInfo)
          val _tmpHashFirmaDigital: String
          _tmpHashFirmaDigital = _stmt.getText(_columnIndexOfHashFirmaDigital)
          val _tmpGranularConsent: Map<String, Boolean>
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfGranularConsent)
          _tmpGranularConsent = __converters.toStringMap(_tmp)
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          val _tmpDeviceId: String
          _tmpDeviceId = _stmt.getText(_columnIndexOfDeviceId)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpSyncAttempts: Int
          _tmpSyncAttempts = _stmt.getLong(_columnIndexOfSyncAttempts).toInt()
          _result =
              ConsentEntity(_tmpId,_tmpStudentId,_tmpAcudienteNombre,_tmpAcudienteDni,_tmpAcudienteParentesco,_tmpAcudienteEmail,_tmpAcudienteTelefono,_tmpPoliticaId,_tmpFechaAceptacion,_tmpFechaRevocacion,_tmpMotivoRevocacion,_tmpDeviceInfo,_tmpHashFirmaDigital,_tmpGranularConsent,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun revokeConsent(
    consentId: String,
    timestamp: Long,
    reason: String,
  ) {
    val _sql: String =
        "UPDATE consentimientos SET fecha_revocacion = ?, motivo_revocacion = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindText(_argIndex, reason)
        _argIndex = 3
        _stmt.bindText(_argIndex, consentId)
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

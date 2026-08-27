package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.RetiroAnticipadoEntity
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
public class RetiroAnticipadoDao_Impl(
  __db: RoomDatabase,
) : RetiroAnticipadoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRetiroAnticipadoEntity: EntityInsertAdapter<RetiroAnticipadoEntity>

  private val __updateAdapterOfRetiroAnticipadoEntity:
      EntityDeleteOrUpdateAdapter<RetiroAnticipadoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRetiroAnticipadoEntity = object :
        EntityInsertAdapter<RetiroAnticipadoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `retiros_anticipados` (`id`,`institutionId`,`studentId`,`docenteId`,`fechaSalida`,`motivo`,`motivoOtro`,`tipoFirmante`,`firmanteNombre`,`firmanteDocumento`,`firmaDigitalPath`,`observaciones`,`notificadoAcudiente`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RetiroAnticipadoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.docenteId)
        statement.bindLong(5, entity.fechaSalida)
        statement.bindText(6, entity.motivo)
        val _tmpMotivoOtro: String? = entity.motivoOtro
        if (_tmpMotivoOtro == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpMotivoOtro)
        }
        statement.bindText(8, entity.tipoFirmante)
        statement.bindText(9, entity.firmanteNombre)
        statement.bindText(10, entity.firmanteDocumento)
        statement.bindText(11, entity.firmaDigitalPath)
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpObservaciones)
        }
        val _tmp: Int = if (entity.notificadoAcudiente) 1 else 0
        statement.bindLong(13, _tmp.toLong())
        statement.bindLong(14, entity.syncStatus.toLong())
        statement.bindLong(15, entity.lastModified)
      }
    }
    this.__updateAdapterOfRetiroAnticipadoEntity = object :
        EntityDeleteOrUpdateAdapter<RetiroAnticipadoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `retiros_anticipados` SET `id` = ?,`institutionId` = ?,`studentId` = ?,`docenteId` = ?,`fechaSalida` = ?,`motivo` = ?,`motivoOtro` = ?,`tipoFirmante` = ?,`firmanteNombre` = ?,`firmanteDocumento` = ?,`firmaDigitalPath` = ?,`observaciones` = ?,`notificadoAcudiente` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RetiroAnticipadoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.docenteId)
        statement.bindLong(5, entity.fechaSalida)
        statement.bindText(6, entity.motivo)
        val _tmpMotivoOtro: String? = entity.motivoOtro
        if (_tmpMotivoOtro == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpMotivoOtro)
        }
        statement.bindText(8, entity.tipoFirmante)
        statement.bindText(9, entity.firmanteNombre)
        statement.bindText(10, entity.firmanteDocumento)
        statement.bindText(11, entity.firmaDigitalPath)
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpObservaciones)
        }
        val _tmp: Int = if (entity.notificadoAcudiente) 1 else 0
        statement.bindLong(13, _tmp.toLong())
        statement.bindLong(14, entity.syncStatus.toLong())
        statement.bindLong(15, entity.lastModified)
        statement.bindLong(16, entity.id)
      }
    }
  }

  public override suspend fun insert(retiro: RetiroAnticipadoEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfRetiroAnticipadoEntity.insertAndReturnId(_connection,
        retiro)
    _result
  }

  public override suspend fun update(retiro: RetiroAnticipadoEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfRetiroAnticipadoEntity.handle(_connection, retiro)
  }

  public override fun getByStudent(studentId: String, instId: String):
      Flow<List<RetiroAnticipadoEntity>> {
    val _sql: String =
        "SELECT * FROM retiros_anticipados WHERE studentId = ? AND institutionId = ? ORDER BY fechaSalida DESC"
    return createFlow(__db, false, arrayOf("retiros_anticipados")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaSalida: Int = getColumnIndexOrThrow(_stmt, "fechaSalida")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfMotivoOtro: Int = getColumnIndexOrThrow(_stmt, "motivoOtro")
        val _columnIndexOfTipoFirmante: Int = getColumnIndexOrThrow(_stmt, "tipoFirmante")
        val _columnIndexOfFirmanteNombre: Int = getColumnIndexOrThrow(_stmt, "firmanteNombre")
        val _columnIndexOfFirmanteDocumento: Int = getColumnIndexOrThrow(_stmt, "firmanteDocumento")
        val _columnIndexOfFirmaDigitalPath: Int = getColumnIndexOrThrow(_stmt, "firmaDigitalPath")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<RetiroAnticipadoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RetiroAnticipadoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpFechaSalida: Long
          _tmpFechaSalida = _stmt.getLong(_columnIndexOfFechaSalida)
          val _tmpMotivo: String
          _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          val _tmpMotivoOtro: String?
          if (_stmt.isNull(_columnIndexOfMotivoOtro)) {
            _tmpMotivoOtro = null
          } else {
            _tmpMotivoOtro = _stmt.getText(_columnIndexOfMotivoOtro)
          }
          val _tmpTipoFirmante: String
          _tmpTipoFirmante = _stmt.getText(_columnIndexOfTipoFirmante)
          val _tmpFirmanteNombre: String
          _tmpFirmanteNombre = _stmt.getText(_columnIndexOfFirmanteNombre)
          val _tmpFirmanteDocumento: String
          _tmpFirmanteDocumento = _stmt.getText(_columnIndexOfFirmanteDocumento)
          val _tmpFirmaDigitalPath: String
          _tmpFirmaDigitalPath = _stmt.getText(_columnIndexOfFirmaDigitalPath)
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              RetiroAnticipadoEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpDocenteId,_tmpFechaSalida,_tmpMotivo,_tmpMotivoOtro,_tmpTipoFirmante,_tmpFirmanteNombre,_tmpFirmanteDocumento,_tmpFirmaDigitalPath,_tmpObservaciones,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<RetiroAnticipadoEntity> {
    val _sql: String =
        "SELECT * FROM retiros_anticipados WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaSalida: Int = getColumnIndexOrThrow(_stmt, "fechaSalida")
        val _columnIndexOfMotivo: Int = getColumnIndexOrThrow(_stmt, "motivo")
        val _columnIndexOfMotivoOtro: Int = getColumnIndexOrThrow(_stmt, "motivoOtro")
        val _columnIndexOfTipoFirmante: Int = getColumnIndexOrThrow(_stmt, "tipoFirmante")
        val _columnIndexOfFirmanteNombre: Int = getColumnIndexOrThrow(_stmt, "firmanteNombre")
        val _columnIndexOfFirmanteDocumento: Int = getColumnIndexOrThrow(_stmt, "firmanteDocumento")
        val _columnIndexOfFirmaDigitalPath: Int = getColumnIndexOrThrow(_stmt, "firmaDigitalPath")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfNotificadoAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "notificadoAcudiente")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<RetiroAnticipadoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RetiroAnticipadoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpFechaSalida: Long
          _tmpFechaSalida = _stmt.getLong(_columnIndexOfFechaSalida)
          val _tmpMotivo: String
          _tmpMotivo = _stmt.getText(_columnIndexOfMotivo)
          val _tmpMotivoOtro: String?
          if (_stmt.isNull(_columnIndexOfMotivoOtro)) {
            _tmpMotivoOtro = null
          } else {
            _tmpMotivoOtro = _stmt.getText(_columnIndexOfMotivoOtro)
          }
          val _tmpTipoFirmante: String
          _tmpTipoFirmante = _stmt.getText(_columnIndexOfTipoFirmante)
          val _tmpFirmanteNombre: String
          _tmpFirmanteNombre = _stmt.getText(_columnIndexOfFirmanteNombre)
          val _tmpFirmanteDocumento: String
          _tmpFirmanteDocumento = _stmt.getText(_columnIndexOfFirmanteDocumento)
          val _tmpFirmaDigitalPath: String
          _tmpFirmaDigitalPath = _stmt.getText(_columnIndexOfFirmaDigitalPath)
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpNotificadoAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificadoAcudiente).toInt()
          _tmpNotificadoAcudiente = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              RetiroAnticipadoEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpDocenteId,_tmpFechaSalida,_tmpMotivo,_tmpMotivoOtro,_tmpTipoFirmante,_tmpFirmanteNombre,_tmpFirmanteDocumento,_tmpFirmaDigitalPath,_tmpObservaciones,_tmpNotificadoAcudiente,_tmpSyncStatus,_tmpLastModified)
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

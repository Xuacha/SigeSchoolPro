package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.CertificadoEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CertificadoDao_Impl(
  __db: RoomDatabase,
) : CertificadoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCertificadoEntity: EntityInsertAdapter<CertificadoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCertificadoEntity = object : EntityInsertAdapter<CertificadoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `cashier_certificados` (`id`,`institutionId`,`studentId`,`tipo`,`fechaEmision`,`numeroSerie`,`rutaArchivo`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CertificadoEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.tipo)
        statement.bindLong(5, entity.fechaEmision)
        statement.bindText(6, entity.numeroSerie)
        statement.bindText(7, entity.rutaArchivo)
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(certificado: CertificadoEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfCertificadoEntity.insertAndReturnId(_connection,
        certificado)
    _result
  }

  public override fun getByEstudiante(instId: String, studentId: String):
      Flow<List<CertificadoEntity>> {
    val _sql: String =
        "SELECT * FROM cashier_certificados WHERE institutionId = ? AND studentId = ?"
    return createFlow(__db, false, arrayOf("cashier_certificados")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, studentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfNumeroSerie: Int = getColumnIndexOrThrow(_stmt, "numeroSerie")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CertificadoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CertificadoEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpNumeroSerie: String
          _tmpNumeroSerie = _stmt.getText(_columnIndexOfNumeroSerie)
          val _tmpRutaArchivo: String
          _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CertificadoEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTipo,_tmpFechaEmision,_tmpNumeroSerie,_tmpRutaArchivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: String): CertificadoEntity? {
    val _sql: String = "SELECT * FROM cashier_certificados WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfNumeroSerie: Int = getColumnIndexOrThrow(_stmt, "numeroSerie")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: CertificadoEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpNumeroSerie: String
          _tmpNumeroSerie = _stmt.getText(_columnIndexOfNumeroSerie)
          val _tmpRutaArchivo: String
          _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              CertificadoEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTipo,_tmpFechaEmision,_tmpNumeroSerie,_tmpRutaArchivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<CertificadoEntity> {
    val _sql: String =
        "SELECT * FROM cashier_certificados WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfNumeroSerie: Int = getColumnIndexOrThrow(_stmt, "numeroSerie")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CertificadoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CertificadoEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpNumeroSerie: String
          _tmpNumeroSerie = _stmt.getText(_columnIndexOfNumeroSerie)
          val _tmpRutaArchivo: String
          _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CertificadoEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpTipo,_tmpFechaEmision,_tmpNumeroSerie,_tmpRutaArchivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, timestamp: Long) {
    val _sql: String =
        "UPDATE cashier_certificados SET syncStatus = 0, lastModified = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
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

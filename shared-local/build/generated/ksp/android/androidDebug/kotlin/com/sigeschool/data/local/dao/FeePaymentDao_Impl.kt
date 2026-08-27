package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.FeePaymentEntity
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
public class FeePaymentDao_Impl(
  __db: RoomDatabase,
) : FeePaymentDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfFeePaymentEntity: EntityInsertAdapter<FeePaymentEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfFeePaymentEntity = object : EntityInsertAdapter<FeePaymentEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `fee_payments` (`id`,`studentId`,`institutionId`,`monto`,`concepto`,`fecha`,`usuarioRecibe`,`metodoPago`,`receiptUrl`,`sincronizado`,`version`,`deviceId`,`lastModified`,`syncStatus`,`syncAttempts`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FeePaymentEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.studentId)
        statement.bindText(3, entity.institutionId)
        statement.bindDouble(4, entity.monto)
        statement.bindText(5, entity.concepto)
        statement.bindText(6, entity.fecha)
        statement.bindText(7, entity.usuarioRecibe)
        statement.bindText(8, entity.metodoPago)
        val _tmpReceiptUrl: String? = entity.receiptUrl
        if (_tmpReceiptUrl == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpReceiptUrl)
        }
        val _tmp: Int = if (entity.sincronizado) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        statement.bindLong(11, entity.version)
        statement.bindText(12, entity.deviceId)
        statement.bindLong(13, entity.lastModified)
        statement.bindLong(14, entity.syncStatus.toLong())
        statement.bindLong(15, entity.syncAttempts.toLong())
      }
    }
  }

  public override suspend fun insert(payment: FeePaymentEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfFeePaymentEntity.insert(_connection, payment)
  }

  public override fun getPaymentsByStudent(studentId: String): Flow<List<FeePaymentEntity>> {
    val _sql: String = "SELECT * FROM fee_payments WHERE studentId = ? ORDER BY fecha DESC"
    return createFlow(__db, false, arrayOf("fee_payments")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _columnIndexOfUsuarioRecibe: Int = getColumnIndexOrThrow(_stmt, "usuarioRecibe")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfReceiptUrl: Int = getColumnIndexOrThrow(_stmt, "receiptUrl")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: MutableList<FeePaymentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FeePaymentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpFecha: String
          _tmpFecha = _stmt.getText(_columnIndexOfFecha)
          val _tmpUsuarioRecibe: String
          _tmpUsuarioRecibe = _stmt.getText(_columnIndexOfUsuarioRecibe)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpReceiptUrl: String?
          if (_stmt.isNull(_columnIndexOfReceiptUrl)) {
            _tmpReceiptUrl = null
          } else {
            _tmpReceiptUrl = _stmt.getText(_columnIndexOfReceiptUrl)
          }
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
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
          _item =
              FeePaymentEntity(_tmpId,_tmpStudentId,_tmpInstitutionId,_tmpMonto,_tmpConcepto,_tmpFecha,_tmpUsuarioRecibe,_tmpMetodoPago,_tmpReceiptUrl,_tmpSincronizado,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllPayments(institutionId: String): Flow<List<FeePaymentEntity>> {
    val _sql: String = "SELECT * FROM fee_payments WHERE institutionId = ? ORDER BY fecha DESC"
    return createFlow(__db, false, arrayOf("fee_payments")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _columnIndexOfUsuarioRecibe: Int = getColumnIndexOrThrow(_stmt, "usuarioRecibe")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfReceiptUrl: Int = getColumnIndexOrThrow(_stmt, "receiptUrl")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: MutableList<FeePaymentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FeePaymentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpFecha: String
          _tmpFecha = _stmt.getText(_columnIndexOfFecha)
          val _tmpUsuarioRecibe: String
          _tmpUsuarioRecibe = _stmt.getText(_columnIndexOfUsuarioRecibe)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpReceiptUrl: String?
          if (_stmt.isNull(_columnIndexOfReceiptUrl)) {
            _tmpReceiptUrl = null
          } else {
            _tmpReceiptUrl = _stmt.getText(_columnIndexOfReceiptUrl)
          }
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
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
          _item =
              FeePaymentEntity(_tmpId,_tmpStudentId,_tmpInstitutionId,_tmpMonto,_tmpConcepto,_tmpFecha,_tmpUsuarioRecibe,_tmpMetodoPago,_tmpReceiptUrl,_tmpSincronizado,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedPayments(): List<FeePaymentEntity> {
    val _sql: String = "SELECT * FROM fee_payments WHERE sincronizado = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _columnIndexOfUsuarioRecibe: Int = getColumnIndexOrThrow(_stmt, "usuarioRecibe")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfReceiptUrl: Int = getColumnIndexOrThrow(_stmt, "receiptUrl")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: MutableList<FeePaymentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FeePaymentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpFecha: String
          _tmpFecha = _stmt.getText(_columnIndexOfFecha)
          val _tmpUsuarioRecibe: String
          _tmpUsuarioRecibe = _stmt.getText(_columnIndexOfUsuarioRecibe)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpReceiptUrl: String?
          if (_stmt.isNull(_columnIndexOfReceiptUrl)) {
            _tmpReceiptUrl = null
          } else {
            _tmpReceiptUrl = _stmt.getText(_columnIndexOfReceiptUrl)
          }
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
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
          _item =
              FeePaymentEntity(_tmpId,_tmpStudentId,_tmpInstitutionId,_tmpMonto,_tmpConcepto,_tmpFecha,_tmpUsuarioRecibe,_tmpMetodoPago,_tmpReceiptUrl,_tmpSincronizado,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPaymentById(id: String): FeePaymentEntity? {
    val _sql: String = "SELECT * FROM fee_payments WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfFecha: Int = getColumnIndexOrThrow(_stmt, "fecha")
        val _columnIndexOfUsuarioRecibe: Int = getColumnIndexOrThrow(_stmt, "usuarioRecibe")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfReceiptUrl: Int = getColumnIndexOrThrow(_stmt, "receiptUrl")
        val _columnIndexOfSincronizado: Int = getColumnIndexOrThrow(_stmt, "sincronizado")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: FeePaymentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpFecha: String
          _tmpFecha = _stmt.getText(_columnIndexOfFecha)
          val _tmpUsuarioRecibe: String
          _tmpUsuarioRecibe = _stmt.getText(_columnIndexOfUsuarioRecibe)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpReceiptUrl: String?
          if (_stmt.isNull(_columnIndexOfReceiptUrl)) {
            _tmpReceiptUrl = null
          } else {
            _tmpReceiptUrl = _stmt.getText(_columnIndexOfReceiptUrl)
          }
          val _tmpSincronizado: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSincronizado).toInt()
          _tmpSincronizado = _tmp != 0
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
              FeePaymentEntity(_tmpId,_tmpStudentId,_tmpInstitutionId,_tmpMonto,_tmpConcepto,_tmpFecha,_tmpUsuarioRecibe,_tmpMetodoPago,_tmpReceiptUrl,_tmpSincronizado,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, url: String?) {
    val _sql: String = "UPDATE fee_payments SET sincronizado = 1, receiptUrl = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (url == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, url)
        }
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

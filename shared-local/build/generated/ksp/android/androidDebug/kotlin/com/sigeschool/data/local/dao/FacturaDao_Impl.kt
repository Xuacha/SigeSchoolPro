package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.FacturaEntity
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
public class FacturaDao_Impl(
  __db: RoomDatabase,
) : FacturaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfFacturaEntity: EntityInsertAdapter<FacturaEntity>

  private val __updateAdapterOfFacturaEntity: EntityDeleteOrUpdateAdapter<FacturaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfFacturaEntity = object : EntityInsertAdapter<FacturaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `cashier_facturas` (`id`,`institutionId`,`studentId`,`numeroFactura`,`fechaEmision`,`fechaVencimiento`,`subtotal`,`impuestos`,`total`,`saldoPendiente`,`estado`,`concepto`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FacturaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.numeroFactura)
        statement.bindLong(5, entity.fechaEmision)
        statement.bindLong(6, entity.fechaVencimiento)
        statement.bindDouble(7, entity.subtotal)
        statement.bindDouble(8, entity.impuestos)
        statement.bindDouble(9, entity.total)
        statement.bindDouble(10, entity.saldoPendiente)
        statement.bindText(11, entity.estado)
        statement.bindText(12, entity.concepto)
        statement.bindLong(13, entity.syncStatus.toLong())
        statement.bindLong(14, entity.lastModified)
      }
    }
    this.__updateAdapterOfFacturaEntity = object : EntityDeleteOrUpdateAdapter<FacturaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `cashier_facturas` SET `id` = ?,`institutionId` = ?,`studentId` = ?,`numeroFactura` = ?,`fechaEmision` = ?,`fechaVencimiento` = ?,`subtotal` = ?,`impuestos` = ?,`total` = ?,`saldoPendiente` = ?,`estado` = ?,`concepto` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: FacturaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.numeroFactura)
        statement.bindLong(5, entity.fechaEmision)
        statement.bindLong(6, entity.fechaVencimiento)
        statement.bindDouble(7, entity.subtotal)
        statement.bindDouble(8, entity.impuestos)
        statement.bindDouble(9, entity.total)
        statement.bindDouble(10, entity.saldoPendiente)
        statement.bindText(11, entity.estado)
        statement.bindText(12, entity.concepto)
        statement.bindLong(13, entity.syncStatus.toLong())
        statement.bindLong(14, entity.lastModified)
        statement.bindText(15, entity.id)
      }
    }
  }

  public override suspend fun insertFactura(factura: FacturaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfFacturaEntity.insert(_connection, factura)
  }

  public override suspend fun updateFactura(factura: FacturaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfFacturaEntity.handle(_connection, factura)
  }

  public override fun getAllFacturas(institutionId: String): Flow<List<FacturaEntity>> {
    val _sql: String = "SELECT * FROM cashier_facturas WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("cashier_facturas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfNumeroFactura: Int = getColumnIndexOrThrow(_stmt, "numeroFactura")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfSubtotal: Int = getColumnIndexOrThrow(_stmt, "subtotal")
        val _columnIndexOfImpuestos: Int = getColumnIndexOrThrow(_stmt, "impuestos")
        val _columnIndexOfTotal: Int = getColumnIndexOrThrow(_stmt, "total")
        val _columnIndexOfSaldoPendiente: Int = getColumnIndexOrThrow(_stmt, "saldoPendiente")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<FacturaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FacturaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpNumeroFactura: String
          _tmpNumeroFactura = _stmt.getText(_columnIndexOfNumeroFactura)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpSubtotal: Double
          _tmpSubtotal = _stmt.getDouble(_columnIndexOfSubtotal)
          val _tmpImpuestos: Double
          _tmpImpuestos = _stmt.getDouble(_columnIndexOfImpuestos)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          val _tmpSaldoPendiente: Double
          _tmpSaldoPendiente = _stmt.getDouble(_columnIndexOfSaldoPendiente)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              FacturaEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpNumeroFactura,_tmpFechaEmision,_tmpFechaVencimiento,_tmpSubtotal,_tmpImpuestos,_tmpTotal,_tmpSaldoPendiente,_tmpEstado,_tmpConcepto,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFacturasByStudent(studentId: String, institutionId: String):
      Flow<List<FacturaEntity>> {
    val _sql: String = "SELECT * FROM cashier_facturas WHERE studentId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("cashier_facturas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfNumeroFactura: Int = getColumnIndexOrThrow(_stmt, "numeroFactura")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfSubtotal: Int = getColumnIndexOrThrow(_stmt, "subtotal")
        val _columnIndexOfImpuestos: Int = getColumnIndexOrThrow(_stmt, "impuestos")
        val _columnIndexOfTotal: Int = getColumnIndexOrThrow(_stmt, "total")
        val _columnIndexOfSaldoPendiente: Int = getColumnIndexOrThrow(_stmt, "saldoPendiente")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<FacturaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FacturaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpNumeroFactura: String
          _tmpNumeroFactura = _stmt.getText(_columnIndexOfNumeroFactura)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpSubtotal: Double
          _tmpSubtotal = _stmt.getDouble(_columnIndexOfSubtotal)
          val _tmpImpuestos: Double
          _tmpImpuestos = _stmt.getDouble(_columnIndexOfImpuestos)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          val _tmpSaldoPendiente: Double
          _tmpSaldoPendiente = _stmt.getDouble(_columnIndexOfSaldoPendiente)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              FacturaEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpNumeroFactura,_tmpFechaEmision,_tmpFechaVencimiento,_tmpSubtotal,_tmpImpuestos,_tmpTotal,_tmpSaldoPendiente,_tmpEstado,_tmpConcepto,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByEstudianteSync(studentId: String, institutionId: String):
      List<FacturaEntity> {
    val _sql: String = "SELECT * FROM cashier_facturas WHERE studentId = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfNumeroFactura: Int = getColumnIndexOrThrow(_stmt, "numeroFactura")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfSubtotal: Int = getColumnIndexOrThrow(_stmt, "subtotal")
        val _columnIndexOfImpuestos: Int = getColumnIndexOrThrow(_stmt, "impuestos")
        val _columnIndexOfTotal: Int = getColumnIndexOrThrow(_stmt, "total")
        val _columnIndexOfSaldoPendiente: Int = getColumnIndexOrThrow(_stmt, "saldoPendiente")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<FacturaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FacturaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpNumeroFactura: String
          _tmpNumeroFactura = _stmt.getText(_columnIndexOfNumeroFactura)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpSubtotal: Double
          _tmpSubtotal = _stmt.getDouble(_columnIndexOfSubtotal)
          val _tmpImpuestos: Double
          _tmpImpuestos = _stmt.getDouble(_columnIndexOfImpuestos)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          val _tmpSaldoPendiente: Double
          _tmpSaldoPendiente = _stmt.getDouble(_columnIndexOfSaldoPendiente)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              FacturaEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpNumeroFactura,_tmpFechaEmision,_tmpFechaVencimiento,_tmpSubtotal,_tmpImpuestos,_tmpTotal,_tmpSaldoPendiente,_tmpEstado,_tmpConcepto,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getFacturaById(id: String, institutionId: String): FacturaEntity? {
    val _sql: String = "SELECT * FROM cashier_facturas WHERE id = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfNumeroFactura: Int = getColumnIndexOrThrow(_stmt, "numeroFactura")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfSubtotal: Int = getColumnIndexOrThrow(_stmt, "subtotal")
        val _columnIndexOfImpuestos: Int = getColumnIndexOrThrow(_stmt, "impuestos")
        val _columnIndexOfTotal: Int = getColumnIndexOrThrow(_stmt, "total")
        val _columnIndexOfSaldoPendiente: Int = getColumnIndexOrThrow(_stmt, "saldoPendiente")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfConcepto: Int = getColumnIndexOrThrow(_stmt, "concepto")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: FacturaEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpNumeroFactura: String
          _tmpNumeroFactura = _stmt.getText(_columnIndexOfNumeroFactura)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpSubtotal: Double
          _tmpSubtotal = _stmt.getDouble(_columnIndexOfSubtotal)
          val _tmpImpuestos: Double
          _tmpImpuestos = _stmt.getDouble(_columnIndexOfImpuestos)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          val _tmpSaldoPendiente: Double
          _tmpSaldoPendiente = _stmt.getDouble(_columnIndexOfSaldoPendiente)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpConcepto: String
          _tmpConcepto = _stmt.getText(_columnIndexOfConcepto)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              FacturaEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpNumeroFactura,_tmpFechaEmision,_tmpFechaVencimiento,_tmpSubtotal,_tmpImpuestos,_tmpTotal,_tmpSaldoPendiente,_tmpEstado,_tmpConcepto,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDeudaTotalEstudiante(studentId: String, institutionId: String):
      Flow<Double?> {
    val _sql: String =
        "SELECT SUM(saldoPendiente) FROM cashier_facturas WHERE studentId = ? AND institutionId = ? AND estado != 'PAGADA'"
    return createFlow(__db, false, arrayOf("cashier_facturas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

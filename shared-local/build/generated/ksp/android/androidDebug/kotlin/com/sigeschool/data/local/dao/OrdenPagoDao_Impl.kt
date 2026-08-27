package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.OrdenPagoEntity
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
public class OrdenPagoDao_Impl(
  __db: RoomDatabase,
) : OrdenPagoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfOrdenPagoEntity: EntityInsertAdapter<OrdenPagoEntity>

  private val __updateAdapterOfOrdenPagoEntity: EntityDeleteOrUpdateAdapter<OrdenPagoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfOrdenPagoEntity = object : EntityInsertAdapter<OrdenPagoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `ordenes_pago` (`id`,`institutionId`,`facturaId`,`estudianteId`,`referencia`,`monto`,`fechaGeneracion`,`fechaVencimiento`,`estado`,`metodoPago`,`datosPago`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: OrdenPagoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.facturaId)
        statement.bindText(4, entity.estudianteId)
        statement.bindText(5, entity.referencia)
        statement.bindDouble(6, entity.monto)
        statement.bindLong(7, entity.fechaGeneracion)
        statement.bindLong(8, entity.fechaVencimiento)
        statement.bindText(9, entity.estado)
        statement.bindText(10, entity.metodoPago)
        statement.bindText(11, entity.datosPago)
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
      }
    }
    this.__updateAdapterOfOrdenPagoEntity = object : EntityDeleteOrUpdateAdapter<OrdenPagoEntity>()
        {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `ordenes_pago` SET `id` = ?,`institutionId` = ?,`facturaId` = ?,`estudianteId` = ?,`referencia` = ?,`monto` = ?,`fechaGeneracion` = ?,`fechaVencimiento` = ?,`estado` = ?,`metodoPago` = ?,`datosPago` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: OrdenPagoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.facturaId)
        statement.bindText(4, entity.estudianteId)
        statement.bindText(5, entity.referencia)
        statement.bindDouble(6, entity.monto)
        statement.bindLong(7, entity.fechaGeneracion)
        statement.bindLong(8, entity.fechaVencimiento)
        statement.bindText(9, entity.estado)
        statement.bindText(10, entity.metodoPago)
        statement.bindText(11, entity.datosPago)
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
        statement.bindLong(14, entity.id)
      }
    }
  }

  public override suspend fun insert(orden: OrdenPagoEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfOrdenPagoEntity.insertAndReturnId(_connection, orden)
    _result
  }

  public override suspend fun update(orden: OrdenPagoEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfOrdenPagoEntity.handle(_connection, orden)
  }

  public override suspend fun getById(id: Long, instId: String): OrdenPagoEntity? {
    val _sql: String = "SELECT * FROM ordenes_pago WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFacturaId: Int = getColumnIndexOrThrow(_stmt, "facturaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfReferencia: Int = getColumnIndexOrThrow(_stmt, "referencia")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfFechaGeneracion: Int = getColumnIndexOrThrow(_stmt, "fechaGeneracion")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfDatosPago: Int = getColumnIndexOrThrow(_stmt, "datosPago")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: OrdenPagoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFacturaId: String
          _tmpFacturaId = _stmt.getText(_columnIndexOfFacturaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpReferencia: String
          _tmpReferencia = _stmt.getText(_columnIndexOfReferencia)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpFechaGeneracion: Long
          _tmpFechaGeneracion = _stmt.getLong(_columnIndexOfFechaGeneracion)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpDatosPago: String
          _tmpDatosPago = _stmt.getText(_columnIndexOfDatosPago)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              OrdenPagoEntity(_tmpId,_tmpInstitutionId,_tmpFacturaId,_tmpEstudianteId,_tmpReferencia,_tmpMonto,_tmpFechaGeneracion,_tmpFechaVencimiento,_tmpEstado,_tmpMetodoPago,_tmpDatosPago,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByFactura(facturaId: String, instId: String): OrdenPagoEntity? {
    val _sql: String = "SELECT * FROM ordenes_pago WHERE facturaId = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, facturaId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFacturaId: Int = getColumnIndexOrThrow(_stmt, "facturaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfReferencia: Int = getColumnIndexOrThrow(_stmt, "referencia")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfFechaGeneracion: Int = getColumnIndexOrThrow(_stmt, "fechaGeneracion")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfDatosPago: Int = getColumnIndexOrThrow(_stmt, "datosPago")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: OrdenPagoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFacturaId: String
          _tmpFacturaId = _stmt.getText(_columnIndexOfFacturaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpReferencia: String
          _tmpReferencia = _stmt.getText(_columnIndexOfReferencia)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpFechaGeneracion: Long
          _tmpFechaGeneracion = _stmt.getLong(_columnIndexOfFechaGeneracion)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpDatosPago: String
          _tmpDatosPago = _stmt.getText(_columnIndexOfDatosPago)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              OrdenPagoEntity(_tmpId,_tmpInstitutionId,_tmpFacturaId,_tmpEstudianteId,_tmpReferencia,_tmpMonto,_tmpFechaGeneracion,_tmpFechaVencimiento,_tmpEstado,_tmpMetodoPago,_tmpDatosPago,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPendientes(instId: String): Flow<List<OrdenPagoEntity>> {
    val _sql: String =
        "SELECT * FROM ordenes_pago WHERE institutionId = ? AND estado = 'PENDIENTE' ORDER BY fechaVencimiento ASC"
    return createFlow(__db, false, arrayOf("ordenes_pago")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFacturaId: Int = getColumnIndexOrThrow(_stmt, "facturaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfReferencia: Int = getColumnIndexOrThrow(_stmt, "referencia")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfFechaGeneracion: Int = getColumnIndexOrThrow(_stmt, "fechaGeneracion")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfDatosPago: Int = getColumnIndexOrThrow(_stmt, "datosPago")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OrdenPagoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OrdenPagoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFacturaId: String
          _tmpFacturaId = _stmt.getText(_columnIndexOfFacturaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpReferencia: String
          _tmpReferencia = _stmt.getText(_columnIndexOfReferencia)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpFechaGeneracion: Long
          _tmpFechaGeneracion = _stmt.getLong(_columnIndexOfFechaGeneracion)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpDatosPago: String
          _tmpDatosPago = _stmt.getText(_columnIndexOfDatosPago)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OrdenPagoEntity(_tmpId,_tmpInstitutionId,_tmpFacturaId,_tmpEstudianteId,_tmpReferencia,_tmpMonto,_tmpFechaGeneracion,_tmpFechaVencimiento,_tmpEstado,_tmpMetodoPago,_tmpDatosPago,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPagadas(instId: String, limit: Int): Flow<List<OrdenPagoEntity>> {
    val _sql: String =
        "SELECT * FROM ordenes_pago WHERE institutionId = ? AND estado = 'PAGADA' ORDER BY fechaGeneracion DESC LIMIT ?"
    return createFlow(__db, false, arrayOf("ordenes_pago")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFacturaId: Int = getColumnIndexOrThrow(_stmt, "facturaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfReferencia: Int = getColumnIndexOrThrow(_stmt, "referencia")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfFechaGeneracion: Int = getColumnIndexOrThrow(_stmt, "fechaGeneracion")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfDatosPago: Int = getColumnIndexOrThrow(_stmt, "datosPago")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OrdenPagoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OrdenPagoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFacturaId: String
          _tmpFacturaId = _stmt.getText(_columnIndexOfFacturaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpReferencia: String
          _tmpReferencia = _stmt.getText(_columnIndexOfReferencia)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpFechaGeneracion: Long
          _tmpFechaGeneracion = _stmt.getLong(_columnIndexOfFechaGeneracion)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpDatosPago: String
          _tmpDatosPago = _stmt.getText(_columnIndexOfDatosPago)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OrdenPagoEntity(_tmpId,_tmpInstitutionId,_tmpFacturaId,_tmpEstudianteId,_tmpReferencia,_tmpMonto,_tmpFechaGeneracion,_tmpFechaVencimiento,_tmpEstado,_tmpMetodoPago,_tmpDatosPago,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<OrdenPagoEntity> {
    val _sql: String = "SELECT * FROM ordenes_pago WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFacturaId: Int = getColumnIndexOrThrow(_stmt, "facturaId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfReferencia: Int = getColumnIndexOrThrow(_stmt, "referencia")
        val _columnIndexOfMonto: Int = getColumnIndexOrThrow(_stmt, "monto")
        val _columnIndexOfFechaGeneracion: Int = getColumnIndexOrThrow(_stmt, "fechaGeneracion")
        val _columnIndexOfFechaVencimiento: Int = getColumnIndexOrThrow(_stmt, "fechaVencimiento")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfDatosPago: Int = getColumnIndexOrThrow(_stmt, "datosPago")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OrdenPagoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OrdenPagoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFacturaId: String
          _tmpFacturaId = _stmt.getText(_columnIndexOfFacturaId)
          val _tmpEstudianteId: String
          _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          val _tmpReferencia: String
          _tmpReferencia = _stmt.getText(_columnIndexOfReferencia)
          val _tmpMonto: Double
          _tmpMonto = _stmt.getDouble(_columnIndexOfMonto)
          val _tmpFechaGeneracion: Long
          _tmpFechaGeneracion = _stmt.getLong(_columnIndexOfFechaGeneracion)
          val _tmpFechaVencimiento: Long
          _tmpFechaVencimiento = _stmt.getLong(_columnIndexOfFechaVencimiento)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpDatosPago: String
          _tmpDatosPago = _stmt.getText(_columnIndexOfDatosPago)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OrdenPagoEntity(_tmpId,_tmpInstitutionId,_tmpFacturaId,_tmpEstudianteId,_tmpReferencia,_tmpMonto,_tmpFechaGeneracion,_tmpFechaVencimiento,_tmpEstado,_tmpMetodoPago,_tmpDatosPago,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun actualizarVencidas(fechaActual: Long, instId: String) {
    val _sql: String =
        "UPDATE ordenes_pago SET estado = 'VENCIDA' WHERE fechaVencimiento < ? AND estado = 'PENDIENTE' AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fechaActual)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE ordenes_pago SET syncStatus = 0, lastModified = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
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

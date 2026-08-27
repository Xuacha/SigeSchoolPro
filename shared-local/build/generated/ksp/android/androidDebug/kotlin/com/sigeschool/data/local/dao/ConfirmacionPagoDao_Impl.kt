package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ConfirmacionPagoEntity
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
public class ConfirmacionPagoDao_Impl(
  __db: RoomDatabase,
) : ConfirmacionPagoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfConfirmacionPagoEntity: EntityInsertAdapter<ConfirmacionPagoEntity>

  private val __updateAdapterOfConfirmacionPagoEntity:
      EntityDeleteOrUpdateAdapter<ConfirmacionPagoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfConfirmacionPagoEntity = object :
        EntityInsertAdapter<ConfirmacionPagoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `confirmaciones_pago` (`id`,`institutionId`,`ordenPagoId`,`referenciaIngresada`,`valorIngresado`,`fechaConfirmacion`,`estadoValidacion`,`observacion`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConfirmacionPagoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.ordenPagoId)
        statement.bindText(4, entity.referenciaIngresada)
        val _tmpValorIngresado: Double? = entity.valorIngresado
        if (_tmpValorIngresado == null) {
          statement.bindNull(5)
        } else {
          statement.bindDouble(5, _tmpValorIngresado)
        }
        statement.bindLong(6, entity.fechaConfirmacion)
        statement.bindText(7, entity.estadoValidacion)
        val _tmpObservacion: String? = entity.observacion
        if (_tmpObservacion == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpObservacion)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfConfirmacionPagoEntity = object :
        EntityDeleteOrUpdateAdapter<ConfirmacionPagoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `confirmaciones_pago` SET `id` = ?,`institutionId` = ?,`ordenPagoId` = ?,`referenciaIngresada` = ?,`valorIngresado` = ?,`fechaConfirmacion` = ?,`estadoValidacion` = ?,`observacion` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ConfirmacionPagoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.ordenPagoId)
        statement.bindText(4, entity.referenciaIngresada)
        val _tmpValorIngresado: Double? = entity.valorIngresado
        if (_tmpValorIngresado == null) {
          statement.bindNull(5)
        } else {
          statement.bindDouble(5, _tmpValorIngresado)
        }
        statement.bindLong(6, entity.fechaConfirmacion)
        statement.bindText(7, entity.estadoValidacion)
        val _tmpObservacion: String? = entity.observacion
        if (_tmpObservacion == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpObservacion)
        }
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindLong(11, entity.id)
      }
    }
  }

  public override suspend fun insert(confirmacion: ConfirmacionPagoEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfConfirmacionPagoEntity.insertAndReturnId(_connection,
        confirmacion)
    _result
  }

  public override suspend fun update(confirmacion: ConfirmacionPagoEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfConfirmacionPagoEntity.handle(_connection, confirmacion)
  }

  public override suspend fun getById(id: Long, instId: String): ConfirmacionPagoEntity? {
    val _sql: String = "SELECT * FROM confirmaciones_pago WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOrdenPagoId: Int = getColumnIndexOrThrow(_stmt, "ordenPagoId")
        val _columnIndexOfReferenciaIngresada: Int = getColumnIndexOrThrow(_stmt,
            "referenciaIngresada")
        val _columnIndexOfValorIngresado: Int = getColumnIndexOrThrow(_stmt, "valorIngresado")
        val _columnIndexOfFechaConfirmacion: Int = getColumnIndexOrThrow(_stmt, "fechaConfirmacion")
        val _columnIndexOfEstadoValidacion: Int = getColumnIndexOrThrow(_stmt, "estadoValidacion")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ConfirmacionPagoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOrdenPagoId: Long
          _tmpOrdenPagoId = _stmt.getLong(_columnIndexOfOrdenPagoId)
          val _tmpReferenciaIngresada: String
          _tmpReferenciaIngresada = _stmt.getText(_columnIndexOfReferenciaIngresada)
          val _tmpValorIngresado: Double?
          if (_stmt.isNull(_columnIndexOfValorIngresado)) {
            _tmpValorIngresado = null
          } else {
            _tmpValorIngresado = _stmt.getDouble(_columnIndexOfValorIngresado)
          }
          val _tmpFechaConfirmacion: Long
          _tmpFechaConfirmacion = _stmt.getLong(_columnIndexOfFechaConfirmacion)
          val _tmpEstadoValidacion: String
          _tmpEstadoValidacion = _stmt.getText(_columnIndexOfEstadoValidacion)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ConfirmacionPagoEntity(_tmpId,_tmpInstitutionId,_tmpOrdenPagoId,_tmpReferenciaIngresada,_tmpValorIngresado,_tmpFechaConfirmacion,_tmpEstadoValidacion,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByOrden(ordenId: Long, instId: String): ConfirmacionPagoEntity? {
    val _sql: String =
        "SELECT * FROM confirmaciones_pago WHERE ordenPagoId = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, ordenId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOrdenPagoId: Int = getColumnIndexOrThrow(_stmt, "ordenPagoId")
        val _columnIndexOfReferenciaIngresada: Int = getColumnIndexOrThrow(_stmt,
            "referenciaIngresada")
        val _columnIndexOfValorIngresado: Int = getColumnIndexOrThrow(_stmt, "valorIngresado")
        val _columnIndexOfFechaConfirmacion: Int = getColumnIndexOrThrow(_stmt, "fechaConfirmacion")
        val _columnIndexOfEstadoValidacion: Int = getColumnIndexOrThrow(_stmt, "estadoValidacion")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ConfirmacionPagoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOrdenPagoId: Long
          _tmpOrdenPagoId = _stmt.getLong(_columnIndexOfOrdenPagoId)
          val _tmpReferenciaIngresada: String
          _tmpReferenciaIngresada = _stmt.getText(_columnIndexOfReferenciaIngresada)
          val _tmpValorIngresado: Double?
          if (_stmt.isNull(_columnIndexOfValorIngresado)) {
            _tmpValorIngresado = null
          } else {
            _tmpValorIngresado = _stmt.getDouble(_columnIndexOfValorIngresado)
          }
          val _tmpFechaConfirmacion: Long
          _tmpFechaConfirmacion = _stmt.getLong(_columnIndexOfFechaConfirmacion)
          val _tmpEstadoValidacion: String
          _tmpEstadoValidacion = _stmt.getText(_columnIndexOfEstadoValidacion)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ConfirmacionPagoEntity(_tmpId,_tmpInstitutionId,_tmpOrdenPagoId,_tmpReferenciaIngresada,_tmpValorIngresado,_tmpFechaConfirmacion,_tmpEstadoValidacion,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPendientesValidacion(instId: String): Flow<List<ConfirmacionPagoEntity>> {
    val _sql: String =
        "SELECT * FROM confirmaciones_pago WHERE institutionId = ? AND estadoValidacion = 'PENDIENTE'"
    return createFlow(__db, false, arrayOf("confirmaciones_pago")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOrdenPagoId: Int = getColumnIndexOrThrow(_stmt, "ordenPagoId")
        val _columnIndexOfReferenciaIngresada: Int = getColumnIndexOrThrow(_stmt,
            "referenciaIngresada")
        val _columnIndexOfValorIngresado: Int = getColumnIndexOrThrow(_stmt, "valorIngresado")
        val _columnIndexOfFechaConfirmacion: Int = getColumnIndexOrThrow(_stmt, "fechaConfirmacion")
        val _columnIndexOfEstadoValidacion: Int = getColumnIndexOrThrow(_stmt, "estadoValidacion")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ConfirmacionPagoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConfirmacionPagoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOrdenPagoId: Long
          _tmpOrdenPagoId = _stmt.getLong(_columnIndexOfOrdenPagoId)
          val _tmpReferenciaIngresada: String
          _tmpReferenciaIngresada = _stmt.getText(_columnIndexOfReferenciaIngresada)
          val _tmpValorIngresado: Double?
          if (_stmt.isNull(_columnIndexOfValorIngresado)) {
            _tmpValorIngresado = null
          } else {
            _tmpValorIngresado = _stmt.getDouble(_columnIndexOfValorIngresado)
          }
          val _tmpFechaConfirmacion: Long
          _tmpFechaConfirmacion = _stmt.getLong(_columnIndexOfFechaConfirmacion)
          val _tmpEstadoValidacion: String
          _tmpEstadoValidacion = _stmt.getText(_columnIndexOfEstadoValidacion)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ConfirmacionPagoEntity(_tmpId,_tmpInstitutionId,_tmpOrdenPagoId,_tmpReferenciaIngresada,_tmpValorIngresado,_tmpFechaConfirmacion,_tmpEstadoValidacion,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<ConfirmacionPagoEntity> {
    val _sql: String =
        "SELECT * FROM confirmaciones_pago WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOrdenPagoId: Int = getColumnIndexOrThrow(_stmt, "ordenPagoId")
        val _columnIndexOfReferenciaIngresada: Int = getColumnIndexOrThrow(_stmt,
            "referenciaIngresada")
        val _columnIndexOfValorIngresado: Int = getColumnIndexOrThrow(_stmt, "valorIngresado")
        val _columnIndexOfFechaConfirmacion: Int = getColumnIndexOrThrow(_stmt, "fechaConfirmacion")
        val _columnIndexOfEstadoValidacion: Int = getColumnIndexOrThrow(_stmt, "estadoValidacion")
        val _columnIndexOfObservacion: Int = getColumnIndexOrThrow(_stmt, "observacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ConfirmacionPagoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConfirmacionPagoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOrdenPagoId: Long
          _tmpOrdenPagoId = _stmt.getLong(_columnIndexOfOrdenPagoId)
          val _tmpReferenciaIngresada: String
          _tmpReferenciaIngresada = _stmt.getText(_columnIndexOfReferenciaIngresada)
          val _tmpValorIngresado: Double?
          if (_stmt.isNull(_columnIndexOfValorIngresado)) {
            _tmpValorIngresado = null
          } else {
            _tmpValorIngresado = _stmt.getDouble(_columnIndexOfValorIngresado)
          }
          val _tmpFechaConfirmacion: Long
          _tmpFechaConfirmacion = _stmt.getLong(_columnIndexOfFechaConfirmacion)
          val _tmpEstadoValidacion: String
          _tmpEstadoValidacion = _stmt.getText(_columnIndexOfEstadoValidacion)
          val _tmpObservacion: String?
          if (_stmt.isNull(_columnIndexOfObservacion)) {
            _tmpObservacion = null
          } else {
            _tmpObservacion = _stmt.getText(_columnIndexOfObservacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ConfirmacionPagoEntity(_tmpId,_tmpInstitutionId,_tmpOrdenPagoId,_tmpReferenciaIngresada,_tmpValorIngresado,_tmpFechaConfirmacion,_tmpEstadoValidacion,_tmpObservacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String =
        "UPDATE confirmaciones_pago SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

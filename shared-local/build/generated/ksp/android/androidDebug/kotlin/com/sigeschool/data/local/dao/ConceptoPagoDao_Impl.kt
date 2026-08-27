package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ConceptoPagoEntity
import javax.`annotation`.processing.Generated
import kotlin.Double
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
public class ConceptoPagoDao_Impl(
  __db: RoomDatabase,
) : ConceptoPagoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfConceptoPagoEntity: EntityInsertAdapter<ConceptoPagoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfConceptoPagoEntity = object : EntityInsertAdapter<ConceptoPagoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `cashier_conceptos` (`id`,`institutionId`,`nombre`,`montoBase`,`descripcion`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConceptoPagoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        statement.bindDouble(4, entity.montoBase)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDescripcion)
        }
        statement.bindLong(6, entity.syncStatus.toLong())
        statement.bindLong(7, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(entity: ConceptoPagoEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfConceptoPagoEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override fun getAll(instId: String): Flow<List<ConceptoPagoEntity>> {
    val _sql: String = "SELECT * FROM cashier_conceptos WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("cashier_conceptos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfMontoBase: Int = getColumnIndexOrThrow(_stmt, "montoBase")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ConceptoPagoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConceptoPagoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpMontoBase: Double
          _tmpMontoBase = _stmt.getDouble(_columnIndexOfMontoBase)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ConceptoPagoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpMontoBase,_tmpDescripcion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): ConceptoPagoEntity? {
    val _sql: String = "SELECT * FROM cashier_conceptos WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfMontoBase: Int = getColumnIndexOrThrow(_stmt, "montoBase")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ConceptoPagoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpMontoBase: Double
          _tmpMontoBase = _stmt.getDouble(_columnIndexOfMontoBase)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ConceptoPagoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpMontoBase,_tmpDescripcion,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM cashier_conceptos WHERE id = ? AND institutionId = ?"
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

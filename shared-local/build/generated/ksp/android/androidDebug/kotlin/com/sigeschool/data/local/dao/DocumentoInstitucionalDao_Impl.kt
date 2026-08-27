package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.DocumentoInstitucionalEntity
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
public class DocumentoInstitucionalDao_Impl(
  __db: RoomDatabase,
) : DocumentoInstitucionalDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDocumentoInstitucionalEntity:
      EntityInsertAdapter<DocumentoInstitucionalEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDocumentoInstitucionalEntity = object :
        EntityInsertAdapter<DocumentoInstitucionalEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `documentos_institucionales` (`id`,`institutionId`,`titulo`,`tipo`,`rutaArchivo`,`fechaSubida`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement,
          entity: DocumentoInstitucionalEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.titulo)
        statement.bindText(4, entity.tipo)
        statement.bindText(5, entity.rutaArchivo)
        statement.bindLong(6, entity.fechaSubida)
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(documento: DocumentoInstitucionalEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfDocumentoInstitucionalEntity.insertAndReturnId(_connection,
        documento)
    _result
  }

  public override fun getByTipo(instId: String, tipo: String):
      Flow<List<DocumentoInstitucionalEntity>> {
    val _sql: String =
        "SELECT * FROM documentos_institucionales WHERE institutionId = ? AND tipo = ?"
    return createFlow(__db, false, arrayOf("documentos_institucionales")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, tipo)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfFechaSubida: Int = getColumnIndexOrThrow(_stmt, "fechaSubida")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DocumentoInstitucionalEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocumentoInstitucionalEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpRutaArchivo: String
          _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          val _tmpFechaSubida: Long
          _tmpFechaSubida = _stmt.getLong(_columnIndexOfFechaSubida)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DocumentoInstitucionalEntity(_tmpId,_tmpInstitutionId,_tmpTitulo,_tmpTipo,_tmpRutaArchivo,_tmpFechaSubida,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAll(instId: String): Flow<List<DocumentoInstitucionalEntity>> {
    val _sql: String = "SELECT * FROM documentos_institucionales WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("documentos_institucionales")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfFechaSubida: Int = getColumnIndexOrThrow(_stmt, "fechaSubida")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DocumentoInstitucionalEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocumentoInstitucionalEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpRutaArchivo: String
          _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          val _tmpFechaSubida: Long
          _tmpFechaSubida = _stmt.getLong(_columnIndexOfFechaSubida)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DocumentoInstitucionalEntity(_tmpId,_tmpInstitutionId,_tmpTitulo,_tmpTipo,_tmpRutaArchivo,_tmpFechaSubida,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): DocumentoInstitucionalEntity? {
    val _sql: String = "SELECT * FROM documentos_institucionales WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfRutaArchivo: Int = getColumnIndexOrThrow(_stmt, "rutaArchivo")
        val _columnIndexOfFechaSubida: Int = getColumnIndexOrThrow(_stmt, "fechaSubida")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: DocumentoInstitucionalEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpRutaArchivo: String
          _tmpRutaArchivo = _stmt.getText(_columnIndexOfRutaArchivo)
          val _tmpFechaSubida: Long
          _tmpFechaSubida = _stmt.getLong(_columnIndexOfFechaSubida)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              DocumentoInstitucionalEntity(_tmpId,_tmpInstitutionId,_tmpTitulo,_tmpTipo,_tmpRutaArchivo,_tmpFechaSubida,_tmpSyncStatus,_tmpLastModified)
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
    val _sql: String = "DELETE FROM documentos_institucionales WHERE id = ? AND institutionId = ?"
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

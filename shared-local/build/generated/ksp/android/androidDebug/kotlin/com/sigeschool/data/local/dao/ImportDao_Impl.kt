package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ImportDetailEntity
import com.sigeschool.`data`.local.entity.ImportEntity
import javax.`annotation`.processing.Generated
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
public class ImportDao_Impl(
  __db: RoomDatabase,
) : ImportDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfImportEntity: EntityInsertAdapter<ImportEntity>

  private val __insertAdapterOfImportDetailEntity: EntityInsertAdapter<ImportDetailEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfImportEntity = object : EntityInsertAdapter<ImportEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `importaciones` (`idImportacion`,`tipo`,`nombreArchivo`,`fechaImportacion`,`idUsuarioImporto`,`totalRegistros`,`registrosCreados`,`registrosActualizados`,`errores`,`duplicados`,`usuariosCreados`,`notificacionesEnviadas`,`estado`,`detalleJson`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ImportEntity) {
        statement.bindText(1, entity.idImportacion)
        statement.bindText(2, entity.tipo)
        statement.bindText(3, entity.nombreArchivo)
        statement.bindLong(4, entity.fechaImportacion)
        statement.bindText(5, entity.idUsuarioImporto)
        statement.bindLong(6, entity.totalRegistros.toLong())
        statement.bindLong(7, entity.registrosCreados.toLong())
        statement.bindLong(8, entity.registrosActualizados.toLong())
        statement.bindLong(9, entity.errores.toLong())
        statement.bindLong(10, entity.duplicados.toLong())
        statement.bindLong(11, entity.usuariosCreados.toLong())
        statement.bindLong(12, entity.notificacionesEnviadas.toLong())
        statement.bindText(13, entity.estado)
        val _tmpDetalleJson: String? = entity.detalleJson
        if (_tmpDetalleJson == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpDetalleJson)
        }
      }
    }
    this.__insertAdapterOfImportDetailEntity = object : EntityInsertAdapter<ImportDetailEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `importaciones_detalle` (`idDetalle`,`idImportacion`,`fila`,`documento`,`accion`,`mensaje`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ImportDetailEntity) {
        statement.bindText(1, entity.idDetalle)
        statement.bindText(2, entity.idImportacion)
        statement.bindLong(3, entity.fila.toLong())
        val _tmpDocumento: String? = entity.documento
        if (_tmpDocumento == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDocumento)
        }
        statement.bindText(5, entity.accion)
        val _tmpMensaje: String? = entity.mensaje
        if (_tmpMensaje == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpMensaje)
        }
      }
    }
  }

  public override suspend fun insertImport(importation: ImportEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfImportEntity.insert(_connection, importation)
  }

  public override suspend fun insertImportDetail(detail: ImportDetailEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfImportDetailEntity.insert(_connection, detail)
  }

  public override fun getAllImports(): Flow<List<ImportEntity>> {
    val _sql: String = "SELECT * FROM importaciones ORDER BY fechaImportacion DESC"
    return createFlow(__db, false, arrayOf("importaciones")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfIdImportacion: Int = getColumnIndexOrThrow(_stmt, "idImportacion")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfNombreArchivo: Int = getColumnIndexOrThrow(_stmt, "nombreArchivo")
        val _columnIndexOfFechaImportacion: Int = getColumnIndexOrThrow(_stmt, "fechaImportacion")
        val _columnIndexOfIdUsuarioImporto: Int = getColumnIndexOrThrow(_stmt, "idUsuarioImporto")
        val _columnIndexOfTotalRegistros: Int = getColumnIndexOrThrow(_stmt, "totalRegistros")
        val _columnIndexOfRegistrosCreados: Int = getColumnIndexOrThrow(_stmt, "registrosCreados")
        val _columnIndexOfRegistrosActualizados: Int = getColumnIndexOrThrow(_stmt,
            "registrosActualizados")
        val _columnIndexOfErrores: Int = getColumnIndexOrThrow(_stmt, "errores")
        val _columnIndexOfDuplicados: Int = getColumnIndexOrThrow(_stmt, "duplicados")
        val _columnIndexOfUsuariosCreados: Int = getColumnIndexOrThrow(_stmt, "usuariosCreados")
        val _columnIndexOfNotificacionesEnviadas: Int = getColumnIndexOrThrow(_stmt,
            "notificacionesEnviadas")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfDetalleJson: Int = getColumnIndexOrThrow(_stmt, "detalleJson")
        val _result: MutableList<ImportEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ImportEntity
          val _tmpIdImportacion: String
          _tmpIdImportacion = _stmt.getText(_columnIndexOfIdImportacion)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpNombreArchivo: String
          _tmpNombreArchivo = _stmt.getText(_columnIndexOfNombreArchivo)
          val _tmpFechaImportacion: Long
          _tmpFechaImportacion = _stmt.getLong(_columnIndexOfFechaImportacion)
          val _tmpIdUsuarioImporto: String
          _tmpIdUsuarioImporto = _stmt.getText(_columnIndexOfIdUsuarioImporto)
          val _tmpTotalRegistros: Int
          _tmpTotalRegistros = _stmt.getLong(_columnIndexOfTotalRegistros).toInt()
          val _tmpRegistrosCreados: Int
          _tmpRegistrosCreados = _stmt.getLong(_columnIndexOfRegistrosCreados).toInt()
          val _tmpRegistrosActualizados: Int
          _tmpRegistrosActualizados = _stmt.getLong(_columnIndexOfRegistrosActualizados).toInt()
          val _tmpErrores: Int
          _tmpErrores = _stmt.getLong(_columnIndexOfErrores).toInt()
          val _tmpDuplicados: Int
          _tmpDuplicados = _stmt.getLong(_columnIndexOfDuplicados).toInt()
          val _tmpUsuariosCreados: Int
          _tmpUsuariosCreados = _stmt.getLong(_columnIndexOfUsuariosCreados).toInt()
          val _tmpNotificacionesEnviadas: Int
          _tmpNotificacionesEnviadas = _stmt.getLong(_columnIndexOfNotificacionesEnviadas).toInt()
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpDetalleJson: String?
          if (_stmt.isNull(_columnIndexOfDetalleJson)) {
            _tmpDetalleJson = null
          } else {
            _tmpDetalleJson = _stmt.getText(_columnIndexOfDetalleJson)
          }
          _item =
              ImportEntity(_tmpIdImportacion,_tmpTipo,_tmpNombreArchivo,_tmpFechaImportacion,_tmpIdUsuarioImporto,_tmpTotalRegistros,_tmpRegistrosCreados,_tmpRegistrosActualizados,_tmpErrores,_tmpDuplicados,_tmpUsuariosCreados,_tmpNotificacionesEnviadas,_tmpEstado,_tmpDetalleJson)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDetailsByImport(idImport: String): List<ImportDetailEntity> {
    val _sql: String = "SELECT * FROM importaciones_detalle WHERE idImportacion = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, idImport)
        val _columnIndexOfIdDetalle: Int = getColumnIndexOrThrow(_stmt, "idDetalle")
        val _columnIndexOfIdImportacion: Int = getColumnIndexOrThrow(_stmt, "idImportacion")
        val _columnIndexOfFila: Int = getColumnIndexOrThrow(_stmt, "fila")
        val _columnIndexOfDocumento: Int = getColumnIndexOrThrow(_stmt, "documento")
        val _columnIndexOfAccion: Int = getColumnIndexOrThrow(_stmt, "accion")
        val _columnIndexOfMensaje: Int = getColumnIndexOrThrow(_stmt, "mensaje")
        val _result: MutableList<ImportDetailEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ImportDetailEntity
          val _tmpIdDetalle: String
          _tmpIdDetalle = _stmt.getText(_columnIndexOfIdDetalle)
          val _tmpIdImportacion: String
          _tmpIdImportacion = _stmt.getText(_columnIndexOfIdImportacion)
          val _tmpFila: Int
          _tmpFila = _stmt.getLong(_columnIndexOfFila).toInt()
          val _tmpDocumento: String?
          if (_stmt.isNull(_columnIndexOfDocumento)) {
            _tmpDocumento = null
          } else {
            _tmpDocumento = _stmt.getText(_columnIndexOfDocumento)
          }
          val _tmpAccion: String
          _tmpAccion = _stmt.getText(_columnIndexOfAccion)
          val _tmpMensaje: String?
          if (_stmt.isNull(_columnIndexOfMensaje)) {
            _tmpMensaje = null
          } else {
            _tmpMensaje = _stmt.getText(_columnIndexOfMensaje)
          }
          _item =
              ImportDetailEntity(_tmpIdDetalle,_tmpIdImportacion,_tmpFila,_tmpDocumento,_tmpAccion,_tmpMensaje)
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

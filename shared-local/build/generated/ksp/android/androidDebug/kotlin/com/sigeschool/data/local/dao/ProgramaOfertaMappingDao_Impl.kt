package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ProgramaOfertaMappingEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class ProgramaOfertaMappingDao_Impl(
  __db: RoomDatabase,
) : ProgramaOfertaMappingDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfProgramaOfertaMappingEntity:
      EntityInsertAdapter<ProgramaOfertaMappingEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfProgramaOfertaMappingEntity = object :
        EntityInsertAdapter<ProgramaOfertaMappingEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `programa_oferta_mapping` (`id`,`institutionId`,`codigoFormulario`,`ofertaAcademicaId`,`gradoId`,`activo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProgramaOfertaMappingEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.codigoFormulario)
        statement.bindLong(4, entity.ofertaAcademicaId)
        statement.bindLong(5, entity.gradoId)
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(mapping: ProgramaOfertaMappingEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfProgramaOfertaMappingEntity.insertAndReturnId(_connection,
        mapping)
    _result
  }

  public override suspend fun getByCodigo(codigo: String, instId: String):
      ProgramaOfertaMappingEntity? {
    val _sql: String =
        "SELECT * FROM programa_oferta_mapping WHERE codigoFormulario = ? AND institutionId = ? AND activo = 1 LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, codigo)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCodigoFormulario: Int = getColumnIndexOrThrow(_stmt, "codigoFormulario")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ProgramaOfertaMappingEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCodigoFormulario: String
          _tmpCodigoFormulario = _stmt.getText(_columnIndexOfCodigoFormulario)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ProgramaOfertaMappingEntity(_tmpId,_tmpInstitutionId,_tmpCodigoFormulario,_tmpOfertaAcademicaId,_tmpGradoId,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllActive(instId: String): Flow<List<ProgramaOfertaMappingEntity>> {
    val _sql: String =
        "SELECT * FROM programa_oferta_mapping WHERE institutionId = ? AND activo = 1"
    return createFlow(__db, false, arrayOf("programa_oferta_mapping")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCodigoFormulario: Int = getColumnIndexOrThrow(_stmt, "codigoFormulario")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ProgramaOfertaMappingEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProgramaOfertaMappingEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCodigoFormulario: String
          _tmpCodigoFormulario = _stmt.getText(_columnIndexOfCodigoFormulario)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ProgramaOfertaMappingEntity(_tmpId,_tmpInstitutionId,_tmpCodigoFormulario,_tmpOfertaAcademicaId,_tmpGradoId,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM programa_oferta_mapping WHERE id = ? AND institutionId = ?"
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

package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ListadoConfigEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ListadoConfigDao_Impl(
  __db: RoomDatabase,
) : ListadoConfigDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfListadoConfigEntity: EntityInsertAdapter<ListadoConfigEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfListadoConfigEntity = object : EntityInsertAdapter<ListadoConfigEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `listado_config` (`institutionId`,`tamanoPapel`,`incluirLogo`,`incluirFirmas`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ListadoConfigEntity) {
        statement.bindText(1, entity.institutionId)
        statement.bindText(2, entity.tamanoPapel)
        val _tmp: Int = if (entity.incluirLogo) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        val _tmp_1: Int = if (entity.incluirFirmas) 1 else 0
        statement.bindLong(4, _tmp_1.toLong())
        statement.bindLong(5, entity.syncStatus.toLong())
        statement.bindLong(6, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(config: ListadoConfigEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfListadoConfigEntity.insert(_connection, config)
  }

  public override suspend fun getByInstitution(instId: String): ListadoConfigEntity? {
    val _sql: String = "SELECT * FROM listado_config WHERE institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTamanoPapel: Int = getColumnIndexOrThrow(_stmt, "tamanoPapel")
        val _columnIndexOfIncluirLogo: Int = getColumnIndexOrThrow(_stmt, "incluirLogo")
        val _columnIndexOfIncluirFirmas: Int = getColumnIndexOrThrow(_stmt, "incluirFirmas")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ListadoConfigEntity?
        if (_stmt.step()) {
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTamanoPapel: String
          _tmpTamanoPapel = _stmt.getText(_columnIndexOfTamanoPapel)
          val _tmpIncluirLogo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIncluirLogo).toInt()
          _tmpIncluirLogo = _tmp != 0
          val _tmpIncluirFirmas: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIncluirFirmas).toInt()
          _tmpIncluirFirmas = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ListadoConfigEntity(_tmpInstitutionId,_tmpTamanoPapel,_tmpIncluirLogo,_tmpIncluirFirmas,_tmpSyncStatus,_tmpLastModified)
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

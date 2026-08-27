package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ConfiguracionAlertasEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class ConfiguracionAlertasDao_Impl(
  __db: RoomDatabase,
) : ConfiguracionAlertasDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfConfiguracionAlertasEntity:
      EntityInsertAdapter<ConfiguracionAlertasEntity>

  private val __updateAdapterOfConfiguracionAlertasEntity:
      EntityDeleteOrUpdateAdapter<ConfiguracionAlertasEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfConfiguracionAlertasEntity = object :
        EntityInsertAdapter<ConfiguracionAlertasEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `configuracion_alertas` (`institutionId`,`umbralInasistenciaConsecutiva`,`umbralAsistenciaSemanal`,`umbralServiciosExcesivos`,`umbralTardanzaMensual`,`activo`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConfiguracionAlertasEntity) {
        statement.bindText(1, entity.institutionId)
        statement.bindLong(2, entity.umbralInasistenciaConsecutiva.toLong())
        statement.bindLong(3, entity.umbralAsistenciaSemanal.toLong())
        statement.bindLong(4, entity.umbralServiciosExcesivos.toLong())
        statement.bindLong(5, entity.umbralTardanzaMensual.toLong())
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
      }
    }
    this.__updateAdapterOfConfiguracionAlertasEntity = object :
        EntityDeleteOrUpdateAdapter<ConfiguracionAlertasEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `configuracion_alertas` SET `institutionId` = ?,`umbralInasistenciaConsecutiva` = ?,`umbralAsistenciaSemanal` = ?,`umbralServiciosExcesivos` = ?,`umbralTardanzaMensual` = ?,`activo` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `institutionId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ConfiguracionAlertasEntity) {
        statement.bindText(1, entity.institutionId)
        statement.bindLong(2, entity.umbralInasistenciaConsecutiva.toLong())
        statement.bindLong(3, entity.umbralAsistenciaSemanal.toLong())
        statement.bindLong(4, entity.umbralServiciosExcesivos.toLong())
        statement.bindLong(5, entity.umbralTardanzaMensual.toLong())
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
        statement.bindText(9, entity.institutionId)
      }
    }
  }

  public override suspend fun insert(config: ConfiguracionAlertasEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfConfiguracionAlertasEntity.insert(_connection, config)
  }

  public override suspend fun update(config: ConfiguracionAlertasEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfConfiguracionAlertasEntity.handle(_connection, config)
  }

  public override suspend fun getByInstitution(instId: String): ConfiguracionAlertasEntity? {
    val _sql: String = "SELECT * FROM configuracion_alertas WHERE institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUmbralInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "umbralInasistenciaConsecutiva")
        val _columnIndexOfUmbralAsistenciaSemanal: Int = getColumnIndexOrThrow(_stmt,
            "umbralAsistenciaSemanal")
        val _columnIndexOfUmbralServiciosExcesivos: Int = getColumnIndexOrThrow(_stmt,
            "umbralServiciosExcesivos")
        val _columnIndexOfUmbralTardanzaMensual: Int = getColumnIndexOrThrow(_stmt,
            "umbralTardanzaMensual")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ConfiguracionAlertasEntity?
        if (_stmt.step()) {
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUmbralInasistenciaConsecutiva: Int
          _tmpUmbralInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfUmbralInasistenciaConsecutiva).toInt()
          val _tmpUmbralAsistenciaSemanal: Int
          _tmpUmbralAsistenciaSemanal = _stmt.getLong(_columnIndexOfUmbralAsistenciaSemanal).toInt()
          val _tmpUmbralServiciosExcesivos: Int
          _tmpUmbralServiciosExcesivos =
              _stmt.getLong(_columnIndexOfUmbralServiciosExcesivos).toInt()
          val _tmpUmbralTardanzaMensual: Int
          _tmpUmbralTardanzaMensual = _stmt.getLong(_columnIndexOfUmbralTardanzaMensual).toInt()
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ConfiguracionAlertasEntity(_tmpInstitutionId,_tmpUmbralInasistenciaConsecutiva,_tmpUmbralAsistenciaSemanal,_tmpUmbralServiciosExcesivos,_tmpUmbralTardanzaMensual,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByInstitutionFlow(instId: String): Flow<ConfiguracionAlertasEntity?> {
    val _sql: String = "SELECT * FROM configuracion_alertas WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("configuracion_alertas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUmbralInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "umbralInasistenciaConsecutiva")
        val _columnIndexOfUmbralAsistenciaSemanal: Int = getColumnIndexOrThrow(_stmt,
            "umbralAsistenciaSemanal")
        val _columnIndexOfUmbralServiciosExcesivos: Int = getColumnIndexOrThrow(_stmt,
            "umbralServiciosExcesivos")
        val _columnIndexOfUmbralTardanzaMensual: Int = getColumnIndexOrThrow(_stmt,
            "umbralTardanzaMensual")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ConfiguracionAlertasEntity?
        if (_stmt.step()) {
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUmbralInasistenciaConsecutiva: Int
          _tmpUmbralInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfUmbralInasistenciaConsecutiva).toInt()
          val _tmpUmbralAsistenciaSemanal: Int
          _tmpUmbralAsistenciaSemanal = _stmt.getLong(_columnIndexOfUmbralAsistenciaSemanal).toInt()
          val _tmpUmbralServiciosExcesivos: Int
          _tmpUmbralServiciosExcesivos =
              _stmt.getLong(_columnIndexOfUmbralServiciosExcesivos).toInt()
          val _tmpUmbralTardanzaMensual: Int
          _tmpUmbralTardanzaMensual = _stmt.getLong(_columnIndexOfUmbralTardanzaMensual).toInt()
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ConfiguracionAlertasEntity(_tmpInstitutionId,_tmpUmbralInasistenciaConsecutiva,_tmpUmbralAsistenciaSemanal,_tmpUmbralServiciosExcesivos,_tmpUmbralTardanzaMensual,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<ConfiguracionAlertasEntity> {
    val _sql: String =
        "SELECT * FROM configuracion_alertas WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUmbralInasistenciaConsecutiva: Int = getColumnIndexOrThrow(_stmt,
            "umbralInasistenciaConsecutiva")
        val _columnIndexOfUmbralAsistenciaSemanal: Int = getColumnIndexOrThrow(_stmt,
            "umbralAsistenciaSemanal")
        val _columnIndexOfUmbralServiciosExcesivos: Int = getColumnIndexOrThrow(_stmt,
            "umbralServiciosExcesivos")
        val _columnIndexOfUmbralTardanzaMensual: Int = getColumnIndexOrThrow(_stmt,
            "umbralTardanzaMensual")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ConfiguracionAlertasEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConfiguracionAlertasEntity
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUmbralInasistenciaConsecutiva: Int
          _tmpUmbralInasistenciaConsecutiva =
              _stmt.getLong(_columnIndexOfUmbralInasistenciaConsecutiva).toInt()
          val _tmpUmbralAsistenciaSemanal: Int
          _tmpUmbralAsistenciaSemanal = _stmt.getLong(_columnIndexOfUmbralAsistenciaSemanal).toInt()
          val _tmpUmbralServiciosExcesivos: Int
          _tmpUmbralServiciosExcesivos =
              _stmt.getLong(_columnIndexOfUmbralServiciosExcesivos).toInt()
          val _tmpUmbralTardanzaMensual: Int
          _tmpUmbralTardanzaMensual = _stmt.getLong(_columnIndexOfUmbralTardanzaMensual).toInt()
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ConfiguracionAlertasEntity(_tmpInstitutionId,_tmpUmbralInasistenciaConsecutiva,_tmpUmbralAsistenciaSemanal,_tmpUmbralServiciosExcesivos,_tmpUmbralTardanzaMensual,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(instId: String, timestamp: Long) {
    val _sql: String =
        "UPDATE configuracion_alertas SET syncStatus = 0, lastModified = ? WHERE institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
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

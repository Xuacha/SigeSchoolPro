package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ConfiguracionAlertaEntity
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ConfiguracionAlertaDao_Impl(
  __db: RoomDatabase,
) : ConfiguracionAlertaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfConfiguracionAlertaEntity:
      EntityInsertAdapter<ConfiguracionAlertaEntity>

  private val __updateAdapterOfConfiguracionAlertaEntity:
      EntityDeleteOrUpdateAdapter<ConfiguracionAlertaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfConfiguracionAlertaEntity = object :
        EntityInsertAdapter<ConfiguracionAlertaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `configuracion_alerta` (`id`,`institutionId`,`inasistenciasConsecutivasParaAlerta`,`diasSemanaUmbral`,`semanasConsecutivasPatron`,`nivelAlertaAcudiente`,`nivelAlertaDirector`,`nivelAlertaJefeArea`,`nivelAlertaCoordinador`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConfiguracionAlertaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.inasistenciasConsecutivasParaAlerta.toLong())
        statement.bindLong(4, entity.diasSemanaUmbral.toLong())
        statement.bindLong(5, entity.semanasConsecutivasPatron.toLong())
        val _tmp: Int = if (entity.nivelAlertaAcudiente) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        val _tmp_1: Int = if (entity.nivelAlertaDirector) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.nivelAlertaJefeArea) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.nivelAlertaCoordinador) 1 else 0
        statement.bindLong(9, _tmp_3.toLong())
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
      }
    }
    this.__updateAdapterOfConfiguracionAlertaEntity = object :
        EntityDeleteOrUpdateAdapter<ConfiguracionAlertaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `configuracion_alerta` SET `id` = ?,`institutionId` = ?,`inasistenciasConsecutivasParaAlerta` = ?,`diasSemanaUmbral` = ?,`semanasConsecutivasPatron` = ?,`nivelAlertaAcudiente` = ?,`nivelAlertaDirector` = ?,`nivelAlertaJefeArea` = ?,`nivelAlertaCoordinador` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ConfiguracionAlertaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.inasistenciasConsecutivasParaAlerta.toLong())
        statement.bindLong(4, entity.diasSemanaUmbral.toLong())
        statement.bindLong(5, entity.semanasConsecutivasPatron.toLong())
        val _tmp: Int = if (entity.nivelAlertaAcudiente) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        val _tmp_1: Int = if (entity.nivelAlertaDirector) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.nivelAlertaJefeArea) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.nivelAlertaCoordinador) 1 else 0
        statement.bindLong(9, _tmp_3.toLong())
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
        statement.bindLong(12, entity.id)
      }
    }
  }

  public override suspend fun insert(config: ConfiguracionAlertaEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfConfiguracionAlertaEntity.insertAndReturnId(_connection,
        config)
    _result
  }

  public override suspend fun update(config: ConfiguracionAlertaEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfConfiguracionAlertaEntity.handle(_connection, config)
  }

  public override suspend fun getByInstitution(instId: String): ConfiguracionAlertaEntity? {
    val _sql: String = "SELECT * FROM configuracion_alerta WHERE institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfInasistenciasConsecutivasParaAlerta: Int = getColumnIndexOrThrow(_stmt,
            "inasistenciasConsecutivasParaAlerta")
        val _columnIndexOfDiasSemanaUmbral: Int = getColumnIndexOrThrow(_stmt, "diasSemanaUmbral")
        val _columnIndexOfSemanasConsecutivasPatron: Int = getColumnIndexOrThrow(_stmt,
            "semanasConsecutivasPatron")
        val _columnIndexOfNivelAlertaAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaAcudiente")
        val _columnIndexOfNivelAlertaDirector: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaDirector")
        val _columnIndexOfNivelAlertaJefeArea: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaJefeArea")
        val _columnIndexOfNivelAlertaCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaCoordinador")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ConfiguracionAlertaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpInasistenciasConsecutivasParaAlerta: Int
          _tmpInasistenciasConsecutivasParaAlerta =
              _stmt.getLong(_columnIndexOfInasistenciasConsecutivasParaAlerta).toInt()
          val _tmpDiasSemanaUmbral: Int
          _tmpDiasSemanaUmbral = _stmt.getLong(_columnIndexOfDiasSemanaUmbral).toInt()
          val _tmpSemanasConsecutivasPatron: Int
          _tmpSemanasConsecutivasPatron =
              _stmt.getLong(_columnIndexOfSemanasConsecutivasPatron).toInt()
          val _tmpNivelAlertaAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNivelAlertaAcudiente).toInt()
          _tmpNivelAlertaAcudiente = _tmp != 0
          val _tmpNivelAlertaDirector: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfNivelAlertaDirector).toInt()
          _tmpNivelAlertaDirector = _tmp_1 != 0
          val _tmpNivelAlertaJefeArea: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfNivelAlertaJefeArea).toInt()
          _tmpNivelAlertaJefeArea = _tmp_2 != 0
          val _tmpNivelAlertaCoordinador: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfNivelAlertaCoordinador).toInt()
          _tmpNivelAlertaCoordinador = _tmp_3 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ConfiguracionAlertaEntity(_tmpId,_tmpInstitutionId,_tmpInasistenciasConsecutivasParaAlerta,_tmpDiasSemanaUmbral,_tmpSemanasConsecutivasPatron,_tmpNivelAlertaAcudiente,_tmpNivelAlertaDirector,_tmpNivelAlertaJefeArea,_tmpNivelAlertaCoordinador,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<ConfiguracionAlertaEntity> {
    val _sql: String =
        "SELECT * FROM configuracion_alerta WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfInasistenciasConsecutivasParaAlerta: Int = getColumnIndexOrThrow(_stmt,
            "inasistenciasConsecutivasParaAlerta")
        val _columnIndexOfDiasSemanaUmbral: Int = getColumnIndexOrThrow(_stmt, "diasSemanaUmbral")
        val _columnIndexOfSemanasConsecutivasPatron: Int = getColumnIndexOrThrow(_stmt,
            "semanasConsecutivasPatron")
        val _columnIndexOfNivelAlertaAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaAcudiente")
        val _columnIndexOfNivelAlertaDirector: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaDirector")
        val _columnIndexOfNivelAlertaJefeArea: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaJefeArea")
        val _columnIndexOfNivelAlertaCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "nivelAlertaCoordinador")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ConfiguracionAlertaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConfiguracionAlertaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpInasistenciasConsecutivasParaAlerta: Int
          _tmpInasistenciasConsecutivasParaAlerta =
              _stmt.getLong(_columnIndexOfInasistenciasConsecutivasParaAlerta).toInt()
          val _tmpDiasSemanaUmbral: Int
          _tmpDiasSemanaUmbral = _stmt.getLong(_columnIndexOfDiasSemanaUmbral).toInt()
          val _tmpSemanasConsecutivasPatron: Int
          _tmpSemanasConsecutivasPatron =
              _stmt.getLong(_columnIndexOfSemanasConsecutivasPatron).toInt()
          val _tmpNivelAlertaAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNivelAlertaAcudiente).toInt()
          _tmpNivelAlertaAcudiente = _tmp != 0
          val _tmpNivelAlertaDirector: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfNivelAlertaDirector).toInt()
          _tmpNivelAlertaDirector = _tmp_1 != 0
          val _tmpNivelAlertaJefeArea: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfNivelAlertaJefeArea).toInt()
          _tmpNivelAlertaJefeArea = _tmp_2 != 0
          val _tmpNivelAlertaCoordinador: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfNivelAlertaCoordinador).toInt()
          _tmpNivelAlertaCoordinador = _tmp_3 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ConfiguracionAlertaEntity(_tmpId,_tmpInstitutionId,_tmpInasistenciasConsecutivasParaAlerta,_tmpDiasSemanaUmbral,_tmpSemanasConsecutivasPatron,_tmpNivelAlertaAcudiente,_tmpNivelAlertaDirector,_tmpNivelAlertaJefeArea,_tmpNivelAlertaCoordinador,_tmpSyncStatus,_tmpLastModified)
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
        "UPDATE configuracion_alerta SET syncStatus = 0, lastModified = ? WHERE id = ?"
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

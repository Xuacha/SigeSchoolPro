package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PeriodoConfiguracionEntity
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
public class PeriodoConfiguracionDao_Impl(
  __db: RoomDatabase,
) : PeriodoConfiguracionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPeriodoConfiguracionEntity:
      EntityInsertAdapter<PeriodoConfiguracionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPeriodoConfiguracionEntity = object :
        EntityInsertAdapter<PeriodoConfiguracionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `periodo_configuracion` (`id`,`institutionId`,`periodoAcademicoId`,`tipoConcepto`,`conceptoId`,`aplicarCada`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PeriodoConfiguracionEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.periodoAcademicoId)
        statement.bindText(4, entity.tipoConcepto)
        statement.bindLong(5, entity.conceptoId)
        statement.bindLong(6, entity.aplicarCada.toLong())
        statement.bindLong(7, entity.syncStatus.toLong())
        statement.bindLong(8, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(config: PeriodoConfiguracionEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPeriodoConfiguracionEntity.insertAndReturnId(_connection,
        config)
    _result
  }

  public override fun getByPeriodo(periodoId: Long, instId: String):
      Flow<List<PeriodoConfiguracionEntity>> {
    val _sql: String =
        "SELECT * FROM periodo_configuracion WHERE periodoAcademicoId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("periodo_configuracion")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, periodoId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfTipoConcepto: Int = getColumnIndexOrThrow(_stmt, "tipoConcepto")
        val _columnIndexOfConceptoId: Int = getColumnIndexOrThrow(_stmt, "conceptoId")
        val _columnIndexOfAplicarCada: Int = getColumnIndexOrThrow(_stmt, "aplicarCada")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PeriodoConfiguracionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PeriodoConfiguracionEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpTipoConcepto: String
          _tmpTipoConcepto = _stmt.getText(_columnIndexOfTipoConcepto)
          val _tmpConceptoId: Long
          _tmpConceptoId = _stmt.getLong(_columnIndexOfConceptoId)
          val _tmpAplicarCada: Int
          _tmpAplicarCada = _stmt.getLong(_columnIndexOfAplicarCada).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PeriodoConfiguracionEntity(_tmpId,_tmpInstitutionId,_tmpPeriodoAcademicoId,_tmpTipoConcepto,_tmpConceptoId,_tmpAplicarCada,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<PeriodoConfiguracionEntity> {
    val _sql: String =
        "SELECT * FROM periodo_configuracion WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfTipoConcepto: Int = getColumnIndexOrThrow(_stmt, "tipoConcepto")
        val _columnIndexOfConceptoId: Int = getColumnIndexOrThrow(_stmt, "conceptoId")
        val _columnIndexOfAplicarCada: Int = getColumnIndexOrThrow(_stmt, "aplicarCada")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PeriodoConfiguracionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PeriodoConfiguracionEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpTipoConcepto: String
          _tmpTipoConcepto = _stmt.getText(_columnIndexOfTipoConcepto)
          val _tmpConceptoId: Long
          _tmpConceptoId = _stmt.getLong(_columnIndexOfConceptoId)
          val _tmpAplicarCada: Int
          _tmpAplicarCada = _stmt.getLong(_columnIndexOfAplicarCada).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PeriodoConfiguracionEntity(_tmpId,_tmpInstitutionId,_tmpPeriodoAcademicoId,_tmpTipoConcepto,_tmpConceptoId,_tmpAplicarCada,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByPeriodo(periodoId: Long, instId: String) {
    val _sql: String =
        "DELETE FROM periodo_configuracion WHERE periodoAcademicoId = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, periodoId)
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

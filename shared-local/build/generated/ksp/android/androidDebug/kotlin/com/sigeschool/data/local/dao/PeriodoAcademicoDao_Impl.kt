package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PeriodoAcademicoEntity
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
public class PeriodoAcademicoDao_Impl(
  __db: RoomDatabase,
) : PeriodoAcademicoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPeriodoAcademicoEntity: EntityInsertAdapter<PeriodoAcademicoEntity>

  private val __updateAdapterOfPeriodoAcademicoEntity:
      EntityDeleteOrUpdateAdapter<PeriodoAcademicoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPeriodoAcademicoEntity = object :
        EntityInsertAdapter<PeriodoAcademicoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_periodos` (`id`,`institutionId`,`nombre`,`tipo`,`fechaInicio`,`fechaFin`,`duracionMeses`,`numeroCortes`,`esActivo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PeriodoAcademicoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        statement.bindText(4, entity.tipo)
        statement.bindLong(5, entity.fechaInicio)
        statement.bindLong(6, entity.fechaFin)
        statement.bindLong(7, entity.duracionMeses.toLong())
        statement.bindLong(8, entity.numeroCortes.toLong())
        val _tmp: Int = if (entity.esActivo) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
      }
    }
    this.__updateAdapterOfPeriodoAcademicoEntity = object :
        EntityDeleteOrUpdateAdapter<PeriodoAcademicoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_periodos` SET `id` = ?,`institutionId` = ?,`nombre` = ?,`tipo` = ?,`fechaInicio` = ?,`fechaFin` = ?,`duracionMeses` = ?,`numeroCortes` = ?,`esActivo` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PeriodoAcademicoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        statement.bindText(4, entity.tipo)
        statement.bindLong(5, entity.fechaInicio)
        statement.bindLong(6, entity.fechaFin)
        statement.bindLong(7, entity.duracionMeses.toLong())
        statement.bindLong(8, entity.numeroCortes.toLong())
        val _tmp: Int = if (entity.esActivo) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        statement.bindLong(10, entity.syncStatus.toLong())
        statement.bindLong(11, entity.lastModified)
        statement.bindLong(12, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: PeriodoAcademicoEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfPeriodoAcademicoEntity.insertAndReturnId(_connection,
        entity)
    _result
  }

  public override suspend fun update(entity: PeriodoAcademicoEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfPeriodoAcademicoEntity.handle(_connection, entity)
  }

  public override fun getAll(instId: String): Flow<List<PeriodoAcademicoEntity>> {
    val _sql: String =
        "SELECT * FROM academic_periodos WHERE institutionId = ? ORDER BY fechaInicio DESC"
    return createFlow(__db, false, arrayOf("academic_periodos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfDuracionMeses: Int = getColumnIndexOrThrow(_stmt, "duracionMeses")
        val _columnIndexOfNumeroCortes: Int = getColumnIndexOrThrow(_stmt, "numeroCortes")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PeriodoAcademicoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PeriodoAcademicoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpDuracionMeses: Int
          _tmpDuracionMeses = _stmt.getLong(_columnIndexOfDuracionMeses).toInt()
          val _tmpNumeroCortes: Int
          _tmpNumeroCortes = _stmt.getLong(_columnIndexOfNumeroCortes).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PeriodoAcademicoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpTipo,_tmpFechaInicio,_tmpFechaFin,_tmpDuracionMeses,_tmpNumeroCortes,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getActive(instId: String): Flow<List<PeriodoAcademicoEntity>> {
    val _sql: String = "SELECT * FROM academic_periodos WHERE institutionId = ? AND esActivo = 1"
    return createFlow(__db, false, arrayOf("academic_periodos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfDuracionMeses: Int = getColumnIndexOrThrow(_stmt, "duracionMeses")
        val _columnIndexOfNumeroCortes: Int = getColumnIndexOrThrow(_stmt, "numeroCortes")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PeriodoAcademicoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PeriodoAcademicoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpDuracionMeses: Int
          _tmpDuracionMeses = _stmt.getLong(_columnIndexOfDuracionMeses).toInt()
          val _tmpNumeroCortes: Int
          _tmpNumeroCortes = _stmt.getLong(_columnIndexOfNumeroCortes).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PeriodoAcademicoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpTipo,_tmpFechaInicio,_tmpFechaFin,_tmpDuracionMeses,_tmpNumeroCortes,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getActiveSync(instId: String): PeriodoAcademicoEntity? {
    val _sql: String =
        "SELECT * FROM academic_periodos WHERE institutionId = ? AND esActivo = 1 LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfDuracionMeses: Int = getColumnIndexOrThrow(_stmt, "duracionMeses")
        val _columnIndexOfNumeroCortes: Int = getColumnIndexOrThrow(_stmt, "numeroCortes")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: PeriodoAcademicoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpDuracionMeses: Int
          _tmpDuracionMeses = _stmt.getLong(_columnIndexOfDuracionMeses).toInt()
          val _tmpNumeroCortes: Int
          _tmpNumeroCortes = _stmt.getLong(_columnIndexOfNumeroCortes).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              PeriodoAcademicoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpTipo,_tmpFechaInicio,_tmpFechaFin,_tmpDuracionMeses,_tmpNumeroCortes,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): PeriodoAcademicoEntity? {
    val _sql: String = "SELECT * FROM academic_periodos WHERE id = ? AND institutionId = ?"
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
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfDuracionMeses: Int = getColumnIndexOrThrow(_stmt, "duracionMeses")
        val _columnIndexOfNumeroCortes: Int = getColumnIndexOrThrow(_stmt, "numeroCortes")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: PeriodoAcademicoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpDuracionMeses: Int
          _tmpDuracionMeses = _stmt.getLong(_columnIndexOfDuracionMeses).toInt()
          val _tmpNumeroCortes: Int
          _tmpNumeroCortes = _stmt.getLong(_columnIndexOfNumeroCortes).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              PeriodoAcademicoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpTipo,_tmpFechaInicio,_tmpFechaFin,_tmpDuracionMeses,_tmpNumeroCortes,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByName(nombre: String, instId: String): PeriodoAcademicoEntity? {
    val _sql: String =
        "SELECT * FROM academic_periodos WHERE nombre = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, nombre)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfDuracionMeses: Int = getColumnIndexOrThrow(_stmt, "duracionMeses")
        val _columnIndexOfNumeroCortes: Int = getColumnIndexOrThrow(_stmt, "numeroCortes")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: PeriodoAcademicoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpDuracionMeses: Int
          _tmpDuracionMeses = _stmt.getLong(_columnIndexOfDuracionMeses).toInt()
          val _tmpNumeroCortes: Int
          _tmpNumeroCortes = _stmt.getLong(_columnIndexOfNumeroCortes).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              PeriodoAcademicoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpTipo,_tmpFechaInicio,_tmpFechaFin,_tmpDuracionMeses,_tmpNumeroCortes,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<PeriodoAcademicoEntity> {
    val _sql: String = "SELECT * FROM academic_periodos WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfDuracionMeses: Int = getColumnIndexOrThrow(_stmt, "duracionMeses")
        val _columnIndexOfNumeroCortes: Int = getColumnIndexOrThrow(_stmt, "numeroCortes")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PeriodoAcademicoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PeriodoAcademicoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpDuracionMeses: Int
          _tmpDuracionMeses = _stmt.getLong(_columnIndexOfDuracionMeses).toInt()
          val _tmpNumeroCortes: Int
          _tmpNumeroCortes = _stmt.getLong(_columnIndexOfNumeroCortes).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PeriodoAcademicoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpTipo,_tmpFechaInicio,_tmpFechaFin,_tmpDuracionMeses,_tmpNumeroCortes,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<PeriodoAcademicoEntity> {
    val _sql: String = "SELECT * FROM academic_periodos WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfDuracionMeses: Int = getColumnIndexOrThrow(_stmt, "duracionMeses")
        val _columnIndexOfNumeroCortes: Int = getColumnIndexOrThrow(_stmt, "numeroCortes")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PeriodoAcademicoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PeriodoAcademicoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpDuracionMeses: Int
          _tmpDuracionMeses = _stmt.getLong(_columnIndexOfDuracionMeses).toInt()
          val _tmpNumeroCortes: Int
          _tmpNumeroCortes = _stmt.getLong(_columnIndexOfNumeroCortes).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PeriodoAcademicoEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpTipo,_tmpFechaInicio,_tmpFechaFin,_tmpDuracionMeses,_tmpNumeroCortes,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_periodos WHERE id = ? AND institutionId = ?"
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

  public override suspend fun markAsSynced(id: Long, instId: String) {
    val _sql: String =
        "UPDATE academic_periodos SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.OfertaAcademicaEntity
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
public class OfertaAcademicaDao_Impl(
  __db: RoomDatabase,
) : OfertaAcademicaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfOfertaAcademicaEntity: EntityInsertAdapter<OfertaAcademicaEntity>

  private val __updateAdapterOfOfertaAcademicaEntity:
      EntityDeleteOrUpdateAdapter<OfertaAcademicaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfOfertaAcademicaEntity = object :
        EntityInsertAdapter<OfertaAcademicaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_ofertas` (`id`,`institutionId`,`gradoId`,`periodoAcademicoId`,`nombre`,`fechaInicio`,`fechaFin`,`estado`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: OfertaAcademicaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.gradoId)
        statement.bindLong(4, entity.periodoAcademicoId)
        statement.bindText(5, entity.nombre)
        statement.bindLong(6, entity.fechaInicio)
        statement.bindLong(7, entity.fechaFin)
        statement.bindText(8, entity.estado)
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfOfertaAcademicaEntity = object :
        EntityDeleteOrUpdateAdapter<OfertaAcademicaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_ofertas` SET `id` = ?,`institutionId` = ?,`gradoId` = ?,`periodoAcademicoId` = ?,`nombre` = ?,`fechaInicio` = ?,`fechaFin` = ?,`estado` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: OfertaAcademicaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.gradoId)
        statement.bindLong(4, entity.periodoAcademicoId)
        statement.bindText(5, entity.nombre)
        statement.bindLong(6, entity.fechaInicio)
        statement.bindLong(7, entity.fechaFin)
        statement.bindText(8, entity.estado)
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindLong(11, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: OfertaAcademicaEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfOfertaAcademicaEntity.insertAndReturnId(_connection,
        entity)
    _result
  }

  public override suspend fun update(entity: OfertaAcademicaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfOfertaAcademicaEntity.handle(_connection, entity)
  }

  public override fun getAll(instId: String): Flow<List<OfertaAcademicaEntity>> {
    val _sql: String =
        "SELECT * FROM academic_ofertas WHERE institutionId = ? ORDER BY fechaInicio DESC"
    return createFlow(__db, false, arrayOf("academic_ofertas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpPeriodoAcademicoId,_tmpNombre,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByGradoAndPeriodo(
    instId: String,
    gradoId: Long,
    periodoId: Long,
  ): OfertaAcademicaEntity? {
    val _sql: String =
        "SELECT * FROM academic_ofertas WHERE institutionId = ? AND gradoId = ? AND periodoAcademicoId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, gradoId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, periodoId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: OfertaAcademicaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              OfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpPeriodoAcademicoId,_tmpNombre,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllByGrado(instId: String, gradoId: Long):
      List<OfertaAcademicaEntity> {
    val _sql: String = "SELECT * FROM academic_ofertas WHERE institutionId = ? AND gradoId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, gradoId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpPeriodoAcademicoId,_tmpNombre,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPublicadas(instId: String): Flow<List<OfertaAcademicaEntity>> {
    val _sql: String =
        "SELECT * FROM academic_ofertas WHERE institutionId = ? AND estado = 'PUBLICADA'"
    return createFlow(__db, false, arrayOf("academic_ofertas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpPeriodoAcademicoId,_tmpNombre,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): OfertaAcademicaEntity? {
    val _sql: String = "SELECT * FROM academic_ofertas WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: OfertaAcademicaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              OfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpPeriodoAcademicoId,_tmpNombre,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<OfertaAcademicaEntity> {
    val _sql: String = "SELECT * FROM academic_ofertas WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpPeriodoAcademicoId,_tmpNombre,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<OfertaAcademicaEntity> {
    val _sql: String = "SELECT * FROM academic_ofertas WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfPeriodoAcademicoId: Int = getColumnIndexOrThrow(_stmt,
            "periodoAcademicoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfFechaInicio: Int = getColumnIndexOrThrow(_stmt, "fechaInicio")
        val _columnIndexOfFechaFin: Int = getColumnIndexOrThrow(_stmt, "fechaFin")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<OfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpPeriodoAcademicoId: Long
          _tmpPeriodoAcademicoId = _stmt.getLong(_columnIndexOfPeriodoAcademicoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpFechaInicio: Long
          _tmpFechaInicio = _stmt.getLong(_columnIndexOfFechaInicio)
          val _tmpFechaFin: Long
          _tmpFechaFin = _stmt.getLong(_columnIndexOfFechaFin)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              OfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpPeriodoAcademicoId,_tmpNombre,_tmpFechaInicio,_tmpFechaFin,_tmpEstado,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_ofertas WHERE id = ? AND institutionId = ?"
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
        "UPDATE academic_ofertas SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

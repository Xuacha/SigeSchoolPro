package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.HorarioEntity
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
public class HorarioDao_Impl(
  __db: RoomDatabase,
) : HorarioDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfHorarioEntity: EntityInsertAdapter<HorarioEntity>

  private val __updateAdapterOfHorarioEntity: EntityDeleteOrUpdateAdapter<HorarioEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfHorarioEntity = object : EntityInsertAdapter<HorarioEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_horarios` (`id`,`institutionId`,`claseId`,`diaSemana`,`horaInicio`,`horaFin`,`aulaId`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: HorarioEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.claseId)
        statement.bindLong(4, entity.diaSemana.toLong())
        statement.bindText(5, entity.horaInicio)
        statement.bindText(6, entity.horaFin)
        val _tmpAulaId: Long? = entity.aulaId
        if (_tmpAulaId == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpAulaId)
        }
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
    this.__updateAdapterOfHorarioEntity = object : EntityDeleteOrUpdateAdapter<HorarioEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_horarios` SET `id` = ?,`institutionId` = ?,`claseId` = ?,`diaSemana` = ?,`horaInicio` = ?,`horaFin` = ?,`aulaId` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: HorarioEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.claseId)
        statement.bindLong(4, entity.diaSemana.toLong())
        statement.bindText(5, entity.horaInicio)
        statement.bindText(6, entity.horaFin)
        val _tmpAulaId: Long? = entity.aulaId
        if (_tmpAulaId == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpAulaId)
        }
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: HorarioEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfHorarioEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun update(entity: HorarioEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfHorarioEntity.handle(_connection, entity)
  }

  public override fun getByClase(instId: String, claseId: Long): Flow<List<HorarioEntity>> {
    val _sql: String = "SELECT * FROM academic_horarios WHERE institutionId = ? AND claseId = ?"
    return createFlow(__db, false, arrayOf("academic_horarios")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, claseId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDiaSemana: Int = getColumnIndexOrThrow(_stmt, "diaSemana")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfAulaId: Int = getColumnIndexOrThrow(_stmt, "aulaId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<HorarioEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: HorarioEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDiaSemana: Int
          _tmpDiaSemana = _stmt.getLong(_columnIndexOfDiaSemana).toInt()
          val _tmpHoraInicio: String
          _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          val _tmpHoraFin: String
          _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
          val _tmpAulaId: Long?
          if (_stmt.isNull(_columnIndexOfAulaId)) {
            _tmpAulaId = null
          } else {
            _tmpAulaId = _stmt.getLong(_columnIndexOfAulaId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              HorarioEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDiaSemana,_tmpHoraInicio,_tmpHoraFin,_tmpAulaId,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByDocente(instId: String, docenteId: String): Flow<List<HorarioEntity>> {
    val _sql: String = """
        |
        |        SELECT h.* FROM academic_horarios h
        |        INNER JOIN academic_clases c ON h.claseId = c.id
        |        INNER JOIN academic_detalles_oferta d ON c.detalleOfertaId = d.id
        |        WHERE h.institutionId = ? AND d.docenteId = ?
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("academic_horarios", "academic_clases",
        "academic_detalles_oferta")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, docenteId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDiaSemana: Int = getColumnIndexOrThrow(_stmt, "diaSemana")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfAulaId: Int = getColumnIndexOrThrow(_stmt, "aulaId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<HorarioEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: HorarioEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDiaSemana: Int
          _tmpDiaSemana = _stmt.getLong(_columnIndexOfDiaSemana).toInt()
          val _tmpHoraInicio: String
          _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          val _tmpHoraFin: String
          _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
          val _tmpAulaId: Long?
          if (_stmt.isNull(_columnIndexOfAulaId)) {
            _tmpAulaId = null
          } else {
            _tmpAulaId = _stmt.getLong(_columnIndexOfAulaId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              HorarioEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDiaSemana,_tmpHoraInicio,_tmpHoraFin,_tmpAulaId,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countAulaConflicts(
    instId: String,
    aulaId: Long,
    dia: Int,
    hInicio: String,
    hFin: String,
    excludeId: Long,
  ): Int {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM academic_horarios 
        |        WHERE institutionId = ? 
        |        AND aulaId = ? 
        |        AND diaSemana = ? 
        |        AND id != ?
        |        AND (
        |            (horaInicio < ? AND horaFin > ?)
        |        )
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, aulaId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, dia.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, excludeId)
        _argIndex = 5
        _stmt.bindText(_argIndex, hFin)
        _argIndex = 6
        _stmt.bindText(_argIndex, hInicio)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countDocenteConflicts(
    instId: String,
    docenteId: String,
    dia: Int,
    hInicio: String,
    hFin: String,
    excludeId: Long,
  ): Int {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM academic_horarios h
        |        INNER JOIN academic_clases c ON h.claseId = c.id
        |        INNER JOIN academic_detalles_oferta d ON c.detalleOfertaId = d.id
        |        WHERE h.institutionId = ? 
        |        AND d.docenteId = ? 
        |        AND h.diaSemana = ? 
        |        AND h.id != ?
        |        AND (
        |            (h.horaInicio < ? AND h.horaFin > ?)
        |        )
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, docenteId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, dia.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, excludeId)
        _argIndex = 5
        _stmt.bindText(_argIndex, hFin)
        _argIndex = 6
        _stmt.bindText(_argIndex, hInicio)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): HorarioEntity? {
    val _sql: String = "SELECT * FROM academic_horarios WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDiaSemana: Int = getColumnIndexOrThrow(_stmt, "diaSemana")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfAulaId: Int = getColumnIndexOrThrow(_stmt, "aulaId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: HorarioEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDiaSemana: Int
          _tmpDiaSemana = _stmt.getLong(_columnIndexOfDiaSemana).toInt()
          val _tmpHoraInicio: String
          _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          val _tmpHoraFin: String
          _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
          val _tmpAulaId: Long?
          if (_stmt.isNull(_columnIndexOfAulaId)) {
            _tmpAulaId = null
          } else {
            _tmpAulaId = _stmt.getLong(_columnIndexOfAulaId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              HorarioEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDiaSemana,_tmpHoraInicio,_tmpHoraFin,_tmpAulaId,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDocenteIdForClase(claseId: Long, instId: String): String? {
    val _sql: String = """
        |
        |        SELECT d.docenteId FROM academic_clases c
        |        INNER JOIN academic_detalles_oferta d ON c.detalleOfertaId = d.id
        |        WHERE c.id = ? AND c.institutionId = ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, claseId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _result: String?
        if (_stmt.step()) {
          if (_stmt.isNull(0)) {
            _result = null
          } else {
            _result = _stmt.getText(0)
          }
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<HorarioEntity> {
    val _sql: String = "SELECT * FROM academic_horarios WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDiaSemana: Int = getColumnIndexOrThrow(_stmt, "diaSemana")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfAulaId: Int = getColumnIndexOrThrow(_stmt, "aulaId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<HorarioEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: HorarioEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDiaSemana: Int
          _tmpDiaSemana = _stmt.getLong(_columnIndexOfDiaSemana).toInt()
          val _tmpHoraInicio: String
          _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          val _tmpHoraFin: String
          _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
          val _tmpAulaId: Long?
          if (_stmt.isNull(_columnIndexOfAulaId)) {
            _tmpAulaId = null
          } else {
            _tmpAulaId = _stmt.getLong(_columnIndexOfAulaId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              HorarioEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDiaSemana,_tmpHoraInicio,_tmpHoraFin,_tmpAulaId,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<HorarioEntity> {
    val _sql: String = "SELECT * FROM academic_horarios WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDiaSemana: Int = getColumnIndexOrThrow(_stmt, "diaSemana")
        val _columnIndexOfHoraInicio: Int = getColumnIndexOrThrow(_stmt, "horaInicio")
        val _columnIndexOfHoraFin: Int = getColumnIndexOrThrow(_stmt, "horaFin")
        val _columnIndexOfAulaId: Int = getColumnIndexOrThrow(_stmt, "aulaId")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<HorarioEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: HorarioEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDiaSemana: Int
          _tmpDiaSemana = _stmt.getLong(_columnIndexOfDiaSemana).toInt()
          val _tmpHoraInicio: String
          _tmpHoraInicio = _stmt.getText(_columnIndexOfHoraInicio)
          val _tmpHoraFin: String
          _tmpHoraFin = _stmt.getText(_columnIndexOfHoraFin)
          val _tmpAulaId: Long?
          if (_stmt.isNull(_columnIndexOfAulaId)) {
            _tmpAulaId = null
          } else {
            _tmpAulaId = _stmt.getLong(_columnIndexOfAulaId)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              HorarioEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDiaSemana,_tmpHoraInicio,_tmpHoraFin,_tmpAulaId,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_horarios WHERE id = ? AND institutionId = ?"
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
        "UPDATE academic_horarios SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

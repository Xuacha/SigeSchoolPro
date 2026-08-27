package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PlanEstudiosDetalleEntity
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
public class PlanEstudiosDetalleDao_Impl(
  __db: RoomDatabase,
) : PlanEstudiosDetalleDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlanEstudiosDetalleEntity:
      EntityInsertAdapter<PlanEstudiosDetalleEntity>

  private val __updateAdapterOfPlanEstudiosDetalleEntity:
      EntityDeleteOrUpdateAdapter<PlanEstudiosDetalleEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlanEstudiosDetalleEntity = object :
        EntityInsertAdapter<PlanEstudiosDetalleEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_planes_estudios_detalle` (`id`,`institutionId`,`planEstudiosId`,`gradoId`,`asignaturaId`,`intensidadHorariaMinima`,`esObligatoria`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlanEstudiosDetalleEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.planEstudiosId)
        statement.bindLong(4, entity.gradoId)
        statement.bindLong(5, entity.asignaturaId)
        statement.bindLong(6, entity.intensidadHorariaMinima.toLong())
        val _tmp: Int = if (entity.esObligatoria) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
    this.__updateAdapterOfPlanEstudiosDetalleEntity = object :
        EntityDeleteOrUpdateAdapter<PlanEstudiosDetalleEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_planes_estudios_detalle` SET `id` = ?,`institutionId` = ?,`planEstudiosId` = ?,`gradoId` = ?,`asignaturaId` = ?,`intensidadHorariaMinima` = ?,`esObligatoria` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PlanEstudiosDetalleEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.planEstudiosId)
        statement.bindLong(4, entity.gradoId)
        statement.bindLong(5, entity.asignaturaId)
        statement.bindLong(6, entity.intensidadHorariaMinima.toLong())
        val _tmp: Int = if (entity.esObligatoria) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: PlanEstudiosDetalleEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPlanEstudiosDetalleEntity.insertAndReturnId(_connection,
        entity)
    _result
  }

  public override suspend fun update(entity: PlanEstudiosDetalleEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPlanEstudiosDetalleEntity.handle(_connection, entity)
  }

  public override fun getByPlan(instId: String, planId: Long):
      Flow<List<PlanEstudiosDetalleEntity>> {
    val _sql: String =
        "SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = ? AND planEstudiosId = ?"
    return createFlow(__db, false, arrayOf("academic_planes_estudios_detalle")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, planId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPlanEstudiosId: Int = getColumnIndexOrThrow(_stmt, "planEstudiosId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfIntensidadHorariaMinima: Int = getColumnIndexOrThrow(_stmt,
            "intensidadHorariaMinima")
        val _columnIndexOfEsObligatoria: Int = getColumnIndexOrThrow(_stmt, "esObligatoria")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanEstudiosDetalleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanEstudiosDetalleEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPlanEstudiosId: Long
          _tmpPlanEstudiosId = _stmt.getLong(_columnIndexOfPlanEstudiosId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpIntensidadHorariaMinima: Int
          _tmpIntensidadHorariaMinima = _stmt.getLong(_columnIndexOfIntensidadHorariaMinima).toInt()
          val _tmpEsObligatoria: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsObligatoria).toInt()
          _tmpEsObligatoria = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanEstudiosDetalleEntity(_tmpId,_tmpInstitutionId,_tmpPlanEstudiosId,_tmpGradoId,_tmpAsignaturaId,_tmpIntensidadHorariaMinima,_tmpEsObligatoria,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByGrado(instId: String, gradoId: Long):
      Flow<List<PlanEstudiosDetalleEntity>> {
    val _sql: String =
        "SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = ? AND gradoId = ?"
    return createFlow(__db, false, arrayOf("academic_planes_estudios_detalle")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, gradoId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPlanEstudiosId: Int = getColumnIndexOrThrow(_stmt, "planEstudiosId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfIntensidadHorariaMinima: Int = getColumnIndexOrThrow(_stmt,
            "intensidadHorariaMinima")
        val _columnIndexOfEsObligatoria: Int = getColumnIndexOrThrow(_stmt, "esObligatoria")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanEstudiosDetalleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanEstudiosDetalleEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPlanEstudiosId: Long
          _tmpPlanEstudiosId = _stmt.getLong(_columnIndexOfPlanEstudiosId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpIntensidadHorariaMinima: Int
          _tmpIntensidadHorariaMinima = _stmt.getLong(_columnIndexOfIntensidadHorariaMinima).toInt()
          val _tmpEsObligatoria: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsObligatoria).toInt()
          _tmpEsObligatoria = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanEstudiosDetalleEntity(_tmpId,_tmpInstitutionId,_tmpPlanEstudiosId,_tmpGradoId,_tmpAsignaturaId,_tmpIntensidadHorariaMinima,_tmpEsObligatoria,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<PlanEstudiosDetalleEntity> {
    val _sql: String =
        "SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPlanEstudiosId: Int = getColumnIndexOrThrow(_stmt, "planEstudiosId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfIntensidadHorariaMinima: Int = getColumnIndexOrThrow(_stmt,
            "intensidadHorariaMinima")
        val _columnIndexOfEsObligatoria: Int = getColumnIndexOrThrow(_stmt, "esObligatoria")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanEstudiosDetalleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanEstudiosDetalleEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPlanEstudiosId: Long
          _tmpPlanEstudiosId = _stmt.getLong(_columnIndexOfPlanEstudiosId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpIntensidadHorariaMinima: Int
          _tmpIntensidadHorariaMinima = _stmt.getLong(_columnIndexOfIntensidadHorariaMinima).toInt()
          val _tmpEsObligatoria: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsObligatoria).toInt()
          _tmpEsObligatoria = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanEstudiosDetalleEntity(_tmpId,_tmpInstitutionId,_tmpPlanEstudiosId,_tmpGradoId,_tmpAsignaturaId,_tmpIntensidadHorariaMinima,_tmpEsObligatoria,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<PlanEstudiosDetalleEntity> {
    val _sql: String = "SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPlanEstudiosId: Int = getColumnIndexOrThrow(_stmt, "planEstudiosId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfIntensidadHorariaMinima: Int = getColumnIndexOrThrow(_stmt,
            "intensidadHorariaMinima")
        val _columnIndexOfEsObligatoria: Int = getColumnIndexOrThrow(_stmt, "esObligatoria")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanEstudiosDetalleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanEstudiosDetalleEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPlanEstudiosId: Long
          _tmpPlanEstudiosId = _stmt.getLong(_columnIndexOfPlanEstudiosId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpIntensidadHorariaMinima: Int
          _tmpIntensidadHorariaMinima = _stmt.getLong(_columnIndexOfIntensidadHorariaMinima).toInt()
          val _tmpEsObligatoria: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsObligatoria).toInt()
          _tmpEsObligatoria = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanEstudiosDetalleEntity(_tmpId,_tmpInstitutionId,_tmpPlanEstudiosId,_tmpGradoId,_tmpAsignaturaId,_tmpIntensidadHorariaMinima,_tmpEsObligatoria,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String =
        "DELETE FROM academic_planes_estudios_detalle WHERE id = ? AND institutionId = ?"
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

  public override suspend fun deleteByPlan(planId: Long, instId: String) {
    val _sql: String =
        "DELETE FROM academic_planes_estudios_detalle WHERE planEstudiosId = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, planId)
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
        "UPDATE academic_planes_estudios_detalle SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

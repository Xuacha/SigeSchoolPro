package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PlanAulaEntity
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
public class PlanAulaDao_Impl(
  __db: RoomDatabase,
) : PlanAulaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlanAulaEntity: EntityInsertAdapter<PlanAulaEntity>

  private val __updateAdapterOfPlanAulaEntity: EntityDeleteOrUpdateAdapter<PlanAulaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlanAulaEntity = object : EntityInsertAdapter<PlanAulaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_planes_aula` (`id`,`institutionId`,`claseId`,`docenteId`,`competencias`,`logros`,`indicadores`,`recursos`,`metodologia`,`evaluacion`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlanAulaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.claseId)
        statement.bindText(4, entity.docenteId)
        val _tmpCompetencias: String? = entity.competencias
        if (_tmpCompetencias == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpCompetencias)
        }
        val _tmpLogros: String? = entity.logros
        if (_tmpLogros == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpLogros)
        }
        val _tmpIndicadores: String? = entity.indicadores
        if (_tmpIndicadores == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpIndicadores)
        }
        val _tmpRecursos: String? = entity.recursos
        if (_tmpRecursos == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpRecursos)
        }
        val _tmpMetodologia: String? = entity.metodologia
        if (_tmpMetodologia == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpMetodologia)
        }
        val _tmpEvaluacion: String? = entity.evaluacion
        if (_tmpEvaluacion == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpEvaluacion)
        }
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
      }
    }
    this.__updateAdapterOfPlanAulaEntity = object : EntityDeleteOrUpdateAdapter<PlanAulaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_planes_aula` SET `id` = ?,`institutionId` = ?,`claseId` = ?,`docenteId` = ?,`competencias` = ?,`logros` = ?,`indicadores` = ?,`recursos` = ?,`metodologia` = ?,`evaluacion` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PlanAulaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.claseId)
        statement.bindText(4, entity.docenteId)
        val _tmpCompetencias: String? = entity.competencias
        if (_tmpCompetencias == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpCompetencias)
        }
        val _tmpLogros: String? = entity.logros
        if (_tmpLogros == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpLogros)
        }
        val _tmpIndicadores: String? = entity.indicadores
        if (_tmpIndicadores == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpIndicadores)
        }
        val _tmpRecursos: String? = entity.recursos
        if (_tmpRecursos == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpRecursos)
        }
        val _tmpMetodologia: String? = entity.metodologia
        if (_tmpMetodologia == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpMetodologia)
        }
        val _tmpEvaluacion: String? = entity.evaluacion
        if (_tmpEvaluacion == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpEvaluacion)
        }
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
        statement.bindLong(13, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: PlanAulaEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfPlanAulaEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun update(entity: PlanAulaEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfPlanAulaEntity.handle(_connection, entity)
  }

  public override suspend fun getByClase(instId: String, claseId: Long): PlanAulaEntity? {
    val _sql: String = "SELECT * FROM academic_planes_aula WHERE institutionId = ? AND claseId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, claseId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfCompetencias: Int = getColumnIndexOrThrow(_stmt, "competencias")
        val _columnIndexOfLogros: Int = getColumnIndexOrThrow(_stmt, "logros")
        val _columnIndexOfIndicadores: Int = getColumnIndexOrThrow(_stmt, "indicadores")
        val _columnIndexOfRecursos: Int = getColumnIndexOrThrow(_stmt, "recursos")
        val _columnIndexOfMetodologia: Int = getColumnIndexOrThrow(_stmt, "metodologia")
        val _columnIndexOfEvaluacion: Int = getColumnIndexOrThrow(_stmt, "evaluacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: PlanAulaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpCompetencias: String?
          if (_stmt.isNull(_columnIndexOfCompetencias)) {
            _tmpCompetencias = null
          } else {
            _tmpCompetencias = _stmt.getText(_columnIndexOfCompetencias)
          }
          val _tmpLogros: String?
          if (_stmt.isNull(_columnIndexOfLogros)) {
            _tmpLogros = null
          } else {
            _tmpLogros = _stmt.getText(_columnIndexOfLogros)
          }
          val _tmpIndicadores: String?
          if (_stmt.isNull(_columnIndexOfIndicadores)) {
            _tmpIndicadores = null
          } else {
            _tmpIndicadores = _stmt.getText(_columnIndexOfIndicadores)
          }
          val _tmpRecursos: String?
          if (_stmt.isNull(_columnIndexOfRecursos)) {
            _tmpRecursos = null
          } else {
            _tmpRecursos = _stmt.getText(_columnIndexOfRecursos)
          }
          val _tmpMetodologia: String?
          if (_stmt.isNull(_columnIndexOfMetodologia)) {
            _tmpMetodologia = null
          } else {
            _tmpMetodologia = _stmt.getText(_columnIndexOfMetodologia)
          }
          val _tmpEvaluacion: String?
          if (_stmt.isNull(_columnIndexOfEvaluacion)) {
            _tmpEvaluacion = null
          } else {
            _tmpEvaluacion = _stmt.getText(_columnIndexOfEvaluacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              PlanAulaEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDocenteId,_tmpCompetencias,_tmpLogros,_tmpIndicadores,_tmpRecursos,_tmpMetodologia,_tmpEvaluacion,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByDocente(instId: String, docenteId: String): Flow<List<PlanAulaEntity>> {
    val _sql: String =
        "SELECT * FROM academic_planes_aula WHERE institutionId = ? AND docenteId = ?"
    return createFlow(__db, false, arrayOf("academic_planes_aula")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindText(_argIndex, docenteId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfCompetencias: Int = getColumnIndexOrThrow(_stmt, "competencias")
        val _columnIndexOfLogros: Int = getColumnIndexOrThrow(_stmt, "logros")
        val _columnIndexOfIndicadores: Int = getColumnIndexOrThrow(_stmt, "indicadores")
        val _columnIndexOfRecursos: Int = getColumnIndexOrThrow(_stmt, "recursos")
        val _columnIndexOfMetodologia: Int = getColumnIndexOrThrow(_stmt, "metodologia")
        val _columnIndexOfEvaluacion: Int = getColumnIndexOrThrow(_stmt, "evaluacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanAulaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanAulaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpCompetencias: String?
          if (_stmt.isNull(_columnIndexOfCompetencias)) {
            _tmpCompetencias = null
          } else {
            _tmpCompetencias = _stmt.getText(_columnIndexOfCompetencias)
          }
          val _tmpLogros: String?
          if (_stmt.isNull(_columnIndexOfLogros)) {
            _tmpLogros = null
          } else {
            _tmpLogros = _stmt.getText(_columnIndexOfLogros)
          }
          val _tmpIndicadores: String?
          if (_stmt.isNull(_columnIndexOfIndicadores)) {
            _tmpIndicadores = null
          } else {
            _tmpIndicadores = _stmt.getText(_columnIndexOfIndicadores)
          }
          val _tmpRecursos: String?
          if (_stmt.isNull(_columnIndexOfRecursos)) {
            _tmpRecursos = null
          } else {
            _tmpRecursos = _stmt.getText(_columnIndexOfRecursos)
          }
          val _tmpMetodologia: String?
          if (_stmt.isNull(_columnIndexOfMetodologia)) {
            _tmpMetodologia = null
          } else {
            _tmpMetodologia = _stmt.getText(_columnIndexOfMetodologia)
          }
          val _tmpEvaluacion: String?
          if (_stmt.isNull(_columnIndexOfEvaluacion)) {
            _tmpEvaluacion = null
          } else {
            _tmpEvaluacion = _stmt.getText(_columnIndexOfEvaluacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanAulaEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDocenteId,_tmpCompetencias,_tmpLogros,_tmpIndicadores,_tmpRecursos,_tmpMetodologia,_tmpEvaluacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<PlanAulaEntity> {
    val _sql: String =
        "SELECT * FROM academic_planes_aula WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfCompetencias: Int = getColumnIndexOrThrow(_stmt, "competencias")
        val _columnIndexOfLogros: Int = getColumnIndexOrThrow(_stmt, "logros")
        val _columnIndexOfIndicadores: Int = getColumnIndexOrThrow(_stmt, "indicadores")
        val _columnIndexOfRecursos: Int = getColumnIndexOrThrow(_stmt, "recursos")
        val _columnIndexOfMetodologia: Int = getColumnIndexOrThrow(_stmt, "metodologia")
        val _columnIndexOfEvaluacion: Int = getColumnIndexOrThrow(_stmt, "evaluacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanAulaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanAulaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpCompetencias: String?
          if (_stmt.isNull(_columnIndexOfCompetencias)) {
            _tmpCompetencias = null
          } else {
            _tmpCompetencias = _stmt.getText(_columnIndexOfCompetencias)
          }
          val _tmpLogros: String?
          if (_stmt.isNull(_columnIndexOfLogros)) {
            _tmpLogros = null
          } else {
            _tmpLogros = _stmt.getText(_columnIndexOfLogros)
          }
          val _tmpIndicadores: String?
          if (_stmt.isNull(_columnIndexOfIndicadores)) {
            _tmpIndicadores = null
          } else {
            _tmpIndicadores = _stmt.getText(_columnIndexOfIndicadores)
          }
          val _tmpRecursos: String?
          if (_stmt.isNull(_columnIndexOfRecursos)) {
            _tmpRecursos = null
          } else {
            _tmpRecursos = _stmt.getText(_columnIndexOfRecursos)
          }
          val _tmpMetodologia: String?
          if (_stmt.isNull(_columnIndexOfMetodologia)) {
            _tmpMetodologia = null
          } else {
            _tmpMetodologia = _stmt.getText(_columnIndexOfMetodologia)
          }
          val _tmpEvaluacion: String?
          if (_stmt.isNull(_columnIndexOfEvaluacion)) {
            _tmpEvaluacion = null
          } else {
            _tmpEvaluacion = _stmt.getText(_columnIndexOfEvaluacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanAulaEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDocenteId,_tmpCompetencias,_tmpLogros,_tmpIndicadores,_tmpRecursos,_tmpMetodologia,_tmpEvaluacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<PlanAulaEntity> {
    val _sql: String = "SELECT * FROM academic_planes_aula WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfClaseId: Int = getColumnIndexOrThrow(_stmt, "claseId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfCompetencias: Int = getColumnIndexOrThrow(_stmt, "competencias")
        val _columnIndexOfLogros: Int = getColumnIndexOrThrow(_stmt, "logros")
        val _columnIndexOfIndicadores: Int = getColumnIndexOrThrow(_stmt, "indicadores")
        val _columnIndexOfRecursos: Int = getColumnIndexOrThrow(_stmt, "recursos")
        val _columnIndexOfMetodologia: Int = getColumnIndexOrThrow(_stmt, "metodologia")
        val _columnIndexOfEvaluacion: Int = getColumnIndexOrThrow(_stmt, "evaluacion")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanAulaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanAulaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpClaseId: Long
          _tmpClaseId = _stmt.getLong(_columnIndexOfClaseId)
          val _tmpDocenteId: String
          _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          val _tmpCompetencias: String?
          if (_stmt.isNull(_columnIndexOfCompetencias)) {
            _tmpCompetencias = null
          } else {
            _tmpCompetencias = _stmt.getText(_columnIndexOfCompetencias)
          }
          val _tmpLogros: String?
          if (_stmt.isNull(_columnIndexOfLogros)) {
            _tmpLogros = null
          } else {
            _tmpLogros = _stmt.getText(_columnIndexOfLogros)
          }
          val _tmpIndicadores: String?
          if (_stmt.isNull(_columnIndexOfIndicadores)) {
            _tmpIndicadores = null
          } else {
            _tmpIndicadores = _stmt.getText(_columnIndexOfIndicadores)
          }
          val _tmpRecursos: String?
          if (_stmt.isNull(_columnIndexOfRecursos)) {
            _tmpRecursos = null
          } else {
            _tmpRecursos = _stmt.getText(_columnIndexOfRecursos)
          }
          val _tmpMetodologia: String?
          if (_stmt.isNull(_columnIndexOfMetodologia)) {
            _tmpMetodologia = null
          } else {
            _tmpMetodologia = _stmt.getText(_columnIndexOfMetodologia)
          }
          val _tmpEvaluacion: String?
          if (_stmt.isNull(_columnIndexOfEvaluacion)) {
            _tmpEvaluacion = null
          } else {
            _tmpEvaluacion = _stmt.getText(_columnIndexOfEvaluacion)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanAulaEntity(_tmpId,_tmpInstitutionId,_tmpClaseId,_tmpDocenteId,_tmpCompetencias,_tmpLogros,_tmpIndicadores,_tmpRecursos,_tmpMetodologia,_tmpEvaluacion,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_planes_aula WHERE id = ? AND institutionId = ?"
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
        "UPDATE academic_planes_aula SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

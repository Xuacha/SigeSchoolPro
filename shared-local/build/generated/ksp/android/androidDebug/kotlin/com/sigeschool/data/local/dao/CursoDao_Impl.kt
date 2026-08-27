package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.CursoEntity
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
public class CursoDao_Impl(
  __db: RoomDatabase,
) : CursoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCursoEntity: EntityInsertAdapter<CursoEntity>

  private val __updateAdapterOfCursoEntity: EntityDeleteOrUpdateAdapter<CursoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCursoEntity = object : EntityInsertAdapter<CursoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_cursos` (`id`,`institutionId`,`gradoId`,`sedeId`,`jornadaId`,`nombre`,`codigo`,`capacidad`,`estudiantesInscritos`,`activo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CursoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.gradoId)
        statement.bindLong(4, entity.sedeId)
        statement.bindLong(5, entity.jornadaId)
        statement.bindText(6, entity.nombre)
        val _tmpCodigo: String? = entity.codigo
        if (_tmpCodigo == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCodigo)
        }
        statement.bindLong(8, entity.capacidad.toLong())
        statement.bindLong(9, entity.estudiantesInscritos.toLong())
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
      }
    }
    this.__updateAdapterOfCursoEntity = object : EntityDeleteOrUpdateAdapter<CursoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_cursos` SET `id` = ?,`institutionId` = ?,`gradoId` = ?,`sedeId` = ?,`jornadaId` = ?,`nombre` = ?,`codigo` = ?,`capacidad` = ?,`estudiantesInscritos` = ?,`activo` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CursoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.gradoId)
        statement.bindLong(4, entity.sedeId)
        statement.bindLong(5, entity.jornadaId)
        statement.bindText(6, entity.nombre)
        val _tmpCodigo: String? = entity.codigo
        if (_tmpCodigo == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCodigo)
        }
        statement.bindLong(8, entity.capacidad.toLong())
        statement.bindLong(9, entity.estudiantesInscritos.toLong())
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
        statement.bindLong(13, entity.id)
      }
    }
  }

  public override suspend fun insert(curso: CursoEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfCursoEntity.insertAndReturnId(_connection, curso)
    _result
  }

  public override suspend fun update(curso: CursoEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfCursoEntity.handle(_connection, curso)
  }

  public override fun getAll(instId: String): Flow<List<CursoEntity>> {
    val _sql: String =
        "SELECT * FROM academic_cursos WHERE institutionId = ? AND activo = 1 ORDER BY gradoId, nombre"
    return createFlow(__db, false, arrayOf("academic_cursos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfSedeId: Int = getColumnIndexOrThrow(_stmt, "sedeId")
        val _columnIndexOfJornadaId: Int = getColumnIndexOrThrow(_stmt, "jornadaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfCapacidad: Int = getColumnIndexOrThrow(_stmt, "capacidad")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CursoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CursoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpSedeId: Long
          _tmpSedeId = _stmt.getLong(_columnIndexOfSedeId)
          val _tmpJornadaId: Long
          _tmpJornadaId = _stmt.getLong(_columnIndexOfJornadaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpCapacidad: Int
          _tmpCapacidad = _stmt.getLong(_columnIndexOfCapacidad).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CursoEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpSedeId,_tmpJornadaId,_tmpNombre,_tmpCodigo,_tmpCapacidad,_tmpEstudiantesInscritos,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByGrado(gradoId: Long, instId: String): Flow<List<CursoEntity>> {
    val _sql: String =
        "SELECT * FROM academic_cursos WHERE gradoId = ? AND institutionId = ? AND activo = 1"
    return createFlow(__db, false, arrayOf("academic_cursos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, gradoId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfSedeId: Int = getColumnIndexOrThrow(_stmt, "sedeId")
        val _columnIndexOfJornadaId: Int = getColumnIndexOrThrow(_stmt, "jornadaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfCapacidad: Int = getColumnIndexOrThrow(_stmt, "capacidad")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<CursoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CursoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpSedeId: Long
          _tmpSedeId = _stmt.getLong(_columnIndexOfSedeId)
          val _tmpJornadaId: Long
          _tmpJornadaId = _stmt.getLong(_columnIndexOfJornadaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpCapacidad: Int
          _tmpCapacidad = _stmt.getLong(_columnIndexOfCapacidad).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              CursoEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpSedeId,_tmpJornadaId,_tmpNombre,_tmpCodigo,_tmpCapacidad,_tmpEstudiantesInscritos,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): CursoEntity? {
    val _sql: String = "SELECT * FROM academic_cursos WHERE id = ? AND institutionId = ?"
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
        val _columnIndexOfSedeId: Int = getColumnIndexOrThrow(_stmt, "sedeId")
        val _columnIndexOfJornadaId: Int = getColumnIndexOrThrow(_stmt, "jornadaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfCapacidad: Int = getColumnIndexOrThrow(_stmt, "capacidad")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: CursoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGradoId: Long
          _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          val _tmpSedeId: Long
          _tmpSedeId = _stmt.getLong(_columnIndexOfSedeId)
          val _tmpJornadaId: Long
          _tmpJornadaId = _stmt.getLong(_columnIndexOfJornadaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpCapacidad: Int
          _tmpCapacidad = _stmt.getLong(_columnIndexOfCapacidad).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              CursoEntity(_tmpId,_tmpInstitutionId,_tmpGradoId,_tmpSedeId,_tmpJornadaId,_tmpNombre,_tmpCodigo,_tmpCapacidad,_tmpEstudiantesInscritos,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun incrementarInscritos(cursoId: Long, instId: String) {
    val _sql: String =
        "UPDATE academic_cursos SET estudiantesInscritos = estudiantesInscritos + 1 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, cursoId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun decrementarInscritos(cursoId: Long, instId: String) {
    val _sql: String =
        "UPDATE academic_cursos SET estudiantesInscritos = estudiantesInscritos - 1 WHERE id = ? AND institutionId = ? AND estudiantesInscritos > 0"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, cursoId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_cursos WHERE id = ? AND institutionId = ?"
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

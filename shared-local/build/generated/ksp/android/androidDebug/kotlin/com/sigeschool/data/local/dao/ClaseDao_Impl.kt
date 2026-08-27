package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ClaseEntity
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
public class ClaseDao_Impl(
  __db: RoomDatabase,
) : ClaseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfClaseEntity: EntityInsertAdapter<ClaseEntity>

  private val __updateAdapterOfClaseEntity: EntityDeleteOrUpdateAdapter<ClaseEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfClaseEntity = object : EntityInsertAdapter<ClaseEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_clases` (`id`,`institutionId`,`ofertaAcademicaId`,`detalleOfertaId`,`nombre`,`horario`,`capacidadMaxima`,`estudiantesInscritos`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ClaseEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.ofertaAcademicaId)
        statement.bindLong(4, entity.detalleOfertaId)
        statement.bindText(5, entity.nombre)
        val _tmpHorario: String? = entity.horario
        if (_tmpHorario == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpHorario)
        }
        statement.bindLong(7, entity.capacidadMaxima.toLong())
        statement.bindLong(8, entity.estudiantesInscritos.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfClaseEntity = object : EntityDeleteOrUpdateAdapter<ClaseEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_clases` SET `id` = ?,`institutionId` = ?,`ofertaAcademicaId` = ?,`detalleOfertaId` = ?,`nombre` = ?,`horario` = ?,`capacidadMaxima` = ?,`estudiantesInscritos` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ClaseEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.ofertaAcademicaId)
        statement.bindLong(4, entity.detalleOfertaId)
        statement.bindText(5, entity.nombre)
        val _tmpHorario: String? = entity.horario
        if (_tmpHorario == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpHorario)
        }
        statement.bindLong(7, entity.capacidadMaxima.toLong())
        statement.bindLong(8, entity.estudiantesInscritos.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindLong(11, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: ClaseEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfClaseEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun update(entity: ClaseEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfClaseEntity.handle(_connection, entity)
  }

  public override fun getByOferta(instId: String, ofertaId: Long): Flow<List<ClaseEntity>> {
    val _sql: String =
        "SELECT * FROM academic_clases WHERE institutionId = ? AND ofertaAcademicaId = ?"
    return createFlow(__db, false, arrayOf("academic_clases")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, ofertaId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfDetalleOfertaId: Int = getColumnIndexOrThrow(_stmt, "detalleOfertaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfCapacidadMaxima: Int = getColumnIndexOrThrow(_stmt, "capacidadMaxima")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ClaseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClaseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpDetalleOfertaId: Long
          _tmpDetalleOfertaId = _stmt.getLong(_columnIndexOfDetalleOfertaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpCapacidadMaxima: Int
          _tmpCapacidadMaxima = _stmt.getLong(_columnIndexOfCapacidadMaxima).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ClaseEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpDetalleOfertaId,_tmpNombre,_tmpHorario,_tmpCapacidadMaxima,_tmpEstudiantesInscritos,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByOfertaSync(instId: String, ofertaId: Long): List<ClaseEntity> {
    val _sql: String =
        "SELECT * FROM academic_clases WHERE institutionId = ? AND ofertaAcademicaId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, ofertaId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfDetalleOfertaId: Int = getColumnIndexOrThrow(_stmt, "detalleOfertaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfCapacidadMaxima: Int = getColumnIndexOrThrow(_stmt, "capacidadMaxima")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ClaseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClaseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpDetalleOfertaId: Long
          _tmpDetalleOfertaId = _stmt.getLong(_columnIndexOfDetalleOfertaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpCapacidadMaxima: Int
          _tmpCapacidadMaxima = _stmt.getLong(_columnIndexOfCapacidadMaxima).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ClaseEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpDetalleOfertaId,_tmpNombre,_tmpHorario,_tmpCapacidadMaxima,_tmpEstudiantesInscritos,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByDetalle(instId: String, detalleId: Long): ClaseEntity? {
    val _sql: String =
        "SELECT * FROM academic_clases WHERE institutionId = ? AND detalleOfertaId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, detalleId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfDetalleOfertaId: Int = getColumnIndexOrThrow(_stmt, "detalleOfertaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfCapacidadMaxima: Int = getColumnIndexOrThrow(_stmt, "capacidadMaxima")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ClaseEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpDetalleOfertaId: Long
          _tmpDetalleOfertaId = _stmt.getLong(_columnIndexOfDetalleOfertaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpCapacidadMaxima: Int
          _tmpCapacidadMaxima = _stmt.getLong(_columnIndexOfCapacidadMaxima).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ClaseEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpDetalleOfertaId,_tmpNombre,_tmpHorario,_tmpCapacidadMaxima,_tmpEstudiantesInscritos,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): ClaseEntity? {
    val _sql: String = "SELECT * FROM academic_clases WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfDetalleOfertaId: Int = getColumnIndexOrThrow(_stmt, "detalleOfertaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfCapacidadMaxima: Int = getColumnIndexOrThrow(_stmt, "capacidadMaxima")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ClaseEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpDetalleOfertaId: Long
          _tmpDetalleOfertaId = _stmt.getLong(_columnIndexOfDetalleOfertaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpCapacidadMaxima: Int
          _tmpCapacidadMaxima = _stmt.getLong(_columnIndexOfCapacidadMaxima).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ClaseEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpDetalleOfertaId,_tmpNombre,_tmpHorario,_tmpCapacidadMaxima,_tmpEstudiantesInscritos,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<ClaseEntity> {
    val _sql: String = "SELECT * FROM academic_clases WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfDetalleOfertaId: Int = getColumnIndexOrThrow(_stmt, "detalleOfertaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfCapacidadMaxima: Int = getColumnIndexOrThrow(_stmt, "capacidadMaxima")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ClaseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClaseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpDetalleOfertaId: Long
          _tmpDetalleOfertaId = _stmt.getLong(_columnIndexOfDetalleOfertaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpCapacidadMaxima: Int
          _tmpCapacidadMaxima = _stmt.getLong(_columnIndexOfCapacidadMaxima).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ClaseEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpDetalleOfertaId,_tmpNombre,_tmpHorario,_tmpCapacidadMaxima,_tmpEstudiantesInscritos,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<ClaseEntity> {
    val _sql: String = "SELECT * FROM academic_clases WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfDetalleOfertaId: Int = getColumnIndexOrThrow(_stmt, "detalleOfertaId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfCapacidadMaxima: Int = getColumnIndexOrThrow(_stmt, "capacidadMaxima")
        val _columnIndexOfEstudiantesInscritos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesInscritos")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ClaseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClaseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpDetalleOfertaId: Long
          _tmpDetalleOfertaId = _stmt.getLong(_columnIndexOfDetalleOfertaId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpCapacidadMaxima: Int
          _tmpCapacidadMaxima = _stmt.getLong(_columnIndexOfCapacidadMaxima).toInt()
          val _tmpEstudiantesInscritos: Int
          _tmpEstudiantesInscritos = _stmt.getLong(_columnIndexOfEstudiantesInscritos).toInt()
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ClaseEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpDetalleOfertaId,_tmpNombre,_tmpHorario,_tmpCapacidadMaxima,_tmpEstudiantesInscritos,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun incrementarInscritos(id: Long, instId: String) {
    val _sql: String =
        "UPDATE academic_clases SET estudiantesInscritos = estudiantesInscritos + 1 WHERE id = ? AND institutionId = ?"
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

  public override suspend fun decrementarInscritos(id: Long, instId: String) {
    val _sql: String =
        "UPDATE academic_clases SET estudiantesInscritos = estudiantesInscritos - 1 WHERE id = ? AND institutionId = ?"
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

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_clases WHERE id = ? AND institutionId = ?"
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
        "UPDATE academic_clases SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

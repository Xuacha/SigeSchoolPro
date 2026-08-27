package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.DetalleOfertaAcademicaEntity
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
public class DetalleOfertaAcademicaDao_Impl(
  __db: RoomDatabase,
) : DetalleOfertaAcademicaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDetalleOfertaAcademicaEntity:
      EntityInsertAdapter<DetalleOfertaAcademicaEntity>

  private val __updateAdapterOfDetalleOfertaAcademicaEntity:
      EntityDeleteOrUpdateAdapter<DetalleOfertaAcademicaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDetalleOfertaAcademicaEntity = object :
        EntityInsertAdapter<DetalleOfertaAcademicaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_detalles_oferta` (`id`,`institutionId`,`ofertaAcademicaId`,`asignaturaId`,`docenteId`,`intensidadHoraria`,`aula`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement,
          entity: DetalleOfertaAcademicaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.ofertaAcademicaId)
        statement.bindLong(4, entity.asignaturaId)
        val _tmpDocenteId: String? = entity.docenteId
        if (_tmpDocenteId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocenteId)
        }
        statement.bindLong(6, entity.intensidadHoraria.toLong())
        val _tmpAula: String? = entity.aula
        if (_tmpAula == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpAula)
        }
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
    this.__updateAdapterOfDetalleOfertaAcademicaEntity = object :
        EntityDeleteOrUpdateAdapter<DetalleOfertaAcademicaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_detalles_oferta` SET `id` = ?,`institutionId` = ?,`ofertaAcademicaId` = ?,`asignaturaId` = ?,`docenteId` = ?,`intensidadHoraria` = ?,`aula` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement,
          entity: DetalleOfertaAcademicaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.ofertaAcademicaId)
        statement.bindLong(4, entity.asignaturaId)
        val _tmpDocenteId: String? = entity.docenteId
        if (_tmpDocenteId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocenteId)
        }
        statement.bindLong(6, entity.intensidadHoraria.toLong())
        val _tmpAula: String? = entity.aula
        if (_tmpAula == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpAula)
        }
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: DetalleOfertaAcademicaEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfDetalleOfertaAcademicaEntity.insertAndReturnId(_connection,
        entity)
    _result
  }

  public override suspend fun update(entity: DetalleOfertaAcademicaEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfDetalleOfertaAcademicaEntity.handle(_connection, entity)
  }

  public override fun getByOferta(instId: String, ofertaId: Long):
      Flow<List<DetalleOfertaAcademicaEntity>> {
    val _sql: String =
        "SELECT * FROM academic_detalles_oferta WHERE institutionId = ? AND ofertaAcademicaId = ?"
    return createFlow(__db, false, arrayOf("academic_detalles_oferta")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, ofertaId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfAula: Int = getColumnIndexOrThrow(_stmt, "aula")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DetalleOfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DetalleOfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpAula: String?
          if (_stmt.isNull(_columnIndexOfAula)) {
            _tmpAula = null
          } else {
            _tmpAula = _stmt.getText(_columnIndexOfAula)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DetalleOfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpAsignaturaId,_tmpDocenteId,_tmpIntensidadHoraria,_tmpAula,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByOfertaSync(instId: String, ofertaId: Long):
      List<DetalleOfertaAcademicaEntity> {
    val _sql: String =
        "SELECT * FROM academic_detalles_oferta WHERE institutionId = ? AND ofertaAcademicaId = ?"
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
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfAula: Int = getColumnIndexOrThrow(_stmt, "aula")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DetalleOfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DetalleOfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpAula: String?
          if (_stmt.isNull(_columnIndexOfAula)) {
            _tmpAula = null
          } else {
            _tmpAula = _stmt.getText(_columnIndexOfAula)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DetalleOfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpAsignaturaId,_tmpDocenteId,_tmpIntensidadHoraria,_tmpAula,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): DetalleOfertaAcademicaEntity? {
    val _sql: String = "SELECT * FROM academic_detalles_oferta WHERE id = ? AND institutionId = ?"
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
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfAula: Int = getColumnIndexOrThrow(_stmt, "aula")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: DetalleOfertaAcademicaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpAula: String?
          if (_stmt.isNull(_columnIndexOfAula)) {
            _tmpAula = null
          } else {
            _tmpAula = _stmt.getText(_columnIndexOfAula)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              DetalleOfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpAsignaturaId,_tmpDocenteId,_tmpIntensidadHoraria,_tmpAula,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<DetalleOfertaAcademicaEntity> {
    val _sql: String =
        "SELECT * FROM academic_detalles_oferta WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfAula: Int = getColumnIndexOrThrow(_stmt, "aula")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DetalleOfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DetalleOfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpAula: String?
          if (_stmt.isNull(_columnIndexOfAula)) {
            _tmpAula = null
          } else {
            _tmpAula = _stmt.getText(_columnIndexOfAula)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DetalleOfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpAsignaturaId,_tmpDocenteId,_tmpIntensidadHoraria,_tmpAula,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<DetalleOfertaAcademicaEntity> {
    val _sql: String = "SELECT * FROM academic_detalles_oferta WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfOfertaAcademicaId: Int = getColumnIndexOrThrow(_stmt, "ofertaAcademicaId")
        val _columnIndexOfAsignaturaId: Int = getColumnIndexOrThrow(_stmt, "asignaturaId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfAula: Int = getColumnIndexOrThrow(_stmt, "aula")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<DetalleOfertaAcademicaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DetalleOfertaAcademicaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpOfertaAcademicaId: Long
          _tmpOfertaAcademicaId = _stmt.getLong(_columnIndexOfOfertaAcademicaId)
          val _tmpAsignaturaId: Long
          _tmpAsignaturaId = _stmt.getLong(_columnIndexOfAsignaturaId)
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpAula: String?
          if (_stmt.isNull(_columnIndexOfAula)) {
            _tmpAula = null
          } else {
            _tmpAula = _stmt.getText(_columnIndexOfAula)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              DetalleOfertaAcademicaEntity(_tmpId,_tmpInstitutionId,_tmpOfertaAcademicaId,_tmpAsignaturaId,_tmpDocenteId,_tmpIntensidadHoraria,_tmpAula,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_detalles_oferta WHERE id = ? AND institutionId = ?"
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
        "UPDATE academic_detalles_oferta SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

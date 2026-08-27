package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AcademicGradoEntity
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
public class AcademicGradoDao_Impl(
  __db: RoomDatabase,
) : AcademicGradoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAcademicGradoEntity: EntityInsertAdapter<AcademicGradoEntity>

  private val __updateAdapterOfAcademicGradoEntity: EntityDeleteOrUpdateAdapter<AcademicGradoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAcademicGradoEntity = object : EntityInsertAdapter<AcademicGradoEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_grados` (`id`,`institutionId`,`nivelEducativoId`,`nombre`,`descripcion`,`orden`,`esActivo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AcademicGradoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.nivelEducativoId)
        statement.bindText(4, entity.nombre)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDescripcion)
        }
        statement.bindLong(6, entity.orden.toLong())
        val _tmp: Int = if (entity.esActivo) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
    this.__updateAdapterOfAcademicGradoEntity = object :
        EntityDeleteOrUpdateAdapter<AcademicGradoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_grados` SET `id` = ?,`institutionId` = ?,`nivelEducativoId` = ?,`nombre` = ?,`descripcion` = ?,`orden` = ?,`esActivo` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AcademicGradoEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.nivelEducativoId)
        statement.bindText(4, entity.nombre)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDescripcion)
        }
        statement.bindLong(6, entity.orden.toLong())
        val _tmp: Int = if (entity.esActivo) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: AcademicGradoEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfAcademicGradoEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun update(entity: AcademicGradoEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfAcademicGradoEntity.handle(_connection, entity)
  }

  public override fun getByNivel(instId: String, nivelId: Long): Flow<List<AcademicGradoEntity>> {
    val _sql: String =
        "SELECT * FROM academic_grados WHERE institutionId = ? AND nivelEducativoId = ? ORDER BY orden ASC"
    return createFlow(__db, false, arrayOf("academic_grados")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, nivelId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfOrden: Int = getColumnIndexOrThrow(_stmt, "orden")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AcademicGradoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AcademicGradoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNivelEducativoId: Long
          _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpOrden: Int
          _tmpOrden = _stmt.getLong(_columnIndexOfOrden).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AcademicGradoEntity(_tmpId,_tmpInstitutionId,_tmpNivelEducativoId,_tmpNombre,_tmpDescripcion,_tmpOrden,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAll(instId: String): Flow<List<AcademicGradoEntity>> {
    val _sql: String = "SELECT * FROM academic_grados WHERE institutionId = ? ORDER BY orden ASC"
    return createFlow(__db, false, arrayOf("academic_grados")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfOrden: Int = getColumnIndexOrThrow(_stmt, "orden")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AcademicGradoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AcademicGradoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNivelEducativoId: Long
          _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpOrden: Int
          _tmpOrden = _stmt.getLong(_columnIndexOfOrden).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AcademicGradoEntity(_tmpId,_tmpInstitutionId,_tmpNivelEducativoId,_tmpNombre,_tmpDescripcion,_tmpOrden,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): AcademicGradoEntity? {
    val _sql: String = "SELECT * FROM academic_grados WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfOrden: Int = getColumnIndexOrThrow(_stmt, "orden")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: AcademicGradoEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNivelEducativoId: Long
          _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpOrden: Int
          _tmpOrden = _stmt.getLong(_columnIndexOfOrden).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              AcademicGradoEntity(_tmpId,_tmpInstitutionId,_tmpNivelEducativoId,_tmpNombre,_tmpDescripcion,_tmpOrden,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<AcademicGradoEntity> {
    val _sql: String = "SELECT * FROM academic_grados WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfOrden: Int = getColumnIndexOrThrow(_stmt, "orden")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AcademicGradoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AcademicGradoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNivelEducativoId: Long
          _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpOrden: Int
          _tmpOrden = _stmt.getLong(_columnIndexOfOrden).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AcademicGradoEntity(_tmpId,_tmpInstitutionId,_tmpNivelEducativoId,_tmpNombre,_tmpDescripcion,_tmpOrden,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<AcademicGradoEntity> {
    val _sql: String = "SELECT * FROM academic_grados WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfOrden: Int = getColumnIndexOrThrow(_stmt, "orden")
        val _columnIndexOfEsActivo: Int = getColumnIndexOrThrow(_stmt, "esActivo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AcademicGradoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AcademicGradoEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNivelEducativoId: Long
          _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpOrden: Int
          _tmpOrden = _stmt.getLong(_columnIndexOfOrden).toInt()
          val _tmpEsActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsActivo).toInt()
          _tmpEsActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AcademicGradoEntity(_tmpId,_tmpInstitutionId,_tmpNivelEducativoId,_tmpNombre,_tmpDescripcion,_tmpOrden,_tmpEsActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_grados WHERE id = ? AND institutionId = ?"
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
        "UPDATE academic_grados SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

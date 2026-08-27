package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AsignaturaEntity
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
public class AsignaturaDao_Impl(
  __db: RoomDatabase,
) : AsignaturaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAsignaturaEntity: EntityInsertAdapter<AsignaturaEntity>

  private val __updateAdapterOfAsignaturaEntity: EntityDeleteOrUpdateAdapter<AsignaturaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAsignaturaEntity = object : EntityInsertAdapter<AsignaturaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_asignaturas` (`id`,`institutionId`,`areaConocimientoId`,`nombre`,`codigo`,`descripcion`,`intensidadHoraria`,`esElectiva`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AsignaturaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.areaConocimientoId)
        statement.bindText(4, entity.nombre)
        val _tmpCodigo: String? = entity.codigo
        if (_tmpCodigo == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpCodigo)
        }
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDescripcion)
        }
        statement.bindLong(7, entity.intensidadHoraria.toLong())
        val _tmp: Int = if (entity.esElectiva) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__updateAdapterOfAsignaturaEntity = object :
        EntityDeleteOrUpdateAdapter<AsignaturaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `academic_asignaturas` SET `id` = ?,`institutionId` = ?,`areaConocimientoId` = ?,`nombre` = ?,`codigo` = ?,`descripcion` = ?,`intensidadHoraria` = ?,`esElectiva` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AsignaturaEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindLong(3, entity.areaConocimientoId)
        statement.bindText(4, entity.nombre)
        val _tmpCodigo: String? = entity.codigo
        if (_tmpCodigo == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpCodigo)
        }
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDescripcion)
        }
        statement.bindLong(7, entity.intensidadHoraria.toLong())
        val _tmp: Int = if (entity.esElectiva) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindLong(11, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: AsignaturaEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfAsignaturaEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun update(entity: AsignaturaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfAsignaturaEntity.handle(_connection, entity)
  }

  public override fun getAll(instId: String): Flow<List<AsignaturaEntity>> {
    val _sql: String =
        "SELECT * FROM academic_asignaturas WHERE institutionId = ? ORDER BY nombre ASC"
    return createFlow(__db, false, arrayOf("academic_asignaturas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAreaConocimientoId: Int = getColumnIndexOrThrow(_stmt,
            "areaConocimientoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfEsElectiva: Int = getColumnIndexOrThrow(_stmt, "esElectiva")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AsignaturaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AsignaturaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAreaConocimientoId: Long
          _tmpAreaConocimientoId = _stmt.getLong(_columnIndexOfAreaConocimientoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpEsElectiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsElectiva).toInt()
          _tmpEsElectiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AsignaturaEntity(_tmpId,_tmpInstitutionId,_tmpAreaConocimientoId,_tmpNombre,_tmpCodigo,_tmpDescripcion,_tmpIntensidadHoraria,_tmpEsElectiva,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByArea(instId: String, areaId: Long): Flow<List<AsignaturaEntity>> {
    val _sql: String =
        "SELECT * FROM academic_asignaturas WHERE institutionId = ? AND areaConocimientoId = ?"
    return createFlow(__db, false, arrayOf("academic_asignaturas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, areaId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAreaConocimientoId: Int = getColumnIndexOrThrow(_stmt,
            "areaConocimientoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfEsElectiva: Int = getColumnIndexOrThrow(_stmt, "esElectiva")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AsignaturaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AsignaturaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAreaConocimientoId: Long
          _tmpAreaConocimientoId = _stmt.getLong(_columnIndexOfAreaConocimientoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpEsElectiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsElectiva).toInt()
          _tmpEsElectiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AsignaturaEntity(_tmpId,_tmpInstitutionId,_tmpAreaConocimientoId,_tmpNombre,_tmpCodigo,_tmpDescripcion,_tmpIntensidadHoraria,_tmpEsElectiva,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): AsignaturaEntity? {
    val _sql: String = "SELECT * FROM academic_asignaturas WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAreaConocimientoId: Int = getColumnIndexOrThrow(_stmt,
            "areaConocimientoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfEsElectiva: Int = getColumnIndexOrThrow(_stmt, "esElectiva")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: AsignaturaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAreaConocimientoId: Long
          _tmpAreaConocimientoId = _stmt.getLong(_columnIndexOfAreaConocimientoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpEsElectiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsElectiva).toInt()
          _tmpEsElectiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              AsignaturaEntity(_tmpId,_tmpInstitutionId,_tmpAreaConocimientoId,_tmpNombre,_tmpCodigo,_tmpDescripcion,_tmpIntensidadHoraria,_tmpEsElectiva,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByName(nombre: String, instId: String): AsignaturaEntity? {
    val _sql: String =
        "SELECT * FROM academic_asignaturas WHERE nombre = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, nombre)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAreaConocimientoId: Int = getColumnIndexOrThrow(_stmt,
            "areaConocimientoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfEsElectiva: Int = getColumnIndexOrThrow(_stmt, "esElectiva")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: AsignaturaEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAreaConocimientoId: Long
          _tmpAreaConocimientoId = _stmt.getLong(_columnIndexOfAreaConocimientoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpEsElectiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsElectiva).toInt()
          _tmpEsElectiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              AsignaturaEntity(_tmpId,_tmpInstitutionId,_tmpAreaConocimientoId,_tmpNombre,_tmpCodigo,_tmpDescripcion,_tmpIntensidadHoraria,_tmpEsElectiva,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<AsignaturaEntity> {
    val _sql: String =
        "SELECT * FROM academic_asignaturas WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAreaConocimientoId: Int = getColumnIndexOrThrow(_stmt,
            "areaConocimientoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfEsElectiva: Int = getColumnIndexOrThrow(_stmt, "esElectiva")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AsignaturaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AsignaturaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAreaConocimientoId: Long
          _tmpAreaConocimientoId = _stmt.getLong(_columnIndexOfAreaConocimientoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpEsElectiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsElectiva).toInt()
          _tmpEsElectiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AsignaturaEntity(_tmpId,_tmpInstitutionId,_tmpAreaConocimientoId,_tmpNombre,_tmpCodigo,_tmpDescripcion,_tmpIntensidadHoraria,_tmpEsElectiva,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(instId: String): List<AsignaturaEntity> {
    val _sql: String = "SELECT * FROM academic_asignaturas WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAreaConocimientoId: Int = getColumnIndexOrThrow(_stmt,
            "areaConocimientoId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfIntensidadHoraria: Int = getColumnIndexOrThrow(_stmt, "intensidadHoraria")
        val _columnIndexOfEsElectiva: Int = getColumnIndexOrThrow(_stmt, "esElectiva")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AsignaturaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AsignaturaEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAreaConocimientoId: Long
          _tmpAreaConocimientoId = _stmt.getLong(_columnIndexOfAreaConocimientoId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpCodigo: String?
          if (_stmt.isNull(_columnIndexOfCodigo)) {
            _tmpCodigo = null
          } else {
            _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpIntensidadHoraria: Int
          _tmpIntensidadHoraria = _stmt.getLong(_columnIndexOfIntensidadHoraria).toInt()
          val _tmpEsElectiva: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEsElectiva).toInt()
          _tmpEsElectiva = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AsignaturaEntity(_tmpId,_tmpInstitutionId,_tmpAreaConocimientoId,_tmpNombre,_tmpCodigo,_tmpDescripcion,_tmpIntensidadHoraria,_tmpEsElectiva,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM academic_asignaturas WHERE id = ? AND institutionId = ?"
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
        "UPDATE academic_asignaturas SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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

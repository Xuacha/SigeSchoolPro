package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ServicioEntity
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
public class ServicioDao_Impl(
  __db: RoomDatabase,
) : ServicioDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfServicioEntity: EntityInsertAdapter<ServicioEntity>

  private val __updateAdapterOfServicioEntity: EntityDeleteOrUpdateAdapter<ServicioEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfServicioEntity = object : EntityInsertAdapter<ServicioEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `servicios` (`id`,`institutionId`,`nombre`,`descripcion`,`tipo`,`responsable`,`ubicacion`,`horario`,`notificaAcudiente`,`activo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ServicioEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDescripcion)
        }
        statement.bindText(5, entity.tipo)
        val _tmpResponsable: String? = entity.responsable
        if (_tmpResponsable == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpResponsable)
        }
        val _tmpUbicacion: String? = entity.ubicacion
        if (_tmpUbicacion == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpUbicacion)
        }
        val _tmpHorario: String? = entity.horario
        if (_tmpHorario == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpHorario)
        }
        val _tmp: Int = if (entity.notificaAcudiente) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        val _tmp_1: Int = if (entity.activo) 1 else 0
        statement.bindLong(10, _tmp_1.toLong())
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
      }
    }
    this.__updateAdapterOfServicioEntity = object : EntityDeleteOrUpdateAdapter<ServicioEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `servicios` SET `id` = ?,`institutionId` = ?,`nombre` = ?,`descripcion` = ?,`tipo` = ?,`responsable` = ?,`ubicacion` = ?,`horario` = ?,`notificaAcudiente` = ?,`activo` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ServicioEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.nombre)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDescripcion)
        }
        statement.bindText(5, entity.tipo)
        val _tmpResponsable: String? = entity.responsable
        if (_tmpResponsable == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpResponsable)
        }
        val _tmpUbicacion: String? = entity.ubicacion
        if (_tmpUbicacion == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpUbicacion)
        }
        val _tmpHorario: String? = entity.horario
        if (_tmpHorario == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpHorario)
        }
        val _tmp: Int = if (entity.notificaAcudiente) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        val _tmp_1: Int = if (entity.activo) 1 else 0
        statement.bindLong(10, _tmp_1.toLong())
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
        statement.bindLong(13, entity.id)
      }
    }
  }

  public override suspend fun insert(servicio: ServicioEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfServicioEntity.insertAndReturnId(_connection, servicio)
    _result
  }

  public override suspend fun update(servicio: ServicioEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfServicioEntity.handle(_connection, servicio)
  }

  public override fun getAllActivos(instId: String): Flow<List<ServicioEntity>> {
    val _sql: String =
        "SELECT * FROM servicios WHERE institutionId = ? AND activo = 1 ORDER BY nombre ASC"
    return createFlow(__db, false, arrayOf("servicios")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResponsable: Int = getColumnIndexOrThrow(_stmt, "responsable")
        val _columnIndexOfUbicacion: Int = getColumnIndexOrThrow(_stmt, "ubicacion")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfNotificaAcudiente: Int = getColumnIndexOrThrow(_stmt, "notificaAcudiente")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ServicioEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ServicioEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResponsable: String?
          if (_stmt.isNull(_columnIndexOfResponsable)) {
            _tmpResponsable = null
          } else {
            _tmpResponsable = _stmt.getText(_columnIndexOfResponsable)
          }
          val _tmpUbicacion: String?
          if (_stmt.isNull(_columnIndexOfUbicacion)) {
            _tmpUbicacion = null
          } else {
            _tmpUbicacion = _stmt.getText(_columnIndexOfUbicacion)
          }
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpNotificaAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificaAcudiente).toInt()
          _tmpNotificaAcudiente = _tmp != 0
          val _tmpActivo: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ServicioEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDescripcion,_tmpTipo,_tmpResponsable,_tmpUbicacion,_tmpHorario,_tmpNotificaAcudiente,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long, instId: String): ServicioEntity? {
    val _sql: String = "SELECT * FROM servicios WHERE institutionId = ? AND id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResponsable: Int = getColumnIndexOrThrow(_stmt, "responsable")
        val _columnIndexOfUbicacion: Int = getColumnIndexOrThrow(_stmt, "ubicacion")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfNotificaAcudiente: Int = getColumnIndexOrThrow(_stmt, "notificaAcudiente")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ServicioEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResponsable: String?
          if (_stmt.isNull(_columnIndexOfResponsable)) {
            _tmpResponsable = null
          } else {
            _tmpResponsable = _stmt.getText(_columnIndexOfResponsable)
          }
          val _tmpUbicacion: String?
          if (_stmt.isNull(_columnIndexOfUbicacion)) {
            _tmpUbicacion = null
          } else {
            _tmpUbicacion = _stmt.getText(_columnIndexOfUbicacion)
          }
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpNotificaAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificaAcudiente).toInt()
          _tmpNotificaAcudiente = _tmp != 0
          val _tmpActivo: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ServicioEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDescripcion,_tmpTipo,_tmpResponsable,_tmpUbicacion,_tmpHorario,_tmpNotificaAcudiente,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(instId: String): List<ServicioEntity> {
    val _sql: String = "SELECT * FROM servicios WHERE institutionId = ? AND syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _columnIndexOfResponsable: Int = getColumnIndexOrThrow(_stmt, "responsable")
        val _columnIndexOfUbicacion: Int = getColumnIndexOrThrow(_stmt, "ubicacion")
        val _columnIndexOfHorario: Int = getColumnIndexOrThrow(_stmt, "horario")
        val _columnIndexOfNotificaAcudiente: Int = getColumnIndexOrThrow(_stmt, "notificaAcudiente")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ServicioEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ServicioEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpTipo: String
          _tmpTipo = _stmt.getText(_columnIndexOfTipo)
          val _tmpResponsable: String?
          if (_stmt.isNull(_columnIndexOfResponsable)) {
            _tmpResponsable = null
          } else {
            _tmpResponsable = _stmt.getText(_columnIndexOfResponsable)
          }
          val _tmpUbicacion: String?
          if (_stmt.isNull(_columnIndexOfUbicacion)) {
            _tmpUbicacion = null
          } else {
            _tmpUbicacion = _stmt.getText(_columnIndexOfUbicacion)
          }
          val _tmpHorario: String?
          if (_stmt.isNull(_columnIndexOfHorario)) {
            _tmpHorario = null
          } else {
            _tmpHorario = _stmt.getText(_columnIndexOfHorario)
          }
          val _tmpNotificaAcudiente: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfNotificaAcudiente).toInt()
          _tmpNotificaAcudiente = _tmp != 0
          val _tmpActivo: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ServicioEntity(_tmpId,_tmpInstitutionId,_tmpNombre,_tmpDescripcion,_tmpTipo,_tmpResponsable,_tmpUbicacion,_tmpHorario,_tmpNotificaAcudiente,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long, instId: String) {
    val _sql: String = "DELETE FROM servicios WHERE id = ? AND institutionId = ?"
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

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE servicios SET syncStatus = 0, lastModified = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
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

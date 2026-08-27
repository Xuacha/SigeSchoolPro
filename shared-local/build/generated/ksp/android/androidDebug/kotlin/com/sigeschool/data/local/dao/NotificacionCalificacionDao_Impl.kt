package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.NotificacionCalificacionEntity
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class NotificacionCalificacionDao_Impl(
  __db: RoomDatabase,
) : NotificacionCalificacionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfNotificacionCalificacionEntity:
      EntityInsertAdapter<NotificacionCalificacionEntity>

  private val __updateAdapterOfNotificacionCalificacionEntity:
      EntityDeleteOrUpdateAdapter<NotificacionCalificacionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfNotificacionCalificacionEntity = object :
        EntityInsertAdapter<NotificacionCalificacionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `notificaciones_calificaciones` (`id`,`calificacionId`,`institutionId`,`enviadoEstudiante`,`enviadoAcudiente`,`enviadoDocente`,`enviadoCoordinador`,`fechaEnvioEstudiante`,`fechaEnvioAcudiente`,`fechaEnvioDocente`,`fechaEnvioCoordinador`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement,
          entity: NotificacionCalificacionEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.calificacionId)
        statement.bindText(3, entity.institutionId)
        val _tmp: Int = if (entity.enviadoEstudiante) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        val _tmp_1: Int = if (entity.enviadoAcudiente) 1 else 0
        statement.bindLong(5, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.enviadoDocente) 1 else 0
        statement.bindLong(6, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.enviadoCoordinador) 1 else 0
        statement.bindLong(7, _tmp_3.toLong())
        val _tmpFechaEnvioEstudiante: Long? = entity.fechaEnvioEstudiante
        if (_tmpFechaEnvioEstudiante == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpFechaEnvioEstudiante)
        }
        val _tmpFechaEnvioAcudiente: Long? = entity.fechaEnvioAcudiente
        if (_tmpFechaEnvioAcudiente == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpFechaEnvioAcudiente)
        }
        val _tmpFechaEnvioDocente: Long? = entity.fechaEnvioDocente
        if (_tmpFechaEnvioDocente == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpFechaEnvioDocente)
        }
        val _tmpFechaEnvioCoordinador: Long? = entity.fechaEnvioCoordinador
        if (_tmpFechaEnvioCoordinador == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpFechaEnvioCoordinador)
        }
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
      }
    }
    this.__updateAdapterOfNotificacionCalificacionEntity = object :
        EntityDeleteOrUpdateAdapter<NotificacionCalificacionEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `notificaciones_calificaciones` SET `id` = ?,`calificacionId` = ?,`institutionId` = ?,`enviadoEstudiante` = ?,`enviadoAcudiente` = ?,`enviadoDocente` = ?,`enviadoCoordinador` = ?,`fechaEnvioEstudiante` = ?,`fechaEnvioAcudiente` = ?,`fechaEnvioDocente` = ?,`fechaEnvioCoordinador` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement,
          entity: NotificacionCalificacionEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.calificacionId)
        statement.bindText(3, entity.institutionId)
        val _tmp: Int = if (entity.enviadoEstudiante) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        val _tmp_1: Int = if (entity.enviadoAcudiente) 1 else 0
        statement.bindLong(5, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.enviadoDocente) 1 else 0
        statement.bindLong(6, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.enviadoCoordinador) 1 else 0
        statement.bindLong(7, _tmp_3.toLong())
        val _tmpFechaEnvioEstudiante: Long? = entity.fechaEnvioEstudiante
        if (_tmpFechaEnvioEstudiante == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpFechaEnvioEstudiante)
        }
        val _tmpFechaEnvioAcudiente: Long? = entity.fechaEnvioAcudiente
        if (_tmpFechaEnvioAcudiente == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpFechaEnvioAcudiente)
        }
        val _tmpFechaEnvioDocente: Long? = entity.fechaEnvioDocente
        if (_tmpFechaEnvioDocente == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpFechaEnvioDocente)
        }
        val _tmpFechaEnvioCoordinador: Long? = entity.fechaEnvioCoordinador
        if (_tmpFechaEnvioCoordinador == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpFechaEnvioCoordinador)
        }
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
        statement.bindLong(14, entity.id)
      }
    }
  }

  public override suspend fun insert(notificacion: NotificacionCalificacionEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long =
        __insertAdapterOfNotificacionCalificacionEntity.insertAndReturnId(_connection, notificacion)
    _result
  }

  public override suspend fun update(notificacion: NotificacionCalificacionEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfNotificacionCalificacionEntity.handle(_connection, notificacion)
  }

  public override suspend fun getByCalificacion(calificacionId: String, instId: String):
      NotificacionCalificacionEntity? {
    val _sql: String =
        "SELECT * FROM notificaciones_calificaciones WHERE calificacionId = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, calificacionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCalificacionId: Int = getColumnIndexOrThrow(_stmt, "calificacionId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEnviadoEstudiante: Int = getColumnIndexOrThrow(_stmt, "enviadoEstudiante")
        val _columnIndexOfEnviadoAcudiente: Int = getColumnIndexOrThrow(_stmt, "enviadoAcudiente")
        val _columnIndexOfEnviadoDocente: Int = getColumnIndexOrThrow(_stmt, "enviadoDocente")
        val _columnIndexOfEnviadoCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "enviadoCoordinador")
        val _columnIndexOfFechaEnvioEstudiante: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioEstudiante")
        val _columnIndexOfFechaEnvioAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioAcudiente")
        val _columnIndexOfFechaEnvioDocente: Int = getColumnIndexOrThrow(_stmt, "fechaEnvioDocente")
        val _columnIndexOfFechaEnvioCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioCoordinador")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: NotificacionCalificacionEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpCalificacionId: String
          _tmpCalificacionId = _stmt.getText(_columnIndexOfCalificacionId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEnviadoEstudiante: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnviadoEstudiante).toInt()
          _tmpEnviadoEstudiante = _tmp != 0
          val _tmpEnviadoAcudiente: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfEnviadoAcudiente).toInt()
          _tmpEnviadoAcudiente = _tmp_1 != 0
          val _tmpEnviadoDocente: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEnviadoDocente).toInt()
          _tmpEnviadoDocente = _tmp_2 != 0
          val _tmpEnviadoCoordinador: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfEnviadoCoordinador).toInt()
          _tmpEnviadoCoordinador = _tmp_3 != 0
          val _tmpFechaEnvioEstudiante: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioEstudiante)) {
            _tmpFechaEnvioEstudiante = null
          } else {
            _tmpFechaEnvioEstudiante = _stmt.getLong(_columnIndexOfFechaEnvioEstudiante)
          }
          val _tmpFechaEnvioAcudiente: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioAcudiente)) {
            _tmpFechaEnvioAcudiente = null
          } else {
            _tmpFechaEnvioAcudiente = _stmt.getLong(_columnIndexOfFechaEnvioAcudiente)
          }
          val _tmpFechaEnvioDocente: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioDocente)) {
            _tmpFechaEnvioDocente = null
          } else {
            _tmpFechaEnvioDocente = _stmt.getLong(_columnIndexOfFechaEnvioDocente)
          }
          val _tmpFechaEnvioCoordinador: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioCoordinador)) {
            _tmpFechaEnvioCoordinador = null
          } else {
            _tmpFechaEnvioCoordinador = _stmt.getLong(_columnIndexOfFechaEnvioCoordinador)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              NotificacionCalificacionEntity(_tmpId,_tmpCalificacionId,_tmpInstitutionId,_tmpEnviadoEstudiante,_tmpEnviadoAcudiente,_tmpEnviadoDocente,_tmpEnviadoCoordinador,_tmpFechaEnvioEstudiante,_tmpFechaEnvioAcudiente,_tmpFechaEnvioDocente,_tmpFechaEnvioCoordinador,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendientesDocente(instId: String):
      List<NotificacionCalificacionEntity> {
    val _sql: String =
        "SELECT * FROM notificaciones_calificaciones WHERE institutionId = ? AND enviadoDocente = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCalificacionId: Int = getColumnIndexOrThrow(_stmt, "calificacionId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEnviadoEstudiante: Int = getColumnIndexOrThrow(_stmt, "enviadoEstudiante")
        val _columnIndexOfEnviadoAcudiente: Int = getColumnIndexOrThrow(_stmt, "enviadoAcudiente")
        val _columnIndexOfEnviadoDocente: Int = getColumnIndexOrThrow(_stmt, "enviadoDocente")
        val _columnIndexOfEnviadoCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "enviadoCoordinador")
        val _columnIndexOfFechaEnvioEstudiante: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioEstudiante")
        val _columnIndexOfFechaEnvioAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioAcudiente")
        val _columnIndexOfFechaEnvioDocente: Int = getColumnIndexOrThrow(_stmt, "fechaEnvioDocente")
        val _columnIndexOfFechaEnvioCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioCoordinador")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<NotificacionCalificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: NotificacionCalificacionEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpCalificacionId: String
          _tmpCalificacionId = _stmt.getText(_columnIndexOfCalificacionId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEnviadoEstudiante: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnviadoEstudiante).toInt()
          _tmpEnviadoEstudiante = _tmp != 0
          val _tmpEnviadoAcudiente: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfEnviadoAcudiente).toInt()
          _tmpEnviadoAcudiente = _tmp_1 != 0
          val _tmpEnviadoDocente: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEnviadoDocente).toInt()
          _tmpEnviadoDocente = _tmp_2 != 0
          val _tmpEnviadoCoordinador: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfEnviadoCoordinador).toInt()
          _tmpEnviadoCoordinador = _tmp_3 != 0
          val _tmpFechaEnvioEstudiante: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioEstudiante)) {
            _tmpFechaEnvioEstudiante = null
          } else {
            _tmpFechaEnvioEstudiante = _stmt.getLong(_columnIndexOfFechaEnvioEstudiante)
          }
          val _tmpFechaEnvioAcudiente: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioAcudiente)) {
            _tmpFechaEnvioAcudiente = null
          } else {
            _tmpFechaEnvioAcudiente = _stmt.getLong(_columnIndexOfFechaEnvioAcudiente)
          }
          val _tmpFechaEnvioDocente: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioDocente)) {
            _tmpFechaEnvioDocente = null
          } else {
            _tmpFechaEnvioDocente = _stmt.getLong(_columnIndexOfFechaEnvioDocente)
          }
          val _tmpFechaEnvioCoordinador: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioCoordinador)) {
            _tmpFechaEnvioCoordinador = null
          } else {
            _tmpFechaEnvioCoordinador = _stmt.getLong(_columnIndexOfFechaEnvioCoordinador)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              NotificacionCalificacionEntity(_tmpId,_tmpCalificacionId,_tmpInstitutionId,_tmpEnviadoEstudiante,_tmpEnviadoAcudiente,_tmpEnviadoDocente,_tmpEnviadoCoordinador,_tmpFechaEnvioEstudiante,_tmpFechaEnvioAcudiente,_tmpFechaEnvioDocente,_tmpFechaEnvioCoordinador,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendientesCoordinador(instId: String):
      List<NotificacionCalificacionEntity> {
    val _sql: String =
        "SELECT * FROM notificaciones_calificaciones WHERE institutionId = ? AND enviadoCoordinador = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCalificacionId: Int = getColumnIndexOrThrow(_stmt, "calificacionId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEnviadoEstudiante: Int = getColumnIndexOrThrow(_stmt, "enviadoEstudiante")
        val _columnIndexOfEnviadoAcudiente: Int = getColumnIndexOrThrow(_stmt, "enviadoAcudiente")
        val _columnIndexOfEnviadoDocente: Int = getColumnIndexOrThrow(_stmt, "enviadoDocente")
        val _columnIndexOfEnviadoCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "enviadoCoordinador")
        val _columnIndexOfFechaEnvioEstudiante: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioEstudiante")
        val _columnIndexOfFechaEnvioAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioAcudiente")
        val _columnIndexOfFechaEnvioDocente: Int = getColumnIndexOrThrow(_stmt, "fechaEnvioDocente")
        val _columnIndexOfFechaEnvioCoordinador: Int = getColumnIndexOrThrow(_stmt,
            "fechaEnvioCoordinador")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<NotificacionCalificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: NotificacionCalificacionEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpCalificacionId: String
          _tmpCalificacionId = _stmt.getText(_columnIndexOfCalificacionId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEnviadoEstudiante: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnviadoEstudiante).toInt()
          _tmpEnviadoEstudiante = _tmp != 0
          val _tmpEnviadoAcudiente: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfEnviadoAcudiente).toInt()
          _tmpEnviadoAcudiente = _tmp_1 != 0
          val _tmpEnviadoDocente: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfEnviadoDocente).toInt()
          _tmpEnviadoDocente = _tmp_2 != 0
          val _tmpEnviadoCoordinador: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfEnviadoCoordinador).toInt()
          _tmpEnviadoCoordinador = _tmp_3 != 0
          val _tmpFechaEnvioEstudiante: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioEstudiante)) {
            _tmpFechaEnvioEstudiante = null
          } else {
            _tmpFechaEnvioEstudiante = _stmt.getLong(_columnIndexOfFechaEnvioEstudiante)
          }
          val _tmpFechaEnvioAcudiente: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioAcudiente)) {
            _tmpFechaEnvioAcudiente = null
          } else {
            _tmpFechaEnvioAcudiente = _stmt.getLong(_columnIndexOfFechaEnvioAcudiente)
          }
          val _tmpFechaEnvioDocente: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioDocente)) {
            _tmpFechaEnvioDocente = null
          } else {
            _tmpFechaEnvioDocente = _stmt.getLong(_columnIndexOfFechaEnvioDocente)
          }
          val _tmpFechaEnvioCoordinador: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvioCoordinador)) {
            _tmpFechaEnvioCoordinador = null
          } else {
            _tmpFechaEnvioCoordinador = _stmt.getLong(_columnIndexOfFechaEnvioCoordinador)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              NotificacionCalificacionEntity(_tmpId,_tmpCalificacionId,_tmpInstitutionId,_tmpEnviadoEstudiante,_tmpEnviadoAcudiente,_tmpEnviadoDocente,_tmpEnviadoCoordinador,_tmpFechaEnvioEstudiante,_tmpFechaEnvioAcudiente,_tmpFechaEnvioDocente,_tmpFechaEnvioCoordinador,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun marcarDocenteEnviado(id: Long, fecha: Long) {
    val _sql: String =
        "UPDATE notificaciones_calificaciones SET enviadoDocente = 1, fechaEnvioDocente = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fecha)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun marcarCoordinadorEnviado(id: Long, fecha: Long) {
    val _sql: String =
        "UPDATE notificaciones_calificaciones SET enviadoCoordinador = 1, fechaEnvioCoordinador = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fecha)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun marcarEstudianteEnviado(id: Long, fecha: Long) {
    val _sql: String =
        "UPDATE notificaciones_calificaciones SET enviadoEstudiante = 1, fechaEnvioEstudiante = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fecha)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun marcarAcudienteEnviado(id: Long, fecha: Long) {
    val _sql: String =
        "UPDATE notificaciones_calificaciones SET enviadoAcudiente = 1, fechaEnvioAcudiente = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fecha)
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

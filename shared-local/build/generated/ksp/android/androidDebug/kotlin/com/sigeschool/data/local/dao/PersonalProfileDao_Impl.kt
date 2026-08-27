package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.HistorialCvEntity
import com.sigeschool.`data`.local.entity.PerfilPersonalEntity
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
public class PersonalProfileDao_Impl(
  __db: RoomDatabase,
) : PersonalProfileDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPerfilPersonalEntity: EntityInsertAdapter<PerfilPersonalEntity>

  private val __insertAdapterOfHistorialCvEntity: EntityInsertAdapter<HistorialCvEntity>

  private val __updateAdapterOfPerfilPersonalEntity:
      EntityDeleteOrUpdateAdapter<PerfilPersonalEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPerfilPersonalEntity = object :
        EntityInsertAdapter<PerfilPersonalEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `perfiles_personal` (`idPerfil`,`idUsuario`,`idRol`,`datosJson`,`documentoOriginalPath`,`documentoOriginalHash`,`estado`,`fechaCarga`,`fechaActualizacion`,`version`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PerfilPersonalEntity) {
        statement.bindText(1, entity.idPerfil)
        statement.bindText(2, entity.idUsuario)
        statement.bindText(3, entity.idRol)
        statement.bindText(4, entity.datosJson)
        val _tmpDocumentoOriginalPath: String? = entity.documentoOriginalPath
        if (_tmpDocumentoOriginalPath == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocumentoOriginalPath)
        }
        val _tmpDocumentoOriginalHash: String? = entity.documentoOriginalHash
        if (_tmpDocumentoOriginalHash == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDocumentoOriginalHash)
        }
        statement.bindText(7, entity.estado)
        statement.bindLong(8, entity.fechaCarga)
        statement.bindLong(9, entity.fechaActualizacion)
        statement.bindLong(10, entity.version.toLong())
      }
    }
    this.__insertAdapterOfHistorialCvEntity = object : EntityInsertAdapter<HistorialCvEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `historial_cv` (`idHistorial`,`idPerfil`,`version`,`datosJson`,`fechaModificacion`,`idUsuarioModificador`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: HistorialCvEntity) {
        statement.bindText(1, entity.idHistorial)
        statement.bindText(2, entity.idPerfil)
        statement.bindLong(3, entity.version.toLong())
        statement.bindText(4, entity.datosJson)
        statement.bindLong(5, entity.fechaModificacion)
        statement.bindText(6, entity.idUsuarioModificador)
      }
    }
    this.__updateAdapterOfPerfilPersonalEntity = object :
        EntityDeleteOrUpdateAdapter<PerfilPersonalEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `perfiles_personal` SET `idPerfil` = ?,`idUsuario` = ?,`idRol` = ?,`datosJson` = ?,`documentoOriginalPath` = ?,`documentoOriginalHash` = ?,`estado` = ?,`fechaCarga` = ?,`fechaActualizacion` = ?,`version` = ? WHERE `idPerfil` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PerfilPersonalEntity) {
        statement.bindText(1, entity.idPerfil)
        statement.bindText(2, entity.idUsuario)
        statement.bindText(3, entity.idRol)
        statement.bindText(4, entity.datosJson)
        val _tmpDocumentoOriginalPath: String? = entity.documentoOriginalPath
        if (_tmpDocumentoOriginalPath == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocumentoOriginalPath)
        }
        val _tmpDocumentoOriginalHash: String? = entity.documentoOriginalHash
        if (_tmpDocumentoOriginalHash == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDocumentoOriginalHash)
        }
        statement.bindText(7, entity.estado)
        statement.bindLong(8, entity.fechaCarga)
        statement.bindLong(9, entity.fechaActualizacion)
        statement.bindLong(10, entity.version.toLong())
        statement.bindText(11, entity.idPerfil)
      }
    }
  }

  public override suspend fun insertProfile(profile: PerfilPersonalEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPerfilPersonalEntity.insert(_connection, profile)
  }

  public override suspend fun insertHistory(history: HistorialCvEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfHistorialCvEntity.insert(_connection, history)
  }

  public override suspend fun updateProfile(profile: PerfilPersonalEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPerfilPersonalEntity.handle(_connection, profile)
  }

  public override fun getAllProfiles(): Flow<List<PerfilPersonalEntity>> {
    val _sql: String = "SELECT * FROM perfiles_personal"
    return createFlow(__db, false, arrayOf("perfiles_personal")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfIdPerfil: Int = getColumnIndexOrThrow(_stmt, "idPerfil")
        val _columnIndexOfIdUsuario: Int = getColumnIndexOrThrow(_stmt, "idUsuario")
        val _columnIndexOfIdRol: Int = getColumnIndexOrThrow(_stmt, "idRol")
        val _columnIndexOfDatosJson: Int = getColumnIndexOrThrow(_stmt, "datosJson")
        val _columnIndexOfDocumentoOriginalPath: Int = getColumnIndexOrThrow(_stmt,
            "documentoOriginalPath")
        val _columnIndexOfDocumentoOriginalHash: Int = getColumnIndexOrThrow(_stmt,
            "documentoOriginalHash")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfFechaCarga: Int = getColumnIndexOrThrow(_stmt, "fechaCarga")
        val _columnIndexOfFechaActualizacion: Int = getColumnIndexOrThrow(_stmt,
            "fechaActualizacion")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _result: MutableList<PerfilPersonalEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PerfilPersonalEntity
          val _tmpIdPerfil: String
          _tmpIdPerfil = _stmt.getText(_columnIndexOfIdPerfil)
          val _tmpIdUsuario: String
          _tmpIdUsuario = _stmt.getText(_columnIndexOfIdUsuario)
          val _tmpIdRol: String
          _tmpIdRol = _stmt.getText(_columnIndexOfIdRol)
          val _tmpDatosJson: String
          _tmpDatosJson = _stmt.getText(_columnIndexOfDatosJson)
          val _tmpDocumentoOriginalPath: String?
          if (_stmt.isNull(_columnIndexOfDocumentoOriginalPath)) {
            _tmpDocumentoOriginalPath = null
          } else {
            _tmpDocumentoOriginalPath = _stmt.getText(_columnIndexOfDocumentoOriginalPath)
          }
          val _tmpDocumentoOriginalHash: String?
          if (_stmt.isNull(_columnIndexOfDocumentoOriginalHash)) {
            _tmpDocumentoOriginalHash = null
          } else {
            _tmpDocumentoOriginalHash = _stmt.getText(_columnIndexOfDocumentoOriginalHash)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpFechaCarga: Long
          _tmpFechaCarga = _stmt.getLong(_columnIndexOfFechaCarga)
          val _tmpFechaActualizacion: Long
          _tmpFechaActualizacion = _stmt.getLong(_columnIndexOfFechaActualizacion)
          val _tmpVersion: Int
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion).toInt()
          _item =
              PerfilPersonalEntity(_tmpIdPerfil,_tmpIdUsuario,_tmpIdRol,_tmpDatosJson,_tmpDocumentoOriginalPath,_tmpDocumentoOriginalHash,_tmpEstado,_tmpFechaCarga,_tmpFechaActualizacion,_tmpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProfileByUserId(userId: String): PerfilPersonalEntity? {
    val _sql: String = "SELECT * FROM perfiles_personal WHERE idUsuario = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        val _columnIndexOfIdPerfil: Int = getColumnIndexOrThrow(_stmt, "idPerfil")
        val _columnIndexOfIdUsuario: Int = getColumnIndexOrThrow(_stmt, "idUsuario")
        val _columnIndexOfIdRol: Int = getColumnIndexOrThrow(_stmt, "idRol")
        val _columnIndexOfDatosJson: Int = getColumnIndexOrThrow(_stmt, "datosJson")
        val _columnIndexOfDocumentoOriginalPath: Int = getColumnIndexOrThrow(_stmt,
            "documentoOriginalPath")
        val _columnIndexOfDocumentoOriginalHash: Int = getColumnIndexOrThrow(_stmt,
            "documentoOriginalHash")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfFechaCarga: Int = getColumnIndexOrThrow(_stmt, "fechaCarga")
        val _columnIndexOfFechaActualizacion: Int = getColumnIndexOrThrow(_stmt,
            "fechaActualizacion")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _result: PerfilPersonalEntity?
        if (_stmt.step()) {
          val _tmpIdPerfil: String
          _tmpIdPerfil = _stmt.getText(_columnIndexOfIdPerfil)
          val _tmpIdUsuario: String
          _tmpIdUsuario = _stmt.getText(_columnIndexOfIdUsuario)
          val _tmpIdRol: String
          _tmpIdRol = _stmt.getText(_columnIndexOfIdRol)
          val _tmpDatosJson: String
          _tmpDatosJson = _stmt.getText(_columnIndexOfDatosJson)
          val _tmpDocumentoOriginalPath: String?
          if (_stmt.isNull(_columnIndexOfDocumentoOriginalPath)) {
            _tmpDocumentoOriginalPath = null
          } else {
            _tmpDocumentoOriginalPath = _stmt.getText(_columnIndexOfDocumentoOriginalPath)
          }
          val _tmpDocumentoOriginalHash: String?
          if (_stmt.isNull(_columnIndexOfDocumentoOriginalHash)) {
            _tmpDocumentoOriginalHash = null
          } else {
            _tmpDocumentoOriginalHash = _stmt.getText(_columnIndexOfDocumentoOriginalHash)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpFechaCarga: Long
          _tmpFechaCarga = _stmt.getLong(_columnIndexOfFechaCarga)
          val _tmpFechaActualizacion: Long
          _tmpFechaActualizacion = _stmt.getLong(_columnIndexOfFechaActualizacion)
          val _tmpVersion: Int
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion).toInt()
          _result =
              PerfilPersonalEntity(_tmpIdPerfil,_tmpIdUsuario,_tmpIdRol,_tmpDatosJson,_tmpDocumentoOriginalPath,_tmpDocumentoOriginalHash,_tmpEstado,_tmpFechaCarga,_tmpFechaActualizacion,_tmpVersion)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getHistoryForProfile(profileId: String): Flow<List<HistorialCvEntity>> {
    val _sql: String = "SELECT * FROM historial_cv WHERE idPerfil = ? ORDER BY version DESC"
    return createFlow(__db, false, arrayOf("historial_cv")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, profileId)
        val _columnIndexOfIdHistorial: Int = getColumnIndexOrThrow(_stmt, "idHistorial")
        val _columnIndexOfIdPerfil: Int = getColumnIndexOrThrow(_stmt, "idPerfil")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDatosJson: Int = getColumnIndexOrThrow(_stmt, "datosJson")
        val _columnIndexOfFechaModificacion: Int = getColumnIndexOrThrow(_stmt, "fechaModificacion")
        val _columnIndexOfIdUsuarioModificador: Int = getColumnIndexOrThrow(_stmt,
            "idUsuarioModificador")
        val _result: MutableList<HistorialCvEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: HistorialCvEntity
          val _tmpIdHistorial: String
          _tmpIdHistorial = _stmt.getText(_columnIndexOfIdHistorial)
          val _tmpIdPerfil: String
          _tmpIdPerfil = _stmt.getText(_columnIndexOfIdPerfil)
          val _tmpVersion: Int
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion).toInt()
          val _tmpDatosJson: String
          _tmpDatosJson = _stmt.getText(_columnIndexOfDatosJson)
          val _tmpFechaModificacion: Long
          _tmpFechaModificacion = _stmt.getLong(_columnIndexOfFechaModificacion)
          val _tmpIdUsuarioModificador: String
          _tmpIdUsuarioModificador = _stmt.getText(_columnIndexOfIdUsuarioModificador)
          _item =
              HistorialCvEntity(_tmpIdHistorial,_tmpIdPerfil,_tmpVersion,_tmpDatosJson,_tmpFechaModificacion,_tmpIdUsuarioModificador)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

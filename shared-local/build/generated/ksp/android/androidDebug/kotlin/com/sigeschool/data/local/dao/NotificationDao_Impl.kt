package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.CircularEntity
import com.sigeschool.`data`.local.entity.InstitutionalNotificationEntity
import com.sigeschool.`data`.local.entity.LogNotificacionEntity
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
public class NotificationDao_Impl(
  __db: RoomDatabase,
) : NotificationDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfInstitutionalNotificationEntity:
      EntityInsertAdapter<InstitutionalNotificationEntity>

  private val __insertAdapterOfCircularEntity: EntityInsertAdapter<CircularEntity>

  private val __insertAdapterOfLogNotificacionEntity: EntityInsertAdapter<LogNotificacionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfInstitutionalNotificationEntity = object :
        EntityInsertAdapter<InstitutionalNotificationEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `institutional_notificaciones` (`idNotificacion`,`institutionId`,`idEstudiante`,`idAcudiente`,`idUsuarioRemitente`,`tipoNotificacion`,`asunto`,`mensaje`,`mensajeWhatsapp`,`mensajeEmail`,`fechaEnvio`,`canales`,`estadoEnvioEmail`,`estadoEnvioWhatsapp`,`estadoEnvioSms`,`estadoEnvioPush`,`idRespuesta`,`prioridad`,`fechaLecturaAcudiente`,`metadata`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement,
          entity: InstitutionalNotificationEntity) {
        statement.bindText(1, entity.idNotificacion)
        statement.bindText(2, entity.institutionId)
        val _tmpIdEstudiante: Long? = entity.idEstudiante
        if (_tmpIdEstudiante == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpIdEstudiante)
        }
        val _tmpIdAcudiente: String? = entity.idAcudiente
        if (_tmpIdAcudiente == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpIdAcudiente)
        }
        statement.bindText(5, entity.idUsuarioRemitente)
        statement.bindText(6, entity.tipoNotificacion)
        statement.bindText(7, entity.asunto)
        statement.bindText(8, entity.mensaje)
        val _tmpMensajeWhatsapp: String? = entity.mensajeWhatsapp
        if (_tmpMensajeWhatsapp == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpMensajeWhatsapp)
        }
        val _tmpMensajeEmail: String? = entity.mensajeEmail
        if (_tmpMensajeEmail == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpMensajeEmail)
        }
        statement.bindLong(11, entity.fechaEnvio)
        statement.bindText(12, entity.canales)
        val _tmpEstadoEnvioEmail: String? = entity.estadoEnvioEmail
        if (_tmpEstadoEnvioEmail == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpEstadoEnvioEmail)
        }
        val _tmpEstadoEnvioWhatsapp: String? = entity.estadoEnvioWhatsapp
        if (_tmpEstadoEnvioWhatsapp == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpEstadoEnvioWhatsapp)
        }
        val _tmpEstadoEnvioSms: String? = entity.estadoEnvioSms
        if (_tmpEstadoEnvioSms == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpEstadoEnvioSms)
        }
        val _tmpEstadoEnvioPush: String? = entity.estadoEnvioPush
        if (_tmpEstadoEnvioPush == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpEstadoEnvioPush)
        }
        val _tmpIdRespuesta: String? = entity.idRespuesta
        if (_tmpIdRespuesta == null) {
          statement.bindNull(17)
        } else {
          statement.bindText(17, _tmpIdRespuesta)
        }
        statement.bindText(18, entity.prioridad)
        val _tmpFechaLecturaAcudiente: Long? = entity.fechaLecturaAcudiente
        if (_tmpFechaLecturaAcudiente == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmpFechaLecturaAcudiente)
        }
        val _tmpMetadata: String? = entity.metadata
        if (_tmpMetadata == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpMetadata)
        }
      }
    }
    this.__insertAdapterOfCircularEntity = object : EntityInsertAdapter<CircularEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `circulares` (`idCircular`,`institutionId`,`titulo`,`contenido`,`contenidoWhatsapp`,`contenidoEmail`,`idUsuarioCreador`,`fechaCreacion`,`fechaProgramacion`,`estado`,`destinatarios`,`archivosAdjuntos`,`fechaEnvio`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CircularEntity) {
        statement.bindText(1, entity.idCircular)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.titulo)
        statement.bindText(4, entity.contenido)
        val _tmpContenidoWhatsapp: String? = entity.contenidoWhatsapp
        if (_tmpContenidoWhatsapp == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpContenidoWhatsapp)
        }
        val _tmpContenidoEmail: String? = entity.contenidoEmail
        if (_tmpContenidoEmail == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpContenidoEmail)
        }
        statement.bindText(7, entity.idUsuarioCreador)
        statement.bindLong(8, entity.fechaCreacion)
        val _tmpFechaProgramacion: Long? = entity.fechaProgramacion
        if (_tmpFechaProgramacion == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpFechaProgramacion)
        }
        statement.bindText(10, entity.estado)
        val _tmpDestinatarios: String? = entity.destinatarios
        if (_tmpDestinatarios == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpDestinatarios)
        }
        val _tmpArchivosAdjuntos: String? = entity.archivosAdjuntos
        if (_tmpArchivosAdjuntos == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpArchivosAdjuntos)
        }
        val _tmpFechaEnvio: Long? = entity.fechaEnvio
        if (_tmpFechaEnvio == null) {
          statement.bindNull(13)
        } else {
          statement.bindLong(13, _tmpFechaEnvio)
        }
      }
    }
    this.__insertAdapterOfLogNotificacionEntity = object :
        EntityInsertAdapter<LogNotificacionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `log_notificaciones` (`idLog`,`idNotificacion`,`institutionId`,`canal`,`fechaIntento`,`codigoRespuesta`,`mensajeRespuesta`,`exito`,`intentos`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: LogNotificacionEntity) {
        statement.bindText(1, entity.idLog)
        statement.bindText(2, entity.idNotificacion)
        statement.bindText(3, entity.institutionId)
        statement.bindText(4, entity.canal)
        statement.bindLong(5, entity.fechaIntento)
        val _tmpCodigoRespuesta: Int? = entity.codigoRespuesta
        if (_tmpCodigoRespuesta == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpCodigoRespuesta.toLong())
        }
        val _tmpMensajeRespuesta: String? = entity.mensajeRespuesta
        if (_tmpMensajeRespuesta == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpMensajeRespuesta)
        }
        val _tmp: Int = if (entity.exito) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindLong(9, entity.intentos.toLong())
      }
    }
  }

  public override suspend fun insertNotificacion(notificacion: InstitutionalNotificationEntity):
      Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfInstitutionalNotificationEntity.insert(_connection, notificacion)
  }

  public override suspend fun insertCircular(circular: CircularEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCircularEntity.insert(_connection, circular)
  }

  public override suspend fun insertLog(log: LogNotificacionEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfLogNotificacionEntity.insert(_connection, log)
  }

  public override suspend fun getNotificacionById(id: String): InstitutionalNotificationEntity? {
    val _sql: String = "SELECT * FROM institutional_notificaciones WHERE idNotificacion = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfIdNotificacion: Int = getColumnIndexOrThrow(_stmt, "idNotificacion")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfIdEstudiante: Int = getColumnIndexOrThrow(_stmt, "idEstudiante")
        val _columnIndexOfIdAcudiente: Int = getColumnIndexOrThrow(_stmt, "idAcudiente")
        val _columnIndexOfIdUsuarioRemitente: Int = getColumnIndexOrThrow(_stmt,
            "idUsuarioRemitente")
        val _columnIndexOfTipoNotificacion: Int = getColumnIndexOrThrow(_stmt, "tipoNotificacion")
        val _columnIndexOfAsunto: Int = getColumnIndexOrThrow(_stmt, "asunto")
        val _columnIndexOfMensaje: Int = getColumnIndexOrThrow(_stmt, "mensaje")
        val _columnIndexOfMensajeWhatsapp: Int = getColumnIndexOrThrow(_stmt, "mensajeWhatsapp")
        val _columnIndexOfMensajeEmail: Int = getColumnIndexOrThrow(_stmt, "mensajeEmail")
        val _columnIndexOfFechaEnvio: Int = getColumnIndexOrThrow(_stmt, "fechaEnvio")
        val _columnIndexOfCanales: Int = getColumnIndexOrThrow(_stmt, "canales")
        val _columnIndexOfEstadoEnvioEmail: Int = getColumnIndexOrThrow(_stmt, "estadoEnvioEmail")
        val _columnIndexOfEstadoEnvioWhatsapp: Int = getColumnIndexOrThrow(_stmt,
            "estadoEnvioWhatsapp")
        val _columnIndexOfEstadoEnvioSms: Int = getColumnIndexOrThrow(_stmt, "estadoEnvioSms")
        val _columnIndexOfEstadoEnvioPush: Int = getColumnIndexOrThrow(_stmt, "estadoEnvioPush")
        val _columnIndexOfIdRespuesta: Int = getColumnIndexOrThrow(_stmt, "idRespuesta")
        val _columnIndexOfPrioridad: Int = getColumnIndexOrThrow(_stmt, "prioridad")
        val _columnIndexOfFechaLecturaAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "fechaLecturaAcudiente")
        val _columnIndexOfMetadata: Int = getColumnIndexOrThrow(_stmt, "metadata")
        val _result: InstitutionalNotificationEntity?
        if (_stmt.step()) {
          val _tmpIdNotificacion: String
          _tmpIdNotificacion = _stmt.getText(_columnIndexOfIdNotificacion)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpIdEstudiante: Long?
          if (_stmt.isNull(_columnIndexOfIdEstudiante)) {
            _tmpIdEstudiante = null
          } else {
            _tmpIdEstudiante = _stmt.getLong(_columnIndexOfIdEstudiante)
          }
          val _tmpIdAcudiente: String?
          if (_stmt.isNull(_columnIndexOfIdAcudiente)) {
            _tmpIdAcudiente = null
          } else {
            _tmpIdAcudiente = _stmt.getText(_columnIndexOfIdAcudiente)
          }
          val _tmpIdUsuarioRemitente: String
          _tmpIdUsuarioRemitente = _stmt.getText(_columnIndexOfIdUsuarioRemitente)
          val _tmpTipoNotificacion: String
          _tmpTipoNotificacion = _stmt.getText(_columnIndexOfTipoNotificacion)
          val _tmpAsunto: String
          _tmpAsunto = _stmt.getText(_columnIndexOfAsunto)
          val _tmpMensaje: String
          _tmpMensaje = _stmt.getText(_columnIndexOfMensaje)
          val _tmpMensajeWhatsapp: String?
          if (_stmt.isNull(_columnIndexOfMensajeWhatsapp)) {
            _tmpMensajeWhatsapp = null
          } else {
            _tmpMensajeWhatsapp = _stmt.getText(_columnIndexOfMensajeWhatsapp)
          }
          val _tmpMensajeEmail: String?
          if (_stmt.isNull(_columnIndexOfMensajeEmail)) {
            _tmpMensajeEmail = null
          } else {
            _tmpMensajeEmail = _stmt.getText(_columnIndexOfMensajeEmail)
          }
          val _tmpFechaEnvio: Long
          _tmpFechaEnvio = _stmt.getLong(_columnIndexOfFechaEnvio)
          val _tmpCanales: String
          _tmpCanales = _stmt.getText(_columnIndexOfCanales)
          val _tmpEstadoEnvioEmail: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioEmail)) {
            _tmpEstadoEnvioEmail = null
          } else {
            _tmpEstadoEnvioEmail = _stmt.getText(_columnIndexOfEstadoEnvioEmail)
          }
          val _tmpEstadoEnvioWhatsapp: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioWhatsapp)) {
            _tmpEstadoEnvioWhatsapp = null
          } else {
            _tmpEstadoEnvioWhatsapp = _stmt.getText(_columnIndexOfEstadoEnvioWhatsapp)
          }
          val _tmpEstadoEnvioSms: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioSms)) {
            _tmpEstadoEnvioSms = null
          } else {
            _tmpEstadoEnvioSms = _stmt.getText(_columnIndexOfEstadoEnvioSms)
          }
          val _tmpEstadoEnvioPush: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioPush)) {
            _tmpEstadoEnvioPush = null
          } else {
            _tmpEstadoEnvioPush = _stmt.getText(_columnIndexOfEstadoEnvioPush)
          }
          val _tmpIdRespuesta: String?
          if (_stmt.isNull(_columnIndexOfIdRespuesta)) {
            _tmpIdRespuesta = null
          } else {
            _tmpIdRespuesta = _stmt.getText(_columnIndexOfIdRespuesta)
          }
          val _tmpPrioridad: String
          _tmpPrioridad = _stmt.getText(_columnIndexOfPrioridad)
          val _tmpFechaLecturaAcudiente: Long?
          if (_stmt.isNull(_columnIndexOfFechaLecturaAcudiente)) {
            _tmpFechaLecturaAcudiente = null
          } else {
            _tmpFechaLecturaAcudiente = _stmt.getLong(_columnIndexOfFechaLecturaAcudiente)
          }
          val _tmpMetadata: String?
          if (_stmt.isNull(_columnIndexOfMetadata)) {
            _tmpMetadata = null
          } else {
            _tmpMetadata = _stmt.getText(_columnIndexOfMetadata)
          }
          _result =
              InstitutionalNotificationEntity(_tmpIdNotificacion,_tmpInstitutionId,_tmpIdEstudiante,_tmpIdAcudiente,_tmpIdUsuarioRemitente,_tmpTipoNotificacion,_tmpAsunto,_tmpMensaje,_tmpMensajeWhatsapp,_tmpMensajeEmail,_tmpFechaEnvio,_tmpCanales,_tmpEstadoEnvioEmail,_tmpEstadoEnvioWhatsapp,_tmpEstadoEnvioSms,_tmpEstadoEnvioPush,_tmpIdRespuesta,_tmpPrioridad,_tmpFechaLecturaAcudiente,_tmpMetadata)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getNotificacionesByAcudiente(idAcudiente: String):
      Flow<List<InstitutionalNotificationEntity>> {
    val _sql: String =
        "SELECT * FROM institutional_notificaciones WHERE idAcudiente = ? ORDER BY fechaEnvio DESC"
    return createFlow(__db, false, arrayOf("institutional_notificaciones")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, idAcudiente)
        val _columnIndexOfIdNotificacion: Int = getColumnIndexOrThrow(_stmt, "idNotificacion")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfIdEstudiante: Int = getColumnIndexOrThrow(_stmt, "idEstudiante")
        val _columnIndexOfIdAcudiente: Int = getColumnIndexOrThrow(_stmt, "idAcudiente")
        val _columnIndexOfIdUsuarioRemitente: Int = getColumnIndexOrThrow(_stmt,
            "idUsuarioRemitente")
        val _columnIndexOfTipoNotificacion: Int = getColumnIndexOrThrow(_stmt, "tipoNotificacion")
        val _columnIndexOfAsunto: Int = getColumnIndexOrThrow(_stmt, "asunto")
        val _columnIndexOfMensaje: Int = getColumnIndexOrThrow(_stmt, "mensaje")
        val _columnIndexOfMensajeWhatsapp: Int = getColumnIndexOrThrow(_stmt, "mensajeWhatsapp")
        val _columnIndexOfMensajeEmail: Int = getColumnIndexOrThrow(_stmt, "mensajeEmail")
        val _columnIndexOfFechaEnvio: Int = getColumnIndexOrThrow(_stmt, "fechaEnvio")
        val _columnIndexOfCanales: Int = getColumnIndexOrThrow(_stmt, "canales")
        val _columnIndexOfEstadoEnvioEmail: Int = getColumnIndexOrThrow(_stmt, "estadoEnvioEmail")
        val _columnIndexOfEstadoEnvioWhatsapp: Int = getColumnIndexOrThrow(_stmt,
            "estadoEnvioWhatsapp")
        val _columnIndexOfEstadoEnvioSms: Int = getColumnIndexOrThrow(_stmt, "estadoEnvioSms")
        val _columnIndexOfEstadoEnvioPush: Int = getColumnIndexOrThrow(_stmt, "estadoEnvioPush")
        val _columnIndexOfIdRespuesta: Int = getColumnIndexOrThrow(_stmt, "idRespuesta")
        val _columnIndexOfPrioridad: Int = getColumnIndexOrThrow(_stmt, "prioridad")
        val _columnIndexOfFechaLecturaAcudiente: Int = getColumnIndexOrThrow(_stmt,
            "fechaLecturaAcudiente")
        val _columnIndexOfMetadata: Int = getColumnIndexOrThrow(_stmt, "metadata")
        val _result: MutableList<InstitutionalNotificationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: InstitutionalNotificationEntity
          val _tmpIdNotificacion: String
          _tmpIdNotificacion = _stmt.getText(_columnIndexOfIdNotificacion)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpIdEstudiante: Long?
          if (_stmt.isNull(_columnIndexOfIdEstudiante)) {
            _tmpIdEstudiante = null
          } else {
            _tmpIdEstudiante = _stmt.getLong(_columnIndexOfIdEstudiante)
          }
          val _tmpIdAcudiente: String?
          if (_stmt.isNull(_columnIndexOfIdAcudiente)) {
            _tmpIdAcudiente = null
          } else {
            _tmpIdAcudiente = _stmt.getText(_columnIndexOfIdAcudiente)
          }
          val _tmpIdUsuarioRemitente: String
          _tmpIdUsuarioRemitente = _stmt.getText(_columnIndexOfIdUsuarioRemitente)
          val _tmpTipoNotificacion: String
          _tmpTipoNotificacion = _stmt.getText(_columnIndexOfTipoNotificacion)
          val _tmpAsunto: String
          _tmpAsunto = _stmt.getText(_columnIndexOfAsunto)
          val _tmpMensaje: String
          _tmpMensaje = _stmt.getText(_columnIndexOfMensaje)
          val _tmpMensajeWhatsapp: String?
          if (_stmt.isNull(_columnIndexOfMensajeWhatsapp)) {
            _tmpMensajeWhatsapp = null
          } else {
            _tmpMensajeWhatsapp = _stmt.getText(_columnIndexOfMensajeWhatsapp)
          }
          val _tmpMensajeEmail: String?
          if (_stmt.isNull(_columnIndexOfMensajeEmail)) {
            _tmpMensajeEmail = null
          } else {
            _tmpMensajeEmail = _stmt.getText(_columnIndexOfMensajeEmail)
          }
          val _tmpFechaEnvio: Long
          _tmpFechaEnvio = _stmt.getLong(_columnIndexOfFechaEnvio)
          val _tmpCanales: String
          _tmpCanales = _stmt.getText(_columnIndexOfCanales)
          val _tmpEstadoEnvioEmail: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioEmail)) {
            _tmpEstadoEnvioEmail = null
          } else {
            _tmpEstadoEnvioEmail = _stmt.getText(_columnIndexOfEstadoEnvioEmail)
          }
          val _tmpEstadoEnvioWhatsapp: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioWhatsapp)) {
            _tmpEstadoEnvioWhatsapp = null
          } else {
            _tmpEstadoEnvioWhatsapp = _stmt.getText(_columnIndexOfEstadoEnvioWhatsapp)
          }
          val _tmpEstadoEnvioSms: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioSms)) {
            _tmpEstadoEnvioSms = null
          } else {
            _tmpEstadoEnvioSms = _stmt.getText(_columnIndexOfEstadoEnvioSms)
          }
          val _tmpEstadoEnvioPush: String?
          if (_stmt.isNull(_columnIndexOfEstadoEnvioPush)) {
            _tmpEstadoEnvioPush = null
          } else {
            _tmpEstadoEnvioPush = _stmt.getText(_columnIndexOfEstadoEnvioPush)
          }
          val _tmpIdRespuesta: String?
          if (_stmt.isNull(_columnIndexOfIdRespuesta)) {
            _tmpIdRespuesta = null
          } else {
            _tmpIdRespuesta = _stmt.getText(_columnIndexOfIdRespuesta)
          }
          val _tmpPrioridad: String
          _tmpPrioridad = _stmt.getText(_columnIndexOfPrioridad)
          val _tmpFechaLecturaAcudiente: Long?
          if (_stmt.isNull(_columnIndexOfFechaLecturaAcudiente)) {
            _tmpFechaLecturaAcudiente = null
          } else {
            _tmpFechaLecturaAcudiente = _stmt.getLong(_columnIndexOfFechaLecturaAcudiente)
          }
          val _tmpMetadata: String?
          if (_stmt.isNull(_columnIndexOfMetadata)) {
            _tmpMetadata = null
          } else {
            _tmpMetadata = _stmt.getText(_columnIndexOfMetadata)
          }
          _item =
              InstitutionalNotificationEntity(_tmpIdNotificacion,_tmpInstitutionId,_tmpIdEstudiante,_tmpIdAcudiente,_tmpIdUsuarioRemitente,_tmpTipoNotificacion,_tmpAsunto,_tmpMensaje,_tmpMensajeWhatsapp,_tmpMensajeEmail,_tmpFechaEnvio,_tmpCanales,_tmpEstadoEnvioEmail,_tmpEstadoEnvioWhatsapp,_tmpEstadoEnvioSms,_tmpEstadoEnvioPush,_tmpIdRespuesta,_tmpPrioridad,_tmpFechaLecturaAcudiente,_tmpMetadata)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCircularById(id: String): CircularEntity? {
    val _sql: String = "SELECT * FROM circulares WHERE idCircular = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfIdCircular: Int = getColumnIndexOrThrow(_stmt, "idCircular")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfContenido: Int = getColumnIndexOrThrow(_stmt, "contenido")
        val _columnIndexOfContenidoWhatsapp: Int = getColumnIndexOrThrow(_stmt, "contenidoWhatsapp")
        val _columnIndexOfContenidoEmail: Int = getColumnIndexOrThrow(_stmt, "contenidoEmail")
        val _columnIndexOfIdUsuarioCreador: Int = getColumnIndexOrThrow(_stmt, "idUsuarioCreador")
        val _columnIndexOfFechaCreacion: Int = getColumnIndexOrThrow(_stmt, "fechaCreacion")
        val _columnIndexOfFechaProgramacion: Int = getColumnIndexOrThrow(_stmt, "fechaProgramacion")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfDestinatarios: Int = getColumnIndexOrThrow(_stmt, "destinatarios")
        val _columnIndexOfArchivosAdjuntos: Int = getColumnIndexOrThrow(_stmt, "archivosAdjuntos")
        val _columnIndexOfFechaEnvio: Int = getColumnIndexOrThrow(_stmt, "fechaEnvio")
        val _result: CircularEntity?
        if (_stmt.step()) {
          val _tmpIdCircular: String
          _tmpIdCircular = _stmt.getText(_columnIndexOfIdCircular)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpContenido: String
          _tmpContenido = _stmt.getText(_columnIndexOfContenido)
          val _tmpContenidoWhatsapp: String?
          if (_stmt.isNull(_columnIndexOfContenidoWhatsapp)) {
            _tmpContenidoWhatsapp = null
          } else {
            _tmpContenidoWhatsapp = _stmt.getText(_columnIndexOfContenidoWhatsapp)
          }
          val _tmpContenidoEmail: String?
          if (_stmt.isNull(_columnIndexOfContenidoEmail)) {
            _tmpContenidoEmail = null
          } else {
            _tmpContenidoEmail = _stmt.getText(_columnIndexOfContenidoEmail)
          }
          val _tmpIdUsuarioCreador: String
          _tmpIdUsuarioCreador = _stmt.getText(_columnIndexOfIdUsuarioCreador)
          val _tmpFechaCreacion: Long
          _tmpFechaCreacion = _stmt.getLong(_columnIndexOfFechaCreacion)
          val _tmpFechaProgramacion: Long?
          if (_stmt.isNull(_columnIndexOfFechaProgramacion)) {
            _tmpFechaProgramacion = null
          } else {
            _tmpFechaProgramacion = _stmt.getLong(_columnIndexOfFechaProgramacion)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpDestinatarios: String?
          if (_stmt.isNull(_columnIndexOfDestinatarios)) {
            _tmpDestinatarios = null
          } else {
            _tmpDestinatarios = _stmt.getText(_columnIndexOfDestinatarios)
          }
          val _tmpArchivosAdjuntos: String?
          if (_stmt.isNull(_columnIndexOfArchivosAdjuntos)) {
            _tmpArchivosAdjuntos = null
          } else {
            _tmpArchivosAdjuntos = _stmt.getText(_columnIndexOfArchivosAdjuntos)
          }
          val _tmpFechaEnvio: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvio)) {
            _tmpFechaEnvio = null
          } else {
            _tmpFechaEnvio = _stmt.getLong(_columnIndexOfFechaEnvio)
          }
          _result =
              CircularEntity(_tmpIdCircular,_tmpInstitutionId,_tmpTitulo,_tmpContenido,_tmpContenidoWhatsapp,_tmpContenidoEmail,_tmpIdUsuarioCreador,_tmpFechaCreacion,_tmpFechaProgramacion,_tmpEstado,_tmpDestinatarios,_tmpArchivosAdjuntos,_tmpFechaEnvio)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingCirculares(currentTime: Long): List<CircularEntity> {
    val _sql: String =
        "SELECT * FROM circulares WHERE estado = 'PROGRAMADA' AND fechaProgramacion <= ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, currentTime)
        val _columnIndexOfIdCircular: Int = getColumnIndexOrThrow(_stmt, "idCircular")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfContenido: Int = getColumnIndexOrThrow(_stmt, "contenido")
        val _columnIndexOfContenidoWhatsapp: Int = getColumnIndexOrThrow(_stmt, "contenidoWhatsapp")
        val _columnIndexOfContenidoEmail: Int = getColumnIndexOrThrow(_stmt, "contenidoEmail")
        val _columnIndexOfIdUsuarioCreador: Int = getColumnIndexOrThrow(_stmt, "idUsuarioCreador")
        val _columnIndexOfFechaCreacion: Int = getColumnIndexOrThrow(_stmt, "fechaCreacion")
        val _columnIndexOfFechaProgramacion: Int = getColumnIndexOrThrow(_stmt, "fechaProgramacion")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfDestinatarios: Int = getColumnIndexOrThrow(_stmt, "destinatarios")
        val _columnIndexOfArchivosAdjuntos: Int = getColumnIndexOrThrow(_stmt, "archivosAdjuntos")
        val _columnIndexOfFechaEnvio: Int = getColumnIndexOrThrow(_stmt, "fechaEnvio")
        val _result: MutableList<CircularEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CircularEntity
          val _tmpIdCircular: String
          _tmpIdCircular = _stmt.getText(_columnIndexOfIdCircular)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpContenido: String
          _tmpContenido = _stmt.getText(_columnIndexOfContenido)
          val _tmpContenidoWhatsapp: String?
          if (_stmt.isNull(_columnIndexOfContenidoWhatsapp)) {
            _tmpContenidoWhatsapp = null
          } else {
            _tmpContenidoWhatsapp = _stmt.getText(_columnIndexOfContenidoWhatsapp)
          }
          val _tmpContenidoEmail: String?
          if (_stmt.isNull(_columnIndexOfContenidoEmail)) {
            _tmpContenidoEmail = null
          } else {
            _tmpContenidoEmail = _stmt.getText(_columnIndexOfContenidoEmail)
          }
          val _tmpIdUsuarioCreador: String
          _tmpIdUsuarioCreador = _stmt.getText(_columnIndexOfIdUsuarioCreador)
          val _tmpFechaCreacion: Long
          _tmpFechaCreacion = _stmt.getLong(_columnIndexOfFechaCreacion)
          val _tmpFechaProgramacion: Long?
          if (_stmt.isNull(_columnIndexOfFechaProgramacion)) {
            _tmpFechaProgramacion = null
          } else {
            _tmpFechaProgramacion = _stmt.getLong(_columnIndexOfFechaProgramacion)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpDestinatarios: String?
          if (_stmt.isNull(_columnIndexOfDestinatarios)) {
            _tmpDestinatarios = null
          } else {
            _tmpDestinatarios = _stmt.getText(_columnIndexOfDestinatarios)
          }
          val _tmpArchivosAdjuntos: String?
          if (_stmt.isNull(_columnIndexOfArchivosAdjuntos)) {
            _tmpArchivosAdjuntos = null
          } else {
            _tmpArchivosAdjuntos = _stmt.getText(_columnIndexOfArchivosAdjuntos)
          }
          val _tmpFechaEnvio: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvio)) {
            _tmpFechaEnvio = null
          } else {
            _tmpFechaEnvio = _stmt.getLong(_columnIndexOfFechaEnvio)
          }
          _item =
              CircularEntity(_tmpIdCircular,_tmpInstitutionId,_tmpTitulo,_tmpContenido,_tmpContenidoWhatsapp,_tmpContenidoEmail,_tmpIdUsuarioCreador,_tmpFechaCreacion,_tmpFechaProgramacion,_tmpEstado,_tmpDestinatarios,_tmpArchivosAdjuntos,_tmpFechaEnvio)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCirculares(): Flow<List<CircularEntity>> {
    val _sql: String = "SELECT * FROM circulares ORDER BY fechaCreacion DESC"
    return createFlow(__db, false, arrayOf("circulares")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfIdCircular: Int = getColumnIndexOrThrow(_stmt, "idCircular")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfContenido: Int = getColumnIndexOrThrow(_stmt, "contenido")
        val _columnIndexOfContenidoWhatsapp: Int = getColumnIndexOrThrow(_stmt, "contenidoWhatsapp")
        val _columnIndexOfContenidoEmail: Int = getColumnIndexOrThrow(_stmt, "contenidoEmail")
        val _columnIndexOfIdUsuarioCreador: Int = getColumnIndexOrThrow(_stmt, "idUsuarioCreador")
        val _columnIndexOfFechaCreacion: Int = getColumnIndexOrThrow(_stmt, "fechaCreacion")
        val _columnIndexOfFechaProgramacion: Int = getColumnIndexOrThrow(_stmt, "fechaProgramacion")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfDestinatarios: Int = getColumnIndexOrThrow(_stmt, "destinatarios")
        val _columnIndexOfArchivosAdjuntos: Int = getColumnIndexOrThrow(_stmt, "archivosAdjuntos")
        val _columnIndexOfFechaEnvio: Int = getColumnIndexOrThrow(_stmt, "fechaEnvio")
        val _result: MutableList<CircularEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CircularEntity
          val _tmpIdCircular: String
          _tmpIdCircular = _stmt.getText(_columnIndexOfIdCircular)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpContenido: String
          _tmpContenido = _stmt.getText(_columnIndexOfContenido)
          val _tmpContenidoWhatsapp: String?
          if (_stmt.isNull(_columnIndexOfContenidoWhatsapp)) {
            _tmpContenidoWhatsapp = null
          } else {
            _tmpContenidoWhatsapp = _stmt.getText(_columnIndexOfContenidoWhatsapp)
          }
          val _tmpContenidoEmail: String?
          if (_stmt.isNull(_columnIndexOfContenidoEmail)) {
            _tmpContenidoEmail = null
          } else {
            _tmpContenidoEmail = _stmt.getText(_columnIndexOfContenidoEmail)
          }
          val _tmpIdUsuarioCreador: String
          _tmpIdUsuarioCreador = _stmt.getText(_columnIndexOfIdUsuarioCreador)
          val _tmpFechaCreacion: Long
          _tmpFechaCreacion = _stmt.getLong(_columnIndexOfFechaCreacion)
          val _tmpFechaProgramacion: Long?
          if (_stmt.isNull(_columnIndexOfFechaProgramacion)) {
            _tmpFechaProgramacion = null
          } else {
            _tmpFechaProgramacion = _stmt.getLong(_columnIndexOfFechaProgramacion)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpDestinatarios: String?
          if (_stmt.isNull(_columnIndexOfDestinatarios)) {
            _tmpDestinatarios = null
          } else {
            _tmpDestinatarios = _stmt.getText(_columnIndexOfDestinatarios)
          }
          val _tmpArchivosAdjuntos: String?
          if (_stmt.isNull(_columnIndexOfArchivosAdjuntos)) {
            _tmpArchivosAdjuntos = null
          } else {
            _tmpArchivosAdjuntos = _stmt.getText(_columnIndexOfArchivosAdjuntos)
          }
          val _tmpFechaEnvio: Long?
          if (_stmt.isNull(_columnIndexOfFechaEnvio)) {
            _tmpFechaEnvio = null
          } else {
            _tmpFechaEnvio = _stmt.getLong(_columnIndexOfFechaEnvio)
          }
          _item =
              CircularEntity(_tmpIdCircular,_tmpInstitutionId,_tmpTitulo,_tmpContenido,_tmpContenidoWhatsapp,_tmpContenidoEmail,_tmpIdUsuarioCreador,_tmpFechaCreacion,_tmpFechaProgramacion,_tmpEstado,_tmpDestinatarios,_tmpArchivosAdjuntos,_tmpFechaEnvio)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLogsByNotification(notificationId: String):
      List<LogNotificacionEntity> {
    val _sql: String = "SELECT * FROM log_notificaciones WHERE idNotificacion = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, notificationId)
        val _columnIndexOfIdLog: Int = getColumnIndexOrThrow(_stmt, "idLog")
        val _columnIndexOfIdNotificacion: Int = getColumnIndexOrThrow(_stmt, "idNotificacion")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCanal: Int = getColumnIndexOrThrow(_stmt, "canal")
        val _columnIndexOfFechaIntento: Int = getColumnIndexOrThrow(_stmt, "fechaIntento")
        val _columnIndexOfCodigoRespuesta: Int = getColumnIndexOrThrow(_stmt, "codigoRespuesta")
        val _columnIndexOfMensajeRespuesta: Int = getColumnIndexOrThrow(_stmt, "mensajeRespuesta")
        val _columnIndexOfExito: Int = getColumnIndexOrThrow(_stmt, "exito")
        val _columnIndexOfIntentos: Int = getColumnIndexOrThrow(_stmt, "intentos")
        val _result: MutableList<LogNotificacionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: LogNotificacionEntity
          val _tmpIdLog: String
          _tmpIdLog = _stmt.getText(_columnIndexOfIdLog)
          val _tmpIdNotificacion: String
          _tmpIdNotificacion = _stmt.getText(_columnIndexOfIdNotificacion)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCanal: String
          _tmpCanal = _stmt.getText(_columnIndexOfCanal)
          val _tmpFechaIntento: Long
          _tmpFechaIntento = _stmt.getLong(_columnIndexOfFechaIntento)
          val _tmpCodigoRespuesta: Int?
          if (_stmt.isNull(_columnIndexOfCodigoRespuesta)) {
            _tmpCodigoRespuesta = null
          } else {
            _tmpCodigoRespuesta = _stmt.getLong(_columnIndexOfCodigoRespuesta).toInt()
          }
          val _tmpMensajeRespuesta: String?
          if (_stmt.isNull(_columnIndexOfMensajeRespuesta)) {
            _tmpMensajeRespuesta = null
          } else {
            _tmpMensajeRespuesta = _stmt.getText(_columnIndexOfMensajeRespuesta)
          }
          val _tmpExito: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfExito).toInt()
          _tmpExito = _tmp != 0
          val _tmpIntentos: Int
          _tmpIntentos = _stmt.getLong(_columnIndexOfIntentos).toInt()
          _item =
              LogNotificacionEntity(_tmpIdLog,_tmpIdNotificacion,_tmpInstitutionId,_tmpCanal,_tmpFechaIntento,_tmpCodigoRespuesta,_tmpMensajeRespuesta,_tmpExito,_tmpIntentos)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLogById(idLog: String): LogNotificacionEntity? {
    val _sql: String = "SELECT * FROM log_notificaciones WHERE idLog = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, idLog)
        val _columnIndexOfIdLog: Int = getColumnIndexOrThrow(_stmt, "idLog")
        val _columnIndexOfIdNotificacion: Int = getColumnIndexOrThrow(_stmt, "idNotificacion")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCanal: Int = getColumnIndexOrThrow(_stmt, "canal")
        val _columnIndexOfFechaIntento: Int = getColumnIndexOrThrow(_stmt, "fechaIntento")
        val _columnIndexOfCodigoRespuesta: Int = getColumnIndexOrThrow(_stmt, "codigoRespuesta")
        val _columnIndexOfMensajeRespuesta: Int = getColumnIndexOrThrow(_stmt, "mensajeRespuesta")
        val _columnIndexOfExito: Int = getColumnIndexOrThrow(_stmt, "exito")
        val _columnIndexOfIntentos: Int = getColumnIndexOrThrow(_stmt, "intentos")
        val _result: LogNotificacionEntity?
        if (_stmt.step()) {
          val _tmpIdLog: String
          _tmpIdLog = _stmt.getText(_columnIndexOfIdLog)
          val _tmpIdNotificacion: String
          _tmpIdNotificacion = _stmt.getText(_columnIndexOfIdNotificacion)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCanal: String
          _tmpCanal = _stmt.getText(_columnIndexOfCanal)
          val _tmpFechaIntento: Long
          _tmpFechaIntento = _stmt.getLong(_columnIndexOfFechaIntento)
          val _tmpCodigoRespuesta: Int?
          if (_stmt.isNull(_columnIndexOfCodigoRespuesta)) {
            _tmpCodigoRespuesta = null
          } else {
            _tmpCodigoRespuesta = _stmt.getLong(_columnIndexOfCodigoRespuesta).toInt()
          }
          val _tmpMensajeRespuesta: String?
          if (_stmt.isNull(_columnIndexOfMensajeRespuesta)) {
            _tmpMensajeRespuesta = null
          } else {
            _tmpMensajeRespuesta = _stmt.getText(_columnIndexOfMensajeRespuesta)
          }
          val _tmpExito: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfExito).toInt()
          _tmpExito = _tmp != 0
          val _tmpIntentos: Int
          _tmpIntentos = _stmt.getLong(_columnIndexOfIntentos).toInt()
          _result =
              LogNotificacionEntity(_tmpIdLog,_tmpIdNotificacion,_tmpInstitutionId,_tmpCanal,_tmpFechaIntento,_tmpCodigoRespuesta,_tmpMensajeRespuesta,_tmpExito,_tmpIntentos)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateCircularStatus(id: String, estado: String) {
    val _sql: String = "UPDATE circulares SET estado = ? WHERE idCircular = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, estado)
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateEmailStatus(id: String, estado: String) {
    val _sql: String =
        "UPDATE institutional_notificaciones SET estadoEnvioEmail = ? WHERE idNotificacion = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, estado)
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateWhatsappStatus(id: String, estado: String) {
    val _sql: String =
        "UPDATE institutional_notificaciones SET estadoEnvioWhatsapp = ? WHERE idNotificacion = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, estado)
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteOldLogs(threshold: Long): Int {
    val _sql: String = "DELETE FROM log_notificaciones WHERE fechaIntento < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, threshold)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

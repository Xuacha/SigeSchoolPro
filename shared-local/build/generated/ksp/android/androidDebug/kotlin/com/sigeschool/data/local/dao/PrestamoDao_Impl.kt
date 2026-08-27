package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PrestamoEntity
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
public class PrestamoDao_Impl(
  __db: RoomDatabase,
) : PrestamoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPrestamoEntity: EntityInsertAdapter<PrestamoEntity>

  private val __deleteAdapterOfPrestamoEntity: EntityDeleteOrUpdateAdapter<PrestamoEntity>

  private val __updateAdapterOfPrestamoEntity: EntityDeleteOrUpdateAdapter<PrestamoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPrestamoEntity = object : EntityInsertAdapter<PrestamoEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `library_prestamos` (`id`,`institutionId`,`libroId`,`estudianteId`,`docenteId`,`fechaPrestamo`,`fechaDevolucionPrevista`,`fechaDevolucionReal`,`estado`,`observaciones`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PrestamoEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.libroId)
        val _tmpEstudianteId: String? = entity.estudianteId
        if (_tmpEstudianteId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpEstudianteId)
        }
        val _tmpDocenteId: String? = entity.docenteId
        if (_tmpDocenteId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocenteId)
        }
        statement.bindLong(6, entity.fechaPrestamo)
        statement.bindLong(7, entity.fechaDevolucionPrevista)
        val _tmpFechaDevolucionReal: Long? = entity.fechaDevolucionReal
        if (_tmpFechaDevolucionReal == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpFechaDevolucionReal)
        }
        statement.bindText(9, entity.estado)
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpObservaciones)
        }
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
      }
    }
    this.__deleteAdapterOfPrestamoEntity = object : EntityDeleteOrUpdateAdapter<PrestamoEntity>() {
      protected override fun createQuery(): String =
          "DELETE FROM `library_prestamos` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PrestamoEntity) {
        statement.bindText(1, entity.id)
      }
    }
    this.__updateAdapterOfPrestamoEntity = object : EntityDeleteOrUpdateAdapter<PrestamoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `library_prestamos` SET `id` = ?,`institutionId` = ?,`libroId` = ?,`estudianteId` = ?,`docenteId` = ?,`fechaPrestamo` = ?,`fechaDevolucionPrevista` = ?,`fechaDevolucionReal` = ?,`estado` = ?,`observaciones` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PrestamoEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.libroId)
        val _tmpEstudianteId: String? = entity.estudianteId
        if (_tmpEstudianteId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpEstudianteId)
        }
        val _tmpDocenteId: String? = entity.docenteId
        if (_tmpDocenteId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDocenteId)
        }
        statement.bindLong(6, entity.fechaPrestamo)
        statement.bindLong(7, entity.fechaDevolucionPrevista)
        val _tmpFechaDevolucionReal: Long? = entity.fechaDevolucionReal
        if (_tmpFechaDevolucionReal == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpFechaDevolucionReal)
        }
        statement.bindText(9, entity.estado)
        val _tmpObservaciones: String? = entity.observaciones
        if (_tmpObservaciones == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpObservaciones)
        }
        statement.bindLong(11, entity.syncStatus.toLong())
        statement.bindLong(12, entity.lastModified)
        statement.bindText(13, entity.id)
      }
    }
  }

  public override suspend fun insert(prestamo: PrestamoEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfPrestamoEntity.insert(_connection, prestamo)
  }

  public override suspend fun delete(prestamo: PrestamoEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfPrestamoEntity.handle(_connection, prestamo)
  }

  public override suspend fun update(prestamo: PrestamoEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfPrestamoEntity.handle(_connection, prestamo)
  }

  public override fun getAll(instId: String): Flow<List<PrestamoEntity>> {
    val _sql: String = "SELECT * FROM library_prestamos WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("library_prestamos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfLibroId: Int = getColumnIndexOrThrow(_stmt, "libroId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaPrestamo: Int = getColumnIndexOrThrow(_stmt, "fechaPrestamo")
        val _columnIndexOfFechaDevolucionPrevista: Int = getColumnIndexOrThrow(_stmt,
            "fechaDevolucionPrevista")
        val _columnIndexOfFechaDevolucionReal: Int = getColumnIndexOrThrow(_stmt,
            "fechaDevolucionReal")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PrestamoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PrestamoEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpLibroId: String
          _tmpLibroId = _stmt.getText(_columnIndexOfLibroId)
          val _tmpEstudianteId: String?
          if (_stmt.isNull(_columnIndexOfEstudianteId)) {
            _tmpEstudianteId = null
          } else {
            _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          }
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaPrestamo: Long
          _tmpFechaPrestamo = _stmt.getLong(_columnIndexOfFechaPrestamo)
          val _tmpFechaDevolucionPrevista: Long
          _tmpFechaDevolucionPrevista = _stmt.getLong(_columnIndexOfFechaDevolucionPrevista)
          val _tmpFechaDevolucionReal: Long?
          if (_stmt.isNull(_columnIndexOfFechaDevolucionReal)) {
            _tmpFechaDevolucionReal = null
          } else {
            _tmpFechaDevolucionReal = _stmt.getLong(_columnIndexOfFechaDevolucionReal)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PrestamoEntity(_tmpId,_tmpInstitutionId,_tmpLibroId,_tmpEstudianteId,_tmpDocenteId,_tmpFechaPrestamo,_tmpFechaDevolucionPrevista,_tmpFechaDevolucionReal,_tmpEstado,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByStudent(studentId: String, instId: String): Flow<List<PrestamoEntity>> {
    val _sql: String =
        "SELECT * FROM library_prestamos WHERE estudianteId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("library_prestamos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfLibroId: Int = getColumnIndexOrThrow(_stmt, "libroId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaPrestamo: Int = getColumnIndexOrThrow(_stmt, "fechaPrestamo")
        val _columnIndexOfFechaDevolucionPrevista: Int = getColumnIndexOrThrow(_stmt,
            "fechaDevolucionPrevista")
        val _columnIndexOfFechaDevolucionReal: Int = getColumnIndexOrThrow(_stmt,
            "fechaDevolucionReal")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PrestamoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PrestamoEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpLibroId: String
          _tmpLibroId = _stmt.getText(_columnIndexOfLibroId)
          val _tmpEstudianteId: String?
          if (_stmt.isNull(_columnIndexOfEstudianteId)) {
            _tmpEstudianteId = null
          } else {
            _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          }
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaPrestamo: Long
          _tmpFechaPrestamo = _stmt.getLong(_columnIndexOfFechaPrestamo)
          val _tmpFechaDevolucionPrevista: Long
          _tmpFechaDevolucionPrevista = _stmt.getLong(_columnIndexOfFechaDevolucionPrevista)
          val _tmpFechaDevolucionReal: Long?
          if (_stmt.isNull(_columnIndexOfFechaDevolucionReal)) {
            _tmpFechaDevolucionReal = null
          } else {
            _tmpFechaDevolucionReal = _stmt.getLong(_columnIndexOfFechaDevolucionReal)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PrestamoEntity(_tmpId,_tmpInstitutionId,_tmpLibroId,_tmpEstudianteId,_tmpDocenteId,_tmpFechaPrestamo,_tmpFechaDevolucionPrevista,_tmpFechaDevolucionReal,_tmpEstado,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAtrasados(currentTime: Long, instId: String): Flow<List<PrestamoEntity>> {
    val _sql: String =
        "SELECT * FROM library_prestamos WHERE estado = 'ACTIVO' AND fechaDevolucionPrevista < ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("library_prestamos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, currentTime)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfLibroId: Int = getColumnIndexOrThrow(_stmt, "libroId")
        val _columnIndexOfEstudianteId: Int = getColumnIndexOrThrow(_stmt, "estudianteId")
        val _columnIndexOfDocenteId: Int = getColumnIndexOrThrow(_stmt, "docenteId")
        val _columnIndexOfFechaPrestamo: Int = getColumnIndexOrThrow(_stmt, "fechaPrestamo")
        val _columnIndexOfFechaDevolucionPrevista: Int = getColumnIndexOrThrow(_stmt,
            "fechaDevolucionPrevista")
        val _columnIndexOfFechaDevolucionReal: Int = getColumnIndexOrThrow(_stmt,
            "fechaDevolucionReal")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfObservaciones: Int = getColumnIndexOrThrow(_stmt, "observaciones")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PrestamoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PrestamoEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpLibroId: String
          _tmpLibroId = _stmt.getText(_columnIndexOfLibroId)
          val _tmpEstudianteId: String?
          if (_stmt.isNull(_columnIndexOfEstudianteId)) {
            _tmpEstudianteId = null
          } else {
            _tmpEstudianteId = _stmt.getText(_columnIndexOfEstudianteId)
          }
          val _tmpDocenteId: String?
          if (_stmt.isNull(_columnIndexOfDocenteId)) {
            _tmpDocenteId = null
          } else {
            _tmpDocenteId = _stmt.getText(_columnIndexOfDocenteId)
          }
          val _tmpFechaPrestamo: Long
          _tmpFechaPrestamo = _stmt.getLong(_columnIndexOfFechaPrestamo)
          val _tmpFechaDevolucionPrevista: Long
          _tmpFechaDevolucionPrevista = _stmt.getLong(_columnIndexOfFechaDevolucionPrevista)
          val _tmpFechaDevolucionReal: Long?
          if (_stmt.isNull(_columnIndexOfFechaDevolucionReal)) {
            _tmpFechaDevolucionReal = null
          } else {
            _tmpFechaDevolucionReal = _stmt.getLong(_columnIndexOfFechaDevolucionReal)
          }
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpObservaciones: String?
          if (_stmt.isNull(_columnIndexOfObservaciones)) {
            _tmpObservaciones = null
          } else {
            _tmpObservaciones = _stmt.getText(_columnIndexOfObservaciones)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PrestamoEntity(_tmpId,_tmpInstitutionId,_tmpLibroId,_tmpEstudianteId,_tmpDocenteId,_tmpFechaPrestamo,_tmpFechaDevolucionPrevista,_tmpFechaDevolucionReal,_tmpEstado,_tmpObservaciones,_tmpSyncStatus,_tmpLastModified)
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

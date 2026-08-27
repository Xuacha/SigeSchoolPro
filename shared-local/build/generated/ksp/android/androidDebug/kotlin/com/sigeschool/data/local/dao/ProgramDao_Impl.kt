package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ProgramEntity
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
public class ProgramDao_Impl(
  __db: RoomDatabase,
) : ProgramDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfProgramEntity: EntityInsertAdapter<ProgramEntity>

  private val __deleteAdapterOfProgramEntity: EntityDeleteOrUpdateAdapter<ProgramEntity>

  private val __updateAdapterOfProgramEntity: EntityDeleteOrUpdateAdapter<ProgramEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfProgramEntity = object : EntityInsertAdapter<ProgramEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `programs` (`id`,`institutionId`,`codigo`,`name`,`description`,`nivelEducativoId`,`gradoId`,`activo`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProgramEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.codigo)
        statement.bindText(4, entity.name)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDescription)
        }
        val _tmpNivelEducativoId: Long? = entity.nivelEducativoId
        if (_tmpNivelEducativoId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpNivelEducativoId)
        }
        val _tmpGradoId: Long? = entity.gradoId
        if (_tmpGradoId == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpGradoId)
        }
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__deleteAdapterOfProgramEntity = object : EntityDeleteOrUpdateAdapter<ProgramEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `programs` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProgramEntity) {
        statement.bindText(1, entity.id)
      }
    }
    this.__updateAdapterOfProgramEntity = object : EntityDeleteOrUpdateAdapter<ProgramEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `programs` SET `id` = ?,`institutionId` = ?,`codigo` = ?,`name` = ?,`description` = ?,`nivelEducativoId` = ?,`gradoId` = ?,`activo` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProgramEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.codigo)
        statement.bindText(4, entity.name)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDescription)
        }
        val _tmpNivelEducativoId: Long? = entity.nivelEducativoId
        if (_tmpNivelEducativoId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpNivelEducativoId)
        }
        val _tmpGradoId: Long? = entity.gradoId
        if (_tmpGradoId == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpGradoId)
        }
        val _tmp: Int = if (entity.activo) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
        statement.bindText(11, entity.id)
      }
    }
  }

  public override suspend fun insertProgram(program: ProgramEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfProgramEntity.insertAndReturnId(_connection, program)
    _result
  }

  public override suspend fun deleteProgram(program: ProgramEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfProgramEntity.handle(_connection, program)
  }

  public override suspend fun updateProgram(program: ProgramEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfProgramEntity.handle(_connection, program)
  }

  public override fun getProgramsByInstitution(institutionId: String): Flow<List<ProgramEntity>> {
    val _sql: String = "SELECT * FROM programs WHERE institutionId = ? AND activo = 1"
    return createFlow(__db, false, arrayOf("programs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ProgramEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProgramEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCodigo: String
          _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          val _tmpNivelEducativoId: Long?
          if (_stmt.isNull(_columnIndexOfNivelEducativoId)) {
            _tmpNivelEducativoId = null
          } else {
            _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          }
          val _tmpGradoId: Long?
          if (_stmt.isNull(_columnIndexOfGradoId)) {
            _tmpGradoId = null
          } else {
            _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          }
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ProgramEntity(_tmpId,_tmpInstitutionId,_tmpCodigo,_tmpName,_tmpDescription,_tmpNivelEducativoId,_tmpGradoId,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProgramById(id: String, institutionId: String): ProgramEntity? {
    val _sql: String = "SELECT * FROM programs WHERE id = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ProgramEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCodigo: String
          _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          val _tmpNivelEducativoId: Long?
          if (_stmt.isNull(_columnIndexOfNivelEducativoId)) {
            _tmpNivelEducativoId = null
          } else {
            _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          }
          val _tmpGradoId: Long?
          if (_stmt.isNull(_columnIndexOfGradoId)) {
            _tmpGradoId = null
          } else {
            _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          }
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ProgramEntity(_tmpId,_tmpInstitutionId,_tmpCodigo,_tmpName,_tmpDescription,_tmpNivelEducativoId,_tmpGradoId,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByCodigo(codigo: String, institutionId: String): ProgramEntity? {
    val _sql: String = "SELECT * FROM programs WHERE codigo = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, codigo)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ProgramEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCodigo: String
          _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          val _tmpNivelEducativoId: Long?
          if (_stmt.isNull(_columnIndexOfNivelEducativoId)) {
            _tmpNivelEducativoId = null
          } else {
            _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          }
          val _tmpGradoId: Long?
          if (_stmt.isNull(_columnIndexOfGradoId)) {
            _tmpGradoId = null
          } else {
            _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          }
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ProgramEntity(_tmpId,_tmpInstitutionId,_tmpCodigo,_tmpName,_tmpDescription,_tmpNivelEducativoId,_tmpGradoId,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByGrado(gradoId: Long, institutionId: String): ProgramEntity? {
    val _sql: String = "SELECT * FROM programs WHERE gradoId = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, gradoId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCodigo: Int = getColumnIndexOrThrow(_stmt, "codigo")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfNivelEducativoId: Int = getColumnIndexOrThrow(_stmt, "nivelEducativoId")
        val _columnIndexOfGradoId: Int = getColumnIndexOrThrow(_stmt, "gradoId")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ProgramEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCodigo: String
          _tmpCodigo = _stmt.getText(_columnIndexOfCodigo)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          val _tmpNivelEducativoId: Long?
          if (_stmt.isNull(_columnIndexOfNivelEducativoId)) {
            _tmpNivelEducativoId = null
          } else {
            _tmpNivelEducativoId = _stmt.getLong(_columnIndexOfNivelEducativoId)
          }
          val _tmpGradoId: Long?
          if (_stmt.isNull(_columnIndexOfGradoId)) {
            _tmpGradoId = null
          } else {
            _tmpGradoId = _stmt.getLong(_columnIndexOfGradoId)
          }
          val _tmpActivo: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ProgramEntity(_tmpId,_tmpInstitutionId,_tmpCodigo,_tmpName,_tmpDescription,_tmpNivelEducativoId,_tmpGradoId,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
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

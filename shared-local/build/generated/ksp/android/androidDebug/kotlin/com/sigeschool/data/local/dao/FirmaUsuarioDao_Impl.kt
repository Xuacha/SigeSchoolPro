package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.FirmaUsuarioEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FirmaUsuarioDao_Impl(
  __db: RoomDatabase,
) : FirmaUsuarioDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfFirmaUsuarioEntity: EntityInsertAdapter<FirmaUsuarioEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfFirmaUsuarioEntity = object : EntityInsertAdapter<FirmaUsuarioEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `firmas_usuarios` (`userId`,`institutionId`,`firmaPath`,`fechaGuardado`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FirmaUsuarioEntity) {
        statement.bindText(1, entity.userId)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.firmaPath)
        statement.bindLong(4, entity.fechaGuardado)
        statement.bindLong(5, entity.syncStatus.toLong())
        statement.bindLong(6, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(firma: FirmaUsuarioEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfFirmaUsuarioEntity.insert(_connection, firma)
  }

  public override suspend fun getByUsuario(userId: String, instId: String): FirmaUsuarioEntity? {
    val _sql: String =
        "SELECT * FROM firmas_usuarios WHERE userId = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFirmaPath: Int = getColumnIndexOrThrow(_stmt, "firmaPath")
        val _columnIndexOfFechaGuardado: Int = getColumnIndexOrThrow(_stmt, "fechaGuardado")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: FirmaUsuarioEntity?
        if (_stmt.step()) {
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFirmaPath: String
          _tmpFirmaPath = _stmt.getText(_columnIndexOfFirmaPath)
          val _tmpFechaGuardado: Long
          _tmpFechaGuardado = _stmt.getLong(_columnIndexOfFechaGuardado)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              FirmaUsuarioEntity(_tmpUserId,_tmpInstitutionId,_tmpFirmaPath,_tmpFechaGuardado,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByUsuario(userId: String, instId: String) {
    val _sql: String = "DELETE FROM firmas_usuarios WHERE userId = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
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

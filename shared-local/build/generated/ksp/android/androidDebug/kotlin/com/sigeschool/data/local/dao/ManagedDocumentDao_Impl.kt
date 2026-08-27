package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ManagedDocumentEntity
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
public class ManagedDocumentDao_Impl(
  __db: RoomDatabase,
) : ManagedDocumentDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfManagedDocumentEntity: EntityInsertAdapter<ManagedDocumentEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfManagedDocumentEntity = object :
        EntityInsertAdapter<ManagedDocumentEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `managed_documents` (`id`,`institutionId`,`type`,`content`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ManagedDocumentEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.type)
        statement.bindText(4, entity.content)
        statement.bindLong(5, entity.syncStatus.toLong())
        statement.bindLong(6, entity.lastModified)
      }
    }
  }

  public override suspend fun insertDocument(document: ManagedDocumentEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfManagedDocumentEntity.insert(_connection, document)
  }

  public override fun getDocumentByType(institutionId: String, type: String):
      Flow<ManagedDocumentEntity?> {
    val _sql: String =
        "SELECT * FROM managed_documents WHERE institutionId = ? AND type = ? LIMIT 1"
    return createFlow(__db, false, arrayOf("managed_documents")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, type)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: ManagedDocumentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              ManagedDocumentEntity(_tmpId,_tmpInstitutionId,_tmpType,_tmpContent,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllDocuments(institutionId: String): Flow<List<ManagedDocumentEntity>> {
    val _sql: String = "SELECT * FROM managed_documents WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("managed_documents")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<ManagedDocumentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ManagedDocumentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              ManagedDocumentEntity(_tmpId,_tmpInstitutionId,_tmpType,_tmpContent,_tmpSyncStatus,_tmpLastModified)
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

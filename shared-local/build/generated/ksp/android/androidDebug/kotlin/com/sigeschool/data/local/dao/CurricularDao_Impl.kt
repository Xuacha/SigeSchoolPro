package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.BlockHistoryEntity
import com.sigeschool.`data`.local.entity.DocumentBlockEntity
import com.sigeschool.`data`.local.entity.InstitutionalDocumentEntity
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
public class CurricularDao_Impl(
  __db: RoomDatabase,
) : CurricularDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfInstitutionalDocumentEntity:
      EntityInsertAdapter<InstitutionalDocumentEntity>

  private val __insertAdapterOfDocumentBlockEntity: EntityInsertAdapter<DocumentBlockEntity>

  private val __insertAdapterOfBlockHistoryEntity: EntityInsertAdapter<BlockHistoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfInstitutionalDocumentEntity = object :
        EntityInsertAdapter<InstitutionalDocumentEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `institutional_documents` (`id`,`title`,`type`,`institutionId`,`grade`,`subject`,`teacherId`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: InstitutionalDocumentEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.type)
        statement.bindText(4, entity.institutionId)
        val _tmpGrade: String? = entity.grade
        if (_tmpGrade == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpGrade)
        }
        val _tmpSubject: String? = entity.subject
        if (_tmpSubject == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpSubject)
        }
        val _tmpTeacherId: String? = entity.teacherId
        if (_tmpTeacherId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpTeacherId)
        }
        statement.bindLong(8, entity.createdAt)
        statement.bindLong(9, entity.updatedAt)
      }
    }
    this.__insertAdapterOfDocumentBlockEntity = object : EntityInsertAdapter<DocumentBlockEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `document_blocks` (`id`,`documentId`,`orderIndex`,`title`,`contentHtml`,`updatedAt`,`modifiedBy`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DocumentBlockEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.documentId)
        statement.bindLong(3, entity.orderIndex.toLong())
        statement.bindText(4, entity.title)
        statement.bindText(5, entity.contentHtml)
        statement.bindLong(6, entity.updatedAt)
        statement.bindText(7, entity.modifiedBy)
      }
    }
    this.__insertAdapterOfBlockHistoryEntity = object : EntityInsertAdapter<BlockHistoryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `block_history` (`id`,`blockId`,`contentHtml`,`modifiedAt`,`modifiedBy`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BlockHistoryEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.blockId)
        statement.bindText(3, entity.contentHtml)
        statement.bindLong(4, entity.modifiedAt)
        statement.bindText(5, entity.modifiedBy)
      }
    }
  }

  public override suspend fun insertDocument(document: InstitutionalDocumentEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfInstitutionalDocumentEntity.insert(_connection, document)
  }

  public override suspend fun insertBlocks(blocks: List<DocumentBlockEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfDocumentBlockEntity.insert(_connection, blocks)
  }

  public override suspend fun insertHistory(history: BlockHistoryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBlockHistoryEntity.insert(_connection, history)
  }

  public override fun getDocumentsByType(type: String): Flow<List<InstitutionalDocumentEntity>> {
    val _sql: String = "SELECT * FROM institutional_documents WHERE type = ?"
    return createFlow(__db, false, arrayOf("institutional_documents")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, type)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfSubject: Int = getColumnIndexOrThrow(_stmt, "subject")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: MutableList<InstitutionalDocumentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: InstitutionalDocumentEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGrade: String?
          if (_stmt.isNull(_columnIndexOfGrade)) {
            _tmpGrade = null
          } else {
            _tmpGrade = _stmt.getText(_columnIndexOfGrade)
          }
          val _tmpSubject: String?
          if (_stmt.isNull(_columnIndexOfSubject)) {
            _tmpSubject = null
          } else {
            _tmpSubject = _stmt.getText(_columnIndexOfSubject)
          }
          val _tmpTeacherId: String?
          if (_stmt.isNull(_columnIndexOfTeacherId)) {
            _tmpTeacherId = null
          } else {
            _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item =
              InstitutionalDocumentEntity(_tmpId,_tmpTitle,_tmpType,_tmpInstitutionId,_tmpGrade,_tmpSubject,_tmpTeacherId,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDocumentById(documentId: String): InstitutionalDocumentEntity? {
    val _sql: String = "SELECT * FROM institutional_documents WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, documentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfSubject: Int = getColumnIndexOrThrow(_stmt, "subject")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: InstitutionalDocumentEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpGrade: String?
          if (_stmt.isNull(_columnIndexOfGrade)) {
            _tmpGrade = null
          } else {
            _tmpGrade = _stmt.getText(_columnIndexOfGrade)
          }
          val _tmpSubject: String?
          if (_stmt.isNull(_columnIndexOfSubject)) {
            _tmpSubject = null
          } else {
            _tmpSubject = _stmt.getText(_columnIndexOfSubject)
          }
          val _tmpTeacherId: String?
          if (_stmt.isNull(_columnIndexOfTeacherId)) {
            _tmpTeacherId = null
          } else {
            _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result =
              InstitutionalDocumentEntity(_tmpId,_tmpTitle,_tmpType,_tmpInstitutionId,_tmpGrade,_tmpSubject,_tmpTeacherId,_tmpCreatedAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getBlocksByDocumentId(documentId: String): Flow<List<DocumentBlockEntity>> {
    val _sql: String = "SELECT * FROM document_blocks WHERE documentId = ? ORDER BY orderIndex ASC"
    return createFlow(__db, false, arrayOf("document_blocks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, documentId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDocumentId: Int = getColumnIndexOrThrow(_stmt, "documentId")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfContentHtml: Int = getColumnIndexOrThrow(_stmt, "contentHtml")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _columnIndexOfModifiedBy: Int = getColumnIndexOrThrow(_stmt, "modifiedBy")
        val _result: MutableList<DocumentBlockEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DocumentBlockEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDocumentId: String
          _tmpDocumentId = _stmt.getText(_columnIndexOfDocumentId)
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpContentHtml: String
          _tmpContentHtml = _stmt.getText(_columnIndexOfContentHtml)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpModifiedBy: String
          _tmpModifiedBy = _stmt.getText(_columnIndexOfModifiedBy)
          _item =
              DocumentBlockEntity(_tmpId,_tmpDocumentId,_tmpOrderIndex,_tmpTitle,_tmpContentHtml,_tmpUpdatedAt,_tmpModifiedBy)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getHistoryByBlockId(blockId: String): Flow<List<BlockHistoryEntity>> {
    val _sql: String = "SELECT * FROM block_history WHERE blockId = ? ORDER BY modifiedAt DESC"
    return createFlow(__db, false, arrayOf("block_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, blockId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfBlockId: Int = getColumnIndexOrThrow(_stmt, "blockId")
        val _columnIndexOfContentHtml: Int = getColumnIndexOrThrow(_stmt, "contentHtml")
        val _columnIndexOfModifiedAt: Int = getColumnIndexOrThrow(_stmt, "modifiedAt")
        val _columnIndexOfModifiedBy: Int = getColumnIndexOrThrow(_stmt, "modifiedBy")
        val _result: MutableList<BlockHistoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BlockHistoryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpBlockId: String
          _tmpBlockId = _stmt.getText(_columnIndexOfBlockId)
          val _tmpContentHtml: String
          _tmpContentHtml = _stmt.getText(_columnIndexOfContentHtml)
          val _tmpModifiedAt: Long
          _tmpModifiedAt = _stmt.getLong(_columnIndexOfModifiedAt)
          val _tmpModifiedBy: String
          _tmpModifiedBy = _stmt.getText(_columnIndexOfModifiedBy)
          _item =
              BlockHistoryEntity(_tmpId,_tmpBlockId,_tmpContentHtml,_tmpModifiedAt,_tmpModifiedBy)
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

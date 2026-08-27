package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AuditEntryEntity
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AuditDao_Impl(
  __db: RoomDatabase,
) : AuditDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAuditEntryEntity: EntityInsertAdapter<AuditEntryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAuditEntryEntity = object : EntityInsertAdapter<AuditEntryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `audit_ledger` (`ledgerIndex`,`previousHash`,`timestamp`,`data`,`nonce`,`hash`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AuditEntryEntity) {
        statement.bindLong(1, entity.ledgerIndex)
        statement.bindText(2, entity.previousHash)
        statement.bindLong(3, entity.timestamp)
        statement.bindText(4, entity.data)
        statement.bindText(5, entity.nonce)
        statement.bindText(6, entity.hash)
      }
    }
  }

  public override suspend fun insertEntry(entry: AuditEntryEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfAuditEntryEntity.insert(_connection, entry)
  }

  public override suspend fun getAllEntries(): List<AuditEntryEntity> {
    val _sql: String = "SELECT * FROM audit_ledger ORDER BY ledgerIndex ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfLedgerIndex: Int = getColumnIndexOrThrow(_stmt, "ledgerIndex")
        val _columnIndexOfPreviousHash: Int = getColumnIndexOrThrow(_stmt, "previousHash")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfData: Int = getColumnIndexOrThrow(_stmt, "data")
        val _columnIndexOfNonce: Int = getColumnIndexOrThrow(_stmt, "nonce")
        val _columnIndexOfHash: Int = getColumnIndexOrThrow(_stmt, "hash")
        val _result: MutableList<AuditEntryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AuditEntryEntity
          val _tmpLedgerIndex: Long
          _tmpLedgerIndex = _stmt.getLong(_columnIndexOfLedgerIndex)
          val _tmpPreviousHash: String
          _tmpPreviousHash = _stmt.getText(_columnIndexOfPreviousHash)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpData: String
          _tmpData = _stmt.getText(_columnIndexOfData)
          val _tmpNonce: String
          _tmpNonce = _stmt.getText(_columnIndexOfNonce)
          val _tmpHash: String
          _tmpHash = _stmt.getText(_columnIndexOfHash)
          _item =
              AuditEntryEntity(_tmpLedgerIndex,_tmpPreviousHash,_tmpTimestamp,_tmpData,_tmpNonce,_tmpHash)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLastIndex(): Long? {
    val _sql: String = "SELECT MAX(ledgerIndex) FROM audit_ledger"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Long?
        if (_stmt.step()) {
          val _tmp: Long?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0)
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLastHash(): String? {
    val _sql: String = "SELECT hash FROM audit_ledger ORDER BY ledgerIndex DESC LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: String?
        if (_stmt.step()) {
          if (_stmt.isNull(0)) {
            _result = null
          } else {
            _result = _stmt.getText(0)
          }
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

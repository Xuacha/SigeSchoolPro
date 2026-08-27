package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PucAccountEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
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
public class PucAccountDao_Impl(
  __db: RoomDatabase,
) : PucAccountDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPucAccountEntity: EntityInsertAdapter<PucAccountEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPucAccountEntity = object : EntityInsertAdapter<PucAccountEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `puc_accounts` (`id`,`code`,`name`,`level`,`parentCode`,`accountType`,`institutionId`,`isCustom`,`isActive`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PucAccountEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.code)
        statement.bindText(3, entity.name)
        statement.bindLong(4, entity.level.toLong())
        val _tmpParentCode: String? = entity.parentCode
        if (_tmpParentCode == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpParentCode)
        }
        statement.bindText(6, entity.accountType)
        statement.bindText(7, entity.institutionId)
        val _tmp: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        val _tmp_1: Int = if (entity.isActive) 1 else 0
        statement.bindLong(9, _tmp_1.toLong())
      }
    }
  }

  public override suspend fun insert(accounts: List<PucAccountEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPucAccountEntity.insert(_connection, accounts)
  }

  public override fun getAllByInstitution(institutionId: String): Flow<List<PucAccountEntity>> {
    val _sql: String = "SELECT * FROM puc_accounts WHERE institutionId = ? ORDER BY code"
    return createFlow(__db, false, arrayOf("puc_accounts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCode: Int = getColumnIndexOrThrow(_stmt, "code")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _columnIndexOfParentCode: Int = getColumnIndexOrThrow(_stmt, "parentCode")
        val _columnIndexOfAccountType: Int = getColumnIndexOrThrow(_stmt, "accountType")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<PucAccountEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PucAccountEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCode: String
          _tmpCode = _stmt.getText(_columnIndexOfCode)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLevel: Int
          _tmpLevel = _stmt.getLong(_columnIndexOfLevel).toInt()
          val _tmpParentCode: String?
          if (_stmt.isNull(_columnIndexOfParentCode)) {
            _tmpParentCode = null
          } else {
            _tmpParentCode = _stmt.getText(_columnIndexOfParentCode)
          }
          val _tmpAccountType: String
          _tmpAccountType = _stmt.getText(_columnIndexOfAccountType)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpIsCustom: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          _item =
              PucAccountEntity(_tmpId,_tmpCode,_tmpName,_tmpLevel,_tmpParentCode,_tmpAccountType,_tmpInstitutionId,_tmpIsCustom,_tmpIsActive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByCode(code: String, institutionId: String): PucAccountEntity? {
    val _sql: String = "SELECT * FROM puc_accounts WHERE code = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, code)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCode: Int = getColumnIndexOrThrow(_stmt, "code")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _columnIndexOfParentCode: Int = getColumnIndexOrThrow(_stmt, "parentCode")
        val _columnIndexOfAccountType: Int = getColumnIndexOrThrow(_stmt, "accountType")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: PucAccountEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCode: String
          _tmpCode = _stmt.getText(_columnIndexOfCode)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLevel: Int
          _tmpLevel = _stmt.getLong(_columnIndexOfLevel).toInt()
          val _tmpParentCode: String?
          if (_stmt.isNull(_columnIndexOfParentCode)) {
            _tmpParentCode = null
          } else {
            _tmpParentCode = _stmt.getText(_columnIndexOfParentCode)
          }
          val _tmpAccountType: String
          _tmpAccountType = _stmt.getText(_columnIndexOfAccountType)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpIsCustom: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          _result =
              PucAccountEntity(_tmpId,_tmpCode,_tmpName,_tmpLevel,_tmpParentCode,_tmpAccountType,_tmpInstitutionId,_tmpIsCustom,_tmpIsActive)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllByInstitution(institutionId: String) {
    val _sql: String = "DELETE FROM puc_accounts WHERE institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
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

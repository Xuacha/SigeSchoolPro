package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.EmployeeEntity
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
public class EmployeeDao_Impl(
  __db: RoomDatabase,
) : EmployeeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEmployeeEntity: EntityInsertAdapter<EmployeeEntity>

  private val __deleteAdapterOfEmployeeEntity: EntityDeleteOrUpdateAdapter<EmployeeEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfEmployeeEntity = object : EntityInsertAdapter<EmployeeEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `employees` (`id`,`authUserId`,`institutionId`,`firstName`,`lastName`,`role`,`email`,`createdAt`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: EmployeeEntity) {
        statement.bindText(1, entity.id)
        val _tmpAuthUserId: String? = entity.authUserId
        if (_tmpAuthUserId == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpAuthUserId)
        }
        statement.bindText(3, entity.institutionId)
        statement.bindText(4, entity.firstName)
        statement.bindText(5, entity.lastName)
        statement.bindText(6, entity.role)
        statement.bindText(7, entity.email)
        statement.bindLong(8, entity.createdAt)
        statement.bindLong(9, entity.syncStatus.toLong())
        statement.bindLong(10, entity.lastModified)
      }
    }
    this.__deleteAdapterOfEmployeeEntity = object : EntityDeleteOrUpdateAdapter<EmployeeEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `employees` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: EmployeeEntity) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertEmployee(employee: EmployeeEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfEmployeeEntity.insert(_connection, employee)
  }

  public override suspend fun deleteEmployee(employee: EmployeeEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfEmployeeEntity.handle(_connection, employee)
  }

  public override fun getEmployeesByInstitution(institutionId: String): Flow<List<EmployeeEntity>> {
    val _sql: String = "SELECT * FROM employees WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("employees")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAuthUserId: Int = getColumnIndexOrThrow(_stmt, "authUserId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<EmployeeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EmployeeEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAuthUserId: String?
          if (_stmt.isNull(_columnIndexOfAuthUserId)) {
            _tmpAuthUserId = null
          } else {
            _tmpAuthUserId = _stmt.getText(_columnIndexOfAuthUserId)
          }
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              EmployeeEntity(_tmpId,_tmpAuthUserId,_tmpInstitutionId,_tmpFirstName,_tmpLastName,_tmpRole,_tmpEmail,_tmpCreatedAt,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEmployeeByAuthId(authUserId: String, institutionId: String):
      EmployeeEntity? {
    val _sql: String = "SELECT * FROM employees WHERE authUserId = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, authUserId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAuthUserId: Int = getColumnIndexOrThrow(_stmt, "authUserId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: EmployeeEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAuthUserId: String?
          if (_stmt.isNull(_columnIndexOfAuthUserId)) {
            _tmpAuthUserId = null
          } else {
            _tmpAuthUserId = _stmt.getText(_columnIndexOfAuthUserId)
          }
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              EmployeeEntity(_tmpId,_tmpAuthUserId,_tmpInstitutionId,_tmpFirstName,_tmpLastName,_tmpRole,_tmpEmail,_tmpCreatedAt,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSyncEmployees(institutionId: String): List<EmployeeEntity> {
    val _sql: String = "SELECT * FROM employees WHERE syncStatus != 0 AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAuthUserId: Int = getColumnIndexOrThrow(_stmt, "authUserId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<EmployeeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EmployeeEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAuthUserId: String?
          if (_stmt.isNull(_columnIndexOfAuthUserId)) {
            _tmpAuthUserId = null
          } else {
            _tmpAuthUserId = _stmt.getText(_columnIndexOfAuthUserId)
          }
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              EmployeeEntity(_tmpId,_tmpAuthUserId,_tmpInstitutionId,_tmpFirstName,_tmpLastName,_tmpRole,_tmpEmail,_tmpCreatedAt,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, institutionId: String) {
    val _sql: String = "UPDATE employees SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteEmployeeById(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM employees WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
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

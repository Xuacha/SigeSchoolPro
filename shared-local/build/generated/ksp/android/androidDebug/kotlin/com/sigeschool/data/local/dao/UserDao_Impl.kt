package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.UserEntity
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
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUserEntity: EntityInsertAdapter<UserEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfUserEntity = object : EntityInsertAdapter<UserEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `users` (`id`,`institutionId`,`username`,`password`,`role`,`fullName`,`email`,`profilePictureUri`,`fcmToken`,`isFirstLogin`,`isActive`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.username)
        statement.bindText(4, entity.password)
        statement.bindText(5, entity.role)
        statement.bindText(6, entity.fullName)
        val _tmpEmail: String? = entity.email
        if (_tmpEmail == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpEmail)
        }
        val _tmpProfilePictureUri: String? = entity.profilePictureUri
        if (_tmpProfilePictureUri == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpProfilePictureUri)
        }
        val _tmpFcmToken: String? = entity.fcmToken
        if (_tmpFcmToken == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpFcmToken)
        }
        val _tmp: Int = if (entity.isFirstLogin) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmp_1: Int = if (entity.isActive) 1 else 0
        statement.bindLong(11, _tmp_1.toLong())
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
      }
    }
  }

  public override suspend fun insertUser(user: UserEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfUserEntity.insert(_connection, user)
  }

  public override suspend fun getUserByIdSync(id: String): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfProfilePictureUri: Int = getColumnIndexOrThrow(_stmt, "profilePictureUri")
        val _columnIndexOfFcmToken: Int = getColumnIndexOrThrow(_stmt, "fcmToken")
        val _columnIndexOfIsFirstLogin: Int = getColumnIndexOrThrow(_stmt, "isFirstLogin")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpProfilePictureUri: String?
          if (_stmt.isNull(_columnIndexOfProfilePictureUri)) {
            _tmpProfilePictureUri = null
          } else {
            _tmpProfilePictureUri = _stmt.getText(_columnIndexOfProfilePictureUri)
          }
          val _tmpFcmToken: String?
          if (_stmt.isNull(_columnIndexOfFcmToken)) {
            _tmpFcmToken = null
          } else {
            _tmpFcmToken = _stmt.getText(_columnIndexOfFcmToken)
          }
          val _tmpIsFirstLogin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFirstLogin).toInt()
          _tmpIsFirstLogin = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              UserEntity(_tmpId,_tmpInstitutionId,_tmpUsername,_tmpPassword,_tmpRole,_tmpFullName,_tmpEmail,_tmpProfilePictureUri,_tmpFcmToken,_tmpIsFirstLogin,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserByUsername(username: String, institutionId: String):
      UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE username = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, username)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfProfilePictureUri: Int = getColumnIndexOrThrow(_stmt, "profilePictureUri")
        val _columnIndexOfFcmToken: Int = getColumnIndexOrThrow(_stmt, "fcmToken")
        val _columnIndexOfIsFirstLogin: Int = getColumnIndexOrThrow(_stmt, "isFirstLogin")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpProfilePictureUri: String?
          if (_stmt.isNull(_columnIndexOfProfilePictureUri)) {
            _tmpProfilePictureUri = null
          } else {
            _tmpProfilePictureUri = _stmt.getText(_columnIndexOfProfilePictureUri)
          }
          val _tmpFcmToken: String?
          if (_stmt.isNull(_columnIndexOfFcmToken)) {
            _tmpFcmToken = null
          } else {
            _tmpFcmToken = _stmt.getText(_columnIndexOfFcmToken)
          }
          val _tmpIsFirstLogin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFirstLogin).toInt()
          _tmpIsFirstLogin = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              UserEntity(_tmpId,_tmpInstitutionId,_tmpUsername,_tmpPassword,_tmpRole,_tmpFullName,_tmpEmail,_tmpProfilePictureUri,_tmpFcmToken,_tmpIsFirstLogin,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserCount(institutionId: String): Int {
    val _sql: String = "SELECT COUNT(*) FROM users WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findByEmailGlobal(email: String): List<UserEntity> {
    val _sql: String = "SELECT * FROM users WHERE username = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfProfilePictureUri: Int = getColumnIndexOrThrow(_stmt, "profilePictureUri")
        val _columnIndexOfFcmToken: Int = getColumnIndexOrThrow(_stmt, "fcmToken")
        val _columnIndexOfIsFirstLogin: Int = getColumnIndexOrThrow(_stmt, "isFirstLogin")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<UserEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpProfilePictureUri: String?
          if (_stmt.isNull(_columnIndexOfProfilePictureUri)) {
            _tmpProfilePictureUri = null
          } else {
            _tmpProfilePictureUri = _stmt.getText(_columnIndexOfProfilePictureUri)
          }
          val _tmpFcmToken: String?
          if (_stmt.isNull(_columnIndexOfFcmToken)) {
            _tmpFcmToken = null
          } else {
            _tmpFcmToken = _stmt.getText(_columnIndexOfFcmToken)
          }
          val _tmpIsFirstLogin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFirstLogin).toInt()
          _tmpIsFirstLogin = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              UserEntity(_tmpId,_tmpInstitutionId,_tmpUsername,_tmpPassword,_tmpRole,_tmpFullName,_tmpEmail,_tmpProfilePictureUri,_tmpFcmToken,_tmpIsFirstLogin,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserById(userId: String): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfProfilePictureUri: Int = getColumnIndexOrThrow(_stmt, "profilePictureUri")
        val _columnIndexOfFcmToken: Int = getColumnIndexOrThrow(_stmt, "fcmToken")
        val _columnIndexOfIsFirstLogin: Int = getColumnIndexOrThrow(_stmt, "isFirstLogin")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpProfilePictureUri: String?
          if (_stmt.isNull(_columnIndexOfProfilePictureUri)) {
            _tmpProfilePictureUri = null
          } else {
            _tmpProfilePictureUri = _stmt.getText(_columnIndexOfProfilePictureUri)
          }
          val _tmpFcmToken: String?
          if (_stmt.isNull(_columnIndexOfFcmToken)) {
            _tmpFcmToken = null
          } else {
            _tmpFcmToken = _stmt.getText(_columnIndexOfFcmToken)
          }
          val _tmpIsFirstLogin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFirstLogin).toInt()
          _tmpIsFirstLogin = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              UserEntity(_tmpId,_tmpInstitutionId,_tmpUsername,_tmpPassword,_tmpRole,_tmpFullName,_tmpEmail,_tmpProfilePictureUri,_tmpFcmToken,_tmpIsFirstLogin,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findByEmail(email: String, institutionId: String): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE username = ? AND institutionId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfProfilePictureUri: Int = getColumnIndexOrThrow(_stmt, "profilePictureUri")
        val _columnIndexOfFcmToken: Int = getColumnIndexOrThrow(_stmt, "fcmToken")
        val _columnIndexOfIsFirstLogin: Int = getColumnIndexOrThrow(_stmt, "isFirstLogin")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpProfilePictureUri: String?
          if (_stmt.isNull(_columnIndexOfProfilePictureUri)) {
            _tmpProfilePictureUri = null
          } else {
            _tmpProfilePictureUri = _stmt.getText(_columnIndexOfProfilePictureUri)
          }
          val _tmpFcmToken: String?
          if (_stmt.isNull(_columnIndexOfFcmToken)) {
            _tmpFcmToken = null
          } else {
            _tmpFcmToken = _stmt.getText(_columnIndexOfFcmToken)
          }
          val _tmpIsFirstLogin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFirstLogin).toInt()
          _tmpIsFirstLogin = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              UserEntity(_tmpId,_tmpInstitutionId,_tmpUsername,_tmpPassword,_tmpRole,_tmpFullName,_tmpEmail,_tmpProfilePictureUri,_tmpFcmToken,_tmpIsFirstLogin,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getUsersByRole(role: String, institutionId: String): Flow<List<UserEntity>> {
    val _sql: String = "SELECT * FROM users WHERE role = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, role)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfProfilePictureUri: Int = getColumnIndexOrThrow(_stmt, "profilePictureUri")
        val _columnIndexOfFcmToken: Int = getColumnIndexOrThrow(_stmt, "fcmToken")
        val _columnIndexOfIsFirstLogin: Int = getColumnIndexOrThrow(_stmt, "isFirstLogin")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<UserEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpProfilePictureUri: String?
          if (_stmt.isNull(_columnIndexOfProfilePictureUri)) {
            _tmpProfilePictureUri = null
          } else {
            _tmpProfilePictureUri = _stmt.getText(_columnIndexOfProfilePictureUri)
          }
          val _tmpFcmToken: String?
          if (_stmt.isNull(_columnIndexOfFcmToken)) {
            _tmpFcmToken = null
          } else {
            _tmpFcmToken = _stmt.getText(_columnIndexOfFcmToken)
          }
          val _tmpIsFirstLogin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFirstLogin).toInt()
          _tmpIsFirstLogin = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              UserEntity(_tmpId,_tmpInstitutionId,_tmpUsername,_tmpPassword,_tmpRole,_tmpFullName,_tmpEmail,_tmpProfilePictureUri,_tmpFcmToken,_tmpIsFirstLogin,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUsersByRoleSync(institutionId: String, role: String):
      List<UserEntity> {
    val _sql: String = "SELECT * FROM users WHERE role = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, role)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfProfilePictureUri: Int = getColumnIndexOrThrow(_stmt, "profilePictureUri")
        val _columnIndexOfFcmToken: Int = getColumnIndexOrThrow(_stmt, "fcmToken")
        val _columnIndexOfIsFirstLogin: Int = getColumnIndexOrThrow(_stmt, "isFirstLogin")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<UserEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpRole: String
          _tmpRole = _stmt.getText(_columnIndexOfRole)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_columnIndexOfFullName)
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpProfilePictureUri: String?
          if (_stmt.isNull(_columnIndexOfProfilePictureUri)) {
            _tmpProfilePictureUri = null
          } else {
            _tmpProfilePictureUri = _stmt.getText(_columnIndexOfProfilePictureUri)
          }
          val _tmpFcmToken: String?
          if (_stmt.isNull(_columnIndexOfFcmToken)) {
            _tmpFcmToken = null
          } else {
            _tmpFcmToken = _stmt.getText(_columnIndexOfFcmToken)
          }
          val _tmpIsFirstLogin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFirstLogin).toInt()
          _tmpIsFirstLogin = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              UserEntity(_tmpId,_tmpInstitutionId,_tmpUsername,_tmpPassword,_tmpRole,_tmpFullName,_tmpEmail,_tmpProfilePictureUri,_tmpFcmToken,_tmpIsFirstLogin,_tmpIsActive,_tmpSyncStatus,_tmpLastModified)
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

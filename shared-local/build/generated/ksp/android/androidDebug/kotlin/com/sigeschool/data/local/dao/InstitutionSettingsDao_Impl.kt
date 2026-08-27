package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.InstitutionSettingsEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class InstitutionSettingsDao_Impl(
  __db: RoomDatabase,
) : InstitutionSettingsDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfInstitutionSettingsEntity:
      EntityInsertAdapter<InstitutionSettingsEntity>

  private val __updateAdapterOfInstitutionSettingsEntity:
      EntityDeleteOrUpdateAdapter<InstitutionSettingsEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfInstitutionSettingsEntity = object :
        EntityInsertAdapter<InstitutionSettingsEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `institution_settings` (`id`,`institutionId`,`syncUrl`,`isSyncEnabled`,`syncFrequencyHours`,`lastSyncTimestamp`,`lastSyncStatus`,`lastSyncMessage`,`downloadUrl`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: InstitutionSettingsEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        val _tmpSyncUrl: String? = entity.syncUrl
        if (_tmpSyncUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpSyncUrl)
        }
        val _tmp: Int = if (entity.isSyncEnabled) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.syncFrequencyHours.toLong())
        val _tmpLastSyncTimestamp: Long? = entity.lastSyncTimestamp
        if (_tmpLastSyncTimestamp == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpLastSyncTimestamp)
        }
        val _tmpLastSyncStatus: String? = entity.lastSyncStatus
        if (_tmpLastSyncStatus == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpLastSyncStatus)
        }
        val _tmpLastSyncMessage: String? = entity.lastSyncMessage
        if (_tmpLastSyncMessage == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpLastSyncMessage)
        }
        val _tmpDownloadUrl: String? = entity.downloadUrl
        if (_tmpDownloadUrl == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpDownloadUrl)
        }
      }
    }
    this.__updateAdapterOfInstitutionSettingsEntity = object :
        EntityDeleteOrUpdateAdapter<InstitutionSettingsEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `institution_settings` SET `id` = ?,`institutionId` = ?,`syncUrl` = ?,`isSyncEnabled` = ?,`syncFrequencyHours` = ?,`lastSyncTimestamp` = ?,`lastSyncStatus` = ?,`lastSyncMessage` = ?,`downloadUrl` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: InstitutionSettingsEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.institutionId)
        val _tmpSyncUrl: String? = entity.syncUrl
        if (_tmpSyncUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpSyncUrl)
        }
        val _tmp: Int = if (entity.isSyncEnabled) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.syncFrequencyHours.toLong())
        val _tmpLastSyncTimestamp: Long? = entity.lastSyncTimestamp
        if (_tmpLastSyncTimestamp == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpLastSyncTimestamp)
        }
        val _tmpLastSyncStatus: String? = entity.lastSyncStatus
        if (_tmpLastSyncStatus == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpLastSyncStatus)
        }
        val _tmpLastSyncMessage: String? = entity.lastSyncMessage
        if (_tmpLastSyncMessage == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpLastSyncMessage)
        }
        val _tmpDownloadUrl: String? = entity.downloadUrl
        if (_tmpDownloadUrl == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpDownloadUrl)
        }
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insert(settings: InstitutionSettingsEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfInstitutionSettingsEntity.insertAndReturnId(_connection,
        settings)
    _result
  }

  public override suspend fun update(settings: InstitutionSettingsEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfInstitutionSettingsEntity.handle(_connection, settings)
  }

  public override fun getByInstitution(instId: String): Flow<InstitutionSettingsEntity?> {
    val _sql: String = "SELECT * FROM institution_settings WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("institution_settings")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfSyncUrl: Int = getColumnIndexOrThrow(_stmt, "syncUrl")
        val _columnIndexOfIsSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "isSyncEnabled")
        val _columnIndexOfSyncFrequencyHours: Int = getColumnIndexOrThrow(_stmt,
            "syncFrequencyHours")
        val _columnIndexOfLastSyncTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastSyncTimestamp")
        val _columnIndexOfLastSyncStatus: Int = getColumnIndexOrThrow(_stmt, "lastSyncStatus")
        val _columnIndexOfLastSyncMessage: Int = getColumnIndexOrThrow(_stmt, "lastSyncMessage")
        val _columnIndexOfDownloadUrl: Int = getColumnIndexOrThrow(_stmt, "downloadUrl")
        val _result: InstitutionSettingsEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpSyncUrl: String?
          if (_stmt.isNull(_columnIndexOfSyncUrl)) {
            _tmpSyncUrl = null
          } else {
            _tmpSyncUrl = _stmt.getText(_columnIndexOfSyncUrl)
          }
          val _tmpIsSyncEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSyncEnabled).toInt()
          _tmpIsSyncEnabled = _tmp != 0
          val _tmpSyncFrequencyHours: Int
          _tmpSyncFrequencyHours = _stmt.getLong(_columnIndexOfSyncFrequencyHours).toInt()
          val _tmpLastSyncTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfLastSyncTimestamp)) {
            _tmpLastSyncTimestamp = null
          } else {
            _tmpLastSyncTimestamp = _stmt.getLong(_columnIndexOfLastSyncTimestamp)
          }
          val _tmpLastSyncStatus: String?
          if (_stmt.isNull(_columnIndexOfLastSyncStatus)) {
            _tmpLastSyncStatus = null
          } else {
            _tmpLastSyncStatus = _stmt.getText(_columnIndexOfLastSyncStatus)
          }
          val _tmpLastSyncMessage: String?
          if (_stmt.isNull(_columnIndexOfLastSyncMessage)) {
            _tmpLastSyncMessage = null
          } else {
            _tmpLastSyncMessage = _stmt.getText(_columnIndexOfLastSyncMessage)
          }
          val _tmpDownloadUrl: String?
          if (_stmt.isNull(_columnIndexOfDownloadUrl)) {
            _tmpDownloadUrl = null
          } else {
            _tmpDownloadUrl = _stmt.getText(_columnIndexOfDownloadUrl)
          }
          _result =
              InstitutionSettingsEntity(_tmpId,_tmpInstitutionId,_tmpSyncUrl,_tmpIsSyncEnabled,_tmpSyncFrequencyHours,_tmpLastSyncTimestamp,_tmpLastSyncStatus,_tmpLastSyncMessage,_tmpDownloadUrl)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByInstitutionSuspend(instId: String): InstitutionSettingsEntity? {
    val _sql: String = "SELECT * FROM institution_settings WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfSyncUrl: Int = getColumnIndexOrThrow(_stmt, "syncUrl")
        val _columnIndexOfIsSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "isSyncEnabled")
        val _columnIndexOfSyncFrequencyHours: Int = getColumnIndexOrThrow(_stmt,
            "syncFrequencyHours")
        val _columnIndexOfLastSyncTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastSyncTimestamp")
        val _columnIndexOfLastSyncStatus: Int = getColumnIndexOrThrow(_stmt, "lastSyncStatus")
        val _columnIndexOfLastSyncMessage: Int = getColumnIndexOrThrow(_stmt, "lastSyncMessage")
        val _columnIndexOfDownloadUrl: Int = getColumnIndexOrThrow(_stmt, "downloadUrl")
        val _result: InstitutionSettingsEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpSyncUrl: String?
          if (_stmt.isNull(_columnIndexOfSyncUrl)) {
            _tmpSyncUrl = null
          } else {
            _tmpSyncUrl = _stmt.getText(_columnIndexOfSyncUrl)
          }
          val _tmpIsSyncEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSyncEnabled).toInt()
          _tmpIsSyncEnabled = _tmp != 0
          val _tmpSyncFrequencyHours: Int
          _tmpSyncFrequencyHours = _stmt.getLong(_columnIndexOfSyncFrequencyHours).toInt()
          val _tmpLastSyncTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfLastSyncTimestamp)) {
            _tmpLastSyncTimestamp = null
          } else {
            _tmpLastSyncTimestamp = _stmt.getLong(_columnIndexOfLastSyncTimestamp)
          }
          val _tmpLastSyncStatus: String?
          if (_stmt.isNull(_columnIndexOfLastSyncStatus)) {
            _tmpLastSyncStatus = null
          } else {
            _tmpLastSyncStatus = _stmt.getText(_columnIndexOfLastSyncStatus)
          }
          val _tmpLastSyncMessage: String?
          if (_stmt.isNull(_columnIndexOfLastSyncMessage)) {
            _tmpLastSyncMessage = null
          } else {
            _tmpLastSyncMessage = _stmt.getText(_columnIndexOfLastSyncMessage)
          }
          val _tmpDownloadUrl: String?
          if (_stmt.isNull(_columnIndexOfDownloadUrl)) {
            _tmpDownloadUrl = null
          } else {
            _tmpDownloadUrl = _stmt.getText(_columnIndexOfDownloadUrl)
          }
          _result =
              InstitutionSettingsEntity(_tmpId,_tmpInstitutionId,_tmpSyncUrl,_tmpIsSyncEnabled,_tmpSyncFrequencyHours,_tmpLastSyncTimestamp,_tmpLastSyncStatus,_tmpLastSyncMessage,_tmpDownloadUrl)
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

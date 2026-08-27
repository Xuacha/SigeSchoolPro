package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.InstitutionEntity
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
public class InstitutionDao_Impl(
  __db: RoomDatabase,
) : InstitutionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfInstitutionEntity: EntityInsertAdapter<InstitutionEntity>

  private val __updateAdapterOfInstitutionEntity: EntityDeleteOrUpdateAdapter<InstitutionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfInstitutionEntity = object : EntityInsertAdapter<InstitutionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `institutions` (`id`,`name`,`address`,`phone`,`email`,`website`,`slogan`,`logoUri`,`createdAt`,`updatedAt`,`isActive`,`syncStatus`,`lastModified`,`planId`,`estudiantesActivos`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: InstitutionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpAddress: String? = entity.address
        if (_tmpAddress == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpAddress)
        }
        val _tmpPhone: String? = entity.phone
        if (_tmpPhone == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpPhone)
        }
        val _tmpEmail: String? = entity.email
        if (_tmpEmail == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpEmail)
        }
        val _tmpWebsite: String? = entity.website
        if (_tmpWebsite == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpWebsite)
        }
        val _tmpSlogan: String? = entity.slogan
        if (_tmpSlogan == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpSlogan)
        }
        val _tmpLogoUri: String? = entity.logoUri
        if (_tmpLogoUri == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpLogoUri)
        }
        statement.bindLong(9, entity.createdAt)
        val _tmpUpdatedAt: Long? = entity.updatedAt
        if (_tmpUpdatedAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpUpdatedAt)
        }
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
        val _tmpPlanId: Long? = entity.planId
        if (_tmpPlanId == null) {
          statement.bindNull(14)
        } else {
          statement.bindLong(14, _tmpPlanId)
        }
        statement.bindLong(15, entity.estudiantesActivos.toLong())
      }
    }
    this.__updateAdapterOfInstitutionEntity = object :
        EntityDeleteOrUpdateAdapter<InstitutionEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `institutions` SET `id` = ?,`name` = ?,`address` = ?,`phone` = ?,`email` = ?,`website` = ?,`slogan` = ?,`logoUri` = ?,`createdAt` = ?,`updatedAt` = ?,`isActive` = ?,`syncStatus` = ?,`lastModified` = ?,`planId` = ?,`estudiantesActivos` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: InstitutionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpAddress: String? = entity.address
        if (_tmpAddress == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpAddress)
        }
        val _tmpPhone: String? = entity.phone
        if (_tmpPhone == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpPhone)
        }
        val _tmpEmail: String? = entity.email
        if (_tmpEmail == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpEmail)
        }
        val _tmpWebsite: String? = entity.website
        if (_tmpWebsite == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpWebsite)
        }
        val _tmpSlogan: String? = entity.slogan
        if (_tmpSlogan == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpSlogan)
        }
        val _tmpLogoUri: String? = entity.logoUri
        if (_tmpLogoUri == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpLogoUri)
        }
        statement.bindLong(9, entity.createdAt)
        val _tmpUpdatedAt: Long? = entity.updatedAt
        if (_tmpUpdatedAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpUpdatedAt)
        }
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.syncStatus.toLong())
        statement.bindLong(13, entity.lastModified)
        val _tmpPlanId: Long? = entity.planId
        if (_tmpPlanId == null) {
          statement.bindNull(14)
        } else {
          statement.bindLong(14, _tmpPlanId)
        }
        statement.bindLong(15, entity.estudiantesActivos.toLong())
        statement.bindText(16, entity.id)
      }
    }
  }

  public override suspend fun insertInstitution(institution: InstitutionEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfInstitutionEntity.insert(_connection, institution)
  }

  public override suspend fun updateInstitution(institution: InstitutionEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfInstitutionEntity.handle(_connection, institution)
  }

  public override fun getAllActiveInstitutions(): Flow<List<InstitutionEntity>> {
    val _sql: String = "SELECT * FROM institutions WHERE isActive = 1"
    return createFlow(__db, false, arrayOf("institutions")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfWebsite: Int = getColumnIndexOrThrow(_stmt, "website")
        val _columnIndexOfSlogan: Int = getColumnIndexOrThrow(_stmt, "slogan")
        val _columnIndexOfLogoUri: Int = getColumnIndexOrThrow(_stmt, "logoUri")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfEstudiantesActivos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesActivos")
        val _result: MutableList<InstitutionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: InstitutionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpWebsite: String?
          if (_stmt.isNull(_columnIndexOfWebsite)) {
            _tmpWebsite = null
          } else {
            _tmpWebsite = _stmt.getText(_columnIndexOfWebsite)
          }
          val _tmpSlogan: String?
          if (_stmt.isNull(_columnIndexOfSlogan)) {
            _tmpSlogan = null
          } else {
            _tmpSlogan = _stmt.getText(_columnIndexOfSlogan)
          }
          val _tmpLogoUri: String?
          if (_stmt.isNull(_columnIndexOfLogoUri)) {
            _tmpLogoUri = null
          } else {
            _tmpLogoUri = _stmt.getText(_columnIndexOfLogoUri)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmpUpdatedAt = null
          } else {
            _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpPlanId: Long?
          if (_stmt.isNull(_columnIndexOfPlanId)) {
            _tmpPlanId = null
          } else {
            _tmpPlanId = _stmt.getLong(_columnIndexOfPlanId)
          }
          val _tmpEstudiantesActivos: Int
          _tmpEstudiantesActivos = _stmt.getLong(_columnIndexOfEstudiantesActivos).toInt()
          _item =
              InstitutionEntity(_tmpId,_tmpName,_tmpAddress,_tmpPhone,_tmpEmail,_tmpWebsite,_tmpSlogan,_tmpLogoUri,_tmpCreatedAt,_tmpUpdatedAt,_tmpIsActive,_tmpSyncStatus,_tmpLastModified,_tmpPlanId,_tmpEstudiantesActivos)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllActiveInstitutionsSync(): List<InstitutionEntity> {
    val _sql: String = "SELECT * FROM institutions WHERE isActive = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfWebsite: Int = getColumnIndexOrThrow(_stmt, "website")
        val _columnIndexOfSlogan: Int = getColumnIndexOrThrow(_stmt, "slogan")
        val _columnIndexOfLogoUri: Int = getColumnIndexOrThrow(_stmt, "logoUri")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfEstudiantesActivos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesActivos")
        val _result: MutableList<InstitutionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: InstitutionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpWebsite: String?
          if (_stmt.isNull(_columnIndexOfWebsite)) {
            _tmpWebsite = null
          } else {
            _tmpWebsite = _stmt.getText(_columnIndexOfWebsite)
          }
          val _tmpSlogan: String?
          if (_stmt.isNull(_columnIndexOfSlogan)) {
            _tmpSlogan = null
          } else {
            _tmpSlogan = _stmt.getText(_columnIndexOfSlogan)
          }
          val _tmpLogoUri: String?
          if (_stmt.isNull(_columnIndexOfLogoUri)) {
            _tmpLogoUri = null
          } else {
            _tmpLogoUri = _stmt.getText(_columnIndexOfLogoUri)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmpUpdatedAt = null
          } else {
            _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpPlanId: Long?
          if (_stmt.isNull(_columnIndexOfPlanId)) {
            _tmpPlanId = null
          } else {
            _tmpPlanId = _stmt.getLong(_columnIndexOfPlanId)
          }
          val _tmpEstudiantesActivos: Int
          _tmpEstudiantesActivos = _stmt.getLong(_columnIndexOfEstudiantesActivos).toInt()
          _item =
              InstitutionEntity(_tmpId,_tmpName,_tmpAddress,_tmpPhone,_tmpEmail,_tmpWebsite,_tmpSlogan,_tmpLogoUri,_tmpCreatedAt,_tmpUpdatedAt,_tmpIsActive,_tmpSyncStatus,_tmpLastModified,_tmpPlanId,_tmpEstudiantesActivos)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getInstitutionById(id: String): InstitutionEntity? {
    val _sql: String = "SELECT * FROM institutions WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfWebsite: Int = getColumnIndexOrThrow(_stmt, "website")
        val _columnIndexOfSlogan: Int = getColumnIndexOrThrow(_stmt, "slogan")
        val _columnIndexOfLogoUri: Int = getColumnIndexOrThrow(_stmt, "logoUri")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfEstudiantesActivos: Int = getColumnIndexOrThrow(_stmt,
            "estudiantesActivos")
        val _result: InstitutionEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpAddress: String?
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          }
          val _tmpPhone: String?
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          }
          val _tmpEmail: String?
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          }
          val _tmpWebsite: String?
          if (_stmt.isNull(_columnIndexOfWebsite)) {
            _tmpWebsite = null
          } else {
            _tmpWebsite = _stmt.getText(_columnIndexOfWebsite)
          }
          val _tmpSlogan: String?
          if (_stmt.isNull(_columnIndexOfSlogan)) {
            _tmpSlogan = null
          } else {
            _tmpSlogan = _stmt.getText(_columnIndexOfSlogan)
          }
          val _tmpLogoUri: String?
          if (_stmt.isNull(_columnIndexOfLogoUri)) {
            _tmpLogoUri = null
          } else {
            _tmpLogoUri = _stmt.getText(_columnIndexOfLogoUri)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long?
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmpUpdatedAt = null
          } else {
            _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          }
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpPlanId: Long?
          if (_stmt.isNull(_columnIndexOfPlanId)) {
            _tmpPlanId = null
          } else {
            _tmpPlanId = _stmt.getLong(_columnIndexOfPlanId)
          }
          val _tmpEstudiantesActivos: Int
          _tmpEstudiantesActivos = _stmt.getLong(_columnIndexOfEstudiantesActivos).toInt()
          _result =
              InstitutionEntity(_tmpId,_tmpName,_tmpAddress,_tmpPhone,_tmpEmail,_tmpWebsite,_tmpSlogan,_tmpLogoUri,_tmpCreatedAt,_tmpUpdatedAt,_tmpIsActive,_tmpSyncStatus,_tmpLastModified,_tmpPlanId,_tmpEstudiantesActivos)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEstudiantesActivos(id: String): Int {
    val _sql: String = "SELECT estudiantesActivos FROM institutions WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _result: Int
        if (_stmt.step()) {
          _result = _stmt.getLong(0).toInt()
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun count(): Int {
    val _sql: String = "SELECT COUNT(*) FROM institutions"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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

  public override suspend fun softDeleteInstitution(id: String) {
    val _sql: String = "UPDATE institutions SET isActive = 0 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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

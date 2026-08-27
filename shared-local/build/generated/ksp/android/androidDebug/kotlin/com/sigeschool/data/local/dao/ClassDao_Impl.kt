package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.ClassEntity
import javax.`annotation`.processing.Generated
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
public class ClassDao_Impl(
  __db: RoomDatabase,
) : ClassDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfClassEntity: EntityInsertAdapter<ClassEntity>

  private val __deleteAdapterOfClassEntity: EntityDeleteOrUpdateAdapter<ClassEntity>

  private val __updateAdapterOfClassEntity: EntityDeleteOrUpdateAdapter<ClassEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfClassEntity = object : EntityInsertAdapter<ClassEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `classes` (`id`,`name`,`level`,`institutionId`,`teacherId`,`createdAt`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ClassEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.level)
        statement.bindText(4, entity.institutionId)
        val _tmpTeacherId: String? = entity.teacherId
        if (_tmpTeacherId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpTeacherId)
        }
        statement.bindText(6, entity.createdAt)
      }
    }
    this.__deleteAdapterOfClassEntity = object : EntityDeleteOrUpdateAdapter<ClassEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `classes` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ClassEntity) {
        statement.bindText(1, entity.id)
      }
    }
    this.__updateAdapterOfClassEntity = object : EntityDeleteOrUpdateAdapter<ClassEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `classes` SET `id` = ?,`name` = ?,`level` = ?,`institutionId` = ?,`teacherId` = ?,`createdAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ClassEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.level)
        statement.bindText(4, entity.institutionId)
        val _tmpTeacherId: String? = entity.teacherId
        if (_tmpTeacherId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpTeacherId)
        }
        statement.bindText(6, entity.createdAt)
        statement.bindText(7, entity.id)
      }
    }
  }

  public override suspend fun insert(clazz: ClassEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfClassEntity.insert(_connection, clazz)
  }

  public override suspend fun delete(clazz: ClassEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __deleteAdapterOfClassEntity.handle(_connection, clazz)
  }

  public override suspend fun update(clazz: ClassEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfClassEntity.handle(_connection, clazz)
  }

  public override fun getAllByInstitution(institutionId: String): Flow<List<ClassEntity>> {
    val _sql: String = "SELECT * FROM classes WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("classes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTeacherId: Int = getColumnIndexOrThrow(_stmt, "teacherId")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<ClassEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClassEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLevel: String
          _tmpLevel = _stmt.getText(_columnIndexOfLevel)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTeacherId: String?
          if (_stmt.isNull(_columnIndexOfTeacherId)) {
            _tmpTeacherId = null
          } else {
            _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId)
          }
          val _tmpCreatedAt: String
          _tmpCreatedAt = _stmt.getText(_columnIndexOfCreatedAt)
          _item =
              ClassEntity(_tmpId,_tmpName,_tmpLevel,_tmpInstitutionId,_tmpTeacherId,_tmpCreatedAt)
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

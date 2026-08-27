package com.sigeschool.`data`.local.dao.billing

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.billing.FeeCategoryEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
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
public class FeeCategoryDao_Impl(
  __db: RoomDatabase,
) : FeeCategoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfFeeCategoryEntity: EntityInsertAdapter<FeeCategoryEntity>

  private val __deleteAdapterOfFeeCategoryEntity: EntityDeleteOrUpdateAdapter<FeeCategoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfFeeCategoryEntity = object : EntityInsertAdapter<FeeCategoryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `fee_categories` (`id`,`name`,`basePrice`,`isRecurring`,`appliesToGrades`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FeeCategoryEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindDouble(3, entity.basePrice)
        val _tmp: Int = if (entity.isRecurring) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindText(5, entity.appliesToGrades)
      }
    }
    this.__deleteAdapterOfFeeCategoryEntity = object :
        EntityDeleteOrUpdateAdapter<FeeCategoryEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `fee_categories` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: FeeCategoryEntity) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertCategory(category: FeeCategoryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfFeeCategoryEntity.insert(_connection, category)
  }

  public override suspend fun deleteCategory(category: FeeCategoryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfFeeCategoryEntity.handle(_connection, category)
  }

  public override fun getAllCategories(): Flow<List<FeeCategoryEntity>> {
    val _sql: String = "SELECT * FROM fee_categories"
    return createFlow(__db, false, arrayOf("fee_categories")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfBasePrice: Int = getColumnIndexOrThrow(_stmt, "basePrice")
        val _columnIndexOfIsRecurring: Int = getColumnIndexOrThrow(_stmt, "isRecurring")
        val _columnIndexOfAppliesToGrades: Int = getColumnIndexOrThrow(_stmt, "appliesToGrades")
        val _result: MutableList<FeeCategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FeeCategoryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpBasePrice: Double
          _tmpBasePrice = _stmt.getDouble(_columnIndexOfBasePrice)
          val _tmpIsRecurring: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsRecurring).toInt()
          _tmpIsRecurring = _tmp != 0
          val _tmpAppliesToGrades: String
          _tmpAppliesToGrades = _stmt.getText(_columnIndexOfAppliesToGrades)
          _item =
              FeeCategoryEntity(_tmpId,_tmpName,_tmpBasePrice,_tmpIsRecurring,_tmpAppliesToGrades)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCategoryById(id: String): FeeCategoryEntity? {
    val _sql: String = "SELECT * FROM fee_categories WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfBasePrice: Int = getColumnIndexOrThrow(_stmt, "basePrice")
        val _columnIndexOfIsRecurring: Int = getColumnIndexOrThrow(_stmt, "isRecurring")
        val _columnIndexOfAppliesToGrades: Int = getColumnIndexOrThrow(_stmt, "appliesToGrades")
        val _result: FeeCategoryEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpBasePrice: Double
          _tmpBasePrice = _stmt.getDouble(_columnIndexOfBasePrice)
          val _tmpIsRecurring: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsRecurring).toInt()
          _tmpIsRecurring = _tmp != 0
          val _tmpAppliesToGrades: String
          _tmpAppliesToGrades = _stmt.getText(_columnIndexOfAppliesToGrades)
          _result =
              FeeCategoryEntity(_tmpId,_tmpName,_tmpBasePrice,_tmpIsRecurring,_tmpAppliesToGrades)
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

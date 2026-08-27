package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AnnouncementEntity
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
public class AnnouncementDao_Impl(
  __db: RoomDatabase,
) : AnnouncementDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAnnouncementEntity: EntityInsertAdapter<AnnouncementEntity>

  private val __deleteAdapterOfAnnouncementEntity: EntityDeleteOrUpdateAdapter<AnnouncementEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAnnouncementEntity = object : EntityInsertAdapter<AnnouncementEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `announcements` (`id`,`title`,`content`,`date`,`authorId`,`institutionId`,`target`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AnnouncementEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.content)
        statement.bindLong(4, entity.date)
        statement.bindText(5, entity.authorId)
        statement.bindText(6, entity.institutionId)
        statement.bindText(7, entity.target)
      }
    }
    this.__deleteAdapterOfAnnouncementEntity = object :
        EntityDeleteOrUpdateAdapter<AnnouncementEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `announcements` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AnnouncementEntity) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertAnnouncement(announcement: AnnouncementEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAnnouncementEntity.insert(_connection, announcement)
  }

  public override suspend fun deleteAnnouncement(announcement: AnnouncementEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfAnnouncementEntity.handle(_connection, announcement)
  }

  public override fun getAnnouncements(institutionId: String): Flow<List<AnnouncementEntity>> {
    val _sql: String = "SELECT * FROM announcements WHERE institutionId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("announcements")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfAuthorId: Int = getColumnIndexOrThrow(_stmt, "authorId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTarget: Int = getColumnIndexOrThrow(_stmt, "target")
        val _result: MutableList<AnnouncementEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AnnouncementEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpAuthorId: String
          _tmpAuthorId = _stmt.getText(_columnIndexOfAuthorId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTarget: String
          _tmpTarget = _stmt.getText(_columnIndexOfTarget)
          _item =
              AnnouncementEntity(_tmpId,_tmpTitle,_tmpContent,_tmpDate,_tmpAuthorId,_tmpInstitutionId,_tmpTarget)
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

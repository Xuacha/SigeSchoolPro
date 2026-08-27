package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.InstitutionThemeEntity
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
public class InstitutionThemeDao_Impl(
  __db: RoomDatabase,
) : InstitutionThemeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfInstitutionThemeEntity: EntityInsertAdapter<InstitutionThemeEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfInstitutionThemeEntity = object :
        EntityInsertAdapter<InstitutionThemeEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `institution_themes` (`institutionId`,`primaryColor`,`secondaryColor`,`accentColor`,`backgroundColor`,`textColor`,`isDarkMode`,`themeMode`,`presetName`,`extractedFromLogo`,`lastUpdated`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: InstitutionThemeEntity) {
        statement.bindText(1, entity.institutionId)
        statement.bindLong(2, entity.primaryColor.toLong())
        statement.bindLong(3, entity.secondaryColor.toLong())
        statement.bindLong(4, entity.accentColor.toLong())
        statement.bindLong(5, entity.backgroundColor.toLong())
        statement.bindLong(6, entity.textColor.toLong())
        val _tmp: Int = if (entity.isDarkMode) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindText(8, entity.themeMode)
        val _tmpPresetName: String? = entity.presetName
        if (_tmpPresetName == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpPresetName)
        }
        val _tmp_1: Int = if (entity.extractedFromLogo) 1 else 0
        statement.bindLong(10, _tmp_1.toLong())
        statement.bindLong(11, entity.lastUpdated)
      }
    }
  }

  public override suspend fun upsertTheme(theme: InstitutionThemeEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfInstitutionThemeEntity.insert(_connection, theme)
  }

  public override fun getThemeByInstitutionId(institutionId: String):
      Flow<InstitutionThemeEntity?> {
    val _sql: String = "SELECT * FROM institution_themes WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("institution_themes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfPrimaryColor: Int = getColumnIndexOrThrow(_stmt, "primaryColor")
        val _columnIndexOfSecondaryColor: Int = getColumnIndexOrThrow(_stmt, "secondaryColor")
        val _columnIndexOfAccentColor: Int = getColumnIndexOrThrow(_stmt, "accentColor")
        val _columnIndexOfBackgroundColor: Int = getColumnIndexOrThrow(_stmt, "backgroundColor")
        val _columnIndexOfTextColor: Int = getColumnIndexOrThrow(_stmt, "textColor")
        val _columnIndexOfIsDarkMode: Int = getColumnIndexOrThrow(_stmt, "isDarkMode")
        val _columnIndexOfThemeMode: Int = getColumnIndexOrThrow(_stmt, "themeMode")
        val _columnIndexOfPresetName: Int = getColumnIndexOrThrow(_stmt, "presetName")
        val _columnIndexOfExtractedFromLogo: Int = getColumnIndexOrThrow(_stmt, "extractedFromLogo")
        val _columnIndexOfLastUpdated: Int = getColumnIndexOrThrow(_stmt, "lastUpdated")
        val _result: InstitutionThemeEntity?
        if (_stmt.step()) {
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpPrimaryColor: Int
          _tmpPrimaryColor = _stmt.getLong(_columnIndexOfPrimaryColor).toInt()
          val _tmpSecondaryColor: Int
          _tmpSecondaryColor = _stmt.getLong(_columnIndexOfSecondaryColor).toInt()
          val _tmpAccentColor: Int
          _tmpAccentColor = _stmt.getLong(_columnIndexOfAccentColor).toInt()
          val _tmpBackgroundColor: Int
          _tmpBackgroundColor = _stmt.getLong(_columnIndexOfBackgroundColor).toInt()
          val _tmpTextColor: Int
          _tmpTextColor = _stmt.getLong(_columnIndexOfTextColor).toInt()
          val _tmpIsDarkMode: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDarkMode).toInt()
          _tmpIsDarkMode = _tmp != 0
          val _tmpThemeMode: String
          _tmpThemeMode = _stmt.getText(_columnIndexOfThemeMode)
          val _tmpPresetName: String?
          if (_stmt.isNull(_columnIndexOfPresetName)) {
            _tmpPresetName = null
          } else {
            _tmpPresetName = _stmt.getText(_columnIndexOfPresetName)
          }
          val _tmpExtractedFromLogo: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfExtractedFromLogo).toInt()
          _tmpExtractedFromLogo = _tmp_1 != 0
          val _tmpLastUpdated: Long
          _tmpLastUpdated = _stmt.getLong(_columnIndexOfLastUpdated)
          _result =
              InstitutionThemeEntity(_tmpInstitutionId,_tmpPrimaryColor,_tmpSecondaryColor,_tmpAccentColor,_tmpBackgroundColor,_tmpTextColor,_tmpIsDarkMode,_tmpThemeMode,_tmpPresetName,_tmpExtractedFromLogo,_tmpLastUpdated)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteTheme(institutionId: String) {
    val _sql: String = "DELETE FROM institution_themes WHERE institutionId = ?"
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

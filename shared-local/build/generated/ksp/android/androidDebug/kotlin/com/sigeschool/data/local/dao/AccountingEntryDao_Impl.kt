package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AccountingEntryEntity
import com.sigeschool.domain.model.EntryType
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.IllegalArgumentException
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
public class AccountingEntryDao_Impl(
  __db: RoomDatabase,
) : AccountingEntryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAccountingEntryEntity: EntityInsertAdapter<AccountingEntryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAccountingEntryEntity = object :
        EntityInsertAdapter<AccountingEntryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `accounting_entries` (`id`,`date`,`description`,`institutionId`,`type`,`centerId`,`entriesJson`,`totalDebit`,`totalCredit`,`isElectronicInvoiced`,`synchronized`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AccountingEntryEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.date)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.institutionId)
        statement.bindText(5, __EntryType_enumToString(entity.type))
        val _tmpCenterId: String? = entity.centerId
        if (_tmpCenterId == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpCenterId)
        }
        statement.bindText(7, entity.entriesJson)
        statement.bindDouble(8, entity.totalDebit)
        statement.bindDouble(9, entity.totalCredit)
        val _tmp: Int = if (entity.isElectronicInvoiced) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmp_1: Int = if (entity.synchronized) 1 else 0
        statement.bindLong(11, _tmp_1.toLong())
      }
    }
  }

  public override suspend fun insert(entry: AccountingEntryEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfAccountingEntryEntity.insert(_connection, entry)
  }

  public override fun getEntries(institutionId: String): Flow<List<AccountingEntryEntity>> {
    val _sql: String = "SELECT * FROM accounting_entries WHERE institutionId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("accounting_entries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfCenterId: Int = getColumnIndexOrThrow(_stmt, "centerId")
        val _columnIndexOfEntriesJson: Int = getColumnIndexOrThrow(_stmt, "entriesJson")
        val _columnIndexOfTotalDebit: Int = getColumnIndexOrThrow(_stmt, "totalDebit")
        val _columnIndexOfTotalCredit: Int = getColumnIndexOrThrow(_stmt, "totalCredit")
        val _columnIndexOfIsElectronicInvoiced: Int = getColumnIndexOrThrow(_stmt,
            "isElectronicInvoiced")
        val _columnIndexOfSynchronized: Int = getColumnIndexOrThrow(_stmt, "synchronized")
        val _result: MutableList<AccountingEntryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccountingEntryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDate: String
          _tmpDate = _stmt.getText(_columnIndexOfDate)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpType: EntryType
          _tmpType = __EntryType_stringToEnum(_stmt.getText(_columnIndexOfType))
          val _tmpCenterId: String?
          if (_stmt.isNull(_columnIndexOfCenterId)) {
            _tmpCenterId = null
          } else {
            _tmpCenterId = _stmt.getText(_columnIndexOfCenterId)
          }
          val _tmpEntriesJson: String
          _tmpEntriesJson = _stmt.getText(_columnIndexOfEntriesJson)
          val _tmpTotalDebit: Double
          _tmpTotalDebit = _stmt.getDouble(_columnIndexOfTotalDebit)
          val _tmpTotalCredit: Double
          _tmpTotalCredit = _stmt.getDouble(_columnIndexOfTotalCredit)
          val _tmpIsElectronicInvoiced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsElectronicInvoiced).toInt()
          _tmpIsElectronicInvoiced = _tmp != 0
          val _tmpSynchronized: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfSynchronized).toInt()
          _tmpSynchronized = _tmp_1 != 0
          _item =
              AccountingEntryEntity(_tmpId,_tmpDate,_tmpDescription,_tmpInstitutionId,_tmpType,_tmpCenterId,_tmpEntriesJson,_tmpTotalDebit,_tmpTotalCredit,_tmpIsElectronicInvoiced,_tmpSynchronized)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedEntries(): List<AccountingEntryEntity> {
    val _sql: String = "SELECT * FROM accounting_entries WHERE synchronized = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfCenterId: Int = getColumnIndexOrThrow(_stmt, "centerId")
        val _columnIndexOfEntriesJson: Int = getColumnIndexOrThrow(_stmt, "entriesJson")
        val _columnIndexOfTotalDebit: Int = getColumnIndexOrThrow(_stmt, "totalDebit")
        val _columnIndexOfTotalCredit: Int = getColumnIndexOrThrow(_stmt, "totalCredit")
        val _columnIndexOfIsElectronicInvoiced: Int = getColumnIndexOrThrow(_stmt,
            "isElectronicInvoiced")
        val _columnIndexOfSynchronized: Int = getColumnIndexOrThrow(_stmt, "synchronized")
        val _result: MutableList<AccountingEntryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccountingEntryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDate: String
          _tmpDate = _stmt.getText(_columnIndexOfDate)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpType: EntryType
          _tmpType = __EntryType_stringToEnum(_stmt.getText(_columnIndexOfType))
          val _tmpCenterId: String?
          if (_stmt.isNull(_columnIndexOfCenterId)) {
            _tmpCenterId = null
          } else {
            _tmpCenterId = _stmt.getText(_columnIndexOfCenterId)
          }
          val _tmpEntriesJson: String
          _tmpEntriesJson = _stmt.getText(_columnIndexOfEntriesJson)
          val _tmpTotalDebit: Double
          _tmpTotalDebit = _stmt.getDouble(_columnIndexOfTotalDebit)
          val _tmpTotalCredit: Double
          _tmpTotalCredit = _stmt.getDouble(_columnIndexOfTotalCredit)
          val _tmpIsElectronicInvoiced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsElectronicInvoiced).toInt()
          _tmpIsElectronicInvoiced = _tmp != 0
          val _tmpSynchronized: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfSynchronized).toInt()
          _tmpSynchronized = _tmp_1 != 0
          _item =
              AccountingEntryEntity(_tmpId,_tmpDate,_tmpDescription,_tmpInstitutionId,_tmpType,_tmpCenterId,_tmpEntriesJson,_tmpTotalDebit,_tmpTotalCredit,_tmpIsElectronicInvoiced,_tmpSynchronized)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String) {
    val _sql: String = "UPDATE accounting_entries SET synchronized = 1 WHERE id = ?"
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

  private fun __EntryType_enumToString(_value: EntryType): String = when (_value) {
    EntryType.FACTURA_VENTA -> "FACTURA_VENTA"
    EntryType.RECIBO_CAJA -> "RECIBO_CAJA"
    EntryType.COMPROBANTE_EGRESO -> "COMPROBANTE_EGRESO"
    EntryType.NOTA_CONTABLE -> "NOTA_CONTABLE"
    EntryType.NOMINA -> "NOMINA"
    EntryType.AJUSTE_IA -> "AJUSTE_IA"
    EntryType.CIERRE_MENSUAL -> "CIERRE_MENSUAL"
  }

  private fun __EntryType_stringToEnum(_value: String): EntryType = when (_value) {
    "FACTURA_VENTA" -> EntryType.FACTURA_VENTA
    "RECIBO_CAJA" -> EntryType.RECIBO_CAJA
    "COMPROBANTE_EGRESO" -> EntryType.COMPROBANTE_EGRESO
    "NOTA_CONTABLE" -> EntryType.NOTA_CONTABLE
    "NOMINA" -> EntryType.NOMINA
    "AJUSTE_IA" -> EntryType.AJUSTE_IA
    "CIERRE_MENSUAL" -> EntryType.CIERRE_MENSUAL
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

package com.sigeschool.`data`.local.dao.billing

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.billing.InvoiceEntity
import com.sigeschool.`data`.local.entity.billing.InvoiceItemEntity
import com.sigeschool.`data`.local.entity.billing.PaymentRecordEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
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
public class BillingDao_Impl(
  __db: RoomDatabase,
) : BillingDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfInvoiceEntity: EntityInsertAdapter<InvoiceEntity>

  private val __insertAdapterOfInvoiceItemEntity: EntityInsertAdapter<InvoiceItemEntity>

  private val __insertAdapterOfPaymentRecordEntity: EntityInsertAdapter<PaymentRecordEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfInvoiceEntity = object : EntityInsertAdapter<InvoiceEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `invoices` (`id`,`pagoId`,`number`,`studentId`,`studentName`,`parentName`,`parentId`,`grade`,`institutionId`,`date`,`dueDate`,`status`,`type`,`totalAmount`,`paidAmount`,`balance`,`concept`,`observations`,`cufe`,`qrCode`,`xmlUrl`,`digitalSignatureUrl`,`isSynced`,`version`,`deviceId`,`lastModified`,`syncStatus`,`syncAttempts`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: InvoiceEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.pagoId)
        statement.bindText(3, entity.number)
        statement.bindText(4, entity.studentId)
        statement.bindText(5, entity.studentName)
        statement.bindText(6, entity.parentName)
        statement.bindText(7, entity.parentId)
        statement.bindText(8, entity.grade)
        statement.bindText(9, entity.institutionId)
        statement.bindLong(10, entity.date)
        statement.bindLong(11, entity.dueDate)
        statement.bindText(12, entity.status)
        statement.bindText(13, entity.type)
        statement.bindDouble(14, entity.totalAmount)
        statement.bindDouble(15, entity.paidAmount)
        statement.bindDouble(16, entity.balance)
        statement.bindText(17, entity.concept)
        val _tmpObservations: String? = entity.observations
        if (_tmpObservations == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpObservations)
        }
        val _tmpCufe: String? = entity.cufe
        if (_tmpCufe == null) {
          statement.bindNull(19)
        } else {
          statement.bindText(19, _tmpCufe)
        }
        val _tmpQrCode: String? = entity.qrCode
        if (_tmpQrCode == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpQrCode)
        }
        val _tmpXmlUrl: String? = entity.xmlUrl
        if (_tmpXmlUrl == null) {
          statement.bindNull(21)
        } else {
          statement.bindText(21, _tmpXmlUrl)
        }
        val _tmpDigitalSignatureUrl: String? = entity.digitalSignatureUrl
        if (_tmpDigitalSignatureUrl == null) {
          statement.bindNull(22)
        } else {
          statement.bindText(22, _tmpDigitalSignatureUrl)
        }
        val _tmp: Int = if (entity.isSynced) 1 else 0
        statement.bindLong(23, _tmp.toLong())
        statement.bindLong(24, entity.version)
        statement.bindText(25, entity.deviceId)
        statement.bindLong(26, entity.lastModified)
        statement.bindLong(27, entity.syncStatus.toLong())
        statement.bindLong(28, entity.syncAttempts.toLong())
      }
    }
    this.__insertAdapterOfInvoiceItemEntity = object : EntityInsertAdapter<InvoiceItemEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `invoice_items` (`id`,`invoiceId`,`categoryId`,`description`,`quantity`,`unitPrice`,`discount`,`tax`,`total`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: InvoiceItemEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.invoiceId)
        statement.bindText(3, entity.categoryId)
        statement.bindText(4, entity.description)
        statement.bindLong(5, entity.quantity.toLong())
        statement.bindDouble(6, entity.unitPrice)
        statement.bindDouble(7, entity.discount)
        statement.bindDouble(8, entity.tax)
        statement.bindDouble(9, entity.total)
      }
    }
    this.__insertAdapterOfPaymentRecordEntity = object : EntityInsertAdapter<PaymentRecordEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `payment_records` (`id`,`invoiceId`,`institutionId`,`amount`,`date`,`paymentMethod`,`reference`,`registrarId`,`isSynced`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PaymentRecordEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.invoiceId)
        statement.bindText(3, entity.institutionId)
        statement.bindDouble(4, entity.amount)
        statement.bindLong(5, entity.date)
        statement.bindText(6, entity.paymentMethod)
        val _tmpReference: String? = entity.reference
        if (_tmpReference == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpReference)
        }
        statement.bindText(8, entity.registrarId)
        val _tmp: Int = if (entity.isSynced) 1 else 0
        statement.bindLong(9, _tmp.toLong())
      }
    }
  }

  public override suspend fun insertInvoice(invoice: InvoiceEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfInvoiceEntity.insert(_connection, invoice)
  }

  public override suspend fun insertInvoiceItems(items: List<InvoiceItemEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfInvoiceItemEntity.insert(_connection, items)
  }

  public override suspend fun insertPayment(payment: PaymentRecordEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPaymentRecordEntity.insert(_connection, payment)
  }

  public override suspend fun saveInvoiceWithItems(invoice: InvoiceEntity,
      items: List<InvoiceItemEntity>): Unit = performInTransactionSuspending(__db) {
    super@BillingDao_Impl.saveInvoiceWithItems(invoice, items)
  }

  public override fun getInvoices(institutionId: String): Flow<List<InvoiceEntity>> {
    val _sql: String = "SELECT * FROM invoices WHERE institutionId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("invoices")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPagoId: Int = getColumnIndexOrThrow(_stmt, "pagoId")
        val _columnIndexOfNumber: Int = getColumnIndexOrThrow(_stmt, "number")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfStudentName: Int = getColumnIndexOrThrow(_stmt, "studentName")
        val _columnIndexOfParentName: Int = getColumnIndexOrThrow(_stmt, "parentName")
        val _columnIndexOfParentId: Int = getColumnIndexOrThrow(_stmt, "parentId")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfDueDate: Int = getColumnIndexOrThrow(_stmt, "dueDate")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfTotalAmount: Int = getColumnIndexOrThrow(_stmt, "totalAmount")
        val _columnIndexOfPaidAmount: Int = getColumnIndexOrThrow(_stmt, "paidAmount")
        val _columnIndexOfBalance: Int = getColumnIndexOrThrow(_stmt, "balance")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfCufe: Int = getColumnIndexOrThrow(_stmt, "cufe")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfXmlUrl: Int = getColumnIndexOrThrow(_stmt, "xmlUrl")
        val _columnIndexOfDigitalSignatureUrl: Int = getColumnIndexOrThrow(_stmt,
            "digitalSignatureUrl")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: MutableList<InvoiceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: InvoiceEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpPagoId: String
          _tmpPagoId = _stmt.getText(_columnIndexOfPagoId)
          val _tmpNumber: String
          _tmpNumber = _stmt.getText(_columnIndexOfNumber)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpStudentName: String
          _tmpStudentName = _stmt.getText(_columnIndexOfStudentName)
          val _tmpParentName: String
          _tmpParentName = _stmt.getText(_columnIndexOfParentName)
          val _tmpParentId: String
          _tmpParentId = _stmt.getText(_columnIndexOfParentId)
          val _tmpGrade: String
          _tmpGrade = _stmt.getText(_columnIndexOfGrade)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpDueDate: Long
          _tmpDueDate = _stmt.getLong(_columnIndexOfDueDate)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpTotalAmount: Double
          _tmpTotalAmount = _stmt.getDouble(_columnIndexOfTotalAmount)
          val _tmpPaidAmount: Double
          _tmpPaidAmount = _stmt.getDouble(_columnIndexOfPaidAmount)
          val _tmpBalance: Double
          _tmpBalance = _stmt.getDouble(_columnIndexOfBalance)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpObservations: String?
          if (_stmt.isNull(_columnIndexOfObservations)) {
            _tmpObservations = null
          } else {
            _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          }
          val _tmpCufe: String?
          if (_stmt.isNull(_columnIndexOfCufe)) {
            _tmpCufe = null
          } else {
            _tmpCufe = _stmt.getText(_columnIndexOfCufe)
          }
          val _tmpQrCode: String?
          if (_stmt.isNull(_columnIndexOfQrCode)) {
            _tmpQrCode = null
          } else {
            _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          }
          val _tmpXmlUrl: String?
          if (_stmt.isNull(_columnIndexOfXmlUrl)) {
            _tmpXmlUrl = null
          } else {
            _tmpXmlUrl = _stmt.getText(_columnIndexOfXmlUrl)
          }
          val _tmpDigitalSignatureUrl: String?
          if (_stmt.isNull(_columnIndexOfDigitalSignatureUrl)) {
            _tmpDigitalSignatureUrl = null
          } else {
            _tmpDigitalSignatureUrl = _stmt.getText(_columnIndexOfDigitalSignatureUrl)
          }
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          val _tmpDeviceId: String
          _tmpDeviceId = _stmt.getText(_columnIndexOfDeviceId)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpSyncAttempts: Int
          _tmpSyncAttempts = _stmt.getLong(_columnIndexOfSyncAttempts).toInt()
          _item =
              InvoiceEntity(_tmpId,_tmpPagoId,_tmpNumber,_tmpStudentId,_tmpStudentName,_tmpParentName,_tmpParentId,_tmpGrade,_tmpInstitutionId,_tmpDate,_tmpDueDate,_tmpStatus,_tmpType,_tmpTotalAmount,_tmpPaidAmount,_tmpBalance,_tmpConcept,_tmpObservations,_tmpCufe,_tmpQrCode,_tmpXmlUrl,_tmpDigitalSignatureUrl,_tmpIsSynced,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getInvoiceById(id: String): Flow<InvoiceEntity?> {
    val _sql: String = "SELECT * FROM invoices WHERE id = ?"
    return createFlow(__db, false, arrayOf("invoices")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPagoId: Int = getColumnIndexOrThrow(_stmt, "pagoId")
        val _columnIndexOfNumber: Int = getColumnIndexOrThrow(_stmt, "number")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfStudentName: Int = getColumnIndexOrThrow(_stmt, "studentName")
        val _columnIndexOfParentName: Int = getColumnIndexOrThrow(_stmt, "parentName")
        val _columnIndexOfParentId: Int = getColumnIndexOrThrow(_stmt, "parentId")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfDueDate: Int = getColumnIndexOrThrow(_stmt, "dueDate")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfTotalAmount: Int = getColumnIndexOrThrow(_stmt, "totalAmount")
        val _columnIndexOfPaidAmount: Int = getColumnIndexOrThrow(_stmt, "paidAmount")
        val _columnIndexOfBalance: Int = getColumnIndexOrThrow(_stmt, "balance")
        val _columnIndexOfConcept: Int = getColumnIndexOrThrow(_stmt, "concept")
        val _columnIndexOfObservations: Int = getColumnIndexOrThrow(_stmt, "observations")
        val _columnIndexOfCufe: Int = getColumnIndexOrThrow(_stmt, "cufe")
        val _columnIndexOfQrCode: Int = getColumnIndexOrThrow(_stmt, "qrCode")
        val _columnIndexOfXmlUrl: Int = getColumnIndexOrThrow(_stmt, "xmlUrl")
        val _columnIndexOfDigitalSignatureUrl: Int = getColumnIndexOrThrow(_stmt,
            "digitalSignatureUrl")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfSyncAttempts: Int = getColumnIndexOrThrow(_stmt, "syncAttempts")
        val _result: InvoiceEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpPagoId: String
          _tmpPagoId = _stmt.getText(_columnIndexOfPagoId)
          val _tmpNumber: String
          _tmpNumber = _stmt.getText(_columnIndexOfNumber)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpStudentName: String
          _tmpStudentName = _stmt.getText(_columnIndexOfStudentName)
          val _tmpParentName: String
          _tmpParentName = _stmt.getText(_columnIndexOfParentName)
          val _tmpParentId: String
          _tmpParentId = _stmt.getText(_columnIndexOfParentId)
          val _tmpGrade: String
          _tmpGrade = _stmt.getText(_columnIndexOfGrade)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpDueDate: Long
          _tmpDueDate = _stmt.getLong(_columnIndexOfDueDate)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpTotalAmount: Double
          _tmpTotalAmount = _stmt.getDouble(_columnIndexOfTotalAmount)
          val _tmpPaidAmount: Double
          _tmpPaidAmount = _stmt.getDouble(_columnIndexOfPaidAmount)
          val _tmpBalance: Double
          _tmpBalance = _stmt.getDouble(_columnIndexOfBalance)
          val _tmpConcept: String
          _tmpConcept = _stmt.getText(_columnIndexOfConcept)
          val _tmpObservations: String?
          if (_stmt.isNull(_columnIndexOfObservations)) {
            _tmpObservations = null
          } else {
            _tmpObservations = _stmt.getText(_columnIndexOfObservations)
          }
          val _tmpCufe: String?
          if (_stmt.isNull(_columnIndexOfCufe)) {
            _tmpCufe = null
          } else {
            _tmpCufe = _stmt.getText(_columnIndexOfCufe)
          }
          val _tmpQrCode: String?
          if (_stmt.isNull(_columnIndexOfQrCode)) {
            _tmpQrCode = null
          } else {
            _tmpQrCode = _stmt.getText(_columnIndexOfQrCode)
          }
          val _tmpXmlUrl: String?
          if (_stmt.isNull(_columnIndexOfXmlUrl)) {
            _tmpXmlUrl = null
          } else {
            _tmpXmlUrl = _stmt.getText(_columnIndexOfXmlUrl)
          }
          val _tmpDigitalSignatureUrl: String?
          if (_stmt.isNull(_columnIndexOfDigitalSignatureUrl)) {
            _tmpDigitalSignatureUrl = null
          } else {
            _tmpDigitalSignatureUrl = _stmt.getText(_columnIndexOfDigitalSignatureUrl)
          }
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          val _tmpDeviceId: String
          _tmpDeviceId = _stmt.getText(_columnIndexOfDeviceId)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpSyncAttempts: Int
          _tmpSyncAttempts = _stmt.getLong(_columnIndexOfSyncAttempts).toInt()
          _result =
              InvoiceEntity(_tmpId,_tmpPagoId,_tmpNumber,_tmpStudentId,_tmpStudentName,_tmpParentName,_tmpParentId,_tmpGrade,_tmpInstitutionId,_tmpDate,_tmpDueDate,_tmpStatus,_tmpType,_tmpTotalAmount,_tmpPaidAmount,_tmpBalance,_tmpConcept,_tmpObservations,_tmpCufe,_tmpQrCode,_tmpXmlUrl,_tmpDigitalSignatureUrl,_tmpIsSynced,_tmpVersion,_tmpDeviceId,_tmpLastModified,_tmpSyncStatus,_tmpSyncAttempts)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getItemsByInvoiceId(invoiceId: String): List<InvoiceItemEntity> {
    val _sql: String = "SELECT * FROM invoice_items WHERE invoiceId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, invoiceId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInvoiceId: Int = getColumnIndexOrThrow(_stmt, "invoiceId")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfQuantity: Int = getColumnIndexOrThrow(_stmt, "quantity")
        val _columnIndexOfUnitPrice: Int = getColumnIndexOrThrow(_stmt, "unitPrice")
        val _columnIndexOfDiscount: Int = getColumnIndexOrThrow(_stmt, "discount")
        val _columnIndexOfTax: Int = getColumnIndexOrThrow(_stmt, "tax")
        val _columnIndexOfTotal: Int = getColumnIndexOrThrow(_stmt, "total")
        val _result: MutableList<InvoiceItemEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: InvoiceItemEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInvoiceId: String
          _tmpInvoiceId = _stmt.getText(_columnIndexOfInvoiceId)
          val _tmpCategoryId: String
          _tmpCategoryId = _stmt.getText(_columnIndexOfCategoryId)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpQuantity: Int
          _tmpQuantity = _stmt.getLong(_columnIndexOfQuantity).toInt()
          val _tmpUnitPrice: Double
          _tmpUnitPrice = _stmt.getDouble(_columnIndexOfUnitPrice)
          val _tmpDiscount: Double
          _tmpDiscount = _stmt.getDouble(_columnIndexOfDiscount)
          val _tmpTax: Double
          _tmpTax = _stmt.getDouble(_columnIndexOfTax)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal)
          _item =
              InvoiceItemEntity(_tmpId,_tmpInvoiceId,_tmpCategoryId,_tmpDescription,_tmpQuantity,_tmpUnitPrice,_tmpDiscount,_tmpTax,_tmpTotal)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPaymentById(id: String): PaymentRecordEntity? {
    val _sql: String = "SELECT * FROM payment_records WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInvoiceId: Int = getColumnIndexOrThrow(_stmt, "invoiceId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfPaymentMethod: Int = getColumnIndexOrThrow(_stmt, "paymentMethod")
        val _columnIndexOfReference: Int = getColumnIndexOrThrow(_stmt, "reference")
        val _columnIndexOfRegistrarId: Int = getColumnIndexOrThrow(_stmt, "registrarId")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _result: PaymentRecordEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInvoiceId: String
          _tmpInvoiceId = _stmt.getText(_columnIndexOfInvoiceId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpPaymentMethod: String
          _tmpPaymentMethod = _stmt.getText(_columnIndexOfPaymentMethod)
          val _tmpReference: String?
          if (_stmt.isNull(_columnIndexOfReference)) {
            _tmpReference = null
          } else {
            _tmpReference = _stmt.getText(_columnIndexOfReference)
          }
          val _tmpRegistrarId: String
          _tmpRegistrarId = _stmt.getText(_columnIndexOfRegistrarId)
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          _result =
              PaymentRecordEntity(_tmpId,_tmpInvoiceId,_tmpInstitutionId,_tmpAmount,_tmpDate,_tmpPaymentMethod,_tmpReference,_tmpRegistrarId,_tmpIsSynced)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPaymentsByInvoiceId(invoiceId: String): Flow<List<PaymentRecordEntity>> {
    val _sql: String = "SELECT * FROM payment_records WHERE invoiceId = ?"
    return createFlow(__db, false, arrayOf("payment_records")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, invoiceId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInvoiceId: Int = getColumnIndexOrThrow(_stmt, "invoiceId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfPaymentMethod: Int = getColumnIndexOrThrow(_stmt, "paymentMethod")
        val _columnIndexOfReference: Int = getColumnIndexOrThrow(_stmt, "reference")
        val _columnIndexOfRegistrarId: Int = getColumnIndexOrThrow(_stmt, "registrarId")
        val _columnIndexOfIsSynced: Int = getColumnIndexOrThrow(_stmt, "isSynced")
        val _result: MutableList<PaymentRecordEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PaymentRecordEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInvoiceId: String
          _tmpInvoiceId = _stmt.getText(_columnIndexOfInvoiceId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpPaymentMethod: String
          _tmpPaymentMethod = _stmt.getText(_columnIndexOfPaymentMethod)
          val _tmpReference: String?
          if (_stmt.isNull(_columnIndexOfReference)) {
            _tmpReference = null
          } else {
            _tmpReference = _stmt.getText(_columnIndexOfReference)
          }
          val _tmpRegistrarId: String
          _tmpRegistrarId = _stmt.getText(_columnIndexOfRegistrarId)
          val _tmpIsSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSynced).toInt()
          _tmpIsSynced = _tmp != 0
          _item =
              PaymentRecordEntity(_tmpId,_tmpInvoiceId,_tmpInstitutionId,_tmpAmount,_tmpDate,_tmpPaymentMethod,_tmpReference,_tmpRegistrarId,_tmpIsSynced)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteInvoice(invoiceId: String) {
    val _sql: String = "DELETE FROM invoices WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, invoiceId)
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

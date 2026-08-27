package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.CashClosingEntity
import com.sigeschool.data.local.entity.CashTransactionEntity
import com.sigeschool.data.local.entity.billing.*
import com.sigeschool.domain.model.CashClosing
import com.sigeschool.domain.model.billing.*
import com.sigeschool.domain.model.billing.DocumentType as BillingDocumentType
import kotlinx.datetime.Instant

fun InvoiceEntity.toDomain(items: List<InvoiceItemEntity> = emptyList()): Invoice = Invoice(
    id = id,
    pagoId = pagoId,
    number = number,
    studentId = studentId,
    studentName = studentName,
    parentName = parentName,
    parentId = parentId,
    grade = grade,
    institutionId = institutionId,
    date = Instant.fromEpochMilliseconds(date),
    dueDate = Instant.fromEpochMilliseconds(dueDate),
    status = InvoiceStatus.valueOf(status),
    type = BillingDocumentType.valueOf(type),
    items = items.map { it.toDomain() },
    totalAmount = totalAmount,
    paidAmount = paidAmount,
    balance = balance,
    concept = concept,
    observations = observations,
    cufe = cufe,
    qrCode = qrCode,
    xmlUrl = xmlUrl,
    digitalSignatureUrl = digitalSignatureUrl,
    version = version,
    deviceId = deviceId,
    isSynced = isSynced
)

fun Invoice.toEntity(): InvoiceEntity = InvoiceEntity(
    id = id,
    pagoId = pagoId,
    number = number,
    studentId = studentId,
    studentName = studentName,
    parentName = parentName,
    parentId = parentId,
    grade = grade,
    institutionId = institutionId,
    date = date.toEpochMilliseconds(),
    dueDate = dueDate.toEpochMilliseconds(),
    status = status.name,
    type = type.name,
    totalAmount = totalAmount,
    paidAmount = paidAmount,
    balance = balance,
    concept = concept,
    observations = observations,
    cufe = cufe,
    qrCode = qrCode,
    xmlUrl = xmlUrl,
    digitalSignatureUrl = digitalSignatureUrl,
    version = version,
    deviceId = deviceId,
    isSynced = isSynced
)

fun InvoiceItemEntity.toDomain(): InvoiceItem = InvoiceItem(
    id = id,
    categoryId = categoryId,
    description = description,
    quantity = quantity,
    unitPrice = unitPrice,
    discount = discount,
    tax = tax,
    total = total
)

fun InvoiceItem.toEntity(invoiceId: String): InvoiceItemEntity = InvoiceItemEntity(
    id = id,
    invoiceId = invoiceId,
    categoryId = categoryId,
    description = description,
    quantity = quantity,
    unitPrice = unitPrice,
    discount = discount,
    tax = tax,
    total = total
)

fun PaymentRecordEntity.toDomain(): PaymentRecord = PaymentRecord(
    id = id,
    invoiceId = invoiceId,
    amount = amount,
    date = Instant.fromEpochMilliseconds(date),
    paymentMethod = PaymentMethod.valueOf(paymentMethod),
    reference = reference,
    institutionId = institutionId,
    registrarId = registrarId,
    isSynced = isSynced
)

fun PaymentRecord.toEntity(): PaymentRecordEntity = PaymentRecordEntity(
    id = id,
    invoiceId = invoiceId,
    institutionId = institutionId,
    amount = amount,
    date = date.toEpochMilliseconds(),
    paymentMethod = paymentMethod.name,
    reference = reference,
    registrarId = registrarId,
    isSynced = isSynced
)

fun FeeCategoryEntity.toDomain(): FeeCategory = FeeCategory(
    id = id,
    name = name,
    basePrice = basePrice,
    isRecurring = isRecurring,
    appliesToGrades = appliesToGrades.split(",").filter { it.isNotBlank() }
)

fun FeeCategory.toEntity(): FeeCategoryEntity = FeeCategoryEntity(
    id = id,
    name = name,
    basePrice = basePrice,
    isRecurring = isRecurring,
    appliesToGrades = appliesToGrades.joinToString(",")
)

// Mappers for CashTransaction
fun CashTransactionEntity.toDomain(): CashTransaction = CashTransaction(
    id = id,
    institutionId = institutionId,
    type = CashTransactionType.valueOf(type),
    concept = concept,
    category = category,
    amount = amount,
    paymentMethod = paymentMethod,
    personName = personName,
    reference = reference,
    timestamp = timestamp,
    observations = observations,
    registradoPorId = registradoPorId,
    isSynced = isSynced
)

fun CashTransaction.toEntity(): CashTransactionEntity = CashTransactionEntity(
    id = id,
    institutionId = institutionId,
    type = type.name,
    concept = concept,
    category = category,
    amount = amount,
    paymentMethod = paymentMethod,
    personName = personName,
    reference = reference,
    timestamp = timestamp,
    observations = observations,
    registradoPorId = registradoPorId,
    isSynced = isSynced
)

// Mappers for CashClosing
fun CashClosingEntity.toDomain(): CashClosing = CashClosing(
    id = id,
    date = date,
    institutionId = institutionId,
    totalCash = totalCash,
    totalTransfer = totalTransfer,
    totalOther = totalOther,
    totalGeneral = totalGeneral,
    closedBy = closedBy,
    closingTimestamp = closingTimestamp,
    observations = observations,
    isSynced = isSynced
)

fun CashClosing.toEntity(): CashClosingEntity = CashClosingEntity(
    id = id,
    date = date,
    institutionId = institutionId,
    totalCash = totalCash,
    totalTransfer = totalTransfer,
    totalOther = totalOther,
    totalGeneral = totalGeneral,
    closedBy = closedBy,
    closingTimestamp = closingTimestamp,
    observations = observations,
    isSynced = isSynced
)

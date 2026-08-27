package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.AdvanceRequestEntity
import com.sigeschool.data.local.entity.PayrollCalculationEntity
import com.sigeschool.data.local.entity.VacationRequestEntity
import com.sigeschool.domain.model.AdvanceRequest
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.VacationRequest
import com.sigeschool.domain.util.randomUUID
import com.sigeschool.domain.util.toDecryptedDouble
import com.sigeschool.domain.util.toDecryptedDoubleSync
import com.sigeschool.domain.util.toEncryptedString
import com.sigeschool.domain.util.toEncryptedStringSync

fun VacationRequestEntity.toDomain() = VacationRequest(
    id = id,
    employeeId = employeeId,
    startDate = startDate,
    endDate = endDate,
    days = days,
    status = status,
    observations = observations
)

fun VacationRequest.toEntity(sincronizado: Boolean = false) = VacationRequestEntity(
    id = id.ifEmpty { randomUUID() },
    employeeId = employeeId,
    startDate = startDate,
    endDate = endDate,
    days = days,
    status = status,
    observations = observations,
    sincronizado = sincronizado
)

fun AdvanceRequestEntity.toDomain() = AdvanceRequest(
    id = id,
    employeeId = employeeId,
    amountRequested = amountRequested,
    reason = reason,
    status = status,
    requestDate = requestDate,
    maxAllowed = maxAllowed
)

fun AdvanceRequest.toEntity(sincronizado: Boolean = false) = AdvanceRequestEntity(
    id = id.ifEmpty { randomUUID() },
    employeeId = employeeId,
    amountRequested = amountRequested,
    reason = reason,
    status = status,
    requestDate = requestDate,
    maxAllowed = maxAllowed,
    sincronizado = sincronizado
)

fun PayrollCalculationEntity.toDomain() = PayrollCalculation(
    employeeId = employeeId,
    date = date,
    basicSalary = basicSalary.toDecryptedDoubleSync(),
    daysWorked = daysWorked,
    transportAllowance = transportAllowance.toDecryptedDoubleSync(),
    healthDeduction = healthDeduction.toDecryptedDoubleSync(),
    pensionDeduction = pensionDeduction.toDecryptedDoubleSync(),
    advances = advances.toDecryptedDoubleSync(),
    extraHours = extraHours.toDecryptedDoubleSync(),
    totalDevengado = totalDevengado.toDecryptedDoubleSync(),
    totalDeducciones = totalDeducciones.toDecryptedDoubleSync(),
    netPay = netPay.toDecryptedDoubleSync()
)

fun PayrollCalculation.toEntity(overrideEmployeeId: String? = null, overrideDate: Long? = null, sincronizado: Boolean = false) = PayrollCalculationEntity(
    id = randomUUID(),
    employeeId = overrideEmployeeId ?: employeeId,
    basicSalary = basicSalary.toEncryptedStringSync(),
    daysWorked = daysWorked,
    transportAllowance = transportAllowance.toEncryptedStringSync(),
    healthDeduction = healthDeduction.toEncryptedStringSync(),
    pensionDeduction = pensionDeduction.toEncryptedStringSync(),
    advances = advances.toEncryptedStringSync(),
    extraHours = extraHours.toEncryptedStringSync(),
    totalDevengado = totalDevengado.toEncryptedStringSync(),
    totalDeducciones = totalDeducciones.toEncryptedStringSync(),
    netPay = netPay.toEncryptedStringSync(),
    date = overrideDate ?: date,
    sincronizado = sincronizado
)

package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.SalaryRecordEntity
import com.sigeschool.domain.model.SalaryRecord

fun SalaryRecordEntity.toDomain(): SalaryRecord {
    return SalaryRecord(
        id = id,
        employeeId = employeeId,
        institutionId = institutionId,
        amount = amount,
        date = date,
        type = type,
        status = status,
        observation = observation,
        sincronizado = sincronizado
    )
}

fun SalaryRecord.toEntity(): SalaryRecordEntity {
    return SalaryRecordEntity(
        id = id,
        employeeId = employeeId,
        institutionId = institutionId,
        amount = amount,
        date = date,
        type = type,
        status = status,
        observation = observation,
        sincronizado = sincronizado
    )
}

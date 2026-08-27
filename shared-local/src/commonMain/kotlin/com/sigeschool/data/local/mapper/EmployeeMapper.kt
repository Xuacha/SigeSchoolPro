package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.EmployeeEntity
import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.EmployeeStatus
import com.sigeschool.domain.model.UserRole

fun EmployeeEntity.toDomain(): Employee {
    return Employee(
        id = id,
        institutionId = institutionId,
        firstName = firstName,
        lastName = lastName,
        dni = dni,
        role = UserRole.fromString(role),
        qualification = qualification,
        specialization = specialization,
        department = department,
        email = email,
        phone = phone,
        hireDate = hireDate,
        status = EmployeeStatus.valueOf(status)
    )
}

fun Employee.toEntity(): EmployeeEntity {
    return EmployeeEntity(
        id = id,
        institutionId = institutionId,
        firstName = firstName,
        lastName = lastName,
        dni = dni,
        role = role.name,
        qualification = qualification,
        specialization = specialization,
        department = department,
        email = email,
        phone = phone,
        hireDate = hireDate,
        status = status.name
    )
}

package com.sigeschool.data.mapper

import com.sigeschool.data.local.entity.CalificacionEntity
import com.sigeschool.domain.model.Grade

fun CalificacionEntity.toDomain() = Grade(
    id = id,
    studentId = estudianteId,
    institutionId = institutionId,
    claseId = claseId.toString(),
    periodId = periodoAcademicoId.toString(),
    score = nota,
    observations = observacion ?: "",
    sincronizado = syncStatus == 1
)

fun Grade.toEntity() = CalificacionEntity(
    id = id,
    estudianteId = studentId,
    claseId = claseId.toLongOrNull() ?: 0,
    periodoAcademicoId = periodId.toLongOrNull() ?: 0,
    nota = score,
    observacion = observations,
    institutionId = institutionId,
    syncStatus = if (sincronizado) 1 else 0
)

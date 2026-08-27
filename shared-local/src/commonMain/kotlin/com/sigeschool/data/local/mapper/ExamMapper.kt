package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.ExamEntity
import com.sigeschool.domain.model.Exam

fun ExamEntity.toDomain(): Exam {
    return Exam(
        id = id,
        title = title,
        date = date,
        classId = classId,
        subjectId = subjectId,
        maxScore = maxScore,
        institutionId = institutionId,
        durationMinutes = durationMinutes,
        questions = questions,
        sincronizado = sincronizado
    )
}

fun Exam.toEntity(): ExamEntity {
    return ExamEntity(
        id = id,
        title = title,
        date = date,
        classId = classId,
        subjectId = subjectId,
        maxScore = maxScore,
        institutionId = institutionId,
        durationMinutes = durationMinutes,
        questions = questions,
        sincronizado = sincronizado
    )
}

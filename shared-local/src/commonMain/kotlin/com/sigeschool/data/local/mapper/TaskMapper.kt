package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.TaskEntity
import com.sigeschool.domain.model.Task

fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate.toString(),
        classId = classId,
        subjectId = subjectId,
        institutionId = institutionId,
        sincronizado = sincronizado
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate.toLongOrNull() ?: 0L,
        classId = classId,
        subjectId = subjectId,
        institutionId = institutionId,
        sincronizado = sincronizado
    )
}

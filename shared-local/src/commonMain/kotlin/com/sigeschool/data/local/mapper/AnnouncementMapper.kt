package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.AnnouncementEntity
import com.sigeschool.domain.model.Announcement

fun AnnouncementEntity.toDomain(): Announcement {
    return Announcement(
        id = id,
        title = title,
        content = content,
        date = date,
        authorId = authorId,
        institutionId = institutionId,
        target = target
    )
}

fun Announcement.toEntity(): AnnouncementEntity {
    return AnnouncementEntity(
        id = id,
        title = title,
        content = content,
        date = date,
        authorId = authorId,
        institutionId = institutionId,
        target = target
    )
}

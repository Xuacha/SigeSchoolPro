package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.DocumentBlockEntity
import com.sigeschool.data.local.entity.InstitutionalDocumentEntity
import com.sigeschool.domain.model.DocumentBlock
import com.sigeschool.domain.model.DocumentType
import com.sigeschool.domain.model.InstitutionalDocument

fun InstitutionalDocumentEntity.toDomain() = InstitutionalDocument(
    id = id,
    title = title,
    type = DocumentType.valueOf(type),
    institutionId = institutionId,
    grade = grade,
    subject = subject,
    teacherId = teacherId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun InstitutionalDocument.toEntity() = InstitutionalDocumentEntity(
    id = id,
    title = title,
    type = type.name,
    institutionId = institutionId,
    grade = grade,
    subject = subject,
    teacherId = teacherId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun DocumentBlockEntity.toDomain() = DocumentBlock(
    id = id,
    documentId = documentId,
    order = orderIndex,
    title = title,
    contentHtml = contentHtml,
    updatedAt = updatedAt,
    modifiedBy = modifiedBy
)

fun DocumentBlock.toEntity() = DocumentBlockEntity(
    id = id,
    documentId = documentId,
    orderIndex = order,
    title = title,
    contentHtml = contentHtml,
    updatedAt = updatedAt,
    modifiedBy = modifiedBy
)

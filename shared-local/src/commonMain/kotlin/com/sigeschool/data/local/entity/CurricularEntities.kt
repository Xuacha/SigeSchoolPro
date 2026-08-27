package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sigeschool.domain.model.DocumentType

@Entity(tableName = "institutional_documents")
data class InstitutionalDocumentEntity(
    @PrimaryKey val id: String,
    val title: String,
    val type: String, // PEI, PLAN_ESTUDIOS, PLAN_AULA
    val institutionId: String,
    val grade: String?,
    val subject: String?,
    val teacherId: String?,
    val createdAt: Long,
    val updatedAt: Long
)

@Entity(tableName = "document_blocks")
data class DocumentBlockEntity(
    @PrimaryKey val id: String,
    val documentId: String,
    val orderIndex: Int,
    val title: String,
    val contentHtml: String,
    val updatedAt: Long,
    val modifiedBy: String
)

@Entity(tableName = "block_history")
data class BlockHistoryEntity(
    @PrimaryKey val id: String,
    val blockId: String,
    val contentHtml: String,
    val modifiedAt: Long,
    val modifiedBy: String
)

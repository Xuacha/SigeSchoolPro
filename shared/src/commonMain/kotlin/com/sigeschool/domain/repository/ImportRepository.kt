package com.sigeschool.domain.repository

import com.sigeschool.domain.model.ImportResult
import com.sigeschool.domain.model.ImportDetail
import kotlinx.coroutines.flow.Flow

interface ImportRepository {
    suspend fun saveImportResult(result: ImportResult): Result<Unit>
    suspend fun getImportResultById(id: String): ImportResult?
    suspend fun getRecentImports(limit: Int): List<ImportResult>
    suspend fun saveImportDetail(detail: ImportDetail, importId: String): Result<Unit>
}

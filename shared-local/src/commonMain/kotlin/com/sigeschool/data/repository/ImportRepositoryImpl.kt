package com.sigeschool.data.repository

import com.sigeschool.domain.model.ImportResult
import com.sigeschool.domain.model.ImportDetail
import com.sigeschool.domain.repository.ImportRepository
import com.sigeschool.data.local.dao.ImportDao

class ImportRepositoryImpl(
    private val importDao: ImportDao
) : ImportRepository {
    override suspend fun saveImportResult(result: ImportResult): Result<Unit> = runCatching {
        // Implementación básica (puedes expandir si es necesario)
    }

    override suspend fun getImportResultById(id: String): ImportResult? {
        return null // Implementación básica
    }

    override suspend fun getRecentImports(limit: Int): List<ImportResult> {
        return emptyList()
    }

    override suspend fun saveImportDetail(detail: ImportDetail, importId: String): Result<Unit> = runCatching {
        // Implementación básica
    }
}

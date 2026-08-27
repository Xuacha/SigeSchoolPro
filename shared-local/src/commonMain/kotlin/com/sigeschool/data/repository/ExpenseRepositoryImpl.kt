package com.sigeschool.data.repository

import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.model.billing.Expense
import com.sigeschool.domain.repository.ExpenseRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.data.local.dao.ExpenseDao
import com.sigeschool.data.local.entity.ExpenseEntity
import com.sigeschool.domain.repository.PucRepository
import com.sigeschool.domain.model.AccountingEntry
import com.sigeschool.domain.model.EntryDetail
import com.sigeschool.domain.util.randomUUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao,
    private val pucRepository: PucRepository,
    private val auditRepository: AuditRepository
) : ExpenseRepository {

    override suspend fun saveExpense(expense: Expense): Resource<String> {
        return try {
            val id = if (expense.id.isEmpty() || expense.id == "0") randomUUID() else expense.id
            val entity = ExpenseEntity(
                id = id,
                institutionId = expense.institutionId,
                amount = expense.amount,
                date = expense.date,
                description = expense.description,
                category = expense.category,
                syncStatus = 1, // PENDING_INSERT
                lastModified = 0
            )
            expenseDao.insertExpense(entity)

            // Registrar en PUC (Contabilidad)
            val dateStr = Instant.fromEpochMilliseconds(expense.date).toLocalDateTime(TimeZone.currentSystemDefault()).toString()
            val entry = AccountingEntry(
                id = randomUUID(),
                date = dateStr,
                description = "Gasto: ${expense.description} - Categoría: ${expense.category}",
                entries = listOf(
                    EntryDetail(
                        accountCode = "5195", // Gastos Diversos (ejemplo)
                        accountName = "Gastos Operacionales",
                        debit = expense.amount,
                        credit = 0.0
                    ),
                    EntryDetail(
                        accountCode = "1110", // Bancos
                        accountName = "Caja/Bancos",
                        debit = 0.0,
                        credit = expense.amount
                    )
                ),
                totalDebit = expense.amount,
                totalCredit = expense.amount
            )
            pucRepository.saveEntry(entry)
            
            auditRepository.log(
                action = "SAVE_EXPENSE",
                resource = "expenses/$id",
                payload = mapOf("amount" to expense.amount, "category" to expense.category)
            )

            Resource.Success(id)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar gasto")
        }
    }

    override fun getAllExpenses(institutionId: String): Flow<List<Expense>> {
        return expenseDao.getAllExpenses(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getTotalExpenses(institutionId: String): Double {
        return expenseDao.getTotalExpenses(institutionId) ?: 0.0
    }

    override suspend fun syncExpenses(institutionId: String): Resource<Unit> {
        return try {
            val pending = expenseDao.getPendingSyncExpenses(institutionId)
            // Lógica de sincronización remota omitida o delegada a PucRepository si corresponde
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Sync error: ${e.message}")
        }
    }

    private fun ExpenseEntity.toDomain(): Expense {
        return Expense(
            id = id,
            institutionId = institutionId,
            amount = amount,
            date = date,
            description = description,
            category = category
        )
    }
}

package com.sigeschool.domain.repository

import com.sigeschool.domain.model.billing.Expense
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun saveExpense(expense: Expense): Resource<String>
    fun getAllExpenses(institutionId: String): Flow<List<Expense>>
    suspend fun getTotalExpenses(institutionId: String): Double
    suspend fun syncExpenses(institutionId: String): Resource<Unit>
}

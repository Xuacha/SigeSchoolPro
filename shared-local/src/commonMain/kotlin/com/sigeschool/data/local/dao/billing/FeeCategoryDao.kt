package com.sigeschool.data.local.dao.billing

import androidx.room.*
import com.sigeschool.data.local.entity.billing.FeeCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeeCategoryDao {
    @Query("SELECT * FROM fee_categories")
    fun getAllCategories(): Flow<List<FeeCategoryEntity>>

    @Query("SELECT * FROM fee_categories WHERE id = :id")
    suspend fun getCategoryById(id: String): FeeCategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: FeeCategoryEntity)

    @Delete
    suspend fun deleteCategory(category: FeeCategoryEntity)
}

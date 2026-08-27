package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.LibroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LibroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libro: LibroEntity)

    @Update
    suspend fun update(libro: LibroEntity)

    @Query("SELECT * FROM library_libros WHERE institutionId = :instId")
    fun getAll(instId: String): Flow<List<LibroEntity>>

    @Query("SELECT * FROM library_libros WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: String, instId: String): LibroEntity?

    @Query("SELECT * FROM library_libros WHERE (titulo LIKE '%' || :query || '%' OR autor LIKE '%' || :query || '%') AND institutionId = :instId")
    suspend fun search(query: String, instId: String): List<LibroEntity>

    @Delete
    suspend fun delete(libro: LibroEntity)
}

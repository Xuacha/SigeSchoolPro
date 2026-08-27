package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.DetalleOfertaAcademicaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetalleOfertaAcademicaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DetalleOfertaAcademicaEntity): Long

    @Update
    suspend fun update(entity: DetalleOfertaAcademicaEntity)

    @Query("SELECT * FROM academic_detalles_oferta WHERE institutionId = :instId AND ofertaAcademicaId = :ofertaId")
    fun getByOferta(instId: String, ofertaId: Long): Flow<List<DetalleOfertaAcademicaEntity>>

    @Query("SELECT * FROM academic_detalles_oferta WHERE institutionId = :instId AND ofertaAcademicaId = :ofertaId")
    suspend fun getByOfertaSync(instId: String, ofertaId: Long): List<DetalleOfertaAcademicaEntity>

    @Query("SELECT * FROM academic_detalles_oferta WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): DetalleOfertaAcademicaEntity?

    @Query("DELETE FROM academic_detalles_oferta WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_detalles_oferta WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<DetalleOfertaAcademicaEntity>

    @Query("SELECT * FROM academic_detalles_oferta WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<DetalleOfertaAcademicaEntity>

    @Query("UPDATE academic_detalles_oferta SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}

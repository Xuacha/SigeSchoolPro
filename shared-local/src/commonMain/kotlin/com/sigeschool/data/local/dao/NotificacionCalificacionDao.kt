package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.NotificacionCalificacionEntity

@Dao
interface NotificacionCalificacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notificacion: NotificacionCalificacionEntity): Long

    @Update
    suspend fun update(notificacion: NotificacionCalificacionEntity)

    @Query("SELECT * FROM notificaciones_calificaciones WHERE calificacionId = :calificacionId AND institutionId = :instId")
    suspend fun getByCalificacion(calificacionId: String, instId: String): NotificacionCalificacionEntity?

    @Query("SELECT * FROM notificaciones_calificaciones WHERE institutionId = :instId AND enviadoDocente = 0")
    suspend fun getPendientesDocente(instId: String): List<NotificacionCalificacionEntity>

    @Query("SELECT * FROM notificaciones_calificaciones WHERE institutionId = :instId AND enviadoCoordinador = 0")
    suspend fun getPendientesCoordinador(instId: String): List<NotificacionCalificacionEntity>

    @Query("UPDATE notificaciones_calificaciones SET enviadoDocente = 1, fechaEnvioDocente = :fecha WHERE id = :id")
    suspend fun marcarDocenteEnviado(id: Long, fecha: Long)

    @Query("UPDATE notificaciones_calificaciones SET enviadoCoordinador = 1, fechaEnvioCoordinador = :fecha WHERE id = :id")
    suspend fun marcarCoordinadorEnviado(id: Long, fecha: Long)

    @Query("UPDATE notificaciones_calificaciones SET enviadoEstudiante = 1, fechaEnvioEstudiante = :fecha WHERE id = :id")
    suspend fun marcarEstudianteEnviado(id: Long, fecha: Long)

    @Query("UPDATE notificaciones_calificaciones SET enviadoAcudiente = 1, fechaEnvioAcudiente = :fecha WHERE id = :id")
    suspend fun marcarAcudienteEnviado(id: Long, fecha: Long)
}

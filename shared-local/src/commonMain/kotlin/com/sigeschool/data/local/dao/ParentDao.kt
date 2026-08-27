package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AcudienteEntity
import com.sigeschool.data.local.entity.EstudianteAcudienteEntity
import com.sigeschool.data.local.entity.PreferenciaNotificacionEntity
import com.sigeschool.data.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAcudiente(acudiente: AcudienteEntity)

    @Update
    suspend fun updateAcudiente(acudiente: AcudienteEntity)

    @Query("SELECT * FROM acudientes WHERE idAcudiente = :id")
    suspend fun getAcudienteById(id: String): AcudienteEntity?

    @Query("SELECT * FROM acudientes WHERE correoElectronico = :email")
    suspend fun getAcudienteByEmail(email: String): AcudienteEntity?

    @Query("SELECT * FROM acudientes WHERE numeroDocumento = :document")
    suspend fun getAcudienteByDocument(document: String): AcudienteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVinculacion(vinculacion: EstudianteAcudienteEntity)

    @Query("DELETE FROM estudiantes_acudientes WHERE idEstudiante = :idEstudiante AND idAcudiente = :idAcudiente")
    suspend fun deleteVinculacion(idEstudiante: String, idAcudiente: String)

    @Query("""
        SELECT s.* FROM students s
        INNER JOIN estudiantes_acudientes ea ON s.id = ea.idEstudiante
        WHERE ea.idAcudiente = :idAcudiente
    """)
    suspend fun getEstudiantesByAcudiente(idAcudiente: String): List<StudentEntity>

    @Query("""
        SELECT a.* FROM acudientes a
        INNER JOIN estudiantes_acudientes ea ON a.idAcudiente = ea.idAcudiente
        WHERE ea.idEstudiante = :idEstudiante
    """)
    suspend fun getAcudientesByEstudiante(idEstudiante: String): List<AcudienteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferencias(preferencias: PreferenciaNotificacionEntity)

    @Query("SELECT * FROM acudiente_preferencias WHERE idAcudiente = :idAcudiente")
    suspend fun getPreferenciasByAcudiente(idAcudiente: String): PreferenciaNotificacionEntity?

    @Query("DELETE FROM acudientes")
    suspend fun deleteAllAcudientes()

    @Query("DELETE FROM estudiantes_acudientes")
    suspend fun deleteAllVinculaciones()
}

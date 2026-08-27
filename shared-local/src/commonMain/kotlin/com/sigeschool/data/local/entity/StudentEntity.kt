package com.sigeschool.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val userId: String? = null,
    val firstName: String,
    val lastName: String,
    val documentId: String,
    val gender: String? = null,
    val ethnicity: String? = null,
    val ethnicCommunity: String? = null,
    val disability: String? = null,
    val disabilityAdjustments: String? = null,
    val photoPath: String?,
    val qrCode: String,
    val cursoId: Long? = null,
    val consentAcceptedAt: Long? = null,
    val consentVersion: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0,
    @ColumnInfo(defaultValue = "0")
    val isDuplicate: Boolean = false,
    val mergedIntoId: String? = null,
    val deletedAt: Long? = null,
    val deletedReason: String? = null,
    val deletedByUserId: String? = null,
    val documentType: String? = null,
    val birthDate: String? = null,
    val age: Int? = null,
    val email: String? = null,
    val phone: String? = null,
    val address: String? = null,
    val neighborhood: String? = null,
    val stratum: Int? = null,
    val educationLevel: String? = null,
    val previousSchool: String? = null,
    val selectedPrograms: String? = null,
    val howDidYouHear: String? = null,
    @ColumnInfo(defaultValue = "ENROLLED")
    val status: String = "ENROLLED",
    val withdrawalReason: String? = null,
    val withdrawalDate: Long? = null,
    val statusUpdatedAt: Long = 0,
    val photoUpdatedAt: Long? = null,
    @ColumnInfo(defaultValue = "MATRICULADO")
    val estadoMatricula: String = "MATRICULADO",
    val fechaRetiro: Long? = null,
    val motivoRetiro: String? = null,
    @ColumnInfo(defaultValue = "0")
    val diasInasistenciaConsecutiva: Int = 0,
    val ultimaFechaAsistencia: Long? = null,
    @ColumnInfo(defaultValue = "0")
    val alertaEnviada30Dias: Boolean = false,
    val guardianFirstName: String? = null,
    val guardianLastName: String? = null,
    val guardianDocumentId: String? = null,
    val guardianRelationship: String? = null,
    val guardianPhone: String? = null,
    val guardianEmail: String? = null,
    @ColumnInfo(defaultValue = "0")
    val esExterno: Boolean = false,
    val institucionOrigen: String? = null,
    val externoId: String? = null
)

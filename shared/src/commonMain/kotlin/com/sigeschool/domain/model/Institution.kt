package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Institution(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    @SerialName("whatsapp_number")
    val whatsappNumber: String = "",  // Formato internacional (+51...)
    val email: String = "",
    val nit: String = "",
    @SerialName("dane_number")
    val daneNumber: String = "",
    @SerialName("resolution_number")
    val resolutionNumber: String = "",
    @SerialName("territorial_entity")
    val territorialEntity: String = "",
    @SerialName("owner_id")
    val ownerId: String = "",           // Usuario que creó la institución
    @SerialName("educational_models")
    val educationalModels: List<String> = emptyList(), // JARDIN, TRANSICION, PRIMARIA, BACHILLERATO, ETDH, etc.
    @SerialName("created_at")
    val createdAt: String = ""
)

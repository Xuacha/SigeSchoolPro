package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class TeacherCategory(val displayName: String, val baseSalary: Double) {
    // Decreto 1278 - Grado 1 (Normalista/Tecnólogo)
    D1278_G1_A("1278 - Grado 1A (Normalista)", 3036203.0),
    D1278_G1_B("1278 - Grado 1B (Normalista)", 3870303.0),
    D1278_G1_C("1278 - Grado 1C (Normalista)", 4989102.0),
    D1278_G1_D("1278 - Grado 1D (Normalista)", 6184872.0),
    
    // Decreto 1278 - Grado 2 (Licenciado/Profesional)
    D1278_G2_A("1278 - Grado 2A (Licenciado)", 3821274.0),
    D1278_G2_B("1278 - Grado 2B (Licenciado)", 4992967.0),
    D1278_G2_C("1278 - Grado 2C (Licenciado)", 5831713.0),
    D1278_G2_D("1278 - Grado 2D (Licenciado)", 6968892.0),
    
    // Decreto 2277 (Antiguo) - Algunos ejemplos
    D2277_G1("2277 - Grado 1", 2246189.0),
    D2277_G7("2277 - Grado 7", 3232157.0),
    D2277_G14("2277 - Grado 14 (Máximo)", 7374130.0),
    
    // Otros
    ORIENTADOR("Docente Orientador", 3821274.0), // Base ejemplo Licenciado A
    SMMLV_2026("Salario Mínimo 2026", 1750905.0)
}

@Serializable
data class PayrollCalculation(
    val employeeId: String = "",
    val date: Long = 0,
    val basicSalary: Double,
    val daysWorked: Int,
    val transportAllowance: Double = 0.0,
    val healthDeduction: Double = 0.0,
    val pensionDeduction: Double = 0.0,
    val advances: Double = 0.0,
    val extraHours: Double = 0.0,
    val totalDevengado: Double,
    val totalDeducciones: Double,
    val netPay: Double
)

@Serializable
data class VacationRequest(
    val id: String = "",
    val employeeId: String,
    val startDate: Long,
    val endDate: Long,
    val days: Int,
    val status: String = "PENDIENTE", // PENDIENTE, APROBADA, RECHAZADA
    val observations: String = ""
)

@Serializable
data class AdvanceRequest(
    val id: String = "",
    val employeeId: String,
    val amountRequested: Double,
    val reason: String = "",
    val status: String = "PENDIENTE", // PENDIENTE, APROBADO, RECHAZADO
    val requestDate: Long = 0,
    val maxAllowed: Double = 0.0
)

@Serializable
data class LiquidationCalculation(
    val lastSalary: Double,
    val startDate: Long,
    val endDate: Long,
    val totalDays: Int,
    val cesantias: Double,
    val interesesCesantias: Double,
    val primaServicios: Double,
    val vacacionesCompensadas: Double,
    val totalLiquidation: Double
)

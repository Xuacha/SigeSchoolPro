package com.sigeschool.domain.util

import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.LiquidationCalculation
import kotlin.math.roundToInt

object LaboralCalculator {
    // Valores Proyectados 2026
    const val SMMLV_2026 = 1750905.0
    const val AUX_TRANSPORTE_2026 = 249095.0
    const val MAX_ADVANCE_PERCENTAGE = 0.50 // Tope legal sugerido del 50%

    fun calculatePayroll(
        basicSalary: Double, 
        daysWorked: Int, 
        advances: Double = 0.0
    ): PayrollCalculation {
        val proportionalSalary = (basicSalary / 30) * daysWorked
        val transportAllowance = if (basicSalary <= SMMLV_2026 * 2) {
            (AUX_TRANSPORTE_2026 / 30) * daysWorked
        } else 0.0

        // Deducciones obligatorias (Salud y Pensión 4% cada una)
        val healthDeduction = proportionalSalary * 0.04
        val pensionDeduction = proportionalSalary * 0.04
        
        val totalDevengado = proportionalSalary + transportAllowance
        
        // LEY COLOMBIANA: Protección al Salario
        // El trabajador no puede recibir menos del 50% de su salario básico tras deducciones (incluyendo adelantos)
        // Excepto si son deudas con cooperativas o embargos, pero para adelantos de empresa aplica el tope.
        val totalMandatory = healthDeduction + pensionDeduction
        val minNetAllowed = basicSalary * 0.5
        val maxDeductionAllowed = totalDevengado - minNetAllowed
        
        val maxAvailableForAdvances = (maxDeductionAllowed - totalMandatory).coerceAtLeast(0.0)
        
        val finalAdvance = if (advances > maxAvailableForAdvances) {
            // Si el adelanto solicitado supera el tope legal, se limita para proteger el 50% del salario.
            maxAvailableForAdvances
        } else {
            advances
        }

        val totalDeducciones = totalMandatory + finalAdvance
        val netPay = totalDevengado - totalDeducciones

        return PayrollCalculation(
            employeeId = "", // Valor por defecto para cálculos puros
            date = 0,
            basicSalary = basicSalary,
            daysWorked = daysWorked,
            transportAllowance = transportAllowance,
            healthDeduction = healthDeduction,
            pensionDeduction = pensionDeduction,
            advances = finalAdvance,
            totalDevengado = totalDevengado,
            totalDeducciones = totalDeducciones,
            netPay = netPay
        )
    }

    fun calculateMaxAdvance(accruedSalary: Double): Double {
        return accruedSalary * MAX_ADVANCE_PERCENTAGE
    }

    fun calculateLiquidation(lastSalary: Double, startDate: Long, endDate: Long): LiquidationCalculation {
        val diffMillis = endDate - startDate
        val totalDays = (diffMillis / (1000 * 60 * 60 * 24)).toInt() + 1
        
        // Base para prestaciones (Incluye auxilio de transporte si aplica)
        val baseSalary = if (lastSalary <= SMMLV_2026 * 2) lastSalary + AUX_TRANSPORTE_2026 else lastSalary
        
        val cesantias = (baseSalary * totalDays) / 360
        val interesesCesantias = (cesantias * totalDays * 0.12) / 360
        val primaServicios = (baseSalary * totalDays) / 360
        val vacaciones = (lastSalary * totalDays) / 720

        return LiquidationCalculation(
            lastSalary = lastSalary,
            startDate = startDate,
            endDate = endDate,
            totalDays = totalDays,
            cesantias = cesantias,
            interesesCesantias = interesesCesantias,
            primaServicios = primaServicios,
            vacacionesCompensadas = vacaciones,
            totalLiquidation = cesantias + interesesCesantias + primaServicios + vacaciones
        )
    }

    /**
     * Calcula las provisiones mensuales para prestaciones sociales y aportes patronales.
     * Cesantías: 8.33%, Int. Cesantías: 1%, Prima: 8.33%, Vacaciones: 4.17%
     * ARL: Según nivel de riesgo (I: 0.522%, II: 1.044%, III: 2.436%, IV: 4.350%, V: 6.960%)
     */
    fun calculateMonthlyProvisions(
        calculation: PayrollCalculation,
        arlRiskLevel: Int = 1
    ): Map<String, Double> {
        // La base para cesantías, prima e intereses incluye el auxilio de transporte
        val basePrestaciones = calculation.totalDevengado 
        // La base para vacaciones y ARL no incluye el auxilio de transporte (IBC)
        val ibc = calculation.totalDevengado - calculation.transportAllowance

        val arlPercentage = when (arlRiskLevel) {
            1 -> 0.00522
            2 -> 0.01044
            3 -> 0.02436
            4 -> 0.04350
            5 -> 0.06960
            else -> 0.00522
        }

        return mapOf(
            "cesantias" to basePrestaciones * 0.0833,
            "interesesCesantias" to basePrestaciones * 0.01,
            "primaServicios" to basePrestaciones * 0.0833,
            "vacaciones" to ibc * 0.0417,
            "arl" to ibc * arlPercentage
        )
    }
}

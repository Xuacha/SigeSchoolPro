package com.sigeschool.domain.util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LaboralCalculatorTest {

    @Test
    fun `test payroll calculation for standard teacher`() {
        val basicSalary = 3821274.0 // D1278_G2_A
        val daysWorked = 30
        val advances = 500000.0
        
        val result = LaboralCalculator.calculatePayroll(basicSalary, daysWorked, advances)
        
        // As it is > 2 * SMMLV (1,750,905 * 2 = 3,501,810), it should NOT have transport allowance
        assertEquals(0.0, result.transportAllowance, "Should not have transport allowance")
        assertEquals(basicSalary * 0.04, result.healthDeduction, "Health deduction mismatch")
        assertEquals(basicSalary * 0.04, result.pensionDeduction, "Pension deduction mismatch")
        assertEquals(advances, result.advances, "Advances should be accepted within limit")
        
        val expectedNet = basicSalary - (result.healthDeduction + result.pensionDeduction + advances)
        assertEquals(expectedNet, result.netPay, 0.1, "Net pay mismatch")
    }

    @Test
    fun `test payroll calculation with advance limit`() {
        val basicSalary = 2000000.0 
        val daysWorked = 30
        val hugeAdvance = 1500000.0 // More than 50%
        
        val result = LaboralCalculator.calculatePayroll(basicSalary, daysWorked, hugeAdvance)
        
        val maxAllowed = result.totalDevengado * 0.5
        assertEquals(maxAllowed, result.advances, "Advance should be capped at 50%")
    }

    @Test
    fun `test liquidation for 1 year of work`() {
        val lastSalary = 3000000.0
        val startDate = 0L
        val endDate = 360L * 24 * 60 * 60 * 1000 - 1000 // approx 360 days
        
        val result = LaboralCalculator.calculateLiquidation(lastSalary, startDate, endDate)
        
        // 3000000 is < 2 * SMMLV (3,501,810), so base includes transport allowance
        val expectedBase = lastSalary + LaboralCalculator.AUX_TRANSPORTE_2026
        
        // Cesantias: (Base * days) / 360 -> approx Base for 1 year
        assertTrue(result.cesantias >= expectedBase * 0.99, "Cesantias mismatch")
        assertTrue(result.primaServicios >= expectedBase * 0.99, "Prima mismatch")
        
        // Vacaciones: (Salary * days) / 720 -> approx Salary / 2 for 1 year
        assertTrue(result.vacacionesCompensadas >= (lastSalary / 2) * 0.99, "Vacation mismatch")
    }

    @Test
    fun `test SMMLV 2026 constants`() {
        assertEquals(1750905.0, LaboralCalculator.SMMLV_2026)
        assertEquals(249095.0, LaboralCalculator.AUX_TRANSPORTE_2026)
    }
}

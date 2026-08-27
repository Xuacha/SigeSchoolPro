package com.sigeschool.data.remote

import com.sigeschool.domain.model.VacationRequest
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.AdvanceRequest
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class LaboralRemoteDataSource(private val postgrest: Postgrest) {
    private val vacationTable = "vacation_requests"
    private val payrollTable = "payroll_calculations"
    private val advancesTable = "advance_requests"

    suspend fun getVacationRequests(employeeId: String): List<VacationRequest> {
        return postgrest.from(vacationTable).select {
            filter {
                eq("employeeId", employeeId)
            }
        }.decodeList<VacationRequest>()
    }

    suspend fun submitVacationRequest(request: VacationRequest): Boolean {
        return try {
            postgrest.from(vacationTable).insert(request)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getAdvanceRequests(employeeId: String): List<AdvanceRequest> {
        return postgrest.from(advancesTable).select {
            filter {
                eq("employeeId", employeeId)
            }
        }.decodeList<AdvanceRequest>()
    }

    suspend fun submitAdvanceRequest(request: AdvanceRequest): Boolean {
        return try {
            postgrest.from(advancesTable).insert(request)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateAdvanceStatus(advanceId: String, status: String): Boolean {
        return try {
            postgrest.from(advancesTable).update({
                set("status", status)
            }) {
                filter {
                    eq("id", advanceId)
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun savePayrollCalculation(calculation: PayrollCalculation, employeeId: String): Boolean {
        return try {
            // Creamos un mapa para insertar ya que PayrollCalculation es un data class de dominio
            // y tal vez no queramos anotarlo con @Serializable si ya lo está o si tiene campos extra.
            // En Supabase/Postgrest-kt se puede insertar el objeto directamente si está anotado.
            // Asumimos que la tabla espera los campos del cálculo más el ID del empleado.
            postgrest.from(payrollTable).insert(mapOf(
                "employeeId" to employeeId,
                "basicSalary" to calculation.basicSalary,
                "daysWorked" to calculation.daysWorked,
                "transportAllowance" to calculation.transportAllowance,
                "healthDeduction" to calculation.healthDeduction,
                "pensionDeduction" to calculation.pensionDeduction,
                "advances" to calculation.advances,
                "extraHours" to calculation.extraHours,
                "totalDevengado" to calculation.totalDevengado,
                "totalDeducciones" to calculation.totalDeducciones,
                "netPay" to calculation.netPay,
                "createdAt" to kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
            ))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun upsertVacationRequest(request: VacationRequest): Boolean {
        return try {
            postgrest.from(vacationTable).upsert(request)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun upsertAdvanceRequest(request: AdvanceRequest): Boolean {
        return try {
            postgrest.from(advancesTable).upsert(request)
            true
        } catch (e: Exception) {
            false
        }
    }
}

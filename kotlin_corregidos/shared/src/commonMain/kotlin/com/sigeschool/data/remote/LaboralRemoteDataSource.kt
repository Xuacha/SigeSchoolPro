package com.sigeschool.data.remote

import com.sigeschool.domain.model.VacationRequest
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.AdvanceRequest
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

// FIX: PayrollCalculation es un DTO puro (solo los números del cálculo,
// sin id/empleado/fecha). Para poder persistirlo como historial se
// necesita un registro que lo envuelva con esos metadatos.
@Serializable
data class PayrollCalculationRecord(
    val id: String,
    @SerialName("employee_id")
    val employeeId: String,
    @SerialName("calculated_at")
    val calculatedAt: Long,
    val calculation: PayrollCalculation
)

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

    // FIX CRÍTICO: esta función era un stub — el insert real estaba
    // comentado y la función devolvía `true` sin guardar nada. Cada
    // vez que un usuario calculaba y "guardaba" una nómina, el sistema
    // reportaba éxito pero el registro se perdía silenciosamente: no
    // quedaba historial de pagos, lo cual además de ser un defecto
    // funcional grave es un riesgo legal/contable (no hay soporte
    // documental de la liquidación de nómina, exigible por la UGPP y
    // por el propio empleado).
    suspend fun savePayrollCalculation(calculation: PayrollCalculation, employeeId: String): Boolean {
        return try {
            val now = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
            val record = PayrollCalculationRecord(
                id = "${employeeId}_$now",
                employeeId = employeeId,
                calculatedAt = now,
                calculation = calculation
            )
            postgrest.from(payrollTable).insert(record)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getPayrollHistory(employeeId: String): List<PayrollCalculationRecord> {
        return try {
            postgrest.from(payrollTable).select {
                filter { eq("employee_id", employeeId) }
                order("calculated_at", io.github.jan.supabase.postgrest.query.Order.DESCENDING)
            }.decodeList<PayrollCalculationRecord>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

package com.sigeschool.data.remote

import com.sigeschool.domain.model.SalaryRecord
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.from

/**
 * SEC-02: Protección de datos de nómina (PII y Financiero).
 * Los salarios son la información más sensible. Se requiere filtrado estricto.
 */
class SalaryRemoteDataSource(private val postgrest: Postgrest) {
    private val table = "salary_records"

    suspend fun getSalaryRecords(institutionId: String): List<SalaryRecord> {
        return postgrest.from(table)
            .select(columns = Columns.raw("*")) {
                filter {
                    // SEC-02: Filtrado por institución
                    eq("institution_id", institutionId)
                }
            }.decodeList<SalaryRecord>()
    }

    suspend fun getSalaryRecordsByEmployee(employeeId: String, institutionId: String): List<SalaryRecord> {
        return postgrest.from(table)
            .select(columns = Columns.raw("*")) {
                filter {
                    eq("employee_id", employeeId)
                    // SEC-02: Evitar que un empleado vea salarios de otra institución si su ID colisiona (IDOR)
                    eq("institution_id", institutionId)
                }
            }.decodeList<SalaryRecord>()
    }

    suspend fun upsertSalaryRecord(record: SalaryRecord, institutionId: String): Boolean {
        // SEC-14: Validar propiedad antes de actualizar
        if (record.institutionId != institutionId) return false
        
        return try {
            postgrest.from(table).upsert(record)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteSalaryRecord(id: String, institutionId: String): Boolean {
        return try {
            postgrest.from(table).delete {
                filter {
                    eq("id", id)
                    // SEC-14: Defensa en profundidad
                    eq("institution_id", institutionId)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

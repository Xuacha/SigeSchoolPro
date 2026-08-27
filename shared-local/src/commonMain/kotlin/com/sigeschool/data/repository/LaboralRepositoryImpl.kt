package com.sigeschool.data.repository

import com.sigeschool.data.datasource.LaboralLocalDataSource
import com.sigeschool.data.remote.LaboralRemoteDataSource
import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.model.VacationRequest
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.LiquidationCalculation
import com.sigeschool.domain.model.AdvanceRequest
import com.sigeschool.domain.repository.LaboralRepository
import com.sigeschool.domain.repository.PucRepository
import com.sigeschool.domain.repository.EmployeeRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class LaboralRepositoryImpl(
    private val localDataSource: LaboralLocalDataSource,
    private val remoteDataSource: LaboralRemoteDataSource,
    private val pucRepository: PucRepository,
    private val employeeRepository: EmployeeRepository,
    private val auditRepository: AuditRepository
) : LaboralRepository {

    override fun getVacationRequests(employeeId: String): Flow<Resource<List<VacationRequest>>> {
        return networkBoundResource<List<VacationRequest>, List<VacationRequest>>(
            query = { localDataSource.getVacationRequests(employeeId) },
            fetch = { remoteDataSource.getVacationRequests(employeeId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertVacationRequest(it) }
            }
        )
    }

    override suspend fun submitVacationRequest(request: VacationRequest): Resource<Boolean> {
        return try {
            localDataSource.insertVacationRequest(request)
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.upsertVacationRequest(request)
            }
            if (success) {
                localDataSource.markVacationSynced(request.id)
                Resource.Success(true)
            } else {
                Resource.Error("Guardado localmente. Se sincronizará al recuperar conexión.", true)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al procesar solicitud")
        }
    }

    override suspend fun savePayrollCalculation(employeeId: String, calculation: PayrollCalculation): Resource<Boolean> {
        return try {
            val date = Clock.System.now().toEpochMilliseconds()
            localDataSource.insertPayrollCalculation(calculation, employeeId, date)
            
            val now = Clock.System.now()
            
            // 1. Asiento Contable de Pago de Nómina
            val payrollEntry = com.sigeschool.domain.model.AccountingEntry(
                id = com.sigeschool.domain.util.randomUUID(),
                date = now.toString(),
                description = "Nómina Empleado: $employeeId - Período: $now",
                entries = listOf(
                    com.sigeschool.domain.model.EntryDetail(
                        accountCode = "5105", // Gasto Personal (Nómina)
                        accountName = "Gastos de Personal",
                        debit = calculation.totalDevengado,
                        credit = 0.0
                    ),
                    com.sigeschool.domain.model.EntryDetail(
                        accountCode = "2370", // Retenciones y aportes de nómina
                        accountName = "Aportes Salud/Pensión",
                        debit = 0.0,
                        credit = calculation.healthDeduction + calculation.pensionDeduction
                    ),
                    com.sigeschool.domain.model.EntryDetail(
                        accountCode = "1110", // Bancos
                        accountName = "Bancos/Caja",
                        debit = 0.0,
                        credit = calculation.netPay
                    )
                ),
                totalDebit = calculation.totalDevengado,
                totalCredit = calculation.totalDevengado
            )
            pucRepository.saveEntry(payrollEntry)

            // 2. Asiento de Provisiones Prestacionales (Pasivo Laboral Real)
            val provisions = com.sigeschool.domain.util.LaboralCalculator.calculateMonthlyProvisions(calculation)
            val totalProvisions = provisions.values.sum()
            
            val provisionsEntry = com.sigeschool.domain.model.AccountingEntry(
                id = com.sigeschool.domain.util.randomUUID(),
                date = now.toString(),
                description = "Provisiones Prestacionales - Empleado: $employeeId - $now",
                entries = listOf(
                    // Gastos (Débitos)
                    com.sigeschool.domain.model.EntryDetail("510569", "Gasto Cesantías", provisions["cesantias"] ?: 0.0, 0.0),
                    com.sigeschool.domain.model.EntryDetail("510570", "Gasto Int. Cesantías", provisions["interesesCesantias"] ?: 0.0, 0.0),
                    com.sigeschool.domain.model.EntryDetail("510572", "Gasto Prima Servicios", provisions["primaServicios"] ?: 0.0, 0.0),
                    com.sigeschool.domain.model.EntryDetail("510575", "Gasto Vacaciones", provisions["vacaciones"] ?: 0.0, 0.0),
                    // Pasivos (Créditos - Cuenta 2610 Provisiones Laborales)
                    com.sigeschool.domain.model.EntryDetail("2610", "Provisiones Laborales", 0.0, totalProvisions)
                ),
                totalDebit = totalProvisions,
                totalCredit = totalProvisions
            )
            pucRepository.saveEntry(provisionsEntry)

            val success = withContext(Dispatchers.Default) {
                remoteDataSource.savePayrollCalculation(calculation, employeeId)
            }
            
            auditRepository.log(
                action = "SAVE_PAYROLL",
                resource = "payroll/$employeeId",
                payload = mapOf("amount" to calculation.netPay, "date" to date)
            )
            
            if (success) {
                Resource.Success(true)
            } else {
                Resource.Error("Cálculo y provisiones guardadas localmente.", true)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar nómina y provisiones")
        }
    }

    override suspend fun getLiquidationEstimate(employeeId: String): Resource<LiquidationCalculation?> {
        return try {
            val employee = employeeRepository.getEmployeeById(employeeId) 
                ?: return Resource.Error("Empleado no encontrado")
            
            val now = Clock.System.now().toEpochMilliseconds()
            val totalDays = ((now - employee.hireDate) / (1000L * 60 * 60 * 24)).toInt()
            val lastSalary = calcularSalarioBase(employeeId)
            
            // Cálculos laborales de ley (Simplificado)
            val cesantias = (lastSalary * totalDays) / 360
            val interesesCesantias = (cesantias * 0.12 * totalDays) / 360
            val primaServicios = (lastSalary * (totalDays % 180)) / 360 // Proporcional al semestre
            val vacaciones = (lastSalary * totalDays) / 720
            
            val total = cesantias + interesesCesantias + primaServicios + vacaciones
            
            Resource.Success(
                LiquidationCalculation(
                    lastSalary = lastSalary,
                    startDate = employee.hireDate,
                    endDate = now,
                    totalDays = totalDays,
                    cesantias = cesantias,
                    interesesCesantias = interesesCesantias,
                    primaServicios = primaServicios,
                    vacacionesCompensadas = vacaciones,
                    totalLiquidation = total
                )
            )
        } catch (e: Exception) {
            Resource.Error("Error al calcular estimación: ${e.message}")
        }
    }

    override suspend fun calcularAntiguedad(employeeId: String): Int {
        val employee = employeeRepository.getEmployeeById(employeeId) ?: return 0
        val now = Clock.System.now().toEpochMilliseconds()
        val diffMillis = now - employee.hireDate
        val years = diffMillis / (1000L * 60 * 60 * 24 * 365)
        return years.toInt()
    }

    override suspend fun calcularSalarioBase(employeeId: String): Double {
        val employee = employeeRepository.getEmployeeById(employeeId) ?: return 2000000.0
        val base = 2000000.0 // Base default
        val antiguedad = calcularAntiguedad(employeeId)
        return base * (1 + (antiguedad * 0.05)) // 5% de incremento anual
    }

    override fun getAdvanceRequests(employeeId: String): Flow<Resource<List<AdvanceRequest>>> {
        return networkBoundResource(
            query = { localDataSource.getAdvanceRequests(employeeId) },
            fetch = { remoteDataSource.getAdvanceRequests(employeeId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertAdvanceRequest(it) }
            }
        )
    }

    override suspend fun submitAdvanceRequest(request: AdvanceRequest): Resource<Boolean> {
        return try {
            localDataSource.insertAdvanceRequest(request)
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.upsertAdvanceRequest(request)
            }
            if (success) {
                Resource.Success(true)
            } else {
                Resource.Error("Solicitud de adelanto registrada offline.", true)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al enviar adelanto")
        }
    }

    override suspend fun updateAdvanceStatus(advanceId: String, status: String): Resource<Boolean> {
        return try {
            val success = remoteDataSource.updateAdvanceStatus(advanceId, status)
            // Aquí se debería actualizar también localmente si tuviéramos un getById
            Resource.Success(success)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar estado")
        }
    }

    override suspend fun syncWithCloud() {
        withContext(Dispatchers.Default) {
            try {
                // Sincronizar Vacaciones
                localDataSource.getUnsyncedVacations().forEach {
                    if (remoteDataSource.upsertVacationRequest(it)) {
                        localDataSource.markVacationSynced(it.id)
                    }
                }
                // Sincronizar Adelantos
                localDataSource.getUnsyncedAdvances().forEach {
                    if (remoteDataSource.upsertAdvanceRequest(it)) {
                        localDataSource.markAdvanceSynced(it.id)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun generateBulkPayrollReport(institutionName: String): Resource<ByteArray> {
        return try {
            val now = Clock.System.now().toEpochMilliseconds()
            val thirtyDaysAgo = now - (30L * 24 * 60 * 60 * 1000)
            
            // 1. Obtener históricos de nómina del último mes
            val history = localDataSource.getPayrollHistoryByDateRange(thirtyDaysAgo, now)
            
            if (history.isEmpty()) {
                return Resource.Error("No hay registros de nómina en los últimos 30 días.")
            }

            // 2. Mapear a datos de reporte real
            val reportData = history.map { calculation: com.sigeschool.domain.model.PayrollCalculation ->
                val employee = employeeRepository.getEmployeeById(calculation.employeeId)
                Triple<com.sigeschool.domain.model.PayrollCalculation, String, String>(
                    calculation,
                    employee?.fullName ?: "Empleado ${calculation.employeeId}",
                    calculation.employeeId
                )
            }
            
            // 3. Generar PDF real
            val pdf = com.sigeschool.util.PdfPlatformGenerator.generateBulkPayrollReport(reportData)
            Resource.Success(pdf)
        } catch (e: Exception) {
            Resource.Error("Error al generar reporte masivo: ${e.message}")
        }
    }
}

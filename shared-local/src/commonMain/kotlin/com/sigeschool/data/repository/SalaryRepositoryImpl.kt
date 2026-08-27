package com.sigeschool.data.repository

import com.sigeschool.data.datasource.SalaryLocalDataSource
import com.sigeschool.data.remote.SalaryRemoteDataSource
import com.sigeschool.domain.model.SalaryRecord
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SalaryRepositoryImpl(
    private val localDataSource: SalaryLocalDataSource,
    private val remoteDataSource: SalaryRemoteDataSource
) : SalaryRepository {

    override fun getSalaryRecords(institutionId: String): Flow<Resource<List<SalaryRecord>>> {
        return networkBoundResource(
            query = { localDataSource.getSalaryRecords(institutionId) },
            fetch = { remoteDataSource.getSalaryRecords(institutionId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertSalaryRecord(it.copy(sincronizado = true)) }
            }
        )
    }

    override fun getSalaryRecordsByEmployee(employeeId: String): Flow<Resource<List<SalaryRecord>>> {
        // En un caso real, deberíamos obtener el institutionId del SessionManager
        // Por ahora, pasamos un valor por defecto o intentamos inferirlo si la interfaz lo permitiera.
        return networkBoundResource(
            query = { localDataSource.getSalaryRecordsByEmployee(employeeId) },
            fetch = { remoteDataSource.getSalaryRecordsByEmployee(employeeId, "DEFAULT") },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertSalaryRecord(it.copy(sincronizado = true)) }
            }
        )
    }

    override suspend fun addSalaryRecord(record: SalaryRecord): Resource<Boolean> {
        return try {
            localDataSource.insertSalaryRecord(record.copy(sincronizado = false))
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.upsertSalaryRecord(record, record.institutionId)
            }
            if (success) {
                localDataSource.insertSalaryRecord(record.copy(sincronizado = true))
                Resource.Success(true)
            } else {
                Resource.Error("Guardado localmente. Se sincronizará pronto.", true)
            }
        } catch (e: Exception) {
            Resource.Error("Error al guardar: ${e.message}")
        }
    }

    override suspend fun deleteSalaryRecord(record: SalaryRecord): Resource<Boolean> {
        return try {
            localDataSource.deleteSalaryRecord(record)
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.deleteSalaryRecord(record.id, record.institutionId)
            }
            Resource.Success(success)
        } catch (e: Exception) {
            Resource.Error("Error al eliminar: ${e.message}")
        }
    }

    override suspend fun syncWithCloud() {
        withContext(Dispatchers.Default) {
            try {
                val unsynced = localDataSource.getUnsyncedRecords()
                unsynced.forEach { record ->
                    if (remoteDataSource.upsertSalaryRecord(record, record.institutionId)) {
                        localDataSource.insertSalaryRecord(record.copy(sincronizado = true))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun calculateNomina(
        employeeId: String,
        month: Int,
        year: Int,
        institutionId: String
    ): Resource<SalaryRecord> {
        // Lógica de cálculo simplificada adaptada del prototipo
        val record = SalaryRecord(
            id = "SAL-${employeeId}-${month}-${year}",
            employeeId = employeeId,
            institutionId = institutionId,
            amount = 2550.0,
            date = kotlinx.datetime.Clock.System.now().toEpochMilliseconds(),
            description = "Nómina Período $month/$year",
            sincronizado = false
        )
        return Resource.Success(record)
    }
}

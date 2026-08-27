package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.NominaEntity
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class NominaDao_Impl(
  __db: RoomDatabase,
) : NominaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfNominaEntity: EntityInsertAdapter<NominaEntity>

  private val __deleteAdapterOfNominaEntity: EntityDeleteOrUpdateAdapter<NominaEntity>

  private val __updateAdapterOfNominaEntity: EntityDeleteOrUpdateAdapter<NominaEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfNominaEntity = object : EntityInsertAdapter<NominaEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `payroll_nominas` (`id`,`employeeId`,`institutionId`,`fechaEmision`,`periodoInicio`,`periodoFin`,`salarioBase`,`bonificaciones`,`deducciones`,`totalNeto`,`estado`,`metodoPago`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: NominaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.employeeId)
        statement.bindText(3, entity.institutionId)
        statement.bindLong(4, entity.fechaEmision)
        statement.bindLong(5, entity.periodoInicio)
        statement.bindLong(6, entity.periodoFin)
        statement.bindDouble(7, entity.salarioBase)
        statement.bindDouble(8, entity.bonificaciones)
        statement.bindDouble(9, entity.deducciones)
        statement.bindDouble(10, entity.totalNeto)
        statement.bindText(11, entity.estado)
        statement.bindText(12, entity.metodoPago)
        statement.bindLong(13, entity.syncStatus.toLong())
        statement.bindLong(14, entity.lastModified)
      }
    }
    this.__deleteAdapterOfNominaEntity = object : EntityDeleteOrUpdateAdapter<NominaEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `payroll_nominas` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NominaEntity) {
        statement.bindText(1, entity.id)
      }
    }
    this.__updateAdapterOfNominaEntity = object : EntityDeleteOrUpdateAdapter<NominaEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `payroll_nominas` SET `id` = ?,`employeeId` = ?,`institutionId` = ?,`fechaEmision` = ?,`periodoInicio` = ?,`periodoFin` = ?,`salarioBase` = ?,`bonificaciones` = ?,`deducciones` = ?,`totalNeto` = ?,`estado` = ?,`metodoPago` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NominaEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.employeeId)
        statement.bindText(3, entity.institutionId)
        statement.bindLong(4, entity.fechaEmision)
        statement.bindLong(5, entity.periodoInicio)
        statement.bindLong(6, entity.periodoFin)
        statement.bindDouble(7, entity.salarioBase)
        statement.bindDouble(8, entity.bonificaciones)
        statement.bindDouble(9, entity.deducciones)
        statement.bindDouble(10, entity.totalNeto)
        statement.bindText(11, entity.estado)
        statement.bindText(12, entity.metodoPago)
        statement.bindLong(13, entity.syncStatus.toLong())
        statement.bindLong(14, entity.lastModified)
        statement.bindText(15, entity.id)
      }
    }
  }

  public override suspend fun insertNomina(nomina: NominaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfNominaEntity.insert(_connection, nomina)
  }

  public override suspend fun deleteNomina(nomina: NominaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfNominaEntity.handle(_connection, nomina)
  }

  public override suspend fun updateNomina(nomina: NominaEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfNominaEntity.handle(_connection, nomina)
  }

  public override fun getAllNominas(institutionId: String): Flow<List<NominaEntity>> {
    val _sql: String = "SELECT * FROM payroll_nominas WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("payroll_nominas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfPeriodoInicio: Int = getColumnIndexOrThrow(_stmt, "periodoInicio")
        val _columnIndexOfPeriodoFin: Int = getColumnIndexOrThrow(_stmt, "periodoFin")
        val _columnIndexOfSalarioBase: Int = getColumnIndexOrThrow(_stmt, "salarioBase")
        val _columnIndexOfBonificaciones: Int = getColumnIndexOrThrow(_stmt, "bonificaciones")
        val _columnIndexOfDeducciones: Int = getColumnIndexOrThrow(_stmt, "deducciones")
        val _columnIndexOfTotalNeto: Int = getColumnIndexOrThrow(_stmt, "totalNeto")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<NominaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: NominaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpPeriodoInicio: Long
          _tmpPeriodoInicio = _stmt.getLong(_columnIndexOfPeriodoInicio)
          val _tmpPeriodoFin: Long
          _tmpPeriodoFin = _stmt.getLong(_columnIndexOfPeriodoFin)
          val _tmpSalarioBase: Double
          _tmpSalarioBase = _stmt.getDouble(_columnIndexOfSalarioBase)
          val _tmpBonificaciones: Double
          _tmpBonificaciones = _stmt.getDouble(_columnIndexOfBonificaciones)
          val _tmpDeducciones: Double
          _tmpDeducciones = _stmt.getDouble(_columnIndexOfDeducciones)
          val _tmpTotalNeto: Double
          _tmpTotalNeto = _stmt.getDouble(_columnIndexOfTotalNeto)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              NominaEntity(_tmpId,_tmpEmployeeId,_tmpInstitutionId,_tmpFechaEmision,_tmpPeriodoInicio,_tmpPeriodoFin,_tmpSalarioBase,_tmpBonificaciones,_tmpDeducciones,_tmpTotalNeto,_tmpEstado,_tmpMetodoPago,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getNominasByEmployee(employeeId: String, institutionId: String):
      Flow<List<NominaEntity>> {
    val _sql: String = "SELECT * FROM payroll_nominas WHERE employeeId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("payroll_nominas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, employeeId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmployeeId: Int = getColumnIndexOrThrow(_stmt, "employeeId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfFechaEmision: Int = getColumnIndexOrThrow(_stmt, "fechaEmision")
        val _columnIndexOfPeriodoInicio: Int = getColumnIndexOrThrow(_stmt, "periodoInicio")
        val _columnIndexOfPeriodoFin: Int = getColumnIndexOrThrow(_stmt, "periodoFin")
        val _columnIndexOfSalarioBase: Int = getColumnIndexOrThrow(_stmt, "salarioBase")
        val _columnIndexOfBonificaciones: Int = getColumnIndexOrThrow(_stmt, "bonificaciones")
        val _columnIndexOfDeducciones: Int = getColumnIndexOrThrow(_stmt, "deducciones")
        val _columnIndexOfTotalNeto: Int = getColumnIndexOrThrow(_stmt, "totalNeto")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfMetodoPago: Int = getColumnIndexOrThrow(_stmt, "metodoPago")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<NominaEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: NominaEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpEmployeeId: String
          _tmpEmployeeId = _stmt.getText(_columnIndexOfEmployeeId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpFechaEmision: Long
          _tmpFechaEmision = _stmt.getLong(_columnIndexOfFechaEmision)
          val _tmpPeriodoInicio: Long
          _tmpPeriodoInicio = _stmt.getLong(_columnIndexOfPeriodoInicio)
          val _tmpPeriodoFin: Long
          _tmpPeriodoFin = _stmt.getLong(_columnIndexOfPeriodoFin)
          val _tmpSalarioBase: Double
          _tmpSalarioBase = _stmt.getDouble(_columnIndexOfSalarioBase)
          val _tmpBonificaciones: Double
          _tmpBonificaciones = _stmt.getDouble(_columnIndexOfBonificaciones)
          val _tmpDeducciones: Double
          _tmpDeducciones = _stmt.getDouble(_columnIndexOfDeducciones)
          val _tmpTotalNeto: Double
          _tmpTotalNeto = _stmt.getDouble(_columnIndexOfTotalNeto)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpMetodoPago: String
          _tmpMetodoPago = _stmt.getText(_columnIndexOfMetodoPago)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              NominaEntity(_tmpId,_tmpEmployeeId,_tmpInstitutionId,_tmpFechaEmision,_tmpPeriodoInicio,_tmpPeriodoFin,_tmpSalarioBase,_tmpBonificaciones,_tmpDeducciones,_tmpTotalNeto,_tmpEstado,_tmpMetodoPago,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteNominaById(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM payroll_nominas WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

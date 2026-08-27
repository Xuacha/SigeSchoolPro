package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.PlanEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlanDao_Impl(
  __db: RoomDatabase,
) : PlanDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlanEntity: EntityInsertAdapter<PlanEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlanEntity = object : EntityInsertAdapter<PlanEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `planes` (`id`,`nombre`,`descripcion`,`limiteEstudiantes`,`precioMensual`,`precioAnual`,`incluyeNomina`,`incluyeCarnets`,`incluyeBI`,`incluyeSoportePrioritario`,`incluyeAPI`,`incluyeGestorDedicado`,`incluyeImplementacionGuiada`,`capacitaciones`,`activo`,`syncStatus`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlanEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.nombre)
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpDescripcion)
        }
        statement.bindLong(4, entity.limiteEstudiantes.toLong())
        statement.bindDouble(5, entity.precioMensual)
        statement.bindDouble(6, entity.precioAnual)
        val _tmp: Int = if (entity.incluyeNomina) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.incluyeCarnets) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.incluyeBI) 1 else 0
        statement.bindLong(9, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.incluyeSoportePrioritario) 1 else 0
        statement.bindLong(10, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.incluyeAPI) 1 else 0
        statement.bindLong(11, _tmp_4.toLong())
        val _tmp_5: Int = if (entity.incluyeGestorDedicado) 1 else 0
        statement.bindLong(12, _tmp_5.toLong())
        val _tmp_6: Int = if (entity.incluyeImplementacionGuiada) 1 else 0
        statement.bindLong(13, _tmp_6.toLong())
        statement.bindLong(14, entity.capacitaciones.toLong())
        val _tmp_7: Int = if (entity.activo) 1 else 0
        statement.bindLong(15, _tmp_7.toLong())
        statement.bindLong(16, entity.syncStatus.toLong())
        statement.bindLong(17, entity.lastModified)
      }
    }
  }

  public override suspend fun insert(plan: PlanEntity): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfPlanEntity.insertAndReturnId(_connection, plan)
    _result
  }

  public override suspend fun getById(id: Long): PlanEntity? {
    val _sql: String = "SELECT * FROM planes WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfLimiteEstudiantes: Int = getColumnIndexOrThrow(_stmt, "limiteEstudiantes")
        val _columnIndexOfPrecioMensual: Int = getColumnIndexOrThrow(_stmt, "precioMensual")
        val _columnIndexOfPrecioAnual: Int = getColumnIndexOrThrow(_stmt, "precioAnual")
        val _columnIndexOfIncluyeNomina: Int = getColumnIndexOrThrow(_stmt, "incluyeNomina")
        val _columnIndexOfIncluyeCarnets: Int = getColumnIndexOrThrow(_stmt, "incluyeCarnets")
        val _columnIndexOfIncluyeBI: Int = getColumnIndexOrThrow(_stmt, "incluyeBI")
        val _columnIndexOfIncluyeSoportePrioritario: Int = getColumnIndexOrThrow(_stmt,
            "incluyeSoportePrioritario")
        val _columnIndexOfIncluyeAPI: Int = getColumnIndexOrThrow(_stmt, "incluyeAPI")
        val _columnIndexOfIncluyeGestorDedicado: Int = getColumnIndexOrThrow(_stmt,
            "incluyeGestorDedicado")
        val _columnIndexOfIncluyeImplementacionGuiada: Int = getColumnIndexOrThrow(_stmt,
            "incluyeImplementacionGuiada")
        val _columnIndexOfCapacitaciones: Int = getColumnIndexOrThrow(_stmt, "capacitaciones")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: PlanEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpLimiteEstudiantes: Int
          _tmpLimiteEstudiantes = _stmt.getLong(_columnIndexOfLimiteEstudiantes).toInt()
          val _tmpPrecioMensual: Double
          _tmpPrecioMensual = _stmt.getDouble(_columnIndexOfPrecioMensual)
          val _tmpPrecioAnual: Double
          _tmpPrecioAnual = _stmt.getDouble(_columnIndexOfPrecioAnual)
          val _tmpIncluyeNomina: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIncluyeNomina).toInt()
          _tmpIncluyeNomina = _tmp != 0
          val _tmpIncluyeCarnets: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIncluyeCarnets).toInt()
          _tmpIncluyeCarnets = _tmp_1 != 0
          val _tmpIncluyeBI: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIncluyeBI).toInt()
          _tmpIncluyeBI = _tmp_2 != 0
          val _tmpIncluyeSoportePrioritario: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIncluyeSoportePrioritario).toInt()
          _tmpIncluyeSoportePrioritario = _tmp_3 != 0
          val _tmpIncluyeAPI: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfIncluyeAPI).toInt()
          _tmpIncluyeAPI = _tmp_4 != 0
          val _tmpIncluyeGestorDedicado: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIncluyeGestorDedicado).toInt()
          _tmpIncluyeGestorDedicado = _tmp_5 != 0
          val _tmpIncluyeImplementacionGuiada: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfIncluyeImplementacionGuiada).toInt()
          _tmpIncluyeImplementacionGuiada = _tmp_6 != 0
          val _tmpCapacitaciones: Int
          _tmpCapacitaciones = _stmt.getLong(_columnIndexOfCapacitaciones).toInt()
          val _tmpActivo: Boolean
          val _tmp_7: Int
          _tmp_7 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_7 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              PlanEntity(_tmpId,_tmpNombre,_tmpDescripcion,_tmpLimiteEstudiantes,_tmpPrecioMensual,_tmpPrecioAnual,_tmpIncluyeNomina,_tmpIncluyeCarnets,_tmpIncluyeBI,_tmpIncluyeSoportePrioritario,_tmpIncluyeAPI,_tmpIncluyeGestorDedicado,_tmpIncluyeImplementacionGuiada,_tmpCapacitaciones,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByNombre(nombre: String): PlanEntity? {
    val _sql: String = "SELECT * FROM planes WHERE nombre = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, nombre)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfLimiteEstudiantes: Int = getColumnIndexOrThrow(_stmt, "limiteEstudiantes")
        val _columnIndexOfPrecioMensual: Int = getColumnIndexOrThrow(_stmt, "precioMensual")
        val _columnIndexOfPrecioAnual: Int = getColumnIndexOrThrow(_stmt, "precioAnual")
        val _columnIndexOfIncluyeNomina: Int = getColumnIndexOrThrow(_stmt, "incluyeNomina")
        val _columnIndexOfIncluyeCarnets: Int = getColumnIndexOrThrow(_stmt, "incluyeCarnets")
        val _columnIndexOfIncluyeBI: Int = getColumnIndexOrThrow(_stmt, "incluyeBI")
        val _columnIndexOfIncluyeSoportePrioritario: Int = getColumnIndexOrThrow(_stmt,
            "incluyeSoportePrioritario")
        val _columnIndexOfIncluyeAPI: Int = getColumnIndexOrThrow(_stmt, "incluyeAPI")
        val _columnIndexOfIncluyeGestorDedicado: Int = getColumnIndexOrThrow(_stmt,
            "incluyeGestorDedicado")
        val _columnIndexOfIncluyeImplementacionGuiada: Int = getColumnIndexOrThrow(_stmt,
            "incluyeImplementacionGuiada")
        val _columnIndexOfCapacitaciones: Int = getColumnIndexOrThrow(_stmt, "capacitaciones")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: PlanEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpLimiteEstudiantes: Int
          _tmpLimiteEstudiantes = _stmt.getLong(_columnIndexOfLimiteEstudiantes).toInt()
          val _tmpPrecioMensual: Double
          _tmpPrecioMensual = _stmt.getDouble(_columnIndexOfPrecioMensual)
          val _tmpPrecioAnual: Double
          _tmpPrecioAnual = _stmt.getDouble(_columnIndexOfPrecioAnual)
          val _tmpIncluyeNomina: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIncluyeNomina).toInt()
          _tmpIncluyeNomina = _tmp != 0
          val _tmpIncluyeCarnets: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIncluyeCarnets).toInt()
          _tmpIncluyeCarnets = _tmp_1 != 0
          val _tmpIncluyeBI: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIncluyeBI).toInt()
          _tmpIncluyeBI = _tmp_2 != 0
          val _tmpIncluyeSoportePrioritario: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIncluyeSoportePrioritario).toInt()
          _tmpIncluyeSoportePrioritario = _tmp_3 != 0
          val _tmpIncluyeAPI: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfIncluyeAPI).toInt()
          _tmpIncluyeAPI = _tmp_4 != 0
          val _tmpIncluyeGestorDedicado: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIncluyeGestorDedicado).toInt()
          _tmpIncluyeGestorDedicado = _tmp_5 != 0
          val _tmpIncluyeImplementacionGuiada: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfIncluyeImplementacionGuiada).toInt()
          _tmpIncluyeImplementacionGuiada = _tmp_6 != 0
          val _tmpCapacitaciones: Int
          _tmpCapacitaciones = _stmt.getLong(_columnIndexOfCapacitaciones).toInt()
          val _tmpActivo: Boolean
          val _tmp_7: Int
          _tmp_7 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_7 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              PlanEntity(_tmpId,_tmpNombre,_tmpDescripcion,_tmpLimiteEstudiantes,_tmpPrecioMensual,_tmpPrecioAnual,_tmpIncluyeNomina,_tmpIncluyeCarnets,_tmpIncluyeBI,_tmpIncluyeSoportePrioritario,_tmpIncluyeAPI,_tmpIncluyeGestorDedicado,_tmpIncluyeImplementacionGuiada,_tmpCapacitaciones,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getActive(): Flow<List<PlanEntity>> {
    val _sql: String = "SELECT * FROM planes WHERE activo = 1 ORDER BY limiteEstudiantes ASC"
    return createFlow(__db, false, arrayOf("planes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfLimiteEstudiantes: Int = getColumnIndexOrThrow(_stmt, "limiteEstudiantes")
        val _columnIndexOfPrecioMensual: Int = getColumnIndexOrThrow(_stmt, "precioMensual")
        val _columnIndexOfPrecioAnual: Int = getColumnIndexOrThrow(_stmt, "precioAnual")
        val _columnIndexOfIncluyeNomina: Int = getColumnIndexOrThrow(_stmt, "incluyeNomina")
        val _columnIndexOfIncluyeCarnets: Int = getColumnIndexOrThrow(_stmt, "incluyeCarnets")
        val _columnIndexOfIncluyeBI: Int = getColumnIndexOrThrow(_stmt, "incluyeBI")
        val _columnIndexOfIncluyeSoportePrioritario: Int = getColumnIndexOrThrow(_stmt,
            "incluyeSoportePrioritario")
        val _columnIndexOfIncluyeAPI: Int = getColumnIndexOrThrow(_stmt, "incluyeAPI")
        val _columnIndexOfIncluyeGestorDedicado: Int = getColumnIndexOrThrow(_stmt,
            "incluyeGestorDedicado")
        val _columnIndexOfIncluyeImplementacionGuiada: Int = getColumnIndexOrThrow(_stmt,
            "incluyeImplementacionGuiada")
        val _columnIndexOfCapacitaciones: Int = getColumnIndexOrThrow(_stmt, "capacitaciones")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpLimiteEstudiantes: Int
          _tmpLimiteEstudiantes = _stmt.getLong(_columnIndexOfLimiteEstudiantes).toInt()
          val _tmpPrecioMensual: Double
          _tmpPrecioMensual = _stmt.getDouble(_columnIndexOfPrecioMensual)
          val _tmpPrecioAnual: Double
          _tmpPrecioAnual = _stmt.getDouble(_columnIndexOfPrecioAnual)
          val _tmpIncluyeNomina: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIncluyeNomina).toInt()
          _tmpIncluyeNomina = _tmp != 0
          val _tmpIncluyeCarnets: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIncluyeCarnets).toInt()
          _tmpIncluyeCarnets = _tmp_1 != 0
          val _tmpIncluyeBI: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIncluyeBI).toInt()
          _tmpIncluyeBI = _tmp_2 != 0
          val _tmpIncluyeSoportePrioritario: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIncluyeSoportePrioritario).toInt()
          _tmpIncluyeSoportePrioritario = _tmp_3 != 0
          val _tmpIncluyeAPI: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfIncluyeAPI).toInt()
          _tmpIncluyeAPI = _tmp_4 != 0
          val _tmpIncluyeGestorDedicado: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIncluyeGestorDedicado).toInt()
          _tmpIncluyeGestorDedicado = _tmp_5 != 0
          val _tmpIncluyeImplementacionGuiada: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfIncluyeImplementacionGuiada).toInt()
          _tmpIncluyeImplementacionGuiada = _tmp_6 != 0
          val _tmpCapacitaciones: Int
          _tmpCapacitaciones = _stmt.getLong(_columnIndexOfCapacitaciones).toInt()
          val _tmpActivo: Boolean
          val _tmp_7: Int
          _tmp_7 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_7 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanEntity(_tmpId,_tmpNombre,_tmpDescripcion,_tmpLimiteEstudiantes,_tmpPrecioMensual,_tmpPrecioAnual,_tmpIncluyeNomina,_tmpIncluyeCarnets,_tmpIncluyeBI,_tmpIncluyeSoportePrioritario,_tmpIncluyeAPI,_tmpIncluyeGestorDedicado,_tmpIncluyeImplementacionGuiada,_tmpCapacitaciones,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSync(): List<PlanEntity> {
    val _sql: String = "SELECT * FROM planes WHERE syncStatus != 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfLimiteEstudiantes: Int = getColumnIndexOrThrow(_stmt, "limiteEstudiantes")
        val _columnIndexOfPrecioMensual: Int = getColumnIndexOrThrow(_stmt, "precioMensual")
        val _columnIndexOfPrecioAnual: Int = getColumnIndexOrThrow(_stmt, "precioAnual")
        val _columnIndexOfIncluyeNomina: Int = getColumnIndexOrThrow(_stmt, "incluyeNomina")
        val _columnIndexOfIncluyeCarnets: Int = getColumnIndexOrThrow(_stmt, "incluyeCarnets")
        val _columnIndexOfIncluyeBI: Int = getColumnIndexOrThrow(_stmt, "incluyeBI")
        val _columnIndexOfIncluyeSoportePrioritario: Int = getColumnIndexOrThrow(_stmt,
            "incluyeSoportePrioritario")
        val _columnIndexOfIncluyeAPI: Int = getColumnIndexOrThrow(_stmt, "incluyeAPI")
        val _columnIndexOfIncluyeGestorDedicado: Int = getColumnIndexOrThrow(_stmt,
            "incluyeGestorDedicado")
        val _columnIndexOfIncluyeImplementacionGuiada: Int = getColumnIndexOrThrow(_stmt,
            "incluyeImplementacionGuiada")
        val _columnIndexOfCapacitaciones: Int = getColumnIndexOrThrow(_stmt, "capacitaciones")
        val _columnIndexOfActivo: Int = getColumnIndexOrThrow(_stmt, "activo")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<PlanEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlanEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpLimiteEstudiantes: Int
          _tmpLimiteEstudiantes = _stmt.getLong(_columnIndexOfLimiteEstudiantes).toInt()
          val _tmpPrecioMensual: Double
          _tmpPrecioMensual = _stmt.getDouble(_columnIndexOfPrecioMensual)
          val _tmpPrecioAnual: Double
          _tmpPrecioAnual = _stmt.getDouble(_columnIndexOfPrecioAnual)
          val _tmpIncluyeNomina: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIncluyeNomina).toInt()
          _tmpIncluyeNomina = _tmp != 0
          val _tmpIncluyeCarnets: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIncluyeCarnets).toInt()
          _tmpIncluyeCarnets = _tmp_1 != 0
          val _tmpIncluyeBI: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIncluyeBI).toInt()
          _tmpIncluyeBI = _tmp_2 != 0
          val _tmpIncluyeSoportePrioritario: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIncluyeSoportePrioritario).toInt()
          _tmpIncluyeSoportePrioritario = _tmp_3 != 0
          val _tmpIncluyeAPI: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfIncluyeAPI).toInt()
          _tmpIncluyeAPI = _tmp_4 != 0
          val _tmpIncluyeGestorDedicado: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIncluyeGestorDedicado).toInt()
          _tmpIncluyeGestorDedicado = _tmp_5 != 0
          val _tmpIncluyeImplementacionGuiada: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfIncluyeImplementacionGuiada).toInt()
          _tmpIncluyeImplementacionGuiada = _tmp_6 != 0
          val _tmpCapacitaciones: Int
          _tmpCapacitaciones = _stmt.getLong(_columnIndexOfCapacitaciones).toInt()
          val _tmpActivo: Boolean
          val _tmp_7: Int
          _tmp_7 = _stmt.getLong(_columnIndexOfActivo).toInt()
          _tmpActivo = _tmp_7 != 0
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              PlanEntity(_tmpId,_tmpNombre,_tmpDescripcion,_tmpLimiteEstudiantes,_tmpPrecioMensual,_tmpPrecioAnual,_tmpIncluyeNomina,_tmpIncluyeCarnets,_tmpIncluyeBI,_tmpIncluyeSoportePrioritario,_tmpIncluyeAPI,_tmpIncluyeGestorDedicado,_tmpIncluyeImplementacionGuiada,_tmpCapacitaciones,_tmpActivo,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE planes SET syncStatus = 0, lastModified = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
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

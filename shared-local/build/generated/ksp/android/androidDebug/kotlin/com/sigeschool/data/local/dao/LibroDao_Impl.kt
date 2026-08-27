package com.sigeschool.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.LibroEntity
import javax.`annotation`.processing.Generated
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
public class LibroDao_Impl(
  __db: RoomDatabase,
) : LibroDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfLibroEntity: EntityInsertAdapter<LibroEntity>

  private val __deleteAdapterOfLibroEntity: EntityDeleteOrUpdateAdapter<LibroEntity>

  private val __updateAdapterOfLibroEntity: EntityDeleteOrUpdateAdapter<LibroEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfLibroEntity = object : EntityInsertAdapter<LibroEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `library_libros` (`id`,`institutionId`,`isbn`,`titulo`,`autor`,`editorial`,`anioPublicacion`,`categoria`,`descripcion`,`ejemplaresTotales`,`ejemplaresDisponibles`,`ubicacionFisica`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: LibroEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        val _tmpIsbn: String? = entity.isbn
        if (_tmpIsbn == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpIsbn)
        }
        statement.bindText(4, entity.titulo)
        statement.bindText(5, entity.autor)
        val _tmpEditorial: String? = entity.editorial
        if (_tmpEditorial == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpEditorial)
        }
        val _tmpAnioPublicacion: Int? = entity.anioPublicacion
        if (_tmpAnioPublicacion == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpAnioPublicacion.toLong())
        }
        val _tmpCategoria: String? = entity.categoria
        if (_tmpCategoria == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpCategoria)
        }
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpDescripcion)
        }
        statement.bindLong(10, entity.ejemplaresTotales.toLong())
        statement.bindLong(11, entity.ejemplaresDisponibles.toLong())
        val _tmpUbicacionFisica: String? = entity.ubicacionFisica
        if (_tmpUbicacionFisica == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpUbicacionFisica)
        }
        statement.bindLong(13, entity.syncStatus.toLong())
        statement.bindLong(14, entity.lastModified)
      }
    }
    this.__deleteAdapterOfLibroEntity = object : EntityDeleteOrUpdateAdapter<LibroEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `library_libros` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: LibroEntity) {
        statement.bindText(1, entity.id)
      }
    }
    this.__updateAdapterOfLibroEntity = object : EntityDeleteOrUpdateAdapter<LibroEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `library_libros` SET `id` = ?,`institutionId` = ?,`isbn` = ?,`titulo` = ?,`autor` = ?,`editorial` = ?,`anioPublicacion` = ?,`categoria` = ?,`descripcion` = ?,`ejemplaresTotales` = ?,`ejemplaresDisponibles` = ?,`ubicacionFisica` = ?,`syncStatus` = ?,`lastModified` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: LibroEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        val _tmpIsbn: String? = entity.isbn
        if (_tmpIsbn == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpIsbn)
        }
        statement.bindText(4, entity.titulo)
        statement.bindText(5, entity.autor)
        val _tmpEditorial: String? = entity.editorial
        if (_tmpEditorial == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpEditorial)
        }
        val _tmpAnioPublicacion: Int? = entity.anioPublicacion
        if (_tmpAnioPublicacion == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpAnioPublicacion.toLong())
        }
        val _tmpCategoria: String? = entity.categoria
        if (_tmpCategoria == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpCategoria)
        }
        val _tmpDescripcion: String? = entity.descripcion
        if (_tmpDescripcion == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpDescripcion)
        }
        statement.bindLong(10, entity.ejemplaresTotales.toLong())
        statement.bindLong(11, entity.ejemplaresDisponibles.toLong())
        val _tmpUbicacionFisica: String? = entity.ubicacionFisica
        if (_tmpUbicacionFisica == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpUbicacionFisica)
        }
        statement.bindLong(13, entity.syncStatus.toLong())
        statement.bindLong(14, entity.lastModified)
        statement.bindText(15, entity.id)
      }
    }
  }

  public override suspend fun insert(libro: LibroEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfLibroEntity.insert(_connection, libro)
  }

  public override suspend fun delete(libro: LibroEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __deleteAdapterOfLibroEntity.handle(_connection, libro)
  }

  public override suspend fun update(libro: LibroEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfLibroEntity.handle(_connection, libro)
  }

  public override fun getAll(instId: String): Flow<List<LibroEntity>> {
    val _sql: String = "SELECT * FROM library_libros WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("library_libros")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfIsbn: Int = getColumnIndexOrThrow(_stmt, "isbn")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfAutor: Int = getColumnIndexOrThrow(_stmt, "autor")
        val _columnIndexOfEditorial: Int = getColumnIndexOrThrow(_stmt, "editorial")
        val _columnIndexOfAnioPublicacion: Int = getColumnIndexOrThrow(_stmt, "anioPublicacion")
        val _columnIndexOfCategoria: Int = getColumnIndexOrThrow(_stmt, "categoria")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfEjemplaresTotales: Int = getColumnIndexOrThrow(_stmt, "ejemplaresTotales")
        val _columnIndexOfEjemplaresDisponibles: Int = getColumnIndexOrThrow(_stmt,
            "ejemplaresDisponibles")
        val _columnIndexOfUbicacionFisica: Int = getColumnIndexOrThrow(_stmt, "ubicacionFisica")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<LibroEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: LibroEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpIsbn: String?
          if (_stmt.isNull(_columnIndexOfIsbn)) {
            _tmpIsbn = null
          } else {
            _tmpIsbn = _stmt.getText(_columnIndexOfIsbn)
          }
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpAutor: String
          _tmpAutor = _stmt.getText(_columnIndexOfAutor)
          val _tmpEditorial: String?
          if (_stmt.isNull(_columnIndexOfEditorial)) {
            _tmpEditorial = null
          } else {
            _tmpEditorial = _stmt.getText(_columnIndexOfEditorial)
          }
          val _tmpAnioPublicacion: Int?
          if (_stmt.isNull(_columnIndexOfAnioPublicacion)) {
            _tmpAnioPublicacion = null
          } else {
            _tmpAnioPublicacion = _stmt.getLong(_columnIndexOfAnioPublicacion).toInt()
          }
          val _tmpCategoria: String?
          if (_stmt.isNull(_columnIndexOfCategoria)) {
            _tmpCategoria = null
          } else {
            _tmpCategoria = _stmt.getText(_columnIndexOfCategoria)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpEjemplaresTotales: Int
          _tmpEjemplaresTotales = _stmt.getLong(_columnIndexOfEjemplaresTotales).toInt()
          val _tmpEjemplaresDisponibles: Int
          _tmpEjemplaresDisponibles = _stmt.getLong(_columnIndexOfEjemplaresDisponibles).toInt()
          val _tmpUbicacionFisica: String?
          if (_stmt.isNull(_columnIndexOfUbicacionFisica)) {
            _tmpUbicacionFisica = null
          } else {
            _tmpUbicacionFisica = _stmt.getText(_columnIndexOfUbicacionFisica)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              LibroEntity(_tmpId,_tmpInstitutionId,_tmpIsbn,_tmpTitulo,_tmpAutor,_tmpEditorial,_tmpAnioPublicacion,_tmpCategoria,_tmpDescripcion,_tmpEjemplaresTotales,_tmpEjemplaresDisponibles,_tmpUbicacionFisica,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: String, instId: String): LibroEntity? {
    val _sql: String = "SELECT * FROM library_libros WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfIsbn: Int = getColumnIndexOrThrow(_stmt, "isbn")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfAutor: Int = getColumnIndexOrThrow(_stmt, "autor")
        val _columnIndexOfEditorial: Int = getColumnIndexOrThrow(_stmt, "editorial")
        val _columnIndexOfAnioPublicacion: Int = getColumnIndexOrThrow(_stmt, "anioPublicacion")
        val _columnIndexOfCategoria: Int = getColumnIndexOrThrow(_stmt, "categoria")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfEjemplaresTotales: Int = getColumnIndexOrThrow(_stmt, "ejemplaresTotales")
        val _columnIndexOfEjemplaresDisponibles: Int = getColumnIndexOrThrow(_stmt,
            "ejemplaresDisponibles")
        val _columnIndexOfUbicacionFisica: Int = getColumnIndexOrThrow(_stmt, "ubicacionFisica")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: LibroEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpIsbn: String?
          if (_stmt.isNull(_columnIndexOfIsbn)) {
            _tmpIsbn = null
          } else {
            _tmpIsbn = _stmt.getText(_columnIndexOfIsbn)
          }
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpAutor: String
          _tmpAutor = _stmt.getText(_columnIndexOfAutor)
          val _tmpEditorial: String?
          if (_stmt.isNull(_columnIndexOfEditorial)) {
            _tmpEditorial = null
          } else {
            _tmpEditorial = _stmt.getText(_columnIndexOfEditorial)
          }
          val _tmpAnioPublicacion: Int?
          if (_stmt.isNull(_columnIndexOfAnioPublicacion)) {
            _tmpAnioPublicacion = null
          } else {
            _tmpAnioPublicacion = _stmt.getLong(_columnIndexOfAnioPublicacion).toInt()
          }
          val _tmpCategoria: String?
          if (_stmt.isNull(_columnIndexOfCategoria)) {
            _tmpCategoria = null
          } else {
            _tmpCategoria = _stmt.getText(_columnIndexOfCategoria)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpEjemplaresTotales: Int
          _tmpEjemplaresTotales = _stmt.getLong(_columnIndexOfEjemplaresTotales).toInt()
          val _tmpEjemplaresDisponibles: Int
          _tmpEjemplaresDisponibles = _stmt.getLong(_columnIndexOfEjemplaresDisponibles).toInt()
          val _tmpUbicacionFisica: String?
          if (_stmt.isNull(_columnIndexOfUbicacionFisica)) {
            _tmpUbicacionFisica = null
          } else {
            _tmpUbicacionFisica = _stmt.getText(_columnIndexOfUbicacionFisica)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _result =
              LibroEntity(_tmpId,_tmpInstitutionId,_tmpIsbn,_tmpTitulo,_tmpAutor,_tmpEditorial,_tmpAnioPublicacion,_tmpCategoria,_tmpDescripcion,_tmpEjemplaresTotales,_tmpEjemplaresDisponibles,_tmpUbicacionFisica,_tmpSyncStatus,_tmpLastModified)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun search(query: String, instId: String): List<LibroEntity> {
    val _sql: String =
        "SELECT * FROM library_libros WHERE (titulo LIKE '%' || ? || '%' OR autor LIKE '%' || ? || '%') AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindText(_argIndex, instId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfIsbn: Int = getColumnIndexOrThrow(_stmt, "isbn")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfAutor: Int = getColumnIndexOrThrow(_stmt, "autor")
        val _columnIndexOfEditorial: Int = getColumnIndexOrThrow(_stmt, "editorial")
        val _columnIndexOfAnioPublicacion: Int = getColumnIndexOrThrow(_stmt, "anioPublicacion")
        val _columnIndexOfCategoria: Int = getColumnIndexOrThrow(_stmt, "categoria")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfEjemplaresTotales: Int = getColumnIndexOrThrow(_stmt, "ejemplaresTotales")
        val _columnIndexOfEjemplaresDisponibles: Int = getColumnIndexOrThrow(_stmt,
            "ejemplaresDisponibles")
        val _columnIndexOfUbicacionFisica: Int = getColumnIndexOrThrow(_stmt, "ubicacionFisica")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<LibroEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: LibroEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpIsbn: String?
          if (_stmt.isNull(_columnIndexOfIsbn)) {
            _tmpIsbn = null
          } else {
            _tmpIsbn = _stmt.getText(_columnIndexOfIsbn)
          }
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpAutor: String
          _tmpAutor = _stmt.getText(_columnIndexOfAutor)
          val _tmpEditorial: String?
          if (_stmt.isNull(_columnIndexOfEditorial)) {
            _tmpEditorial = null
          } else {
            _tmpEditorial = _stmt.getText(_columnIndexOfEditorial)
          }
          val _tmpAnioPublicacion: Int?
          if (_stmt.isNull(_columnIndexOfAnioPublicacion)) {
            _tmpAnioPublicacion = null
          } else {
            _tmpAnioPublicacion = _stmt.getLong(_columnIndexOfAnioPublicacion).toInt()
          }
          val _tmpCategoria: String?
          if (_stmt.isNull(_columnIndexOfCategoria)) {
            _tmpCategoria = null
          } else {
            _tmpCategoria = _stmt.getText(_columnIndexOfCategoria)
          }
          val _tmpDescripcion: String?
          if (_stmt.isNull(_columnIndexOfDescripcion)) {
            _tmpDescripcion = null
          } else {
            _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          }
          val _tmpEjemplaresTotales: Int
          _tmpEjemplaresTotales = _stmt.getLong(_columnIndexOfEjemplaresTotales).toInt()
          val _tmpEjemplaresDisponibles: Int
          _tmpEjemplaresDisponibles = _stmt.getLong(_columnIndexOfEjemplaresDisponibles).toInt()
          val _tmpUbicacionFisica: String?
          if (_stmt.isNull(_columnIndexOfUbicacionFisica)) {
            _tmpUbicacionFisica = null
          } else {
            _tmpUbicacionFisica = _stmt.getText(_columnIndexOfUbicacionFisica)
          }
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              LibroEntity(_tmpId,_tmpInstitutionId,_tmpIsbn,_tmpTitulo,_tmpAutor,_tmpEditorial,_tmpAnioPublicacion,_tmpCategoria,_tmpDescripcion,_tmpEjemplaresTotales,_tmpEjemplaresDisponibles,_tmpUbicacionFisica,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

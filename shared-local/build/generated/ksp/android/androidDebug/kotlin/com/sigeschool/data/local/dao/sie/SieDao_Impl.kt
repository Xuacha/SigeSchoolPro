package com.sigeschool.`data`.local.dao.sie

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.sie.AchievementIndicatorEntity
import com.sigeschool.`data`.local.entity.sie.CompetencyEntity
import com.sigeschool.`data`.local.entity.sie.CriterionLevelEntity
import com.sigeschool.`data`.local.entity.sie.CriterionSelectionEntity
import com.sigeschool.`data`.local.entity.sie.GradeCategoryEntity
import com.sigeschool.`data`.local.entity.sie.GradingScaleEntity
import com.sigeschool.`data`.local.entity.sie.RubricCriterionEntity
import com.sigeschool.`data`.local.entity.sie.RubricEntity
import com.sigeschool.`data`.local.entity.sie.RubricEvaluationEntity
import com.sigeschool.`data`.local.entity.sie.ScaleRangeEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class SieDao_Impl(
  __db: RoomDatabase,
) : SieDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfGradingScaleEntity: EntityInsertAdapter<GradingScaleEntity>

  private val __insertAdapterOfScaleRangeEntity: EntityInsertAdapter<ScaleRangeEntity>

  private val __insertAdapterOfGradeCategoryEntity: EntityInsertAdapter<GradeCategoryEntity>

  private val __insertAdapterOfCompetencyEntity: EntityInsertAdapter<CompetencyEntity>

  private val __insertAdapterOfAchievementIndicatorEntity:
      EntityInsertAdapter<AchievementIndicatorEntity>

  private val __insertAdapterOfRubricEntity: EntityInsertAdapter<RubricEntity>

  private val __insertAdapterOfRubricCriterionEntity: EntityInsertAdapter<RubricCriterionEntity>

  private val __insertAdapterOfCriterionLevelEntity: EntityInsertAdapter<CriterionLevelEntity>

  private val __insertAdapterOfRubricEvaluationEntity: EntityInsertAdapter<RubricEvaluationEntity>

  private val __insertAdapterOfCriterionSelectionEntity:
      EntityInsertAdapter<CriterionSelectionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfGradingScaleEntity = object : EntityInsertAdapter<GradingScaleEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `grading_scales` (`id`,`institutionId`,`name`,`minScore`,`maxScore`,`isDefault`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GradingScaleEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.name)
        statement.bindDouble(4, entity.minScore)
        statement.bindDouble(5, entity.maxScore)
        val _tmp: Int = if (entity.isDefault) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
    this.__insertAdapterOfScaleRangeEntity = object : EntityInsertAdapter<ScaleRangeEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `scale_ranges` (`id`,`gradingScaleId`,`name`,`minLimit`,`maxLimit`,`description`,`color`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ScaleRangeEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.gradingScaleId)
        statement.bindText(3, entity.name)
        statement.bindDouble(4, entity.minLimit)
        statement.bindDouble(5, entity.maxLimit)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDescription)
        }
        val _tmpColor: String? = entity.color
        if (_tmpColor == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpColor)
        }
      }
    }
    this.__insertAdapterOfGradeCategoryEntity = object : EntityInsertAdapter<GradeCategoryEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `grade_categories` (`id`,`institutionId`,`name`,`weightPercentage`,`periodId`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GradeCategoryEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.name)
        statement.bindDouble(4, entity.weightPercentage)
        val _tmpPeriodId: String? = entity.periodId
        if (_tmpPeriodId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpPeriodId)
        }
      }
    }
    this.__insertAdapterOfCompetencyEntity = object : EntityInsertAdapter<CompetencyEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `competencies` (`id`,`institutionId`,`code`,`description`,`area`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CompetencyEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.code)
        statement.bindText(4, entity.description)
        statement.bindText(5, entity.area)
      }
    }
    this.__insertAdapterOfAchievementIndicatorEntity = object :
        EntityInsertAdapter<AchievementIndicatorEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `achievement_indicators` (`id`,`competencyId`,`rangeId`,`description`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AchievementIndicatorEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.competencyId)
        statement.bindText(3, entity.rangeId)
        statement.bindText(4, entity.description)
      }
    }
    this.__insertAdapterOfRubricEntity = object : EntityInsertAdapter<RubricEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `rubrics` (`id`,`institutionId`,`title`,`description`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RubricEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.title)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDescription)
        }
      }
    }
    this.__insertAdapterOfRubricCriterionEntity = object :
        EntityInsertAdapter<RubricCriterionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `rubric_criteria` (`id`,`rubricId`,`name`,`weight`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RubricCriterionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.rubricId)
        statement.bindText(3, entity.name)
        statement.bindDouble(4, entity.weight)
      }
    }
    this.__insertAdapterOfCriterionLevelEntity = object :
        EntityInsertAdapter<CriterionLevelEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `criterion_levels` (`id`,`criterionId`,`name`,`score`,`description`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CriterionLevelEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.criterionId)
        statement.bindText(3, entity.name)
        statement.bindDouble(4, entity.score)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDescription)
        }
      }
    }
    this.__insertAdapterOfRubricEvaluationEntity = object :
        EntityInsertAdapter<RubricEvaluationEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `rubric_evaluations` (`id`,`gradeId`,`rubricId`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RubricEvaluationEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.gradeId)
        statement.bindText(3, entity.rubricId)
      }
    }
    this.__insertAdapterOfCriterionSelectionEntity = object :
        EntityInsertAdapter<CriterionSelectionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `rubric_selections` (`localId`,`evaluationId`,`criterionId`,`levelId`,`score`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CriterionSelectionEntity) {
        statement.bindLong(1, entity.localId)
        statement.bindText(2, entity.evaluationId)
        statement.bindText(3, entity.criterionId)
        statement.bindText(4, entity.levelId)
        statement.bindDouble(5, entity.score)
      }
    }
  }

  public override suspend fun insertGradingScale(scale: GradingScaleEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfGradingScaleEntity.insert(_connection, scale)
  }

  public override suspend fun insertScaleRanges(ranges: List<ScaleRangeEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfScaleRangeEntity.insert(_connection, ranges)
  }

  public override suspend fun insertGradeCategory(category: GradeCategoryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfGradeCategoryEntity.insert(_connection, category)
  }

  public override suspend fun insertCompetency(competency: CompetencyEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCompetencyEntity.insert(_connection, competency)
  }

  public override suspend
      fun insertAchievementIndicators(indicators: List<AchievementIndicatorEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAchievementIndicatorEntity.insert(_connection, indicators)
  }

  public override suspend fun insertRubric(rubric: RubricEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfRubricEntity.insert(_connection, rubric)
  }

  public override suspend fun insertRubricCriteria(criteria: List<RubricCriterionEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRubricCriterionEntity.insert(_connection, criteria)
  }

  public override suspend fun insertCriterionLevels(levels: List<CriterionLevelEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCriterionLevelEntity.insert(_connection, levels)
  }

  public override suspend fun insertRubricEvaluation(evaluation: RubricEvaluationEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRubricEvaluationEntity.insert(_connection, evaluation)
  }

  public override suspend fun insertCriterionSelections(selections: List<CriterionSelectionEntity>):
      Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCriterionSelectionEntity.insert(_connection, selections)
  }

  public override suspend fun saveFullScale(scale: GradingScaleEntity,
      ranges: List<ScaleRangeEntity>): Unit = performInTransactionSuspending(__db) {
    super@SieDao_Impl.saveFullScale(scale, ranges)
  }

  public override suspend fun saveFullRubricEvaluation(evaluation: RubricEvaluationEntity,
      selections: List<CriterionSelectionEntity>): Unit = performInTransactionSuspending(__db) {
    super@SieDao_Impl.saveFullRubricEvaluation(evaluation, selections)
  }

  public override fun getGradingScales(institutionId: String): Flow<List<GradingScaleEntity>> {
    val _sql: String = "SELECT * FROM grading_scales WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("grading_scales")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfMinScore: Int = getColumnIndexOrThrow(_stmt, "minScore")
        val _columnIndexOfMaxScore: Int = getColumnIndexOrThrow(_stmt, "maxScore")
        val _columnIndexOfIsDefault: Int = getColumnIndexOrThrow(_stmt, "isDefault")
        val _result: MutableList<GradingScaleEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GradingScaleEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpMinScore: Double
          _tmpMinScore = _stmt.getDouble(_columnIndexOfMinScore)
          val _tmpMaxScore: Double
          _tmpMaxScore = _stmt.getDouble(_columnIndexOfMaxScore)
          val _tmpIsDefault: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDefault).toInt()
          _tmpIsDefault = _tmp != 0
          _item =
              GradingScaleEntity(_tmpId,_tmpInstitutionId,_tmpName,_tmpMinScore,_tmpMaxScore,_tmpIsDefault)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getGradingScaleById(id: String): GradingScaleEntity? {
    val _sql: String = "SELECT * FROM grading_scales WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfMinScore: Int = getColumnIndexOrThrow(_stmt, "minScore")
        val _columnIndexOfMaxScore: Int = getColumnIndexOrThrow(_stmt, "maxScore")
        val _columnIndexOfIsDefault: Int = getColumnIndexOrThrow(_stmt, "isDefault")
        val _result: GradingScaleEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpMinScore: Double
          _tmpMinScore = _stmt.getDouble(_columnIndexOfMinScore)
          val _tmpMaxScore: Double
          _tmpMaxScore = _stmt.getDouble(_columnIndexOfMaxScore)
          val _tmpIsDefault: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDefault).toInt()
          _tmpIsDefault = _tmp != 0
          _result =
              GradingScaleEntity(_tmpId,_tmpInstitutionId,_tmpName,_tmpMinScore,_tmpMaxScore,_tmpIsDefault)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRangesForScale(scaleId: String): Flow<List<ScaleRangeEntity>> {
    val _sql: String = "SELECT * FROM scale_ranges WHERE gradingScaleId = ? ORDER BY minLimit ASC"
    return createFlow(__db, false, arrayOf("scale_ranges")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, scaleId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfGradingScaleId: Int = getColumnIndexOrThrow(_stmt, "gradingScaleId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfMinLimit: Int = getColumnIndexOrThrow(_stmt, "minLimit")
        val _columnIndexOfMaxLimit: Int = getColumnIndexOrThrow(_stmt, "maxLimit")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _result: MutableList<ScaleRangeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ScaleRangeEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpGradingScaleId: String
          _tmpGradingScaleId = _stmt.getText(_columnIndexOfGradingScaleId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpMinLimit: Double
          _tmpMinLimit = _stmt.getDouble(_columnIndexOfMinLimit)
          val _tmpMaxLimit: Double
          _tmpMaxLimit = _stmt.getDouble(_columnIndexOfMaxLimit)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          _item =
              ScaleRangeEntity(_tmpId,_tmpGradingScaleId,_tmpName,_tmpMinLimit,_tmpMaxLimit,_tmpDescription,_tmpColor)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGradeCategories(institutionId: String): Flow<List<GradeCategoryEntity>> {
    val _sql: String = "SELECT * FROM grade_categories WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("grade_categories")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfWeightPercentage: Int = getColumnIndexOrThrow(_stmt, "weightPercentage")
        val _columnIndexOfPeriodId: Int = getColumnIndexOrThrow(_stmt, "periodId")
        val _result: MutableList<GradeCategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GradeCategoryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpWeightPercentage: Double
          _tmpWeightPercentage = _stmt.getDouble(_columnIndexOfWeightPercentage)
          val _tmpPeriodId: String?
          if (_stmt.isNull(_columnIndexOfPeriodId)) {
            _tmpPeriodId = null
          } else {
            _tmpPeriodId = _stmt.getText(_columnIndexOfPeriodId)
          }
          _item =
              GradeCategoryEntity(_tmpId,_tmpInstitutionId,_tmpName,_tmpWeightPercentage,_tmpPeriodId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCompetencies(institutionId: String): Flow<List<CompetencyEntity>> {
    val _sql: String = "SELECT * FROM competencies WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("competencies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfCode: Int = getColumnIndexOrThrow(_stmt, "code")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfArea: Int = getColumnIndexOrThrow(_stmt, "area")
        val _result: MutableList<CompetencyEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CompetencyEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpCode: String
          _tmpCode = _stmt.getText(_columnIndexOfCode)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpArea: String
          _tmpArea = _stmt.getText(_columnIndexOfArea)
          _item = CompetencyEntity(_tmpId,_tmpInstitutionId,_tmpCode,_tmpDescription,_tmpArea)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getIndicatorsForCompetency(competencyId: String):
      Flow<List<AchievementIndicatorEntity>> {
    val _sql: String = "SELECT * FROM achievement_indicators WHERE competencyId = ?"
    return createFlow(__db, false, arrayOf("achievement_indicators")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, competencyId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCompetencyId: Int = getColumnIndexOrThrow(_stmt, "competencyId")
        val _columnIndexOfRangeId: Int = getColumnIndexOrThrow(_stmt, "rangeId")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _result: MutableList<AchievementIndicatorEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AchievementIndicatorEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCompetencyId: String
          _tmpCompetencyId = _stmt.getText(_columnIndexOfCompetencyId)
          val _tmpRangeId: String
          _tmpRangeId = _stmt.getText(_columnIndexOfRangeId)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          _item = AchievementIndicatorEntity(_tmpId,_tmpCompetencyId,_tmpRangeId,_tmpDescription)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRubrics(institutionId: String): Flow<List<RubricEntity>> {
    val _sql: String = "SELECT * FROM rubrics WHERE institutionId = ?"
    return createFlow(__db, false, arrayOf("rubrics")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _result: MutableList<RubricEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RubricEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          _item = RubricEntity(_tmpId,_tmpInstitutionId,_tmpTitle,_tmpDescription)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCriteriaForRubric(rubricId: String): Flow<List<RubricCriterionEntity>> {
    val _sql: String = "SELECT * FROM rubric_criteria WHERE rubricId = ?"
    return createFlow(__db, false, arrayOf("rubric_criteria")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, rubricId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfRubricId: Int = getColumnIndexOrThrow(_stmt, "rubricId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfWeight: Int = getColumnIndexOrThrow(_stmt, "weight")
        val _result: MutableList<RubricCriterionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RubricCriterionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpRubricId: String
          _tmpRubricId = _stmt.getText(_columnIndexOfRubricId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpWeight: Double
          _tmpWeight = _stmt.getDouble(_columnIndexOfWeight)
          _item = RubricCriterionEntity(_tmpId,_tmpRubricId,_tmpName,_tmpWeight)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLevelsForCriterion(criterionId: String): Flow<List<CriterionLevelEntity>> {
    val _sql: String = "SELECT * FROM criterion_levels WHERE criterionId = ?"
    return createFlow(__db, false, arrayOf("criterion_levels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, criterionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCriterionId: Int = getColumnIndexOrThrow(_stmt, "criterionId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _result: MutableList<CriterionLevelEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CriterionLevelEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCriterionId: String
          _tmpCriterionId = _stmt.getText(_columnIndexOfCriterionId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpScore: Double
          _tmpScore = _stmt.getDouble(_columnIndexOfScore)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          _item = CriterionLevelEntity(_tmpId,_tmpCriterionId,_tmpName,_tmpScore,_tmpDescription)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRubricEvaluationByGrade(gradeId: String): Flow<RubricEvaluationEntity?> {
    val _sql: String = "SELECT * FROM rubric_evaluations WHERE gradeId = ?"
    return createFlow(__db, false, arrayOf("rubric_evaluations")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, gradeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfGradeId: Int = getColumnIndexOrThrow(_stmt, "gradeId")
        val _columnIndexOfRubricId: Int = getColumnIndexOrThrow(_stmt, "rubricId")
        val _result: RubricEvaluationEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpGradeId: String
          _tmpGradeId = _stmt.getText(_columnIndexOfGradeId)
          val _tmpRubricId: String
          _tmpRubricId = _stmt.getText(_columnIndexOfRubricId)
          _result = RubricEvaluationEntity(_tmpId,_tmpGradeId,_tmpRubricId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSelectionsForEvaluation(evaluationId: String):
      Flow<List<CriterionSelectionEntity>> {
    val _sql: String = "SELECT * FROM rubric_selections WHERE evaluationId = ?"
    return createFlow(__db, false, arrayOf("rubric_selections")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, evaluationId)
        val _columnIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _columnIndexOfEvaluationId: Int = getColumnIndexOrThrow(_stmt, "evaluationId")
        val _columnIndexOfCriterionId: Int = getColumnIndexOrThrow(_stmt, "criterionId")
        val _columnIndexOfLevelId: Int = getColumnIndexOrThrow(_stmt, "levelId")
        val _columnIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _result: MutableList<CriterionSelectionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CriterionSelectionEntity
          val _tmpLocalId: Long
          _tmpLocalId = _stmt.getLong(_columnIndexOfLocalId)
          val _tmpEvaluationId: String
          _tmpEvaluationId = _stmt.getText(_columnIndexOfEvaluationId)
          val _tmpCriterionId: String
          _tmpCriterionId = _stmt.getText(_columnIndexOfCriterionId)
          val _tmpLevelId: String
          _tmpLevelId = _stmt.getText(_columnIndexOfLevelId)
          val _tmpScore: Double
          _tmpScore = _stmt.getDouble(_columnIndexOfScore)
          _item =
              CriterionSelectionEntity(_tmpLocalId,_tmpEvaluationId,_tmpCriterionId,_tmpLevelId,_tmpScore)
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

package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.sie.*
import com.sigeschool.domain.model.sie.*
import kotlinx.datetime.Instant

fun AchievementEntity.toDomain(): Achievement = Achievement(
    id = id,
    subjectId = subjectId,
    gradeId = gradeId,
    period = period,
    description = description,
    type = AchievementType.valueOf(type)
)

fun Achievement.toEntity(): AchievementEntity = AchievementEntity(
    id = id,
    subjectId = subjectId,
    gradeId = gradeId,
    period = period,
    description = description,
    type = type.name
)

fun AcademicGradeEntity.toDomain(): AcademicGrade = AcademicGrade(
    id = id,
    studentId = studentId,
    subjectId = subjectId,
    period = period,
    value = value,
    achievementIds = achievementIds.split(",").filter { it.isNotBlank() },
    observations = observations,
    updatedAt = Instant.fromEpochMilliseconds(updatedAt)
)

fun AcademicGrade.toEntity(): AcademicGradeEntity = AcademicGradeEntity(
    id = id,
    studentId = studentId,
    subjectId = subjectId,
    period = period,
    value = value,
    achievementIds = achievementIds.joinToString(","),
    observations = observations,
    updatedAt = updatedAt.toEpochMilliseconds()
)

fun DisciplineRecordEntity.toDomain(): DisciplineRecord = DisciplineRecord(
    id = id,
    studentId = studentId,
    type = DisciplineType.valueOf(type),
    description = description,
    date = Instant.fromEpochMilliseconds(date),
    teacherId = teacherId,
    impactOnGrade = impactOnGrade,
    parentNotified = parentNotified,
    parentAttended = parentAttended
)

fun DisciplineRecord.toEntity(): DisciplineRecordEntity = DisciplineRecordEntity(
    id = id,
    studentId = studentId,
    type = type.name,
    description = description,
    date = date.toEpochMilliseconds(),
    teacherId = teacherId,
    impactOnGrade = impactOnGrade,
    parentNotified = parentNotified,
    parentAttended = parentAttended
)

fun StudyPlanEntity.toDomain(areas: List<AreaPlanEntity> = emptyList()): StudyPlan = StudyPlan(
    id = id,
    title = title,
    areas = areas.map { it.toDomain() },
    version = version,
    lastUpdated = Instant.fromEpochMilliseconds(lastUpdated)
)

fun StudyPlan.toEntity(): StudyPlanEntity = StudyPlanEntity(
    id = id,
    title = title,
    version = version,
    lastUpdated = lastUpdated.toEpochMilliseconds()
)

fun AreaPlanEntity.toDomain(): AreaPlan = AreaPlan(
    id = id,
    name = name,
    intensity = intensity,
    subjects = subjectIds.split(",").filter { it.isNotBlank() }
)

fun AreaPlan.toEntity(studyPlanId: String): AreaPlanEntity = AreaPlanEntity(
    id = id,
    studyPlanId = studyPlanId,
    name = name,
    intensity = intensity,
    subjectIds = subjects.joinToString(",")
)

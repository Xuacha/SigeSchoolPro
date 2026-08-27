package com.sigeschool.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.sigeschool.data.local.dao.*
import com.sigeschool.data.local.entity.*
import com.sigeschool.data.local.converter.Converters
import androidx.room.TypeConverters

@Database(
    entities = [
        StudentEntity::class,
        AttendanceEntity::class,
        GradeEntity::class,
        ClassEntity::class,
        EmployeeEntity::class,
        SalaryRecordEntity::class,
        TaskEntity::class,
        ExamEntity::class,
        AnnouncementEntity::class,
        PucAccountEntity::class,
        AccountingEntryEntity::class,
        EmployeeAttendanceEntity::class,
        FeePaymentEntity::class
    ],
    // FIX: se sube la versión porque StudentEntity ahora tiene una
    // columna nueva (institutionId). Sin este incremento, Room detecta
    // el esquema compilado distinto del que ya existe en disco en
    // dispositivos con una instalación previa y falla al abrir la base
    // de datos. Gracias a fallbackToDestructiveMigration(true)
    // (ver PlatformAppDatabase), el cambio de versión simplemente
    // recrea la caché local, que se vuelve a poblar desde Supabase.
    version = 12
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun gradeDao(): GradeDao
    abstract fun classDao(): ClassDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun salaryDao(): SalaryDao
    abstract fun taskDao(): TaskDao
    abstract fun examDao(): ExamDao
    abstract fun announcementDao(): AnnouncementDao
    abstract fun pucAccountDao(): PucAccountDao
    abstract fun accountingEntryDao(): AccountingEntryDao
    abstract fun feePaymentDao(): FeePaymentDao
}

// The expected constructor for Room KMP
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
        .build()
}

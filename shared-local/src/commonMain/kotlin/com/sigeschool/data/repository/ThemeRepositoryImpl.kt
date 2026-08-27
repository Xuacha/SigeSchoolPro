package com.sigeschool.data.repository

import com.sigeschool.data.local.database.AppDatabase
import com.sigeschool.data.local.entity.toDomain
import com.sigeschool.data.local.entity.toEntity
import com.sigeschool.domain.model.InstitutionTheme
import com.sigeschool.domain.repository.ThemeRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class ThemeRepositoryImpl(
    private val database: AppDatabase,
    private val supabaseClient: SupabaseClient
) : ThemeRepository {

    private val themeDao = database.institutionThemeDao()

    override fun getThemeByInstitutionId(institutionId: String): Flow<InstitutionTheme?> {
        return themeDao.getThemeByInstitutionId(institutionId).map { it?.toDomain() }
    }

    override suspend fun saveTheme(theme: InstitutionTheme) {
        val updatedTheme = theme.copy(lastUpdated = Clock.System.now().toEpochMilliseconds())
        val entity = updatedTheme.toEntity()
        themeDao.upsertTheme(entity)
        try {
            // Nota: El DTO para Supabase podría necesitar ser diferente o @Serializable
            // Para el prototipo, asumimos que InstitutionThemeEntity sigue siendo compatible con Supabase (via mappers o serialización)
            // Re-agregamos @Serializable al modelo de dominio si es necesario para Supabase-kt
            supabaseClient.from("institution_themes").upsert(updatedTheme)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun syncThemes(institutionId: String) {
        try {
            val remoteTheme = supabaseClient.from("institution_themes")
                .select {
                    filter {
                        eq("institution_id", institutionId)
                    }
                }
                .decodeSingleOrNull<InstitutionTheme>()
            
            remoteTheme?.let {
                themeDao.upsertTheme(it.toEntity())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

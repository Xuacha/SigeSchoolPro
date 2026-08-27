package com.sigeschool.data.remote

import com.sigeschool.domain.model.Announcement
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class AnnouncementRemoteDataSource(private val supabaseClient: SupabaseClient) {
    suspend fun getAnnouncements(institutionId: String): List<Announcement> {
        return supabaseClient.postgrest["announcements"]
            .select(columns = Columns.ALL) {
                filter {
                    eq("institution_id", institutionId)
                }
            }.decodeList<Announcement>()
    }

    suspend fun upsertAnnouncement(announcement: Announcement): Boolean {
        return try {
            supabaseClient.postgrest["announcements"].upsert(announcement)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteAnnouncement(id: String): Boolean {
        return try {
            supabaseClient.postgrest["announcements"].delete {
                filter {
                    eq("id", id)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

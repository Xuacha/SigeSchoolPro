package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Announcement
import kotlinx.coroutines.flow.Flow

interface AnnouncementLocalDataSource {
    fun getAnnouncements(institutionId: String): Flow<List<Announcement>>
    suspend fun insertAnnouncement(announcement: Announcement)
    suspend fun deleteAnnouncement(announcement: Announcement)
}

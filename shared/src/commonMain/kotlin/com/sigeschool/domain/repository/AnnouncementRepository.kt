package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Announcement
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {
    fun getAnnouncements(institutionId: String): Flow<List<Announcement>>
    suspend fun saveAnnouncement(announcement: Announcement)
    suspend fun syncAnnouncements(institutionId: String)
}

package com.sigeschool.data.repository

import com.sigeschool.data.datasource.AnnouncementLocalDataSource
import com.sigeschool.data.remote.AnnouncementRemoteDataSource
import com.sigeschool.domain.model.Announcement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnnouncementRepositoryImpl(
    private val localDataSource: AnnouncementLocalDataSource,
    private val remoteDataSource: AnnouncementRemoteDataSource
) : AnnouncementRepository {
    override fun getAnnouncements(institutionId: String): Flow<List<Announcement>> {
        return localDataSource.getAnnouncements(institutionId)
    }

    override suspend fun addAnnouncement(announcement: Announcement): Boolean {
        localDataSource.insertAnnouncement(announcement)
        return remoteDataSource.upsertAnnouncement(announcement)
    }

    override suspend fun deleteAnnouncement(announcement: Announcement): Boolean {
        localDataSource.deleteAnnouncement(announcement)
        return remoteDataSource.deleteAnnouncement(announcement.id)
    }

    override suspend fun syncAnnouncements(institutionId: String) {
        withContext(Dispatchers.Default) {
            try {
                val remoteAnnouncements = remoteDataSource.getAnnouncements(institutionId)
                remoteAnnouncements.forEach { localDataSource.insertAnnouncement(it) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

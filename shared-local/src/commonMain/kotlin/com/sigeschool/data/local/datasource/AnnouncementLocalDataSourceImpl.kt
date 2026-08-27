package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.AnnouncementLocalDataSource
import com.sigeschool.data.local.dao.AnnouncementDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.Announcement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnnouncementLocalDataSourceImpl(
    private val announcementDao: AnnouncementDao
) : AnnouncementLocalDataSource {
    override fun getAnnouncements(institutionId: String): Flow<List<Announcement>> {
        return announcementDao.getAnnouncements(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertAnnouncement(announcement: Announcement) {
        announcementDao.insertAnnouncement(announcement.toEntity())
    }

    override suspend fun deleteAnnouncement(announcement: Announcement) {
        announcementDao.deleteAnnouncement(announcement.toEntity())
    }
}

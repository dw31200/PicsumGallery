package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PicsumRepositoryImpl(
    private val picsumApiService: PicsumApiService,
    private val picsumDao: PicsumDao,
) : PicsumRepository {
    override fun fetchContents(page: Int, limit: Int): Flow<List<Picsum>> {
        return flow {
            val offset = (page - 1) * limit
            val local = picsumDao.getItem(limit, offset).map {
                Picsum(it)
            }
            emit(local)
            val remote = picsumApiService.fetchContents(page, limit).map {
                Picsum(it)
            }
            picsumDao.insert(remote.map { it.toEntity() })
            val remoteInsertLocal = picsumDao.getItem(limit, offset).map {
                Picsum(it)
            }
            emit(remoteInsertLocal)
        }
    }

    override fun getItem(galleryId: Int): Flow<Picsum?> {
        return flow {
            val local = picsumDao.getItem(galleryId)?.let {
                Picsum(it)
            }
            emit(local)
            val remote = picsumApiService.getItem(galleryId)?.let {
                Picsum(it)
            }
            if (remote != null) {
                picsumDao.insert(remote.toEntity())
                emit(remote)
            }
        }
    }
}

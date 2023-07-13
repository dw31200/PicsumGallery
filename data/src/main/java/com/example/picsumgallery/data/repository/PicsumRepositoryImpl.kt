package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PicsumRepositoryImpl @Inject constructor(
    private val picsumApiService: PicsumApiService,
    private val picsumDao: PicsumDao,
) : PicsumRepository {
    override fun getItemList(page: Int, limit: Int): Flow<List<Picsum>> {
        return flow {
            val offset = (page - 1) * limit
            val local = picsumDao.getItemList(limit, offset).map {
                Picsum(it)
            }
            emit(local)
            val remote = picsumApiService.getItemList(page, limit).map {
                Picsum(it)
            }
            picsumDao.insert(remote.map { it.toEntity() })
            emit(remote)
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

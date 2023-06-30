package com.example.picsumgallery.ui.list

import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.Limit
import com.example.picsumgallery.data.remote.PicsumApiService
import com.example.picsumgallery.ui.model.Picsum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryModel @Inject constructor(
    private val picsumDao: com.example.picsumgallery.data.local.PicsumDao,
    private val picsumApiService: com.example.picsumgallery.data.remote.PicsumApiService,
    @com.example.picsumgallery.data.remote.Limit private val limit: Int,
) {
    fun fetchContents(page: Int): Flow<List<com.example.picsumgallery.data.model.Picsum>> {
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
}

package com.example.picsumgallery.ui.detail

import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import com.example.picsumgallery.ui.model.Picsum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryDetailModel @Inject constructor(
    private val picsumDao: com.example.picsumgallery.data.local.PicsumDao,
    private val picsumApiService: com.example.picsumgallery.data.remote.PicsumApiService,
) {
    fun getItem(galleryId: Int): Flow<com.example.picsumgallery.data.model.Picsum?> {
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

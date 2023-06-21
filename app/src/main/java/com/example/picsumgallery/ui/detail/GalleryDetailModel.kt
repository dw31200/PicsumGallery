package com.example.picsumgallery.ui.detail

import com.example.picsumgallery.data.local.Local
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GalleryDetailModel {
    fun getItem(galleryId: Int): Flow<Picsum?> {
        return flow {
            val local = Local.getPicsumDao().getItem(galleryId)?.let {
                Picsum(it)
            }
            emit(local)
            val remote = PicsumApiService.getItem(galleryId)?.let {
                Picsum(it)
            }
            if (remote != null) {
                Local.getPicsumDao().insert(remote.toEntity())
                emit(remote)
            }
        }
    }
}

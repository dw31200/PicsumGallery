package com.example.picsumgallery.ui.detail

import com.example.picsumgallery.data.local.Local
import com.example.picsumgallery.data.model.Picsum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GalleryDetailModel {
    fun getItem(galleryId: Int): Flow<Picsum> {
        return flow {
            val local = Picsum(Local.getPicsumDao().getItem(galleryId))
            emit(local)
        }
    }
}

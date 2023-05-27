package com.example.picsumgallery.ui.detail

import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.network.PicsumApiService

class GalleryDetailModel : GalleryDetailContract.Model {
    override suspend fun getItem(galleryId: Int): Picsum? {
        return PicsumApiService.getItem(galleryId)
    }
}

package com.example.picsumgallery.ui.list

import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.network.PicsumApiService

class GalleryModel : GalleryContract.Model {
    override suspend fun fetchContents(page: Int): List<Picsum> {
        return PicsumApiService.fetchContents(page)
    }
}

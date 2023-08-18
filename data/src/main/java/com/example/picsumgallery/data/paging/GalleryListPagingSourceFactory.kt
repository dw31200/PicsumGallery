package com.example.picsumgallery.data.paging

import androidx.paging.Pager
import com.example.picsumgallery.data.model.Picsum

interface GalleryListPagingSourceFactory {
    fun getPagingData(): Pager<Int, Picsum>
}

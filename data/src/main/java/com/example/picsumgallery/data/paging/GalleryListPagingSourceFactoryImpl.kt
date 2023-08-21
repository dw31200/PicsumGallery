package com.example.picsumgallery.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import javax.inject.Inject

class GalleryListPagingSourceFactoryImpl @Inject constructor(
    private val picsumDao: PicsumDao,
) : GalleryListPagingSourceFactory {
    override fun getPagingData(): Pager<Int, Picsum> {
        return Pager(
            PagingConfig(pageSize = 30),
            pagingSourceFactory = { GalleryListPagingSource(picsumDao) },
        )
    }
}

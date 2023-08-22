package com.example.picsumgallery.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.Limit
import com.example.picsumgallery.data.remote.PicsumApiService
import javax.inject.Inject

class GalleryListPagingSourceFactoryImpl @Inject constructor(
    private val picsumDao: PicsumDao,
    private val picsumApiService: PicsumApiService,
    @Limit private val limit: Int,
) : GalleryListPagingSourceFactory {
    override fun getPagingData(): Pager<Int, Picsum> {
        return Pager(
            PagingConfig(pageSize = limit),
            pagingSourceFactory = { GalleryListPagingSource(picsumDao, picsumApiService, limit) },
        )
    }
}

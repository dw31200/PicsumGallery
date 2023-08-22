package com.example.picsumgallery.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService

class GalleryListPagingSource(
    private val picsumDao: PicsumDao,
    private val picsumApiService: PicsumApiService,
    private val limit: Int,
) : PagingSource<Int, Picsum>() {
    override fun getRefreshKey(state: PagingState<Int, Picsum>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picsum> {
        val offset = params.key ?: 0
        var result = picsumDao.getItemList(limit, offset).map {
            Picsum(it)
        }

        if (result.isEmpty()) {
            val page = (offset / limit) + 1
            val remote = picsumApiService.getItemList(page, limit).map {
                Picsum(it)
            }.map {
                it.toEntity()
            }
            picsumDao.insert(remote)

            result = picsumDao.getItemList(limit, offset).map {
                Picsum(it)
            }
        }

        return LoadResult.Page(
            data = result,
            prevKey = if (offset == 0) null else offset - limit,
            nextKey = if (result.size < limit) null else offset + limit,
        )
    }
}

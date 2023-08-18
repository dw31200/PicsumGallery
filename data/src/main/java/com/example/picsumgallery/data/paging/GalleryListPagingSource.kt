package com.example.picsumgallery.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum

class GalleryListPagingSource(
    private val picsumDao: PicsumDao,
) : PagingSource<Int, Picsum>() {
    override fun getRefreshKey(state: PagingState<Int, Picsum>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picsum> {
        val limit = params.loadSize
        val offset = (params.key?.div(limit) ?: 0).times(limit)
        val result = picsumDao.getItemList(limit, offset).map {
            Picsum(it)
        }

        return LoadResult.Page(
            data = result,
            prevKey = if (offset == 0) null else offset - 30,
            nextKey = if (result.size < 30) null else offset + 30,
        )
    }
}

package com.example.picsumgallery.ui.list

import com.example.picsumgallery.data.local.Local
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GalleryModel {
    fun fetchContents(page: Int): Flow<List<Picsum>> {
        return flow {
            val offset = (page - 1) * PicsumApiService.LIMIT
            val local = Local.getPicsumDao().getItem(PicsumApiService.LIMIT, offset).map {
                Picsum(it)
            }
            emit(local)
            val remote = PicsumApiService.fetchContents(page).map {
                Picsum(it)
            }
            Local.getPicsumDao().insert(remote.map { it.toEntity() })
            emit(remote)
        }
    }
}

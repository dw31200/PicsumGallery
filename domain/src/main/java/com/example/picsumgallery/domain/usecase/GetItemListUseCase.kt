package com.example.picsumgallery.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.picsumgallery.data.paging.GalleryListPagingSourceFactory
import com.example.picsumgallery.domain.model.PicsumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemListUseCase @Inject constructor(
    private val galleryListPagingSourceFactory: GalleryListPagingSourceFactory,
) {
    operator fun invoke(): Flow<PagingData<PicsumEntity>> {
        return galleryListPagingSourceFactory.getPagingData().flow.map {
            it.map { picsum ->
                PicsumEntity(picsum)
            }
        }
    }
}

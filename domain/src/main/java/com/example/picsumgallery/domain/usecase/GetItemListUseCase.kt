package com.example.picsumgallery.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.picsumgallery.data.model.PicsumLike
import com.example.picsumgallery.data.paging.GalleryListPagingSourceFactory
import com.example.picsumgallery.data.repository.PicsumLikeRepository
import com.example.picsumgallery.domain.model.PicsumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemListUseCase @Inject constructor(
    private val galleryListPagingSourceFactory: GalleryListPagingSourceFactory,
    private val picsumLikeRepository: PicsumLikeRepository,
) {
    operator fun invoke(): Flow<PagingData<PicsumEntity>> {
        val likeItemList = picsumLikeRepository.getAll()
        val itemList = galleryListPagingSourceFactory.getPagingData().flow.map {
            it.map { picsum ->
                PicsumEntity(picsum)
            }
        }

        return combine(itemList, likeItemList) { _itemList, _likeItemList ->
            _itemList.map { it.copy(isLiked = _likeItemList.contains(PicsumLike(it.id))) }
        }
    }
}

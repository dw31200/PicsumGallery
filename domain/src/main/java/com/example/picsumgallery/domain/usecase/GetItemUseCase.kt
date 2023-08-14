package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.repository.PicsumLikeRepository
import com.example.picsumgallery.data.repository.PicsumRepository
import com.example.picsumgallery.domain.model.PicsumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: PicsumRepository,
    private val picsumLikeRepository: PicsumLikeRepository,
) {
    operator fun invoke(galleryId: Int): Flow<PicsumEntity?> {
        val item = repository.getItem(galleryId).map {
            it?.let { PicsumEntity(it) }
        }
        val likeItem = flow { emit(picsumLikeRepository.getItem(galleryId)) }

        return combine(item, likeItem) { _item, _likeItem ->
            _item?.copy(isLiked = _likeItem?.id != null)
        }
    }
}

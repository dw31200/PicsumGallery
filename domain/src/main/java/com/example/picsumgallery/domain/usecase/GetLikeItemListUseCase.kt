package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.model.PicsumLike
import com.example.picsumgallery.data.repository.PicsumLikeRepository
import com.example.picsumgallery.data.repository.PicsumRepository
import com.example.picsumgallery.domain.model.PicsumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLikeItemListUseCase @Inject constructor(
    private val picsumRepository: PicsumRepository,
    private val picsumLikeRepository: PicsumLikeRepository,
) {
    operator fun invoke(): Flow<List<PicsumEntity>> {
        val itemList = picsumRepository.getAll().map {
            it.map {
                PicsumEntity(it)
            }
        }
        val likeItemList = picsumLikeRepository.getAll()

        return combine(itemList, likeItemList) { _itemList, _likeItemList ->
            _itemList.filter { _likeItemList.contains(PicsumLike(it.id)) }
                .map {
                    it.copy(isLiked = true)
                }
        }
    }
}

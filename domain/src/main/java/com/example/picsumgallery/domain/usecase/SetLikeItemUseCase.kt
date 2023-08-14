package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.repository.PicsumLikeRepository
import com.example.picsumgallery.domain.model.PicsumEntity
import javax.inject.Inject

class SetLikeItemUseCase @Inject constructor(
    private val picsumLikeRepository: PicsumLikeRepository,
) {
    suspend operator fun invoke(picsumEntity: PicsumEntity) {
        if (picsumEntity.isLiked) {
            picsumLikeRepository.unlikeItem(picsumEntity.id)
        } else {
            picsumLikeRepository.likeItem(picsumEntity.id)
        }
    }
}

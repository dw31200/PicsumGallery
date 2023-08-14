package com.example.picsumgallery.data.model

import com.example.picsumgallery.data.local.model.PicsumLikeEntity

data class PicsumLike(
    val id: Int,
) {
    companion object {
        operator fun invoke(picsumLikeEntity: PicsumLikeEntity): PicsumLike {
            return PicsumLike(
                id = picsumLikeEntity.id,
            )
        }
    }
}

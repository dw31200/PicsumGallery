package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.model.PicsumLike

interface PicsumLikeRepository {
    suspend fun likeItem(galleryId: Int)

    suspend fun unlikeItem(galleryId: Int)

    suspend fun getAll(): List<PicsumLike>

    suspend fun getItem(galleryId: Int): PicsumLike?
}

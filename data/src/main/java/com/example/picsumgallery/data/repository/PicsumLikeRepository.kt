package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.model.PicsumLike
import kotlinx.coroutines.flow.Flow

interface PicsumLikeRepository {
    suspend fun likeItem(galleryId: Int)

    suspend fun unlikeItem(galleryId: Int)

    fun getAll(): Flow<List<PicsumLike>>

    fun getItem(galleryId: Int): Flow<PicsumLike?>
}

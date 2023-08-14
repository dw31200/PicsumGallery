package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.local.PicsumLikeDao
import com.example.picsumgallery.data.local.model.PicsumLikeEntity
import com.example.picsumgallery.data.model.PicsumLike
import javax.inject.Inject

class PicsumLikeRepositoryImpl @Inject constructor(
    private val picsumLikeDao: PicsumLikeDao,
) : PicsumLikeRepository {
    override suspend fun likeItem(galleryId: Int) {
        picsumLikeDao.insert(PicsumLikeEntity(galleryId))
    }

    override suspend fun unlikeItem(galleryId: Int) {
        picsumLikeDao.delete(PicsumLikeEntity(galleryId))
    }

    override suspend fun getAll(): List<PicsumLike> {
        return picsumLikeDao.getAll().map {
            PicsumLike(it)
        }
    }

    override suspend fun getItem(galleryId: Int): PicsumLike? {
        return picsumLikeDao.getItem(galleryId)?.let {
            PicsumLike(it)
        }
    }
}

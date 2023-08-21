package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.local.PicsumLikeDao
import com.example.picsumgallery.data.local.model.PicsumLikeEntity
import com.example.picsumgallery.data.model.PicsumLike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun getAll(): Flow<List<PicsumLike>> {
        return picsumLikeDao.getAll().map {
            it.map {
                PicsumLike(it)
            }
        }
    }

    override fun getItem(galleryId: Int): Flow<PicsumLike?> {
        return picsumLikeDao.getItem(galleryId).map {
            it?.let {
                PicsumLike(it)
            }
        }
    }
}

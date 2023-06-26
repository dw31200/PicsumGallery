package com.example.picsumgallery.ui.list

import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryModel @Inject constructor(
    private val picsumDao: PicsumDao,
    private val picsumApiService: PicsumApiService,
    private val limit: Int,
) {
    fun fetchContents(page: Int): Flow<List<Picsum>> {
        return flow {
            val offset = (page - 1) * limit
            val local = picsumDao.getItem(limit, offset).map {
                Picsum(it)
            }
            emit(local)
            val remote = picsumApiService.fetchContents(page, limit).map {
                Picsum(it)
            }
            picsumDao.insert(remote.map { it.toEntity() })
            val remoteInsertLocal = picsumDao.getItem(limit, offset).map {
                Picsum(it)
            }
            /* TODO 이미 DB에 있는 데이터들이 중복으로 emit되어 list에 중복 데이터가 누적이 되길래 기존 DB와 insert후에 DB가 다른지 확인하고 emit하고 싶은데
                아래처럼 해도 리스트 비교가 가능한가요? data class의 equals가 이렇게도 비교가 가능한가요?
             */
            if (local != remoteInsertLocal) {
                emit(remoteInsertLocal)
            }
        }
    }
}

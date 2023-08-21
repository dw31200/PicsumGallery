package com.example.picsumgallery.domain.usecase

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.picsumgallery.data.repository.PicsumLikeRepository
import com.example.picsumgallery.data.repository.PicsumRepository
import com.example.picsumgallery.data.work.GetItemWork
import com.example.picsumgallery.domain.model.PicsumEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: PicsumRepository,
    private val picsumLikeRepository: PicsumLikeRepository,
    @ApplicationContext private val context: Context,
) {
    operator fun invoke(galleryId: Int): Flow<PicsumEntity?> {
        val item = repository.getItem(galleryId).map {
            it?.let { PicsumEntity(it) }
        }
        val likeItem = picsumLikeRepository.getItem(galleryId)

        return combine(item, likeItem) { _item, _likeItem ->
            _item?.copy(isLiked = _likeItem?.id != null)
        }.also {
            val getItemWorkRequest = OneTimeWorkRequestBuilder<GetItemWork>()
                .setInputData(workDataOf("galleryId" to galleryId))
                .build()

            WorkManager
                .getInstance(context)
                .enqueue(getItemWorkRequest)
        }
    }
}

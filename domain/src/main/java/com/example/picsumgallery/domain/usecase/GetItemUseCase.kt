package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.repository.PicsumRepository
import com.example.picsumgallery.domain.model.PicsumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: PicsumRepository,
) {
    operator fun invoke(galleryId: Int): Flow<PicsumEntity?> {
        return repository.getItem(galleryId).map {
            it?.let { PicsumEntity(it) }
        }
    }
}

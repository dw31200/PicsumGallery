package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.repository.PicsumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: PicsumRepository,
) {
    operator fun invoke(galleryId: Int): Flow<Picsum?> {
        return repository.getItem(galleryId)
    }
}

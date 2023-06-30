package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.repository.PicsumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchContentsUsecase @Inject constructor(
    private val repository: PicsumRepository,
) {
    operator fun invoke(page: Int, limit: Int): Flow<List<Picsum>> {
        return repository.fetchContents(page, limit)
    }
}

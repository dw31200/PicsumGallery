package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.repository.PicsumRepository
import com.example.picsumgallery.domain.model.PicsumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchContentsUsecase @Inject constructor(
    private val repository: PicsumRepository,
) {
    operator fun invoke(page: Int, limit: Int): Flow<List<PicsumEntity>> {
        return repository.fetchContents(page, limit).map {
            it.map {
                PicsumEntity(it)
            }
        }
    }
}

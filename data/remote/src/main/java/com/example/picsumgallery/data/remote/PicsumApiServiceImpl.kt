package com.example.picsumgallery.data.remote

import com.example.picsumgallery.data.remote.model.PicsumResponse
import javax.inject.Inject

class PicsumApiServiceImpl @Inject constructor(
    private val picsumApi: PicsumApi,
) : PicsumApiService {
    override suspend fun fetchContents(page: Int, limit: Int): List<PicsumResponse> {
        return runCatching {
            picsumApi.fetchContents(page, limit)
        }.fold(
            onSuccess = { it },
            onFailure = { emptyList() },
        )
    }

    override suspend fun getItem(id: Int): PicsumResponse? {
        return runCatching {
            picsumApi.getItem(id)
        }.fold(
            onSuccess = { it },
            onFailure = { null },
        )
    }
}

package com.example.picsumgallery.data.remote

import com.example.picsumgallery.data.remote.model.PicsumResponse

object PicsumApiService {
    const val LIMIT = 30
    private val retrofitService: PicsumApi by lazy {
        Network.retrofit.create(PicsumApi::class.java)
    }

    suspend fun fetchContents(page: Int, limit: Int = LIMIT): List<PicsumResponse> {
        return runCatching {
            retrofitService.fetchContents(page, limit)
        }.fold(
            onSuccess = { it },
            onFailure = { emptyList() },
        )
    }

    suspend fun getItem(id: Int): PicsumResponse? {
        return runCatching {
            retrofitService.getItem(id)
        }.fold(
            onSuccess = { it },
            onFailure = { null },
        )
    }
}

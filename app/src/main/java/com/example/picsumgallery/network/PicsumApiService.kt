package com.example.picsumgallery.network

import com.example.picsumgallery.data.Picsum
import retrofit2.HttpException

object PicsumApiService {
    const val LIMIT = 30
    private val retrofitService: PicsumApi by lazy {
        Network.retrofit.create(PicsumApi::class.java)
    }

    suspend fun fetchContents(page: Int, limit: Int = LIMIT): List<Picsum> {
        return retrofitService.fetchContents(page, limit)
    }

    suspend fun getItem(id: Int): Picsum? {
        try {
            return retrofitService.getItem(id)
        } catch (e: HttpException) {
            return null
        }
    }
}

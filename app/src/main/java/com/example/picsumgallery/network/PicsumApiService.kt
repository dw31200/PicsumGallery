package com.example.picsumgallery.network

import com.example.picsumgallery.data.Picsum
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicsumApiService {
    @GET("v2/list")
    suspend fun fetchContents(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 30,
    ): List<Picsum>

    @GET("id/{id}/info")
    suspend fun getItem(@Path("id") id: Int): Picsum
}

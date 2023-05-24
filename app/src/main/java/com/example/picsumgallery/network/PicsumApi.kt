package com.example.picsumgallery.network

import com.example.picsumgallery.data.Picsum
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicsumApi {
    @GET("v2/list")
    suspend fun fetchContents(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<Picsum>

    @GET("id/{id}/info")
    suspend fun getItem(@Path("id") id: Int): Picsum
}

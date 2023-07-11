package com.example.picsumgallery.data.remote

import com.example.picsumgallery.data.remote.model.PicsumResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicsumApi {
    @GET("v2/list")
    suspend fun getItemList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<PicsumResponse>

    @GET("id/{id}/info")
    suspend fun getItem(@Path("id") id: Int): PicsumResponse
}

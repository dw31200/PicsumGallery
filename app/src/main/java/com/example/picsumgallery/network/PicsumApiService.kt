package com.example.picsumgallery.network

import com.example.picsumgallery.data.Picsum
import retrofit2.http.GET
import retrofit2.http.Path

interface PicsumApiService {
    @GET("v2/list")
    suspend fun fetchContents(): List<Picsum>

    @GET("id/{id}/info")
    suspend fun getItem(@Path("id") id: Int): Picsum
}

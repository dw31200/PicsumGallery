package com.example.picsumgallery

import com.example.picsumgallery.data.Picsum
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PicsumApiService {
    @GET("v2/list")
    fun fetchContents(): Call<List<Picsum>>

    @GET("id/{id}/info")
    fun getItem(@Path("id") id: String): Call<Picsum>
}

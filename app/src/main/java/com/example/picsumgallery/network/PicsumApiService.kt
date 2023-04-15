package com.example.picsumgallery

import com.example.picsumgallery.data.Picsum
import retrofit2.Call
import retrofit2.http.GET

interface PicsumApiService {
    @GET("v2/list")
    fun fetchContents(): Call<List<Picsum>>
}

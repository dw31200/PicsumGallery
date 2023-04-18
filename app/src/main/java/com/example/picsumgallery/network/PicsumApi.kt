package com.example.picsumgallery.network

import com.example.picsumgallery.PicsumApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PicsumApi {
    private val BASE_URL = "https://picsum.photos/"
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: PicsumApiService by lazy { retrofit.create(PicsumApiService::class.java) }
}

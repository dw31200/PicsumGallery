package com.example.picsumgallery.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.picsumgallery.PicsumApiService
import com.example.picsumgallery.data.Picsum
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PicsumApi {
    private val BASE_URL = "https://picsum.photos/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    private val retrofitService: PicsumApiService = retrofit.create(PicsumApiService::class.java)

    fun fetchContents(): LiveData<List<Picsum>> {
        val responseLiveData: MutableLiveData<List<Picsum>> = MutableLiveData()
        val picsumRequest: Call<List<Picsum>> = retrofitService.fetchContents()

        picsumRequest.enqueue(object : Callback<List<Picsum>> {
            override fun onResponse(call: Call<List<Picsum>>, response: Response<List<Picsum>>) {
                Log.d("PicsumApi", "Response received")
                responseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Picsum>>, t: Throwable) {
                Log.e("PicsumApi", "Failed to fetch image", t)
            }
        })

        return responseLiveData
    }
}

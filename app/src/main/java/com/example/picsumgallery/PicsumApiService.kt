package com.example.picsumgallery


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL = "https://picsum.photos/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PicsumApiService {
    @GET("v2/list")
    fun fetchContents(): Call<List<Picsum>>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}

object PicsumApi {
    val retrofitService: PicsumApiService by lazy {
        retrofit.create(PicsumApiService::class.java)
    }

    fun fetchContents(): LiveData<List<Picsum>> {
        val responseLiveData: MutableLiveData<List<Picsum>> = MutableLiveData()
        val picsumRequest: Call<List<Picsum>> = retrofitService.fetchContents()

        picsumRequest.enqueue(object : Callback<List<Picsum>> {
            override fun onResponse(call: Call<List<Picsum>>, response: Response<List<Picsum>>) {
                Log.d("PicsumApi","Response received")
                responseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Picsum>>, t: Throwable) {
                Log.e("PicsumApi","Failed to fetch image",t)
            }
        })

        return responseLiveData
    }

    @WorkerThread
    fun fetchImage(url: String): Bitmap? {
        val response: Response<ResponseBody> = retrofitService.fetchUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use(BitmapFactory::decodeStream)
        Log.i("PicsumApi", "Bitmap: $bitmap Response: $response")
        return bitmap
    }
}


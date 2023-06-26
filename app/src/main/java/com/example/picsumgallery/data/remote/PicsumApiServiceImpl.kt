package com.example.picsumgallery.data.remote

import com.example.picsumgallery.data.remote.model.PicsumResponse
import retrofit2.Retrofit
import javax.inject.Inject

class PicsumApiServiceImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val limit: Int,
) : PicsumApiService {
    // TODO 굳이 지연초기화를 안해도 되는 상황에서는 안하는게 맞나요?
    private val retrofitService: PicsumApi by lazy {
        retrofit.create(PicsumApi::class.java)
    }

    /* TODO 기존처럼 구현체에서 limit 값을 지정하고 싶은데, bind를 구현하려니 default값을 지정을 못해서 함수 호출부에서 일일히 inject받아서 넣었는데
        bind에 들어가는 함수에 디폴트를 넣는게 틀린걸까요?
     */
    override suspend fun fetchContents(page: Int, limit: Int): List<PicsumResponse> {
        return runCatching {
            retrofitService.fetchContents(page, limit)
        }.fold(
            onSuccess = { it },
            onFailure = { emptyList() },
        )
    }

    override suspend fun getItem(id: Int): PicsumResponse? {
        return runCatching {
            retrofitService.getItem(id)
        }.fold(
            onSuccess = { it },
            onFailure = { null },
        )
    }
}

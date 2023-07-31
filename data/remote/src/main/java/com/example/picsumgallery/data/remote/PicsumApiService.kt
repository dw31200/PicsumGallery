package com.example.picsumgallery.data.remote

import com.example.picsumgallery.data.remote.model.PicsumResponse

interface PicsumApiService {
    suspend fun getItemList(page: Int, limit: Int): List<PicsumResponse>

    suspend fun getItem(id: Int): PicsumResponse?
}

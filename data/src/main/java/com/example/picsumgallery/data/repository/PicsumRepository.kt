package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.model.Picsum
import kotlinx.coroutines.flow.Flow

interface PicsumRepository {
    fun getItemList(page: Int, limit: Int): Flow<List<Picsum>>

    fun getItem(galleryId: Int): Flow<Picsum?>
}

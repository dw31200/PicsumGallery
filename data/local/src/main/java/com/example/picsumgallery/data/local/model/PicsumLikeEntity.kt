package com.example.picsumgallery.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PicsumLikeEntity(
    @PrimaryKey
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val like: Boolean,
)

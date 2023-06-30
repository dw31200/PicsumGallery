package com.example.picsumgallery.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PicsumEntity(
    @PrimaryKey
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val webSiteUrl: String,
    val url: String,
)

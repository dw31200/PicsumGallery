package com.example.picsumgallery.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Picsum(
    @PrimaryKey
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    @Json(name = "url")
    @ColumnInfo(name = "website_url")
    val webSiteUrl: String,
    @Json(name = "download_url")
    val url: String,
)

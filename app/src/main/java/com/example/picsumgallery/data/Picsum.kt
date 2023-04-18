package com.example.picsumgallery.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Picsum(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    @Json(name = "url")
    val webSiteUrl: String,
    @Json(name = "download_url")
    val url: String,
)

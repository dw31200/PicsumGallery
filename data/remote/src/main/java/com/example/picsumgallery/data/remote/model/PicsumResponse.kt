package com.example.picsumgallery.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PicsumResponse(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "download_url")
    val downloadUrl: String,
)

package com.example.picsumgallery

import com.squareup.moshi.Json


data class Picsum(var id: String = "",
                  var author: String = "",
                  var width: String = "",
                  var height: String = "",
                  @Json(name = "url")var webSiteUrl: String = "",
                  @Json(name = "download_url")var url: String = ""
)
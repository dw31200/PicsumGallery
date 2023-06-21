package com.example.picsumgallery.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/* todo @JsonClass(generateAdapter = true)로 codegen을 활성화 하고
    KotlinJsonAdapterFactory()를 추가하여 리플렉션을 한다는데 둘을 같이 쓰는건가요?
    각각의 역할이 다른가요? Json을 코틀린 클래스로 변환해주는거 아닌가요?
 */
@JsonClass(generateAdapter = true)
data class PicsumResponse(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    @Json(name = "url")
    val webSiteUrl: String,
    @Json(name = "download_url")
    val url: String,
)

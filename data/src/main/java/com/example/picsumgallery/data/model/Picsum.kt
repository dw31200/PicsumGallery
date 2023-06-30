package com.example.picsumgallery.data.model

import com.example.picsumgallery.data.local.model.PicsumEntity
import com.example.picsumgallery.data.remote.model.PicsumResponse

data class Picsum(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val webSiteUrl: String,
    val url: String,
) {
    fun toEntity(): PicsumEntity {
        return PicsumEntity(
            id = id,
            author = author,
            width = width,
            height = height,
            webSiteUrl = webSiteUrl,
            url = url,
        )
    }

    companion object {
        operator fun invoke(picsumResponse: PicsumResponse): Picsum {
            return Picsum(
                id = picsumResponse.id,
                author = picsumResponse.author,
                width = picsumResponse.width,
                height = picsumResponse.height,
                webSiteUrl = picsumResponse.webSiteUrl,
                url = picsumResponse.url,
            )
        }

        operator fun invoke(picsumEntity: PicsumEntity): Picsum {
            return Picsum(
                id = picsumEntity.id,
                author = picsumEntity.author,
                width = picsumEntity.width,
                height = picsumEntity.height,
                webSiteUrl = picsumEntity.webSiteUrl,
                url = picsumEntity.url,
            )
        }
        //    TODO Picsum을 Domain PicsumEntity로 변환해서 넣는 방법을 잘 모르겠어요.
    }
}

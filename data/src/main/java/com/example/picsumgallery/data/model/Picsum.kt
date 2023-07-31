package com.example.picsumgallery.data.model

import com.example.picsumgallery.data.local.model.PicsumEntity
import com.example.picsumgallery.data.remote.model.PicsumResponse

data class Picsum(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
) {
    fun toEntity(): PicsumEntity {
        return PicsumEntity(
            id = id,
            author = author,
            width = width,
            height = height,
            url = url,
            downloadUrl = downloadUrl,
        )
    }

    companion object {
        operator fun invoke(picsumResponse: PicsumResponse): Picsum {
            return Picsum(
                id = picsumResponse.id,
                author = picsumResponse.author,
                width = picsumResponse.width,
                height = picsumResponse.height,
                url = picsumResponse.url,
                downloadUrl = picsumResponse.downloadUrl,
            )
        }

        operator fun invoke(picsumEntity: PicsumEntity): Picsum {
            return Picsum(
                id = picsumEntity.id,
                author = picsumEntity.author,
                width = picsumEntity.width,
                height = picsumEntity.height,
                url = picsumEntity.url,
                downloadUrl = picsumEntity.downloadUrl,
            )
        }
    }
}

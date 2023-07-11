package com.example.picsumgallery.domain.model

import com.example.picsumgallery.data.model.Picsum

data class PicsumEntity(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val webSiteUrl: String,
    val url: String,
) {
    companion object {
        operator fun invoke(picsum: Picsum): PicsumEntity {
            return PicsumEntity(
                id = picsum.id,
                author = picsum.author,
                width = picsum.width,
                height = picsum.height,
                webSiteUrl = picsum.url,
                url = picsum.downloadUrl,
            )
        }
    }
}

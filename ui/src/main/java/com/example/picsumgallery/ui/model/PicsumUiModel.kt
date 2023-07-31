package com.example.picsumgallery.ui.model

import com.example.picsumgallery.domain.model.PicsumEntity

data class PicsumUiModel(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
) {
    companion object {
        operator fun invoke(picsumEntity: PicsumEntity): PicsumUiModel {
            return PicsumUiModel(
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

package com.example.picsumgallery.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
// TODO data class를 사용함으로써의 이점이 무엇일까요?
data class PicsumEntity(
    @PrimaryKey
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val webSiteUrl: String,
    val url: String,
)

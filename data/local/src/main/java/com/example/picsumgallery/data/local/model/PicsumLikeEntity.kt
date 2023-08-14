package com.example.picsumgallery.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PicsumLikeEntity(
    @PrimaryKey
    val id: Int,
)

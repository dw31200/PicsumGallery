package com.example.picsumgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.picsumgallery.data.local.model.PicsumLikeEntity

@Database(entities = [PicsumLikeEntity::class], version = 1)
abstract class PicsumLikeDatabase : RoomDatabase() {
    abstract fun picsumLikeDao(): PicsumLikeDao
}

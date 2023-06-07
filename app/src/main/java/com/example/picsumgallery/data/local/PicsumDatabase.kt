package com.example.picsumgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.picsumgallery.data.local.model.PicsumEntity

@Database(entities = [PicsumEntity::class], version = 1)
abstract class PicsumDatabase : RoomDatabase() {
    abstract fun picsumDao(): PicsumDao
}

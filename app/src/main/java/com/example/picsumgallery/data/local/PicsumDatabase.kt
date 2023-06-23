package com.example.picsumgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.picsumgallery.data.local.model.PicsumEntity

// TODO 버전 변경의 의미? 한번이라도 빌드하면 버전이 고정되는 건가요?
@Database(entities = [PicsumEntity::class], version = 1)
abstract class PicsumDatabase : RoomDatabase() {
    abstract fun picsumDao(): PicsumDao
}

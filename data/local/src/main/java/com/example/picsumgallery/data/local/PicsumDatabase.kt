package com.example.picsumgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.picsumgallery.data.local.model.PicsumEntity

@Database(entities = [PicsumEntity::class], version = 2)
abstract class PicsumDatabase : RoomDatabase() {
    abstract fun picsumDao(): PicsumDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE PicsumEntity RENAME COLUMN url to downloadUrl",
        )
        database.execSQL(
            "ALTER TABLE PicsumEntity RENAME COLUMN webSiteUrl to url",
        )
    }
}

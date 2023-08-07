package com.example.picsumgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.picsumgallery.data.local.model.PicsumEntity
import com.example.picsumgallery.data.local.model.PicsumLikeEntity

@Database(entities = [PicsumEntity::class, PicsumLikeEntity::class], version = 3)
abstract class PicsumDatabase : RoomDatabase() {
    abstract fun picsumDao(): PicsumDao

    abstract fun picsumLikeDao(): PicsumLikeDao
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
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `PicsumLikeEntity` " +
                "(`id` INTEGER PRIMARY KEY NOT NULL)",
        )
    }
}

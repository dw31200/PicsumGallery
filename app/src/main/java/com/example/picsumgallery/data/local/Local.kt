package com.example.picsumgallery.data.local

import android.content.Context
import androidx.room.Room

object Local {
    @Volatile
    private var database: PicsumDatabase? = null

    fun init(context: Context) {
        database ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                PicsumDatabase::class.java,
                "picsum.db",
            ).build()
                .also {
                    database = it
                }
        }
    }

    fun getPicsumDao(): PicsumDao {
        return database?.picsumDao() ?: throw IllegalStateException("PicsumDatabase null")
    }
}

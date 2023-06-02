package com.example.picsumgallery.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Picsum::class], version = 1)
abstract class PicsumDatabase : RoomDatabase() {
    abstract fun picsumDao(): PicsumDao

    private class PicsumDatabaseCallback(
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val picsumDao = database.picsumDao()
                    picsumDao.deleteAll()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PicsumDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): PicsumDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PicsumDatabase::class.java,
                    "picsum",
                )
                    .addCallback(PicsumDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

package com.example.picsumgallery.data.local

import android.content.Context
import androidx.room.Room

object Local {
    //  todo 모든 스레드에서 즉시 변경된다는 의미 같은데, 락을 걸면 수정이 불가한거면 둘중 하나만 해도 되는거 아닌가요? 다시 한번 설명 부탁드려요.
    @Volatile
    private var database: PicsumDatabase? = null

    fun init(context: Context) {
        database ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                PicsumDatabase::class.java,
                "picsum.db",
            )
                .build()
                .also {
                    database = it
                }
        }
    }

    fun getPicsumDao(): PicsumDao {
        return database?.picsumDao() ?: throw IllegalStateException("PicsumDatabase null")
    }
}

package com.example.picsumgallery.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Singleton
    @Provides
    fun providePicsumDatabase(
        @ApplicationContext context: Context,
    ): PicsumDatabase {
        return Room.databaseBuilder(
            context,
            PicsumDatabase::class.java,
            "picsum.db",
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun providePicsumDao(
        database: PicsumDatabase,
    ): PicsumDao {
        return database.picsumDao()
    }
}

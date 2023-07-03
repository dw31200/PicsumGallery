package com.example.picsumgallery.data

import com.example.picsumgallery.data.repository.PicsumRepository
import com.example.picsumgallery.data.repository.PicsumRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindPicsumRepository(picsumRepositoryImpl: PicsumRepositoryImpl): PicsumRepository
}

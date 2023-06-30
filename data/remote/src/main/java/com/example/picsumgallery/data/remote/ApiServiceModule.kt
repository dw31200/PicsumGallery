package com.example.picsumgallery.data.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiServiceModule {
    @Binds
    abstract fun bindPicsumApiService(picsumApiServiceImpl: PicsumApiServiceImpl): PicsumApiService
}

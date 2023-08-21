package com.example.picsumgallery.data

import com.example.picsumgallery.data.paging.GalleryListPagingSourceFactory
import com.example.picsumgallery.data.paging.GalleryListPagingSourceFactoryImpl
import com.example.picsumgallery.data.repository.PicsumLikeRepository
import com.example.picsumgallery.data.repository.PicsumLikeRepositoryImpl
import com.example.picsumgallery.data.repository.PicsumRepository
import com.example.picsumgallery.data.repository.PicsumRepositoryImpl
import com.example.picsumgallery.data.repository.SystemSettingsRepository
import com.example.picsumgallery.data.repository.SystemSettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindPicsumRepository(picsumRepositoryImpl: PicsumRepositoryImpl): PicsumRepository

    @Binds
    abstract fun bindPicsumLikeRepository(picsumLikeRepositoryImpl: PicsumLikeRepositoryImpl): PicsumLikeRepository

    @Binds
    abstract fun bindGalleryListPagingSourceFactory(galleryListPagingSourceFactoryImpl: GalleryListPagingSourceFactoryImpl): GalleryListPagingSourceFactory

    @Binds
    abstract fun bindSystemSettingsRepository(systemSettingsRepositoryImpl: SystemSettingsRepositoryImpl): SystemSettingsRepository
}

package com.example.picsumgallery.data.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiServiceModule {
    // TODO Binds로 생성할때는 항상 아래 형식처럼 interface와 Impl의 구조로 만들어야 하나요?
    @Binds
    abstract fun bindPicsumApiService(picsumApiServiceImpl: PicsumApiServiceImpl): PicsumApiService
}

package com.example.picsumgallery.data.repository

import com.example.picsumgallery.data.local.SystemDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SystemSettingsRepositoryImpl @Inject constructor(
    private val systemDataStore: SystemDataStore,
) : SystemSettingsRepository {
    override fun getTheme(): Flow<Int> {
        return systemDataStore.getTheme
    }

    override suspend fun setTheme(theme: Int) {
        systemDataStore.setTheme(theme)
    }
}

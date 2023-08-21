package com.example.picsumgallery.data.repository

import kotlinx.coroutines.flow.Flow

interface SystemSettingsRepository {
    fun getTheme(): Flow<Int>

    suspend fun setTheme(theme: Int)
}

package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.repository.SystemSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SystemSettingsUseCase @Inject constructor(
    private val systemSettingsRepository: SystemSettingsRepository,
) {
    fun getTheme(): Flow<Int> {
        return systemSettingsRepository.getTheme()
    }

    suspend fun setTheme(theme: Int) {
        systemSettingsRepository.setTheme(theme)
    }
}

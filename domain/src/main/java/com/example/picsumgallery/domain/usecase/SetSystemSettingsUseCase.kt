package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.repository.SystemSettingsRepository
import javax.inject.Inject

class SetSystemSettingsUseCase @Inject constructor(
    private val systemSettingsRepository: SystemSettingsRepository,
) {
    suspend operator fun invoke(theme: Int) {
        systemSettingsRepository.setTheme(theme)
    }
}

package com.example.picsumgallery.domain.usecase

import com.example.picsumgallery.data.repository.SystemSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSystemSettingsUseCase @Inject constructor(
    private val systemSettingsRepository: SystemSettingsRepository,
) {
    operator fun invoke(): Flow<Int> {
        return systemSettingsRepository.getTheme()
    }
}

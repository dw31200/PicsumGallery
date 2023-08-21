package com.example.picsumgallery.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.SystemSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val systemSettingsUseCase: SystemSettingsUseCase,
) : ViewModel() {
    enum class Theme {
        SYSTEM, LIGHT, DARK
    }

    var theme = systemSettingsUseCase.getTheme()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            0,
        )

    suspend fun setTheme(theme: Int) {
        systemSettingsUseCase.setTheme(theme)
    }
}

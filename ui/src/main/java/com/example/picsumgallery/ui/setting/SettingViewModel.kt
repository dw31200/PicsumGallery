package com.example.picsumgallery.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetSystemSettingsUseCase
import com.example.picsumgallery.domain.usecase.SetSystemSettingsUseCase
import com.example.picsumgallery.share.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val setSystemSettingsUseCase: SetSystemSettingsUseCase,
    private val getSystemSettingsUseCase: GetSystemSettingsUseCase,
) : ViewModel() {
    var theme = getSystemSettingsUseCase()
        .map {
            Theme.values()[it]
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            Theme.SYSTEM,
        )

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            setSystemSettingsUseCase(theme.ordinal)
        }
    }
}

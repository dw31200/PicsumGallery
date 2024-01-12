package com.example.picsumgallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetSystemSettingsUseCase
import com.example.picsumgallery.share.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSystemSettingsUseCase: GetSystemSettingsUseCase,
) : ViewModel() {
    val theme = getSystemSettingsUseCase()
        .map {
            Theme.values()[it]
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            Theme.SYSTEM,
        )
}

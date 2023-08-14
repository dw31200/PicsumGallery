package com.example.picsumgallery.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {
    enum class Theme {
        SYSTEM, LIGHT, DARK
    }

    private var _theme = MutableLiveData<Theme>(Theme.SYSTEM)
    val theme: LiveData<Theme>
        get() = _theme
}

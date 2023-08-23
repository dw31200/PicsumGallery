package com.example.picsumgallery.ui.setting

import androidx.databinding.BindingAdapter
import com.example.picsumgallery.share.Theme
import com.example.picsumgallery.ui.R
import com.google.android.material.button.MaterialButtonToggleGroup

@BindingAdapter("bind:checkedButton")
fun MaterialButtonToggleGroup.setCheckedButton(theme: Theme) {
    when (theme) {
        Theme.SYSTEM -> check(R.id.system_mode_button)
        Theme.LIGHT -> check(R.id.light_mode_button)
        Theme.DARK -> check(R.id.dark_mode_button)
    }
}

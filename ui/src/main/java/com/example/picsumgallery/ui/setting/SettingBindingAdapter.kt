package com.example.picsumgallery.ui.setting

import androidx.databinding.BindingAdapter
import com.example.picsumgallery.ui.R
import com.google.android.material.button.MaterialButtonToggleGroup

@BindingAdapter("bind:checkedButton")
fun MaterialButtonToggleGroup.setCheckedButton(theme: Int) {
    when (theme) {
        0 -> check(R.id.system_mode_button)
        1 -> check(R.id.light_mode_button)
        2 -> check(R.id.dark_mode_button)
    }
}

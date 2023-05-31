package com.example.picsumgallery.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:integerToString")
fun TextView.setTextFromInteger(number: Int?) {
    text = number.toString()
}

@BindingAdapter("bind:imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide
        .with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("bind:visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:enabled")
fun View.setEnabled(enabled: Boolean) {
    isEnabled = enabled
}

package com.example.picsumgallery.ui.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:integerToString")
fun TextView.setTextFromInteger(number: Int) {
    text = number.toString()
}

// todo imageView가 어떻게 파라미터에 들어가는지 모르겠습니다.
@BindingAdapter("bind:imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Glide
        .with(imageView)
        .load(url)
        .into(imageView)
}

// todo View.setVisible 과 setImageUrl 차이
// todo 이름이 같은 bind 함수를 따로 부르는 방법이 있나요?
@BindingAdapter("bind:visibleImage")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:enabled")
fun View.setEnabled(enabled: Boolean) {
    visibility = if (enabled) View.VISIBLE else View.INVISIBLE
    isEnabled = enabled
}

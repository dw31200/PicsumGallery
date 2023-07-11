package com.example.picsumgallery.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.example.picsumgallery.ui.databinding.WidgetLabelTextBinding

@BindingMethods(
    value = [
        BindingMethod(
            type = LabelTextWidget::class,
            attribute = "bind:text",
            method = "setText",
        ),
        BindingMethod(
            type = LabelTextWidget::class,
            attribute = "bind:label",
            method = "setLabel",
        ),
    ],
)
class LabelTextWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = WidgetLabelTextBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )
    var text: String?
        set(value) {
            binding.text.text = value
        }
        get() = binding.text.text.toString()

    fun setText(number: Int?) {
        binding.text.text = number.toString()
    }

    var label: String?
        set(value) {
            binding.label.text = value
        }
        get() = binding.label.text.toString()
}

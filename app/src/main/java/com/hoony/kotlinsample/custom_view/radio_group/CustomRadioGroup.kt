package com.hoony.kotlinsample.custom_view.radio_group

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class CustomRadioGroup @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleRes: Int = 0
) : LinearLayout(context, attributeSet, defStyleRes) {

    init {
        orientation = VERTICAL
    }
}
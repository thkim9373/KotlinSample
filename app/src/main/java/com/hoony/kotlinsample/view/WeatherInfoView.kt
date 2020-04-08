package com.hoony.kotlinsample.view

import android.content.Context
import android.util.AttributeSet
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.view_info.view.*

class WeatherInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : InfoView(context, attrs, defStyleAttr) {

    init {
        typeImage.setImageResource(R.drawable.ic_weather)
        infoText.text = ""
    }

    fun setWeather(weatherText: String) {
        if (weatherText.isEmpty()) {
            infoText.text = "날씨 정보가 없습니다."
        } else {
            infoText.text = weatherText
        }
    }
}
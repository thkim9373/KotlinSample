package com.hoony.kotlinsample.animation.interpolator

import android.util.Log
import android.view.animation.Interpolator
import kotlin.math.cos

class CustomInterpolator : Interpolator {

    override fun getInterpolation(input: Float): Float {
//        val value = input
        val value = -cos(input * 20) + 1
        Log.d("interpolator", "input : $input value : $value")
        return value
    }
}
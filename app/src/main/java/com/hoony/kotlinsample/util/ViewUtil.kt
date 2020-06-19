package com.hoony.kotlinsample.util

import android.content.Context

class ViewUtil {
    companion object {
        fun convertPixelsToDp(
            context: Context,
            px: Float
        ): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return px / (metrics.densityDpi / 160f)
        }

        fun convertDpToPixel(
            context: Context,
            dp: Float
        ): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return dp * (metrics.densityDpi / 160f)
        }
    }
}
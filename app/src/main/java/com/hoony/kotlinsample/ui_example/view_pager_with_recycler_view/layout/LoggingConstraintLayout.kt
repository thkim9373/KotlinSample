package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoony.kotlinsample.util.Tag
import kotlin.math.abs

class LoggingConstraintLayout(
    context: Context,
    attrs: AttributeSet?,
    int: Int
) : ConstraintLayout(context, attrs, int) {
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null, 0)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        val result = false
        val result = super.dispatchTouchEvent(ev)
        Log.d(Tag.TAG_TOUCH, "layout - dispatchTouchEvent result : $result")
        return result
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        val result = super.onInterceptTouchEvent(ev)
        val result = true
        Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent result : $result")
        return result
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        val result = true
//        val result = super.dispatchTouchEvent(ev)
        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent result : $result")
        return result
    }
}
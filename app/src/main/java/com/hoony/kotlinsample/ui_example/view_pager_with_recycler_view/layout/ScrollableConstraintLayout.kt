package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoony.kotlinsample.util.Tag
import kotlin.math.abs

class ScrollableConstraintLayout(
    context: Context,
    attrs: AttributeSet?,
    int: Int
) : ConstraintLayout(context, attrs, int) {
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null, 0)

    companion object {
        const val NON = -1
        const val VERTICAL = 0
        const val HORIZONTAL = 1
    }

    var orientation: Int = NON
    private var scaleSlop: Int = -1
    private var startX: Float = 0f
    private var startY: Float = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onInterceptTouchEvent")

        if (scaleSlop == -1) {
            scaleSlop = ViewConfiguration.get(this.context).scaledTouchSlop
        }

        when (ev?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onInterceptTouchEvent ACTION_DOWN")
                startX = ev.x
                startY = ev.y
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onInterceptTouchEvent ACTION_UP or ACTION_CANCEL")
                orientation = NON
            }
        }

        if (orientation == NON) {
            Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onInterceptTouchEvent return true")
            return true
        }

        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onInterceptTouchEvent return false")
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent")

        when (ev?.actionMasked) {
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent ACTION_UP or ACTION_CANCEL")
                orientation = NON
            }
        }

        if(orientation == NON) {
            when (ev?.actionMasked) {
                MotionEvent.ACTION_MOVE -> {
                    Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent ACTION_MOVE")
                    if (abs(ev.x - startX) > scaleSlop) {
                        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent HORIZONTAL")
                        orientation = HORIZONTAL
                        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent return false")
                        ev.action = MotionEvent.ACTION_DOWN
                        onTouchEvent(ev)
                        return false
                    }
                    if (abs(ev.y - startY) > scaleSlop) {
                        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent VERTICAL")
                        orientation = VERTICAL
                        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent return false")
                        ev.action = MotionEvent.ACTION_DOWN
                        onTouchEvent(ev)
                        return false
                    }
                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {
                    Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent ACTION_UP or ACTION_CANCEL")
                    orientation = NON
                }
            }
        } else {
            onTouchEvent(ev)
            return false
        }
        Log.d(Tag.TAG_TOUCH, "ScrollableConstraintLayout onTouchEvent return true")
        return true
    }
}
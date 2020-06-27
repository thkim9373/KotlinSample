package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoony.kotlinsample.util.Tag
import java.util.*
import kotlin.math.abs

class OrientationConstraintLayout(
    context: Context,
    attrs: AttributeSet?,
    int: Int
) : ConstraintLayout(context, attrs, int) {
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null, 0)

    enum class Orientation {
        NON, VERTICAL, HORIZONTAL
    }

    interface OnPreTouchListener {
        fun onActionDown(ev: MotionEvent)
    }

    fun addListener(listener: OnPreTouchListener) {
        listenerList.add(listener)
    }

    private fun onActionDown(ev: MotionEvent) {
        for (listener in listenerList) {
            listener.onActionDown(ev)
        }
    }

    private var listenerList: MutableList<OnPreTouchListener> = arrayListOf()

    var orientation: Orientation = Orientation.NON
    private var scaleSlop: Int = -1
    private var firstMotionEvent: MotionEvent? = null
    private val motionQueue: Queue<MotionEvent> = LinkedList()
    private var startX: Float = 0f
    private var startY: Float = 0f

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                firstMotionEvent = ev
                onActionDown(ev)
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
//                Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent ACTION_UP or ACTION_CANCEL")
                orientation = Orientation.NON
                firstMotionEvent = null
                val result = super.dispatchTouchEvent(ev)
                Log.d(Tag.TAG_TOUCH, "layout - dispatchTouchEvent result : $result")
                return result
            }
        }
        if (orientation == Orientation.NON) {
            Log.d(Tag.TAG_TOUCH, "layout - dispatchTouchEvent return true")
            onInterceptTouchEvent(ev)
            return true
        }
        val result = super.dispatchTouchEvent(ev)
        Log.d(Tag.TAG_TOUCH, "layout - dispatchTouchEvent result : $result")
        return result
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent")

        if (scaleSlop == -1) {
            scaleSlop = ViewConfiguration.get(this.context).scaledTouchSlop
        }

        when (ev?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
//                Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent ACTION_DOWN")
                startX = ev.x
                startY = ev.y
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
//                Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent ACTION_UP or ACTION_CANCEL")
                orientation = Orientation.NON
                firstMotionEvent = null
            }
        }

        if (orientation == Orientation.NON) {
            Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent return true")
            onTouchEvent(ev)
            return true
        }

//        Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent return false")
//        return false
        val result = super.onInterceptTouchEvent(ev)
        Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent result : $result")
        return result
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
//        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent")

        when (ev?.actionMasked) {
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
//                Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent ACTION_UP or ACTION_CANCEL")
                orientation = Orientation.NON
                firstMotionEvent = null
            }
        }

        if (orientation == Orientation.NON) {
            when (ev?.actionMasked) {
                MotionEvent.ACTION_MOVE -> {
//                    Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent ACTION_MOVE")
                    if (isScrollVertical(ev)) {
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent VERTICAL")
                        orientation = Orientation.VERTICAL
                        ev.action = MotionEvent.ACTION_DOWN
                        dispatchTouchEvent(firstMotionEvent)
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent return true")
                        return true
                    }
                    if (isScrollHorizontal(ev)) {
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent HORIZONTAL")
                        orientation = Orientation.HORIZONTAL
                        ev.action = MotionEvent.ACTION_DOWN
                        dispatchTouchEvent(firstMotionEvent)
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent return true")
                        return true
                    }
                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {
//                    Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent ACTION_UP or ACTION_CANCEL")
                    orientation = Orientation.NON
                }
            }
            Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent return true")
            return true
        }
//        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent return true")
//        return true

        val result = super.onTouchEvent(ev)
        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent result : $result")
        return result
    }

    private fun isScrollVertical(ev: MotionEvent): Boolean {
        return abs(ev.y - startY) > scaleSlop
    }

    private fun isScrollHorizontal(ev: MotionEvent): Boolean {
        return abs(ev.x - startX) > scaleSlop
    }
}
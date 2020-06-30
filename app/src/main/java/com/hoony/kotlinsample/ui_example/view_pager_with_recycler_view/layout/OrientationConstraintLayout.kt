package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoony.kotlinsample.util.Tag
import kotlin.math.abs

class OrientationConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleInt: Int = 0
) : ConstraintLayout(context, attrs, defStyleInt) {

    enum class Orientation {
        NON, VERTICAL, HORIZONTAL
    }

    /**
     * 해당 뷰 그룹에서 orientation 을 결정하는 중에 touch event 수신을 알아야할 경우 사용하는 interface.
     */
    interface OnPreTouchListener {
        /**
         * ACTION_DOWN 이 발생했을 때 호출
         * @param ev ACTION_DOWN 일 때 들어온 motion event.
         */
        fun onActionDown(ev: MotionEvent)

        fun onScrollStateChanged(orientation: Orientation, isScrolling: Boolean)
    }

    fun addListener(listener: OnPreTouchListener) {
        listenerList.add(listener)
    }

    private fun onActionDown(ev: MotionEvent) {
        for (listener in listenerList) {
            listener.onActionDown(ev)
        }
    }

    private fun scrollStateChange(orientation: Orientation, isScrolling: Boolean) {
        for (listener in listenerList) {
            listener.onScrollStateChanged(orientation, isScrolling)
        }
    }

    private var listenerList: MutableList<OnPreTouchListener> = arrayListOf()
    private var isClick: Boolean = false
    var orientation: Orientation = Orientation.NON  // 사용자의 터치 방향
    private var scaleSlop: Int = -1 // 유저 액션이 스크롤 중인지 판단할 때 사용하는 거리
    private var firstMotionEvent: MotionEvent? = null
    private var startX: Float = 0f
    private var startY: Float = 0f

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(
            "click",
            "layout - dispatchTouchEvent action : ${ev?.actionMasked}    orientation : $orientation    isClick : $isClick"
        )
        when (ev?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (orientation == Orientation.NON && !isClick) {
                    firstMotionEvent = ev
                    onActionDown(ev)
                    onInterceptTouchEvent(ev)
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (orientation == Orientation.NON) {
                    onInterceptTouchEvent(ev)
                    return true
                }
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                scrollStateChange(orientation, false)

                val originAction = ev.action

                if (orientation == Orientation.NON) {
                    ev.action = MotionEvent.ACTION_DOWN
                    isClick = true
                    dispatchTouchEvent(ev)
                    ev.addBatch(
                        ev.eventTime + 1,
                        ev.x,
                        ev.y,
                        ev.pressure,
                        ev.size,
                        ev.metaState
                    )

                    ev.action = originAction
                    val result = super.dispatchTouchEvent(ev)

                    orientation = Orientation.NON
                    firstMotionEvent = null

                    isClick = false

                    return result
                }

                ev.action = originAction
                val result = super.dispatchTouchEvent(ev)

                orientation = Orientation.NON
                firstMotionEvent = null
                return result
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent")
        Log.d(
            "click",
            "layout - onInterceptTouchEvent action : ${ev?.actionMasked}    orientation : $orientation    isClick : $isClick"
        )
        if (scaleSlop == -1) {
            scaleSlop = ViewConfiguration.get(this.context).scaledTouchSlop
        }

        if (orientation == Orientation.NON && !isClick) {
            when (ev?.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    startX = ev.x
                    startY = ev.y
                }
            }
            onTouchEvent(ev)
            return true
        }

//        val result = super.onInterceptTouchEvent(ev)
//        Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent result : $result")
//        return result
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(
            "click",
            "layout - onTouchEvent action : ${ev?.actionMasked}    orientation : $orientation    isClick : $isClick"
        )
        if (orientation == Orientation.NON) {
            when (ev?.actionMasked) {
                MotionEvent.ACTION_MOVE -> {
                    if (isScrollVertical(ev)) {
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent VERTICAL")
                        orientation = Orientation.VERTICAL

                        scrollStateChange(orientation, true)
//                        dispatchSavedMotionEvents()
                        printSamples(ev)
                        ev.action = MotionEvent.ACTION_DOWN
                        dispatchTouchEvent(ev)
                        return true
                    }
                    if (isScrollHorizontal(ev)) {
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent HORIZONTAL")
                        orientation = Orientation.HORIZONTAL

                        scrollStateChange(orientation, true)
//                        dispatchSavedMotionEvents()
                        printSamples(ev)
                        ev.action = MotionEvent.ACTION_DOWN
                        dispatchTouchEvent(ev)
                        return true
                    }
                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {

                    orientation = Orientation.NON
                }
            }
            return true
        }

        return super.onTouchEvent(ev)
    }

    private fun printSamples(ev: MotionEvent) {
        val historySize = ev.historySize
        val pointerCount = ev.pointerCount
        Log.d("history", "historySize : ${ev.historySize}   pointerCount : ${ev.pointerCount}")
        for (h in 0 until historySize) {
            Log.d("history", "At time ${ev.getHistoricalEventTime(h)}:")
            for (p in 0 until pointerCount) {
                Log.d(
                    "history",
                    "  pointer ${ev.getPointerId(p)}: (${ev.getHistoricalX(
                        p,
                        h
                    )},${ev.getHistoricalY(p, h)})"
                )
            }
        }
        Log.d("history", "At time ${ev.eventTime}:")
        for (p in 0 until pointerCount) {
            Log.d(
                "history",
                "  pointer ${ev.getPointerId(p)}: (${ev.getX(p)},${ev.getY(p)})"
            )
        }
    }

    private fun isScrollVertical(ev: MotionEvent): Boolean {
        return abs(ev.y - startY) > scaleSlop
    }

    private fun isScrollHorizontal(ev: MotionEvent): Boolean {
        return abs(ev.x - startX) > scaleSlop
    }
}
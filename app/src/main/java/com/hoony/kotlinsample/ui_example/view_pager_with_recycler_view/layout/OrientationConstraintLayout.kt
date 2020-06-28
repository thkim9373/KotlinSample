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

    /**
     * 해당 뷰 그룹에서 orientation 을 결정하는 중에 touch event 수신을 알아야할 경우 사용하는 interface.
     */
    interface OnPreTouchListener {
        /**
         * ACTION_DOWN 이 발생했을 때 호출
         * @param ev ACTION_DOWN 일 때 들어온 motion event.
         */
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

    var orientation: Orientation = Orientation.NON  // 사용자의 터치 방향
    var isExporting: Boolean = false
    private var scaleSlop: Int = -1 // 유저 액션이 스크롤 중인지 판단할 때 사용하는 거리
    private var firstMotionEvent: MotionEvent? = null
    private val motionQueue: Queue<MotionEvent> = LinkedList()
    private var startX: Float = 0f
    private var startY: Float = 0f

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (orientation == Orientation.NON) {
                    firstMotionEvent = ev
                    Log.d(Tag.TAG_TOUCH, "add down action")
//                    motionQueue.add(ev)
                    addQueuePrintLog(ev)
                    onActionDown(ev)

                    Log.d(Tag.TAG_TOUCH, "layout - dispatchTouchEvent return true")
                    onInterceptTouchEvent(ev)
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (orientation == Orientation.NON) {
//                    motionQueue.add(ev)
                    addQueuePrintLog(ev)
                    Log.d(Tag.TAG_TOUCH, "layout - dispatchTouchEvent return true")
                    onInterceptTouchEvent(ev)
                    return true
                }
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
//                Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent ACTION_UP or ACTION_CANCEL")

                dispatchSavedMotionEvents()

//                if (orientation == Orientation.NON && firstMotionEvent != null) {
//                    Log.d(Tag.TAG_CLICK, "")
//                    super.dispatchTouchEvent(firstMotionEvent)
//                }

//                if(firstMotionEvent != null) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        performContextClick(firstMotionEvent!!.x, firstMotionEvent!!.y)
//                    }
//                }

                val result = super.dispatchTouchEvent(ev)

                orientation = Orientation.NON
                firstMotionEvent = null
                Log.d(Tag.TAG_TOUCH, "layout - dispatchTouchEvent result : $result")
                return result
            }
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

        if (orientation == Orientation.NON) {
            when (ev?.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
//                Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent ACTION_DOWN")

                    startX = ev.x
                    startY = ev.y
                }
//            MotionEvent.ACTION_UP,
//            MotionEvent.ACTION_CANCEL -> {
////                Log.d(Tag.TAG_TOUCH, "layout - onInterceptTouchEvent ACTION_UP or ACTION_CANCEL")
//                orientation = Orientation.NON
//                firstMotionEvent = null
//            }
            }

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
        if (orientation == Orientation.NON) {
            when (ev?.actionMasked) {
                MotionEvent.ACTION_MOVE -> {
                    if (isScrollVertical(ev)) {
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent VERTICAL")
                        orientation = Orientation.VERTICAL
                        dispatchSavedMotionEvents()
                        printSamples(ev)
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent return true")
                        return true
                    }
                    if (isScrollHorizontal(ev)) {
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent HORIZONTAL")
                        orientation = Orientation.HORIZONTAL
                        dispatchSavedMotionEvents()
                        printSamples(ev)
                        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent return true")
                        return true
                    }
                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {
                    orientation = Orientation.NON
                }
            }
            Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent return true")
            return true
        }

        val result = super.onTouchEvent(ev)
        Log.d(Tag.TAG_TOUCH, "layout - onTouchEvent result : $result")
        return result
    }

    private fun addQueuePrintLog(ev: MotionEvent) {
        motionQueue.add(ev)
        for (motion in motionQueue) {
            Log.d("queue", "ev.action : ${motion.action}")
        }
        Log.d("queue", "=============================================")
    }


    private fun dispatchSavedMotionEvents() {
        isExporting = true
        while (motionQueue.isNotEmpty()) {
            Log.d(
                Tag.TAG_TOUCH,
                "dispatchTouchEvent user queue item action : ${motionQueue.peek().action}"
            )
            val ev = motionQueue.poll()
            dispatchTouchEvent(ev)
        }
        isExporting = false
    }

    fun printSamples(ev: MotionEvent) {
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
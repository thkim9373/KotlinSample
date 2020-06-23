package com.hoony.kotlinsample.behavior.scroll_example_3

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.OverScroller
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.util.ViewUtil
import kotlinx.android.synthetic.main.activity_behavior_example_5.view.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class ViewPagerBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var dyDirectionSum: Int = 0
    private val recyclerViewOriginTopMargin: Int = ViewUtil.convertDpToPixel(context, 400f).toInt()
    private var isBeginDragged: Boolean = false
    private var touchSlop: Int = -1
    private var lastMotionY: Float = 0f
    private lateinit var velocityTracker: VelocityTracker
    private var overScroller: OverScroller = OverScroller(context)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL && getRecyclerViewTopMargin() <= 0
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {

    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        dyDirectionSum = min(0, dyDirectionSum - dyConsumed)

        // View pager 이동

        scroll(coordinatorLayout, child)
//        target.translationY = max(dyDirectionSum, -child.height).toFloat()
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {
//        Log.d("onInterceptTouchEvent", "ev.flags : ${ev.flags}")
        if (touchSlop < 0) {
            touchSlop = ViewConfiguration.get(parent.context).scaledTouchSlop
        }

        val action = ev.action

        if (action == MotionEvent.ACTION_MOVE &&
            isBeginDragged &&
            getRecyclerViewTopMargin() > 0
        ) return true

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                isBeginDragged = false
                lastMotionY = ev.y
                velocityTracker = VelocityTracker.obtain()
            }
            MotionEvent.ACTION_MOVE -> {
//                dyDirectionSum -= (lastMotionY - ev.y).toInt()
//                dyDirectionSum = min(0, dyDirectionSum)
//                lastMotionY = ev.y
//                scroll(parent, child)

                val y = ev.y
                val yDiff = abs(y - lastMotionY)
                if (yDiff > touchSlop) {
                    isBeginDragged = true
                    lastMotionY = y
                }
                Log.d(
                    "onInterceptTouchEvent",
                    "ACTION_MOVE    ev.y : ${ev.y}     (yDown - ev.y).toInt() : ${(lastMotionY - ev.y).toInt()}"
                )
            }
            MotionEvent.ACTION_UP -> {
                isBeginDragged = false
                lastMotionY = ev.y
                velocityTracker.recycle()
            }
            MotionEvent.ACTION_CANCEL -> {
                isBeginDragged = false
                lastMotionY = ev.y
                velocityTracker.recycle()
            }
        }
        return false
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
//        Log.d("onTouchEvent", "ev.flags : ${ev.flags}")
        if (getRecyclerViewTopMargin() <= 0)
            return false

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                lastMotionY = ev.y
                Log.d("onTouchEvent", "ACTION_DOWN      ev.x : ${ev.x}    ev.y : ${ev.y}")
            }
            MotionEvent.ACTION_MOVE -> {
                dyDirectionSum -= (lastMotionY - ev.y).toInt()
                dyDirectionSum = min(0, dyDirectionSum)
                lastMotionY = ev.y
                scroll(parent, child)
                Log.d("onTouchEvent", "ACTION_SCROLL    ev.x : ${ev.x}    ev.y : ${ev.y}")
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.addMovement(ev)
                velocityTracker.computeCurrentVelocity(1000)
                val yvel = velocityTracker.getYVelocity()
                Log.d("onTouchEvent", "ACTION_UP        ev.x : ${ev.x}    ev.y : ${ev.y}")
            }
            else -> {
                Log.d(
                    "onTouchEvent",
                    "else     ev.actionMasked : ${ev.action}        ev.x : ${ev.x}    ev.y : ${ev.y}"
                )
            }
        }
        return true
    }

    private fun scroll(parent: CoordinatorLayout, child: View) {
        // Move view pager
        child.translationY = dyDirectionSum.toFloat()

        val topMargin = getRecyclerViewTopMargin()

        val target = parent.rvList as RecyclerView
        val layoutParams = target.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.topMargin = topMargin

        target.layoutParams = layoutParams

//        Log.d(
//            "onNestedScroll",
//            "dyDirectionSum: $dyDirectionSum     ViewUtil.convertDpToPixel(recyclerView.context, 400f).toInt() + dyDirectionSum: ${ViewUtil.convertDpToPixel(
//                target.context, 400f
//            ).toInt() + dyDirectionSum}      topMargin : $topMargin"
//        )
    }

    private fun getRecyclerViewTopMargin(): Int {
        return max(recyclerViewOriginTopMargin + dyDirectionSum, 0)
    }

//    private inner class FlingRunnable(private val parent: CoordinatorLayout,private val layout: ) : Runnable {
//
//
//        override fun run() {
//            TODO("Not yet implemented")
//        }
//
//    }
}
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
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.util.ViewUtil
import kotlinx.android.synthetic.main.activity_behavior_example_5.view.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class RecyclerViewBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var dyDirectionSum: Int = 0
    private val recyclerViewOriginTopMargin: Int = ViewUtil.convertDpToPixel(context, 400f).toInt()
    private var isBeginDragged: Boolean = false
    private var touchSlop: Int = -1
    private var lastMotionY: Float = 0f
    private var velocityTracker: VelocityTracker? = null
    private var overScroller: OverScroller = OverScroller(context)
    private var flingRunnable: FlingRunnable? = null

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {
        return (child.layoutParams as CoordinatorLayout.LayoutParams).topMargin > 0
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
//        Log.d("onTouchEvent", "ev.flags : ${ev.flags}")
        if (getRecyclerViewTopMargin() < 0)
            return false

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                lastMotionY = ev.y
                obtainVelocityTacker()
            }
            MotionEvent.ACTION_MOVE -> {
                dyDirectionSum -= (lastMotionY - ev.y).toInt()
                dyDirectionSum = min(0, dyDirectionSum)
                lastMotionY = ev.y
                velocityTracker?.addMovement(ev)
                scroll(parent, child)
            }
            MotionEvent.ACTION_UP -> {
                Log.d("fling", "onTouchEvent action up")
                if (velocityTracker != null) {
                    Log.d("fling", "velocity tracker is not null!!")
                    velocityTracker?.let {
                        it.addMovement(ev)
                        it.computeCurrentVelocity(1000)
                        fling(
                            parent,
                            child,
                            getRecyclerViewTopMargin(),
                            0,
                            it.yVelocity
                        )
                    }
                } else {
                    Log.d("fling", "velocity tracker is null!!")
                }
                recyclerVelocityTracker()
            }
        }
        return true
    }

    private fun obtainVelocityTacker() {
        if (velocityTracker != null) {
            velocityTracker = VelocityTracker.obtain()
        }
    }

    private fun recyclerVelocityTracker() {
        velocityTracker?.recycle()
        velocityTracker = null
    }

    private fun scroll(parent: CoordinatorLayout, child: View) {
        // Move view pager
//        ViewCompat.offsetTopAndBottom(child, -dyDirectionSum)
//        Log.d("offsetTopAndBottom", "dyDirectionSum $dyDirectionSum")
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

    private fun fling(
        coordinatorLayout: CoordinatorLayout,
        view: View,
        minOffset: Int,
        maxOffset: Int,
        velocityY: Float
    ): Boolean {
        if (flingRunnable != null) {
            flingRunnable = null
        }
        Log.d("fling", "start fling")

        overScroller.fling(
            0,
            lastMotionY.toInt(),
            0,
            velocityY.toInt(),
            0,
            0,
            minOffset,
            maxOffset
        )

        if (overScroller.computeScrollOffset()) {
            Log.d("fling", "overScroller.computeScrollOffset() true")
            flingRunnable = FlingRunnable(coordinatorLayout, view)
            ViewCompat.postOnAnimation(view, flingRunnable)
            return true
        } else {
            Log.d("fling", "overScroller.computeScrollOffset() false")
            return false
        }
    }

    private inner class FlingRunnable(
        private val parent: CoordinatorLayout,
        private val view: View
    ) : Runnable {

        override fun run() {
            if (overScroller.computeScrollOffset()) {
                Log.d("FlingRunnable", "overScroller.currY : ${overScroller.currY}")
            }
        }
    }
}
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

class ViewPagerBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var dyDirectionSum: Int = 0 // max = 0
    private val recyclerViewOriginTopMargin: Int = ViewUtil.convertDpToPixel(context, 400f).toInt()
    private var isBeginDragged: Boolean = false
    private var touchSlop: Int = -1
    private var lastMotionY: Float = 0f
    private var startFlingY: Float = 0f
    private var velocityTracker: VelocityTracker? = null
    private var overScroller: OverScroller = OverScroller(context)
    private var flingRunnable: FlingRunnable? = null

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
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

        if (touchSlop < 0) {
            touchSlop = ViewConfiguration.get(parent.context).scaledTouchSlop
        }

        val action = ev.action

        if ((action == MotionEvent.ACTION_MOVE && isBeginDragged) && getRecyclerViewTopMargin() > 0) return true

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                isBeginDragged = false
                lastMotionY = ev.y
                obtainVelocityTacker()
            }
            MotionEvent.ACTION_MOVE -> {
                val y = ev.y
                val yDiff = abs(y - lastMotionY)
                if (yDiff > touchSlop) {
                    isBeginDragged = true
                    lastMotionY = y
                }
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                isBeginDragged = false
                lastMotionY = ev.y
//                recyclerVelocityTracker()
            }
        }
        return false
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
                recyclerVelocityTracker()
            }
        }
        return true
    }

    private fun obtainVelocityTacker() {
        if (velocityTracker == null) {
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
        Log.d("fling", "lastMotionY : $lastMotionY    velocityY : $velocityY")

        startFlingY = lastMotionY
        overScroller.fling(
            0,
            lastMotionY.toInt(),
            0,
            velocityY.toInt(),
            0,
            0,
            -10000,
            10000
        )

        if (overScroller.computeScrollOffset()) {
            flingRunnable = FlingRunnable(coordinatorLayout, view)
            ViewCompat.postOnAnimation(view, flingRunnable)
            return true
        } else {
            return false
        }
    }

    private inner class FlingRunnable(
        private val parent: CoordinatorLayout,
        private val view: View
    ) : Runnable {

        override fun run() {
            val changeY = (overScroller.currY - startFlingY).toInt()
            if (overScroller.computeScrollOffset()) {
                if(getRecyclerViewTopMargin() > 0) {
                    dyDirectionSum += (overScroller.currY - startFlingY).toInt()
                    Log.d(
                        "fling",
                        "(startFlingY - overScroller.currY).toInt() : ${(startFlingY - overScroller.currY).toInt()}"
                    )
                    startFlingY = overScroller.currY.toFloat()
                    dyDirectionSum = min(0, dyDirectionSum)
                    Log.d("fling", "dyDirectionSum : $dyDirectionSum")
                    Log.d(
                        "fling",
                        "overScroller.currY : ${overScroller.currY}      overScroller.finalY : ${overScroller.finalY}"
                    )
                    scroll(parent, view)
                } else {
//                    parent.rvList.scrollY = changeY
//                    onNestedScroll(parent, view, parent.rvList, 0, changeY, 0, 0, 0, intArrayOf())
                }
                ViewCompat.postOnAnimation(parent, this)
            }
        }
    }
}
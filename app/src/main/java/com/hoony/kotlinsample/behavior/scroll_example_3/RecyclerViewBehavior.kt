package com.hoony.kotlinsample.behavior.scroll_example_3

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.util.ViewUtil
import kotlinx.android.synthetic.main.activity_behavior_example_5.view.*
import kotlin.math.max
import kotlin.math.min

class RecyclerViewBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var dyDirectionSum: Int = 0
    private var yDown: Float = 0f

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        Log.d(
            "onStartNestedScroll",
            "onStartNestedScroll return : ${axes == ViewCompat.SCROLL_AXIS_VERTICAL}"
        )
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
        dyDirectionSum -= dyConsumed

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
        val viewPager = child as ViewPager2
        when(ev.action) {
            MotionEvent.ACTION_DOWN -> {
                yDown = ev.y
                Log.d("onInterceptTouchEvent", "ACTION_DOWN      ev.x : ${ev.x}    ev.y : ${ev.y}")
            }
            MotionEvent.ACTION_SCROLL -> {
                Log.d("onInterceptTouchEvent", "ACTION_SCROLL    ev.x : ${ev.x}    ev.y : ${ev.y}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("onInterceptTouchEvent", "ACTION_UP        ev.x : ${ev.x}    ev.y : ${ev.y}")
            }
            MotionEvent.ACTION_MOVE -> {
                dyDirectionSum -= (yDown - ev.y).toInt()
                dyDirectionSum = min(0, dyDirectionSum)
                yDown = ev.y
                scroll(parent, child)
                Log.d("onInterceptTouchEvent", "ACTION_MOVE    ev.y : ${ev.y}     (yDown - ev.y).toInt() : ${(yDown - ev.y).toInt()}")
            }
            else -> {
                Log.d("onInterceptTouchEvent", "else     ev.actionMasked : ${ev.action}        ev.x : ${ev.x}    ev.y : ${ev.y}")
            }
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }

//    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
////        Log.d("onTouchEvent", "ev.flags : ${ev.flags}")
//        when(ev.action) {
//            MotionEvent.ACTION_DOWN -> {
//                Log.d("onTouchEvent", "ACTION_DOWN      ev.x : ${ev.x}    ev.y : ${ev.y}")
//            }
//            MotionEvent.ACTION_SCROLL -> {
//                Log.d("onTouchEvent", "ACTION_SCROLL    ev.x : ${ev.x}    ev.y : ${ev.y}")
//            }
//            MotionEvent.ACTION_UP -> {
//                Log.d("onTouchEvent", "ACTION_UP        ev.x : ${ev.x}    ev.y : ${ev.y}")
//            }
//            else -> {
//                Log.d("onTouchEvent", "else     ev.actionMasked : ${ev.action}        ev.x : ${ev.x}    ev.y : ${ev.y}")
//            }
//        }
//        return true
//    }

    private fun scroll(parent: CoordinatorLayout, child: View) {
        child.translationY = dyDirectionSum.toFloat()

        val target = parent.rvList as RecyclerView
//        val layoutParams = target.layoutParams as CoordinatorLayout.LayoutParams

        val topMargin =
            max(ViewUtil.convertDpToPixel(target.context, 400f).toInt() + dyDirectionSum, 0)

//        layoutParams.topMargin = topMargin
//
//        target.layoutParams = layoutParams

        (target.layoutParams as CoordinatorLayout.LayoutParams).topMargin = topMargin

        Log.d(
            "onNestedScroll",
            "dyDirectionSum: $dyDirectionSum     ViewUtil.convertDpToPixel(recyclerView.context, 400f).toInt() + dyDirectionSum: ${ViewUtil.convertDpToPixel(
                target.context, 400f).toInt() + dyDirectionSum}      topMargin : $topMargin"
        )
    }
}
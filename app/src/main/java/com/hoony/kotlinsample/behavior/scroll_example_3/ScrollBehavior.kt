package com.hoony.kotlinsample.behavior.scroll_example_3

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import kotlin.math.max

class ScrollBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var dyDirectionSum: Int = 0

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

//        if (dyDirectionSum <= child.height) dyDirectionSum = child.height
//        if (dyDirectionSum > 0) dyDirectionSum = 0

        Log.d(
            "onNestedScroll",
            "dyConsumed : $dyConsumed    dyDirectionSum: $dyDirectionSum     -child.height: ${-child.height}"
        )

        child.translationY = dyDirectionSum.toFloat()
        target.translationY = max(dyDirectionSum, -child.height).toFloat()
    }
}
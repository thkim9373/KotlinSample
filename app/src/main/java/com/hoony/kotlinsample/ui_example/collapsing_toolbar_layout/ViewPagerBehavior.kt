package com.hoony.kotlinsample.ui_example.collapsing_toolbar_layout

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout

class ViewPagerBehavior : CoordinatorLayout.Behavior<ViewPager2>() {
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: ViewPager2,
        dependency: View
    ): Boolean {
        return dependency is NestedScrollView
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ViewPager2,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if (dyUnconsumed < 0) {

        }
    }


}
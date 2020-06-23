package com.hoony.kotlinsample.behavior.scroll_example_3

import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.util.ViewUtil
import kotlinx.android.synthetic.main.activity_behavior_example_5.*

class BehaviorExampleActivity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_example_5)

        initViewPager()
        initRecyclerView()
    }

    private fun initViewPager() {
        vpMain.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = ViewPagerAdapter()
        }
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager = LinearLayoutManager(
                this@BehaviorExampleActivity5,
                LinearLayoutManager.VERTICAL,
                false
            )

//                {
//                    override fun canScrollVertically(): Boolean {
//                        return false
//                    }
//                }

            addItemDecoration(
                DividerItemDecoration(
                    this@BehaviorExampleActivity5,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = ListAdapter()

//            val display = this@BehaviorExampleActivity5.windowManager.defaultDisplay
//            val size = Point()
//            display.getSize(size)
//            val height = size.y
//
//            val layoutParams = CoordinatorLayout.LayoutParams(
//                MATCH_PARENT,
//                height
//            )
//            layoutParams.setMargins(
//                0,
//                ViewUtil.convertDpToPixel(this@BehaviorExampleActivity5, 400f).toInt(),
//                0,
//                0
//            )

            val layoutParams = layoutParams as CoordinatorLayout.LayoutParams

            layoutParams.setMargins(
                0,
                ViewUtil.convertDpToPixel(this@BehaviorExampleActivity5, 400f).toInt(),
                0,
                0
            )

            setLayoutParams(layoutParams)
        }
    }
}
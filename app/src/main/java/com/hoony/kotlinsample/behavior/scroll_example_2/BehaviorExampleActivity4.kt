package com.hoony.kotlinsample.behavior.scroll_example_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_behavior_example_4.*

class BehaviorExampleActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_example_4)

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
            layoutManager =
                LinearLayoutManager(
                    this@BehaviorExampleActivity4,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            addItemDecoration(
                DividerItemDecoration(
                    this@BehaviorExampleActivity4,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = ListAdapter()
        }
    }
}
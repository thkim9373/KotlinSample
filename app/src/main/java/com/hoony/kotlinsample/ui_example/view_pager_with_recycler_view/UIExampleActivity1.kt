package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityUiViewPagerWithRecyclerViewBinding

/**
 *  Nested scroll view example : https://black-jin0427.tistory.com/164
 */
class UIExampleActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivityUiViewPagerWithRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_ui_view_pager_with_recycler_view)

        initViewPager()
        initRecyclerView()
    }

    fun viewPager() = binding.vpMain

    private fun initViewPager() {
        binding.vpMain.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = UIViewPagerAdapter()
        }
    }

    private fun initRecyclerView() {
        binding.rvList.apply {
            layoutManager =
                GridLayoutManager(this@UIExampleActivity1, 2, GridLayoutManager.VERTICAL, false)
            adapter = UIExample1ListAdapter()
        }
    }
}
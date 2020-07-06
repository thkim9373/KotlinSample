package com.hoony.kotlinsample.custom_view.view_pager_indicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_view_pager_indicator.*

class ViewPagerIndicatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_indicator)

        initViewPager()
    }

    private fun initViewPager() {
        viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = ViewPagerAdapter()
        }
    }
}
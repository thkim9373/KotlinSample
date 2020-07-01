package com.hoony.kotlinsample.custom_view.layout_contain_view_pager_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_view_pager_custom_view.*

class CustomVIewActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_custom_view)

        viewPagerCustomView.setAdapter(ViewPagerAdapter())
    }
}
package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ListItemViewPager(private val view: View) : RecyclerView.ViewHolder(view) {

    init {
        view.viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = ViewPagerAdapter()
            adapter?.notifyDataSetChanged()
        }
    }

    fun setAdapter(adapter: ViewPagerAdapter) {
        view.viewPager.adapter = adapter
    }
}
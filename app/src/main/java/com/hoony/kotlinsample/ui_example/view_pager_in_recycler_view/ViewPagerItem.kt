package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_single_text_full_size.view.*
import kotlin.random.Random

class ViewPagerItem(private val view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(title: String) {
        view.tvTitle.text = title
    }

    fun changeBackgroundColor() {
        val random = Random
        val color: Int =
            Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        view.clContainer.setBackgroundColor(color)
    }
}
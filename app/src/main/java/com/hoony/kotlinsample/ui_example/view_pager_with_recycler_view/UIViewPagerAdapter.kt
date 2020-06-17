package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R

class UIViewPagerAdapter : RecyclerView.Adapter<ViewPagerItem>() {

    private val titleList = arrayListOf<String>()

    init {
        for (i in 1..30) {
            titleList.add("Page $i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerItem {
        return ViewPagerItem(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_single_text_full_size,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: ViewPagerItem, position: Int) {
        holder.bind(titleList[position])
        holder.changeBackgroundColor()
    }
}
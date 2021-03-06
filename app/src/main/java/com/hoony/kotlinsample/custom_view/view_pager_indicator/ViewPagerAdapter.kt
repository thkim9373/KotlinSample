package com.hoony.kotlinsample.custom_view.view_pager_indicator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerItem>() {

    private val titleList = arrayListOf<String>()

    init {
        for (i in 1..30) {
            titleList.add("Page $i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerItem {
        return ViewPagerItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_single_text_gradient,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: ViewPagerItem, position: Int) {
        holder.setText(titleList[position])
        holder.changeBackgroundColor()
        holder.setToastPrinter()
        holder.setBottomGradient()
    }
}
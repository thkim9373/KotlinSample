package com.hoony.kotlinsample.custom_view.layout_contain_view_pager_example

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerItem>() {

    private val titleList = arrayListOf<String>()

    init {
        for (i in 1..30) {
            titleList.add("Page $i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerItem {

        val view = ViewPagerItemView(parent.context)

        val layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.MarginLayoutParams.MATCH_PARENT,
            ViewGroup.MarginLayoutParams.MATCH_PARENT
        )
        view.layoutParams = layoutParams
//        val view = LayoutInflater.from(parent.context).inflate(
//            R.layout.item_single_text_full_size,
//            parent,
//            false
//        )

        return ViewPagerItem(
            view
        )
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: ViewPagerItem, position: Int) {
        holder.bind(titleList[position])
        holder.changeBackgroundColor()
        holder.setToastPrinter()
    }
}
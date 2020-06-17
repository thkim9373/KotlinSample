package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextBinding

class UIExample1ListViewHolder(private val binding: ItemSingleTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.tvTitle.text = text
//        itemView.setOnTouchListener { v, event ->
//            (v.context as? UIActivity1)?.viewPager()?.dispatchTouchEvent(event)
//            false
//        }
    }
}
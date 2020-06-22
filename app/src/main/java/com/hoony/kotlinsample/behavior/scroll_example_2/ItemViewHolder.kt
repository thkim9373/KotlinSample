package com.hoony.kotlinsample.behavior.scroll_example_2

import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextBinding

class ItemViewHolder(private val binding: ItemSingleTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.tvTitle.text = text
    }
}
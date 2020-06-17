package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextFullSizeBinding
import kotlin.random.Random

class ViewPagerItem(private val binding: ItemSingleTextFullSizeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String) {
        binding.tvTitle.text = title
    }

    fun changeBackgroundColor() {
        val random = Random
        val color: Int =
            Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        binding.clContainer.setBackgroundColor(color)
    }
}
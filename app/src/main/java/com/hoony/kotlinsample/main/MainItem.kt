package com.hoony.kotlinsample.main

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemMainBinding

class MainItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding: ItemMainBinding? = null

    init {
        binding = DataBindingUtil.bind(itemView)
    }
}
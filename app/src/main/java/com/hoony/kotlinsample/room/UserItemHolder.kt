package com.hoony.kotlinsample.room

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextBinding

class UserItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding: ItemSingleTextBinding?

    init {
        itemView.let {
            binding = DataBindingUtil.bind(it)
        }
    }
}
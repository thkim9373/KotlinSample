package com.hoony.kotlinsample.room

import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextBinding
import com.hoony.kotlinsample.room.db.table.user.User

class UserItemHolder(private val binding: ItemSingleTextBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: User) {
        binding.apply {
            user = item
        }
    }
}
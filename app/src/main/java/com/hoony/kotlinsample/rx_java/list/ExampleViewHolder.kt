package com.hoony.kotlinsample.rx_java.list

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextBinding

class ExampleViewHolder(private val binding: ItemSingleTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setText(text: String) {
        binding.tvTitle.text = text
    }

    fun setOnClickListener(target: Class<*>) {
        binding.clContainer.setOnClickListener {
            val intent = Intent(binding.root.context, target)
            binding.root.context.startActivity(intent)
        }
    }
}
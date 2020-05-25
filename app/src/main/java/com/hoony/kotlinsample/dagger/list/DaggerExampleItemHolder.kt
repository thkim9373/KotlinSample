package com.hoony.kotlinsample.dagger.list

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextBinding

class DaggerExampleItemHolder(private val binding: ItemSingleTextBinding) :
    RecyclerView.ViewHolder(binding.root) {
    
    fun setText(text: String) {
        binding.tvTitle.text = text
    }

    fun setTargetActivity(target: Class<*>) {
        binding.clContainer.setOnClickListener {
            val intent = Intent(binding.root.context, target)
            binding.root.context.startActivity(intent)
        }
    }
}
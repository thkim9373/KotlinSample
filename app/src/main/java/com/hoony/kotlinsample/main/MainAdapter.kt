package com.hoony.kotlinsample.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R

class MainAdapter(
    private val targetList: Array<Class<*>>,
    private val titleArray: Array<String>
) :
    RecyclerView.Adapter<MainItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItem {
        return MainItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return titleArray.size
    }

    override fun onBindViewHolder(holder: MainItem, position: Int) {
        val context = holder.itemView.context
        val binding = holder.binding
        binding?.let {
            binding.tvTitle.text = titleArray[position]
            it.clContainer.setOnClickListener {
                run {
                    val intent = Intent(context, targetList[position])
                    context.startActivity(intent)
                }
            }
        }
    }

}
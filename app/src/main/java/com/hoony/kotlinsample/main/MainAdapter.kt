package com.hoony.kotlinsample.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.data.TargetData

class MainAdapter(
    private val targetDataList: List<TargetData>
) :
    RecyclerView.Adapter<MainItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItem {
        return MainItem(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return targetDataList.size
    }

    override fun onBindViewHolder(holder: MainItem, position: Int) {
        val context = holder.itemView.context
        val binding = holder.binding
        binding.let {
            binding.tvTitle.text = targetDataList[position].title
            it.clContainer.setOnClickListener {
                run {
                    val intent = Intent(context, targetDataList[position].targetClass)
                    context.startActivity(intent)
                }
            }
        }
    }

}
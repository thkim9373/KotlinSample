package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerVIewItemViewHolder>() {

    private val titleList = arrayListOf<String>()

    init {
        for (i in 1..100) {
            titleList.add("Item $i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVIewItemViewHolder {
        return RecyclerVIewItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: RecyclerVIewItemViewHolder, position: Int) {
        holder.bind(titleList[position])
    }
}
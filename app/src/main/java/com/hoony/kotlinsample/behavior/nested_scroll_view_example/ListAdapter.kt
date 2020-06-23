package com.hoony.kotlinsample.behavior.nested_scroll_view_example

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.data.TargetData

class ListAdapter : RecyclerView.Adapter<ListItemViewHolder>() {

    private val textList: MutableList<String> = arrayListOf()

    init {
        for(i in 1 .. 300) {
            textList.add("Item $i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return textList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        Log.d(
            "onBind",
            "onBindViewHolder $position"
        )
        holder.setText(textList[position])
    }
}
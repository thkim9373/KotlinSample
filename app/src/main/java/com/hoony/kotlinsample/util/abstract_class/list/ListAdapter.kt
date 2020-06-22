package com.hoony.kotlinsample.util.abstract_class.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.data.TargetData

class ListAdapter(
    private val targetDataList: List<TargetData>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return targetDataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setText(targetDataList[position].title)
        holder.setListener(onItemClickListener)
    }
}
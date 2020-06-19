package com.hoony.kotlinsample.ui_example.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.data.TargetData

class ListAdapter(private val itemList: List<TargetData>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ListItem>() {

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItem {
        return ListItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListItem, position: Int) {
        val title = itemList[position].title
        holder.setTitle(title)
        holder.setListener(listener = listener)
    }

}
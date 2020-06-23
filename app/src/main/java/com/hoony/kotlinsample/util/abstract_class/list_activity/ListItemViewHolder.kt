package com.hoony.kotlinsample.util.abstract_class.list_activity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_single_text.view.*

class ListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setText(text: String) {
        view.tvTitle.text = text
    }

    fun setListener(listener: ListAdapter.OnItemClickListener) {
        view.clContainer.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
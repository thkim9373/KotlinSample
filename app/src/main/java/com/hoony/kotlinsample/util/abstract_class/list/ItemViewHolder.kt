package com.hoony.kotlinsample.util.abstract_class.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_single_text.view.*

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setText(text: String) {
        view.tvTitle.text = text
    }

    fun setListener(onItemClickListener: ListAdapter.OnItemClickListener) {
        view.clContainer.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }
    }
}
package com.hoony.kotlinsample.ui_example.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_single_text.view.*

class ListItem(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setTitle(text: String) {
        view.tvTitle.text = text
    }

    fun setListener(listener: ListAdapter.OnItemClickListener) {
        view.clContainer.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onClick(position = adapterPosition)
            }
        }
    }
}
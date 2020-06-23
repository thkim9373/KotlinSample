package com.hoony.kotlinsample.behavior.nested_scroll_view_example

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_single_text.view.*

class ListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setText(text: String) {
        view.tvTitle.text = text
    }
}
package com.hoony.kotlinsample.ui_example.collapsing_toolbar_layout

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_single_text.view.*

class ListItemText(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setText(text: String) {
        view.tvTitle.text = text
    }
}
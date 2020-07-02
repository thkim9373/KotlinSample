package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.common.ToastPrinter
import kotlinx.android.synthetic.main.item_single_text.view.*

class ListItemText(private val view: View) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            if(adapterPosition != RecyclerView.NO_POSITION) {
                ToastPrinter.showToast(view.context, "Recycler view item clicked. position : $adapterPosition")
            }
        }
    }

    fun setText(text: String) {
        view.tvTitle.text = text
    }
}
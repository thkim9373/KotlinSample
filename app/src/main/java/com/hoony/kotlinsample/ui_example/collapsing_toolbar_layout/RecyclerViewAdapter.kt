package com.hoony.kotlinsample.ui_example.collapsing_toolbar_layout

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.item_view_pager.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<ListItemText>() {

    private val itemList: ArrayList<String> = arrayListOf()

    init {
        for (i in 1..300) {
            itemList.add("Item $i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemText {
        return ListItemText(
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

    override fun onBindViewHolder(holder: ListItemText, position: Int) {
        holder.setText(itemList[position])
        Log.d(
            "onBind",
            "onBindViewHolder $position"
        )
    }
}
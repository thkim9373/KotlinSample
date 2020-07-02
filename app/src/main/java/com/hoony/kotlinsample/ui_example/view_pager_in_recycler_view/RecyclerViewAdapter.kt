package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPagerAdapter = ViewPagerAdapter()

    companion object {
        const val ITEM_VIEW_PAGER = 1
        const val ITEM_DEFAULT = 2
    }

    private val itemList: ArrayList<String> = arrayListOf()

    init {
        for (i in 1..50) {
            itemList.add("Item $i")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ITEM_VIEW_PAGER
        } else {
            ITEM_DEFAULT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_PAGER -> ListItemText(
//                LayoutInflater.from(parent.context).inflate(
//                    R.layout.item_view_pager,
//                    parent,
//                    false
//                )
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_single_text_full_size,
                    parent,
                    false
                )
            )
            else -> ListItemText(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_single_text,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return itemList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("hoony", "onBindViewHolder position : $position")
        when (holder.itemViewType) {
            ITEM_VIEW_PAGER -> {
//                holder.itemView.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//                holder.itemView.viewPager.adapter = viewPagerAdapter
//                holder.itemView.translationY = 200f
            }
            else -> {
                (holder as ListItemText).setText(itemList[position - 1])

            }
        }
    }
}
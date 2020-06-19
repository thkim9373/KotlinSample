package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.item_view_pager.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPagerAdapter = ViewPagerAdapter()

    companion object {
        const val ITEM_VIEW_PAGER = 1
        const val ITEM_DEFAULT = 2
    }

    private val itemList: ArrayList<String> = arrayListOf()

    init {
        for (i in 1..20) {
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
            ITEM_VIEW_PAGER -> ListItemViewPager(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_view_pager,
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
        when (holder.itemViewType) {
            ITEM_VIEW_PAGER -> {
//                holder.itemView.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//                holder.itemView.viewPager.adapter = viewPagerAdapter
            }
            else -> {
                (holder as ListItemText).setText(itemList[position - 1])
            }
        }
    }
}
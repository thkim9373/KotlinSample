package com.hoony.kotlinsample.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.data.MemoData
import kotlinx.android.synthetic.main.item_memo.view.*
import java.text.SimpleDateFormat
import java.util.*

class MemoListAdapter(private var list: MutableList<MemoData>) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private val dateFormat = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault())

    lateinit var itemClickListener: (itemId: String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        view.setOnClickListener {
            itemClickListener.run {
                val memoId = it.tag as String
                this(memoId)
            }
        }
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (list[position].title.isNotEmpty()) {
            holder.containerView.titleView.visibility = View.VISIBLE
            holder.containerView.titleView.text = list[position].title
        } else {
            holder.containerView.titleView.visibility = View.GONE
        }
        holder.containerView.summaryView.text = list[position].summary
        holder.containerView.dateView.text = dateFormat.format(list[position].createAt)
        holder.containerView.tag = list[position].id
    }
}
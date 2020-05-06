package com.hoony.kotlinsample.content_provider.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R

class Adapter(private val targetClasses: Array<Class<*>>, private val titleArray: Array<String>) :
    RecyclerView.Adapter<ListItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItem {
        return ListItem(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return titleArray.size
        return titleArray.size
    }

    override fun onBindViewHolder(holder: ListItem, position: Int) {
        holder.binding.let {
            it.tvTitle.text = titleArray[position]
            it.clContainer.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, targetClasses[position])
                context.startActivity(intent)
            }
        }
    }
}
package com.hoony.kotlinsample.content_provider.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.content_provider.data.Contact

class ContactAdapter(private val contactList: List<Contact>) :
    RecyclerView.Adapter<ItemContactHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemContactHolder {
        return ItemContactHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_contact,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ItemContactHolder, position: Int) {
        holder.bind(contactList[position])
    }
}
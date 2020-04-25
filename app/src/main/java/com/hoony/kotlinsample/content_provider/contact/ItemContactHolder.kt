package com.hoony.kotlinsample.content_provider.contact

import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.content_provider.data.Contact
import com.hoony.kotlinsample.databinding.ItemContactBinding

class ItemContactHolder(private val binding: ItemContactBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(contact: Contact) {
        binding.apply {
            this.contact = contact
        }
    }
}
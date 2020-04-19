package com.hoony.kotlinsample.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.room.db.table.user.User

class RoomAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemHolder {
        return UserItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserItemHolder, position: Int) {
        val user = this.userList[position]

        holder.binding?.tvTitle?.text = user.name
    }
}
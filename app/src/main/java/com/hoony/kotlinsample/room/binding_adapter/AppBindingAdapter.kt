package com.hoony.kotlinsample.room.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.room.RoomActivity
import com.hoony.kotlinsample.room.RoomAdapter
import com.hoony.kotlinsample.room.db.table.user.User

open class AppBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setList")
        fun bindUserList(recyclerView: RecyclerView, userListLiveData: LiveData<List<User>>) {
            userListLiveData.observe(
                recyclerView.context as RoomActivity,
                Observer {
                    it?.let {
                        recyclerView.adapter = RoomAdapter(it)
                    }
                }
            )
        }
    }
}
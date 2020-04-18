package com.hoony.kotlinsample.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding
    private var viewModel: RoomViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room)
        viewModel = application?.let {
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(it)).get(
                RoomViewModel::class.java
            )
        }


    }
}
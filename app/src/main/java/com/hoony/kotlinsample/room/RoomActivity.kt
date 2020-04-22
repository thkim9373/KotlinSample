package com.hoony.kotlinsample.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.common.ToastPrinter
import com.hoony.kotlinsample.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding
    private lateinit var viewModel: RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_room)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(RoomViewModel::class.java)
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this

        setView()
        setObserver()
    }

    private fun setView() {
        binding.rvUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUser.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setObserver() {
//        viewModel.userListLiveData.observe(
//            this,
//            Observer {
//                binding.rvUser.adapter = RoomAdapter(it)
//            }
//        )
        viewModel.toastMsgLiveData.observe(
            this,
            Observer {
                ToastPrinter.showToast(this, it)
            }
        )
    }
}
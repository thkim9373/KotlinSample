package com.hoony.kotlinsample.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.content_provider.list.ListActivity
import com.hoony.kotlinsample.custom_view.CustomViewActivity
import com.hoony.kotlinsample.databinding.ActivityMainBinding
import com.hoony.kotlinsample.memo.intro.IntroActivity
import com.hoony.kotlinsample.room.RoomActivity
import com.hoony.kotlinsample.saved_state_view_model.SavedStateViewModelActivity

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.let {
            it.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            it.rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

            it.rvList.adapter = MainAdapter(
                arrayOf(
                    IntroActivity::class.java,
                    RoomActivity::class.java,
                    ListActivity::class.java,
                    CustomViewActivity::class.java,
                    SavedStateViewModelActivity::class.java
                ),
                resources.getStringArray(R.array.main_item_array)
            )
        }
    }
}
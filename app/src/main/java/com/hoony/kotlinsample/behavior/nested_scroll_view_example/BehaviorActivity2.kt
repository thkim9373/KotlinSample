package com.hoony.kotlinsample.behavior.nested_scroll_view_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_behavior_2.*

class BehaviorActivity2 :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_2)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager = LinearLayoutManager(this@BehaviorActivity2, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@BehaviorActivity2, DividerItemDecoration.VERTICAL))
            adapter = ListAdapter()
        }
    }
}
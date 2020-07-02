package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_ui_example_2.*

class UIExample2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_example_2)

        initRecyclerView()
    }

    private fun initRecyclerView() {
//        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        rvList.layoutManager = LinearLayoutManager(this)

        rvList.layoutManager = CustomLayoutManager(this)

        rvList.adapter = RecyclerViewAdapter()

        rvList.setItemViewCacheSize(20)
    }
}

class CustomLayoutManager(context: Context) : LinearLayoutManager(context, VERTICAL, false) {

    override fun getExtraLayoutSpace(state: RecyclerView.State?) = 1500
}
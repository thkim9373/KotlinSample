package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
        rvList.addItemDecoration(ItemDecoration(this))
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = RecyclerViewAdapter()
    }
}
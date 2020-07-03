package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
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

//        val layoutManager = object : LinearLayoutManager(this) {
//            override fun calculateExtraLayoutSpace(
//                state: RecyclerView.State,
//                extraLayoutSpace: IntArray
//            ) {
//                extraLayoutSpace[0] = 1500
//                extraLayoutSpace[1] = 1500
//            }
//        }
//        rvList.layoutManager = layoutManager

        val layoutManager = object : GridLayoutManager(this, 1) {
            override fun calculateExtraLayoutSpace(
                state: RecyclerView.State,
                extraLayoutSpace: IntArray
            ) {
                extraLayoutSpace[0] = 1500
                extraLayoutSpace[1] = 1500
            }
        }
        rvList.layoutManager = layoutManager

//        rvList.layoutManager = CustomLayoutManager(this)

        rvList.adapter = RecyclerViewAdapter()
    }
}

class CustomLayoutManager(context: Context) : LinearLayoutManager(context, VERTICAL, false) {

    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        extraLayoutSpace[0] = 1500
        extraLayoutSpace[1] = 1500
    }
}
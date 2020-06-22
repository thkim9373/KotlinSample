package com.hoony.kotlinsample.behavior.scroll_example_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_behavior_example_3.*

class BehaviorExampleActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_example_3)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager =
                LinearLayoutManager(
                    this@BehaviorExampleActivity3,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            addItemDecoration(
                DividerItemDecoration(
                    this@BehaviorExampleActivity3,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = ListAdapter()
        }
    }
}
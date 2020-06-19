package com.hoony.kotlinsample.sticky_item_decoration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_sticky_item_decoration.*

class StickyItemDecorationActivity : AppCompatActivity() {

    private val wordsList: List<String> = listOf(
        "account", "alpha", "beta", "best", "bet", "country", "create", "delay"
    )

    private val alphabetMap: Map<Int, String> = mapOf(
        0 to "A", 2 to "B", 5 to "C", 7 to "D"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky_item_decoration)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager = LinearLayoutManager(this@StickyItemDecorationActivity, LinearLayoutManager.VERTICAL, false)

            adapter = ListAdapter(wordsList, alphabetMap)
        }
    }
}
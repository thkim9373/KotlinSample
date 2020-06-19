package com.hoony.kotlinsample.ui_example.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view.UIExample2Activity
import com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.UIExampleActivity1
import kotlinx.android.synthetic.main.activity_ui_example.*

class UiListActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {

    private val targetDataList = listOf(
        TargetData(UIExampleActivity1::class.java, "Background view pager with recycler view"),
        TargetData(UIExample2Activity::class.java, "View pager in recycler view")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_example)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager = LinearLayoutManager(this@UiListActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@UiListActivity, DividerItemDecoration.VERTICAL))
            adapter = ListAdapter(targetDataList, this@UiListActivity)
        }
    }

    override fun onClick(position: Int) {
        val intent = Intent(this, targetDataList[position].targetClass)
        startActivity(intent)
    }
}
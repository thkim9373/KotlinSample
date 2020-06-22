package com.hoony.kotlinsample.util.abstract_class.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.data.TargetData
import kotlinx.android.synthetic.main.activity_single_list.*

abstract class AbsListActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {

    abstract fun getTargetDataList(): List<TargetData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_list)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager =
                LinearLayoutManager(this@AbsListActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    this@AbsListActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = ListAdapter(getTargetDataList(), this@AbsListActivity)
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, getTargetDataList()[position].targetClass)
        startActivity(intent)
    }
}
package com.hoony.kotlinsample.rx_java.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityRxJavaExampleListBinding

class RxJavaExampleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRxJavaExampleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_example_list)

        binding.rvList.let {
            it.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            it.adapter = RxJavaExamplesAdapter()
        }
    }
}
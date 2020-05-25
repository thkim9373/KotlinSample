package com.hoony.kotlinsample.dagger.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityDaggerExampleListBinding

class DaggerExampleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaggerExampleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dagger_example_list)
        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvList.adapter = DaggerExampleListAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvList.adapter = null
    }
}
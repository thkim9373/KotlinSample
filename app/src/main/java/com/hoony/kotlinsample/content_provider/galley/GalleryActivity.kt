package com.hoony.kotlinsample.content_provider.galley

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_single_list.*

class GalleryActivity : AppCompatActivity() {

    private val viewModel by viewModels<GalleryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_list)

        initRecyclerView()
        setObserver()
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager = GridLayoutManager(this@GalleryActivity, 3)
            adapter = GalleryListAdapter()
        }
    }

    private fun setObserver() {
        viewModel.imageListLiveData.observe(
            this,
            Observer {
                (rvList.adapter as GalleryListAdapter).submitList(it)
            }
        )
    }
}
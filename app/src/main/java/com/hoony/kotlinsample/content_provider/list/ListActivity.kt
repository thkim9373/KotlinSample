package com.hoony.kotlinsample.content_provider.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.content_provider.audio.AudioActivity
import com.hoony.kotlinsample.content_provider.contact.ContactActivity
import com.hoony.kotlinsample.content_provider.video.VideoActivity
import com.hoony.kotlinsample.databinding.ActivityContentProviderListBinding

class ListActivity : AppCompatActivity() {

    lateinit var binding: ActivityContentProviderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_provider_list)

        binding.rvList.let {
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            it.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

            val titleArray = resources.getStringArray(R.array.content_provider_example_array)
            it.adapter = Adapter(
                arrayOf(
                    ContactActivity::class.java,
                    AudioActivity::class.java,
                    VideoActivity::class.java
                ),
                titleArray
            )
        }
    }
}
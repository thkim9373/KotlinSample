package com.hoony.kotlinsample.content_provider.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class VideoActivity : AppCompatActivity() {

    private lateinit var viewModel: VideoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                VideoViewModel::class.java
            )
    }
}

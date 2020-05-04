package com.hoony.kotlinsample.content_provider.video.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer

class PlayerFragment : Fragment() {
    private lateinit var mPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
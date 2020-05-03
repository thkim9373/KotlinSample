package com.hoony.kotlinsample.content_provider.video.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.hoony.kotlinsample.R

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var mPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPlayer = ExoPlayerFactory.newSimpleInstance(this)

        val dataSourceFactory =
            DefaultDataSourceFactory(this, Util.getUserAgent(this, getString(R.string.app_name)))

        val videoSource =
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource()

        mPlayer.prepare(videoSource)
        mPlayer.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release()
    }
}
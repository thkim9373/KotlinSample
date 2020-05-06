package com.hoony.kotlinsample.content_provider.video.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.content_provider.video.VideoViewModel
import com.hoony.kotlinsample.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {
    private lateinit var mPlayer: ExoPlayer
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var viewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_player,
            container,
            false
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(VideoViewModel::class.java)

        mPlayer = ExoPlayerFactory.newSimpleInstance(requireContext())

        setObserver()
    }

    private fun setObserver() {
        viewModel.videoLiveData.observe(
            viewLifecycleOwner,
            Observer {
                val dataSourceFactory =
                    DefaultDataSourceFactory(
                        requireContext(),
                        Util.getUserAgent(requireContext(), getString(R.string.app_name))
                    )

                val videoSource =
                    ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(it.uri)

                mPlayer.prepare(videoSource)
                mPlayer.playWhenReady = true
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release()
    }
}
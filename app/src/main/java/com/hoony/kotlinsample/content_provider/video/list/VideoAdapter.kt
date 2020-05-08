package com.hoony.kotlinsample.content_provider.video.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.content_provider.data.Video

class VideoAdapter(
    private val list: List<Video>,
    private val callback: VideoItemHolder.OnVideoItemClickListener
) : RecyclerView.Adapter<VideoItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemHolder {
        return VideoItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_video,
                parent,
                false
            ),
            callback
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideoItemHolder, position: Int) {
        val video = list[position]
        holder.bind(video)
    }
}
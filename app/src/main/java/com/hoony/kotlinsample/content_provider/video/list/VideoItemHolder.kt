package com.hoony.kotlinsample.content_provider.video.list

import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.content_provider.data.Video
import com.hoony.kotlinsample.databinding.ItemVideoBinding

class VideoItemHolder(
    private val binding: ItemVideoBinding,
    private val callback: OnVideoItemClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(video: Video) {
        binding.video = video

        if (adapterPosition != RecyclerView.NO_POSITION) {
            binding.clContainer.setOnClickListener {
                callback.onVideoItemClick(adapterPosition)
            }
        }
    }

    interface OnVideoItemClickListener {
        fun onVideoItemClick(position: Int)
    }
}
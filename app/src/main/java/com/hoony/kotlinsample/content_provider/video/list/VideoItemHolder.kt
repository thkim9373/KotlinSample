package com.hoony.kotlinsample.content_provider.video.list

import android.text.format.DateFormat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hoony.kotlinsample.content_provider.data.Video
import com.hoony.kotlinsample.databinding.ItemVideoBinding
import java.text.SimpleDateFormat
import java.util.*

class VideoItemHolder(
    private val binding: ItemVideoBinding,
    private val callback: OnVideoItemClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(video: Video) {
        binding.video = video

        val formatString = DateFormat.getBestDateTimePattern(
            Locale.getDefault(),
            "yyyy.MM.dd EEE HH:mm"
        )
        binding.tvDate.text =
            SimpleDateFormat(
                formatString,
                Locale.getDefault()
            )
                .format(video.dateAdded)

        Glide.with(itemView)
            .load(video.uri)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.ivThumbnail)

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
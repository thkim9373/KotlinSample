package com.hoony.kotlinsample.content_provider.galley

import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryListAdapter :
    ListAdapter<ImageData, GalleryViewHolder>(object : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData):
                Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData):
                Boolean = oldItem.id == newItem.id
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_gallery,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.showImage(getItem(position).id)
    }
}

class GalleryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun showImage(id: Long) {
        val contentUri = Uri.withAppendedPath(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            id.toString()
        )

        Glide.with(view.context)
            .load(contentUri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .thumbnail(0.3f)
            .into(view.ivImage)
    }
}
package com.hoony.kotlinsample.content_provider.data

import android.net.Uri

data class Album(
    val uri: Uri?,
    val artist: String,
    val numOfSong: Int
) : Comparable<Album> {
    override fun compareTo(other: Album): Int {
        return this.artist.compareTo(other.artist)
    }
}
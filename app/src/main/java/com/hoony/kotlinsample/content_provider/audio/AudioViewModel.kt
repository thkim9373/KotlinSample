package com.hoony.kotlinsample.content_provider.audio

import android.app.Application
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hoony.kotlinsample.content_provider.data.Album

class AudioViewModel(application: Application) : AndroidViewModel(application) {
    private val audioInfoCursor: Cursor? by lazy {
        application.contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null,
            null
        )
    }

    val albumListLiveData: LiveData<List<Album>> = liveData {
        val albumList = ArrayList<Album>()

        audioInfoCursor?.let {
            val idIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART)
            val artistIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST)
            val numOfSongIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Albums.NUMBER_OF_SONGS)

            while (it.moveToNext()) {
                albumList.add(
                    Album(
                        Uri.parse(it.getString(idIndex)),
                        it.getString(artistIndex),
                        it.getInt(numOfSongIndex)
                    )
                )
            }
        }

        this.emit(albumList as List<Album>)
    }
}
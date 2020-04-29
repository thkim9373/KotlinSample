package com.hoony.kotlinsample.content_provider.audio

import android.app.Application
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hoony.kotlinsample.content_provider.data.Album
import java.io.File

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
            val albumArtIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART)
            val artistIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST)
            val numOfSongIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Albums.NUMBER_OF_SONGS)

            while (it.moveToNext()) {
                albumList.add(
                    Album(
                        if (it.getString(albumArtIndex) != null) {
                            Uri.fromFile(File(it.getString(albumArtIndex)))
                        } else {
                            null
                        },
                        it.getString(artistIndex),
                        it.getInt(numOfSongIndex)
                    )
                )
            }
        }

        this.emit(albumList as List<Album>)
    }
}

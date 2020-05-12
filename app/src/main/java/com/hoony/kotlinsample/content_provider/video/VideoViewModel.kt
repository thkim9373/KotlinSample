package com.hoony.kotlinsample.content_provider.video

import android.app.Application
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.hoony.kotlinsample.content_provider.data.Video
import java.util.*
import kotlin.collections.ArrayList

class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private val projection = arrayOf(
        MediaStore.Video.Media._ID,
        MediaStore.Video.Media.DISPLAY_NAME,
        MediaStore.Video.Media.DATE_ADDED,
        MediaStore.Video.Media.SIZE
    )

    private val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"

    private val cursor: Cursor? by lazy {
        application.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )
    }

    val videoListLiveData: LiveData<List<Video>> = liveData {
        val videoList = ArrayList<Video>()

        cursor?.let {
            val idIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val displayNameIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val dateAddedIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val sizeIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

            while (it.moveToNext()) {
                val id = it.getLong(idIndex)
                val displayName = it.getString(displayNameIndex)
                val dateAdded = Date(it.getLong(dateAddedIndex) * 1000)
                val size = it.getLong(sizeIndex)

                val contentUri = Uri.withAppendedPath(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )
                videoList.add(Video(contentUri, displayName, dateAdded, size))
            }
        }

        emit(videoList as List<Video>)
    }

    private val _videoMutableLiveData = MutableLiveData<Video>()
    val videoLiveData: LiveData<Video>
        get() = _videoMutableLiveData

    fun setSelectedData(position: Int) {
        this.videoListLiveData.value?.let {
            this._videoMutableLiveData.postValue(it[position])
        }
    }

    fun removeSelectedData() {
        this._videoMutableLiveData.postValue(null)
    }
}
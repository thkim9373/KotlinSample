package com.hoony.kotlinsample.content_provider.video

import android.app.Application
import android.database.Cursor
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel

class VideoViewModel(application: Application) : AndroidViewModel(application) {
    private val cursor: Cursor? by lazy {
        application.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )
    }
}
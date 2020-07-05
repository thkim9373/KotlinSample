package com.hoony.kotlinsample.content_provider.galley

import android.app.Application
import android.database.Cursor
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val cursor: Cursor? by lazy {
        application.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )
    }

    private val projection = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATE_ADDED
    )

    private val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

    val imageListLiveData: LiveData<List<ImageData>> = liveData {
        val imageList = arrayListOf<ImageData>()

        cursor?.let {
            val idIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val displayNameIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateAddedIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)

            while (it.moveToNext()) {
                imageList.add(
                    ImageData(
                        it.getLong(idIndex),
                        it.getString(displayNameIndex),
                        it.getLong(dateAddedIndex)
                    )
                )
            }
        }

        emit(imageList as List<ImageData>)
    }
}
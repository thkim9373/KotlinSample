package com.hoony.kotlinsample.content_provider.data

import android.net.Uri
import java.util.*

data class Video(
    val uri: Uri,
    val name: String,
    val dateAdded: Date,
    val size: Long
)
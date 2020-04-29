package com.hoony.kotlinsample.content_provider.data

import android.net.Uri

data class Video(
    val uri: Uri,
    val name: String,
    val duration: Int,
    val size: Int
)
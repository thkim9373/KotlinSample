package com.hoony.kotlinsample.data

import androidx.appcompat.app.AppCompatActivity

data class TargetData(
    val targetClass: Class<out AppCompatActivity>,
    val title: String
)
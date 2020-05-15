package com.hoony.kotlinsample.custom_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_view)
    }
}
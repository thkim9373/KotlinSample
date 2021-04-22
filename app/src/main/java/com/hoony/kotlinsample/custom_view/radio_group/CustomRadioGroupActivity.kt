package com.hoony.kotlinsample.custom_view.radio_group

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.databinding.ActivityCustomRadioGroupBinding

class CustomRadioGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCustomRadioGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
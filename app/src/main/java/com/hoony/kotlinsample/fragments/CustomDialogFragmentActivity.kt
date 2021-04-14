package com.hoony.kotlinsample.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.databinding.ActivityCustomDialogFragmentBinding

class CustomDialogFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCustomDialogFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            this.showButton.setOnClickListener {
                CustomDialogFragment("title", listOf("content"), "positive", listOf("negative")).showNow(
                    supportFragmentManager,
                    "fragments!"
                )
            }
        }
    }
}
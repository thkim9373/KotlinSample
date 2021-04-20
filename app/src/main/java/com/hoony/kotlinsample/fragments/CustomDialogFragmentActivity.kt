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
                CustomDialogFragment.newInstance(
                    CustomDialogFragment.Type.Common(
                        "title",
                        arrayListOf("content"),
                        "dd",
                        arrayListOf("ss")
                    )
                ).showNow(
                    supportFragmentManager,
                    "fragments!"
                )
            }
        }
    }
}
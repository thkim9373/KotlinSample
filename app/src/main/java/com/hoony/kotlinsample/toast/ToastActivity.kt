package com.hoony.kotlinsample.toast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.databinding.ActivityToastBinding

class ToastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        binding.apply {
            showToast.setOnClickListener {
                SimpleCustomToast.show(this@ToastActivity, "Favorite")
            }
            showErrorToast.setOnClickListener {
                ErrorToast.show(this@ToastActivity, "Error!!!")
            }
        }
    }
}
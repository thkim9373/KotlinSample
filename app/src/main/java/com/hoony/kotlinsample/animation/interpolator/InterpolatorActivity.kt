package com.hoony.kotlinsample.animation.interpolator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.databinding.ActivityInterpolatorBinding

class InterpolatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInterpolatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterpolatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
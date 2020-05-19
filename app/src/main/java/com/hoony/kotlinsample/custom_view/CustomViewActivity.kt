package com.hoony.kotlinsample.custom_view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_view)

        binding.let {
            it.happyButton.setOnClickListener(this)
            it.sadButton.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when (view.id) {
                // Set the emotionalFaceView's happinessState to HAPPY when the user clicks on the happy button.
                R.id.happy_button -> {
                    binding.emotionFaceView.happinessState = EmotionalFaceView.HAPPY
                }
                // Set the emotionalFaceView's happiness to SAD when the user clicks on the sad button.
                R.id.sad_button -> {
                    binding.emotionFaceView.happinessState = EmotionalFaceView.SAD
                }
            }
        }
    }
}
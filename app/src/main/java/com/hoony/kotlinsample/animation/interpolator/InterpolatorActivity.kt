package com.hoony.kotlinsample.animation.interpolator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.databinding.ActivityInterpolatorBinding

class InterpolatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInterpolatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterpolatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        binding.apply {
            startFavoriteAnimation.setOnClickListener {
                startAnimation()
            }
        }
    }

    private fun startAnimation() {

        val animationSet = AnimatorSet().apply {
            duration = 10000
            interpolator = CustomInterpolator()
        }

        val objectAnimation1 = ObjectAnimator.ofFloat(
            binding.favorite,
            "scaleX",
            0f,
            1f
        )
        val objectAnimation2 = ObjectAnimator.ofFloat(
            binding.favorite,
            "scaleY",
            0f,
            1f
        )

        animationSet.play(objectAnimation1).with(objectAnimation2)

        animationSet.start()
    }
}
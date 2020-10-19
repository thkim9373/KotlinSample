package com.hoony.kotlinsample.toast

import android.content.Context
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ToastErrorBinding

object ErrorToast {

    fun show(context: Context, text: String) {
        val toast = Toast(context)

        val binding: ToastErrorBinding = ToastErrorBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            this.text.text = text
        }

        val animation = AnimationUtils.loadAnimation(context, R.anim.shake)

        toast.view = binding.root

        toast.show()
        binding.text.startAnimation(animation)
    }
}
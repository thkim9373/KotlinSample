package com.hoony.kotlinsample.toast

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.hoony.kotlinsample.databinding.ToastSimpleBinding

object SimpleCustomToast {

    fun show(context: Context, text: String) {
        val toast = Toast(context)

        val binding: ToastSimpleBinding = ToastSimpleBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            this.text.text = text
        }

        toast.view = binding.root

        toast.show()
    }
}
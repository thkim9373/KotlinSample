package com.hoony.kotlinsample.custom_view.radio_group

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.common.ToastPrinter
import com.hoony.kotlinsample.databinding.ActivityCustomRadioGroupBinding

class CustomRadioGroupActivity : AppCompatActivity(), CustomRadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityCustomRadioGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomRadioGroupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.radioGroup.listener = this
    }

    override fun onChecked(id: Int) {
        when (id) {
            binding.button1.id -> ToastPrinter.showToast(this, "Button 1 checked changed!")
            binding.button2.id -> ToastPrinter.showToast(this, "Button 2 checked changed!")
            binding.button3.id -> ToastPrinter.showToast(this, "Button 3 checked changed!")
        }
    }
}
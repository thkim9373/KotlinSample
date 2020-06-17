package com.hoony.kotlinsample.ui_example.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityUiExampleBinding

class UiListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUiExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ui_example)


    }
}
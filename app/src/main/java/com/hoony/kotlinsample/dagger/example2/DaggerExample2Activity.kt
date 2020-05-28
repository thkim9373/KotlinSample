package com.hoony.kotlinsample.dagger.example2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityDaggerExample2Binding

class DaggerExample2Activity : AppCompatActivity() {

    private lateinit var binding : ActivityDaggerExample2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dagger_example_2)
    }
}
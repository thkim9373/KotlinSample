package com.hoony.kotlinsample.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            parent,
            R.layout.activity_main
        )

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel.class)
    }


}

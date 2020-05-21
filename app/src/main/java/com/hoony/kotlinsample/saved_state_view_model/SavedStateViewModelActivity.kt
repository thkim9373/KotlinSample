package com.hoony.kotlinsample.saved_state_view_model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivitySavedStateViewModelBinding

class SavedStateViewModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedStateViewModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_saved_state_view_model)

        savedInstanceState?.let {
            val count = it.getInt("count")
            binding.tvCount.text = count.toString()
        }

        binding.fabAdd.setOnClickListener {
            var count = Integer.parseInt(binding.tvCount.text.toString())
            binding.tvCount.text = (++count).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("count", Integer.parseInt(binding.tvCount.text.toString()))
        super.onSaveInstanceState(outState)
    }
}
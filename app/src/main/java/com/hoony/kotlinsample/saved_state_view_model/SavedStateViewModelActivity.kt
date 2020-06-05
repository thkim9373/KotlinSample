package com.hoony.kotlinsample.saved_state_view_model

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivitySavedStateViewModelBinding

// This example from : http://pluu.github.io/blog/android/2020/02/10/saved-state/
class SavedStateViewModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedStateViewModelBinding
    private val viewModel: SavedStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_saved_state_view_model)
//        viewModel = ViewModelProvider(
//            this,
//            SavedStateViewModelFactory(application, this)
//        ).get(SavedStateViewModel::class.java)

//        savedInstanceState?.let {
//            val count = it.getInt("count")
//            binding.tvCount.text = count.toString()
//        }

        setObserver()
        setListener()
    }

    private fun setObserver() {
        viewModel.countState.observe(
            this,
            Observer {
                binding.tvCount.text = it.toString()
            }
        )
    }

    private fun setListener() {
//        binding.fabAdd.setOnClickListener {
//            var count = Integer.parseInt(binding.tvCount.text.toString())
//            binding.tvCount.text = (++count).toString()
//        }
        binding.fabAdd.setOnClickListener {
            viewModel.incCounter()
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt("count", Integer.parseInt(binding.tvCount.text.toString()))
//        super.onSaveInstanceState(outState)
//    }
}
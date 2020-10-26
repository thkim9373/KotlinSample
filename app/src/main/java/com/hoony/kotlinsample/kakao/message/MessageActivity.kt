package com.hoony.kotlinsample.kakao.message

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.databinding.ActivityKokoaMessageBinding

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKokoaMessageBinding
    private val viewModel by viewModels<MessageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKokoaMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
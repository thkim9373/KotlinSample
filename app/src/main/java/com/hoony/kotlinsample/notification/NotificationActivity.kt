package com.hoony.kotlinsample.notification

import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityNotificationBinding
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)

        setListener()
    }

    private fun setListener() {
        binding.create.setOnClickListener {
            NotificationHelper.sendNotification(
                this,
                NotificationManager.IMPORTANCE_DEFAULT,
                false,
                titleEdit.text.toString(),
                textEdit.text.toString()
            )
        }
        binding.styleGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.defaultStyle -> {

                }
                R.id.bigTextStyle -> {

                }
                R.id.bigPictureStyle -> {

                }
                R.id.messagingStyle -> {

                }
                R.id.mediaStyle -> {

                }
                R.id.inboxStyle -> {

                }
            }
        }
    }
}
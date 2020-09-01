package com.hoony.kotlinsample.notification

import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding

    private var nowFragmentName: String = ""
    private val bigTextFragment: BigTextFragment = BigTextFragment()
    private val bigPictureFragment: BigPictureFragment = BigPictureFragment()

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
                getNotificationStyle(binding.styleGroup.checkedRadioButtonId),
                getNotificationTitle(),
                getNotificationText()
            )
        }
        binding.styleGroup.setOnCheckedChangeListener { _, checkedId ->
            val fragment = getFragment(checkedId)
            if (fragment != null) {
                nowFragmentName = fragment.toString()
                supportFragmentManager.beginTransaction()
                    .replace(binding.customItemLayout.id, fragment, nowFragmentName).commit()
            } else {
                val nowFragment = supportFragmentManager.findFragmentByTag(nowFragmentName)
                if (nowFragment != null && nowFragment.isVisible) {
                    supportFragmentManager.beginTransaction().remove(nowFragment).commit()
                }
            }
        }
    }

    private fun getNotificationTitle(): String =
        if (binding.titleEdit.text.isNotEmpty()) {
            binding.titleEdit.text.toString()
        } else {
            "Title"
        }

    private fun getNotificationText(): String =
        if (binding.textEdit.text.isNotEmpty()) {
            binding.textEdit.text.toString()
        } else {
            "Text"
        }

    private fun getFragment(checkedId: Int): Fragment? =
        when (checkedId) {
            R.id.defaultStyle -> null
            R.id.bigTextStyle -> bigTextFragment
            R.id.bigPictureStyle -> bigPictureFragment
            R.id.messagingStyle -> null
            R.id.mediaStyle -> null
            R.id.inboxStyle -> null
            else -> null
        }

    private fun getNotificationStyle(checkedId: Int): NotificationCompat.Style? =
        when (checkedId) {
            R.id.defaultStyle -> null
            R.id.bigTextStyle -> {
                NotificationCompat.BigTextStyle()
                    .bigText(bigTextFragment.getBigText())
            }
            R.id.bigPictureStyle -> {
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bigPictureFragment.getImageBitmap())
                    .bigLargeIcon(bigPictureFragment.getImageBitmap())
            }
            R.id.messagingStyle -> {
                NotificationCompat.MessagingStyle("User")
            }
            R.id.mediaStyle -> androidx.media.app.NotificationCompat.MediaStyle()
            R.id.inboxStyle -> NotificationCompat.InboxStyle()
            else -> null
        }
}
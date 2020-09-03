package com.hoony.kotlinsample.notification

import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
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
            R.id.inboxStyle -> null
            R.id.messagingStyle -> null
            R.id.mediaStyle -> null
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
            R.id.inboxStyle -> {
                NotificationCompat.InboxStyle()
                    .addLine("Mail1 ...")
                    .addLine("Mail2 ...")
                    .addLine("Mail3 ...")
                    .setBigContentTitle("Big content title")
                    .setSummaryText("Summary text")
            }
            R.id.messagingStyle -> {
//                https://picsum.photos/id/237/200/300
                val userIcon1 = IconCompat.createWithResource(this, R.drawable.ic_location)
                val userIcon2 = IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)
                val userIcon3 = IconCompat.createWithResource(this, R.drawable.ic_alarm)
                val userName1 = "휴"
                val userName2 = "엘라인"
                val userName3 = "조시"
                val timeStamp = System.currentTimeMillis()
                val user1 = Person.Builder().setIcon(userIcon1).setName(userName1).build()
                val user2 = Person.Builder().setIcon(userIcon2).setName(userName2).build()
                val user3 = Person.Builder().setIcon(userIcon3).setName(userName3).build()

                NotificationCompat.MessagingStyle(user3)
                    .addMessage("조시야 국이 짜다", timeStamp, user1)
                    .addMessage("조시야 물도 짜다", timeStamp, user2)
            }
            R.id.mediaStyle -> androidx.media.app.NotificationCompat.MediaStyle()
            else -> null
        }
}
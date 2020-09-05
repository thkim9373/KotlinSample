package com.hoony.kotlinsample.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.hoony.kotlinsample.R

class NotificationHelper {
    enum class NotificationInfo(
        val id: String,
        val channelName: String,
        val description: String,
        val importance: Int
    ) {
        DEFAULT(
            "DEFAULT_NOTIFICATION_ID",
            "Default notification",
            "Description",
            NotificationManager.IMPORTANCE_DEFAULT
        ),
        HIGH(
            "HIGH_NOTIFICATION_ID",
            "High notification",
            "Description",
            NotificationManager.IMPORTANCE_HIGH
        ),
    }

    companion object {

        fun sendNotification(context: Context, title: String, message: String) {
            this.sendNotification(
                context,
                NotificationManager.IMPORTANCE_DEFAULT,
                false,
                null,
                title,
                message
            )
        }

        fun sendNotification(
            context: Context,
            importance: Int,
            showBadge: Boolean,
            style: NotificationCompat.Style?,
            title: String,
            message: String
        ) {

            createNotificationChannel(
                context,
                showBadge,
                getNotificationInfo(importance)
            )

            val notification =
                buildNotification(context, getNotificationInfo(importance), style, title, message)

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(0, notification)
        }

        private fun createNotificationChannel(
            notificationManager: NotificationManager,
            notificationInfo: NotificationInfo
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val notificationChannel = NotificationChannel(
                    notificationInfo.id,
                    notificationInfo.channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = notificationInfo.description
                    enableLights(true)
                    enableVibration(true)
                    vibrationPattern = longArrayOf(100, 200, 100, 200)
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }

                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

        private fun getNotificationInfo(importance: Int): NotificationInfo =
            when (importance) {
                NotificationManager.IMPORTANCE_HIGH -> NotificationInfo.HIGH
                else -> NotificationInfo.DEFAULT
            }

        private fun createNotificationChannel(
            context: Context,
            showBadge: Boolean,
            infoInfo: NotificationInfo
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val notificationChannel = NotificationChannel(
                    infoInfo.id,
                    infoInfo.name,
                    infoInfo.importance
                ).apply {
                    this.description = description
                    enableLights(true)
                    enableVibration(true)
                    setShowBadge(showBadge)
                    vibrationPattern = longArrayOf(100, 200, 100, 200)
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }

                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

        private fun buildNotification(
            context: Context,
            notificationInfo: NotificationInfo,
            style: NotificationCompat.Style?,
            title: String,
            message: String
        ): Notification {
            val pendingIntent = getPendingIntent(context)

            val notificationBuilder = NotificationCompat.Builder(context, notificationInfo.id)
                .apply {

                    if (style != null) {
                        this.setStyle(style)
                    }
                    setSmallIcon(R.drawable.ic_launcher_background)
                    setAutoCancel(true)
                    setDefaults(Notification.DEFAULT_ALL)
                    setWhen(System.currentTimeMillis())
                    setContentTitle(title)
                    setContentText(message)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        setVibrate(longArrayOf(500, 500))
                    }
                    if (pendingIntent != null) {
                        setContentIntent(pendingIntent)
                    }
                    if (notificationInfo.importance == NotificationManager.IMPORTANCE_HIGH) {
                        priority = NotificationCompat.PRIORITY_HIGH

                        setFullScreenIntent(getFullScreenPendingIntent(context), true)
                    }
                }
            return notificationBuilder.build()
        }

        private fun getPendingIntent(context: Context): PendingIntent? {
            // Create an Intent for the activity you want to start
            val intent = Intent(context, NotificationActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            // Create the TaskStackBuilder
            return TaskStackBuilder.create(context).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(intent)

                // Get the PendingIntent containing the entire back stack
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        }

        private fun getFullScreenPendingIntent(context: Context): PendingIntent? {
            val intent = Intent(context, NotificationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return PendingIntent.getActivity(
                context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }
}
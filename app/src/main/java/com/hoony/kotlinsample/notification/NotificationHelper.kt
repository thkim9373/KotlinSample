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

    companion object {
        private const val NOTIFICATION_ID: String = "DEFAULT_NOTIFICATION_ID"
        private const val NOTIFICATION_NAME: String = "Default notification"
        private const val NOTIFICATION_DESCRIPTION: String = "Description"

        fun sendNotification(context: Context, title: String, message: String) {
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            createNotificationChannel(notificationManager)
            val notification = buildNotification(context, title, message)

            notificationManager.notify(0, notification)
        }

        fun sendNotification(
            context: Context,
            importance: Int,
            showBadge: Boolean,
            title: String,
            message: String
        ) {

            createNotificationChannel(
                context,
                importance,
                showBadge,
                NOTIFICATION_NAME,
                NOTIFICATION_DESCRIPTION
            )

            val notification = buildNotification(context, title, message)

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(0, notification)
        }

        private fun createNotificationChannel(
            notificationManager: NotificationManager
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val notificationChannel = NotificationChannel(
                    NOTIFICATION_ID,
                    NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = NOTIFICATION_DESCRIPTION
                    enableLights(true)
                    enableVibration(true)
                    vibrationPattern = longArrayOf(100, 200, 100, 200)
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }

                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

        private fun createNotificationChannel(
            context: Context,
            importance: Int,
            showBadge: Boolean,
            name: String,
            description: String
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val notificationChannel = NotificationChannel(
                    NOTIFICATION_ID,
                    name,
                    importance
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
            title: String,
            message: String
        ): Notification {
            val pendingIntent = getPendingIntent(context)

            val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_ID)
                .apply {
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
    }
}
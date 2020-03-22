package com.hoony.kotlinsample.common

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri

class AlarmTool : BroadcastReceiver() {
    companion object {
        private const val ACTION_RUN_ALARM = "RUN_ALARM"

        private fun createAlarmIntent(context: Context, id: String): PendingIntent {
            val intent = Intent(context, AlarmTool::class.java)
            intent.data = Uri.parse("id:" + id)
            intent.putExtra("MEMO_ID", id)
            intent.action = ACTION_RUN_ALARM

            return PendingIntent.getBroadcast(context, 0, intent, 0)
        }
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        TODO("Not yet implemented")
    }
}
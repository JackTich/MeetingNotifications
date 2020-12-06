package com.jacktich.meetingnotifications.utils.receivers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.utils.constants.AppConstants

class NotificationBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val KEY_USER_NAME = "keyUserName"
        const val KEY_NOTIFICATION_ID = "keyNotificationId"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val name = intent.getStringExtra(KEY_USER_NAME)
        val notificationId = intent.getIntExtra(KEY_NOTIFICATION_ID, -1)

        val notificationManager = (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

        val builder =  NotificationCompat.Builder(context, AppConstants.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_active)
            .setContentTitle(context.getString(R.string.meeting))
            .setContentText("До встречи с $name остался час!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(notificationId, builder.build())
    }
}
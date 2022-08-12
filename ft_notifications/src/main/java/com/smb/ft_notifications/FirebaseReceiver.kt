package com.smb.ft_notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_home.presentation.HomeActivity

const val channel_id = "notification_channel"

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseReceiver : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null)
            showNotification(message)
    }

    private fun showNotification(message: RemoteMessage) {
        val intent = Intent(applicationContext, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(applicationContext, channel_id)
            .setSmallIcon(R.drawable.ic_arrow)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setContent(
                getCustomView(
                    message.notification?.title ?: EMPTY_STRING,
                    message.notification?.body ?: EMPTY_STRING
                )
            )

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            channel_id, "web_app",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(
            notificationChannel
        )
        notificationManager.notify(0, builder.build())
    }

    private fun getCustomView(title: String, message: String) =
        RemoteViews(applicationContext.packageName, R.layout.custom_notification).apply {
            setTextViewText(R.id.title, title)
            setTextViewText(R.id.message, message)
            setImageViewResource(R.id.icon, R.drawable.ic_arrow)
        }

}
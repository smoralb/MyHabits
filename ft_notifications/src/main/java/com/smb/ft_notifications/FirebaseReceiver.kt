package com.smb.ft_notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val CHANNEL_ID = "${BuildConfig.LIBRARY_PACKAGE_NAME}_channel_mh"
const val CHANNEL_NAME = "Reminders"
const val NOTIFICATION_ID = 101

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseReceiver : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

    fun showNotification(context: Context, title: String?, message: String?, intent: PendingIntent) {
        val notificationBuilder = createNotification(context, intent, title, message)
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(context, notificationManager)

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun createNotification(
        context: Context,
        pendingIntent: PendingIntent,
        title: String?,
        message: String?
    ) = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title ?: context.getString(R.string.app_name))
            .setContentText(
                message ?: context.getString(R.string.default_notification_message)
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(PRIORITY_HIGH)

    private fun createNotificationChannel(context: Context, notificationManager: NotificationManager) {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description =
            context.getString(R.string.notification_channel_description)
        notificationChannel.lockscreenVisibility = VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
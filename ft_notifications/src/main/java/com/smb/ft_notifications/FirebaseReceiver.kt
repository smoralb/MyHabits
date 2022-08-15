package com.smb.ft_notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.smb.ft_home.presentation.HomeActivity

const val CHANNEL_ID = "${BuildConfig.LIBRARY_PACKAGE_NAME}_channel_mh"
const val CHANNEL_NAME = "Reminders"
const val NOTIFICATION_ID = 101

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseReceiver : FirebaseMessagingService() {

    private val intent: Intent
        get() {
            return HomeActivity.newIntentFlags(
                applicationContext,
                FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            )
        }

    private val pendingIntent: PendingIntent
        @SuppressLint("UnspecifiedImmutableFlag")
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getActivity(this, 0, intent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
            } else {
                getActivity(this, 0, intent, FLAG_UPDATE_CURRENT)
            }
        }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null)
            showNotification(message)
    }


    private fun showNotification(message: RemoteMessage) {
        val notificationBuilder = createNotification(pendingIntent, message)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun createNotification(pendingIntent: PendingIntent, message: RemoteMessage) =
        NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(
                message.notification?.title ?: applicationContext.getString(R.string.app_name)
            )
            .setContentText(
                message.notification?.body
                    ?: applicationContext.getString(R.string.default_notification_message)
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
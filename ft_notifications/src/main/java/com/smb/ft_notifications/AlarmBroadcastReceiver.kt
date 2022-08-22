package com.smb.ft_notifications

import android.annotation.SuppressLint
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.PendingIntent.getActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

@SuppressLint("UnspecifiedImmutableFlag")
class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            FirebaseReceiver().showNotification(
                context, null, null,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    getActivity(
                        context,
                        102,
                        intent,
                        FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
                    )
                } else {
                    getActivity(context, 102, intent, FLAG_UPDATE_CURRENT)
                }
            )
        }
    }
}
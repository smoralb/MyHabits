package com.smb.core.navigation

import android.content.Context
import android.content.Intent
import androidx.navigation.ActivityNavigator

interface BaseNavigator {

    fun navigateClearTop(context: Context, intent: Intent) {
        ActivityNavigator(context).apply {
            this.navigate(
                this.createDestination()
                    .setIntent(intent),
                null,
                null,
                ActivityNavigator.Extras.Builder()
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .build()
            )
        }
    }
}
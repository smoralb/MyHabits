package com.smb.myhabits.com.smb.myhabits.navigation

import android.content.Context
import android.content.Intent
import androidx.navigation.ActivityNavigator
import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.ft_main.presentation.main.MainActivity

class AuthNavigatorImpl : AuthNavigator {

    override fun navigateToMainScreen(context: Context) {
        val activityNavigator = ActivityNavigator(context)
        activityNavigator.navigate(
            activityNavigator.createDestination().setIntent(Intent(context, MainActivity::class.java)),
            null,
            null,
            null
        )
    }
}
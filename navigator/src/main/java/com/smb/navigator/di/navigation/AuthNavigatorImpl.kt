package com.smb.navigator.di.navigation

import android.content.Context
import android.content.Intent
import androidx.navigation.ActivityNavigator
import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.ft_main.presentation.HomeActivity

class AuthNavigatorImpl : AuthNavigator {

    override fun navigateToHomeScreen(context: Context) {
        val activityNavigator = ActivityNavigator(context)
        activityNavigator.navigate(
            activityNavigator.createDestination()
                .setIntent(Intent(context, HomeActivity::class.java)),
            null,
            null,
            null
        )
    }
}
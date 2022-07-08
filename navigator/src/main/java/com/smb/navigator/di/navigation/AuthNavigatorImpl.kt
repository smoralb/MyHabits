package com.smb.navigator.di.navigation

import android.content.Context
import android.content.Intent
import androidx.navigation.ActivityNavigator
import com.smb.ft_auth.AuthActivity
import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.ft_main.presentation.HomeActivity

class AuthNavigatorImpl : AuthNavigator {

    override fun navigateToHomeScreen(context: Context) {
        ActivityNavigator(context).apply {
            this.navigate(
                this.createDestination()
                    .setIntent(Intent(context, HomeActivity::class.java)),
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

    override fun navigateToLogin(context: Context) {
        ActivityNavigator(context).apply {
            this.navigate(
                this.createDestination()
                    .setIntent(Intent(context, AuthActivity::class.java)),
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
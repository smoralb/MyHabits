package com.smb.navigator.di.navigation

import android.content.Context
import com.smb.ft_auth.AuthActivity
import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.ft_main.presentation.HomeActivity

class AuthNavigatorImpl : AuthNavigator {

    override fun navigateToHomeScreen(context: Context) {
        navigateClearTop(context, HomeActivity.newIntent(context))
    }

    override fun navigateToLogin(context: Context) {
        navigateClearTop(context, AuthActivity.newIntent(context))
    }
}
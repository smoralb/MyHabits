package com.smb.navigator.di.navigation

import android.content.Context
import com.smb.ft_auth.AuthActivity
import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.ft_main.presentation.HomeActivity

class AuthNavigatorImpl(
    private val context: Context
) : AuthNavigator {

    override fun navigateToHomeScreen() {
        navigateClearTop(context, HomeActivity.newIntent(context))
    }

    override fun navigateToLogin() {
        navigateClearTop(context, AuthActivity.newIntent(context))
    }
}
package com.smb.ft_auth.navigation

import android.content.Context

interface AuthNavigator {
    fun navigateToHomeScreen(context: Context)
    fun navigateToLogin(context: Context)
}
package com.smb.ft_auth.navigation

import android.content.Context
import android.content.Intent
import com.smb.core.navigation.BaseNavigator

interface AuthNavigator : BaseNavigator {
    fun navigateToHomeScreen(context: Context)
    fun navigateToLogin(context: Context)
}
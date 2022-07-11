package com.smb.ft_auth.navigation

import com.smb.core.navigation.BaseNavigator

interface AuthNavigator : BaseNavigator {
    fun navigateToHomeScreen()
    fun navigateToLogin()
}
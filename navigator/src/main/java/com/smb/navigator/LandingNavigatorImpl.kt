package com.smb.navigator

import android.content.Context
import com.smb.ft_auth.navigation.LandingNavigator
import com.smb.ft_home.presentation.HomeActivity

class LandingNavigatorImpl(
    private val context: Context
) : LandingNavigator {

    override fun navigateToHomeScreen() {
        navigateClearTop(context, HomeActivity.newIntent(context))
    }
}
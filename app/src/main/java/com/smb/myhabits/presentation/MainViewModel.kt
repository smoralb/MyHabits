package com.smb.myhabits.presentation

import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.myhabits.com.smb.myhabits.domain.usecase.CheckSessionUseCase
import com.smb.myhabits.presentation.MainState.NavigateToHome
import com.smb.myhabits.presentation.MainState.NavigateToLogin

class MainViewModel(
    private val checkSessionUseCase: CheckSessionUseCase,
    private val authNavigator: AuthNavigator
) : BaseViewModel<MainState>() {

    fun checkUserSession() {
        execute {
            checkSessionUseCase(Unit).fold(
                handleError = {},
                handleSuccess = { isLoggedIn ->
                    if (isLoggedIn) {
                        _viewState update NavigateToHome
                    } else {
                        _viewState update NavigateToLogin
                    }
                }
            )
        }
    }

    fun navigateToHome() {
        authNavigator.navigateToHomeScreen()
    }

    fun navigateToLogin() {
        authNavigator.navigateToLogin()
    }
}
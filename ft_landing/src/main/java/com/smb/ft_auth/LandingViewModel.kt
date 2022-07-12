package com.smb.ft_auth

import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_auth.LandingState.NavigateToHome
import com.smb.ft_auth.domain.usecase.CheckSessionUseCase
import com.smb.ft_auth.navigation.LandingNavigator

class LandingViewModel(
    private val checkSessionUseCase: CheckSessionUseCase,
    private val landingNavigator: LandingNavigator
) : BaseViewModel<LandingState>() {

    fun checkUserSession() {
        execute {
            checkSessionUseCase(Unit).fold(
                handleError = {},
                handleSuccess = { isLoggedIn ->
                    if (isLoggedIn) {
                        _viewState update NavigateToHome
                    }
                }
            )
        }
    }

    fun navigateToHome() {
        landingNavigator.navigateToHomeScreen()
    }

}
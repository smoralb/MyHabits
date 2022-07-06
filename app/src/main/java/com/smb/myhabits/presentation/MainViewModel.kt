package com.smb.myhabits.presentation

import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.myhabits.com.smb.myhabits.domain.usecase.CheckSessionUseCase
import com.smb.myhabits.presentation.MainState.NavigateToLogin
import com.smb.myhabits.presentation.MainState.NavigateToMain

class MainViewModel(
    private val checkSessionUseCase: CheckSessionUseCase
) : BaseViewModel<MainState>() {

    fun checkUserSession() {
        execute {
            checkSessionUseCase(Unit).fold(
                handleError = {},
                handleSuccess = { isLoggedIn ->
                    if (isLoggedIn) {
                        _viewState update NavigateToMain
                    } else {
                        _viewState update NavigateToLogin
                    }
                }
            )
        }
    }
}
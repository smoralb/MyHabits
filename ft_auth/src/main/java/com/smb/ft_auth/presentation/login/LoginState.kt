package com.smb.ft_auth.presentation.login

import com.smb.core.presentation.base.BaseState

sealed class LoginState: BaseState() {
    object NavigateToSignUp: LoginState()
    object NavigateToMainView: LoginState()
    object ShowError: LoginState()
    object ShowLoading: LoginState()
    object HideLoading: LoginState()
}
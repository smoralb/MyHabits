package com.smb.ft_auth.presentation.signup

import com.smb.core.presentation.base.BaseState

sealed class SignUpState : BaseState() {
    object ShowLoading : SignUpState()
    object NavigateToLogin : SignUpState()
    data class ShowError(val message: Int) : SignUpState()
    object ShowSuccess : SignUpState()
}
package com.smb.ft_auth.signup

import com.smb.core.presentation.base.BaseState

sealed class SignUpState: BaseState() {
    object NavigateToLogin: SignUpState()
}
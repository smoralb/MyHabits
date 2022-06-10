package com.smb.ft_auth.signup

import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_auth.signup.SignUpState.NavigateToLogin

class SignUpViewModel : BaseViewModel<SignUpState>() {

    fun navigateToLogin() {
        _viewState update NavigateToLogin
    }

}
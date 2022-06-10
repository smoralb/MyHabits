package com.smb.ft_auth.login

import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_auth.login.LoginState.NavigateToSignUp

class LoginViewModel : BaseViewModel<LoginState>() {

    fun onCreateAccountClickListener () {
        _viewState update NavigateToSignUp
    }
}
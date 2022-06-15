package com.smb.ft_auth.presentation.login

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_auth.domain.usecase.LoginUseCase
import com.smb.ft_auth.presentation.login.LoginState.NavigateToMainView
import com.smb.ft_auth.presentation.login.LoginState.NavigateToSignUp
import com.smb.ft_auth.presentation.login.LoginState.ShowError
import com.smb.ft_auth.presentation.login.LoginState.ShowLoading
import com.smb.ft_auth.presentation.login.mapper.LoginMapper

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val mapper: LoginMapper
) : BaseViewModel<LoginState>() {

    val email: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val password: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun navigateToSignUp() {
        _viewState update NavigateToSignUp
    }

    fun login() {
        _viewState update ShowLoading
        execute {
            loginUseCase(LoginUseCase.Params(email.value!!, password.value!!)).fold(
                handleSuccess = {
                    _viewState update NavigateToMainView
                },
                handleError = {
                    _viewState update ShowError(mapper.checkErrorMessage(it))
                }
            )
        }
    }
}
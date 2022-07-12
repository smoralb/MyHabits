package com.smb.ft_auth.presentation.signup

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCase
import com.smb.ft_auth.presentation.signup.SignUpState.NavigateToLogin
import com.smb.ft_auth.presentation.signup.SignUpState.ShowError
import com.smb.ft_auth.presentation.signup.SignUpState.ShowLoading
import com.smb.ft_auth.presentation.signup.SignUpState.ShowSuccess
import com.smb.ft_auth.presentation.signup.mapper.SignUpMapper

class SignUpViewModel(
    private val createAccountUseCase: CreateNewAccountUseCase,
    private val mapper: SignUpMapper
) : BaseViewModel<SignUpState>() {

    val email: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val password: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun navigateToLogin() {
        _viewState update NavigateToLogin
    }

    fun createNewAccount() {
        _viewState update ShowLoading
        execute {
            createAccountUseCase(
                CreateNewAccountUseCase.Params(
                    email.value!!,
                    password.value!!
                )
            ).fold(
                handleError = {
                    _viewState update ShowError(mapper.checkErrorMessage(it))
                },
                handleSuccess = {
                    _viewState update ShowSuccess
                }
            )
        }
    }

}
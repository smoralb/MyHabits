package com.smb.ft_auth.login

import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_auth.BR
import com.smb.ft_auth.R
import com.smb.ft_auth.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginState, FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login, BR.viewModel) {

    override val viewModel: LoginViewModel by viewModel()

    override fun checkViewState(state: LoginState) {
        TODO("Not yet implemented")
    }


}
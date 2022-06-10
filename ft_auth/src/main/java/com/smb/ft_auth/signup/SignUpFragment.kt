package com.smb.ft_auth.signup

import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_auth.BR
import com.smb.ft_auth.R
import com.smb.ft_auth.databinding.FragmentSignupBinding
import com.smb.ft_auth.signup.SignUpState.NavigateToLogin
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseFragment<SignUpState, FragmentSignupBinding, SignUpViewModel>(
    R.layout.fragment_signup, BR.viewModel
) {
    override val viewModel: SignUpViewModel by viewModel()

    override fun checkViewState(state: SignUpState) {
        when (state) {
            is NavigateToLogin -> navigateTo(SignUpFragmentDirections.goToLogin())
        }
    }
}
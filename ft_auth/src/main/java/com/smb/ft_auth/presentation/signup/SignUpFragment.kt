package com.smb.ft_auth.presentation.signup

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.smb.core.extensions.hideKeyboard
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_auth.BR
import com.smb.ft_auth.R
import com.smb.ft_auth.databinding.FragmentSignupBinding
import com.smb.ft_auth.presentation.signup.SignUpState.NavigateToLogin
import com.smb.ft_auth.presentation.signup.SignUpState.ShowError
import com.smb.ft_auth.presentation.signup.SignUpState.ShowLoading
import com.smb.ft_auth.presentation.signup.SignUpState.ShowSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseFragment<SignUpState, FragmentSignupBinding, SignUpViewModel>(
    R.layout.fragment_signup, BR.viewModel
) {
    override val viewModel: SignUpViewModel by viewModel()

    override fun checkViewState(state: SignUpState) {
        when (state) {
            is ShowLoading -> binding.pILoading.visibility = VISIBLE
            is ShowError -> showToastResult(state.message)
            is ShowSuccess -> showToastResult(getString(R.string.sign_up_success))
            is NavigateToLogin -> navigateTo(SignUpFragmentDirections.goToLogin())
        }
    }

    private fun showToastResult(messageId: String) {
        hideKeyboard()
        binding.pILoading.visibility = GONE
        Toast.makeText(
            activity,
            messageId,
            Toast.LENGTH_LONG
        ).show()
    }
}
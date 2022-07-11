package com.smb.ft_auth.presentation.login

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.smb.core.extensions.hideKeyboard
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_auth.AuthActivity
import com.smb.ft_auth.BR
import com.smb.ft_auth.R
import com.smb.ft_auth.databinding.FragmentLoginBinding
import com.smb.ft_auth.presentation.login.LoginState.HideLoading
import com.smb.ft_auth.presentation.login.LoginState.NavigateToMainView
import com.smb.ft_auth.presentation.login.LoginState.NavigateToSignUp
import com.smb.ft_auth.presentation.login.LoginState.ShowError
import com.smb.ft_auth.presentation.login.LoginState.ShowLoading
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginState, FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login, BR.viewModel
) {

    override val viewModel: LoginViewModel by viewModel()

    override fun checkViewState(state: LoginState) {
        when (state) {
            is NavigateToSignUp -> navigateTo(LoginFragmentDirections.goToSignUp())
            is ShowLoading -> binding.pILoading.visibility = VISIBLE
            is HideLoading -> binding.pILoading.visibility = GONE
            
            // TODO: Pass Intent instead of Activity
            is NavigateToMainView -> viewModel.navigateToHomeView(requireContext())
            is ShowError -> showToastResult(state.errorMessage)
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
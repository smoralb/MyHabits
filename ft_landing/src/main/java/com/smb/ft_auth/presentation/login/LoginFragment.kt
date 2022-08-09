package com.smb.ft_auth.presentation.login

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.smb.core.extensions.clearUserdata
import com.smb.core.extensions.getUserName
import com.smb.core.extensions.getUserPassword
import com.smb.core.extensions.hideKeyboard
import com.smb.core.extensions.isUserRemembered
import com.smb.core.extensions.storeInSharedPreferences
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserData()
    }

    override fun checkViewState(state: LoginState) {
        hideKeyboard()
        when (state) {
            is NavigateToSignUp -> navigateTo(LoginFragmentDirections.goToSignUp())
            is ShowLoading -> binding.pILoading.visibility = VISIBLE
            is HideLoading -> binding.pILoading.visibility = GONE
            is NavigateToMainView -> {
                manageSharedPreferences()
                viewModel.navigateToHomeView()
            }
            is ShowError -> showToastResult(state.errorMessage)
        }
    }

    private fun loadUserData() {
        if (!getUserName().isNullOrBlank() && !getUserPassword().isNullOrBlank()) {
            viewModel.email update getUserName() as String
            viewModel.password update getUserPassword() as String
            viewModel.isRememberChecked update isUserRemembered()
        }
    }

    private fun manageSharedPreferences() {
        if (viewModel.isRememberChecked.value!!) {
            storeInSharedPreferences(viewModel.email.value!!, viewModel.password.value!!)
        } else clearUserdata()
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
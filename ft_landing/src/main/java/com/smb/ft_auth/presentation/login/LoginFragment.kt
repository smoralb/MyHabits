package com.smb.ft_auth.presentation.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.smb.core.extensions.CryptographyManager
import com.smb.core.extensions.createBiometricPrompt
import com.smb.core.extensions.createPromptInfo
import com.smb.core.extensions.hideKeyboard
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
import java.nio.charset.Charset

class LoginFragment : BaseFragment<LoginState, FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login, BR.viewModel
) {

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private var readyToEncrypt: Boolean = false
    private val cryptographyManager = CryptographyManager()
    private lateinit var ciphertext: ByteArray
    private lateinit var initializationVector: ByteArray
    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        biometricPrompt = createBiometricPrompt(
            onAuthenticationSucceeded = {
                processData(it.cryptoObject)
            }
        )
        promptInfo = createPromptInfo()
        authenticateToEncrypt()
    }

    override fun checkViewState(state: LoginState) {
        when (state) {
            is NavigateToSignUp -> navigateTo(LoginFragmentDirections.goToSignUp())
            is ShowLoading -> binding.pILoading.visibility = VISIBLE
            is HideLoading -> binding.pILoading.visibility = GONE
            is NavigateToMainView -> viewModel.navigateToHomeView()
            is ShowError -> showToastResult(state.errorMessage)
        }
    }

    private fun authenticateToEncrypt() {
        readyToEncrypt = true
        if (BiometricManager.from(requireContext()).canAuthenticate() == BiometricManager
                .BIOMETRIC_SUCCESS
        ) {
            val cipher = cryptographyManager.getInitializedCipherForEncryption("secretKeyName")
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

    private fun authenticateToDecrypt() {
        readyToEncrypt = false
        if (BiometricManager.from(requireContext()).canAuthenticate() == BiometricManager
                .BIOMETRIC_SUCCESS
        ) {
            val cipher = cryptographyManager.getInitializedCipherForDecryption(
                "secretKeyName",
                initializationVector
            )
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

    private fun processData(cryptoObject: BiometricPrompt.CryptoObject?) {
        val data = if (readyToEncrypt) {
            val text = viewModel.email.value!!
            val encryptedData = cryptographyManager.encryptData(text, cryptoObject?.cipher!!)
            ciphertext = encryptedData.ciphertext
            initializationVector = encryptedData.initializationVector

            String(ciphertext, Charset.forName("UTF-8"))
        } else {
            cryptographyManager.decryptData(ciphertext, cryptoObject?.cipher!!)
        }
        viewModel.email update data
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
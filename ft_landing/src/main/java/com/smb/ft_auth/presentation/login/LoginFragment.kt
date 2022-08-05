package com.smb.ft_auth.presentation.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.smb.core.extensions.CryptographyManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biometricPrompt = createBiometricPrompt()
        promptInfo = createPromptInfo()
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

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            // e.g. "Sign in"
            .setTitle("prompt_info_title")
            // e.g. "Biometric for My App"
            .setSubtitle("R.string.prompt_info_subtitle")
            // e.g. "Confirm biometric to continue"
            .setDescription("R.string.prompt_info_description")
            .setConfirmationRequired(false)
            .setNegativeButtonText("R.string.prompt_info_use_app_password")
            // .setDeviceCredentialAllowed(true) // Allow PIN/pattern/password authentication.
            // Also note that setDeviceCredentialAllowed and setNegativeButtonText are
            // incompatible so that if you uncomment one you must comment out the other
            .build()
        return promptInfo
    }

    private fun createBiometricPrompt(): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(requireContext())

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Log.d(TAG, "$errorCode :: $errString")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.d(TAG, "Authentication failed for an unknown reason")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Log.d(TAG, "Authentication was successful")
                processData(result.cryptoObject)
            }
        }

        //The API requires the client/Activity context for displaying the prompt
        val biometricPrompt = BiometricPrompt(this, executor, callback)
        return biometricPrompt
    }
}
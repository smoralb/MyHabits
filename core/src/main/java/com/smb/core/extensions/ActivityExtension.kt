package com.smb.core.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricManager.from
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.AuthenticationResult
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.hasBiometricCapability() = from(this).canAuthenticate(BIOMETRIC_STRONG)

fun Context.isBiometricReady() = this.hasBiometricCapability() == BIOMETRIC_SUCCESS

fun Fragment.createBiometricPrompt(
    onAuthenticationError: () -> Unit,
    onAuthenticationFailed: () -> Unit,
    onAuthenticationSuccess: () -> Unit
): BiometricPrompt {

    val executor = ContextCompat.getMainExecutor(requireContext())

    return BiometricPrompt(this, executor, object : AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            onAuthenticationError()
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            onAuthenticationFailed()
        }

        override fun onAuthenticationSucceeded(result: AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            onAuthenticationSuccess()
        }
    })
}

fun createPromptInfo(
    title: String? = null,
    description: String? = null,
    subtitle: String? = null,
    negativeButtonText: String? = null,
    allowDeviceCredential: Boolean = false,
) = PromptInfo.Builder()
    .setTitle(title ?: "This is the title")
    .setDescription(description ?: "This is the description")
    .setSubtitle(subtitle ?: "This is the subtitle")
    .setNegativeButtonText(negativeButtonText ?: "Cancel")
    .apply {
        if (allowDeviceCredential) {
            setAllowedAuthenticators(BIOMETRIC_STRONG)
            setNegativeButtonText(negativeButtonText ?: "Cancel")
        } else if (Build.VERSION.SDK_INT > 29) {
            setAllowedAuthenticators(DEVICE_CREDENTIAL)
        }
    }
    .build()
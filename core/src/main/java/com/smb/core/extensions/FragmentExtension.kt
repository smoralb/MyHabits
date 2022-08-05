package com.smb.core.extensions

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.hasBiometricCapability() = BiometricManager.from(this.requireContext())
    .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)

fun Fragment.isBiometricReady() =
    this.hasBiometricCapability() == BiometricManager.BIOMETRIC_SUCCESS

fun Fragment.createPromptInfo(
    title: String? = null,
    description: String? = null,
    subtitle: String? = null,
    negativeButtonText: String? = null
) = BiometricPrompt.PromptInfo.Builder()
    .setTitle(title ?: "This is the title")
    .setDescription(description ?: "This is the description")
    .setSubtitle(subtitle ?: "This is the subtitle")
    .setNegativeButtonText(negativeButtonText ?: "Cancel")
    .build()

fun Fragment.createBiometricPrompt(
    onAuthenticationError: () -> Unit = {},
    onAuthenticationFailed: () -> Unit = {},
    onAuthenticationSucceeded: (BiometricPrompt.AuthenticationResult) -> Unit
): BiometricPrompt {
    val executor = ContextCompat.getMainExecutor(this.requireContext())

    val callback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            onAuthenticationError()
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            onAuthenticationFailed()
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            onAuthenticationSucceeded(result)
        }
    }

    return BiometricPrompt(this, executor, callback)
}
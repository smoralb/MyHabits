package com.smb.core.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKeys
import com.example.core.R

const val SHARED_PREFERENCES = "Shared Preferences"
const val USER_NAME = "USER_NAME"
const val USER_PASSWORD = "USER_PASSWORD"
const val USER_REMEMBERED = "USER_REMEMBERED"

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.showDialogFragment(
    message: Int = R.string.login_dialog_message,
    positiveButton: Int = R.string.login_dialog_positive,
    negativeButton: Int = R.string.login_dialog_negative,
    onPositiveButtonClicked: () -> Unit,
    onNegativeButtonClicked: () -> Unit
) {
    AlertDialog.Builder(this.requireContext())
        .setMessage(message)
        .setPositiveButton(positiveButton) { _, _ -> onPositiveButtonClicked() }
        .setNegativeButton(negativeButton) { _, _ -> onNegativeButtonClicked() }
        .create()
        .show()
}

fun Fragment.encryptUserData(userName: String, password: String) {
    with(getSharedPreferences(requireContext()).edit()) {
        putString(USER_NAME, userName)
        putString(USER_PASSWORD, password)
        putBoolean(USER_REMEMBERED, true)
        apply()
    }
}

fun Fragment.getUserEmail() =
    getSharedPreferences(requireContext()).getString(USER_NAME, EMPTY_STRING)

fun Fragment.getUserPassword() =
    getSharedPreferences(requireContext()).getString(USER_PASSWORD, EMPTY_STRING)

fun Fragment.isUserRemembered() =
    getSharedPreferences(requireContext()).getBoolean(USER_REMEMBERED, false)

fun Fragment.clearUserdata() =
    getSharedPreferences(requireContext()).edit()
        .apply {
            remove(USER_NAME)
            remove(USER_PASSWORD)
            remove(USER_REMEMBERED)
        }.apply()


private fun getSharedPreferences(context: Context): SharedPreferences =
    EncryptedSharedPreferences.create(
        SHARED_PREFERENCES,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        AES256_SIV,
        AES256_GCM
    )

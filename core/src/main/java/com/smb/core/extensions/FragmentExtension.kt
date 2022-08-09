package com.smb.core.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
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


fun Fragment.storeInSharedPreferences(userName: String, password: String) {
    requireContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        .edit().apply {
            putString(USER_NAME, userName)
            putString(USER_PASSWORD, password)
            putBoolean(USER_REMEMBERED, true)
        }.apply()
}

fun Fragment.getUserName() =
    requireContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        .getString(USER_NAME, EMPTY_STRING)

fun Fragment.getUserPassword() =
    requireContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        .getString(USER_PASSWORD, EMPTY_STRING)

fun Fragment.isUserRemembered() =
    requireContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        .getBoolean(USER_REMEMBERED, false)

fun Fragment.clearUserdata() =
    requireContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
        .apply {
            remove(USER_NAME)
            remove(USER_PASSWORD)
            remove(USER_REMEMBERED)
        }.apply()
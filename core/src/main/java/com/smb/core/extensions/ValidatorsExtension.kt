package com.smb.core.extensions

import android.util.Patterns

fun String.isAValidEmail() =
    this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun String.isAValidPassword() =
    this.isNotBlank() && this.length > 8
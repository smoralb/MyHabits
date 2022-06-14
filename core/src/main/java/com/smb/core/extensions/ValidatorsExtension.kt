package com.smb.core.extensions

import androidx.core.util.PatternsCompat

fun String.isAValidEmail() =
    this.isNotBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()


fun String.isAValidPassword() =
    this.isNotBlank() && this.length > 8
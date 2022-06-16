package com.smb.core.extensions

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

val EMAIL_ADDRESS_PATTERN = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)
fun String.isAValidEmail(): Boolean{
    return this.isNotBlank() && EMAIL_ADDRESS_PATTERN.matcher(this).matches()
}

/*
fun String.isAValidEmail() =
    this.isNotBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
*/

fun String.isAValidPassword() =
    this.isNotBlank() && this.length > 8
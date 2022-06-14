package com.smb.ft_auth.presentation.signup.mapper

import android.content.Context
import com.example.core.R
import com.smb.core.data.Result

interface SignUpMapper {
    fun checkErrorMessage(error: Result.Error): String
}

class SignUpMapperImpl(val context: Context) : SignUpMapper {

    override fun checkErrorMessage(error: Result.Error): String =
        when {
            error.error.isNotBlank() -> error.error
            error.errorId != -1 -> context.getString(error.errorId)
            else -> context.getString(R.string.generic_error)
        }
}
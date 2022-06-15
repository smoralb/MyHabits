package com.smb.ft_auth.presentation.login.mapper

import android.content.Context
import com.example.core.R
import com.smb.core.data.Result

interface LoginMapper {
    fun checkErrorMessage(error: Result.Error): String
}

class LoginMapperImpl(
    val context: Context
): LoginMapper {
    override fun checkErrorMessage(error: Result.Error): String =
        when {
            error.error.isNotBlank() -> error.error
            error.errorId != -1 -> context.getString(error.errorId)
            else -> context.getString(R.string.generic_error)
        }
}
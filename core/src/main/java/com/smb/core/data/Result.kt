package com.smb.core.data

import com.example.core.R
import com.smb.core.extensions.EMPTY_STRING

sealed class Result<out S> {
    data class Success<out S>(val value: S) : Result<S>()
    data class Error(val code: Int? = null, val error: String = EMPTY_STRING, val errorId: Int = -1) :
        Result<Nothing>()

    val isSuccess
        get() = this is Success

    val isError
        get() = this is Error


    fun fold(
        handleSuccess: (S) -> Unit,
        handleError: (Error) -> Unit
    ) {
        when (this) {
            is Success -> handleSuccess(value)
            is Error -> handleError(Error(code, error, errorId))
        }
    }
}




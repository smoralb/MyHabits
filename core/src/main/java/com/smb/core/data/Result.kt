package com.smb.core.data

import com.example.core.R

sealed class Result<out S> {
    data class Success<out S>(val value: S) : Result<S>()
    data class Error(val code: Int? = null, val error: String? = null, val errorId: Int = R.string.generic_error) :
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




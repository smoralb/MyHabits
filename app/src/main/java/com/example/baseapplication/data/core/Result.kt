package com.example.baseapplication.data.core

sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()
    data class Error(val code: Int? = null, val error: ErrorResponse? = null): Result<Nothing>()
}

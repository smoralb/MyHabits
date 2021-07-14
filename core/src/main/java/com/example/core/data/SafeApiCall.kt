package com.example.core.data

import retrofit2.HttpException
import retrofit2.Response

/**
 * Here we can handle different types or errors retrieved from api and propagate them
 * to the presentation layer
 */

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): Result<R> {
    val response: Response<T>
    return try {
        response = apiCall.invoke()
        Result.Success(mapper(response.body()!!))
    } catch (exception: Throwable) {
        when (exception) {
            is HttpException -> Result.Error(exception.code(), exception.message())
            else -> Result.Error(null, null)
        }
    }
}
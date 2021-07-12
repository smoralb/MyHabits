package com.example.baseapplication.data.core

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
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
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> Result.Error(throwable.code(), convertErrorBody(throwable))
                else -> Result.Error(null, null)
            }
        }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}
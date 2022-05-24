package com.smb.core.data

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException

/**
 * Here we can handle different types or errors retrieved from api and propagate them
 * to the presentation layer
 */

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Task<T>,
    mapper: (T) -> R
): Result<R> {
    val response: T
    return try {
        response = apiCall.invoke().await()
        Result.Success(mapper(response))
    } catch (exception: Throwable) {
        when (exception) {
            is HttpException -> Result.Error(exception.code(), exception.message())
            else -> Result.Error(null, null)
        }
    }
}
package com.smb.core.data

import coil.network.HttpException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.tasks.await

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
            is HttpException -> Result.Error()
            is FirebaseAuthUserCollisionException -> Result.Error(error = exception.localizedMessage)
            else -> Result.Error(null)
        }
    }
}
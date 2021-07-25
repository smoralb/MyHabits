package com.example.baseapplication.data.core

/**
 * This is a sample class but should be adapted to the class retrieved from api
 * to show a specific error with any description for the user
 */
data class ErrorResponse(
    val errorCode: String,
    val description: String
)
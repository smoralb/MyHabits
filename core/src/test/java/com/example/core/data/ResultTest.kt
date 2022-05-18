package com.example.core.data

import com.smb.core.data.Result
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ResultTest {

    private val successResult = Result.Success(1)
    private val errorResult = Result.Error(1)

    @Test
    fun `fold method should return success`() {
        successResult.fold(
            handleSuccess = {
                assertTrue(it == 1)
            },
            handleError = {}
        )
    }

    @Test
    fun `fold method should return error`() {
        errorResult.fold(
            handleSuccess = {},
            handleError = {
                assertTrue(it.code == 1)
            }
        )
    }
}
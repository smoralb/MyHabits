package com.smb.ft_auth.presentation.signup

import android.content.Context
import com.smb.core.data.Result
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_auth.presentation.signup.mapper.SignUpMapper
import com.smb.ft_auth.presentation.signup.mapper.SignUpMapperImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.whenever

class SignUpMapperTest : BaseUnitTest() {

    @Mock
    private lateinit var context: Context

    private lateinit var mapper: SignUpMapper

    @BeforeEach
    fun setUp() {
        mapper = SignUpMapperImpl(context)
    }

    @TestFactory
    fun `Check error message should return`() = listOf(
        Result.Error(error = "Any error"), Result.Error(errorId = -1), Result.Error(errorId = 12)
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {

            whenever(context.getString(any())).thenReturn(EMPTY_STRING)

            val result = mapper.checkErrorMessage(testCase)

            if (testCase.error.isEmpty()) {
                assertEquals(EMPTY_STRING, result)
            } else {
                assertEquals("Any error", result)
            }
        }
    }
}
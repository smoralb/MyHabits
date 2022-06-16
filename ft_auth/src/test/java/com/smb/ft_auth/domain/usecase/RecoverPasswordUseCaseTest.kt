package com.smb.ft_auth.domain.usecase

import com.smb.core.data.Result
import com.smb.core.test.BaseUnitTest
import com.smb.ft_auth.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RecoverPasswordUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var recoverPasswordUseCase: RecoverPasswordUseCase

    @BeforeEach
    fun setUp() {
        recoverPasswordUseCase = RecoverPasswordUseCaseImpl(repository = repository)
    }

    @TestFactory
    fun `RecoverPasswordUseCase should return`() = listOf(
        Result.Error(), Result.Success(Unit)
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(repository.recoverPassword("sample@sample")).thenReturn(
                    testCase
                )

                val result = recoverPasswordUseCase(RecoverPasswordUseCase.Params("sample@sample"))

                assertEquals(result.isSuccess, testCase.isSuccess)
                assertEquals(result.isError, testCase.isError)
                verify(repository).recoverPassword(any())
            }
            clearInvocations(repository)
        }
    }
}
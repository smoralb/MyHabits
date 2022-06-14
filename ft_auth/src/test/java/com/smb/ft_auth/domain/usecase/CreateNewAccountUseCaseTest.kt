package com.smb.ft_auth.domain.usecase

import androidx.core.util.PatternsCompat
import com.smb.core.data.Result
import com.smb.core.extensions.isAValidEmail
import com.smb.core.extensions.isAValidPassword
import com.smb.core.test.BaseUnitTest
import com.smb.ft_auth.domain.mocks.CreateNewAccountDataMock
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
class CreateNewAccountUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var createUseCase: CreateNewAccountUseCase

    @BeforeEach
    fun setUp() {
        createUseCase = CreateNewAccountUseCaseImpl(repository)
    }

    @TestFactory
    fun `CreateNewAccount should return`() = listOf(
        CreateNewAccountDataMock(
            result = Result.Error(),
            email = "email.com",
            validEmail = false,
            password = "123456",
            validPassword = false
        ),
        CreateNewAccountDataMock(
            result = Result.Error(),
            email = "email@email.com",
            validEmail = true,
            password = "123456",
            validPassword = false
        ),
        CreateNewAccountDataMock(
            result = Result.Error(),
            email = "email@email.com", validEmail = true,
            password = "123456789", validPassword = true
        ),
        CreateNewAccountDataMock(
            result = Result.Success(Unit),
            email = "email@email.com",
            validEmail = true,
            password = "123456789",
            validPassword = true
        ),

        ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(PatternsCompat.EMAIL_ADDRESS.matcher(testCase.email).matches()).thenReturn(
                    testCase.validEmail
                )
                if (testCase.result.isError) {
                    whenever(testCase.email.isAValidEmail()).thenReturn(testCase.validEmail)
                    whenever(testCase.password.isAValidPassword()).thenReturn(testCase.validPassword)
                }
                if (testCase.validEmail && testCase.validPassword) {
                    whenever(repository.createNewAccount(any(), any())).thenReturn(testCase.result)
                }

                val result = createUseCase(any())

                assertEquals(result.isSuccess, testCase.result.isSuccess)
                assertEquals(result.isError, testCase.result.isError)
                verify(repository).createNewAccount(any(), any())
                clearInvocations(repository)
            }
        }
    }
}
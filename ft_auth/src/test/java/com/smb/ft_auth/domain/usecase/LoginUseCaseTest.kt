package com.smb.ft_auth.domain.usecase

import androidx.core.util.PatternsCompat
import com.smb.core.data.Result
import com.smb.core.extensions.isAValidEmail
import com.smb.core.extensions.isAValidPassword
import com.smb.core.test.BaseUnitTest
import com.smb.ft_auth.domain.mocks.LoginDataMock
import com.smb.ft_auth.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var useCase: LoginUseCase

    @BeforeEach
    fun setUp() {
        useCase = LoginUseCaseImpl(repository = repository)
    }

    @TestFactory
    fun `CreateNewAccount should return`() = listOf(
        LoginDataMock(
            result = Result.Error(),
            email = "email.com",
            validEmail = false,
            password = "123456",
            validPassword = false
        ),
        LoginDataMock(
            result = Result.Error(),
            email = "email@email.com",
            validEmail = true,
            password = "123456",
            validPassword = false
        ),
        LoginDataMock(
            result = Result.Error(),
            email = "email@email.com", validEmail = true,
            password = "123456789", validPassword = true
        ),
        LoginDataMock(
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
                    whenever(repository.login(any(), any())).thenReturn(testCase.result)
                }

                val result = useCase(any())

                Assertions.assertEquals(result.isSuccess, testCase.result.isSuccess)
                Assertions.assertEquals(result.isError, testCase.result.isError)
                verify(repository).login(any(), any())
                clearInvocations(repository)
            }
        }
    }
}
package com.smb.ft_auth.presentation.signup

import com.smb.core.data.Result
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCase
import com.smb.ft_auth.presentation.signup.SignUpState.NavigateToLogin
import com.smb.ft_auth.presentation.signup.SignUpState.ShowError
import com.smb.ft_auth.presentation.signup.SignUpState.ShowSuccess
import com.smb.ft_auth.presentation.signup.mapper.SignUpMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SignUpViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var mapper: SignUpMapper

    @Mock
    private lateinit var createUserUseCase: CreateNewAccountUseCase

    private lateinit var viewModel: SignUpViewModel

    @BeforeEach
    fun setUp() {
        viewModel = SignUpViewModel(createUserUseCase, mapper)
    }

    @TestFactory
    fun `create new user should return`() = listOf(
        Result.Success(Unit), Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase when create a new user") {
            runBlockingTest {
                whenever(createUserUseCase(any())).thenReturn(testCase)
                if (testCase.isError)
                    whenever(mapper.checkErrorMessage(any())).thenReturn(EMPTY_STRING)

                viewModel.createNewAccount()

                if (testCase.isSuccess) {
                    assertEquals(viewModel.viewState.value, ShowSuccess)
                } else {
                    assertEquals(viewModel.viewState.value, ShowError(EMPTY_STRING))
                }
            }
        }
    }

    @Test
    fun `navigate to Login should update state`() {
        viewModel.navigateToLogin()
        assertEquals(viewModel.viewState.value, NavigateToLogin)
    }

}
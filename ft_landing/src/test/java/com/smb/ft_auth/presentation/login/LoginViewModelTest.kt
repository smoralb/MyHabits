package com.smb.ft_auth.presentation.login

import com.smb.core.data.Result
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_auth.domain.usecase.LoginUseCase
import com.smb.ft_auth.domain.usecase.RecoverPasswordUseCase
import com.smb.ft_auth.navigation.LandingNavigator
import com.smb.ft_auth.presentation.login.LoginState.HideLoading
import com.smb.ft_auth.presentation.login.LoginState.NavigateToMainView
import com.smb.ft_auth.presentation.login.LoginState.NavigateToSignUp
import com.smb.ft_auth.presentation.login.LoginState.ShowError
import com.smb.ft_auth.presentation.login.mapper.LoginMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var recoverPasswordUseCase: RecoverPasswordUseCase

    @Mock
    private lateinit var mapper: LoginMapper

    @Mock
    private lateinit var navigator: LandingNavigator

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun setUp() {
        viewModel =
            LoginViewModel(
                loginUseCase = loginUseCase,
                recoverPassword = recoverPasswordUseCase,
                mapper = mapper,
                navigator = navigator
            )
    }

    @Test
    fun `navigateToSignUp should update state`() {
        runBlockingTest {
            viewModel.navigateToSignUp()
            assertEquals(NavigateToSignUp, viewModel.viewState.value)
        }
    }

    @TestFactory
    fun `recoverPassword should call use case to recover password with result`() = listOf(
        Result.Error(), Result.Success(Unit)
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(recoverPasswordUseCase(any()))
                    .thenReturn(testCase)
                if (testCase.isError)
                    whenever(mapper.checkErrorMessage(any())).thenReturn(EMPTY_STRING)

                viewModel.recoverPassword()

                if (testCase.isSuccess) {
                    assertTrue(viewModel.viewState.value is HideLoading)
                } else {
                    assertTrue(viewModel.viewState.value is ShowError)
                    verify(mapper).checkErrorMessage(any())
                }
            }
            clearInvocations(mapper, recoverPasswordUseCase)
        }
    }

    @TestFactory
    fun `login should call use case to log in with result`() = listOf(
        Result.Error(), Result.Success(Unit)
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(loginUseCase(any())).thenReturn(testCase)
                if (testCase.isError)
                    whenever(mapper.checkErrorMessage(any())).thenReturn(EMPTY_STRING)

                viewModel.login()

                if (testCase.isSuccess) {
                    assertTrue(viewModel.viewState.value is NavigateToMainView)
                } else {
                    assertTrue(viewModel.viewState.value is ShowError)
                    verify(mapper).checkErrorMessage(any())
                }
            }
            clearInvocations(mapper, loginUseCase)
        }
    }
}
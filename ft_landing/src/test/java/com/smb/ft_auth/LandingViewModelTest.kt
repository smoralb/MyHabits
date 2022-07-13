package com.smb.ft_auth

import com.smb.core.data.Result
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_auth.LandingState.NavigateToHome
import com.smb.ft_auth.domain.usecase.CheckSessionUseCase
import com.smb.ft_auth.navigation.LandingNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LandingViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var checkSessionUseCase: CheckSessionUseCase

    @Mock
    private lateinit var landingNavigator: LandingNavigator

    private lateinit var landingViewModel: LandingViewModel

    @BeforeEach
    fun setUp() {
        landingViewModel = LandingViewModel(checkSessionUseCase, landingNavigator)
    }

    @Test
    fun `navigateToHome will navigate to HomeActivity using LandingNavigator`() {
        landingViewModel.navigateToHome()
        verify(landingNavigator).navigateToHomeScreen()
    }

    @TestFactory
    fun `checkUserSession should check session and navigate to Home if session is active`() =
        listOf(
            Result.Success(true), Result.Success(false)
        ).map { testCase ->
            DynamicTest.dynamicTest("with result $testCase") {
                runBlockingTest {
                    whenever(checkSessionUseCase(Unit)).thenReturn(testCase)
                    landingViewModel.checkUserSession()
                    if (testCase.value)
                        assertTrue(landingViewModel.viewState.value == NavigateToHome)
                }
            }
        }
}
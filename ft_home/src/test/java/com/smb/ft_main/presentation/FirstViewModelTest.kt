package com.smb.ft_main.presentation

import com.smb.core.data.Result
import com.smb.core.domain.LogOutUseCase
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_main.domain.usecases.GetSampleDataUseCase
import com.smb.ft_main.presentation.firstView.FirstViewModel
import com.smb.ft_main.presentation.firstView.FirstViewState
import com.smb.ft_main.presentation.firstView.FirstViewState.HideLoading
import com.smb.ft_main.presentation.firstView.mapper.FirstFragmentMapper
import com.smb.ft_main.presentation.mocks.presentationHabitListModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FirstViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var getSampleDataUseCase: GetSampleDataUseCase

    @Mock
    private lateinit var logOutUseCase: LogOutUseCase

    @Mock
    private lateinit var mapper: FirstFragmentMapper

    private lateinit var viewModel: FirstViewModel

    @BeforeEach
    fun setUp() {
        viewModel = FirstViewModel(getSampleDataUseCase, logOutUseCase, mapper)
    }

    @TestFactory
    fun `getSampleData should return sample data `() = listOf(
        Result.Success(presentationHabitListModelMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(getSampleDataUseCase(any())).thenReturn(testCase)

                viewModel.initialize()

                if (testCase.isSuccess) {
                    verify(mapper).mapItems(any(), any())
                } else if (testCase.isError) {
                    assertTrue(viewModel.firstViewModelText.value == "Error")
                }
                assertTrue(viewModel.viewState.value is HideLoading)
            }
            clearInvocations(getSampleDataUseCase, mapper)
        }
    }

    @TestFactory
    fun `logOut should sign out `() = listOf(
        Result.Success(Unit),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(logOutUseCase(any())).thenReturn(testCase)

                viewModel.signOut()

                if (testCase.isSuccess) {
                    assertTrue(viewModel.viewState.value is FirstViewState.NavigateUp)
                }
                clearInvocations(getSampleDataUseCase, mapper)
            }
        }
    }
}
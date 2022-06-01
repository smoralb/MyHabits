package com.smb.myhabits.presentation

import com.smb.core.data.Result
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.myhabits.domain.usecases.GetSampleDataUseCase
import com.smb.myhabits.presentation.main.firstView.FirstViewModel
import com.smb.myhabits.presentation.main.firstView.FirstViewState
import com.smb.myhabits.presentation.main.firstView.FirstViewState.HideLoading
import com.smb.myhabits.presentation.main.firstView.mapper.FirstFragmentMapper
import com.smb.myhabits.presentation.mocks.presentationHabitListModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
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
    private lateinit var mapper: FirstFragmentMapper

    private lateinit var viewModel: FirstViewModel

    @BeforeEach
    fun setUp() {
        viewModel = FirstViewModel(getSampleDataUseCase, mapper)
    }

    @TestFactory
    fun `getSampleData should return sample data `() = listOf(
        Result.Success(presentationHabitListModelMock)
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runTest {
                whenever(getSampleDataUseCase(any())).thenReturn(testCase)

                viewModel.initialize()

                assertTrue(viewModel.viewState.value == FirstViewState.Loading)
                if (testCase.isSuccess) {
                    verify(mapper).mapItems(any(), any())
                }
                assertTrue(viewModel.viewState.value is HideLoading)
            }
            clearInvocations(getSampleDataUseCase, mapper)
        }
    }

}
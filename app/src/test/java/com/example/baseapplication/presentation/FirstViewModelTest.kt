package com.smb.myhabits.presentation

import com.smb.myhabits.domain.usecases.GetSampleDataUseCase
import com.smb.myhabits.presentation.main.firstView.FirstViewModel
import com.smb.myhabits.presentation.main.firstView.FirstViewState
import com.smb.myhabits.presentation.main.firstView.FirstViewState.HideLoading
import com.smb.myhabits.presentation.main.firstView.mapper.FirstFragmentMapper
import com.smb.myhabits.presentation.mocks.sampleResponseChildModelMock
import com.smb.core.data.Result
import com.smb.core.test.BaseViewModelUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
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
        Result.Success(sampleResponseChildModelMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(getSampleDataUseCase(any())).thenReturn(testCase)

                viewModel.initialize()

                when (testCase.isSuccess) {
                    true -> {
                        verify(mapper).mapItems(any(), any())
                        assertEquals(HideLoading, viewModel.viewState.value)
                    }
                    else -> assertEquals(viewModel.firstViewModelText.value, "Error")
                }
            }
            assertTrue(viewModel.viewState.value == HideLoading)
            clearInvocations(getSampleDataUseCase, mapper)
        }
    }

}
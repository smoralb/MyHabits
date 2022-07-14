package com.smb.ft_home.presentation

import com.smb.core.data.Result
import com.smb.core.domain.LogOutUseCase
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_home.domain.usecases.GetTasksUseCase
import com.smb.ft_home.presentation.home.HomeState.AddTask
import com.smb.ft_home.presentation.home.HomeState.HideLoading
import com.smb.ft_home.presentation.home.HomeState.NavigateUp
import com.smb.ft_home.presentation.home.HomeViewModel
import com.smb.ft_home.presentation.home.mapper.FirstFragmentMapper
import com.smb.ft_home.presentation.mocks.presentationHabitListModelMock
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
class HomeViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var getTasksUseCase: GetTasksUseCase

    @Mock
    private lateinit var logOutUseCase: LogOutUseCase

    @Mock
    private lateinit var mapper: FirstFragmentMapper

    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setUp() {
        viewModel = HomeViewModel(getTasksUseCase, logOutUseCase, mapper)
    }

    @TestFactory
    fun `getSampleData should return sample data `() = listOf(
        Result.Success(presentationHabitListModelMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(getTasksUseCase(any())).thenReturn(testCase)

                viewModel.initialize()

                if (testCase.isSuccess) {
                    verify(mapper).mapItems(any(), any())
                } else if (testCase.isError) {
                    assertTrue(viewModel.firstViewModelText.value == "Error")
                }
                assertTrue(viewModel.viewState.value is HideLoading)
            }
            clearInvocations(getTasksUseCase, mapper)
        }
    }

    @Test
    fun `create task will update state to AddTask`() {
        viewModel.createTask()
        assertEquals(viewModel.viewState.value, AddTask)
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
                    assertTrue(viewModel.viewState.value is NavigateUp)
                }
                clearInvocations(getTasksUseCase, mapper)
            }
        }
    }
}
package com.smb.ft_main.presentation

import com.smb.core.data.Result
import com.smb.core.domain.LogOutUseCase
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_main.domain.mocks.habitListModelMock
import com.smb.ft_main.domain.usecases.CreateTaskUseCase
import com.smb.ft_main.domain.usecases.GetTasksUseCase
import com.smb.ft_main.presentation.home.HomeState.HideLoading
import com.smb.ft_main.presentation.home.HomeState.NavigateUp
import com.smb.ft_main.presentation.home.HomeViewModel
import com.smb.ft_main.presentation.home.mapper.FirstFragmentMapper
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
class HomeViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var getTasksUseCase: GetTasksUseCase

    @Mock
    private lateinit var createTaskUseCase: CreateTaskUseCase

    @Mock
    private lateinit var logOutUseCase: LogOutUseCase

    @Mock
    private lateinit var mapper: FirstFragmentMapper

    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setUp() {
        viewModel = HomeViewModel(getTasksUseCase, createTaskUseCase, logOutUseCase, mapper)
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

    @TestFactory
    fun `createTaskUseCase should return sample data `() = listOf(
        Result.Success(Unit) to Result.Success(habitListModelMock),
        Result.Error() to Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                if (testCase.first.isSuccess)
                    whenever(getTasksUseCase(any())).thenReturn(testCase.second)
                whenever(createTaskUseCase(any())).thenReturn(testCase.first)

                viewModel.createTask()

                if (testCase.first.isSuccess) {
                    verify(mapper).mapItems(any(), any())
                }
            }
            clearInvocations(createTaskUseCase, getTasksUseCase, mapper)
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
                    assertTrue(viewModel.viewState.value is NavigateUp)
                }
                clearInvocations(getTasksUseCase, mapper)
            }
        }
    }
}
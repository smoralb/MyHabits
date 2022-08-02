package com.smb.ft_home.presentation

import com.smb.core.data.Result
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_home.domain.usecases.GetTasksUseCase
import com.smb.ft_home.presentation.detail.TaskDetailState.NavigateUp
import com.smb.ft_home.presentation.detail.TaskDetailViewModel
import com.smb.ft_home.presentation.mocks.presentationHabitListModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class TaskDetailViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var getTasksUseCase: GetTasksUseCase

    private lateinit var viewModel: TaskDetailViewModel

    @BeforeEach
    fun setUp() {
        viewModel = TaskDetailViewModel(getTasksUseCase)
    }

    @TestFactory
    fun `getTasks should return sample data `() = listOf(
        Result.Success(presentationHabitListModelMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(getTasksUseCase(any())).thenReturn(testCase)

                viewModel.init(any())

                if (testCase.isSuccess) {
                    assertEquals(viewModel.title.value, "NAME")
                    assertEquals(viewModel.description.value, "details.description")
                    assertEquals(viewModel.publisher.value, "details.name")
                }
            }
            clearInvocations(getTasksUseCase)
        }
    }

    @Test
    fun `navigate up should update viewModel state`() {
        viewModel.navigateUp()
        Assertions.assertTrue(viewModel.viewState.value is NavigateUp)
    }

}
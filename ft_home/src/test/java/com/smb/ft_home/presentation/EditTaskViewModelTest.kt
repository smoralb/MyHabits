package com.smb.ft_home.presentation

import com.smb.core.data.Result
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_home.domain.usecases.UpdateTaskUseCase
import com.smb.ft_home.presentation.edit.EditTaskMapper
import com.smb.ft_home.presentation.edit.EditTaskState.NavigateUp
import com.smb.ft_home.presentation.edit.EditTaskViewModel
import com.smb.ft_home.presentation.mocks.updateTaskModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class EditTaskViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var updateTaskUseCase: UpdateTaskUseCase

    @Mock
    private lateinit var mapper: EditTaskMapper

    private lateinit var viewModel: EditTaskViewModel

    @BeforeEach
    fun setUp() {
        viewModel = EditTaskViewModel(updateTaskUseCase, mapper)
    }


    @TestFactory
    fun `update task should return sample data `() = listOf(
        Result.Success(Unit), Result.Error(),
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(updateTaskUseCase(any())).thenReturn(testCase)
                whenever(mapper.toEditModel(any(), any(), any())).thenReturn(updateTaskModelMock)

                viewModel.updateTasks()

                if (testCase.isSuccess) {
                    assertTrue(viewModel.viewState.value is NavigateUp)
                }

            }
            clearInvocations(updateTaskUseCase)
        }
    }

    @Test
    fun `navigate up should update viewModel state`() {
        viewModel.navigateUp()
        Assertions.assertTrue(viewModel.viewState.value is NavigateUp)
    }
}
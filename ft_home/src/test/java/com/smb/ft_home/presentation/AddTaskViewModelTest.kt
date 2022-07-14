package com.smb.ft_home.presentation

import com.smb.core.data.Result
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_home.domain.mocks.habitListModelMock
import com.smb.ft_home.domain.usecases.CreateTaskUseCase
import com.smb.ft_home.presentation.add.AddTaskViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AddTaskViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var createTaskUseCase: CreateTaskUseCase

    private lateinit var viewModel: AddTaskViewModel

    @BeforeEach
    fun setUp() {
        viewModel = AddTaskViewModel(createTaskUseCase)
    }


    @TestFactory
    fun `createTaskUseCase should return sample data `() = listOf(
        Result.Success(Unit), Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(createTaskUseCase(any())).thenReturn(testCase)

                viewModel.createNewTask()

            }
            clearInvocations(createTaskUseCase)
        }
    }
}
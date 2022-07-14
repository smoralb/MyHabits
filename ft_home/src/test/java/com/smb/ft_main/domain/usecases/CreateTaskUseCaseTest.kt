package com.smb.ft_main.domain.usecases

import com.smb.core.data.Result
import com.smb.core.test.BaseUnitTest
import com.smb.ft_main.domain.mocks.taskModelMock
import com.smb.ft_main.domain.repository.HomeRepository
import com.smb.ft_main.domain.usecases.CreateTaskUseCase.Params
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CreateTaskUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: HomeRepository

    private lateinit var useCase: CreateTaskUseCase

    @BeforeEach
    fun setUp() {
        useCase = CreateTaskUseCaseImpl(repository)
    }

    @TestFactory
    fun `GetSampleDataUseCase should return `() = listOf(
        Result.Success(Unit),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(repository.createTask(taskModelMock)).thenReturn(testCase)

                val result = useCase(Params(taskModelMock))

                Assertions.assertEquals(result.isError, testCase.isError)
                Assertions.assertEquals(result.isSuccess, testCase.isSuccess)
                verify(repository, times(1)).createTask(taskModelMock)
                verifyNoMoreInteractions(repository)
                clearInvocations(repository)
            }
        }
    }
}
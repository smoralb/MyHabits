package com.smb.ft_home.domain.usecases

import com.smb.core.data.Result
import com.smb.core.test.BaseUnitTest
import com.smb.ft_home.domain.mocks.habitListModelMock
import com.smb.ft_home.domain.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
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
class GetTasksUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repositoryImpl: HomeRepository

    private lateinit var useCase: GetTasksUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetTasksUseCaseImpl(repositoryImpl)
    }

    @TestFactory
    fun `GetTasksUseCase should return `() = listOf(
        Result.Success(habitListModelMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(repositoryImpl.getTasks()).thenReturn(testCase)

                val result = useCase(Unit)

                assertEquals(result.isError, testCase.isError)
                assertEquals(result.isSuccess, testCase.isSuccess)
                verify(repositoryImpl, times(1)).getTasks()
                verifyNoMoreInteractions(repositoryImpl)
                clearInvocations(repositoryImpl)
            }
        }
    }
}
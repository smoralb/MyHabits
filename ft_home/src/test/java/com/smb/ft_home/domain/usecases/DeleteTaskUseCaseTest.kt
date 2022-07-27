package com.smb.ft_home.domain.usecases

import com.smb.core.data.Result
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_home.domain.mocks.taskModelMock
import com.smb.ft_home.domain.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DeleteTaskUseCaseTest: BaseUnitTest() {

    @Mock
    private lateinit var repository: HomeRepository

    private lateinit var useCase: DeleteTaskUseCase

    @BeforeEach
    fun setUp() {
        useCase = DeleteTaskUseCaseImpl(repository)
    }

    @TestFactory
    fun `GetSampleDataUseCase should return `() = listOf(
        Result.Success(Unit),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(repository.deleteTask(any())).thenReturn(testCase)

                val result = useCase(DeleteTaskUseCase.Params(EMPTY_STRING))

                Assertions.assertEquals(result.isError, testCase.isError)
                Assertions.assertEquals(result.isSuccess, testCase.isSuccess)
                verify(repository, times(1)).deleteTask(EMPTY_STRING)
                verifyNoMoreInteractions(repository)
                clearInvocations(repository)
            }
        }
    }
}
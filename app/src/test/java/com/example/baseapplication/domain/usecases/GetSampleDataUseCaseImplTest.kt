package com.example.baseapplication.domain

import com.example.baseapplication.data.mocks.sampleResponseChildModelMock
import com.example.baseapplication.domain.repository.SampleDataRepository
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.baseapplication.domain.usecases.GetSampleDataUseCaseImpl
import com.example.core.data.Result
import com.example.core.test.BaseUnitTest
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
class GetSampleDataUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repositoryImpl: SampleDataRepository

    private lateinit var useCase: GetSampleDataUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetSampleDataUseCaseImpl(repositoryImpl)
    }

    @TestFactory
    fun `GetSampleDataUseCase should return `() = listOf(
        Result.Success(sampleResponseChildModelMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(repositoryImpl.getSampleData()).thenReturn(testCase)

                val result = useCase(Unit)

                assertEquals(result.isError, testCase.isError)
                assertEquals(result.isSuccess, testCase.isSuccess)
                verify(repositoryImpl, times(1)).getSampleData()
                verifyNoMoreInteractions(repositoryImpl)
                clearInvocations(repositoryImpl)
            }
        }
    }
}
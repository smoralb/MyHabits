package com.example.baseapplication.data.repository

import com.example.baseapplication.data.mocks.sampleResponseModelValidMock
import com.example.baseapplication.domain.repository.SampleDataRepository
import com.example.baseapplication.data.source.SampleDataRemoteSource
import com.example.core.test.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import com.example.core.data.Result

@ExperimentalCoroutinesApi
class SampleDataRepositoryImplTest: BaseUnitTest() {

    @Mock
    private lateinit var remoteSource: SampleDataRemoteSource

    private lateinit var repository: SampleDataRepository

    @BeforeEach
    fun setUp() {
        repository = SampleDataRepositoryImpl(remoteSource)
    }

    @TestFactory
    fun `repository should call remote source and return `() = listOf(
        Result.Success(sampleResponseModelValidMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase as result") {
            runBlockingTest {
                whenever(remoteSource.getSampleData()).thenReturn(testCase)
                val result = repository.getSampleData()

                if (testCase.isSuccess) {
                    assertTrue(result.isSuccess)
                    assertEquals((result as Result.Success).value, sampleResponseModelValidMock)
                } else {
                    assertTrue(result.isError)
                }
                verifyNoMoreInteractions(remoteSource)
                clearInvocations(remoteSource)
            }
        }
    }
}
package com.smb.myhabits.domain

import com.smb.core.data.Result
import com.smb.core.test.BaseUnitTest
import com.smb.myhabits.data.repository.SampleDataRepositoryImpl
import com.smb.myhabits.data.source.SampleDataRemoteSource
import com.smb.myhabits.domain.mocks.habitListModelMock
import com.smb.myhabits.domain.repository.SampleDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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

@ExperimentalCoroutinesApi
class SampleDataRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteSource: SampleDataRemoteSource

    private lateinit var repository: SampleDataRepository

    @BeforeEach
    fun setUp() {
        repository = SampleDataRepositoryImpl(remoteSource)
    }

    @TestFactory
    fun `repository should call remote source and return `() = listOf(
        Result.Success(habitListModelMock)
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase as result") {
            runBlockingTest {
                whenever(remoteSource.getSampleData()).thenReturn(testCase)
                val result = repository.getSampleData()

                assertTrue(result.isSuccess)
                assertEquals((result as Result.Success).value, habitListModelMock)
                verifyNoMoreInteractions(remoteSource)
                clearInvocations(remoteSource)
            }
        }
    }
}
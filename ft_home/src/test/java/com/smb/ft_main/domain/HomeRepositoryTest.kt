package com.smb.ft_main.domain

import com.smb.core.data.Result
import com.smb.core.test.BaseUnitTest
import com.smb.ft_main.data.repository.HomeRepositoryImpl
import com.smb.ft_main.data.source.HomeRemoteSource
import com.smb.ft_main.domain.mocks.habitListModelMock
import com.smb.ft_main.domain.repository.HomeRepository
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

@ExperimentalCoroutinesApi
class HomeRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteSource: HomeRemoteSource

    private lateinit var repository: HomeRepository

    @BeforeEach
    fun setUp() {
        repository = HomeRepositoryImpl(remoteSource)
    }

    @TestFactory
    fun `repository should call remote source and return `() = listOf(
        Result.Success(habitListModelMock)
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase as result") {
            runBlockingTest {
                whenever(remoteSource.getTasks()).thenReturn(testCase)
                val result = repository.getTasks()

                assertTrue(result.isSuccess)
                assertEquals((result as Result.Success).value, habitListModelMock)
                verifyNoMoreInteractions(remoteSource)
                clearInvocations(remoteSource)
            }
        }
    }
}
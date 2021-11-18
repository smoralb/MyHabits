package com.example.baseapplication.data.source

import com.example.baseapplication.data.SampleApi
import com.example.baseapplication.data.mocks.sampleApiResponseValidEntityMock
import com.example.baseapplication.data.mocks.sampleResponseChildModelMock
import com.example.baseapplication.data.repository.mapper.SampleDataMapper
import com.example.core.extensions.EMPTY_STRING
import com.example.core.test.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class SampleDataRemoteSourceImplTest : BaseUnitTest() {

    @Mock
    private lateinit var api: SampleApi

    @Mock
    private lateinit var mapper: SampleDataMapper

    private lateinit var remoteSource: SampleDataRemoteSource

    @BeforeEach
    fun setUp() {
        remoteSource = SampleDataRemoteSourceImpl(api, mapper)
    }

    @TestFactory
    fun `Call api should return result`() = listOf(
        Response.success(sampleApiResponseValidEntityMock),
        Response.error(500, EMPTY_STRING.toResponseBody())
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            runBlockingTest {

                whenever(api.getSampleData()).thenReturn(testCase)
                if (testCase.isSuccessful)
                    whenever(mapper.toDomainModel(any())).thenReturn(sampleResponseChildModelMock)

                val result = remoteSource.getSampleData()

                when (testCase.isSuccessful) {
                    true -> assertTrue(result.isSuccess)
                    else -> assertTrue(result.isError)
                }
                verify(api, times(1)).getSampleData()
                clearInvocations(api, mapper)
            }
        }
    }

}
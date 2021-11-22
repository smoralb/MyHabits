package com.example.baseapplication.data.mapper

import com.example.baseapplication.data.mocks.sampleApiResponseChildDetailsListNullEntityMock
import com.example.baseapplication.data.mocks.sampleApiResponseNullEntityMock
import com.example.baseapplication.data.mocks.sampleApiResponseValidEntityMock
import com.example.baseapplication.data.mocks.sampleResponseChildModelMock
import com.example.baseapplication.data.mocks.sampleResponseModelEmptyListMock
import com.example.baseapplication.data.mocks.sampleResponseModelEmptyMock
import com.example.baseapplication.data.repository.mapper.SampleDataMapper
import com.example.baseapplication.data.repository.mapper.SampleDataMapperImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class SampleDataMapperTest {

    private lateinit var mapper: SampleDataMapper

    @BeforeEach
    fun setUp() {
        mapper = SampleDataMapperImpl()
    }

    @TestFactory
    fun `mapper should map entity`() = listOf(
        sampleApiResponseValidEntityMock to sampleResponseChildModelMock,
        sampleApiResponseNullEntityMock to sampleResponseModelEmptyListMock,
        sampleApiResponseChildDetailsListNullEntityMock to sampleResponseModelEmptyMock
    ).map { testcase ->
        DynamicTest.dynamicTest(" to model ${testcase.second}") {
            val result = mapper.toDomainModel(testcase.first)

            assertEquals(result, testcase.second)
        }
    }
}
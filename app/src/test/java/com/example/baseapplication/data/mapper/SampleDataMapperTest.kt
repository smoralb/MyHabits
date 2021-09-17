package com.example.baseapplication.data.mapper

import com.example.baseapplication.data.mocks.sampleResponseEntityNullMock
import com.example.baseapplication.data.mocks.sampleResponseEntityValidMock
import com.example.baseapplication.data.mocks.sampleResponseModelEmptyMock
import com.example.baseapplication.data.mocks.sampleResponseModelValidMock
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
        sampleResponseEntityValidMock to sampleResponseModelValidMock,
        sampleResponseEntityValidMock.copy(sampleChildResponseEntity = null) to emptyList(),
        sampleResponseEntityNullMock to sampleResponseModelEmptyMock
    ).map { testcase ->
        DynamicTest.dynamicTest(" to model ${testcase.second}") {
            val result = mapper.toDomainModel(testcase.first)

            assertEquals(result, testcase.second)
        }
    }
}
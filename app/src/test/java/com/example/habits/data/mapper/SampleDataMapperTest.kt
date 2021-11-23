package com.example.habits.data.mapper

import com.example.habits.data.mocks.sampleApiResponseChildDetailsListNullEntityMock
import com.example.habits.data.mocks.sampleApiResponseNullEntityMock
import com.example.habits.data.mocks.sampleApiResponseValidEntityMock
import com.example.habits.data.mocks.sampleResponseChildModelMock
import com.example.habits.data.mocks.sampleResponseModelEmptyListMock
import com.example.habits.data.mocks.sampleResponseModelEmptyMock
import com.example.habits.data.repository.mapper.SampleDataMapper
import com.example.habits.data.repository.mapper.SampleDataMapperImpl
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
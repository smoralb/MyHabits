package com.example.baseapplication.data.repository.mapper

import com.example.baseapplication.data.mocks.sampleResponseEntityNullMock
import com.example.baseapplication.data.mocks.sampleResponseEntityValidMock
import com.example.baseapplication.data.mocks.sampleResponseModelEmptyMock
import com.example.baseapplication.data.mocks.sampleResponseModelValidMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock

class SampleDataMapperImplTest {

    @Mock
    private lateinit var mapper: SampleDataMapper

    @BeforeEach
    fun setUp() {
        mapper = SampleDataMapperImpl()
    }

    @TestFactory
    fun `toDomainModel should map entity(SampleApiResponseEntity) to model(SampleDataModel)` () = listOf(
        sampleResponseEntityValidMock to sampleResponseModelValidMock,
        sampleResponseEntityNullMock to sampleResponseModelEmptyMock
    ).map{ testCase ->
        DynamicTest.dynamicTest(" an return ${testCase.second}") {
            val result = mapper.toDomainModel(testCase.first)
            assertEquals(testCase.second, result)
        }
    }
}
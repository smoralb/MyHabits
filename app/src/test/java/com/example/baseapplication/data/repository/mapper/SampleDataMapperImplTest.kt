package com.smb.myhabits.data.repository.mapper

import com.smb.myhabits.data.mocks.sampleResponseEntityNullMock
import com.smb.myhabits.data.mocks.sampleResponseEntityValidMock
import com.smb.myhabits.data.mocks.sampleResponseModelEmptyMock
import com.smb.myhabits.data.mocks.sampleResponseModelValidMock
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
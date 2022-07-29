package com.smb.ft_home.presentation

import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_home.presentation.edit.EditTaskMapper
import com.smb.ft_home.presentation.edit.EditTaskMapperImpl
import com.smb.ft_home.presentation.mocks.updateTaskModelMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EditTaskMapperTest: BaseUnitTest() {

    private lateinit var mapper: EditTaskMapper

    @BeforeEach
    fun setUp() {
        mapper = EditTaskMapperImpl()
    }

    @Test
    fun `toEditModel should return a valid model from values`() {
        val result = mapper.toEditModel(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING)

        assertEquals(result, updateTaskModelMock)
    }
}
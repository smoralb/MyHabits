package com.example.core.extensions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.core.test.BaseUnitTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.rules.TestRule

class MutableLivedataExtensionTest : BaseUnitTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val stringValue: String = "New value"

    private var liveDataMock: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    @Test
    fun `Update function should update LiveData value`() {
        liveDataMock update stringValue
        assertNotNull(liveDataMock.value)
        assertEquals(stringValue, liveDataMock.value)
    }
}
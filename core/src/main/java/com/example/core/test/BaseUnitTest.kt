package com.example.core.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
@OptIn(ExperimentalCoroutinesApi::class)
open class BaseUnitTest : TestCoroutineScope by TestCoroutineScope() {
/*
    @Rule
    @JvmField
    val injectMocks = TestRule { statement, _ ->
        MockitoAnnotations.openMocks(this)
        statement
    }*/
}
package com.example.core.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
open class BaseViewModelUnitTest : BaseUnitTest() {

    /**
     * To be able to test Livedata objects.
     * This rule runs all Architecture Components-related background jobs in the same thread so that
     * the test results happen synchronously, and in a repeatable order.
     */
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

}
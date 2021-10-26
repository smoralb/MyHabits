package com.example.baseapplication.presentation

import com.example.baseapplication.presentation.main.secondView.SecondViewModel
import com.example.core.test.BaseViewModelUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach

@ExperimentalCoroutinesApi
class SecondViewModelTest : BaseViewModelUnitTest() {

    private lateinit var viewModel: SecondViewModel

    @BeforeEach
    fun setUp() {
        viewModel = SecondViewModel()
    }
}
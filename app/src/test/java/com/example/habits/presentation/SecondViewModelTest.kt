package com.example.habits.presentation

import com.example.habits.presentation.main.secondView.SecondViewModel
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
package com.example.baseapplication.presentation.di

import com.example.baseapplication.presentation.main.firstView.FirstViewModel
import com.example.baseapplication.presentation.main.secondView.SecondViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val presentationModule = module {

    viewModel { FirstViewModel(get()) }
    viewModel { SecondViewModel() }
}
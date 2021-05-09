package com.example.baseapplication.presentation.di

import com.example.baseapplication.presentation.main.firstView.FirstViewModel
import com.example.baseapplication.presentation.main.secondView.SecondViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val presentationModule = module {

    viewModel { FirstViewModel() }
    viewModel { SecondViewModel() }
}
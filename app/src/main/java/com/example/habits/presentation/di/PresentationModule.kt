package com.example.habits.presentation.di

import com.example.habits.presentation.main.firstView.FirstViewModel
import com.example.habits.presentation.main.firstView.mapper.FirstFragmentMapper
import com.example.habits.presentation.main.firstView.mapper.FirstFragmentMapperImpl
import com.example.habits.presentation.main.secondView.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<FirstFragmentMapper> { FirstFragmentMapperImpl() }

    viewModel { FirstViewModel(get(), get()) }
    viewModel { SecondViewModel() }
}
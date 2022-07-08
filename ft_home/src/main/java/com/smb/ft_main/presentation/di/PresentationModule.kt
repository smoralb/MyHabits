package com.smb.ft_main.presentation.di

import com.smb.ft_main.presentation.firstView.FirstViewModel
import com.smb.ft_main.presentation.firstView.mapper.FirstFragmentMapper
import com.smb.ft_main.presentation.firstView.mapper.FirstFragmentMapperImpl
import com.smb.ft_main.presentation.secondView.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<FirstFragmentMapper> { FirstFragmentMapperImpl() }

    viewModel {
        FirstViewModel(
            getSampleDataUseCase = get(),
            logOutUseCase = get(),
            mapper = get()
        )
    }
    viewModel { SecondViewModel(get()) }
}
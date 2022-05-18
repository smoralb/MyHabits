package com.smb.myhabits.presentation.di

import com.smb.myhabits.presentation.main.firstView.FirstViewModel
import com.smb.myhabits.presentation.main.firstView.mapper.FirstFragmentMapper
import com.smb.myhabits.presentation.main.firstView.mapper.FirstFragmentMapperImpl
import com.smb.myhabits.presentation.main.secondView.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<FirstFragmentMapper> { FirstFragmentMapperImpl() }

    viewModel { FirstViewModel(get(), get()) }
    viewModel { SecondViewModel(get()) }
}
package com.smb.ft_main.presentation.di

import com.smb.ft_main.presentation.detail.TaskDetailViewModel
import com.smb.ft_main.presentation.home.HomeViewModel
import com.smb.ft_main.presentation.home.mapper.FirstFragmentMapper
import com.smb.ft_main.presentation.home.mapper.HomeMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homePresentationModule = module {

    factory<FirstFragmentMapper> { HomeMapperImpl() }

    viewModel {
        HomeViewModel(
            getTasksUseCase = get(),
            createTaskUseCase = get(),
            logOutUseCase = get(),
            mapper = get()
        )
    }
    viewModel { TaskDetailViewModel(get()) }
}
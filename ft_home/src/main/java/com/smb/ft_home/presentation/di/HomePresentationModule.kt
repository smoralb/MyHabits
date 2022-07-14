package com.smb.ft_home.presentation.di

import com.smb.ft_home.presentation.add.AddTaskViewModel
import com.smb.ft_home.presentation.detail.TaskDetailViewModel
import com.smb.ft_home.presentation.home.HomeViewModel
import com.smb.ft_home.presentation.home.mapper.FirstFragmentMapper
import com.smb.ft_home.presentation.home.mapper.HomeMapperImpl
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

    viewModel { AddTaskViewModel() }
}
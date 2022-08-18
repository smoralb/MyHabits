package com.smb.ft_home.presentation.di

import com.smb.ft_home.presentation.add.AddTaskMapper
import com.smb.ft_home.presentation.add.AddTaskMapperImpl
import com.smb.ft_home.presentation.add.AddTaskViewModel
import com.smb.ft_home.presentation.detail.TaskDetailViewModel
import com.smb.ft_home.presentation.edit.EditTaskMapper
import com.smb.ft_home.presentation.edit.EditTaskMapperImpl
import com.smb.ft_home.presentation.edit.EditTaskViewModel
import com.smb.ft_home.presentation.home.HomeViewModel
import com.smb.ft_home.presentation.home.mapper.FirstFragmentMapper
import com.smb.ft_home.presentation.home.mapper.HomeMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.Calendar

val homePresentationModule = module {

    factory<FirstFragmentMapper> { HomeMapperImpl() }
    factory<AddTaskMapper> { AddTaskMapperImpl() }
    factory<EditTaskMapper> { EditTaskMapperImpl() }

    viewModel {
        HomeViewModel(
            getTasksUseCase = get(),
            logOutUseCase = get(),
            mapper = get(),
            deleteTaskUseCase = get()
        )
    }
    viewModel { TaskDetailViewModel(getTasksUseCase = get()) }

    viewModel { AddTaskViewModel(createTaskUseCase = get(), mapper = get()) }

    viewModel { EditTaskViewModel(updateTaskUseCase = get(), mapper = get()) }
}
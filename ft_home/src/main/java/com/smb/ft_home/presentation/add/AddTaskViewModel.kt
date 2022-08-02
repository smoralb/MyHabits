package com.smb.ft_home.presentation.add

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_home.R
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.usecases.CreateTaskUseCase
import com.smb.ft_home.presentation.add.AddTaskState.Loading
import com.smb.ft_home.presentation.add.AddTaskState.NavigateUp
import com.smb.ft_home.presentation.add.AddTaskState.ShowError
import com.smb.ft_home.presentation.edit.EditTaskState

class AddTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase
) : BaseViewModel<AddTaskState>() {

    val title: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun createNewTask() {
        _viewState update Loading
        execute {
            createTaskUseCase(
                CreateTaskUseCase.Params(
                    CreateTaskModel(
                        name = title.value!!,
                        description = description.value!!
                    )
                )
            ).fold(
                handleSuccess = {
                    _viewState update NavigateUp
                },
                handleError = {
                    _viewState update ShowError(it.error)
                }
            )
        }
    }

    fun navigateUp() {
        _viewState update NavigateUp
    }
}
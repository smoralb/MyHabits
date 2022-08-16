package com.smb.ft_home.presentation.add

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.usecases.CreateTaskUseCase
import com.smb.ft_home.presentation.add.AddTaskState.Loading
import com.smb.ft_home.presentation.add.AddTaskState.NavigateUp
import com.smb.ft_home.presentation.add.AddTaskState.ShowError

class AddTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase
) : BaseViewModel<AddTaskState>() {

    val title: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val hour: MutableLiveData<Int> = MutableLiveData()
    val minutes: MutableLiveData<Int> = MutableLiveData()
    val buttonClickable: MutableLiveData<Boolean> = MutableLiveData(
        (title.value?.isNotBlank() ?: false && description.value?.isNotBlank() ?: false)
    )

    fun createNewTask() {
        _viewState update Loading
        execute {
            createTaskUseCase(
                CreateTaskUseCase.Params(
                    CreateTaskModel(
                        name = title.value!!,
                        description = description.value!!,
                        hour = hour.value!!.toString(),
                        min = minutes.value!!.toString()
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
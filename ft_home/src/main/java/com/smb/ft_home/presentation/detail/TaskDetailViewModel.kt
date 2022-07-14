package com.smb.ft_home.presentation.detail

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_home.domain.usecases.GetTasksUseCase

class TaskDetailViewModel(
    private val getTasksUseCase: GetTasksUseCase
) : BaseViewModel<TaskDetailState>() {

    val title: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val publisher: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    val onClickListener: () -> Unit = {

    }

    fun init(habitId: String) {
        execute {
            getTasksUseCase(Unit).fold(
                handleError = {},
                handleSuccess = {
                    it.habitList.first() { habitModel ->
                        habitModel.id == habitId
                    }.also { details ->
                        title update "NAME"
                        description update "details.description"
                        publisher update "details.name"
                    }
                }
            )
        }
    }
}
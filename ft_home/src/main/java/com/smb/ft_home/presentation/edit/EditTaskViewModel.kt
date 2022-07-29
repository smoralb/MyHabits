package com.smb.ft_home.presentation.edit

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_home.domain.usecases.UpdateTaskUseCase

class EditTaskViewModel(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val mapper: EditTaskMapper
) : BaseViewModel<EditTaskState>() {

    val title: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    var itemId: String = EMPTY_STRING

    fun initialize(itemId: String) {
        this.itemId = itemId
    }

    fun updateTasks() {
        execute {
            updateTaskUseCase(
                UpdateTaskUseCase.Params(
                    mapper.toEditModel(
                        id = this.itemId,
                        title = this.title.value!!,
                        description = this.description.value!!
                    )
                )
            ).fold(
                handleSuccess = {},
                handleError = {}
            )
        }
    }
}
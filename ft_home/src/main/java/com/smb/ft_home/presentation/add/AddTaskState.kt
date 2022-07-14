package com.smb.ft_home.presentation.add

import com.smb.core.presentation.base.BaseState

sealed class AddTaskState : BaseState() {
    object Loading: AddTaskState()
    object NavigateUp: AddTaskState()
    class ShowError(val message: String): AddTaskState()
}
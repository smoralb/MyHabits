package com.smb.ft_home.presentation.edit

import com.smb.core.presentation.base.BaseState

sealed class EditTaskState: BaseState() {
    object Loading: EditTaskState()
    object NavigateUp: EditTaskState()
}
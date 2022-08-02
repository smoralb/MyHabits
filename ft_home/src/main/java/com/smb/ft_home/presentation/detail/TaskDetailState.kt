package com.smb.ft_home.presentation.detail

import com.smb.core.presentation.base.BaseState

sealed class TaskDetailState : BaseState() {
    object NavigateUp: TaskDetailState()
}
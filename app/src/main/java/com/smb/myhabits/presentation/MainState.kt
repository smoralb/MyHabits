package com.smb.myhabits.presentation

import com.smb.core.presentation.base.BaseState

sealed class MainState : BaseState() {
    object NavigateToHome : MainState()
    object NavigateToLogin : MainState()
}
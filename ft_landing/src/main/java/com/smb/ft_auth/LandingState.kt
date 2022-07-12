package com.smb.ft_auth

import com.smb.core.presentation.base.BaseState

sealed class LandingState : BaseState() {
    object NavigateToHome : LandingState()
}
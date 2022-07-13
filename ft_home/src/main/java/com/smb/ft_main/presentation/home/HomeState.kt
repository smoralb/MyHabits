package com.smb.ft_main.presentation.home

import com.smb.core.presentation.base.BaseState

sealed class HomeState : BaseState() {
    object Loading : HomeState()
    object HideLoading : HomeState()
    object NavigateUp : HomeState()
    data class NavigateToSecondFragment(val isbn: String) : HomeState()
}

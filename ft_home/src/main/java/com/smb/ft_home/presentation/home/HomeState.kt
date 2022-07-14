package com.smb.ft_home.presentation.home

import com.smb.core.presentation.base.BaseState

sealed class HomeState : BaseState() {
    object Loading : HomeState()
    object HideLoading : HomeState()
    object NavigateUp : HomeState()
    data class NavigateToSecondFragment(val id: String) : HomeState()
}

package com.smb.ft_home.presentation.home

import com.smb.core.presentation.base.BaseState

sealed class HomeState : BaseState() {
    object Loading : HomeState()
    object HideLoading : HomeState()
    object EmptyState : HomeState()
    object NavigateUp : HomeState()
    object AddTask : HomeState()
    data class NavigateToSecondFragment(val id: String) : HomeState()
}

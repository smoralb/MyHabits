package com.smb.ft_main.presentation.firstView

import com.smb.core.presentation.base.BaseState

sealed class FirstViewState: BaseState() {
    object Loading: FirstViewState()
    object HideLoading: FirstViewState()
    object NavigateUp: FirstViewState()
    data class NavigateToSecondFragment(val isbn: String): FirstViewState()
}

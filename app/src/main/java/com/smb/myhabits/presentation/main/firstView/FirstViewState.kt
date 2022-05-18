package com.smb.myhabits.presentation.main.firstView

import com.smb.core.presentation.base.BaseState

sealed class FirstViewState: BaseState() {
    object Loading: FirstViewState()
    object HideLoading: FirstViewState()
    data class NavigateToSecondFragment(val isbn: String): FirstViewState()
}

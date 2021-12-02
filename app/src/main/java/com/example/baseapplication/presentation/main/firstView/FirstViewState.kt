package com.example.baseapplication.presentation.main.firstView

import com.example.core.presentation.base.BaseState

sealed class FirstViewState: BaseState() {
    object Loading: FirstViewState()
    object HideLoading: FirstViewState()
    data class NavigateToSecondFragment(val isbn: String): FirstViewState()
}

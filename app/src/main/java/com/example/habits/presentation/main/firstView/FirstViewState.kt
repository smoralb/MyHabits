package com.example.habits.presentation.main.firstView

import com.example.core.presentation.base.BaseState

sealed class FirstViewState: BaseState() {
    object Loading: FirstViewState()
    object HideLoading: FirstViewState()
    object NavigateToSecondFragment: FirstViewState()
}

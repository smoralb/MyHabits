package com.example.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * viewModelScope it attached to the lifecycle of the ViewModel, so this scope will be cancelled
 * when the ViewModel is cleared
 */
fun ViewModel.execute(useCase: suspend () -> Unit) {
    viewModelScope.launch { useCase() }
}
package com.example.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun ViewModel.execute(useCase: suspend () -> Unit) {
    viewModelScope.launch { useCase() }
}
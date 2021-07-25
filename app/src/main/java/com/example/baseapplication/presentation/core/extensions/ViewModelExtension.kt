package com.example.baseapplication.presentation.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapplication.data.core.Result
import kotlinx.coroutines.launch

fun ViewModel.execute(useCase: suspend () -> Unit) {
    viewModelScope.launch { useCase() }
}
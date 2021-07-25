package com.example.baseapplication.domain.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapplication.data.core.Result
import kotlinx.coroutines.launch

fun ViewModel.execute(useCase: suspend () -> Unit) {
    viewModelScope.launch { useCase() }
}
package com.smb.core.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.R

abstract class BaseViewModel<S : BaseState> : ViewModel() {

    protected val _viewState: MutableLiveData<S> = MutableLiveData()

    val headerTitle: MutableLiveData<Int> = MutableLiveData(R.string.app_name)
    val headerIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.ic_back)

    val viewState: LiveData<S>
        get() = _viewState
}
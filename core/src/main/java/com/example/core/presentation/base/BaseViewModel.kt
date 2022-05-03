package com.example.core.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S : BaseState> : ViewModel() {

<<<<<<< HEAD
    protected val _viewState: MutableLiveData<S> = MutableLiveData()
=======
    val viewState: MutableLiveData<S> = MutableLiveData()
>>>>>>> e52c2b9d2aa6eabffb57c8c86b863ed0cc0939f3

}
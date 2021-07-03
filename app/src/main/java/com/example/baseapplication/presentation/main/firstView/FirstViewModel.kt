package com.example.baseapplication.presentation.main.firstView

import androidx.lifecycle.MutableLiveData
import com.example.baseapplication.presentation.base.BaseViewModel

class FirstViewModel : BaseViewModel() {

    var firstViewModelText: MutableLiveData<String> = MutableLiveData("")

    internal fun initialize() {
        firstViewModelText.value = "Hello first fragment"
    }
}
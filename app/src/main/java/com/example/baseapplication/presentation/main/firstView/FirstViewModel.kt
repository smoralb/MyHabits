package com.example.baseapplication.presentation.main.firstView

import androidx.lifecycle.MutableLiveData
import com.example.baseapplication.presentation.base.BaseViewModel

class FirstViewModel : BaseViewModel() {

    val firstViewModelText: MutableLiveData<String> = MutableLiveData("Hello first fragment")

    internal fun initialize() {
        firstViewModelText.value = "Hello first fragment"
    }
}
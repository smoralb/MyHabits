package com.example.baseapplication.presentation.core.extensions

import androidx.lifecycle.MutableLiveData

infix fun <V> MutableLiveData<V>.update(value: V) {
    this.postValue(value)
}
package com.example.baseapplication.presentation.main.firstView

import androidx.lifecycle.MutableLiveData
import com.example.baseapplication.EMPTY_STRING
import com.example.baseapplication.domain.core.execute
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.baseapplication.presentation.base.BaseViewModel

class FirstViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase
) : BaseViewModel() {

    var firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    init {
        getSampleData()
    }

    private fun getSampleData() {
        execute {
            getSampleDataUseCase(GetSampleDataUseCase.Params("sampleId")).fold(
                handleSuccess = {
                    firstViewModelText.value = it.name
                                },
                handleError = {
                    firstViewModelText.value = "Error"
                }
            )
        }
    }
}
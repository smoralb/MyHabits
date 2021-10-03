package com.example.baseapplication.presentation.main.firstView

import androidx.lifecycle.MutableLiveData
import com.example.core.extensions.execute
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.core.extensions.EMPTY_STRING
import com.example.core.extensions.update
import com.example.core.presentation.base.BaseViewModel

class FirstViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase
) : BaseViewModel() {

    var firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun initialize() {
        getSampleData()
    }

    private fun getSampleData() {
        execute {
            getSampleDataUseCase(GetSampleDataUseCase.Params("sampleId")).fold(
                handleSuccess = {
                    firstViewModelText update it.first().name
                                },
                handleError = {
                    firstViewModelText update "Error"
                }
            )
        }
    }
}
package com.example.baseapplication.presentation.main.firstView

import androidx.lifecycle.MutableLiveData
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.core.extensions.EMPTY_STRING
import com.example.core.extensions.execute
import com.example.core.extensions.update
import com.example.core.presentation.base.BaseViewModel

class FirstViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase
) : BaseViewModel<FirstViewState>() {

    var firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun initialize() {
        getSampleData()
    }

    fun navigateToSecondView() {
        _viewState.postValue(FirstViewState.NavigateToSecondFragment)
    }

    private fun getSampleData() {
        _viewState.postValue(FirstViewState.Loading)
        execute {
            getSampleDataUseCase(GetSampleDataUseCase.Params("sampleId")).fold(
                handleSuccess = {
                    firstViewModelText update it.first().name
                    _viewState.postValue(FirstViewState.HideLoading)
                },
                handleError = {
                    firstViewModelText update "Error"
                    _viewState.postValue(FirstViewState.HideLoading)
                }
            )
        }
    }
}
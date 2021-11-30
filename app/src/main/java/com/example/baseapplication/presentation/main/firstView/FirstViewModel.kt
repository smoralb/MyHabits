package com.example.baseapplication.presentation.main.firstView

import androidx.lifecycle.MutableLiveData
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.baseapplication.presentation.main.firstView.adapter.SampleDataItems
import com.example.baseapplication.presentation.main.firstView.mapper.FirstFragmentMapper
import com.example.core.extensions.EMPTY_STRING
import com.example.core.extensions.execute
import com.example.core.extensions.update
import com.example.core.presentation.base.BaseViewModel

class FirstViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase,
    private val mapper: FirstFragmentMapper
) : BaseViewModel<FirstViewState>() {

    val firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val itemList: MutableLiveData<List<SampleDataItems.SampleDataItem>> =
        MutableLiveData(emptyList())

    private val onItemClickListener: (String) -> Unit = {
        _viewState update FirstViewState.Loading
    }

    fun initialize() {
        getSampleData()
    }

    fun navigateToSecondView() {
        _viewState update FirstViewState.NavigateToSecondFragment
    }

    private fun getSampleData() {
        _viewState update FirstViewState.Loading
        execute {
            getSampleDataUseCase(Unit).fold(
                handleSuccess = {
                    itemList update mapper.mapItems(it.bookDetails, onItemClickListener)
                    _viewState update FirstViewState.HideLoading
                },
                handleError = {
                    firstViewModelText update "Error"
                    _viewState update FirstViewState.HideLoading
                }
            )
        }
    }
}
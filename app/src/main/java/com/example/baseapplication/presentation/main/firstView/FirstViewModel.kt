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
        viewState update FirstViewState.NavigateToSecondFragment(isbn = it)
    }

    fun initialize() {
        getSampleData()
    }

    private fun getSampleData() {
        viewState update FirstViewState.Loading
        execute {
            getSampleDataUseCase(Unit).fold(
                handleSuccess = {
                    itemList update mapper.mapItems(it.bookDetails, onItemClickListener)
                    viewState update FirstViewState.HideLoadingSampleApiResponseEntityMocks
                },
                handleError = {
                    firstViewModelText update "Error"
                    viewState update FirstViewState.HideLoading
                }
            )
        }
    }
}
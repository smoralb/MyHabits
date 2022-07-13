package com.smb.ft_main.presentation.home

import androidx.lifecycle.MutableLiveData
import com.smb.core.domain.LogOutUseCase
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_main.domain.usecases.GetSampleDataUseCase
import com.smb.ft_main.presentation.home.HomeState.HideLoading
import com.smb.ft_main.presentation.home.HomeState.Loading
import com.smb.ft_main.presentation.home.HomeState.NavigateToSecondFragment
import com.smb.ft_main.presentation.home.HomeState.NavigateUp
import com.smb.ft_main.presentation.home.adapter.TaskDataItems
import com.smb.ft_main.presentation.home.mapper.FirstFragmentMapper

class HomeViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val mapper: FirstFragmentMapper
) : BaseViewModel<HomeState>() {

    val firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val itemList: MutableLiveData<List<TaskDataItems.TaskDataItem>> =
        MutableLiveData(emptyList())

    private val onItemClickListener: (String) -> Unit = {
        _viewState update NavigateToSecondFragment(isbn = it)
    }

    fun initialize() {
        getSampleData()
    }

    private fun getSampleData() {
        execute {
            _viewState update Loading
            getSampleDataUseCase(Unit).fold(
                handleSuccess = { habitList ->
                    itemList update mapper.mapItems(habitList.habitList, onItemClickListener)
                    _viewState update HideLoading
                },
                handleError = {
                    firstViewModelText update "Error"
                    _viewState update HideLoading
                }
            )
        }
    }

    fun signOut() {
        execute {
            logOutUseCase(Unit).fold(
                handleSuccess = {
                    _viewState update NavigateUp
                },
                handleError = {}
            )
        }
    }
}
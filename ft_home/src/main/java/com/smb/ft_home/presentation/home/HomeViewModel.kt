package com.smb.ft_home.presentation.home

import androidx.lifecycle.MutableLiveData
import com.smb.core.domain.LogOutUseCase
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_home.domain.usecases.DeleteTaskUseCase
import com.smb.ft_home.domain.usecases.DeleteTaskUseCase.Params
import com.smb.ft_home.domain.usecases.GetTasksUseCase
import com.smb.ft_home.presentation.detail.TaskDetailState
import com.smb.ft_home.presentation.home.HomeState.AddTask
import com.smb.ft_home.presentation.home.HomeState.HideLoading
import com.smb.ft_home.presentation.home.HomeState.Loading
import com.smb.ft_home.presentation.home.HomeState.NavigateToSecondFragment
import com.smb.ft_home.presentation.home.HomeState.NavigateUp
import com.smb.ft_home.presentation.home.adapter.TaskDataItems
import com.smb.ft_home.presentation.home.mapper.FirstFragmentMapper

class HomeViewModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val mapper: FirstFragmentMapper
) : BaseViewModel<HomeState>() {

    val firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val itemList: MutableLiveData<List<TaskDataItems.TaskDataItem>> =
        MutableLiveData(emptyList())

    private val onItemClickListener: (String) -> Unit = {
        _viewState update NavigateToSecondFragment(id = it)
    }

    internal fun initialize() {
        getTasks()
    }

    fun createTask() {
        _viewState update AddTask
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

    internal fun getTasks() {
        _viewState update Loading
        execute {
            getTasksUseCase(Unit).fold(
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

    internal fun deleteTask(itemPosition: Int) {
        _viewState update Loading
        execute {
            deleteTaskUseCase(Params(itemList.value!![itemPosition].id)).fold(
                handleSuccess = { getTasks() },
                handleError = {}
            )
        }
    }

    fun navigateUp() {
        _viewState update NavigateUp
    }
}
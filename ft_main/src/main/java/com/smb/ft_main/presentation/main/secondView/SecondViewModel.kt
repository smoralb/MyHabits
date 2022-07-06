package com.smb.ft_main.presentation.main.secondView

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_main.domain.usecases.GetSampleDataUseCase

class SecondViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase
) : BaseViewModel<SecondViewState>() {

    val title: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val publisher: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    val onClickListener: () -> Unit = {

    }

    fun init(habitId: String) {
        execute {
            getSampleDataUseCase(Unit).fold(
                handleError = {},
                handleSuccess = {
                    it.habitList.first() { habitModel ->
                        habitModel.id == habitId
                    }.also { details ->
                        title update "NAME"
                        description update "details.description"
                        publisher update "details.name"
                    }
                }
            )
        }
    }
}
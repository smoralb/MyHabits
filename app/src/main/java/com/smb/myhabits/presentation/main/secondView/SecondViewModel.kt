package com.smb.myhabits.presentation.main.secondView

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.myhabits.domain.usecases.GetSampleDataUseCase

class SecondViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase
) : BaseViewModel<SecondViewState>() {

    val title: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val publisher: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    val onClickListener: () -> Unit = {

    }

    fun init(isbn: String) {
        execute {
            getSampleDataUseCase(Unit).fold(
                handleError = {},
                handleSuccess = {
                    it.bookDetails.first { bookDetails ->
                        bookDetails.isbn == isbn
                    }.also { details ->
                        title update details.title
                        description update details.description
                        publisher update details.publisher
                    }
                }
            )
        }
    }
}
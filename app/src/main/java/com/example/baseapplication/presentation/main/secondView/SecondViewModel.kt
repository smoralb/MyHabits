package com.example.baseapplication.presentation.main.secondView

import androidx.lifecycle.MutableLiveData
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.core.extensions.EMPTY_STRING
import com.example.core.extensions.execute
import com.example.core.extensions.update
import com.example.core.presentation.base.BaseViewModel

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
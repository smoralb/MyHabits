package com.example.baseapplication.domain.usecases

import com.example.baseapplication.domain.model.SampleDataModel
import com.example.core.domain.UseCase

interface GetSampleDataUseCase: UseCase<GetSampleDataUseCase.Params, SampleDataModel> {

    data class Params(val sameDataID: String)
}
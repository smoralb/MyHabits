package com.example.baseapplication.domain.usecases

import com.example.baseapplication.domain.model.SampleChildModel
import com.example.core.domain.UseCase

interface GetSampleDataUseCase: UseCase<GetSampleDataUseCase.Params, SampleChildModel> {

    data class Params(val sameDataID: String)
}
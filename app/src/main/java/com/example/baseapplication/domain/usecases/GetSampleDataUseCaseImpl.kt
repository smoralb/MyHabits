package com.example.baseapplication.domain.usecases

import com.example.core.data.Result
import com.example.baseapplication.data.repository.SampleDataRepository
import com.example.baseapplication.domain.model.SampleDataModel

class GetSampleDataUseCaseImpl(
    private val repository: SampleDataRepository
) : GetSampleDataUseCase {

    override suspend fun invoke(params: GetSampleDataUseCase.Params): Result<SampleDataModel> =
        repository.getSampleData()
}
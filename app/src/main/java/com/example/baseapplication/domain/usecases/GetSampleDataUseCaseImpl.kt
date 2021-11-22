package com.example.baseapplication.domain.usecases

import com.example.baseapplication.domain.model.SampleChildModel
import com.example.baseapplication.domain.repository.SampleDataRepository
import com.example.core.data.Result

class GetSampleDataUseCaseImpl(
    private val repository: SampleDataRepository
) : GetSampleDataUseCase {

    override suspend fun invoke(params: Unit): Result<SampleChildModel> =
        repository.getSampleData()
}